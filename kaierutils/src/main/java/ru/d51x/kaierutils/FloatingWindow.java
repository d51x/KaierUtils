package ru.d51x.kaierutils;

import static java.lang.Math.abs;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2113_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CVT_2103_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2102_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2110_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A1_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A3_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AE_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CLIMATE_2113;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CVT_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21AE;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_MAF_CHANGED;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.d51x.kaierutils.Data.ClimateData;
import ru.d51x.kaierutils.Data.CombineMeterData;
import ru.d51x.kaierutils.Data.CvtData;
import ru.d51x.kaierutils.Data.EngineData;
import ru.d51x.kaierutils.utils.UiUtils;

public class FloatingWindow implements View.OnClickListener, View.OnTouchListener {

    public static int DEFAULT_ICON_SIZE = 48;
    public static int DEFAULT_MAIN_TEXT_SIZE = 40;
    public static int DEFAULT_SECOND_TEXT_SIZE = 28;
    public static int DEFAULT_UNITS_TEXT_SIZE = 16;
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

    private ImageView ivBatteryLevel;
    private TextView tvBatteryLevel;

    private ImageView ivCoolantTemp;
    private ImageView ivCoolantTempFan;
    private TextView tvCoolantTemp;

    private ImageView ivCvtTemperature;
    private TextView tvCvtTemperature;
    private ImageView ivTrip;
    private TextView tvTrip;
    private ImageView ivFuelConsump;
    private TextView tvFuelConsump;

    private ImageView ivFuelLevel;
    private TextView tvFuelLevel;

    private TextView tvSpeedUnit;
    private TextView tvCarBatteryUnit;
    private TextView tvCoolantTempUnit;
    private TextView tvCvtTempUnit;
    private TextView tvFuelTankUnit;
    private TextView tvFuelConsumptionUnit;
    private TextView tvTripUnit;


    private BroadcastReceiver receiver;
    private UiUtils ui = new UiUtils();

    public boolean showSpeed = true;
    public boolean showBatteryLevel = true;
    public boolean showCoolantTemperature = true;
    public boolean showCvtTemperature = true;
    public boolean showFuelLevel = true;
    public boolean showFuelConsumption = true;
    public boolean showDistance = true;

    public int iconSize = DEFAULT_ICON_SIZE;
    public int mainTextSize = DEFAULT_MAIN_TEXT_SIZE;
    public int secondTextSize = DEFAULT_SECOND_TEXT_SIZE;
    public int unitsTextSize = DEFAULT_UNITS_TEXT_SIZE;
    private LinearLayout layoutSpeed;
    private LinearLayout layoutBatteryLevel;
    private LinearLayout layoutCoolantTemperature;
    private LinearLayout layoutCvtTemperature;
    private LinearLayout layoutFuelLevel;
    private LinearLayout layoutFuelConsumption;
    private LinearLayout layoutDistance;
    @SuppressLint("InflateParams")
    public FloatingWindow(Context context, boolean vertical) {
        this.context = context;
        load();

        if (vertical) {
            floatingView = LayoutInflater.from(this.context).inflate(R.layout.floating_panel_vertical, null);
        } else {
            floatingView = LayoutInflater.from(this.context).inflate(R.layout.floating_panel, null);
        }

        layoutParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);


        //floatingView = LayoutInflater.from(this.context).inflate(R.layout.floating_panel, null);
        floatingView.setOnTouchListener(this);

        init();
    }

    private void load() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        showSpeed = sharedPreferences.getBoolean ( "floating_panel_show_speed", true);
        showBatteryLevel = sharedPreferences.getBoolean ( "floating_panel_show_battery_level", true);
        showCoolantTemperature = sharedPreferences.getBoolean ( "floating_panel_show_coolant_temperature", true);
        showCvtTemperature = sharedPreferences.getBoolean ( "floating_panel_show_cvt_temperature", true);
        showFuelLevel = sharedPreferences.getBoolean ( "floating_panel_show_fuel_level", true);
        showFuelConsumption = sharedPreferences.getBoolean ( "floating_panel_show_fuel_consumption", true);
        showDistance = sharedPreferences.getBoolean ( "floating_panel_show_distance", true);


        iconSize = Integer.parseInt(sharedPreferences.getString("FLOATING_PANEL_ICON_SIZE", Integer.toString(DEFAULT_ICON_SIZE)));
        mainTextSize = Integer.parseInt(sharedPreferences.getString("FLOATING_PANEL_MAIN_TEXT_SIZE", Integer.toString(DEFAULT_MAIN_TEXT_SIZE)));
        secondTextSize = Integer.parseInt(sharedPreferences.getString("FLOATING_PANEL_SECOND_TEXT_SIZE", Integer.toString(DEFAULT_SECOND_TEXT_SIZE)));
        unitsTextSize = Integer.parseInt(sharedPreferences.getString("FLOATING_PANEL_UNITS_SIZE", Integer.toString(DEFAULT_UNITS_TEXT_SIZE)));
    }

    private void init() {

        ivHideFloatingPanel = floatingView.findViewById(R.id.ivHideFloatingPanel);

        ivCoolantTemp = floatingView.findViewById(R.id.ivCoolantTemp);
        ivCoolantTempFan = floatingView.findViewById(R.id.ivCoolantTempFan);
        tvCoolantTemp = floatingView.findViewById(R.id.tvCoolantTemp);

        ivSpeed = floatingView.findViewById(R.id.ivSpeed);
        tvSpeed = floatingView.findViewById(R.id.tvSpeed);

        ivBatteryLevel = floatingView.findViewById(R.id.ivCarBattery);
        tvBatteryLevel = floatingView.findViewById(R.id.tvCarBattery);

        ivTrip = floatingView.findViewById(R.id.ivTrip);
        tvTrip = floatingView.findViewById(R.id.tvTrip);

        ivCvtTemperature = floatingView.findViewById(R.id.ivCvtData);
        tvCvtTemperature = floatingView.findViewById(R.id.tvCvtData);

        ivFuelLevel = floatingView.findViewById(R.id.ivFuelTank);
        tvFuelLevel = floatingView.findViewById(R.id.tvFuelTank);
        ivFuelConsump = floatingView.findViewById(R.id.ivFuelConsump);
        tvFuelConsump = floatingView.findViewById(R.id.tvFuelConsump);

        tvSpeedUnit = floatingView.findViewById(R.id.tvSpeedUnit);
        tvCarBatteryUnit = floatingView.findViewById(R.id.tvCarBatteryUnit);
        tvCoolantTempUnit = floatingView.findViewById(R.id.tvCoolantTempUnit);
        tvCvtTempUnit = floatingView.findViewById(R.id.tvCvtTempUnit);
        tvFuelTankUnit = floatingView.findViewById(R.id.tvFuelTankUnit);
        tvFuelConsumptionUnit = floatingView.findViewById(R.id.tvFuelConsumptionUnit);
        tvTripUnit = floatingView.findViewById(R.id.tvTripUnit);

        layoutSpeed = floatingView.findViewById(R.id.layoutSpeed);
        layoutBatteryLevel = floatingView.findViewById(R.id.layoutBatteryLevel);
        layoutCoolantTemperature = floatingView.findViewById(R.id.layoutCoolantTemperature);
        layoutCvtTemperature = floatingView.findViewById(R.id.layoutCvtTemperature);
        layoutFuelLevel = floatingView.findViewById(R.id.layoutFuelLevel);
        layoutFuelConsumption = floatingView.findViewById(R.id.layoutFuelConsumption);
        layoutDistance = floatingView.findViewById(R.id.layoutDistance);


        ui.updateSpeedText(tvSpeed, App.obd.can.engine.getSpeed(), App.GS.ui.isColorSpeed, mainTextSize);
        ui.updateSpeedIcon(ivSpeed, App.obd.can.engine.getSpeed());

        ui.updateBatteryLevelText(tvBatteryLevel, App.obd.can.engine.getVoltage(), mainTextSize, secondTextSize);
        ui.updateBatteryLevelIcon(ivBatteryLevel, App.obd.can.engine.getVoltage());

        ui.updateCvtTemperatureText(tvCvtTemperature, App.obd.can.cvt.getTemperature(), mainTextSize);
        ui.updateCvtTemperatureIcon(ivCvtTemperature, App.obd.can.cvt.getTemperature());

        ui.updateCoolantTemperatureText(tvCoolantTemp, (float)App.obd.can.engine.getCoolantTemperature(), mainTextSize);
        ui.updateCoolantTemperatureIcon(ivCoolantTemp, (float)App.obd.can.engine.getCoolantTemperature());

        ui.updateFuelLevelText(tvFuelLevel, App.obd.can.meter.getFuelLevel(), mainTextSize);
        ui.updateDistanceText(tvTrip, App.obd.todayTrip.distance, mainTextSize, secondTextSize);
        ui.updateFuelConsumptionText(tvFuelConsump, App.obd.oneTrip.fuelConsLp100KmAvg, mainTextSize, secondTextSize);

    }

    private void setImageSize(ImageView iv, int size) {
        iv.getLayoutParams().width = size;
        iv.getLayoutParams().height = size;
    }

    private void setIconsSize(int size) {
        setImageSize(ivSpeed, iconSize);
        setImageSize(ivBatteryLevel, iconSize);
        setImageSize(ivCoolantTemp, iconSize);
        setImageSize(ivCvtTemperature, iconSize);
        setImageSize(ivFuelLevel, iconSize);
        setImageSize(ivFuelConsump, iconSize);
        setImageSize(ivTrip, iconSize);
    }

    private void setTextUnitsSize(int size) {
        tvSpeedUnit.setTextSize(size);
        tvCarBatteryUnit.setTextSize(size);
        tvCoolantTempUnit.setTextSize(size);
        tvCvtTempUnit.setTextSize(size);
        tvFuelTankUnit.setTextSize(size);
        tvFuelConsumptionUnit.setTextSize(size);
        tvTripUnit.setTextSize(size);
    }

    public void show() {

        if (Settings.canDrawOverlays(context)) {

            dismiss();

            isShowing = true;
            layoutParams.gravity = Gravity.TOP | Gravity.START;
            layoutParams.x = App.GS.ui.floatingWindowLeft;
            layoutParams.y = App.GS.ui.floatingWindowTop;
            getWindowManager().addView(floatingView, layoutParams);

            setIconsSize(iconSize);
            setTextUnitsSize(unitsTextSize);

            showSpeedLayout(showSpeed);
            showBatteryLevelLayout(showBatteryLevel);
            showCoolantTempLayout(showCoolantTemperature);
            showCvtTempLayout(showCvtTemperature);
            showFuelLevelLayout(showFuelLevel);
            showFuelConsumptionLayout(showFuelConsumption);
            showDistanceLayout(showDistance);

            showUnits(App.GS.ui.floatingWindowShowUnits);

            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED.equals(action)) {
                        ui.updateCoolantTemperatureText(tvCoolantTemp, intent.getFloatExtra("coolantTempD", -255));
                    }
                    else if (OBD_BROADCAST_ACTION_MAF_CHANGED.equals(action)) {
                        ui.updateFuelConsumptionText(tvFuelConsump, App.obd.oneTrip.fuelConsLp100KmAvg);
                    }
                    else if (ACTION_OBD_ENGINE_2101_CHANGED.equals(action)) {
                        EngineData engine = (EngineData) intent.getSerializableExtra("obd_engine_2101");
                        // speed
                        // TODO: 06.10.2025 select speed type from preferences
                        if (engine != null) {
                            ui.updateSpeedText(tvSpeed, engine.getSpeed(), App.GS.ui.isColorSpeed);
                            ui.updateSpeedIcon(ivSpeed, engine.getSpeed());

                            // TODO: 06.10.2025 select voltage type from preferences
                            //voltage
                            float voltage = engine.getVoltage();
                            ui.updateBatteryLevelText(tvBatteryLevel, voltage);
                            ui.updateBatteryLevelIcon(ivBatteryLevel, voltage);
                        }
                    }
                    else if (ACTION_OBD_ENGINE_2102_CHANGED.equals(action)) {
                        // TODO: 06.10.2025 select coolant type from preferences
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2102);
                        if (engine != null) {
                            ui.updateCoolantTemperatureText(tvCoolantTemp, (float) engine.getCoolantTemperature());
                            ui.updateCoolantTemperatureIcon(ivCoolantTemp, (float) engine.getCoolantTemperature());
                            //updateOBD_CoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                        }
                    }
                    else if (ACTION_OBD_ENGINE_2110_CHANGED.equals(action)) {
                        // TODO: 06.10.2025 select coolant type from preferences
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2110);
                        if (engine != null) {
                            ui.updateFuelConsumptionText(tvFuelConsump, App.obd.oneTrip.fuelConsLp100KmAvg);
                        }
                    }
                    else if (ACTION_OBD_CVT_2103_CHANGED.equals(action)) {
                        CvtData cvtData = (CvtData) intent.getSerializableExtra(KEY_OBD_CVT_2103);
                        if (cvtData != null ) {
                            ui.updateCvtTemperatureText(tvCvtTemperature, cvtData.getTemperature());
                            ui.updateCvtTemperatureIcon(ivCvtTemperature, cvtData.getTemperature());
                        }
                    }
                    else if (ACTION_OBD_METER_21A3_CHANGED.equals(action)) {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A3);
                        if (meterData != null) {
                            ui.updateFuelLevelText(tvFuelLevel, meterData.getFuelLevel());
                        }
                    }
                    else if (ACTION_OBD_METER_21A1_CHANGED.equals(action)) {
                        CombineMeterData meter = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A1);
                        if (meter != null) {
                            ui.updateSpeedText(tvSpeed, meter.getVehicleSpeed(), App.GS.ui.isColorSpeed);
                            ui.updateSpeedIcon(ivSpeed, meter.getVehicleSpeed());
                        }
                    }
                    else if (ACTION_OBD_METER_21AE_CHANGED.equals(action)) {
                        CombineMeterData meter = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21AE);
                        if (meter != null) {
                            // App.obd.oneTrip.distance - текущая поездка
                            // App.obd.todayTrip.distance - общая поездка за день
                            ui.updateDistanceText(tvTrip, App.obd.todayTrip.distance);
                        }
                    }
                    else if (ACTION_OBD_CLIMATE_2113_CHANGED.equals(action)) {
                        ClimateData climate = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2113);
                        if (climate != null) {
//                        updateSpeedText(tvSpeed, climate.vehicleSpeed, App.GS.ui.isColorSpeed);
//                        updateSpeedIcon(ivSpeed, climate.vehicleSpeed);
                            }
                    }
                }
            };
            context.registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_MAF_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2101_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2102_CHANGED));
            context.registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2110_CHANGED));
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
                rememberWindowLocation();
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

    private void rememberWindowLocation() {
        int[] location = new int[2];
        floatingView.getLocationOnScreen(location);
        Log.d("FW", "Left: " + location[0] + " Top: " + location[1]);
        App.GS.ui.floatingWindowLeft = location[0];
        App.GS.ui.floatingWindowTop = location[1];

        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit()
                .putInt ("floating_window_left", App.GS.ui.floatingWindowLeft)
                .putInt ("floating_window_top", App.GS.ui.floatingWindowTop)
                .apply();

    }

    private void showSpeedLayout(boolean show) {
        layoutSpeed.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showBatteryLevelLayout(boolean show) {
        layoutBatteryLevel.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showCoolantTempLayout(boolean show) {
        layoutCoolantTemperature.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showCvtTempLayout(boolean show) {
        layoutCvtTemperature.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showFuelLevelLayout(boolean show) {
        layoutFuelLevel.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showFuelConsumptionLayout(boolean show) {
        layoutFuelConsumption.setVisibility(show ? View.VISIBLE : View.GONE);
    }
    private void showDistanceLayout(boolean show) {
        layoutDistance.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void showUnits(boolean show) {
        if (!App.GS.ui.floatingWindowVertical) return;
        int visibility = show ? View.VISIBLE : View.GONE;

        tvSpeedUnit = floatingView.findViewById(R.id.tvSpeedUnit);
        tvCarBatteryUnit = floatingView.findViewById(R.id.tvCarBatteryUnit);
        tvCoolantTempUnit = floatingView.findViewById(R.id.tvCoolantTempUnit);
        tvCvtTempUnit = floatingView.findViewById(R.id.tvCvtTempUnit);
        tvFuelTankUnit = floatingView.findViewById(R.id.tvFuelTankUnit);
        tvFuelConsumptionUnit = floatingView.findViewById(R.id.tvFuelConsumptionUnit);
        tvTripUnit = floatingView.findViewById(R.id.tvTripUnit);

        tvSpeedUnit.setVisibility(visibility);
        tvCarBatteryUnit.setVisibility(visibility);
        tvCoolantTempUnit.setVisibility(visibility);
        tvCvtTempUnit.setVisibility(visibility);
        tvFuelTankUnit.setVisibility(visibility);
        tvFuelConsumptionUnit.setVisibility(visibility);
        tvTripUnit.setVisibility(visibility);
    }
}
