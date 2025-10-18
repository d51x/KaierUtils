package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_763_PID_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_PARKING_SENSORS;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class OdbParking {
    public static final String TAG = "OdbParking";
    private static void processPid2101(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();
        message.what = msgId;
        message.obj = buffer;
        mHandler.sendMessage(message);
    }

    public static void processResult(Handler mHandler, String pid, ArrayList<Integer> buffer) {
        if ( BLOCK_763_PID_2101.equalsIgnoreCase(pid) ) {
            OdbParking.processPid2101(MESSAGE_OBD_PARKING_SENSORS, mHandler, buffer);
        }
    }
}
