/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.lighthouselabs.obd.commands.can;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;
import pt.lighthouselabs.obd.exceptions.BusInitException;
import pt.lighthouselabs.obd.exceptions.MisunderstoodCommandException;
import pt.lighthouselabs.obd.exceptions.NoDataException;
import pt.lighthouselabs.obd.exceptions.ObdResponseException;
import pt.lighthouselabs.obd.exceptions.StoppedException;
import pt.lighthouselabs.obd.exceptions.UnableToConnectException;
import pt.lighthouselabs.obd.exceptions.UnknownObdErrorException;


public class CanObdCommand extends ObdCommand {

    private ArrayList<Integer> result = null;

    private Class[] ERROR_CLASSES = {
            UnableToConnectException.class,
            BusInitException.class,
            MisunderstoodCommandException.class,
            NoDataException.class,
            StoppedException.class,
            UnknownObdErrorException.class
    };

    /**
     * Default ctor.
     */
    public CanObdCommand(String cmd) {
        super(cmd);
    }

   /**
     * Copy ctor.
     *
     * @param other a {@link CanObdCommand} object.
     */
    public CanObdCommand(CanObdCommand other) {
        super(other);
    }

    @Override
    protected void performCalculations() {
        // ignore first two bytes [41 0C] of the response
        result = buffer;
    }

    @Override
    public ArrayList<Integer> getBuffer() {
        return buffer;
    }

    // get raw data
    @Override
    protected void readResult(InputStream in) throws IOException {
        readRawData(in);
        // new.... try to fix crashes when IG Off
        checkForErrors();
    }

    @Override
    protected void readRawData(InputStream in) throws IOException {
        byte b = 0;
        StringBuilder res = new StringBuilder();

        // read until '>' arrives
        while ((char) (b = (byte) in.read()) != '>')
            res.append((char) b);

        // after that we have
        /*
        command: 21 03
        answer:
            012                         1st line - answer size in HEX (i.e. 18)
            0: 61 03 02 02 1C 7F        2nd line and so on - answer with two bytes header (61 03) and data in next lines
            1: EA 00 00 00 00 F5 73
            2: 53 00 51 00 00 00 00

            or

        command: 21 10
        answer:
                025
                0: 61 10 00 00 04 26
                1: 2F 08 00 0A 00 00 2F
                2: 34 00 00 00 06 00 15
                3: FF 00 00 00 00 12 24
                4: 92 00 00 DF 51 00 00
                5: 00 00 00 00 00 00 00
         */
        rawData = res.toString().trim();

       boolean simple = ( rawData.indexOf(":") > 0 ) ? false : true;

        // get answer size
        if ( simple ) {
          buffer.clear();

            rawData = rawData.replaceAll("\\s", "");
            int begin = 0;
            int end = 2;
            String tmp = rawData;
            while (end <= tmp.length()) {
                String s = tmp.substring(begin, end);
                buffer.add(Integer.decode("0x" + s));
                //rawData += rawData.substring(begin, end) + " ";
                begin = end;
                end += 2;
            }
        } else {
            String[] rawDataArray = rawData.split("\\r");
            buffer.clear();
            rawData = "";
            int size = Integer.decode("0x" + rawDataArray[0].trim());
            for (int i = 1; i < rawDataArray.length; i++) {
                String tmp = rawDataArray[i].trim();
                tmp = tmp.substring(tmp.indexOf(":") + 1);
                tmp = tmp.trim();
                tmp = tmp.replaceAll("\\s", "");
                int begin = 0;
                int end = 2;
                while (end <= tmp.length()) {
                    buffer.add(Integer.decode("0x" + tmp.substring(begin, end)));
                    rawData += tmp.substring(begin, end) + " ";
                    begin = end;
                    end += 2;
                }
            }
        }
    }

    @Override
    protected void checkForErrors() {
        for (Class<? extends ObdResponseException> errorClass : ERROR_CLASSES) {
            ObdResponseException messageError;

            try {
                messageError = errorClass.newInstance();
                messageError.setCommand(this.cmd);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            if (messageError.isError(rawData)) {
                throw messageError;
            }
        }
    }

    @Override
    public String getName() {
        return "CAN OBD command: " + cmd;
    }

    @Override
    public String getFormattedResult() {
        return rawData;
    }
}
