package ru.d51x.kaierutils;

import static java.lang.Math.abs;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CVT_2103_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2102_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A3_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CVT_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED;
import static ru.d51x.kaierutils.utils.UiUtils.TextViewToSpans;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ru.d51x.kaierutils.Data.CombineMeterData;
import ru.d51x.kaierutils.Data.CvtData;
import ru.d51x.kaierutils.Data.EngineData;
import ru.d51x.kaierutils.OBD2.OBDII;
import ru.d51x.kaierutils.utils.UiUtils;

public class FloatingWindow implements View.OnClickListener, View.OnTouchListener {


    private WindowManager windowManager;
    private final Context context;
    private final View floatingView;
    private final WindowManager.LayoutParams layoutParams;

    private boolean isShowing = false;
    private boolean touchConsumedByMove = false;
    private int lastX = 0;
    private int lastY = 0;
    private int firstX = 0;
    private int firstY = 0;
    public ImageButton ibHideFloatingPanel;
    private final TextView tvOBD_CoolantTemp;
    private ImageView ivSpeed;
    private TextView tvGPSSpeed;
    private ImageView ivOBD_CarBattery;
    private TextView tvOBD_CarBattery;
    private ImageView ivOBD_CVT_Data;
    private TextView tvOBD_CVT_Data;
    private TextView tvOBD_FuelTank;
    private BroadcastReceiver receiver;

    @SuppressLint("InflateParams")
    public FloatingWindow(Context context) {
        this.context = context;
        floatingView = LayoutInflater.from(this.context).inflate(R.layout.floating_panel, null);
        floatingView.setOnTouchListener(this);

        ibHideFloatingPanel = floatingView.findViewById(R.id.ibHideFloatingPanel);
        //ibHideFloatingPanel.setOnClickListener (this);

        tvOBD_CoolantTemp = floatingView.findViewById(R.id.tvOBD_CoolantTemp);
        ivSpeed = floatingView.findViewById(R.id.ivSpeed);
        //tvGPSSpeed = floatingView.findViewById(R.id.text_gps_speed_value);
        ivOBD_CarBattery = floatingView.findViewById(R.id.ivOBD_CarBattery);
        tvOBD_CarBattery = floatingView.findViewById(R.id.tvOBD_CarBattery);
        ivOBD_CVT_Data = floatingView.findViewById(R.id.ivOBD_CVT_Data);
        tvOBD_CVT_Data = floatingView.findViewById(R.id.tvOBD_CVT_Data);
        tvOBD_FuelTank = floatingView.findViewById(R.id.tvOBD_FuelTank);

        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
    }

    public void show() {
        if (Settings.canDrawOverlays(context)) {
            // dismiss();
            isShowing = true;
            getWindowManager().addView(floatingView, layoutParams);

            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (action.equals(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED)) {
                        tvOBD_CoolantTemp.setText(String.format(context.getString(R.string.text_obd_coolant_temp_f), intent.getStringExtra("coolantTemp")));
                    }
                    else if (action.equals(ACTION_OBD_ENGINE_2101_CHANGED)) {
                        EngineData engine = (EngineData) intent.getSerializableExtra("obd_engine_2101");
                        //updateOBD_CarBattery();
                        // speed
                        // TODO: 06.10.2025 select speed type from preferences
//                        int speed = engine.getSpeed();
//                        if (speed > 80) {
//                            ivSpeed.setImageResource(R.drawable.speedo_2);
//                        } else {
//                            ivSpeed.setImageResource(R.drawable.speedo_1);
//                        }
//                        tvGPSSpeed.setText(speed > 0 ? String.format(context.getString(R.string.text_gps_speed_value), speed) : "---");
//                        color_speed(tvGPSSpeed, speed);

                        // TODO: 06.10.2025 select voltage type from preferences
                        //voltage
                        float voltage = engine.getVoltage();
                        updateOBD_CarBattery(voltage);
                        //updateOBD_CarBattery(App.obd.can.engine.getVoltage());
                    }
                    else if (action.equals(ACTION_OBD_ENGINE_2102_CHANGED)) {
                        // TODO: 06.10.2025 select coolant type from preferences
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2102);
                        tvOBD_CoolantTemp.setText(String.format(context.getString(R.string.text_obd_coolant_temp_f), Integer.toString(engine.getCoolantTemperature())));
                        //updateOBD_CoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                    }
                    else if (action.equals(ACTION_OBD_CVT_2103_CHANGED)) {
                        CvtData cvtData = (CvtData) intent.getSerializableExtra(KEY_OBD_CVT_2103);
                        updateOBD_CVT_data(cvtData.getTemperature());
                    }
                    else if (action.equals(ACTION_OBD_METER_21A3_CHANGED)) {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A3);
                        updateOBD_FuelTank(meterData.getFuelLevel());
                    }
                }
            };
            context.registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2101_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2102_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_CVT_2103_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A3_CHANGED));
        }
    }

    public void updateOBD_CVT_data(int temp) {
        if (App.obd.can.cvt.getTemperature() > -255) {
            tvOBD_CVT_Data.setText(Integer.toString(App.obd.can.cvt.getTemperature()));
        } else {
            tvOBD_CVT_Data.setText("--");
        }
        if (App.obd.can.cvt.getTemperature() < 71) {
            ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_min);
        } else if (App.obd.can.cvt.getTemperature() < 91) {
            ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_nom_green);
        } else if (App.obd.can.cvt.getTemperature() < 103) {
            ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_nom_yellow);
        } else {
            ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_nom_orange);
        }
    }

    public void updateOBD_CarBattery(double voltage) {
            if (voltage < 12) {
                ivOBD_CarBattery.setImageResource(R.drawable.car_battery_bad);
            } else {
                ivOBD_CarBattery.setImageResource(R.drawable.car_battery_good);
            }
            TextViewToSpans(tvOBD_CarBattery, String.format("%1$.1f", voltage),
                    UiUtils.TEXT_SIZE_BEFORE_DOT, UiUtils.TEXT_SIZE_AFTER_DOT);
    }
    public void dismiss() {
        if (isShowing) {
            getWindowManager().removeView(floatingView);
            isShowing = false;
            context.unregisterReceiver(receiver);
            App.GS.isShowingFloatingPanel = false;
        }
    }

    public boolean isShowing() {
        return isShowing;
    }
    public WindowManager getWindowManager() {
        if (windowManager == null) {
            windowManager = (WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE);
        }
        return windowManager;
    }

    public void updateOBD_FuelTank(float remain){
//        if ( remain < 20 ) {
//            ivOBD_FuelTank.setImageResource( R.drawable.fuel_tank_min);
//        } else {
//            ivOBD_FuelTank.setImageResource( R.drawable.fuel_tank_full);
//        }
        //tvOBD_FuelTank.setText( String.format("%1$.1f", tank));
        //tvOBD_FuelTank.setText( String.format("%1$.1f", remain));
        if ( App.obd.can.meter.getFuelLevel() > 0)
        tvOBD_FuelTank.setText(String.format("%1$s", App.obd.can.meter.getFuelLevel()));
                            else tvOBD_FuelTank.setText(String.format("%1$s", "--"));
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
//        prefs.edit().putInt("kaierutils_modeFuelTank", modeFuelTank).apply();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibHideFloatingPanel:
                //dismiss();
                break;
            default:
                break;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int totalDeltaX = lastX - firstX;
        int totalDeltaY = lastY - firstY;
        if (event == null) return false;

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                firstX = lastX;
                firstY = lastY;
                break;
            case MotionEvent.ACTION_UP:
                v.performClick();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) event.getRawX() - lastX;
                int deltaY = (int) event.getRawY() - lastY;
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                if (abs(totalDeltaX) >= 5 || abs(totalDeltaY) >= 5) {
                    if (event.getPointerCount() == 1) {
                        layoutParams.x += deltaX;
                        layoutParams.y += deltaY;
                        touchConsumedByMove = true;
                        getWindowManager().updateViewLayout(floatingView, layoutParams);
                    } else {
                        touchConsumedByMove = false;
                    }
                } else {
                    touchConsumedByMove = false;
                }
                break;
            default:
                break;
        }
        return touchConsumedByMove;
    }
}
