package ru.d51x.kaierutils;

import static java.lang.Math.abs;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2113_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CVT_2103_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2102_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A1_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A3_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AE_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CLIMATE_2113;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CVT_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21AE;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_MAF_CHANGED;
import static ru.d51x.kaierutils.utils.UiUtils.TEXT_SIZE_AFTER_DOT;
import static ru.d51x.kaierutils.utils.UiUtils.TEXT_SIZE_BEFORE_DOT;
import static ru.d51x.kaierutils.utils.UiUtils.TextViewToSpans;
import static ru.d51x.kaierutils.utils.UiUtils.updateBatteryLevelIcon;
import static ru.d51x.kaierutils.utils.UiUtils.updateBatteryLevelText;
import static ru.d51x.kaierutils.utils.UiUtils.updateCoolantTemperatureIcon;
import static ru.d51x.kaierutils.utils.UiUtils.updateCoolantTemperatureText;
import static ru.d51x.kaierutils.utils.UiUtils.updateCvtTemperatureIcon;
import static ru.d51x.kaierutils.utils.UiUtils.updateCvtTemperatureText;
import static ru.d51x.kaierutils.utils.UiUtils.updateFuelLevelText;
import static ru.d51x.kaierutils.utils.UiUtils.updateSpeedIcon;
import static ru.d51x.kaierutils.utils.UiUtils.updateSpeedText;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ru.d51x.kaierutils.Data.ClimateData;
import ru.d51x.kaierutils.Data.CombineMeterData;
import ru.d51x.kaierutils.Data.CvtData;
import ru.d51x.kaierutils.Data.EngineData;

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
    public ImageView ivHideFloatingPanel;

    private ImageView ivSpeed;

    private TextView tvSpeed;

    private final ImageView ivBatteryLevel;
    private final TextView tvBatteryLevel;

    private ImageView ivCoolantTemp;
    private ImageView ivCoolantTempFan;
    private final TextView tvCoolantTemp;

    private final ImageView ivCvtTemperature;
    private final TextView tvCvtTemperature;
    private final ImageView ivTrip;
    private final TextView tvTrip;
    private final TextView tvFuelConsump;

    private final TextView tvFuelLevel;

    private BroadcastReceiver receiver;

    @SuppressLint("InflateParams")
    public FloatingWindow(Context context) {
        this.context = context;
        floatingView = LayoutInflater.from(this.context).inflate(R.layout.floating_panel, null);
        floatingView.setOnTouchListener(this);

        ivHideFloatingPanel = floatingView.findViewById(R.id.ivHideFloatingPanel);
        //ibHideFloatingPanel.setOnClickListener (this);

        ivCoolantTemp = floatingView.findViewById(R.id.ivOBD_CoolantTemp);
        ivCoolantTempFan = floatingView.findViewById(R.id.ivOBD_CoolantTempFan);
        tvCoolantTemp = floatingView.findViewById(R.id.tvOBD_CoolantTemp);

        ivSpeed = floatingView.findViewById(R.id.ivSpeed);
        tvSpeed = floatingView.findViewById(R.id.tvSpeed);

        ivBatteryLevel = floatingView.findViewById(R.id.ivOBD_CarBattery);
        tvBatteryLevel = floatingView.findViewById(R.id.tvOBD_CarBattery);

        ivTrip = floatingView.findViewById(R.id.ivTrip);
        tvTrip = floatingView.findViewById(R.id.tvTrip);

        ivCvtTemperature = floatingView.findViewById(R.id.ivOBD_CVT_Data);
        tvCvtTemperature = floatingView.findViewById(R.id.tvOBD_CVT_Data);
        tvFuelLevel = floatingView.findViewById(R.id.tvOBD_FuelTank);
        tvFuelConsump = floatingView.findViewById(R.id.tvFuelConsump);

        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        tvBatteryLevel.setText("--");
        tvCoolantTemp.setText("--");
        tvCvtTemperature.setText("--");
        tvFuelLevel.setText("--");
        tvSpeed.setText("--");
        tvTrip.setText("---.-");
        tvFuelConsump.setText("--.-");

        if (App.obd.can.engine.getSpeed() > 0) {
            updateSpeedText(tvSpeed, App.obd.can.engine.getSpeed(), true);
            updateSpeedIcon(ivSpeed, App.obd.can.engine.getSpeed());
        }

        if (App.obd.can.engine.getVoltage() > 0) {
            updateBatteryLevelText(tvBatteryLevel, App.obd.can.engine.getVoltage());
            updateBatteryLevelIcon(ivBatteryLevel, App.obd.can.engine.getVoltage());
        }

        if (App.obd.can.cvt.getTemperature() > -255) {
            updateCvtTemperatureText(tvCvtTemperature, App.obd.can.cvt.getTemperature());
            updateCvtTemperatureIcon(ivCvtTemperature, App.obd.can.cvt.getTemperature());
        }

        if (App.obd.can.engine.getCoolantTemperature() > -255) {
            updateCoolantTemperatureText(tvCoolantTemp, (float)App.obd.can.engine.getCoolantTemperature());
            updateCoolantTemperatureIcon(ivCoolantTemp, (float)App.obd.can.engine.getCoolantTemperature());
        }

        if (App.obd.can.meter.getFuelLevel() > 0 && App.obd.can.meter.getFuelLevel() < 67) {
            updateFuelLevelText(tvFuelLevel, App.obd.can.meter.getFuelLevel());
        }
        if (App.obd.oneTrip.distance > 0) {
            tvTrip.setText(String.format(context.getString(R.string.text_distance), App.obd.oneTrip.distance));
        }
        if (App.obd.oneTrip.fuel_cons_lp100km_avg > 0) {
            TextViewToSpans(tvFuelConsump, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
            tvFuelConsump.setText(String.format(context.getString(R.string.text_distance), App.obd.oneTrip.fuel_cons_lp100km_avg));
        }
//        if (App.obd.oneTrip.fuel_cons_lph > 0) {
//            TextViewToSpans(tvFuelConsump, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lph), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
//            tvFuelConsump.setText(String.format(context.getString(R.string.text_distance), App.obd.oneTrip.fuel_cons_lph));
//        }

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
                        tvCoolantTemp.setText(String.format(context.getString(R.string.text_obd_coolant_temp_f), intent.getStringExtra("coolantTemp")));
                    }
                    else if (action.equals(OBD_BROADCAST_ACTION_MAF_CHANGED)) {
                        if (App.obd.oneTrip.fuel_cons_lp100km_avg > 0) {
                            TextViewToSpans(tvFuelConsump, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                            tvFuelConsump.setText(String.format(context.getString(R.string.text_distance), App.obd.oneTrip.fuel_cons_lp100km_avg));
                        } else {
                            tvFuelConsump.setText("--.-");
                        }
                    }
                    else if (action.equals(ACTION_OBD_ENGINE_2101_CHANGED)) {
                        EngineData engine = (EngineData) intent.getSerializableExtra("obd_engine_2101");
                        // speed
                        // TODO: 06.10.2025 select speed type from preferences
                        updateSpeedText(tvSpeed, engine.getSpeed(), App.GS.ui.isColorSpeed);
                        updateSpeedIcon(ivSpeed, engine.getSpeed());

                        // TODO: 06.10.2025 select voltage type from preferences
                        //voltage
                        float voltage = engine.getVoltage();
                        updateBatteryLevelText(tvBatteryLevel, voltage);
                        updateBatteryLevelIcon(ivBatteryLevel, voltage);
                    }
                    else if (action.equals(ACTION_OBD_ENGINE_2102_CHANGED)) {
                        // TODO: 06.10.2025 select coolant type from preferences
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2102);
                        updateCoolantTemperatureText(tvCoolantTemp, (float)engine.getCoolantTemperature());
                        updateCoolantTemperatureIcon(ivCoolantTemp, (float)engine.getCoolantTemperature());
                        //updateOBD_CoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                    }
                    else if (action.equals(ACTION_OBD_CVT_2103_CHANGED)) {
                        CvtData cvtData = (CvtData) intent.getSerializableExtra(KEY_OBD_CVT_2103);
                        updateCvtTemperatureText(tvCvtTemperature, cvtData.getTemperature());
                        updateCvtTemperatureIcon(ivCvtTemperature, cvtData.getTemperature());
                    }
                    else if (action.equals(ACTION_OBD_METER_21A3_CHANGED)) {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A3);
                        updateFuelLevelText(tvFuelLevel, meterData.getFuelLevel());
                    }
                    else if (action.equals(ACTION_OBD_METER_21A1_CHANGED)) {
                        CombineMeterData meter = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A1);
                        updateSpeedText(tvSpeed, meter.getVehicleSpeed(), App.GS.ui.isColorSpeed);
                        updateSpeedIcon(ivSpeed, meter.getVehicleSpeed());
                    }
                    else if (action.equals(ACTION_OBD_METER_21AE_CHANGED)) {
                        CombineMeterData meter = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21AE);
                        if (App.obd.oneTrip.distance > 0) {
                            tvTrip.setText(String.format(context.getString(R.string.text_distance), App.obd.oneTrip.distance));
                        } else {
                            tvTrip.setText("---.-");
                        }
                    }
                    else if (action.equals(ACTION_OBD_CLIMATE_2113_CHANGED)) {
                        ClimateData climate = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2113);
//                        updateSpeedText(tvSpeed, climate.vehicleSpeed, App.GS.ui.isColorSpeed);
//                        updateSpeedIcon(ivSpeed, climate.vehicleSpeed);
                    }
                }
            };
            context.registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_MAF_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2101_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2102_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_CVT_2103_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A1_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A3_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2113_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21AE_CHANGED));
        }
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivHideFloatingPanel:
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
