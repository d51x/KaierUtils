package ru.d51x.kaierutils;

import static android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE;
import static android.widget.Toast.LENGTH_LONG;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_AWC_2130_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2110_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2111_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2113_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2132_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2160_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2161_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CLIMATE_2180_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CVT_2103_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_CVT_2110_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2102_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2103_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_2110_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_211D_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_ENGINE_211E_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A1_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A2_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A3_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A6_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21A8_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AD_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AE_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21AF_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_METER_21BC_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_PARKING_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CLIMATE_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CLIMATE_2111;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CLIMATE_2113;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CLIMATE_2160;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CLIMATE_2161;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CVT_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2101;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2102;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2103;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_2110;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_211D;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_ENGINE_211E;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A1;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A2;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21A3;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21AD;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21AE;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_METER_21BC;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_MAF_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_STATUS_CHANGED;
import static ru.d51x.kaierutils.utils.UiUtils.TEXT_SIZE_AFTER_DOT;
import static ru.d51x.kaierutils.utils.UiUtils.TEXT_SIZE_BEFORE_DOT;
import static ru.d51x.kaierutils.utils.UiUtils.TextViewToSpans;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TimeZone;

import ru.d51x.kaierutils.Data.CanMmcData;
import ru.d51x.kaierutils.Data.ClimateData;
import ru.d51x.kaierutils.Data.CombineMeterData;
import ru.d51x.kaierutils.Data.CvtData;
import ru.d51x.kaierutils.Data.EngineData;
import ru.d51x.kaierutils.TWUtils.TWUtilConst;
import ru.d51x.kaierutils.TWUtils.TWUtilEx;
import ru.d51x.kaierutils.utils.UiUtils;

public class MainActivity extends Activity implements View.OnClickListener,
													  OnLongClickListener{

    private static final int REQUEST_CODE_DRAW_OVERLAY_PERMISSION = 5;
    private static final int REQUEST_CODE_PERMISSION = 6;
    private static final int REQUEST_CODE_LOCATION_PERMISSION = 7;
    private static final int REQUEST_CODE_BLUETOOTH_PERMISSION = 8;
    private static final int REQUEST_CODE_BLUETOOTH_ACTION = 9;
    private TextView tvCurrentVolume;

    private int modeFuelTank = 0;
    private int modeEngineTemp = 0;
    private int modeFuelConsump = 0;

	private TextView tvDistance;
    private ImageView ivTrackTime;

    private TextView tvSpeed;
    private TextView tvTrackTime;
    private TextView tvTrackTime2;
    private TextView tvTrackTimeMinOrSec;
    private TextView tvTrackTimeHourOrMin;

    private TextView tvAverageSpeed;
    private TextView tvMaxSpeed;


    private ImageView ivVolumeLevel;
    private ImageView ivSpeed;

    private LinearLayout layoutObd2;
    private LinearLayout layoutObdFuel;
//    private LinearLayout layoutFuelConsump;
    private LinearLayout layoutTempData;
    private ImageView ivOBD2Status;
    private ImageView ivCarBattery;
    private TextView tvCarBattery;
    private ImageView ivCoolantTemp;
    private ImageView ivCoolantTempFan;
    private TextView tvCoolantTemp;

    private ImageView ivCvtData;
    private TextView tvCvtData;


    private ImageView ivFuelTank;
    private TextView tvFuelTank;
    private TextView tvFuelTankUnit;
    private ImageView ivFuelConsump;
    private TextView tvFuelConsump;
    private TextView tvFuelConsump2;

    private ImageView ivClimateFanSpeed;
    private ImageView ivClimateBlowDirection;
    private ImageView ivClimateAcState;
    private ImageView ivClimateRrecirculation;
    private ImageView ivClimateDefogger;

    private ImageView ivClimateBlowMode;
    private ImageView ivClimateFanMode;
    private ImageView ivClimateTemperature;

    private TextView tvClimateTemperature;
    private TextView tvOuterTemperature;

    private ConstraintLayout layoutMmcClimate;
    private ImageButton ibFloatingPanel;
    private ImageView ivHideFloatingPanel;



    private final UiUtils ui = new UiUtils();
	@SuppressLint("SuspiciousIndentation")
    @Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
        // Убираем заголовок
        if ( App.GS.ui.isHideHeader ) this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Убираем панель уведомлений
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView (R.layout.main_activity);
		Log.d ("MainActivity", "onCreate");

        if (savedInstanceState == null){
            startService(new Intent(this, BackgroundService.class));
        }

        registerReceivers(receiver);
        //floatingWindow = new FloatingWindow(getApplicationContext(), App.GS.ui.floatingWindowVertical);
		initComponents();
		setInitData();

        requestPermissions();
        bluetoothTurnOn();

        boolean showfloating = getIntent().getBooleanExtra("show_floating", false);
        //boolean showfloating = App.GS.ui.isAutoStartFloating;
        if (showfloating) {
            App.GS.showFloatingPanelButton = false;
            App.GS.isShowingFloatingPanel = true;
            ibFloatingPanel.setVisibility(View.INVISIBLE);
            showFloatingPanel();
            finish();
        }

	}

    private void bluetoothTurnOn() {
        Log.i("BT", "Bluetooth turn on ....");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S ) {
            BluetoothManager btManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            BluetoothAdapter btAdapter = btManager.getAdapter();
            if (!btAdapter.isEnabled()) {
                Log.i("BT", "BT adapter disabled. Enable it");
                btAdapter.enable();
                App.GS.btState = btAdapter.isEnabled();
            } else {
                Log.i("BT", "BT adapter already enabled.");
            }
        } else
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            Log.i("BT", "Bluetooth turn on .... Check permission BLUETOOTH_CONNECT");
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                Log.i("BT", "Permission granted. .... Turn on bt");
                BluetoothManager btManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
                BluetoothAdapter btAdapter = btManager.getAdapter();
                if (!btAdapter.isEnabled()) {
                    Log.i("BT", "BT adapter disabled. Enable it");
                    btAdapter.enable();
                    App.GS.btState = btAdapter.isEnabled();
                } else {
                    Log.i("BT", "BT adapter already enabled.");
                }
            }  else {
                Log.e("BT", "Permission not granted.");
            }
        } else {
            Log.i("BT", "Bluetooth turn on .... Check permission BLUETOOTH_CONNECT");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                Log.i("BT", "Permission granted. .... Send intent");
                Intent intent = new Intent(ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_CODE_BLUETOOTH_ACTION);
            } else {
                Log.e("BT", "Permission not granted.");
            }
        }
    }

	public void initComponents() {
		tvCurrentVolume = findViewById(R.id.tvCurrentVolume);

		// color speed
        tvSpeed = findViewById(R.id.text_gps_speed_value);
        ivSpeed = findViewById(R.id.ivSpeed);
        tvAverageSpeed = findViewById(R.id.tvAverageSpeed);
        tvMaxSpeed = findViewById(R.id.tvMaxSpeed);

		// track time
        tvTrackTime = findViewById(R.id.tvTrackTime);
        tvTrackTime2 = findViewById(R.id.tvTrackTime2);
        tvTrackTimeMinOrSec = findViewById(R.id.tvTrackTimeMinOrSec);
        tvTrackTimeHourOrMin = findViewById(R.id.tvTrackTimeHourOrMin);
        ivTrackTime = findViewById(R.id.ivTrackTime);

		// track distance
        tvDistance = findViewById(R.id.tvGPSDistance);

        // volume
        ivVolumeLevel = findViewById(R.id.ivVolumeLevel);
        ivVolumeLevel.setOnClickListener(this);

        // bluetooth & obd connection status
        ivOBD2Status = findViewById(R.id.ivOBD2Status);
        ivOBD2Status.setOnClickListener(this);
        ivOBD2Status.setOnLongClickListener(this);

        //
        ivCarBattery = findViewById(R.id.ivCarBattery);
        tvCarBattery = findViewById(R.id.tvCarBattery);
        tvCarBattery.setText("--");

        ivCoolantTemp = findViewById(R.id.ivCoolantTemp);
        ivCoolantTempFan = findViewById(R.id.ivCoolantTempFan);
        tvCoolantTemp = findViewById(R.id.tvCoolantTemp);
        tvCoolantTemp.setText("--");

        ivCvtData = findViewById(R.id.ivCvtData);
        tvCvtData = findViewById(R.id.tvCvtData);
        tvCvtData.setText("--");


        ivFuelTank = findViewById(R.id.ivFuelTank);
        tvFuelTank = findViewById(R.id.tvFuelTank);
        tvFuelTank.setText("--");
        tvFuelTankUnit = findViewById(R.id.tvOBD_FuelTank_desc);

        ivFuelConsump = findViewById(R.id.ivFuelConsump);
        tvFuelConsump = findViewById(R.id.tvOBD_FuelConsump);
        tvFuelConsump.setText("--");

        ivClimateFanSpeed = findViewById(R.id.iv_air_fan_speed);
        ivClimateFanSpeed.setImageResource( R.drawable.climate_fan_speed_0);

        ivClimateBlowDirection = findViewById(R.id.iv_air_direction);
        ivClimateBlowDirection.setImageResource(R.drawable.climate_air_wind_face);

        ivClimateAcState = findViewById(R.id.iv_air_ac_state);

        ivClimateRrecirculation = findViewById(R.id.iv_air_recirculation);
        //ivClimateRrecirculation.setVisibility( View.INVISIBLE );

        ivClimateDefogger = findViewById(R.id.iv_air_defogger);
        //ivClimateDefogger.setVisibility( View.INVISIBLE );

        ivClimateBlowMode = findViewById(R.id.iv_ac_blow_auto);
        //ivClimateBlowMode.setVisibility( View.INVISIBLE );

        ivClimateFanMode = findViewById(R.id.iv_ac_fan_mode);
        //ivClimateFanMode.setVisibility( View.INVISIBLE );

        ivClimateTemperature = findViewById(R.id.iv_air_temp);

        tvClimateTemperature = findViewById(R.id.tv_air_cond_temp);
        tvOuterTemperature = findViewById(R.id.tvOuterTemperature);

        layoutMmcClimate = findViewById(R.id.layout_MMC_climate);

        ibFloatingPanel = findViewById(R.id.ibFloatingPanel);

        ibFloatingPanel.setOnClickListener (this);
        App.floatingWindow.ivHideFloatingPanel.setOnClickListener(this);
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_DRAW_OVERLAY_PERMISSION:
                if (Settings.canDrawOverlays(getApplicationContext())) {
                    App.floatingWindow.show();
                } else {
                    Log.e("Main", "Permission is not granted!");
                    Toast.makeText(getApplicationContext(), "Permission is not granted!", LENGTH_LONG).show();
                }
                break;
            case REQUEST_CODE_LOCATION_PERMISSION:
                break;
            case REQUEST_CODE_BLUETOOTH_ACTION:
                if (resultCode == Activity.RESULT_OK) {
                    Log.i("BT", "BT adapter enabled, data " + data);
                    App.GS.btState = true;
                } else {
                    Log.i("BT", "BT adapter fail to turn on, data " + data);
                    App.GS.btState = false;
                }
                break;
            default: break;
        }
    }

    public void setInitData() {
		// gps info
		tvSpeed.setText( "---" );
        tvMaxSpeed.setText( String.format(getString(R.string.text_max_speed), "---"));
        tvAverageSpeed.setText( String.format( getString(R.string.text_average_speed), "---"));

		//track time & distance
		tvTrackTime.setText( getString(R.string.text_gps_track_time_null));
		tvTrackTime2.setText( getString(R.string.text_gps_track_time_null));
		tvTrackTimeMinOrSec.setText(getString(R.string.text_gps_track_time_format_sec));
		tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));
		tvDistance.setText( "----.-" );


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        modeFuelTank = prefs.getInt("kaierutils_modeFuelTank", 0);
        modeFuelConsump = prefs.getInt("kaierutils_modeFuelConsump", 0);

//        ivFuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst);
        //ivFuelTank.setImageResource(R.drawable.fuel_tank_in_tank_full);

        switch_fuel_consump_mode(false);
        switch_fuel_tank_mode(false);
    }

    public void updataData() {
        saveFuelTankToStorage(App.obd.totalTrip.fuelRemains);
	}

	public void onStart() {
		super.onStart();
		updataData();
	}

	@SuppressLint("SetTextI18n")
    public void onResume() {
		super.onResume();

		tvCurrentVolume.setText(Integer.toString(App.GS.getVolumeLevel()));

        setVolumeIcon(ivVolumeLevel, App.GS.getVolumeLevel());
        //TWUtilEx.requestAudioFocusState();
        updateOBDStatus(App.obd.isConnected);

//        layoutFuelConsump.setVisibility( (App.obd.fuelConsumpShow) ? View.VISIBLE : View.GONE);

//        layoutMmcClimate.setVisibility(App.obd.can.can_mmc_ac_data_show ? View.VISIBLE : View.GONE);
        ibFloatingPanel.setVisibility(!App.GS.isShowingFloatingPanel ? View.VISIBLE : View.INVISIBLE);

        // обновить данные OBD
        updateClimateData(App.obd.can.climate);
	}

    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
//                case R.id.layout_gps_speed:
//                    this.openOptionsMenu();
//                    return true;
//                case R.id.layout_waypoints:
//                    App.GS.gpsData.totalDistance = 0;
//                    tvDistance.setText( "----.-" );
//                    Toast.makeText(this, "Счетчик был сброшен", Toast.LENGTH_SHORT).show ();
//                    return true;
//                case R.id.layout_tracktime:
//	                switch(App.GS.gpsData.timeAtWayType) {
//		                case 0:
//			                App.GS.gpsData.timeAtWay = 0;
//			                App.GS.gpsData.prevTimeAtWay = 0;
//			                break;
//		                case 1:
//			                App.GS.gpsData.timeAtWayWithoutStops = 0;
//			                App.GS.gpsData.prevTimeAtWayWithoutStops = 0;
//			                break;
//		                default:
//			                break;
//	                }
//
//	                Toast.makeText(this, "Счетчик сброшен", Toast.LENGTH_SHORT).show();
//                    tvTrackTime.setText( getString(R.string.text_gps_track_time_null));
//                    tvTrackTime2.setText( getString(R.string.text_gps_track_time_null));
//                    tvTrackTimeMinOrSec.setText( getString(R.string.text_gps_track_time_format_sec));
//                    tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));
//
//                    return true;
                case R.id.ivOBD2Status:
                    showObd2Activity(MainActivity.this);
                    return true;
                default:
                    return false;
            }

        }

    // анализируем, какая кнопка была нажата. Всего один метод для всех кнопок
    @SuppressLint({"NonConstantResourceId", "CommitPrefEdits"})
    @Override
    public void onClick(View v){

        switch (v.getId()) {
//            case R.id.layout_gps_speed:
//                //color_speed(tvGPSSpeed, App.GS.gpsData.speed);
//                ui.updateSpeedText(tvSpeed, 0, App.GS.ui.isColorSpeed);
//                App.GS.ui.isColorSpeed = ! App.GS.ui.isColorSpeed;
//	            PreferenceManager.getDefaultSharedPreferences (App.getInstance())
//                        .edit()
//                        .putBoolean ("kaierutils_show_color_speed", App.GS.ui.isColorSpeed)
//                        .apply();
//                break;
//            case R.id.layout_tracktime:
//                App.GS.gpsData.timeAtWayType++;
//                if ( App.GS.gpsData.timeAtWayType > 1) App.GS.gpsData.timeAtWayType = 0;
//                if ( App.GS.gpsData.timeAtWayType == 0) {
//                    Toast.makeText(this, "Пройденное время с учетом простоя", Toast.LENGTH_SHORT).show();
//                } else if (App.GS.gpsData.timeAtWayType == 1) {
//                    Toast.makeText(this, "Пройденное время без учета простоя", Toast.LENGTH_SHORT).show();
//                }
//                PreferenceManager.getDefaultSharedPreferences(App.getInstance())
//                    .edit()
//                        .putInt("CAR_SETTINGS__GPS_TIME_AT_WAY_TYPE", App.GS.gpsData.timeAtWayType)
//                        .apply();
//                // сменить иконку, сменить текст
//                showFormatedTrackTime( App.GS.gpsData.timeAtWayType);
//                break;
            case R.id.ivOBD2Status:
//                if ( App.obd.isConnected ) {
//                    BackgroundService.stopOBDThread();
//                } else {
//                    BackgroundService.startOBDThread();
//                }
                this.openOptionsMenu();
                break;
//            case R.id.layout_fuel_data:
//                switch_fuel_tank_mode(true);
//                break;
//            case R.id.layout_fuel_consump:
//                switch_fuel_consump_mode(true);
//                break;
//            case R.id.layout_temp_data:
//                switch_temp_mode();
//                break;
            case R.id.ibFloatingPanel:
                App.GS.showFloatingPanelButton = false;
                App.GS.isShowingFloatingPanel = true;
                ibFloatingPanel.setVisibility(View.INVISIBLE);
                showFloatingPanel();
                break;
            case R.id.ivHideFloatingPanel:
                App.GS.isShowingFloatingPanel = false;
                App.GS.showFloatingPanelButton = true;
                App.floatingWindow.dismiss();
                ibFloatingPanel.setVisibility(View.VISIBLE);
                ibFloatingPanel.invalidate();
                break;
            case R.id.ivVolumeLevel:
                this.openOptionsMenu();
                break;
            default:
                break;
        }

    }


    private void showFloatingPanel() {
        if (Settings.canDrawOverlays(getApplicationContext())) {
            if (!App.floatingWindow.isShowing()) {
                App.floatingWindow.show();
            }
        } else {
            startManageDrawOverlaysPermission();
        }

    }

    private void startManageDrawOverlaysPermission() {
        Intent intent = new Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                //Uri.parse("package:${applicationContext.packageName}")
                Uri.parse("package:" + getPackageName())
        );
        startActivityForResult(intent, REQUEST_CODE_DRAW_OVERLAY_PERMISSION);
    }

    private void requestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return;

        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                //Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                Manifest.permission.BLUETOOTH_CONNECT,
                Manifest.permission.BLUETOOTH_SCAN
        };

        List<String> permissionsToRequest = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                Log.e("Permissions", String.format("Permission %s is not granted", permission));
                permissionsToRequest.add(permission);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]), // Convert list to array
                    REQUEST_CODE_PERMISSION // Pass the request code
            );
        } else {
            Log.i("Permissions", "All permissions already granted");
        }
    }

    public void showFineLocationPermissionsAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle("Нужно разрешение");
        alertDialogBuilder.setMessage("Приложению необходимо разрешение на получение геолокации. Продолжить?");
        //alertDialogBuilder.setIcon(R.drawable.fuel_tank_full);

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                //Manifest.permission.ACCESS_BACKGROUND_LOCATION
                        }, REQUEST_CODE_PERMISSION);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void showBluetoothPermissionsAlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle("Нужно разрешение");
        alertDialogBuilder.setMessage("Приложению необходимо разрешение обнаружение и подключение к Bluetooth устройствам. Продолжить?");
        //alertDialogBuilder.setIcon(R.drawable.fuel_tank_full);

        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{
                                    Manifest.permission.BLUETOOTH_CONNECT,
                                    Manifest.permission.BLUETOOTH_SCAN
                            }, REQUEST_CODE_PERMISSION);
                }
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            List<String> deniedPermissions = new ArrayList<>();

            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permissions[i]);
                }
            }

            if (deniedPermissions.isEmpty()) {
                Log.i("Permissions", "All permissions granted");
            } else {
                Log.e("Permissions", "Permissions denied: " + deniedPermissions);
                boolean needOpenSettings = false;
                for (String permission : deniedPermissions) {
                    switch (permission) {
                        case Manifest.permission.ACCESS_COARSE_LOCATION:
                        //case Manifest.permission.ACCESS_BACKGROUND_LOCATION:
                        //case Manifest.permission.ACCESS_FINE_LOCATION:
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                                showFineLocationPermissionsAlertDialog();
                            } else {
                                needOpenSettings = true;
                            }
                            break;
                        case Manifest.permission.BLUETOOTH_CONNECT:
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                    Manifest.permission.BLUETOOTH_CONNECT)) {
                                showBluetoothPermissionsAlertDialog();
                            } else {
                                needOpenSettings = true;
                            }
                            break;
                        case Manifest.permission.BLUETOOTH_SCAN:
                            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                                    Manifest.permission.BLUETOOTH_SCAN)) {
                                showBluetoothPermissionsAlertDialog();
                            } else {
                                needOpenSettings = true;
                            }
                            break;
                    }
                }
                if (needOpenSettings) {
                    Intent appSettingsIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:" + getPackageName()));
                    startActivityForResult(appSettingsIntent, REQUEST_CODE_PERMISSION);
                }
            }
        }

    }


    private final BroadcastReceiver receiver = new BroadcastReceiver() {
		@SuppressLint({"SetTextI18n", "StringFormatMatches"})
        @Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
            if (action == null) return;

            switch (action) {
                case TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED:
                    int vol = intent.getIntExtra(TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED, 0);
                    tvCurrentVolume.setText(String.format(Integer.toString(vol)));
                    setVolumeIcon(ivVolumeLevel, vol);
                    break;
                case TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH:
                    break;
                case TWUtilConst.TW_BROADCAST_ACTION_SLEEP:
                    break;
                case TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP:
                    break;
                case GlSets.GPS_BROADCAST_ACTION_AGPS_RESET:
                    App.GS.gpsData.cntGpsHangs++;
                    break;

                case GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED: {
                    // TODO: 06.10.2025 select speed type from preferences
                    if (App.GS.isGpsSpeed) {
                        int speed = intent.getIntExtra("Speed", 0);
                        //ui.updateSpeedIcon(ivSpeed, (float) speed);
                        ui.updateSpeedText(tvSpeed, speed, App.GS.ui.isColorSpeed);


                        tvAverageSpeed.setText(String.format(getString(R.string.text_average_speed), App.GS.gpsData.averageSpeed));
                        tvMaxSpeed.setText(String.format(getString(R.string.text_max_speed), App.GS.gpsData.maxSpeed));

                        if (App.obd.newDistanceCalc) {
                            ui.updateDistanceText(tvDistance, App.obd.todayTrip.distance,
                                    52, 32);
                        } else {
                            float dist = App.GS.gpsData.totalDistance / 1000;
                            ui.updateDistanceText(tvDistance, dist,
                                    52, 32);
                        }
                    }
                    showFormatedTrackTime(App.GS.gpsData.timeAtWayType);
                    break;
                }
                case TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED:
                    // update screen

                    break;
                case OBD_BROADCAST_ACTION_STATUS_CHANGED:
                    boolean obd_status = intent.getBooleanExtra("Status", false);
                    updateOBDStatus(obd_status);
                    break;
                //*********** ACTIONS: COMMON OBD COMMANDS ***********************************
                case OBD_BROADCAST_ACTION_MAF_CHANGED:
                    saveFuelConsumptionToStorage(App.obd.oneTrip.fuelConsumptionLph);
                    saveFuelTankToStorage(App.obd.totalTrip.fuelRemains);
                    break;
                //************ ACTIONS: MMC CAN BLOCK 7E0 - ENGINE ********************************
                case ACTION_OBD_ENGINE_2101_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2101);
                        if (engine != null) {
                            // speed
                            if (!App.GS.isGpsSpeed && App.obd.speedFromEngine) {
                                ui.updateSpeedText(tvSpeed, engine.getSpeed(), App.GS.ui.isColorSpeed);
                                //ui.updateSpeedIcon(ivSpeed, engine.getSpeed());

                                tvAverageSpeed.setText(String.format(getString(R.string.text_average_speed), App.obd.oneTrip.getAverageSpeed()));
                                tvMaxSpeed.setText(String.format(getString(R.string.text_max_speed), App.obd.oneTrip.getMaxSpeed()));
                            }
                            //voltage
                            float voltage = engine.getVoltage();
                            updateCarBattery(voltage);
                        }
                    }
                    break;
                case ACTION_OBD_ENGINE_2102_CHANGED: {
                        // coolant
                        // TODO: 06.10.2025 select coolant type from preferences
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2102);
                        if (engine != null) {
                            updateCoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                        }
                    }
                    break;
                case ACTION_OBD_ENGINE_2103_CHANGED: {
                    // air flow sensor
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2103);
                        }
                    break;
                case ACTION_OBD_ENGINE_2110_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2110);
                        if (engine != null) {
                            saveFuelConsumptionToStorage(App.obd.oneTrip.fuelConsumptionLph);
                            saveFuelTankToStorage(App.obd.totalTrip.fuelRemains);
                        }
                    }
                    break;
                case ACTION_OBD_ENGINE_211D_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_211D);
                        if (engine != null) {
                            updateCoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                        }
                    }
                    break;
                case ACTION_OBD_ENGINE_211E_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_211E);
                    }
                    break;
                //************ ACTIONS: MMC CAN BLOCK 7E1 - CVT ***********************************
                case ACTION_OBD_CVT_2103_CHANGED: {
                        CvtData cvtData = (CvtData) intent.getSerializableExtra(KEY_OBD_CVT_2103);
                        if (cvtData != null) {
                            updateCvtTemp(cvtData.getTemperature());

                            if (!App.GS.isGpsSpeed && App.obd.speedFromCVT) {
                                ui.updateSpeedText(tvSpeed, cvtData.getVehicleSpeed(), App.GS.ui.isColorSpeed);
                                //ui.updateSpeedIcon(ivSpeed, cvtData.getVehicleSpeed());
                                tvAverageSpeed.setText(String.format(getString(R.string.text_average_speed), App.obd.oneTrip.getAverageSpeed()));
                                tvMaxSpeed.setText(String.format(getString(R.string.text_max_speed), App.obd.oneTrip.getMaxSpeed()));
                            }
                        }
                    }
                    break;
                //************ ACTIONS: MMC CAN BLOCK 6A0 - COMBINE METER **************************
                case ACTION_OBD_METER_21A1_CHANGED: {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A1);
                        //speed
                        if (meterData != null) {
                            if (!App.GS.isGpsSpeed && App.obd.speedFromMeter) {
                                ui.updateSpeedText(tvSpeed, meterData.getVehicleSpeed(), App.GS.ui.isColorSpeed);
                                //ui.updateSpeedIcon(ivSpeed, meterData.getVehicleSpeed());
                                tvAverageSpeed.setText(String.format(getString(R.string.text_average_speed), App.obd.oneTrip.getAverageSpeed()));
                                tvMaxSpeed.setText(String.format(getString(R.string.text_max_speed), App.obd.oneTrip.getMaxSpeed()));
                            }
                        }
                    }
                    break;
                case ACTION_OBD_METER_21A2_CHANGED: {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A2);
                        //rpm
                    }
                    break;
                case ACTION_OBD_METER_21A3_CHANGED: {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A3);
                        if (meterData != null) {
                            saveFuelTankToStorage(meterData.getFuelLevel());
                        }
                    }
                    break;
                case ACTION_OBD_METER_21AD_CHANGED: {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21AD);
                        //mileage
                    }
                    break;
                case ACTION_OBD_METER_21AE_CHANGED: {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21AE);
                        if (meterData != null) {
                            // tripA tripB
                            if (App.obd.newDistanceCalc) {
                                ui.updateDistanceText(tvDistance, App.obd.todayTrip.distance,
                                        52, 32);
                            }
                        }
                    }
                    break;
                case ACTION_OBD_METER_21BC_CHANGED: {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21BC);
                        //service reminder
                    }
                    break;
                //************ ACTIONS: MMC CAN BLOCK 688 - AIR CONDITION **************************
                case ACTION_OBD_CLIMATE_2110_CHANGED: {
                        ClimateData climateData = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2110);
                        // coolant temp
                    }
                    break;
                case ACTION_OBD_CLIMATE_2111_CHANGED: {
                        ClimateData climateData = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2111);
                        //updateClimateExteriorTemperature(climateData.externalTemperature);
                    }
                    break;
                case ACTION_OBD_CLIMATE_2113_CHANGED: {
                        ClimateData climateData = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2113);
                        if (climateData != null) {
                            //external temp
                            ui.updateExternalTemperature(tvOuterTemperature, climateData.externalTemperature);
                            //rpm
                            //speed
                            if (!App.GS.isGpsSpeed && App.obd.speedFromClimate) {
                                ui.updateSpeedText(tvSpeed, climateData.vehicleSpeed, App.GS.ui.isColorSpeed);
                                //ui.updateSpeedIcon(ivSpeed, climateData.vehicleSpeed);
                                tvAverageSpeed.setText(String.format(getString(R.string.text_average_speed), App.obd.oneTrip.getAverageSpeed()));
                                tvMaxSpeed.setText(String.format(getString(R.string.text_max_speed), App.obd.oneTrip.getMaxSpeed()));
                            }
                        }
                    }
                    break;
                case ACTION_OBD_CLIMATE_2160_CHANGED: {
                        ClimateData climateData = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2160);
                        if (climateData != null) {
                            ui.updateClimateFanMode(ivClimateFanMode, climateData.fan_mode);
                            ui.updateClimateBlowMode(ivClimateBlowMode, climateData.blow_mode);

                            ui.updateClimateTemperatureText(tvClimateTemperature, climateData.temperature);
                            ui.updateClimateTemperatureIcon(ivClimateTemperature, climateData.temperature);
                        }
                    }
                    break;
                case ACTION_OBD_CLIMATE_2161_CHANGED: {
                        ClimateData climateData = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2161);
                        if (climateData != null) {
                            ui.updateClimateBlowDirection(ivClimateBlowDirection, climateData.blow_direction);
                            ui.updateClimateFanSpeed(ivClimateFanSpeed, climateData.fan_speed);
                            ui.updateClimateAcState(ivClimateAcState, climateData.ac_state);
                            ui.updateClimateRecirculation(ivClimateRrecirculation, climateData.recirculation_state);
                            ui.updateClimateDefogger(ivClimateDefogger, climateData.defogger_state);
                        }
                    }
                    break;
            }

		}
	};





    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void openOptionsMenu() {
        //super.openOptionsMenu();
        Configuration config = getResources().getConfiguration();
        if((config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                > Configuration.SCREENLAYOUT_SIZE_LARGE) {

            int originalScreenLayout = config.screenLayout;
            config.screenLayout = Configuration.SCREENLAYOUT_SIZE_LARGE;
            super.openOptionsMenu();
            config.screenLayout = originalScreenLayout;

        } else {
            super.openOptionsMenu();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
	        case R.id.menu_general_settings:
		        show_general_settings();
		        return true;
	        case R.id.menu_odb2_settings:
		        showObd2Activity(MainActivity.this);
		        return true;
	        case R.id.menu_agps_reset:
		        Intent intent = new Intent();
		        intent.setAction( GlSets.GPS_BROADCAST_ACTION_AGPS_RESET );
		        sendBroadcast(intent);
		        return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	private void show_general_settings() {
		try {
			Intent it = new Intent();
			it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.Settings.SettingsActivity");
			//it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			startActivity(it);
		} catch (Exception e) {
            Log.e("Main", Objects.requireNonNull(e.getMessage()));
		}


	}

    private void setVolumeIcon(ImageView iv, int vol) {
        if ( vol < 1 ) {
            iv.setImageResource(R.drawable.volume_mute);
        } else if ( vol <= 5 ) {
            iv.setImageResource(R.drawable.volume_low);
        } else if (vol <= 10 ) {
            iv.setImageResource(R.drawable.volume_medium);
        } else {
            iv.setImageResource(R.drawable.volume_high);
        }

    }

    private void showObd2Activity(Context context) {
        try {
            Intent it = new Intent();
            it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.OBDIIActivity");
            it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("DefaultLocale")
    private void showFormatedTrackTime(int wayType) {
        long tm = 0;
        if ( wayType == 0)
        {
            tm = App.GS.gpsData.timeAtWay;// - TimeZone.getDefault().getOffset(0L);
            // поменять значек
            ivTrackTime.setImageResource(R.drawable.track_time_0);
        } else if ( wayType == 1) {
            tm =  App.GS.gpsData.timeAtWayWithoutStops;// - TimeZone.getDefault().getOffset(0L);
            ivTrackTime.setImageResource(R.drawable.track_time_1);

        }

        Date date = new Date( tm  );
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );
		int offset = TimeZone.getDefault().getOffset(0L) / 3600000;
        if ( tm > 1000*60*60 ) { // больше часа
            tvTrackTime.setText(  String.format("%1$02d", cal.get(Calendar.HOUR) - offset) );
            tvTrackTime2.setText(  String.format("%1$02d", cal.get(Calendar.MINUTE)) );
            tvTrackTimeMinOrSec.setText( getString(R.string.text_gps_track_time_format_min));
            tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_hour));


        } else { // меньше часа
            tvTrackTime.setText(  String.format("%1$02d", cal.get(Calendar.MINUTE)) );
            tvTrackTime2.setText(String.format("%1$02d", cal.get(Calendar.SECOND)));
            tvTrackTimeMinOrSec.setText( getString(R.string.text_gps_track_time_format_sec));
            tvTrackTimeHourOrMin.setText(getString(R.string.text_gps_track_time_format_min));

        }
    }

	@SuppressLint("UnspecifiedRegisterReceiverFlag")
    private void registerReceivers(BroadcastReceiver receiver) {
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED));

		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED));

		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_FIRST_FIX));
		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET));

		registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_STATUS_CHANGED));

        //********* OBD: COMMON COMMANDS **********************************************************
        // OBD GENERIC
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_MAF_CHANGED));

        // ENGINE
        //registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2101_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2102_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2103_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_2110_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_211E_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_ENGINE_211D_CHANGED));

        // CVT
        //registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CVT_2103_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CVT_2110_CHANGED));

        // CLIMATE
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2110_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2111_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2113_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2132_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2160_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2161_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_CLIMATE_2180_CHANGED));

        // COMBINE METER
        //registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A1_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A2_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A3_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A6_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21A8_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21AD_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21AE_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21AF_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_METER_21BC_CHANGED));

        // PARKING SENSORS
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_PARKING_2101_CHANGED));

        // AWC
        // registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_AWC_CHANGED));
        registerReceiver(receiver, new IntentFilter(ACTION_OBD_AWC_2130_CHANGED));
	}

    public void updateOBDStatus(boolean status){
        //
        //ivOBD2Status.setSelected(status);

        if ( status ) {
            //ivOBD2Status.setImageResource(R.drawable.bt_state);
            ivOBD2Status.setColorFilter(Color.BLUE);
        } else {
            //ivOBD2Status.setImageResource(R.drawable.bt_state);
            ivOBD2Status.setColorFilter(Color.WHITE);
        }
    }

    @SuppressLint("DefaultLocale")
    public void updateCarBattery(float voltage) {
//        ui.updateBatteryLevelIcon(ivCarBattery, voltage);
        ui.updateBatteryLevelText(tvCarBattery, voltage, 52, 36);
    }

    @SuppressLint("DefaultLocale")
    public void updateCoolantTemp(int mode, CanMmcData.State state){
        float temp = App.obd.can.engine.getCoolantTemperature();
//        ivCoolantTempFan.setVisibility(((state == CanMmcData.State.on)
//                        && App.obd.can.engine_fan_show) ? View.VISIBLE : View.INVISIBLE);
//        ui.updateCoolantTemperatureIcon(ivCoolantTemp, temp);
        ui.updateCoolantTemperatureText(tvCoolantTemp, temp);
    }

    public void updateCvtTemp(int temperature) {
        ui.updateCvtTemperatureText(tvCvtData, temperature);
//        ui.updateCvtTemperatureIcon(ivCvtData, temperature);
    }

    public void saveFuelTankToStorage(float remain){
        show_fuel_tank_data(modeFuelTank);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putInt("kaierutils_modeFuelTank", modeFuelTank).apply();
    }

    public void saveFuelConsumptionToStorage(float consump){
        show_fuel_consumption(modeFuelConsump);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putInt("kaierutils_modeFuelConsump", modeFuelConsump).apply();
    }


    // TODO: переключение режима показаний топлива в баке (учесть показания с приборки)
    private void switch_fuel_tank_mode(boolean increase) {
        // режим отображения уровня топлива
        // 0 - осталось в литрах
        // 1 - осталось в процентах
        // 2 - микс-режим (первая строка - литры, вторая строка - проценты
        // 3 - потрачено за поездку (л)
       if ( increase ) {
           modeFuelTank++;
           if (modeFuelTank > 2) modeFuelTank = 0;
       }

        show_fuel_tank_data(modeFuelTank);

//        switch ( modeFuelTank ) {
//            case 0:
//                ivFuelTank.setImageResource(R.drawable.fuel_tank_in_tank_full);
//                //Toast.makeText(this, "Остаток в баке(л) ", Toast.LENGTH_SHORT).show();
//                tvFuelTankUnit.setVisibility( View.VISIBLE);
//                break;
//            case 1:
//                ivFuelTank.setImageResource(R.drawable.fuel_tank_in_tank_percent_full);
//                //Toast.makeText(this, "Остаток в баке (%)", Toast.LENGTH_SHORT).show();
//                tvFuelTankUnit.setVisibility( View.GONE);
//                break;
//            case 2:
//                ivFuelTank.setImageResource(R.drawable.fuel_tank_used_full);
//                //Toast.makeText(this, "Израсходовано топлива (л)", Toast.LENGTH_SHORT).show();
//                tvFuelTankUnit.setVisibility( View.VISIBLE);
//                break;
//            default:
//                ivFuelTank.setImageResource(R.drawable.fuel_tank_full);
//                break;
//        }
    }

    // TODO: переключение режима показаний температуры (учесть включение вентилятора)
    private void switch_temp_mode() {
        // режим отображения уровня топлива
        // 0 - движок
        // 1 - коробка
        // 2 - деградация
        modeEngineTemp++;
        if ( modeEngineTemp > 0) modeEngineTemp = 0;

        updateCoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());

    }

    // переключение режима показаний расхода
    private void switch_fuel_consump_mode(boolean increase) {
        // режим отображения расхода
        // 0 - мгновенный
        // 1 - средний за поездку с учетом sleep
        // 2 - отображаем литры в час
        // 3 - комбинированный режим, первая строка - средний, вторая мгновенный
        if ( increase ) {
            modeFuelConsump++;
            if (modeFuelConsump > 3) modeFuelConsump = 0;
        }

        show_fuel_consumption(modeFuelConsump);

        // инфу о переключении надо отображать здесь
        switch ( modeFuelConsump ) {
            case 0:
//                ivFuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            case 1:
//                ivFuelConsump.setImageResource(R.drawable.fuel_consump_lpk_avg);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            case 2:
//                ivFuelConsump.setImageResource(R.drawable.fuel_consump_lph);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            case 3:
//                ivFuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst_avg);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            default:
//                show_hide_fuel_consump_line_2(false);
                ivFuelConsump.setImageResource(R.drawable.fuel_consump);
                break;
        }
    }

    // отобразить данные о текущем расходе топлива
    @SuppressLint("DefaultLocale")
    private void show_fuel_consumption(int mode) {
        switch (mode) {
            case 0:
                ui.updateFuelConsumptionText(tvFuelConsump, App.obd.oneTrip.fuelConsLp100KmInst);
                break;
            case 1:
                ui.updateFuelConsumptionText(tvFuelConsump, App.obd.oneTrip.fuelConsLp100KmAvg);
                break;
            case 2:
                ui.updateFuelConsumptionText(tvFuelConsump, App.obd.oneTrip.fuelConsumptionLph);
                break;
            case 3:
                ui.updateFuelConsumptionText2(tvFuelConsump, App.obd.oneTrip.fuelConsLp100KmAvg);
                ui.updateFuelConsumptionText3(tvFuelConsump2, App.obd.oneTrip.fuelConsLp100KmInst);
                break;
            default:
                break;
        }
    }

    // TODO: отобразить данные  о количестве топлива в баке (учесть показания с приборки)
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void show_fuel_tank_data(int mode) {
        switch (mode) {
            case 0:
                ui.updateFuelLevelText(tvFuelTank, App.obd.can.meter.getFuelLevel());
                break;
            case 1:
                if ( App.obd.can.meter.getFuelLevel() < 0) {
                    tvFuelTank.setText(String.format("%1$s", "--"));
                } else {
                    tvFuelTank.setText(String.format("%1$s",
                            Math.round((float) App.obd.can.meter.getFuelLevel() / ((float) App.obd.fuelTankCapacity / 100))) + "%");
                }
                break;
            case 2:
                TextViewToSpans(tvFuelTank, String.format("%1$.2f", App.obd.oneTrip.fuelUsage),
                        TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                break;
            default:
                break;
        }

    }


    // отобразить/спрятать второй показатель расхода при включении/выключении комбинированного режима
    private void show_hide_fuel_consump_line_2 (boolean line2) {
//        if ( ! App.obd.fuelConsumpShow) {
//            layoutFuelConsump.setVisibility( View.GONE );
//        } else {
//            layoutFuelConsump.setVisibility( View.VISIBLE );
//            if (line2) {
//                tvFuelConsump2.setVisibility(View.VISIBLE);
//            } else {
//                tvFuelConsump2.setVisibility(View.GONE);
//            }
//        }
    }

    @SuppressLint("DefaultLocale")
    private void updateClimateData(ClimateData climateData) {
        ui.updateClimateAcState(ivClimateAcState, climateData.ac_state);
        ui.updateClimateBlowMode(ivClimateBlowMode, climateData.blow_mode);
        ui.updateClimateRecirculation(ivClimateRrecirculation, climateData.recirculation_state);
        ui.updateClimateDefogger(ivClimateDefogger, climateData.defogger_state);
        ui.updateClimateBlowDirection(ivClimateBlowDirection, climateData.blow_direction);
        ui.updateClimateTemperatureText(tvClimateTemperature, climateData.temperature);
        ui.updateClimateTemperatureIcon(ivClimateTemperature, climateData.temperature);
        ui.updateClimateFanMode(ivClimateFanMode, climateData.fan_mode);
        ui.updateClimateFanSpeed(ivClimateFanSpeed, climateData.fan_speed);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ibFloatingPanel.setVisibility(!App.GS.isShowingFloatingPanel ? View.VISIBLE : View.INVISIBLE);
    }

}


