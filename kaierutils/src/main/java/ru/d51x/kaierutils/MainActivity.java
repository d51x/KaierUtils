package ru.d51x.kaierutils;

import static android.bluetooth.BluetoothAdapter.ACTION_REQUEST_ENABLE;
import static android.widget.Toast.LENGTH_LONG;

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
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
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
import ru.d51x.kaierutils.OBD2.OBDII;
import ru.d51x.kaierutils.Radio.Radio;
import ru.d51x.kaierutils.TWUtils.TWUtilConst;
import ru.d51x.kaierutils.TWUtils.TWUtilEx;

public class MainActivity extends Activity implements View.OnClickListener,
													  OnLongClickListener{

    private static final int TEXT_SIZE_BEFORE_DOT = 40;
    private static final int TEXT_SIZE_BEFORE_DOT_2 = 26;
    private static final int TEXT_SIZE_BEFORE_DOT_3 = 2;
    private static final int TEXT_SIZE_BEFORE_DOT_4 = 44;
    private static final int TEXT_SIZE_AFTER_DOT = 16;

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

	private TextView tvGPSDistance;
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

    private ImageView iv_air_fan_speed;
    private ImageView iv_air_direction;
    private ImageView iv_air_ac_state;
    private ImageView iv_air_recirculation;
    private ImageView iv_air_defogger;

    private ImageView iv_ac_blow_auto;
    private ImageView iv_ac_fan_mode;
    private ImageView iv_air_temp;

    private TextView tv_air_cond_temp;

    private RelativeLayout layout_MMC_climate;
    private ImageButton ibFloatingPanel;

    private FloatingWindow floatingWindow;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
        // Убираем заголовок
        if ( App.GS.isHideHeader ) this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Убираем панель уведомлений
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView (R.layout.main_activity);
		Log.d ("MainActivity", "onCreate");

        if (savedInstanceState == null){
            startService(new Intent(this, BackgroundService.class));
        }

        registerReceivers(receiver);
        floatingWindow = new FloatingWindow(getApplicationContext());
		initComponents();
		setInitData();

        requestPermissions();
        bluetoothTurnOn();

	}

    private void bluetoothTurnOn() {
        Log.i("BT", "Bluetooth turn on ....");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S ) {
            BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!mBluetoothAdapter.isEnabled()) {
                mBluetoothAdapter.isEnabled();
                App.GS.btState = mBluetoothAdapter.isEnabled();
            }
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
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
            }
        } else {
            Log.i("BT", "Bluetooth turn on .... Check permission BLUETOOTH_CONNECT");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                Log.i("BT", "Permission granted. .... Send intent");
                Intent intent = new Intent(ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, REQUEST_CODE_BLUETOOTH_ACTION);
            } else {
                Log.i("BT", "Permission not granted.");
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
		tvGPSDistance = findViewById(R.id.tvGPSDistance);
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

        iv_air_fan_speed = findViewById(R.id.iv_air_fan_speed);
        iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_0);

        iv_air_direction = findViewById(R.id.iv_air_direction);
        iv_air_direction.setImageResource(R.drawable.air_wind_seat_my_to_face);

        iv_air_ac_state = findViewById(R.id.iv_air_ac_state);
        //iv_air_ac_state.setVisibility( View.INVISIBLE );

        iv_air_recirculation = findViewById(R.id.iv_air_recirculation);
        iv_air_recirculation.setVisibility( View.INVISIBLE );

        iv_air_defogger = findViewById(R.id.iv_air_defogger);
        iv_air_defogger.setVisibility( View.INVISIBLE );

        iv_ac_blow_auto = findViewById(R.id.iv_ac_blow_auto);
        iv_ac_blow_auto.setVisibility( View.INVISIBLE );

        iv_ac_fan_mode = findViewById(R.id.iv_ac_fan_mode);
        iv_ac_fan_mode.setVisibility( View.INVISIBLE );

        iv_air_temp = findViewById(R.id.iv_air_temp);

        tv_air_cond_temp = findViewById(R.id.tv_air_cond_temp);

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
        floatingWindow.ibHideFloatingPanel.setOnClickListener(this);
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_DRAW_OVERLAY_PERMISSION:
                if (Settings.canDrawOverlays(getApplicationContext())) {
                    floatingWindow.show();
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
		tvGPSDistance.setText( "----.-" );
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
        updateOBD_FuelTank(App.obd.totalTrip.fuel_remains);


	}

	public void onStart() {
		super.onStart();
		updataData();
	}

	public void onPause() {
		super.onPause();
	}

	@SuppressLint("SetTextI18n")
    public void onResume() {
		super.onResume();

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

        layout_cvt_data.setVisibility( (App.obd.MMC_CAN && (App.obd.canMmcData.can_mmc_cvt_degr_show || App.obd.canMmcData.can_mmc_cvt_temp_show)) ? View.VISIBLE : View.GONE);
        layout_MMC_climate.setVisibility( (App.obd.MMC_CAN && App.obd.canMmcData.can_mmc_ac_data_show) ? View.VISIBLE : View.GONE);
        layout_temp_data.setVisibility( App.obd.engine_temp_show ? View.VISIBLE : View.GONE);
        ibFloatingPanel.setVisibility(!App.GS.isShowingFloatingPanel ? View.VISIBLE : View.INVISIBLE);

        // обновить данные OBD
        updateOBD_climate_data(App.obd.climateData);
	}

    @SuppressLint("NonConstantResourceId")
    @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.layout_waypoints:
                    App.GS.totalDistance = 0;
                    tvGPSDistance.setText( "----.-" );
                    Toast.makeText(this, "Счетчик был сброшен", Toast.LENGTH_SHORT).show ();
                    return true;
                case R.id.layout_tracktime:
	                switch(App.GS.gpsTimeAtWay_Type) {
		                case 0:
			                App.GS.gpsTimeAtWay = 0;
			                App.GS.gpsPrevTimeAtWay = 0;
			                break;
		                case 1:
			                App.GS.gpsTimeAtWayWithoutStops = 0;
			                App.GS.gpsPrevTimeAtWayWithoutStops = 0;
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
                color_speed(tvGPSSpeed, App.GS.gpsSpeed);
                App.GS.isColorSpeed = ! App.GS.isColorSpeed;
	            PreferenceManager.getDefaultSharedPreferences (App.getInstance ()).edit().putBoolean ("kaierutils_show_color_speed", App.GS.isColorSpeed).apply();
                break;
            case R.id.layout_tracktime:
                App.GS.gpsTimeAtWay_Type++;
                if ( App.GS.gpsTimeAtWay_Type > 1) App.GS.gpsTimeAtWay_Type = 0;
                if ( App.GS.gpsTimeAtWay_Type == 0) {
                    Toast.makeText(this, "Пройденное время с учетом простоя", Toast.LENGTH_SHORT).show();
                } else if (App.GS.gpsTimeAtWay_Type == 1) {
                    Toast.makeText(this, "Пройденное время без учета простоя", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
                prefs.edit().putInt("CAR_SETTINGS__GPS_TIME_AT_WAY_TYPE", App.GS.gpsTimeAtWay_Type);
                prefs.edit().apply();
                // сменить иконку, сменить текст
                showFormatedTrackTime( App.GS.gpsTimeAtWay_Type );
                break;
            case R.id.ivOBD2Status:
                if ( App.obd.isConnected ) {
                    BackgroundService.stopOBDThread();
                } else {
                    BackgroundService.startOBDThread();
                }
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
            case R.id.ibHideFloatingPanel:
                App.GS.isShowingFloatingPanel = false;
                App.GS.showFloatingPanelButton = true;
                floatingWindow.dismiss();
                ibFloatingPanel.setVisibility(View.VISIBLE);
                ibFloatingPanel.invalidate();
                break;
            case R.id.layout_battery:
                boolean isVisible = ibFloatingPanel.getVisibility() == View.VISIBLE;
                ibFloatingPanel.setVisibility(isVisible ? View.INVISIBLE : View.VISIBLE);

            default:
                break;
        }

    }


    private void showFloatingPanel() {
        if (Settings.canDrawOverlays(getApplicationContext())) {
            if (!floatingWindow.isShowing()) {
                floatingWindow.show();
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
                    App.GS.cntGpsHangs++;
                    break;

                case GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED: {
                    //double latitude = intent.getDoubleExtra("Latitude", 0);
                /*
                if ( latitude < 0 ) {
                    //S - south latitude
                    tvGPSLatitude.setText( String.format("S %1$.5f", latitude*(-1)).replace(",", ".") );
                } else {
                    //N - north latitude
                    tvGPSLatitude.setText( String.format("N %1$.5f", latitude).replace(",", ".") );
                }
                */

                    //double longitude = intent.getDoubleExtra("Longitude", 0);
                /*
                if ( longitude < 0 ) {
                    //W - west longitude
                    tvGPSLongitude.setText( String.format("W %1$.5f", longitude*(-1)).replace(",", ".") );
                } else {
                    //E - east longitude
                    tvGPSLongitude.setText( String.format("E %1$.5f", longitude).replace(",", ".") );
                }
                */
                    //tvGPSAccuracy.setText( String.format(getString(R.string.text_gps_accuracy), intent.getStringExtra("Accuracy")) );
                    //tvGPSAltitude.setText( String.format(getString(R.string.text_gps_altitude), intent.getStringExtra("Altitude")).replace(",", ".") );

                    int speed = intent.getIntExtra("Speed", 0);
                    if (speed > 80) {
                        ivSpeed.setImageResource(R.drawable.speedo_2);
                    } else {
                        ivSpeed.setImageResource(R.drawable.speedo_1);
                    }
                    tvGPSSpeed.setText(speed > 0 ? String.format(getString(R.string.text_gps_speed_value), speed) : "---");
                    color_speed(tvGPSSpeed, speed);

                    tvAverageSpeed.setText(String.format(getString(R.string.text_average_speed), App.GS.gpsAverageSpeed));
                    tvMaxSpeed.setText(String.format(getString(R.string.text_max_speed), App.GS.gpsMaxSpeed));

                    float dist = App.GS.totalDistance / 1000;

                    tvGPSDistance.setText(dist > 0 ? String.format(getString(R.string.text_gps_distance), dist).replace(",", ".") : "----.-");
                    showFormatedTrackTime(App.GS.gpsTimeAtWay_Type);

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
                    if (App.GS.interactWithPowerAmp && App.GS.isShowMusicInfo && App.GS.isPowerAmpPlaying) {
                        //String TrackTitle = intent.getStringExtra("TrackTitle");
                        //String AlbumArtist = intent.getStringExtra ("AlbumArtist");
                        Bitmap AlbumArt = intent.getParcelableExtra("AlbumArt");
                        //tvMusicInfo1.setText( TrackTitle );
                        tvMusicInfo1.setText(App.GS.PowerAmp_TrackTitle);
                        //tvMusicInfo2.setText( AlbumArtist );
                        tvMusicInfo2.setText(App.GS.PowerAmp_AlbumArtist);
                        //if (!AlbumArt.equals(App.GS.PowerAmp_AlbumArt))
                        //if ( AlbumArt != null ) {
                        ivAlbumArt.setImageBitmap(AlbumArt);
                        //} else {
                        //  ivAlbumArt.setImageResource( R.drawable.toast_music);
                        //}
                    }
                    break;
                case OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED:
                    boolean obd_status = intent.getBooleanExtra("Status", false);
                    updateOBDStatus(obd_status);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED:
                    updateOBD_CarBattery(App.obd.obdData.voltage);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED:
                    updateOBD_CoolantTemp(modeEngineTemp, App.obd.canMmcData.fan_state);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED:
                    updateOBD_FuelConsump(App.obd.oneTrip.fuel_cons_lph);
                    updateOBD_FuelTank(App.obd.totalTrip.fuel_remains);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED:

                    break;
                case OBDII.OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED:
                    updateOBD_CoolantTemp(modeEngineTemp, App.obd.canMmcData.fan_state);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_DEGR_CHANGED:
                    updateOBD_CVT_data(modeCVT);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_TEMP_CHANGED:
                    updateOBD_CVT_data(modeCVT);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_FAN_SPEED_CHANGED:
                    updateOBD_air_cond_fan_speed(ClimateData.FanSpeed.values()[intent.getIntExtra("air_cond_fan_speed_position", -1)]);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_FAN_MODE_CHANGED:
                    updateOBD_air_cond_fan_mode(ClimateData.FanMode.values()[intent.getIntExtra("air_cond_fan_mode", -1)]);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_TEMP_CHANGED:
                    updateOBD_air_cond_temperature(intent.getStringExtra("air_cond_set_temperature"));
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_EXT_TEMP_CHANGED:
                    updateOBD_air_cond_ext_temperature(intent.getIntExtra("air_cond_external_temp", -255));
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_BLOW_DIRECTION_CHANGED:
                    updateOBD_air_cond_blow_direction(ClimateData.BlowDirection.values()[intent.getIntExtra("air_cond_blow_direction_position", -1)]);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_BLOW_MODE_CHANGED:
                    updateOBD_air_cond_blow_mode(ClimateData.BlowMode.values()[intent.getIntExtra("air_cond_blow_mode", -1)]);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_DEFOGGER_CHANGED:
                    updateOBD_air_cond_defogger(ClimateData.State.values()[intent.getIntExtra("air_cond_defogger_state", -1)]);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_RECIRCULATION_CHANGED:
                    updateOBD_air_cond_recirculation(ClimateData.State.values()[intent.getIntExtra("air_cond_recirculation_state", -1)]);
                    break;
                case OBDII.OBD_BROADCAST_ACTION_AC_STATE_CHANGED:
                    updateOBD_air_cond_state(ClimateData.State.values()[intent.getIntExtra("air_cond_state", -1)]);
                    break;
            }

		}
	};



    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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
            it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.OBD2.OBDIIActivity");
            //it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        } catch (Exception e) {
        }
//        if ( App.GS.BT_deviceAddress != "unknown" ) {
//            App.OBD.connect();
//        }
    }

	private void color_speed(TextView tv, int speed) {
		if ( App.GS.isColorSpeed ) {
			if ( speed < 10 ) tv.setTextColor( Color.LTGRAY);
			else if ( speed < 40 ) tv.setTextColor( Color.rgb(0,255,255));
			else if ( speed < 60 ) tv.setTextColor( Color.rgb(0,255,144));
			else if ( speed < 80 ) tv.setTextColor( Color.rgb(182,255,0));
			else if ( speed < 100 ) tv.setTextColor( Color.rgb(255,216,0));
			else if ( speed < 120 ) tv.setTextColor( Color.rgb(155,106,0));
			else tv.setTextColor( Color.rgb(255,0,0));
		} else {
            tv.setTextColor(Color.LTGRAY);
        }
	}

    @SuppressLint("DefaultLocale")
    private void showFormatedTrackTime(int wayType) {
        long tm = 0;
        if ( wayType == 0)
        {
            tm = App.GS.gpsTimeAtWay;// - TimeZone.getDefault().getOffset(0L);
            // поменять значек
            ivTrackTime.setImageResource(R.drawable.track_time_0);
        } else if ( wayType == 1) {
            tm =  App.GS.gpsTimeAtWayWithoutStops;// - TimeZone.getDefault().getOffset(0L);
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

		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));


        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED));


        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_DEGR_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_OIL_TEMP_CHANGED));


        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED));


        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_EXT_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_FAN_SPEED_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_FAN_MODE_CHANGED));

		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_BLOW_MODE_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_BLOW_DIRECTION_CHANGED));

		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_DEFOGGER_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_RECIRCULATION_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_STATE_CHANGED));

		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_FUEL_TANK_CHANGED));
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
                if ( App.GS.interactWithPowerAmp && App.GS.isShowMusicInfo ) {
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
    public void updateOBD_CarBattery(double voltage) {
        if (App.obd.battery_show) {
            layout_battery.setVisibility(View.VISIBLE);
            if (voltage < 12) {
                ivOBD_CarBattery.setImageResource(R.drawable.car_battery_bad);
            } else {
                ivOBD_CarBattery.setImageResource(R.drawable.car_battery_good);
            }
           TextViewToSpans(tvOBD_CarBattery, String.format("%1$.1f", voltage), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
        } else {
            layout_battery.setVisibility(View.GONE);
        }
    }

    @SuppressLint("DefaultLocale")
    public void updateOBD_CoolantTemp(int mode, CanMmcData.State state){
        if ( App.obd.engine_temp_show ) {
            layout_temp_data.setVisibility( View.VISIBLE );

            float temp = App.obd.obdData.coolant;
            switch (mode) {
                case 0:
                    ivOBD_CoolantTempFan.setVisibility(((state == CanMmcData.State.on) &&
                            App.obd.MMC_CAN && App.obd.canMmcData.engine_fan_show) ? View.VISIBLE : View.INVISIBLE);
                    if (temp < 80) {
                        ivOBD_CoolantTemp.setImageResource(R.drawable.coolant_temp_min);
                    } else if (temp < 99) {
                        ivOBD_CoolantTemp.setImageResource(R.drawable.coolant_temp_norm);
                    } else {
                        ivOBD_CoolantTemp.setImageResource(R.drawable.coolant_temp_hot);
                    }
                    tvOBD_CoolantTemp.setText(String.format("%1$.0f", temp));
                    break;
                default:
                    ivOBD_CoolantTemp.setImageResource(R.drawable.coolant_temp_norm);
                    tvOBD_CoolantTemp.setText(String.format("%1$.0f", temp));
                    break;
            }
        } else {
            layout_temp_data.setVisibility( View.GONE );
        }
    }

    @SuppressLint("SetTextI18n")
    public void updateOBD_CVT_data(int mode) {
        if ( App.obd.MMC_CAN && (App.obd.canMmcData.can_mmc_cvt_degr_show || App.obd.canMmcData.can_mmc_cvt_temp_show)) {
            layout_cvt_data.setVisibility( View.VISIBLE);
        } else {
            layout_cvt_data.setVisibility( View.GONE);
            return;
        }

        int temp = -255;
        switch (mode) {
            case 0:
                if ( App.obd.MMC_CAN && App.obd.canMmcData.can_mmc_cvt_temp_show) {

                    temp = App.obd.canMmcData.can_mmc_cvt_temp;
                    if (temp > -255) tvOBD_CVT_Data.setText(Integer.toString(temp));
                    else tvOBD_CVT_Data.setText("--");
                    if (temp < 71) {
                        ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_min);
                    } else if (temp < 91) {
                        ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_nom_green);
                    } else if (temp < 103) {
                        ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_nom_yellow);
                    } else {
                        ivOBD_CVT_Data.setImageResource(R.drawable.cvt_temp_nom_orange);
                    }
                }
                break;
            case 1:
                if ( App.obd.MMC_CAN && App.obd.canMmcData.can_mmc_cvt_degr_show) {
                    temp = App.obd.canMmcData.can_mmc_cvt_degradation_level;
                    ivOBD_CVT_Data.setImageResource(R.drawable.cvt_degr_nom);
                    tvOBD_CVT_Data.setText(Integer.toString(temp));
                }
                break;
            default:
                ivOBD_CVT_Data.setImageResource( R.drawable.cvt_temp_nom);
                tvOBD_CVT_Data.setText( "--");
                break;
        }
    }

    public void updateOBD_FuelTank(float remain){
//        if ( remain < 20 ) {
//            ivOBD_FuelTank.setImageResource( R.drawable.fuel_tank_min);
//        } else {
//            ivOBD_FuelTank.setImageResource( R.drawable.fuel_tank_full);
//        }
        //tvOBD_FuelTank.setText( String.format("%1$.1f", tank));
        //tvOBD_FuelTank.setText( String.format("%1$.1f", remain));
        show_fuel_tank_data(modeFuelTank);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putInt("kaierutils_modeFuelTank", modeFuelTank).apply();

    }

    public void updateOBD_FuelConsump(float consump){
//        if ( consump < 9 ) {
//            ivOBD_FuelConsump.setImageResource( R.drawable.fuel_consump_min);
//        } else {
//            ivOBD_FuelConsump.setImageResource( R.drawable.fuel_consump_max);
//        }
        //tvOBD_FuelConsump.setText( String.format("%1$.1f", consump));
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

        alertDialogBuilder.setPositiveButton("Отмена", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // Обработчик на нажатие НЕТ
        alertDialogBuilder.setNegativeButton("Да", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                App.obd.setFullTank();

            }
        });

        // Обработчик на нажатие ОТМЕНА
        alertDialogBuilder.setNeutralButton("Нет", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // show dialog to enter fuel details
                show_obd_fuel_detail();
            }
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

        final EditText etFuelTankCapacity = (EditText)linearlayout.findViewById(R.id.etFuelTankCapacity);
        final EditText etFuelTankRemain = (EditText)linearlayout.findViewById(R.id.etFuelTankRemain);
        final SeekBar seekBarFuel = (SeekBar) linearlayout.findViewById(R.id.seekBarFuel);
        final TextView tvFuelPercent = (TextView) linearlayout.findViewById(R.id.tvFuelPercent);

        etFuelTankCapacity.setText( String.format("%1$.0f", App.obd.obdData.fuel_tank));
        etFuelTankRemain.setText( String.format("%1$.0f", App.obd.totalTrip.fuel_remains));
        seekBarFuel.setMax( 100 );
        int percent = Math.round(App.obd.totalTrip.fuel_remains * 100 / App.obd.obdData.fuel_tank);
        seekBarFuel.setProgress(percent);
        tvFuelPercent.setText(String.valueOf(percent) + " %");
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
                tvFuelPercent.setText(String.valueOf(progress) + " %");
            }
        });
        fuelDialog.setPositiveButton("Сохранить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        App.obd.setCustomTank(Float.parseFloat(etFuelTankCapacity.getText().toString()),
                                Float.parseFloat(etFuelTankRemain.getText().toString()));
                        updateOBD_FuelTank(App.obd.totalTrip.fuel_remains);
                    }
                });

        fuelDialog.setNegativeButton("Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
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

        updateOBD_CoolantTemp(modeEngineTemp, App.obd.canMmcData.fan_state);

    }

    // переключение режима показаний данных cvt
    private void switch_cvt_mode() {
        // режим отображения уровня топлива
        // 0 - коробка
        // 1 - деградация
        modeCVT++;
        if ( modeCVT > 1) modeCVT = 0;

        updateOBD_CVT_data(modeCVT);

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
                //tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_inst) );
                TextViewToSpans(tvOBD_FuelConsump, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_inst), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                break;
            case 1:
                //tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg) );
                TextViewToSpans(tvOBD_FuelConsump, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                break;
            case 2:
                //tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lph) );
                TextViewToSpans(tvOBD_FuelConsump, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lph), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                break;
            case 3:
                //tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg) );
                TextViewToSpans(tvOBD_FuelConsump, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg), TEXT_SIZE_BEFORE_DOT_2, TEXT_SIZE_AFTER_DOT);

                //tvOBD_FuelConsump2.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_inst) );
                TextViewToSpans(tvOBD_FuelConsump2, String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_inst), TEXT_SIZE_BEFORE_DOT_3, TEXT_SIZE_AFTER_DOT);
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
                    if (App.obd.MMC_CAN && App.obd.canMmcData.can_mmc_fuel_remain_show) {
                        // по кан
                        if ( App.obd.canMmcData.can_mmc_fuel_remain > 0 )
                            tvOBD_FuelTank.setText(String.format("%1$s", Integer.toString(App.obd.canMmcData.can_mmc_fuel_remain)));
                            else tvOBD_FuelTank.setText(String.format("%1$s", "--"));
                    } else {
                        // вычисляем
//                        String s = String.format("%1$.1f", App.obd.totalTrip.fuel_remains).replace(",", ".");
//                        SpannableString ss =  new SpannableString(s);
//                        int dot = s.indexOf(".");
//                        ss.setSpan(new AbsoluteSizeSpan( 32 ), 0, dot, 0);
//                        ss.setSpan(new AbsoluteSizeSpan( 16 ), dot+1, s.length()-1, 0);
//
//
//                        tvOBD_FuelTank.setText(ss);
                        TextViewToSpans(tvOBD_FuelTank, String.format("%1$.1f", App.obd.totalTrip.fuel_remains), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
                    }

                    break;
                case 1:

                    if (App.obd.MMC_CAN && App.obd.canMmcData.can_mmc_fuel_remain_show) {
                        // по кан
                        if ( App.obd.canMmcData.can_mmc_fuel_remain < 0 )
                        tvOBD_FuelTank.setText(String.format("%1$s", "--"));
                        else tvOBD_FuelTank.setText(String.format("%1$s", Integer.toString(Math.round(App.obd.canMmcData.can_mmc_fuel_remain / (App.obd.obdData.fuel_tank / 100))))  + "\\%");
                    } else {
                        // вычисляем
                        tvOBD_FuelTank.setText(String.format("%1$.0f", (float) ((App.obd.totalTrip.fuel_remains * 100) / App.obd.obdData.fuel_tank)) + "\\%");
                    }


                    break;
                case 2:

//                    String s = String.format("%1$.2f", App.obd.oneTrip.fuel_usage).replace(",", ".");
//                    SpannableString ss =  new SpannableString(s);
//                    int dot = s.indexOf(".");
//                    ss.setSpan(new AbsoluteSizeSpan( 32 ), 0, dot, 0);
//                    ss.setSpan(new AbsoluteSizeSpan( 16 ), dot+1, s.length()-1, 0);
//
//
//                    tvOBD_FuelTank.setText(ss);

                    TextViewToSpans(tvOBD_FuelTank, String.format("%1$.2f", App.obd.oneTrip.fuel_usage), TEXT_SIZE_BEFORE_DOT, TEXT_SIZE_AFTER_DOT);
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
                // show line 2
                ////tvOBD_FuelConsump.setTextSize(26);
                //tvOBD_FuelConsump2.setTextSize(22);
                tvOBD_FuelConsump2.setVisibility(View.VISIBLE);
            } else {
                // hide line 2
                //tvOBD_FuelConsump.setTextSize(40);
                tvOBD_FuelConsump2.setVisibility(View.GONE);
            }
        }
    }

    private void updateOBD_air_cond_fan_speed(ClimateData.FanSpeed fanSpeed) {
        iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_0);

        //switch ( App.obd.climateData.fan_speed ) {
        switch ( fanSpeed ) {
            case off:       iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_0); break;
            case speed1:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_1); break;
            case speed2:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_2); break;
            case speed3:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_3); break;
            case speed4:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_4); break;
            case speed5:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_5); break;
            case speed6:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_6); break;
            case speed7:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_7); break;
            case speed8:    iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_8); break;
            default:  break;
        }

    }

    private void updateOBD_air_cond_fan_mode( ClimateData.FanMode fanMode) {
//        iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_0);
//
//        switch ( App.obd.climateData.fan_speed ) {
//            case 0x0: break;
//            case 0x1: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_0); break;
//            case 0x2: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_1); break;
//            case 0x3: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_2); break;
//            case 0x4: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_3); break;
//            case 0x5: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_4); break;
//            case 0x6: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_5); break;
//            case 0x7: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_6); break;
//            case 0x8: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_7); break;
//            case 0x9: iv_air_fan_speed.setImageResource( R.drawable.air_wind_seat_my_fan_8); break;
//            default:  break;
//        }
        iv_ac_fan_mode.setVisibility((fanMode == ClimateData.FanMode.auto) ? View.VISIBLE : View.INVISIBLE);
    }

    private void updateOBD_air_cond_temperature(String temp){
//        //tv_air_cond_temp.setText( String.format("%1$.1f", App.obd.climateData.temperature ));
//        String s = temp.replace(",", ".");
//        SpannableString ss =  new SpannableString(s);
//        int dot = s.indexOf(".");
//        ss.setSpan(new AbsoluteSizeSpan( 40 ), 0, dot, 0);
//        ss.setSpan(new AbsoluteSizeSpan( 16 ), dot+1, s.length()-1, 0);
//
//
//        //tv_air_cond_temp.setText( temp );
//        tv_air_cond_temp.setText( ss );

        TextViewToSpans(tv_air_cond_temp, temp, TEXT_SIZE_BEFORE_DOT_4, TEXT_SIZE_AFTER_DOT);

        if ( App.obd.climateData.temperature < 19f )
            iv_air_temp.setImageResource(R.drawable.ac_temp_blue);
        else if ( App.obd.climateData.temperature < 21f )
            iv_air_temp.setImageResource(R.drawable.ac_temp_green);
        else if ( App.obd.climateData.temperature < 23f )
            iv_air_temp.setImageResource(R.drawable.ac_temp_orange);
        else
            iv_air_temp.setImageResource(R.drawable.ac_temp_red);

    }

    private void updateOBD_air_cond_blow_direction(ClimateData.BlowDirection blowDirection){

        switch ( blowDirection ) {
            case face:  iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_face); break;
            case from_face_to_feet_and_face: iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_beetwen_feet_and_face_and_feet); break;
            case feet_and_face: iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_face_and_feet); break;
            case from_feet_and_face_to_feet: iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_beetwen_feet_and_feet_and_face); break;
            case feet: iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_feet); break;
            case from_feet_to_feet_and_window: iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_beetwen_feet_and_feet_and_window); break;
            case feet_and_window: iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_feet_and_window); break;
            case from_feet_and_window_to_window: iv_air_direction.setImageResource(R.drawable.air_wind_seat_my_to_between_window_and_feet_and_window); break;
            case window: iv_air_direction.setImageResource( R.drawable.air_wind_seat_my_to_window); break;
            default:  break;
        }

    }

    private void updateOBD_air_cond_defogger( ClimateData.State state){
        //iv_air_defogger.setVisibility( (App.obd.climateData.defogger_state == ClimateData.State.on)  ? View.VISIBLE : View.INVISIBLE );
        iv_air_defogger.setVisibility( (state == ClimateData.State.on)  ? View.VISIBLE : View.INVISIBLE );
    }

    private void updateOBD_air_cond_recirculation( ClimateData.State state){
        //iv_air_recirculation.setVisibility( (App.obd.climateData.recirculation_state == ClimateData.State.on)  ? View.VISIBLE : View.INVISIBLE );
        iv_air_recirculation.setVisibility((state == ClimateData.State.on) ? View.VISIBLE : View.INVISIBLE);
    }

    private void updateOBD_air_cond_state( ClimateData.State state) {

        //iv_air_ac_state.setVisibility( (App.obd.climateData.ac_state == ClimateData.State.on) ? View.VISIBLE : View.INVISIBLE );
        iv_air_ac_state.setImageResource((state == ClimateData.State.on) ? R.drawable.air_cond__state_on : R.drawable.air_cond__state_off);

    }

    private void updateOBD_air_cond_ext_temperature (int temp) {
        // TODO
    }

    private void updateOBD_air_cond_blow_mode( ClimateData.BlowMode blowMode){
        iv_ac_blow_auto.setVisibility((blowMode == ClimateData.BlowMode.auto) ? View.VISIBLE : View.INVISIBLE);
    }

    @SuppressLint("DefaultLocale")
    private void updateOBD_climate_data(ClimateData climateData) {
        updateOBD_air_cond_state(climateData.ac_state);
        updateOBD_air_cond_blow_mode(climateData.blow_mode);
        updateOBD_air_cond_recirculation(climateData.recirculation_state);
        updateOBD_air_cond_defogger(climateData.defogger_state);
        updateOBD_air_cond_blow_direction(climateData.blow_direction);
        if (( climateData.temperature < 15 ) || ( climateData.temperature > 27 ) )
            updateOBD_air_cond_temperature( "--.-" ) ;
        else updateOBD_air_cond_temperature(String.format("%1$.1f", climateData.temperature) ) ;
        updateOBD_air_cond_fan_mode( climateData.fan_mode);
        updateOBD_air_cond_fan_speed(climateData.fan_speed);

    }

    private void TextViewToSpans(TextView tv, String value, int size1, int size2) {
        String s = value.replace(",", ".");
        SpannableString ss =  new SpannableString(s);
        int dot = s.indexOf(".");
        if ( dot > -1 ) {
            ss.setSpan(new AbsoluteSizeSpan(size1), 0, dot, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ss.setSpan(new AbsoluteSizeSpan(size2), dot, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(ss);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ibFloatingPanel.setVisibility(!App.GS.isShowingFloatingPanel ? View.VISIBLE : View.INVISIBLE);
    }

}


