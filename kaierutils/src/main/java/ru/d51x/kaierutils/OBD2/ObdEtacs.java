package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_620_PID_1A87;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_620_PID_1A88;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_620_PID_1A90;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_620_PID_1A9C;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CVT_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CVT_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ETACS_1A87;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ETACS_1A88;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ETACS_1A90;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ETACS_1A9C;
import static ru.d51x.kaierutils.utils.MessageUtils.sendMessage;
import static ru.d51x.kaierutils.utils.SecurityUtils.getDiagVersion;
import static ru.d51x.kaierutils.utils.SecurityUtils.getPartNumber;
import static ru.d51x.kaierutils.utils.SecurityUtils.getVIN;
import static ru.d51x.kaierutils.utils.StringUtils.bufferToHex;
import static ru.d51x.kaierutils.utils.StringUtils.toByteArray;

import android.os.Handler;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

import ru.d51x.kaierutils.Data.CvtData;
import ru.d51x.kaierutils.Data.EtacsData;

public class ObdEtacs {

    private static final String TAG = "ObdEtacs";

    public static void processResult(Handler mHandler, String pid, ArrayList<Integer> buffer) {
        switch (pid) {
            case BLOCK_620_PID_1A87:
                processPartNumber(MESSAGE_OBD_ETACS_1A87, mHandler, buffer);
                break;
            case BLOCK_620_PID_1A9C:
                processPartNumber(MESSAGE_OBD_ETACS_1A9C, mHandler, buffer);
                break;
            case BLOCK_620_PID_1A88:
                processCurrentVIN(MESSAGE_OBD_ETACS_1A88, mHandler, buffer);
                break;
            case BLOCK_620_PID_1A90:
                processOriginalVIN(MESSAGE_OBD_ETACS_1A90, mHandler, buffer);
                break;
        }
    }



    private static void processPartNumber(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 22)) return;

        int diagVersion = getDiagVersion(buffer); //           (data[5] << 8 | data[5])
        String partNumber = getPartNumber(buffer);
        EtacsData etacsData = new EtacsData();
        etacsData.setDiagVersion(diagVersion);
        etacsData.setPartNumber(partNumber);

        sendMessage(mHandler, msgId, etacsData);

        Log.d(TAG, "620 1A87 DiagVersion: " + diagVersion);
        Log.d(TAG, "620 1A87 Part Number: " + partNumber);
    }

    private static void processOriginalVIN(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 19)) return;
        String vin = getVIN(buffer);
        EtacsData etacsData = new EtacsData();
        etacsData.setOriginalVIN(vin);
        sendMessage(mHandler, msgId, etacsData);
        Log.d(TAG, "620 1A90 Original VIN: " + vin);
    }

    private static void processCurrentVIN(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 19)) return;
        String vin = getVIN(buffer);
        EtacsData etacsData = new EtacsData();
        etacsData.setCurrentVIN(vin);
        sendMessage(mHandler, msgId, etacsData);
        Log.d(TAG, "620 1A88 Current VIN: " + vin);
    }
}
