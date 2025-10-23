package ru.d51x.kaierutils.OBD2;

import android.util.Pair;

import java.util.ArrayList;

import ru.d51x.kaierutils.App;

public class ObdCombineMeterUtils {

    public static int getMeterEngineRpm(ArrayList<Integer> buffer) {
        int rpm =  buffer.get(2) + buffer.get(3) * 256; // FFFF
        if (rpm == 0xFFFF) rpm = 0;
        return rpm;
    }

    public static float getMeterVehicleSpeed(ArrayList<Integer> buffer) {
        float speed =  0;
        if (buffer.get(2) != 0xFF && buffer.get(3) != 0xFF) {
            //speed = (buffer.get(2) + buffer.get(3) * 256) / 100.0f;
            speed = buffer.get(2) / 128.0f + buffer.get(3);
        }
        return speed;
    }

    public static int getMeterFuelLevel(ArrayList<Integer> buffer) {
        int level = Math.round(( buffer.get(4) - 16) * 0.6f);
        if (level == 0x7F) {
            level = 0;
        }
        return level;
    }

    public static int getMeterMileage(ArrayList<Integer> buffer) {
        return buffer.get(2) + buffer.get(3) * 256 + buffer.get(4) * 256 * 256;
    }

    public static Pair<Float, Float> getMeterTrips(ArrayList<Integer> buffer) {
        float tripA = (buffer.get(2) + buffer.get(3) * 256 + buffer.get(4) * 256 * 256) / 10.0f;
        float tripB = (buffer.get(5) + buffer.get(6) * 256 + buffer.get(7) * 256 * 256) / 10.0f;
        return Pair.create(tripA, tripB);
    }

    public static float getMeterVoltage(ArrayList<Integer> buffer) {
        return buffer.get(2) / 10.0f;
    }

    public static Pair<Integer, Integer> getMeterServiceReminder(ArrayList<Integer> buffer) {
        int km = (buffer.get(2) + buffer.get(3) * 256) * 100;
        int month = buffer.get(6);
        return Pair.create(km, month);
    }
}
