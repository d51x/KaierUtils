package ru.d51x.kaierutils.OBD2;

import java.util.ArrayList;

import ru.d51x.kaierutils.Data.CanMmcData;

public class ObdEngineUtils {
    public static float getEngineVoltage(ArrayList<Integer> buffer) {
        return (buffer.get(2) * 18.68f) / 255.0f;
    }

    public static int getEngineSpeed(ArrayList<Integer> buffer) {
        return buffer.get(3) * 2;
    }

    public static int getEngineRpm(ArrayList<Integer> buffer) {
        return (buffer.get(4) * 256 + buffer.get(5)) / 8;
    }

    public static int getEngineCoolantTemperature(ArrayList<Integer> buffer) {
        return buffer.get(3) - 40;
    }

    public static float getAirFlowSensore(ArrayList<Integer> buffer) {
        return buffer.get(4) * 5.0f / 255.0f;
    }

    public static CanMmcData.State getEngineAcFanState(ArrayList<Integer> buffer) {
        return ( (buffer.get(2) & 0x8) > 0 ) ? CanMmcData.State.on : CanMmcData.State.off;
    }

    public static CanMmcData.State getEngineFuelRelay(ArrayList<Integer> buffer) {
        return ( (buffer.get(2) & (1 << 2)) > 0) ? CanMmcData.State.on : CanMmcData.State.off;
    }
}
