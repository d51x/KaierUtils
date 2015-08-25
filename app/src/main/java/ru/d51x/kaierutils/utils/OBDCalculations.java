package ru.d51x.kaierutils.utils;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.CanMmcData;
import ru.d51x.kaierutils.ClimateData;
import ru.d51x.kaierutils.OBDII;

/**
 */
public class OBDCalculations {


    public static void sendOBD_ENGINE_Fan_State(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();
        // Выключатель кондиционера 211D {A:3}
        App.obd.canMmcData.fan_state = ( (buffer.get(2) & 0x8) > 0 ) ? CanMmcData.State.on : CanMmcData.State.off;

        if ( App.obd.canMmcData.fan_state != App.obd.canMmcDataPrev.fan_state) {
            // send message
            message.what = msg_id;
            message.arg1 = App.obd.canMmcData.fan_state.ordinal();
            mHandler.sendMessage(message);
            App.obd.canMmcDataPrev.fan_state = App.obd.canMmcData.fan_state;
        }
    }

    public static void sendOBD_CVT_Temp(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        int N = buffer.get(15);
        int temp_1 = Math.round( -21.592f + (1.137f * N) - (0.0063f * N * N) + (0.0000195f * N * N * N));
        App.obd.canMmcData.can_mmc_cvt_temp = temp_1;
        Message message = new Message();
        if ( App.obd.canMmcData.can_mmc_cvt_temp != App.obd.canMmcDataPrev.can_mmc_cvt_temp) {
            // send message
            message.what = msg_id;
            message.arg1 = temp_1;
            mHandler.sendMessage(message);
            App.obd.canMmcDataPrev.can_mmc_cvt_temp = App.obd.canMmcData.can_mmc_cvt_temp;
        }
    }

    public static void sendOBD_CVT_Degradation(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        // AB*65536 + AC*256 + AD
        int degr = buffer.get(29) * 65536 + buffer.get(30) * 256 + buffer.get(31);
        Message message = new Message();
        App.obd.canMmcData.can_mmc_cvt_degradation_level = degr;
        if ( App.obd.canMmcData.can_mmc_cvt_degradation_level != App.obd.canMmcDataPrev.can_mmc_cvt_degradation_level) {
            // send message
            message.what = msg_id;
            message.arg1 = degr;
            mHandler.sendMessage(message);
            App.obd.canMmcDataPrev.can_mmc_cvt_degradation_level = App.obd.canMmcData.can_mmc_cvt_degradation_level;
        }
    }

    public static void sendOBD_AC_ExtTemp(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        // "C-Cross External temperature","Ext temp","2111","A/2-40","-40","87.5"," grad C","688"
        Message message = new Message();
        int temp = buffer.get(2)/2 - 40;
        App.obd.climateData.ext_temperature = temp;
        if ( App.obd.climateData.ext_temperature != App.obd.climateDataPrev.ext_temperature) {
            // send message
            message.what = msg_id;
            message.arg1 = temp;
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.ext_temperature = App.obd.climateData.ext_temperature;
        }
    }

    public static void sendOBD_AC_Fan_Mode(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();
        int fan_mode = buffer.get(3) & 0xF; // bits 1-4, mask 0xF
        if ( fan_mode == 0x0 ) App.obd.climateData.fan_mode = ClimateData.FanMode.auto;
        else if ( fan_mode == 0x1 ) App.obd.climateData.fan_mode = ClimateData.FanMode.off;
        else if ( fan_mode > 1 ) App.obd.climateData.fan_mode = ClimateData.FanMode.manual;
        else App.obd.climateData.fan_mode = ClimateData.FanMode.unknown;

        if ( App.obd.climateData.fan_mode != App.obd.climateDataPrev.fan_mode) {
            message.what = msg_id;
            message.arg1 = App.obd.climateData.fan_mode.ordinal();
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.fan_mode = App.obd.climateData.fan_mode;
        }
    }

    public static void sendOBD_AC_Blow_Mode(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();

        int blow_mode = buffer.get(3)  >> 4; // bits 5-8  (getBlowValue2)
        if ( blow_mode == 0x0 ) App.obd.climateData.blow_mode = ClimateData.BlowMode.auto;
        else App.obd.climateData.blow_mode = ClimateData.BlowMode.manual;

        if ( App.obd.climateData.blow_mode != App.obd.climateDataPrev.blow_mode) {
            message.what = msg_id;
            message.arg1 = App.obd.climateData.blow_mode.ordinal();
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.blow_mode = App.obd.climateData.blow_mode;
        }
    }

    public static void sendOBD_AC_Temp(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();

        double A = buffer.get(2)/2f - 50f;
        App.obd.climateData.temperature = A;
        if ( App.obd.climateData.temperature != App.obd.climateDataPrev.temperature) {
            // send message
            message.what = msg_id;
            message.obj = String.format("%1$.1f", A);
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.temperature = App.obd.climateData.temperature;
        }
    }

    public static void sendOBD_AC_Blow_direction(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();


        switch ( buffer.get(3) >> 4  ) // bits 5-8
        {
            case 0x0:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.off; break;
            case 0x1:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.face; break;
            case 0x3:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.from_face_to_feet_and_face; break;
            case 0x4:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.feet_and_face; break;
            case 0x5:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.from_feet_and_face_to_feet; break;
            case 0x7:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.feet; break;
            case 0x9:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.from_feet_to_feet_and_window; break;
            case 0xA:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.feet_and_window; break;
            case 0xB:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.from_feet_and_window_to_window; break;
            case 0xD:  App.obd.climateData.blow_direction = ClimateData.BlowDirection.window; break;

            default:
                App.obd.climateData.blow_direction = ClimateData.BlowDirection.unknown;
                break;
        }
        if ( App.obd.climateData.blow_direction != App.obd.climateDataPrev.blow_direction) {
            // send message
            message.what = msg_id;
            message.arg1 = App.obd.climateData.blow_direction.ordinal();
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.blow_direction = App.obd.climateData.blow_direction;
        }
    }

    public static void sendOBD_AC_Fan_Speed(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();

        // fan speed
        switch (buffer.get(3) & 0xF )   // bits 1-4, mask 0xF
        {
            case 0x0: App.obd.climateData.fan_speed = ClimateData.FanSpeed.auto; break;
            case 0x1: App.obd.climateData.fan_speed = ClimateData.FanSpeed.off; break;
            case 0x2: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed1; break;
            case 0x3: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed2; break;
            case 0x4: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed3; break;
            case 0x5: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed4; break;
            case 0x6: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed5; break;
            case 0x7: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed6; break;
            case 0x8: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed7; break;
            case 0x9: App.obd.climateData.fan_speed = ClimateData.FanSpeed.speed8; break;
            default:
                App.obd.climateData.fan_speed = ClimateData.FanSpeed.unknown;
                break;
        }
        if ( App.obd.climateData.fan_speed != App.obd.climateDataPrev.fan_speed) {
            // send message
            message.what = msg_id;
            message.arg1 =  App.obd.climateData.fan_speed.ordinal();
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.fan_speed = App.obd.climateData.fan_speed;
        }
    }

    public static void sendOBD_AC_State(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();


        // A/C state: On/Off  // А:5

        App.obd.climateData.ac_state = ( (buffer.get(2) & 0x10) > 0 ) ? ClimateData.State.on : ClimateData.State.off;
        if ( App.obd.climateData.ac_state != App.obd.climateDataPrev.ac_state) {
            // send message
            message.what = msg_id;
            message.arg1 = App.obd.climateData.ac_state.ordinal();
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.ac_state = App.obd.climateData.ac_state;
        }

    }

    public static void sendOBD_AC_Recirculation_State(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();

        // recirculation state  А:7
        App.obd.climateData.recirculation_state = ( ( buffer.get(2) & 0x40 ) > 0 ) ? ClimateData.State.on : ClimateData.State.off;
        if ( App.obd.climateData.recirculation_state != App.obd.climateDataPrev.recirculation_state) {
            // send message
            message.what = msg_id;
            message.arg1 = App.obd.climateData.recirculation_state.ordinal();
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.recirculation_state = App.obd.climateData.recirculation_state;
        }
    }

    public  static void sendOBD_AC_Defogger_State(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();
        // defogger state А:1
        App.obd.climateData.defogger_state = ( ( buffer.get(2) & 0x1 ) > 0 ) ? ClimateData.State.on : ClimateData.State.off;
        if ( App.obd.climateData.defogger_state != App.obd.climateDataPrev.defogger_state) {
            // send message
            message.what = msg_id;
            message.arg1 = App.obd.climateData.defogger_state.ordinal();
            mHandler.sendMessage(message);
            App.obd.climateDataPrev.defogger_state = App.obd.climateData.defogger_state;
        }
    }


    public static void sendOBD_ParkingSensors(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();

         // send message
         message.what = msg_id;
//         message.arg1 = buffer.get(4);
//         message.arg2 = buffer.get(6);



         message.obj = buffer;
         mHandler.sendMessage(message);

//        Log.d("PARKING DATA: ", "buffer: " + buffer.toString());

//        try {
//            for (int k = 0; k < buffer.size() - 1; k++) {
//                Log.d("PARKING DATA: ", String.format("buffer[%1$d]: %2$s", k, Integer.toHexString(buffer.get(k))));
//            }
//        } catch (Exception e) {
//
//        }
    }

    public static void sendOBD_CombineMeter_FuelLevel(int msg_id, Handler mHandler, ArrayList<Integer> buffer) {
        Message message = new Message();
        //App.obd.canMmcData.can_mmc_fuel_remain = Math.round(( buffer.get(4) - 16) * 0.6f);
        if ( App.obd.obdData.fuel_tank > 0 )
                App.obd.canMmcData.can_mmc_fuel_remain = Math.round(( buffer.get(4) - 16) * (App.obd.obdData.fuel_tank / 100));
        else
            App.obd.canMmcData.can_mmc_fuel_remain = Math.round(( buffer.get(4) - 16) * 0.6f);
        if ( App.obd.canMmcData.can_mmc_fuel_remain != App.obd.canMmcDataPrev.can_mmc_fuel_remain) {
            // send message
            message.what = msg_id;
            message.arg1 = App.obd.canMmcData.can_mmc_fuel_remain;
            mHandler.sendMessage(message);
            App.obd.canMmcDataPrev.can_mmc_fuel_remain = App.obd.canMmcData.can_mmc_fuel_remain;
        }
    }
}
