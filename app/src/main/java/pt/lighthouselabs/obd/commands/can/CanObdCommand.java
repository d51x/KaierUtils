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

import java.util.ArrayList;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.commands.ObdMultiCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;


public class CanObdCommand extends ObdCommand {

    private ArrayList<Integer> result = null;

    /**
     * Default ctor.
     */
    public CanObdCommand(String cmd) {
        super(cmd);
    }

    public CanObdCommand(String cmd, String header) {
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

    public ArrayList<Integer> getBuffer() {
        return result;
    }

    @Override
    public String getName() {
        return AvailableCommandNames.ENGINE_RPM.getValue();
    }

    @Override
    public String getFormattedResult() {
        return getResult();
    }
}
