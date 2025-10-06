package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.ObdCombineMeterUtils.getMeterEngineRpm;
import static ru.d51x.kaierutils.OBD2.ObdCombineMeterUtils.getMeterFuelLevel;
import static ru.d51x.kaierutils.OBD2.ObdCombineMeterUtils.getMeterMileage;
import static ru.d51x.kaierutils.OBD2.ObdCombineMeterUtils.getMeterServiceReminder;
import static ru.d51x.kaierutils.OBD2.ObdCombineMeterUtils.getMeterTrips;
import static ru.d51x.kaierutils.OBD2.ObdCombineMeterUtils.getMeterVehicleSpeed;
import static ru.d51x.kaierutils.OBD2.ObdCombineMeterUtils.getMeterVoltage;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21A1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21A2;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21A6;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21A8;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21AD;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21AE;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21AF;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_6A0_PID_21BC;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A2;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A6;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21A8;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21AD;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21AE;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21AF;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_COMBINE_METER_21BC;
import static ru.d51x.kaierutils.utils.MessageUtils.sendMessage;

import android.os.Handler;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;

import ru.d51x.kaierutils.Data.CombineMeterData;

public class ObdCombineMeter {
    private static final String TAG = "OBD2Meter";
    private static void processPid21A1(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 4)) return;

        float speed = getMeterVehicleSpeed(buffer);
        CombineMeterData combineMeterData = new CombineMeterData();
        combineMeterData.setVehicleSpeed(speed);
        sendMessage(mHandler, msgId, combineMeterData);

        Log.d(TAG, "6A0 21A1 Speed: " + speed);
    }

    private static void processPid21A2(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 4)) return;

        int rpm =  getMeterEngineRpm(buffer);
        CombineMeterData combineMeterData = new CombineMeterData();
        combineMeterData.setEngineRpm(rpm);
        sendMessage(mHandler, msgId, combineMeterData);

        Log.d(TAG, "6A0 21A2 RPM: " + rpm);
    }

    private static void processPid21A3(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 5)) return;

        int level = getMeterFuelLevel(buffer);
        CombineMeterData combineMeterData = new CombineMeterData();
        combineMeterData.setFuelLevel(level);
        sendMessage(mHandler, msgId, combineMeterData);

        Log.d(TAG, "6A0 21A3 Fuel Level: " + level);
    }

    private static void processPid21A6(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 5)) return;
    }

    private static void processPid21A8(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 5)) return;

        boolean turnLeft =  (buffer.get(2) & (1 << 4)) > 0;
        boolean turnRight =  (buffer.get(2) & (1 << 3)) > 0;

        boolean frontFog =  (buffer.get(3) & (1 << 0)) > 0;
        boolean rearFog =  (buffer.get(5) & (1 << 0)) > 0;

        boolean highBeam =  (buffer.get(4) & (1 << 7)) > 0;

        boolean handBreak =  (buffer.get(4) & (1 << 5)) > 0;

        boolean batterySignal =  (buffer.get(4) & (1 << 2)) > 0;
        boolean espSignal =  (buffer.get(4) & (1 << 3)) > 0;

        Log.d(TAG, "6A0 21A8 Turn Left: " + turnLeft);
        Log.d(TAG, "6A0 21A8 Turn Right: " + turnRight);
        Log.d(TAG, "6A0 21A8 Front Fog: " + frontFog);
        Log.d(TAG, "6A0 21A8 Rear Fog: " + rearFog);
        Log.d(TAG, "6A0 21A8 High Beam: " + highBeam);
        Log.d(TAG, "6A0 21A8 Hand Break: " + handBreak);
        Log.d(TAG, "6A0 21A8 Battery Indicator: " + batterySignal);
        Log.d(TAG, "6A0 21A8 Esp Indicator: " + espSignal);
    }

    private static void processPid21AD(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 5)) return;
        int mileage = getMeterMileage(buffer);
        CombineMeterData combineMeterData = new CombineMeterData();
        combineMeterData.setMileage(mileage);
        sendMessage(mHandler, msgId, combineMeterData);
        Log.d(TAG, "6A0 21AD Mileage: " + mileage);
    }

    private static void processPid21AE(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 8)) return;
        Pair<Float, Float> trips = getMeterTrips(buffer);
        CombineMeterData combineMeterData = new CombineMeterData();
        combineMeterData.setTripA(trips.first);
        combineMeterData.setTripB(trips.second);
        sendMessage(mHandler, msgId, combineMeterData);
        Log.d(TAG, "6A0 21AE Trip A: " + trips.first);
        Log.d(TAG, "6A0 21AE Trip B: " + trips.second);
    }

    private static void processPid21AF(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 3)) return;
        float voltage = getMeterVoltage(buffer);
        CombineMeterData combineMeterData = new CombineMeterData();
        combineMeterData.setVoltage(voltage);
        sendMessage(mHandler, msgId, combineMeterData);
        Log.d(TAG, "6A0 21AF Meter Voltage: " + voltage);
    }

    private static void processPid21BC(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 7)) return;
        Pair<Integer, Integer> serviceReminder = getMeterServiceReminder(buffer);
        int km = serviceReminder.first;
        int month = serviceReminder.second;
        CombineMeterData combineMeterData = new CombineMeterData();
        combineMeterData.setServiceReminderDistance(serviceReminder.first);
        combineMeterData.setServiceReminderPeriod(serviceReminder.second);
        sendMessage(mHandler, msgId, combineMeterData);

        Log.d(TAG, "6A0 21BC Service Reminder Km: " + km);
        Log.d(TAG, "6A0 21BC Service Reminder Month: " + month);
    }

    public static void processResult(Handler mHandler, String pid, ArrayList<Integer> buffer) {
        switch (pid) {
            case BLOCK_6A0_PID_21A1:
                processPid21A1(MESSAGE_OBD_COMBINE_METER_21A1, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21A2:
                processPid21A2(MESSAGE_OBD_COMBINE_METER_21A2, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21A3:
                processPid21A3(MESSAGE_OBD_COMBINE_METER_21A3, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21A6:
                processPid21A6(MESSAGE_OBD_COMBINE_METER_21A6, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21A8:
                processPid21A8(MESSAGE_OBD_COMBINE_METER_21A8, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21AD:
                processPid21AD(MESSAGE_OBD_COMBINE_METER_21AD, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21AE:
                processPid21AE(MESSAGE_OBD_COMBINE_METER_21AE, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21AF:
                processPid21AF(MESSAGE_OBD_COMBINE_METER_21AF, mHandler, buffer);
                break;
            case BLOCK_6A0_PID_21BC:
                processPid21BC(MESSAGE_OBD_COMBINE_METER_21BC, mHandler, buffer);
                break;
        }
    }
}
