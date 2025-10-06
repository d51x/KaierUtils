package ru.d51x.kaierutils.OBD2;

import java.util.ArrayList;

import ru.d51x.kaierutils.Data.ClimateData;

public class ObdClimateUtils {


    // Climate utils
    public static ClimateData.BlowDirection getBlowDirection(ArrayList<Integer> buffer) {
        ClimateData.BlowDirection blowDirection = ClimateData.BlowDirection.unknown;
        switch ( buffer.get(3) >> 4  ) // bits 5-8
        {
            case 0x0:  blowDirection = ClimateData.BlowDirection.off; break;
            case 0x1:  blowDirection = ClimateData.BlowDirection.face; break;
            case 0x3:  blowDirection = ClimateData.BlowDirection.from_face_to_feet_and_face; break;
            case 0x4:  blowDirection = ClimateData.BlowDirection.feet_and_face; break;
            case 0x5:  blowDirection = ClimateData.BlowDirection.from_feet_and_face_to_feet; break;
            case 0x7:  blowDirection = ClimateData.BlowDirection.feet; break;
            case 0x9:  blowDirection = ClimateData.BlowDirection.from_feet_to_feet_and_window; break;
            case 0xA:  blowDirection = ClimateData.BlowDirection.feet_and_window; break;
            case 0xB:  blowDirection = ClimateData.BlowDirection.from_feet_and_window_to_window; break;
            case 0xD:  blowDirection = ClimateData.BlowDirection.window; break;

            default:
                break;
        }
        return blowDirection;
    }

    public static ClimateData.FanSpeed getFanSpeed(ArrayList<Integer> buffer) {
        ClimateData.FanSpeed fanSpeed = ClimateData.FanSpeed.unknown;

        switch (buffer.get(3) & 0xF)   // bits 1-4, mask 0xF
        {
            case 0x0: fanSpeed = ClimateData.FanSpeed.auto; break;
            case 0x1: fanSpeed = ClimateData.FanSpeed.off; break;
            case 0x2: fanSpeed = ClimateData.FanSpeed.speed1; break;
            case 0x3: fanSpeed = ClimateData.FanSpeed.speed2; break;
            case 0x4: fanSpeed = ClimateData.FanSpeed.speed3; break;
            case 0x5: fanSpeed = ClimateData.FanSpeed.speed4; break;
            case 0x6: fanSpeed = ClimateData.FanSpeed.speed5; break;
            case 0x7: fanSpeed = ClimateData.FanSpeed.speed6; break;
            case 0x8: fanSpeed = ClimateData.FanSpeed.speed7; break;
            case 0x9: fanSpeed = ClimateData.FanSpeed.speed8; break;
            default:
                break;
        }

        return fanSpeed;
    }

    public static ClimateData.State getAcState(ArrayList<Integer> buffer) {
        return ( (buffer.get(2) & 0x10) > 0 ) ? ClimateData.State.on : ClimateData.State.off;
    }

    public static ClimateData.State getRecirculationState(ArrayList<Integer> buffer) {
        return ( ( buffer.get(2) & 0x40 ) > 0 ) ? ClimateData.State.on : ClimateData.State.off;
    }

    public static ClimateData.State getDefoggerState(ArrayList<Integer> buffer) {
        return ( ( buffer.get(2) & 0x1 ) > 0 ) ? ClimateData.State.on : ClimateData.State.off;
    }

    public static ClimateData.FanMode getAcFanMode(ArrayList<Integer> buffer) {
        ClimateData.FanMode fanMode;
        int fan_mode = buffer.get(3) & 0x0F; // bits 1-4, mask 0xF
        if ( fan_mode == 0x00 ) fanMode = ClimateData.FanMode.auto;
        else if ( fan_mode == 0x01 ) fanMode = ClimateData.FanMode.off;
        else fanMode = ClimateData.FanMode.manual;
        return fanMode;
    }

    public static ClimateData.BlowMode getAcBlowMode(ArrayList<Integer> buffer) {
        ClimateData.BlowMode blowMode;
        int blow_mode = buffer.get(3)  >> 4;
        if ( blow_mode == 0x0 ) blowMode = ClimateData.BlowMode.auto;
        else blowMode = ClimateData.BlowMode.manual;
        return blowMode;
    }
    public static double getAcSetTemperature(ArrayList<Integer> buffer) {
        return buffer.get(2) / 2.0f - 50.0f;
    }

    public static float getAcAmbientTemperature(ArrayList<Integer> buffer) {
        return buffer.get(2) / 2.0f - 40;
    }

    public static float getAcExternalTemperature(ArrayList<Integer> buffer) {
        return (((buffer.get(2) / 2.0f) - 32.0f) * 5.0f) / 9.0f;
    }
    public static float getAcInteriorTemperature(ArrayList<Integer> buffer) {
        return (buffer.get(2) / 4.0f) - 16;
    }

    public static float getAcAirThermoSensor(ArrayList<Integer> buffer) {
        return buffer.get(3) / 4.0f;
    }

    public static float getAcPressure(ArrayList<Integer> buffer) {
        return ((buffer.get(4) * 3.3f) / 255.0f) * 1000.0f;
    }

    public static float getAcSolarSensor(ArrayList<Integer> buffer) {
        return (buffer.get(5) * 5.5f) / 255.0f;
    }

    public static int getAcCoolantTemperature(ArrayList<Integer> buffer) {
        return buffer.get(6) - 40;
    }

    public static int getAcEngineRpm(ArrayList<Integer> buffer) {
        return buffer.get(3) * 256 + buffer.get(4);
    }

    public static float getAcVehicleSpeed(ArrayList<Integer> buffer) {
        return (buffer.get(5) * 2.0f) * (buffer.get(6) / 128.0f);
    }

    public static boolean getAcLeak(ArrayList<Integer> buffer) {
        return (buffer.get(2) & (1 << 1)) > 0;
    }

    public static boolean getAcLeak20(ArrayList<Integer> buffer) {
        return (buffer.get(2) & (1 << 3)) > 0;
    }

    public static long getAcSystemWorkTime(ArrayList<Integer> buffer) {
        return (buffer.get(2) * 65536 + buffer.get(3) * 256 + buffer.get(4)) * 5;
    }
}
