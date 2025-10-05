package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E1_PID_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E1_PID_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CVT_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CVT_2110;
import static ru.d51x.kaierutils.utils.MessageUtils.sendMessage;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import ru.d51x.kaierutils.Data.CvtData;

public class ObdCvt {
    private static final String TAG = "OBD2Cvt";
    private static void processPid2103(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 16)) return;
        int N = buffer.get(15);
        int oilTemperature = Math.round( -21.592f + (1.137f * N) - (0.0063f * N * N) + (0.0000195f * N * N * N));
        CvtData cvtData = new CvtData();
        cvtData.setTemperature(oilTemperature);
        sendMessage(mHandler, msgId, cvtData);
        Log.d(TAG, "7E1 2103 Cvt Oil Temperature: " + oilTemperature);
    }

    private static void processPid2110(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 32)) return;

        int oilDegradation = buffer.get(29) * 65536 + buffer.get(30) * 256 + buffer.get(31);
        float workHoursTotal = (buffer.get(6) * 256 + buffer.get(7)) / 6.0f; //           (data[6] << 8 | data[7]) / 6
        float workHoursHot = (buffer.get(26) * 256 + buffer.get(27)) / 6.0f; //         (data[26] << 8 | data[27]) / 6

        CvtData cvtData = new CvtData();
        cvtData.setOilDegradation(oilDegradation);
        cvtData.setWorkHoursHot(workHoursHot);
        cvtData.setWorkHoursTotal(workHoursTotal);

        sendMessage(mHandler, msgId, cvtData);

        Log.d(TAG, "7E1 2110 Oil Degradation Level: " + oilDegradation);
        Log.d(TAG, "7E1 2110 Work Hours Total: " + workHoursTotal);
        Log.d(TAG, "7E1 2110 Work Hours Hot: " + workHoursHot);
    }

    public static void processResult(Handler mHandler, String pid, ArrayList<Integer> buffer) {
        switch (pid) {
            case BLOCK_7E1_PID_2103:
                processPid2103(MESSAGE_OBD_CVT_2103, mHandler, buffer);
                break;
            case BLOCK_7E1_PID_2110:
                processPid2110(MESSAGE_OBD_CVT_2110, mHandler, buffer);
                break;
        }
    }
}
