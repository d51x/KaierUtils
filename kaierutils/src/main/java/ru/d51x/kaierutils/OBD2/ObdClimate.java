package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcAirThermoSensor;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcAmbientTemperature;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcBlowMode;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcCoolantTemperature;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcEngineRpm;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcExternalTemperature;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcFanMode;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcInteriorTemperature;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcLeak;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcLeak20;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcPressure;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcSetTemperature;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcSolarSensor;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcState;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcSystemWorkTime;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getAcVehicleSpeed;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getBlowDirection;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getDefoggerState;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getFanSpeed;
import static ru.d51x.kaierutils.OBD2.ObdClimateUtils.getRecirculationState;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2111;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2113;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2132;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2160;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2161;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_688_PID_2180;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2111;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2113;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2132;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2160;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2161;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_CLIMATE_2180;
import static ru.d51x.kaierutils.utils.MessageUtils.sendMessage;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.Data.ClimateData;

public class ObdClimate {
    public static final String TAG = "OBD2Climate";

    private static void processPid2110(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 7)) return;

        float interiorTemp = getAcInteriorTemperature(buffer);
        float airThermoSensor = getAcAirThermoSensor(buffer);
        float pressure = getAcPressure(buffer);
        float solarSensor = getAcSolarSensor(buffer);
        int coolantTemp = getAcCoolantTemperature(buffer);

        ClimateData climateData = new ClimateData();
        sendMessage(mHandler, msgId, climateData);

        Log.d(TAG, "688 2110 Interior Temperature: " + interiorTemp);
        Log.d(TAG, "688 2110 Air Thermo Sensor: " + airThermoSensor);
        Log.d(TAG, "688 2110 Pressure, MPa: " + pressure);
        Log.d(TAG, "688 2110 Solar Sensor: " + solarSensor);
        Log.d(TAG, "688 2110 Coolant Temperature: " + coolantTemp);
    }

    private static void processPid2111(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if ( buffer.isEmpty() || (buffer.size() < 3)) return;

        float temp = getAcAmbientTemperature(buffer);
        ClimateData climateData = new ClimateData();
        climateData.ambientTemperature = temp;

        sendMessage(mHandler, msgId, climateData);

        Log.d(TAG, "688 2111 Ambient Temperature: " + temp);
    }

    private static void processPid2113(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 7)) return;

        float externalTemp = getAcExternalTemperature(buffer);
        int rpm = getAcEngineRpm(buffer);
        float speed = getAcVehicleSpeed(buffer);

        ClimateData climateData = new ClimateData();
        climateData.externalTemperature = externalTemp;
        climateData.engineRpm = rpm;
        climateData.vehicleSpeed = speed;
        sendMessage(mHandler, msgId, climateData);

        Log.d(TAG, "688 2113 External Temperature: " + externalTemp);
        Log.d(TAG, "688 2113 Rpm: " + rpm);
        Log.d(TAG, "688 2113 Speed: " + speed);
    }

    private static void processPid2132(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 3)) return;

        boolean leak =  getAcLeak(buffer);
        boolean leak20 =  getAcLeak20(buffer);

        ClimateData climateData = new ClimateData();
        climateData.leak = leak;
        climateData.leak20 = leak20;
        sendMessage(mHandler, msgId, climateData);

        Log.d(TAG, "688 2132 Leak Indicator: " + leak);
        Log.d(TAG, "688 2132 Leak Indicator 20%: " + leak20);
    }

    private static void processPid2160(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 4)) return;
        ClimateData climateData = new ClimateData();

        climateData.fan_mode = getAcFanMode(buffer);
        climateData.blow_mode = getAcBlowMode(buffer);
        climateData.temperature = getAcSetTemperature(buffer);
        sendMessage(mHandler, msgId, climateData);
    }

    private static void processPid2161(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 4)) return;
        ClimateData climateData = new ClimateData();

        climateData.blow_direction = getBlowDirection(buffer);
        climateData.fan_speed = getFanSpeed(buffer);
        climateData.ac_state = getAcState(buffer);
        climateData.recirculation_state = getRecirculationState(buffer);
        climateData.defogger_state = getDefoggerState(buffer);

        sendMessage(mHandler, msgId, climateData);
    }

    private static void processPid2180(int msgId, Handler mHandler, ArrayList<Integer> buffer) {
        if (buffer.isEmpty() || (buffer.size() < 5)) return;

        long condSysWorkTime = getAcSystemWorkTime(buffer) ;
        ClimateData climateData = new ClimateData();
        climateData.condSysWorkTime = condSysWorkTime;

        sendMessage(mHandler, msgId, climateData);

        Log.d(TAG, "688 2180 AC System Work Time Sec: " + condSysWorkTime);
    }

    public static void processResult(Handler mHandler, String pid, ArrayList<Integer> buffer) {
        switch (pid) {
            case BLOCK_688_PID_2110:
                processPid2110(MESSAGE_OBD_CLIMATE_2110, mHandler, buffer);
                break;
            case BLOCK_688_PID_2111:
                processPid2111(MESSAGE_OBD_CLIMATE_2111, mHandler, buffer);
                break;
            case BLOCK_688_PID_2113:
                processPid2113(MESSAGE_OBD_CLIMATE_2113, mHandler, buffer);
                break;
            case BLOCK_688_PID_2132:
                processPid2132(MESSAGE_OBD_CLIMATE_2132, mHandler, buffer);
                break;
            case BLOCK_688_PID_2160:
                processPid2160(MESSAGE_OBD_CLIMATE_2160, mHandler, buffer);
                break;
            case BLOCK_688_PID_2161:
                processPid2161(MESSAGE_OBD_CLIMATE_2161, mHandler, buffer);
                break;
            case BLOCK_688_PID_2180:
                processPid2180(MESSAGE_OBD_CLIMATE_2180, mHandler, buffer);
                break;
        }
    }

}
