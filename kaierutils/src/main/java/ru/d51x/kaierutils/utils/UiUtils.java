package ru.d51x.kaierutils.utils;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
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
            view.setText(Integer.toString(temp));
        } else {
            view.setText("--");
        }
    }

    public void updateCvtTemperatureText(TextView view, int temp, int size) {
        view.setTextSize(size);
        updateCvtTemperatureText(view, temp);
        updateCvtTemperatureTextColor(view, temp);
    }

    public void updateCvtTemperatureTextColor(TextView view, int temp) {
        if (temp < 71) {
            view.setTextColor(Color.WHITE);
        } else if (temp < 91) {
            view.setTextColor(Color.GREEN);
        } else if (temp < 103) {
            view.setTextColor(Color.YELLOW);
        } else {
            view.setTextColor(Color.RED);
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
    @SuppressLint("DefaultLocale")
    public void updateBatteryLevelText(TextView view, float voltage, int size1, int size2) {
        if (voltage > 0) {
            TextViewToSpans(view, String.format("%1$.1f", voltage), size1, size2);
        } else {
            view.setText("--");
        }
    }
    public void updateBatteryLevelText(TextView view, float voltage) {
        updateBatteryLevelText(view, voltage, TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
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
            view.setText(String.format(App.getInstance().getApplicationContext().getString(R.string.text_obd_coolant_temp_f), (int)temp));
        } else {
            view.setText("--");
        }
    }
    public void updateCoolantTemperatureText(TextView view, float temp, int size) {
        view.setTextSize(size);
        updateCoolantTemperatureText(view, temp);
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
    public void updateFuelLevelText(TextView view, int level, int size) {
        view.setTextSize(size);
        updateFuelLevelText(view, level);
    }

    public void updateFuelConsumptionText(TextView view, float consumption) {
        updateFuelConsumptionText(view, consumption, TEXT_SIZE_BEFORE_DOT);
    }

    public void updateFuelConsumptionText2(TextView view, float consumption) {
        updateFuelConsumptionText(view, consumption, TEXT_SIZE_BEFORE_DOT_2);
    }

    public void updateFuelConsumptionText3(TextView view, float consumption) {
        updateFuelConsumptionText(view, consumption, TEXT_SIZE_BEFORE_DOT_3);
    }

    public void updateFuelConsumptionText(TextView view, float consumption, int size1) {
        updateFuelConsumptionText(view, consumption, size1, TEXT_SIZE_AFTER_DOT);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void updateFuelConsumptionText(TextView view, float consumption, int size1, int size2) {
        if (consumption > 99.9f) {
            view.setText("99.9");
        }
        else if (consumption < 1) {
            view.setText("--.-");
        }  else {
            TextViewToSpans(view, String.format("%1$.1f", consumption).replace(",", "."),
                    size1, size2);
        }
    }

    //---------------- DISTANCE -----------------------------------------------------------
    public void updateDistanceText(TextView view, float distance) {
        updateDistanceText(view, distance, TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
    }

    public void updateDistanceText(TextView view, float distance, int size1, int size2) {
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
    public void updateSpeedText(TextView view, float speed, boolean withColor, int textSize) {
        view.setTextSize(textSize);
        updateSpeedText(view, speed, withColor);
    }
    public void updateSpeedText(TextView view, float speed, boolean withColor) {
        view.setText(speed > 0 ? String.format(App.getInstance().getApplicationContext()
                .getString(R.string.text_gps_speed_value), (int) speed) : "---");

        int color = 0;
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
            TextViewToSpans(view, Float.toString(temp), 72, 44);
        }
    }
    public void updateExternalTemperature(TextView view, float temp) {
        if (temp < -50 || temp > 70) {
            view.setText("--.-");
        } else {
            TextViewToSpans(view, String.format("%1$.1f", temp), 52, 32);
        }
    }
    public void updateClimateTemperatureIcon(ImageView view, float temp){
        if ( temp < 19f )
            view.setColorFilter(Color.BLUE);
        else if ( temp < 21f )
            view.setColorFilter(Color.GREEN);
        else if ( temp < 23f )
            view.setColorFilter(Color.parseColor("#FFA500"));
        else
            view.setColorFilter(Color.RED);
        int res = R.drawable.climate_temperature_setpoint;
        view.setImageResource(res);
    }
    public void updateClimateBlowDirection(ImageView view, ClimateData.BlowDirection blowDirection) {
        int res = R.drawable.climate_blow_direction;

        //TypedArray a = App.getInstance().obtainStyledAttributes(R.styleable.ClimateData);
        //if (a.hasValue(R.styleable.ClimateData_BlowDirection)) {
        //    res = ClimateData.BlowDirection.values()[a.getInt(R.styleable.ClimateData_BlowDirection, 0)].ordinal();
        //}
        switch (blowDirection) {
            case off:
                res = 0;
                break;
            case face:
                res = R.drawable.climate_air_wind_face;
                break;
            case from_face_to_feet_and_face:
                res = R.drawable.climate_air_wind_feet_face2;
                break;
            case feet_and_face:
                res = R.drawable.climate_air_wind_feet_face;
                break;
            case from_feet_and_face_to_feet:
                res = R.drawable.climate_air_wind_feet2_face;
                break;
            case feet:
                res = R.drawable.climate_air_wind_feet;
                break;
            case from_feet_to_feet_and_window:
                res = R.drawable.climate_air_wind_feet2_window;
                break;
            case feet_and_window:
                res = R.drawable.climate_air_wind_feet2_window;
                break;
            case from_feet_and_window_to_window:
                res = R.drawable.climate_air_wind_feet_window2;
                break;
            case window:
                res = R.drawable.climate_air_wind_window;
                break;
        }
        view.setImageResource( res );
    }
    public void updateClimateFanSpeed(ImageView view, ClimateData.FanSpeed fanSpeed) {
        int res;
        switch ( fanSpeed ) {
            case off:       res = R.drawable.climate_fan_speed_0; break;
            case speed1:    res = R.drawable.climate_fan_speed_1; break;
            case speed2:    res = R.drawable.climate_fan_speed_2; break;
            case speed3:    res = R.drawable.climate_fan_speed_3; break;
            case speed4:    res = R.drawable.climate_fan_speed_4; break;
            case speed5:    res = R.drawable.climate_fan_speed_5; break;
            case speed6:    res = R.drawable.climate_fan_speed_6; break;
            case speed7:    res = R.drawable.climate_fan_speed_7; break;
            case speed8:    res = R.drawable.climate_fan_speed_8; break;
            default: res = R.drawable.climate_fan_speed_0;
        }
        view.setImageResource( res );
    }
    public void updateClimateFanMode(ImageView view, ClimateData.FanMode fanMode) {
        //view.setVisibility((fanMode == ClimateData.FanMode.auto) ? View.VISIBLE : View.INVISIBLE);
        view.setImageResource((fanMode == ClimateData.FanMode.auto) ? R.drawable.climate_fan_auto : R.drawable.climate_fan);
    }
    public void updateClimateDefogger(ImageView view, ClimateData.State state){
        //view.setVisibility( (state == ClimateData.State.on)  ? View.VISIBLE : View.INVISIBLE );
        view.setImageResource( (state == ClimateData.State.on)  ? R.drawable.climate_rear_window_on : R.drawable.climate_rear_window_off );
    }
    public void updateClimateRecirculation(ImageView view, ClimateData.State state){
        //view.setVisibility((state == ClimateData.State.on) ? View.VISIBLE : View.INVISIBLE);
        view.setImageResource((state == ClimateData.State.on) ? R.drawable.climate_recirculation_on : R.drawable.climate_recirculation_off);
    }
    public void updateClimateAcState(ImageView view, ClimateData.State state) {
        view.setImageResource((state == ClimateData.State.on) ? R.drawable.climate_ac_on_ : R.drawable.climate_ac_off_);
    }
    public void updateClimateBlowMode(ImageView view, ClimateData.BlowMode blowMode){
        view.setVisibility((blowMode == ClimateData.BlowMode.auto) ? View.VISIBLE : View.INVISIBLE);
    }



}
