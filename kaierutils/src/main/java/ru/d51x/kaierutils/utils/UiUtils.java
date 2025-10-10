package ru.d51x.kaierutils.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.Data.ClimateData;
import ru.d51x.kaierutils.R;

public class UiUtils {

    public static final int TEXT_SIZE_BEFORE_DOT = 40;
    public static final int TEXT_SIZE_BEFORE_DOT_2 = 26;
    public static final int TEXT_SIZE_BEFORE_DOT_3 = 2;
    public static final int TEXT_SIZE_BEFORE_DOT_4 = 44;
    public static final int TEXT_SIZE_AFTER_DOT = 16;

    public static void TextViewToSpans(TextView tv, String value, int size1, int size2) {
        String s = value.replace(",", ".");
        SpannableString ss =  new SpannableString(s);
        int dot = s.indexOf(".");
        if ( dot > -1 ) {
            ss.setSpan(new AbsoluteSizeSpan(size1), 0, dot, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan(size2), dot, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(ss);
    }

    //---------------- CVT -------------------------------------------------------------
    @SuppressLint("SetTextI18n")
    public void updateCvtTemperatureText(TextView view, int temp) {
        if (temp > -255) {
            view.setText(Integer.toString(temp) + "°");
        } else {
            view.setText("--");
        }
    }
    public void updateCvtTemperatureIcon(ImageView view, int temp) {
        int imageRes;
        if (temp < 71) {
            imageRes = R.drawable.cvt_temp_min;
        } else if (temp < 91) {
            imageRes = R.drawable.cvt_temp_nom_green;
        } else if (temp < 103) {
            imageRes = R.drawable.cvt_temp_nom_yellow;
        } else {
            imageRes = R.drawable.cvt_temp_nom_orange;
        }
        view.setImageResource(imageRes);
    }
    public void updateCvtOilDegradationIcon(ImageView view, int temp) {
        int imageRes = R.drawable.cvt_degr_nom;
        view.setImageResource(imageRes);
    }

    //---------------- BATTERY ----------------------------------------------------------
    public void updateBatteryLevelText(TextView view, float voltage) {
        if (voltage > 0) {
            TextViewToSpans(view, String.format("%1$.1f", voltage), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
        } else {
            view.setText("--");
        }
    }
    public void updateBatteryLevelIcon(ImageView view, float voltage) {
        if (voltage < 12) {
            view.setImageResource(R.drawable.car_battery_bad);
        } else {
            view.setImageResource(R.drawable.car_battery_good);
        }
    }

    //--------------- COOLANT ------------------------------------------------------------
    @SuppressLint("StringFormatMatches")
    public void updateCoolantTemperatureText(TextView view, float temp) {
        if (temp > -255) {
            view.setText(String.format(App.getInstance().getApplicationContext().getString(R.string.text_obd_coolant_temp_f), (int)temp) + "°");
        } else {
            view.setText("--");
        }
    }
    public void updateCoolantTemperatureIcon(ImageView view, float temp) {
        int res;
        if (temp < 80) {
            res = R.drawable.coolant_temp_min;
        } else if (temp < 99) {
            res = R.drawable.coolant_temp_norm;
        } else {
            res = R.drawable.coolant_temp_hot;
        }
        view.setImageResource(res);
    }

    //--------------- FUEL ----------------------------------------------------------------
    public void updateFuelLevelText(TextView view, int level) {
        if ( level > 0 && level < 67) {
            view.setText(String.format("%1$s", level));
        } else {
            view.setText(String.format("%1$s", "--"));
        }
    }

    public void updateFuelConsumptionText(TextView view, float consumption) {
        updateFuelConsumptionTextWithSize(view, consumption, TEXT_SIZE_BEFORE_DOT);
    }

    public void updateFuelConsumptionText2(TextView view, float consumption) {
        updateFuelConsumptionTextWithSize(view, consumption, TEXT_SIZE_BEFORE_DOT_2);
    }

    public void updateFuelConsumptionText3(TextView view, float consumption) {
        updateFuelConsumptionTextWithSize(view, consumption, TEXT_SIZE_BEFORE_DOT_3);
    }

    private void updateFuelConsumptionTextWithSize(TextView view, float consumption, int size1) {
        if (consumption > 0) {
            TextViewToSpans(view, String.format("%1$.1f", consumption).replace(",", "."),
                    size1, TEXT_SIZE_AFTER_DOT);
//            view.setText(String.format(App.getInstance().getApplicationContext()
//                            .getString(R.string.text_distance), consumption)
//                    .replace(",", "."));
        } else {
            view.setText("--.-");
        }
    }

    //---------------- DISTANCE -----------------------------------------------------------
    public void updateDistanceText(TextView view, float distance) {
        updateDistanceTextWithSize(view, distance, TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
    }

    public void updateDistanceTextWithSize(TextView view, float distance, int size1, int size2) {
        if (distance > 0) {
            TextViewToSpans(view, String.format(App.getInstance().getApplicationContext()
                                    .getString(R.string.text_distance), distance)
                            .replace(",", "."),
                    size1, size2);
        } else {
            view.setText("---.-");
        }
    }
    //---------------- SPEED ---------------------------------------------------------------
    public void updateSpeedText(TextView view, float speed, boolean withColor) {
        view.setText(speed > 0 ? String.format(App.getInstance().getApplicationContext()
                .getString(R.string.text_gps_speed_value), (int) speed) : "---");

        int color = Color.LTGRAY;
        if ( withColor ) {
            if ( speed < 10 ) color = Color.LTGRAY;
            else if ( speed < 40 ) color = Color.rgb(0,255,255);
            else if ( speed < 60 ) color = Color.rgb(0,255,144);
            else if ( speed < 80 ) color = Color.rgb(182,255,0);
            else if ( speed < 100 ) color = Color.rgb(255,216,0);
            else if ( speed < 120 ) color = Color.rgb(155,106,0);
            else color = Color.rgb(255,0,0);
        }
        view.setTextColor( color);
    }
    public void updateSpeedIcon(ImageView view, float speed) {
        if (speed > 80) {
            view.setImageResource(R.drawable.speedo_2);
        } else {
            view.setImageResource(R.drawable.speedo_1);
        }
    }

    //---------------- CLIMATE UTILS --------------------------------------------------------
    public void updateClimateTemperatureText(TextView view, float temp) {
        if (temp < 15f || temp > 27f) {
            view.setText("--.-");
        } else {
            TextViewToSpans(view, Float.toString(temp), TEXT_SIZE_BEFORE_DOT_4, TEXT_SIZE_AFTER_DOT);
        }
    }
    public void updateClimateTemperatureIcon(ImageView view, float temp){
        int res;
        if ( temp < 19f )
            res = R.drawable.ac_temp_blue;
        else if ( temp < 21f )
            res =R.drawable.ac_temp_green;
        else if ( temp < 23f )
            res = R.drawable.ac_temp_orange;
        else
            res = R.drawable.ac_temp_red;
        view.setImageResource(res);
    }
    public void updateClimateBlowDirection(ImageView view, ClimateData.BlowDirection blowDirection) {
        int res;
        switch ( blowDirection ) {
            case face:
                res = R.drawable.air_wind_seat_my_to_face;
                break;
            case from_face_to_feet_and_face:
                res = R.drawable.air_wind_seat_my_to_beetwen_feet_and_face_and_feet;
                break;
            case feet_and_face:
                res = R.drawable.air_wind_seat_my_to_face_and_feet;
                break;
            case from_feet_and_face_to_feet:
                res = R.drawable.air_wind_seat_my_to_beetwen_feet_and_feet_and_face;
                break;
            case feet:
                res = R.drawable.air_wind_seat_my_to_feet;
                break;
            case from_feet_to_feet_and_window:
                res = R.drawable.air_wind_seat_my_to_beetwen_feet_and_feet_and_window;
                break;
            case feet_and_window:
                res = R.drawable.air_wind_seat_my_to_feet_and_window;
                break;
            case from_feet_and_window_to_window:
                res = R.drawable.air_wind_seat_my_to_between_window_and_feet_and_window;
                break;
            case window:
                res = R.drawable.air_wind_seat_my_to_window;
                break;
            default:  res = R.drawable.air_wind_seat_my_to_face; break;
        }
        view.setImageResource( res );
    }
     public void updateClimateFanSpeed(ImageView view, ClimateData.FanSpeed fanSpeed) {
        int res;
        switch ( fanSpeed ) {
            case off:       res = R.drawable.air_wind_seat_my_fan_0; break;
            case speed1:    res = R.drawable.air_wind_seat_my_fan_1; break;
            case speed2:    res = R.drawable.air_wind_seat_my_fan_2; break;
            case speed3:    res = R.drawable.air_wind_seat_my_fan_3; break;
            case speed4:    res = R.drawable.air_wind_seat_my_fan_4; break;
            case speed5:    res = R.drawable.air_wind_seat_my_fan_5; break;
            case speed6:    res = R.drawable.air_wind_seat_my_fan_6; break;
            case speed7:    res = R.drawable.air_wind_seat_my_fan_7; break;
            case speed8:    res = R.drawable.air_wind_seat_my_fan_8; break;
            default: res = R.drawable.air_wind_seat_my_fan_0;
        }
        view.setImageResource( res );
    }
    public void updateClimateFanMode(ImageView view, ClimateData.FanMode fanMode) {
        view.setVisibility((fanMode == ClimateData.FanMode.auto) ? View.VISIBLE : View.INVISIBLE);
    }
    public void updateClimateDefogger(ImageView view, ClimateData.State state){
        view.setVisibility( (state == ClimateData.State.on)  ? View.VISIBLE : View.INVISIBLE );
    }
    public void updateClimateRecirculation(ImageView view, ClimateData.State state){
        view.setVisibility((state == ClimateData.State.on) ? View.VISIBLE : View.INVISIBLE);
    }
    public void updateClimateAcState(ImageView view, ClimateData.State state) {
        view.setImageResource((state == ClimateData.State.on) ? R.drawable.air_cond__state_on : R.drawable.air_cond__state_off);
    }
    public void updateClimateExteriorTemperature (ImageView view, float temp) {
        // TODO
    }
    public void updateClimateBlowMode(ImageView view, ClimateData.BlowMode blowMode){
        view.setVisibility((blowMode == ClimateData.BlowMode.auto) ? View.VISIBLE : View.INVISIBLE);
    }



}
