package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_211D;
import static ru.d51x.kaierutils.OBD2.ObdConstants.BLOCK_7E0_PID_211E;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_211D;
import static ru.d51x.kaierutils.OBD2.ObdConstants.MESSAGE_OBD_ENGINE_211E;
import static ru.d51x.kaierutils.OBD2.ObdEngineUtils.getAirFlowSensore;
import static ru.d51x.kaierutils.OBD2.ObdEngineUtils.getEngineAcFanState;
import static ru.d51x.kaierutils.OBD2.ObdEngineUtils.getEngineCoolantTemperature;
import static ru.d51x.kaierutils.OBD2.ObdEngineUtils.getEngineFuelRelay;
import static ru.d51x.kaierutils.OBD2.ObdEngineUtils.getEngineRpm;
import static ru.d51x.kaierutils.OBD2.ObdEngineUtils.getEngineSpeed;
import static ru.d51x.kaierutils.OBD2.ObdEngineUtils.getEngineVoltage;
import static ru.d51x.kaierutils.utils.MessageUtils.sendMessage;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;

import ru.d51x.kaierutils.Data.CanMmcData;
import ru.d51x.kaierutils.Data.EngineData;

public class ObdEngine {
    public static final String TAG = "OBD2Engine";

    private static void processPid2101(int msgId, Handler handler, ArrayList<Integer> buffer) {
        if ( buffer.isEmpty() || (buffer.size() < 6) ) return;
        float voltage = getEngineVoltage(buffer);
        int speed = getEngineSpeed(buffer);
        int rpm =  getEngineRpm(buffer);

        EngineData engineData = new EngineData();
        engineData.setVoltage(voltage);
        engineData.setSpeed(speed);
        engineData.setRpm(rpm);
        sendMessage(handler, msgId, engineData);

        Log.d(TAG, "7E0 2101 Engine voltage: " + voltage);
        Log.d(TAG, "7E0 2101 Vehicle speed: " + speed);
        Log.d(TAG, "7E0 2101 Engine rpm: " + rpm);
    }

    private static void processPid2102(int msgId, Handler handler, ArrayList<Integer> buffer) {
        if ( buffer.isEmpty() || (buffer.size() < 4) ) return;
        int temp = getEngineCoolantTemperature(buffer) ;

        EngineData engineData = new EngineData();
        engineData.setCoolantTemperature(temp);
        sendMessage(handler, msgId, engineData);

        Log.d(TAG, "7E0 2102 Coolant Temp: " + temp);
    }

    private static void processPid2103(int msgId, Handler handler, ArrayList<Integer> buffer) {
        if ( buffer.isEmpty() || (buffer.size() < 5) ) return;
        float airFlow = getAirFlowSensore(buffer);
        EngineData engineData = new EngineData();
        engineData.setAirFlowSensor(airFlow);
        sendMessage(handler, msgId, engineData);

        Log.d(TAG, "7E0 2103 Air Flow Sensor (V): " + airFlow);
    }

    private static void processPid211D(int msgId, Handler handler, ArrayList<Integer> buffer) {
        // Выключатель кондиционера 211D {A:3}
        if ( buffer.isEmpty() || (buffer.size() < 3) ) return;
        CanMmcData.State state = getEngineAcFanState(buffer);
        EngineData engineData = new EngineData();
        engineData.setAcFanRelay(state);
        sendMessage(handler, msgId, engineData);
    }

    private static void processPid211E(int msgId, Handler handler, ArrayList<Integer> buffer) {
        if ( buffer.isEmpty() || (buffer.size() < 3) ) return;
        CanMmcData.State state = getEngineFuelRelay(buffer);
        EngineData engineData = new EngineData();
        engineData.setFuelPumpRelay(state);
        sendMessage(handler, msgId, engineData);
    }

    public static void processResult(Handler handler, String pid, ArrayList<Integer> buffer) {
        switch (pid) {
            case BLOCK_7E0_PID_2101:
                processPid2101(MESSAGE_OBD_ENGINE_2101, handler, buffer);
                break;
            case BLOCK_7E0_PID_2102:
                processPid2102(MESSAGE_OBD_ENGINE_2102, handler, buffer);
                break;
            case BLOCK_7E0_PID_2103:
                processPid2103(MESSAGE_OBD_ENGINE_2103, handler, buffer);
                break;
            case BLOCK_7E0_PID_211D:
                processPid211D(MESSAGE_OBD_ENGINE_211D, handler, buffer);
                break;
            case BLOCK_7E0_PID_211E:
                processPid211E(MESSAGE_OBD_ENGINE_211E, handler, buffer);
                break;
        }
    }
}
