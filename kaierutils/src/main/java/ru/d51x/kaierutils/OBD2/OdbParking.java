package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.OBDII.MESSAGE_OBD_CAN_PARKING_SENSORS;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_763_PID_2101;

import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;

public class OdbParking {
    public static final String TAG = "OBD2Parking";
    private static void processPid2101(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();
        message.what = msg_id;
        message.obj = buffer;
        mHandler.sendMessage(message);
    }

    public static void processResult(Handler mHandler, String pid, ArrayList<Integer> buffer) {
        if ( BLOCK_763_PID_2101.equalsIgnoreCase(pid) ) {
            OdbParking.processPid2101(MESSAGE_OBD_CAN_PARKING_SENSORS, mHandler, buffer);
        }
    }
}
