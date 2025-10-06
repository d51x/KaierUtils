package ru.d51x.kaierutils.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.widget.ImageView;
import android.widget.TextView;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class UiUtils {

    public static final int TEXT_SIZE_BEFORE_DOT = 40;
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

    @SuppressLint("SetTextI18n")
    public static void updateCvtTemperatureText(TextView view, int temp) {
        if (temp > -255) {
            view.setText(Integer.toString(temp));
        } else {
            view.setText("--");
        }
    }
    public static void updateCvtTemperatureIcon(ImageView view, int temp) {
        int imageRes = R.drawable.cvt_temp_min;
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

    public static void updateCvtOilDegradationIcon(ImageView view, int temp) {
        int imageRes = R.drawable.cvt_degr_nom;
        view.setImageResource(imageRes);
    }

    public static void updateBatteryLevelText(TextView view, float voltage) {
        if (voltage > 0) {
            TextViewToSpans(view, String.format("%1$.1f", voltage), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
        } else {
            view.setText("--");
        }
    }
    public static void updateBatteryLevelIcon(ImageView view, float voltage) {
        if (voltage < 12) {
            view.setImageResource(R.drawable.car_battery_bad);
        } else {
            view.setImageResource(R.drawable.car_battery_good);
        }
    }

    @SuppressLint("StringFormatMatches")
    public static void updateCoolantTemperatureText(TextView view, float temp) {
        if (temp > -255) {
            view.setText(String.format(App.getInstance().getApplicationContext().getString(R.string.text_obd_coolant_temp_f), (int)temp));
        } else {
            view.setText("--");
        }
    }

    public static void updateCoolantTemperatureIcon(ImageView view, float temp) {
        int res = R.drawable.coolant_temp_min;
        if (temp < 80) {
            res = R.drawable.coolant_temp_min;
        } else if (temp < 99) {
            res = R.drawable.coolant_temp_norm;
        } else {
            res = R.drawable.coolant_temp_hot;
        }
        view.setImageResource(res);
    }

    public static void updateFuelLevelText(TextView view, int level) {
        if ( level > 0 && level < 67) {
            view.setText(String.format("%1$s", level));
        } else {
            view.setText(String.format("%1$s", "--"));
        }
    }

    public static void updateSpeedText(TextView view, float speed, boolean withColor) {
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

    public static void updateSpeedIcon(ImageView view, float speed) {
        if (speed > 80) {
            view.setImageResource(R.drawable.speedo_2);
        } else {
            view.setImageResource(R.drawable.speedo_1);
        }
    }
}
