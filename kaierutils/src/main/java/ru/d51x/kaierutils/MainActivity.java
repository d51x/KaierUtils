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
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_CVT_2110;
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
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_MAF_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.OBD_BROADCAST_ACTION_SPEED_CHANGED;
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
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
import ru.d51x.kaierutils.Radio.Radio;
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
    private int modeCVT = 0;
    private int modeFuelConsump = 0;

	private TextView tvDistance;
    private ImageView ivTrackTime;

    private TextView tvGPSSpeed;
    private TextView tvTrackTime;
    private TextView tvTrackTime2;
    private TextView tvTrackTimeMinOrSec;
    private TextView tvTrackTimeHourOrMin;

    private TextView tvAverageSpeed;
    private TextView tvMaxSpeed;


    private ImageView ivVolumeLevel;
    private ImageView ivSpeed;

    private TextView tvRadioInfo1;
    private TextView tvRadioInfo2;
    private TextView tvMusicInfo1;
    private TextView tvMusicInfo2;
    private ImageView ivAlbumArt;
    private LinearLayout layout_radio_info;
    private LinearLayout layout_music_info;

    private LinearLayout layout_obd2;
    private LinearLayout layout_obd_fuel;
    private LinearLayout layout_fuel_consump;
    private LinearLayout layout_temp_data;
    private LinearLayout layout_cvt_data;
    private LinearLayout layout_battery;

    private ImageView ivOBD2Status;
    private ImageView ivOBD_CarBattery;
    private TextView tvOBD_CarBattery;
    private ImageView ivOBD_CoolantTemp;
    private ImageView ivOBD_CoolantTempFan;
    private TextView tvOBD_CoolantTemp;

    private ImageView ivOBD_CVT_Data;
    private TextView tvOBD_CVT_Data;


    private ImageView ivOBD_FuelTank;
    private TextView tvOBD_FuelTank;
    private TextView tvOBD_FuelTank_desc;
    private ImageView ivOBD_FuelConsump;
    private TextView tvOBD_FuelConsump;
    private TextView tvOBD_FuelConsump2;

    private ImageView ivClimateFanSpeed;
    private ImageView ivClimateBlowDirection;
    private ImageView ivClimateAcState;
    private ImageView ivClimateRrecirculation;
    private ImageView ivClimateDefogger;

    private ImageView ivClimateBlowMode;
    private ImageView ivClimateFanMode;
    private ImageView ivClimateTemperature;

    private TextView tvClimateTemperature;

    private RelativeLayout layout_MMC_climate;
    private ImageButton ibFloatingPanel;
    private ImageView ivHideFloatingPanel;



    private UiUtils ui = new UiUtils();
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
        LinearLayout layout_gps_speed = findViewById(R.id.layout_gps_speed);
		layout_gps_speed.setOnClickListener(this);

		// track time
        LinearLayout layout_tracktime = findViewById(R.id.layout_tracktime);
		layout_tracktime.setOnLongClickListener(this);
		layout_tracktime.setOnClickListener(this);


        LinearLayout layout_radio_music_info = findViewById(R.id.layout_radio_music_info);
        layout_radio_music_info.setOnLongClickListener(this);

		// gps info
		tvGPSSpeed = findViewById(R.id.text_gps_speed_value);

		// track distance
		tvTrackTime = findViewById(R.id.tvTrackTime);
		tvTrackTime2 = findViewById(R.id.tvTrackTime2);
		tvTrackTimeMinOrSec = findViewById(R.id.tvTrackTimeMinOrSec);
		tvTrackTimeHourOrMin = findViewById(R.id.tvTrackTimeHourOrMin);
		tvDistance = findViewById(R.id.tvGPSDistance);
        LinearLayout layout_waypoints = findViewById(R.id.layout_waypoints);
		layout_waypoints.setOnLongClickListener(this);
		ivTrackTime = findViewById(R.id.ivTrackTime);
		tvAverageSpeed = findViewById(R.id.tvAverageSpeed);
		tvMaxSpeed = findViewById(R.id.tvMaxSpeed);

		ivVolumeLevel = findViewById(R.id.ivVolumeLevel);
		ivSpeed = findViewById(R.id.ivSpeed);

        ivOBD2Status = findViewById(R.id.ivOBD2Status);
        ivOBD2Status.setOnClickListener(this);
        ivOBD2Status.setOnLongClickListener(this);

        ivOBD_CarBattery = findViewById(R.id.ivOBD_CarBattery);
        tvOBD_CarBattery = findViewById(R.id.tvOBD_CarBattery);
        tvOBD_CarBattery.setText("--");

        ivOBD_CoolantTemp = findViewById(R.id.ivOBD_CoolantTemp);
        ivOBD_CoolantTempFan = findViewById(R.id.ivOBD_CoolantTempFan);
        tvOBD_CoolantTemp = findViewById(R.id.tvOBD_CoolantTemp);
        tvOBD_CoolantTemp.setText("--");

        ivOBD_CVT_Data = findViewById(R.id.ivOBD_CVT_Data);
        tvOBD_CVT_Data = findViewById(R.id.tvOBD_CVT_Data);
        tvOBD_CVT_Data.setText("--");


        ivOBD_FuelTank = findViewById(R.id.ivOBD_FuelTank);
        tvOBD_FuelTank = findViewById(R.id.tvOBD_FuelTank);
        tvOBD_FuelTank.setText("--");
        tvOBD_FuelTank_desc = findViewById(R.id.tvOBD_FuelTank_desc);

        ivOBD_FuelConsump = findViewById(R.id.ivOBD_FuelConsump);
        tvOBD_FuelConsump = findViewById(R.id.tvOBD_FuelConsump);
        tvOBD_FuelConsump.setText("--");
        tvOBD_FuelConsump2 = findViewById(R.id.tvOBD_FuelConsump2);
        tvOBD_FuelConsump2.setText("--");
        tvOBD_FuelConsump2.setVisibility(View.GONE);

        tvRadioInfo1 = findViewById(R.id.tvRadioInfo1);
        tvRadioInfo2 = findViewById(R.id.tvRadioInfo2);
        tvMusicInfo1 = findViewById(R.id.tvMusicInfo1);
        tvMusicInfo2 = findViewById(R.id.tvMusicInfo2);
        tvMusicInfo1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvMusicInfo1.setMarqueeRepeatLimit(-1);
        tvMusicInfo1.setHorizontallyScrolling(true);
        tvMusicInfo1.setSelected(true);

        ivAlbumArt = findViewById(R.id.ivAlbumArt);



        layout_radio_info = findViewById(R.id.layout_radio_info);
        layout_music_info = findViewById(R.id.layout_music_info);
        layout_radio_info.setVisibility( View.GONE );
        layout_music_info.setVisibility( View.GONE );

        layout_obd_fuel = findViewById(R.id.layout_fuel_data);
        layout_obd2 = findViewById(R.id.layoutCanMMC);
        layout_obd_fuel.setOnLongClickListener (this);
        layout_obd_fuel.setOnClickListener (this);

        ivClimateFanSpeed = findViewById(R.id.iv_air_fan_speed);
        ivClimateFanSpeed.setImageResource( R.drawable.air_wind_seat_my_fan_0);

        ivClimateBlowDirection = findViewById(R.id.iv_air_direction);
        ivClimateBlowDirection.setImageResource(R.drawable.air_wind_seat_my_to_face);

        ivClimateAcState = findViewById(R.id.iv_air_ac_state);
        //iv_air_ac_state.setVisibility( View.INVISIBLE );

        ivClimateRrecirculation = findViewById(R.id.iv_air_recirculation);
        ivClimateRrecirculation.setVisibility( View.INVISIBLE );

        ivClimateDefogger = findViewById(R.id.iv_air_defogger);
        ivClimateDefogger.setVisibility( View.INVISIBLE );

        ivClimateBlowMode = findViewById(R.id.iv_ac_blow_auto);
        ivClimateBlowMode.setVisibility( View.INVISIBLE );

        ivClimateFanMode = findViewById(R.id.iv_ac_fan_mode);
        ivClimateFanMode.setVisibility( View.INVISIBLE );

        ivClimateTemperature = findViewById(R.id.iv_air_temp);

        tvClimateTemperature = findViewById(R.id.tv_air_cond_temp);

        layout_fuel_consump = findViewById(R.id.layout_fuel_consump);
        layout_fuel_consump.setOnClickListener (this);

        layout_temp_data = findViewById(R.id.layout_temp_data);
        layout_temp_data.setOnClickListener (this);

        layout_battery = findViewById(R.id.layout_battery);
        layout_battery.setOnClickListener(this);

        layout_cvt_data = findViewById(R.id.layout_cvt_data);
        layout_cvt_data.setOnClickListener (this);


        layout_MMC_climate = findViewById(R.id.layout_MMC_climate);

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
		tvGPSSpeed.setText( "---" );

		//track distance
		tvTrackTime.setText( getString(R.string.text_gps_track_time_null));
		tvTrackTime2.setText( getString(R.string.text_gps_track_time_null));
		tvTrackTimeMinOrSec.setText(getString(R.string.text_gps_track_time_format_sec));
		tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));
		tvDistance.setText( "----.-" );
		tvMaxSpeed.setText( String.format(getString(R.string.text_max_speed), "---"));
		tvAverageSpeed.setText( String.format( getString(R.string.text_average_speed), "---"));

        tvRadioInfo1.setText("");
        tvRadioInfo2.setText("");
        tvMusicInfo1.setText("");
        tvMusicInfo2.setText("");
        ivAlbumArt.setImageResource(R.drawable.toast_music);




        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        modeFuelTank = prefs.getInt("kaierutils_modeFuelTank", 0);
        modeFuelConsump = prefs.getInt("kaierutils_modeFuelConsump", 0);

        ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst);
        ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_in_tank_full);

        switch_fuel_consump_mode(false);
        switch_fuel_tank_mode(false);

    }

    public void updataData() {
        saveFuelTankToStorage(App.obd.totalTrip.fuel_remains);


	}

	public void onStart() {
		super.onStart();
		updataData();
	}

	public void onPause() {
		super.onPause();
//        if (App.GS.ui.showFloatingOnMinimize) {
//            if (!App.floatingWindow.isShowing()) {
//                App.floatingWindow.show();
//            }
//        }
	}

	@SuppressLint("SetTextI18n")
    public void onResume() {
		super.onResume();

//        if (App.GS.ui.showFloatingOnMinimize) {
//            // hide floating panel
//            App.floatingWindow.dismiss();
//        }

		tvCurrentVolume.setText(Integer.toString(App.GS.getVolumeLevel()));

        setVolumeIcon(ivVolumeLevel, App.GS.getVolumeLevel());
        TWUtilEx.requestAudioFocusState();
        // определим, запущено ли радио
        Radio.checkRadioActivityStarted(false);
        updateOBDStatus(App.obd.isConnected);

        layout_obd2.setVisibility( App.obd.useOBD ? View.VISIBLE : View.INVISIBLE );
        layout_battery.setVisibility( (App.obd.battery_show) ? View.VISIBLE : View.GONE);
        layout_obd_fuel.setVisibility( (App.obd.fuel_data_show) ? View.VISIBLE : View.GONE);
        layout_fuel_consump.setVisibility( (App.obd.fuel_consump_show) ? View.VISIBLE : View.GONE);

        layout_cvt_data.setVisibility( (App.obd.MMC_CAN && (App.obd.can.can_mmc_cvt_degr_show || App.obd.can.can_mmc_cvt_temp_show)) ? View.VISIBLE : View.GONE);
        layout_MMC_climate.setVisibility( (App.obd.MMC_CAN && App.obd.can.can_mmc_ac_data_show) ? View.VISIBLE : View.GONE);
        layout_temp_data.setVisibility( App.obd.engine_temp_show ? View.VISIBLE : View.GONE);
        ibFloatingPanel.setVisibility(!App.GS.isShowingFloatingPanel ? View.VISIBLE : View.INVISIBLE);

        // обновить данные OBD
        updateClimateData(App.obd.can.climate);
	}

    protected void onDestroy() {
//        if (App.GS.ui.showFloatingOnMinimize) {
//            if (!App.floatingWindow.isShowing()) {
//                App.floatingWindow.show();
//            }
//        }
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.layout_waypoints:
                    App.GS.gpsData.totalDistance = 0;
                    tvDistance.setText( "----.-" );
                    Toast.makeText(this, "Счетчик был сброшен", Toast.LENGTH_SHORT).show ();
                    return true;
                case R.id.layout_tracktime:
	                switch(App.GS.gpsData.timeAtWayType) {
		                case 0:
			                App.GS.gpsData.timeAtWay = 0;
			                App.GS.gpsData.prevTimeAtWay = 0;
			                break;
		                case 1:
			                App.GS.gpsData.timeAtWayWithoutStops = 0;
			                App.GS.gpsData.prevTimeAtWayWithoutStops = 0;
			                break;
		                default:
			                break;
	                }

	                Toast.makeText(this, "Счетчик сброшен", Toast.LENGTH_SHORT).show();
                    tvTrackTime.setText( getString(R.string.text_gps_track_time_null));
                    tvTrackTime2.setText( getString(R.string.text_gps_track_time_null));
                    tvTrackTimeMinOrSec.setText( getString(R.string.text_gps_track_time_format_sec));
                    tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));

                    return true;
                case R.id.layout_fuel_data:
                    // показать диалог с вводом топлива
                    show_obd_fuel_dialog();
                    return true;
                case R.id.ivOBD2Status:
                    show_obdii_activity(MainActivity.this);
                    return true;
                case R.id.layout_radio_music_info:
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
            case R.id.layout_gps_speed:
                //color_speed(tvGPSSpeed, App.GS.gpsData.speed);
                ui.updateSpeedText(tvGPSSpeed, 0, App.GS.ui.isColorSpeed);
                App.GS.ui.isColorSpeed = ! App.GS.ui.isColorSpeed;
	            PreferenceManager.getDefaultSharedPreferences (App.getInstance ()).edit().putBoolean ("kaierutils_show_color_speed", App.GS.ui.isColorSpeed).apply();
                break;
            case R.id.layout_tracktime:
                App.GS.gpsData.timeAtWayType++;
                if ( App.GS.gpsData.timeAtWayType > 1) App.GS.gpsData.timeAtWayType = 0;
                if ( App.GS.gpsData.timeAtWayType == 0) {
                    Toast.makeText(this, "Пройденное время с учетом простоя", Toast.LENGTH_SHORT).show();
                } else if (App.GS.gpsData.timeAtWayType == 1) {
                    Toast.makeText(this, "Пройденное время без учета простоя", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
                prefs.edit().putInt("CAR_SETTINGS__GPS_TIME_AT_WAY_TYPE", App.GS.gpsData.timeAtWayType);
                prefs.edit().apply();
                // сменить иконку, сменить текст
                showFormatedTrackTime( App.GS.gpsData.timeAtWayType);
                break;
            case R.id.ivOBD2Status:
//                if ( App.obd.isConnected ) {
//                    BackgroundService.stopOBDThread();
//                } else {
//                    BackgroundService.startOBDThread();
//                }
                this.openOptionsMenu();
                break;
            case R.id.layout_fuel_data:
                switch_fuel_tank_mode(true);
                break;
            case R.id.layout_fuel_consump:
                switch_fuel_consump_mode(true);
                break;
            case R.id.layout_temp_data:
                switch_temp_mode();
                break;
            case R.id.layout_cvt_data:
                switch_cvt_mode();
                break;
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
                    int speed = intent.getIntExtra("Speed", 0);
                    ui.updateSpeedIcon(ivSpeed, (float) speed);
                    ui.updateSpeedText(tvGPSSpeed, speed, App.GS.ui.isColorSpeed);


                    tvAverageSpeed.setText(String.format(getString(R.string.text_average_speed), App.GS.gpsData.averageSpeed));
                    tvMaxSpeed.setText(String.format(getString(R.string.text_max_speed), App.GS.gpsData.maxSpeed));

                    if (App.obd.newDistanceCalc) {
                        ui.updateDistanceText(tvDistance, App.obd.todayTrip.distance);
                    } else {
                        float dist = App.GS.gpsData.totalDistance / 1000;
                        ui.updateDistanceText(tvDistance, dist);
                    }
                    showFormatedTrackTime(App.GS.gpsData.timeAtWayType);

//                NotifyData notifyData = new NotifyData( App.getInstance () );
//                notifyData.line_inway_distance = dist > 0 ? "Пройдено: " + String.format(getString(R.string.text_gps_distance), dist).replace(",", ".") : "----.-";
//                notifyData.show();
                    break;
                }
                case TWUtilConst.TW_BROADCAST_ACTION_RADIO_CHANGED:
                    String title = intent.getStringExtra("Title");
                    if (title == null) break;
                    String freq = intent.getStringExtra("Frequency");
                    tvRadioInfo1.setText(title);
                    tvRadioInfo1.setVisibility(title.contentEquals(Radio.BLANK_STATION_NAME) ? View.GONE : View.VISIBLE);
                    tvRadioInfo2.setText(freq + " MHz");
                    break;
                case TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED:
                    // update screen
                    updateAudioModeInfo(App.GS.curAudioFocusID);

                    break;
                case GlSets.PWRAMP_BROADCAST_ACTION_TRACK_CHANGED:
                    if (App.GS.powerAmpOpt.interactWithPowerAmp && App.GS.ui.isShowMusicInfo && App.GS.powerAmpOpt.isPowerAmpPlaying) {
                        //String TrackTitle = intent.getStringExtra("TrackTitle");
                        //String AlbumArtist = intent.getStringExtra ("AlbumArtist");
                        Bitmap AlbumArt = intent.getParcelableExtra("AlbumArt");
                        //tvMusicInfo1.setText( TrackTitle );
                        tvMusicInfo1.setText(App.GS.powerAmpOpt.PowerAmp_TrackTitle);
                        //tvMusicInfo2.setText( AlbumArtist );
                        tvMusicInfo2.setText(App.GS.powerAmpOpt.PowerAmp_AlbumArtist);
                        //if (!AlbumArt.equals(App.GS.PowerAmp_AlbumArt))
                        //if ( AlbumArt != null ) {
                        ivAlbumArt.setImageBitmap(AlbumArt);
                        //} else {
                        //  ivAlbumArt.setImageResource( R.drawable.toast_music);
                        //}
                    }
                    break;
                case OBD_BROADCAST_ACTION_STATUS_CHANGED:
                    boolean obd_status = intent.getBooleanExtra("Status", false);
                    updateOBDStatus(obd_status);
                    break;
                //*********** ACTIONS: COMMON OBD COMMANDS ***********************************
                case OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED:
                    // TODO: 06.10.2025 select speed type from preferences
                    updateOBD_CarBattery(App.obd.obdData.voltage);
                    break;
                case OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED:
                    // TODO: 06.10.2025 select coolant type from preferences
                    updateOBD_CoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                    break;
                case OBD_BROADCAST_ACTION_MAF_CHANGED:
                    saveFuelConsumptionToStorage(App.obd.oneTrip.fuel_cons_lph);
                    saveFuelTankToStorage(App.obd.totalTrip.fuel_remains);
                    break;
                case OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED:

                    break;
                //************ ACTIONS: MMC CAN BLOCK 7E0 - ENGINE ********************************
                case ACTION_OBD_ENGINE_2101_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2101);
                        // speed
                        // TODO: 06.10.2025 select speed type from preferences
                        //updateSpeedText(tvGPSSpeed, engine.getSpeed(), App.GS.ui.isColorSpeed);
                        //updateSpeedIcon(ivSpeed, engine.getSpeed());

                        // TODO: 06.10.2025 select voltage type from preferences
                        //voltage
                        float voltage = engine.getVoltage();
                        updateOBD_CarBattery(voltage);
                        //updateOBD_CarBattery(App.obd.can.engine.getVoltage());
                    }
                    break;
                case ACTION_OBD_ENGINE_2102_CHANGED: {
                        // coolant
                        // TODO: 06.10.2025 select coolant type from preferences
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2102);
                        updateOBD_CoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                    }
                    break;
                case ACTION_OBD_ENGINE_2103_CHANGED: {
                    // air flow sensor
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2103);
                        }
                    break;
                case ACTION_OBD_ENGINE_2110_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_2110);
                        saveFuelConsumptionToStorage(App.obd.oneTrip.fuel_cons_lph);
                        saveFuelTankToStorage(App.obd.totalTrip.fuel_remains);
                    }
                    break;
                case ACTION_OBD_ENGINE_211D_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_211D);
                        updateOBD_CoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());
                    }
                    break;
                case ACTION_OBD_ENGINE_211E_CHANGED: {
                        EngineData engine = (EngineData) intent.getSerializableExtra(KEY_OBD_ENGINE_211E);
                    }
                    break;
                //************ ACTIONS: MMC CAN BLOCK 7E1 - CVT ***********************************
                case ACTION_OBD_CVT_2103_CHANGED: {
                        CvtData cvtData = (CvtData) intent.getSerializableExtra(KEY_OBD_CVT_2103);
//                        if (modeCVT == 0) {
//                            updateCvtTemperatureText(tvOBD_CVT_Data, cvtData.getTemperature());
//                            updateCvtTemperatureIcon(ivOBD_CVT_Data, cvtData.getTemperature());
//                        }
                        updateOBD_CVT_data(modeCVT, cvtData.getTemperature(), -255);
                    }
                    break;
                case ACTION_OBD_CVT_2110_CHANGED: {
                        CvtData cvtData = (CvtData) intent.getSerializableExtra(KEY_OBD_CVT_2110);
//                        if (modeCVT == 1) {
//                            updateCvtTemperatureText(tvOBD_CVT_Data, cvtData.getTemperature());
//                            updateCvtOilDegradationIcon(ivOBD_CVT_Data, cvtData.getOilDegradation());
//                        }
                        updateOBD_CVT_data(modeCVT, -255, cvtData.getOilDegradation());
                    }
                    break;
                //************ ACTIONS: MMC CAN BLOCK 6A0 - COMBINE METER **************************
                case ACTION_OBD_METER_21A1_CHANGED: {
                    CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A1);
                    //speed
                    }
                    break;
                case ACTION_OBD_METER_21A2_CHANGED: {
                    CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A2);
                    //rpm
                    }
                    break;
                case ACTION_OBD_METER_21A3_CHANGED: {
                        CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21A3);
                        saveFuelTankToStorage(meterData.getFuelLevel());
                    }
                    break;
                case ACTION_OBD_METER_21AD_CHANGED: {
                    CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21AD);
                    //mileage
                    }
                    break;
                case ACTION_OBD_METER_21AE_CHANGED: {
                    CombineMeterData meterData = (CombineMeterData) intent.getSerializableExtra(KEY_OBD_METER_21AE);
                    // tripA tripB
                        if (App.obd.newDistanceCalc) {
                            ui.updateDistanceTextWithSize(tvDistance, App.obd.todayTrip.distance,
                                    TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_BEFORE_DOT);
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
                    //external temp
                    //rpm
                    //speed
                }
                break;
                case ACTION_OBD_CLIMATE_2160_CHANGED: {
                        ClimateData climateData = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2160);
                        ui.updateClimateFanMode(ivClimateFanMode, climateData.fan_mode);
                        ui.updateClimateBlowMode(ivClimateBlowMode, climateData.blow_mode);

                        ui.updateClimateTemperatureText(tvClimateTemperature, climateData.temperature);
                        ui.updateClimateTemperatureIcon(ivClimateTemperature, climateData.temperature);
                    }
                    break;
                case ACTION_OBD_CLIMATE_2161_CHANGED: {
                        ClimateData climateData = (ClimateData) intent.getSerializableExtra(KEY_OBD_CLIMATE_2161);
                        ui.updateClimateBlowDirection(ivClimateBlowDirection, climateData.blow_direction);
                        ui.updateClimateFanSpeed(ivClimateFanSpeed, climateData.fan_speed);
                        ui.updateClimateAcState(ivClimateAcState, climateData.ac_state);
                        ui.updateClimateRecirculation(ivClimateRrecirculation, climateData.recirculation_state);
                        ui.updateClimateDefogger(ivClimateDefogger, climateData.defogger_state);
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
		        show_obdii_activity(MainActivity.this);
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

    private void show_obdii_activity(Context context) {
        try {
            Intent it = new Intent();
            it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.OBDIIActivity");
            it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        } catch (Exception e) {
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
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_RADIO_CHANGED));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED));

		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED));

		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_FIRST_FIX));
		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET));
		registerReceiver(receiver, new IntentFilter(GlSets.PWRAMP_BROADCAST_ACTION_TRACK_CHANGED));

		registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_STATUS_CHANGED));

        //********* OBD: COMMON COMMANDS **********************************************************
        // OBD GENERIC
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
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

    public void updateAudioModeInfo(int id) {
        layout_radio_info.setVisibility( View.GONE );
        layout_music_info.setVisibility( View.GONE );
        switch (id) {
            case TWUtilConst.TW_AUDIO_FOCUS_RADIO_ID:
                TWUtilEx.requestRadioInfo();
                if (  App.GS.radio.showInfo) {
                    layout_radio_info.setVisibility( View.VISIBLE );
                }
                break;
            case 0:
            case TWUtilConst.TW_AUDIO_FOCUS_MUSIC_ID:
                if ( App.GS.powerAmpOpt.interactWithPowerAmp && App.GS.ui.isShowMusicInfo ) {
                    layout_music_info.setVisibility(View.VISIBLE);
                }
                break;
              default:
                break;
        }
    }

    public void updateOBDStatus(boolean status){
        //
        if ( status ) {
            ivOBD2Status.setImageResource(R.drawable.obd_connected);
        } else {
            ivOBD2Status.setImageResource(R.drawable.obd_disconnected);
        }
    }

    @SuppressLint("DefaultLocale")
    public void updateOBD_CarBattery(float voltage) {
        if (App.obd.battery_show) {
            layout_battery.setVisibility(View.VISIBLE);
            ui.updateBatteryLevelIcon(ivOBD_CarBattery, voltage);
            ui.updateBatteryLevelText(tvOBD_CarBattery, voltage);
        } else {
            layout_battery.setVisibility(View.GONE);
        }
    }

    @SuppressLint("DefaultLocale")
    public void updateOBD_CoolantTemp(int mode, CanMmcData.State state){
        if ( App.obd.engine_temp_show ) {
            layout_temp_data.setVisibility( View.VISIBLE );

            // TODO: 06.10.2025 select coolant type from preferences
            //float temp = App.obd.obdData.coolant;
            float temp = App.obd.can.engine.getCoolantTemperature();
            ivOBD_CoolantTempFan.setVisibility(((state == CanMmcData.State.on) &&
                            App.obd.MMC_CAN && App.obd.can.engine_fan_show) ? View.VISIBLE : View.INVISIBLE);
            ui.updateCoolantTemperatureIcon(ivOBD_CoolantTemp, temp);
            ui.updateCoolantTemperatureText(tvOBD_CoolantTemp, temp);
        } else {
            layout_temp_data.setVisibility( View.GONE );
        }
    }

    @SuppressLint("SetTextI18n")
    public void updateOBD_CVT_data(int mode, int temperature, int degr) {
        if ( App.obd.MMC_CAN && (App.obd.can.can_mmc_cvt_degr_show
                || App.obd.can.can_mmc_cvt_temp_show)) {
            layout_cvt_data.setVisibility( View.VISIBLE);
        } else {
            layout_cvt_data.setVisibility( View.GONE);
            return;
        }

        switch (mode) {
            case 0:
                if ( App.obd.MMC_CAN && App.obd.can.can_mmc_cvt_temp_show) {
                    ui.updateCvtTemperatureText(tvOBD_CVT_Data, temperature);
                    ui.updateCvtTemperatureIcon(ivOBD_CVT_Data, temperature);
                }
                break;
            case 1:
                if ( App.obd.MMC_CAN && App.obd.can.can_mmc_cvt_degr_show) {
                    ui.updateCvtTemperatureText(tvOBD_CVT_Data, degr);
                    ui.updateCvtOilDegradationIcon(ivOBD_CVT_Data, degr);
                }
                break;
            default:
                ivOBD_CVT_Data.setImageResource( R.drawable.cvt_temp_nom);
                tvOBD_CVT_Data.setText( "--");
                break;
        }
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

    // диалог с впросом о заправке (полный бак или нет)
    public void show_obd_fuel_dialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle(getString(R.string.text_fuel_tank_title));
        alertDialogBuilder.setMessage(getString(R.string.text_fuel_tank_full));
        alertDialogBuilder.setIcon(R.drawable.fuel_tank_full);

        alertDialogBuilder.setPositiveButton("Отмена", (dialog, which) -> {
        });

        // Обработчик на нажатие НЕТ
        alertDialogBuilder.setNegativeButton("Да", (dialog, which) -> App.obd.setFullTank());

        // Обработчик на нажатие ОТМЕНА
        alertDialogBuilder.setNeutralButton("Нет", (dialog, which) -> {
            // show dialog to enter fuel details
            show_obd_fuel_detail();
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // показываем Alert
        alertDialog.show();
    }

    // диалог редактирования остатка кол-ва топлива в баке и  объема бака
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void show_obd_fuel_detail(){
        AlertDialog.Builder fuelDialog = new AlertDialog.Builder(MainActivity.this);
        fuelDialog.setTitle(getString(R.string.text_fuel_tank_detail));
        View linearlayout = getLayoutInflater().inflate(R.layout.fuel_dialog, null);
        fuelDialog.setView(linearlayout);

        final EditText etFuelTankCapacity = linearlayout.findViewById(R.id.etFuelTankCapacity);
        final EditText etFuelTankRemain = linearlayout.findViewById(R.id.etFuelTankRemain);
        final SeekBar seekBarFuel = linearlayout.findViewById(R.id.seekBarFuel);
        final TextView tvFuelPercent = linearlayout.findViewById(R.id.tvFuelPercent);

        etFuelTankCapacity.setText( String.format("%1$.0f", App.obd.fuel.getTankCapacity()));
        etFuelTankRemain.setText( String.format("%1$.0f", App.obd.totalTrip.fuel_remains));
        seekBarFuel.setMax( 100 );
        int percent = Math.round(App.obd.totalTrip.fuel_remains * 100 / App.obd.fuel.getTankCapacity());
        seekBarFuel.setProgress(percent);
        tvFuelPercent.setText(percent + " %");
        seekBarFuel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float fltank = Float.parseFloat(etFuelTankCapacity.getText().toString());
                float flremain = fltank * progress / 100;

                etFuelTankRemain.setText(String.format("%1$.0f", flremain));
                tvFuelPercent.setText(progress + " %");
            }
        });
        fuelDialog.setPositiveButton("Сохранить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        App.obd.setCustomTank(Float.parseFloat(etFuelTankCapacity.getText().toString()),
                                Float.parseFloat(etFuelTankRemain.getText().toString()));
                        saveFuelTankToStorage(App.obd.totalTrip.fuel_remains);
                    }
                });

        fuelDialog.setNegativeButton("Отмена",
                (dialog, which) -> {

                });
        fuelDialog.create();
        fuelDialog.show();
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

        switch ( modeFuelTank ) {
            case 0:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_in_tank_full);
                //Toast.makeText(this, "Остаток в баке(л) ", Toast.LENGTH_SHORT).show();
                tvOBD_FuelTank_desc.setVisibility( View.VISIBLE);
                break;
            case 1:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_in_tank_percent_full);
                //Toast.makeText(this, "Остаток в баке (%)", Toast.LENGTH_SHORT).show();
                tvOBD_FuelTank_desc.setVisibility( View.GONE);
                break;
            case 2:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_used_full);
                //Toast.makeText(this, "Израсходовано топлива (л)", Toast.LENGTH_SHORT).show();
                tvOBD_FuelTank_desc.setVisibility( View.VISIBLE);
                break;
            default:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_full);
                break;
        }
    }

    // TODO: переключение режима показаний температуры (учесть включение вентилятора)
    private void switch_temp_mode() {
        // режим отображения уровня топлива
        // 0 - движок
        // 1 - коробка
        // 2 - деградация
        modeEngineTemp++;
        if ( modeEngineTemp > 0) modeEngineTemp = 0;

        updateOBD_CoolantTemp(modeEngineTemp, App.obd.can.engine.isAcFanRelay());

    }

    // переключение режима показаний данных cvt
    private void switch_cvt_mode() {
        // режим отображения уровня топлива
        // 0 - коробка
        // 1 - деградация
        modeCVT++;
        if ( modeCVT > 1) modeCVT = 0;

        updateOBD_CVT_data(modeCVT,
                modeCVT == 0 ? App.obd.can.cvt.getTemperature() : -255,
                modeCVT == 1 ? App.obd.can.cvt.getOilDegradation() : -255);
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
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            case 1:
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_avg);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            case 2:
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lph);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            case 3:
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst_avg);
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                break;
            default:
                show_hide_fuel_consump_line_2(modeFuelConsump == 3);
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump);
                break;
        }
    }

    // отобразить данные о текущем расходе топлива
    @SuppressLint("DefaultLocale")
    private void show_fuel_consumption(int mode) {
        switch (mode) {
            case 0:
                ui.updateFuelConsumptionText(tvOBD_FuelConsump, App.obd.oneTrip.fuel_cons_lp100km_inst);
                break;
            case 1:
                ui.updateFuelConsumptionText(tvOBD_FuelConsump, App.obd.oneTrip.fuel_cons_lp100km_avg);
                break;
            case 2:
                ui.updateFuelConsumptionText(tvOBD_FuelConsump, App.obd.oneTrip.fuel_cons_lph);
                break;
            case 3:
                ui.updateFuelConsumptionText2(tvOBD_FuelConsump, App.obd.oneTrip.fuel_cons_lp100km_avg);
                ui.updateFuelConsumptionText3(tvOBD_FuelConsump2, App.obd.oneTrip.fuel_cons_lp100km_inst);
                break;
            default:
                break;
        }
    }

    // TODO: отобразить данные  о количестве топлива в баке (учесть показания с приборки)
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void show_fuel_tank_data(int mode) {
        if ( ! App.obd.fuel_data_show ) {
            layout_obd_fuel.setVisibility( View.GONE );
        } else {
            layout_obd_fuel.setVisibility( View.VISIBLE );
            switch (mode) {
                case 0:
                    if (App.obd.MMC_CAN && App.obd.can.can_mmc_fuel_remain_show) {
                        // по кан
                        ui.updateFuelLevelText(tvOBD_FuelTank, App.obd.can.meter.getFuelLevel());
                    } else {
                        // вычисляем
                        TextViewToSpans(tvOBD_FuelTank,
                                String.format("%1$.1f", App.obd.totalTrip.fuel_remains), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                    }

                    break;
                case 1:

                    if (App.obd.MMC_CAN && App.obd.can.can_mmc_fuel_remain_show) {
                        // по кан
                        if ( App.obd.can.meter.getFuelLevel() < 0)
                        tvOBD_FuelTank.setText(String.format("%1$s", "--"));
                        else tvOBD_FuelTank.setText(String.format("%1$s",
                                Math.round(App.obd.can.meter.getFuelLevel() / (App.obd.fuel.getTankCapacity() / 100)))  + "%");
                    } else {
                        // вычисляем
                        tvOBD_FuelTank.setText(String.format("%1$.0f",
                                (App.obd.totalTrip.fuel_remains * 100) / App.obd.fuel.getTankCapacity()) + "%");
                    }


                    break;
                case 2:
                    TextViewToSpans(tvOBD_FuelTank, String.format("%1$.2f", App.obd.oneTrip.fuel_usage),
                            TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                    break;
                default:
                    break;
            }
        }
    }


    // отобразить/спрятать второй показатель расхода при включении/выключении комбинированного режима
    private void show_hide_fuel_consump_line_2 (boolean line2) {
        if ( ! App.obd.fuel_consump_show ) {
            layout_fuel_consump.setVisibility( View.GONE );
        } else {
            layout_fuel_consump.setVisibility( View.VISIBLE );
            if (line2) {
                tvOBD_FuelConsump2.setVisibility(View.VISIBLE);
            } else {
                tvOBD_FuelConsump2.setVisibility(View.GONE);
            }
        }
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


