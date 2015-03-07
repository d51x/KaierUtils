package ru.d51x.kaierutils;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuInflater;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.provider.Settings;
import android.view.View.OnLongClickListener;

import android.view.Window;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;


import 	android.widget.ArrayAdapter;

import android.content.DialogInterface;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.Calendar;

import pt.lighthouselabs.obd.commands.protocol.*;
import pt.lighthouselabs.obd.commands.engine.*;
import pt.lighthouselabs.obd.commands.SpeedObdCommand;
import pt.lighthouselabs.obd.enums.ObdProtocols;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener,
                                                      View.OnClickListener, OnLongClickListener  {

    private TextView tvDeviceName;
    private TextView tvCurrentVolume;
    private Switch switch_show_notification_icon;
    private LinearLayout layout_gps_speed;
    private LinearLayout layout_waypoints;
    private LinearLayout layout_tracktime;

    private TextView tvReverseCount;
    private TextView tvSleepModeCount;
    private TextView tvSleepModeLastTime;
    private TextView tvWorkingStart;

	private TextView tvGPSDistance;
	private ImageView ivGPSDistance;
    private ImageView ivTrackTime;
    private TextView tvGPSSatellitesTotal;
    private TextView tvGPSSatellitesGoodQACount;
    private TextView tvGPSSatellitesInUse;

    private TextView tvGPSAccuracy;
    private TextView tvGPSAltitude;
    private TextView tvGPSLatitude;
    private TextView tvGPSLongitude;
    private TextView tvGPSSpeed;
    private TextView tvTrackTime;
    private TextView tvTrackTime2;
    private TextView tvTrackTimeMinOrSec;
    private TextView tvTrackTimeHourOrMin;

    private TextView tvAverageSpeed;
    private TextView tvMaxSpeed;

	private TextView tv_eq_bass;
	private TextView tv_eq_mid;
	private TextView tv_eq_tre;

    private Button btnAGPSReset;

    private ImageView ivVolumeLevel;
    private TextView tvGPSHangs;
    private ImageView ivSpeed;
    private ImageView ivSpeedChange;
    //private ImageView ivSpeedChange2;
    private TextView tvOBD_RPM;
    private TextView tvOBD_Speed;

	private Button btnSpeedUp, btnSpeedDown;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);


        // Убираем заголовок
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Убираем панель уведомлений
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView (R.layout.main_activity);
		Log.d ("MainActivity", "onCreate");
		startService(new Intent(this, BackgroundService.class));
		Toast.makeText (this, "Service started", 0).show();

        tvDeviceName = (TextView) findViewById(R.id.txtDeviceName);
        String string_device_name = String.format(getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

        tvCurrentVolume = (TextView) findViewById(R.id.tvCurrentVolum);
        tvCurrentVolume.setText(Integer.toString(App.mGlSets.getVolumeLevel()) );

        switch_show_notification_icon = (Switch) findViewById(R.id.switch_show_notification_icon);
        App.mGlSets.isNotificationIconShow = Settings.System.getInt(getContentResolver(), NotifyData.OPTION_SHOW_NOTIFICATION_ICON, 0) == 1;
        switch_show_notification_icon.setChecked(App.mGlSets.isNotificationIconShow);
        switch_show_notification_icon.setOnCheckedChangeListener(this);

        App.mGlSets.isColorSpeed = Settings.System.getInt(getContentResolver(), App.mGlSets.GLOBAL_SETTINGS_COLOR_SPEED, 0) == 1;
        layout_gps_speed = (LinearLayout) findViewById(R.id.layout_gps_speed);
        layout_gps_speed.setOnClickListener(this);

        layout_tracktime = (LinearLayout) findViewById(R.id.layout_tracktime);
        layout_tracktime.setOnLongClickListener(this);
        layout_tracktime.setOnClickListener(this);

        tvReverseCount = (TextView) findViewById(R.id.tv_reverse_count);
        tvReverseCount.setText( String.format(getString(R.string.text_reverse_count), App.mGlSets.ReverseActivityCount) );

        tvSleepModeCount = (TextView) findViewById(R.id.tv_sleep_mode_count);
        tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count), App.mGlSets.SleepModeCount) );

        tvSleepModeLastTime = (TextView) findViewById(R.id.tv_sleep_mode_last_time);
        if ( App.mGlSets.lastSleep == 0) {
            tvSleepModeLastTime.setVisibility( View.INVISIBLE );
        } else {
            Date date = new Date( App.mGlSets.lastSleep );
            SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy  hh:mm");
            tvSleepModeLastTime.setText( String.format(getString(R.string.text_sleep_mode_last_time), ft.format(date)) );
            tvSleepModeLastTime.setVisibility( View.VISIBLE );
        }

        tvWorkingStart = (TextView) findViewById(R.id.tv_working_start);
        Date date = new Date( App.mGlSets.startDate );
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
        tvWorkingStart.setText( String.format(getString(R.string.text_working_start), ft.format(date)) );

        tvGPSSatellitesTotal = (TextView) findViewById(R.id.text_satellites_total);
        tvGPSSatellitesGoodQACount = (TextView) findViewById(R.id.text_satellites_good);
        tvGPSSatellitesInUse = (TextView) findViewById(R.id.text_satellites_inuse);
        tvGPSSatellitesTotal.setText( "--");
        tvGPSSatellitesInUse.setText( "--");
        tvGPSSatellitesGoodQACount.setText( "--" );

        tvGPSAccuracy = (TextView) findViewById(R.id.text_gps_accuracy);
        tvGPSAccuracy.setText( "---" );
        tvGPSAltitude = (TextView) findViewById(R.id.text_gps_altitude);
        tvGPSAltitude.setText( "---" );
        tvGPSLatitude = (TextView) findViewById(R.id.text_gps_latitude);
        tvGPSLatitude.setText( "--.-----" );
        tvGPSLongitude = (TextView) findViewById(R.id.text_gps_longitude);
        tvGPSLongitude.setText( "--.-----" );
        tvGPSSpeed = (TextView) findViewById(R.id.text_gps_speed_value);
        tvGPSSpeed.setText( "---" );
        //tvGPSSpeed.setOnClickListener(this);

        tvTrackTime = (TextView) findViewById(R.id.tvTrackTime);
        tvTrackTime2 = (TextView) findViewById(R.id.tvTrackTime2);
        tvTrackTime.setText( getString(R.string.text_gps_track_time_null));
        tvTrackTime2.setText( getString(R.string.text_gps_track_time_null));

        tvTrackTimeMinOrSec = (TextView) findViewById(R.id.tvTrackTimeMinOrSec);
        tvTrackTimeMinOrSec.setText(getString(R.string.text_gps_track_time_format_sec));
        tvTrackTimeHourOrMin = (TextView) findViewById(R.id.tvTrackTimeHourOrMin);
        tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));

		tvGPSDistance = (TextView) findViewById(R.id.tvGPSDistance);
		tvGPSDistance.setText( "----.-" );
        layout_waypoints = (LinearLayout) findViewById(R.id.layout_waypoints);
        layout_waypoints.setOnLongClickListener(this);

        ivTrackTime = (ImageView) findViewById(R.id.ivTrackTime);

        ivVolumeLevel = (ImageView) findViewById(R.id.ivVolumeLevel);
        setVolumeIcon(ivVolumeLevel, App.mGlSets.getVolumeLevel());

        ivSpeed = (ImageView) findViewById(R.id.ivSpeed);
        ivSpeedChange = (ImageView) findViewById(R.id.ivSpeedChange);
        //ivSpeedChange2 = (ImageView) findViewById(R.id.ivSpeedChange2);
        ivSpeedChange.setVisibility(View.INVISIBLE);
        //ivSpeedChange2.setVisibility(View.INVISIBLE);

        tvGPSHangs = (TextView) findViewById(R.id.tvGPSHangs);
		tvGPSHangs.setVisibility (View.INVISIBLE);
        tvGPSHangs.setText(String.format(getString(R.string.text_gps_hangs), 0));

        tvOBD_RPM = (TextView) findViewById(R.id.text_obd_rpm);
        tvOBD_Speed = (TextView) findViewById(R.id.text_obd_speed);

		btnSpeedUp = (Button) findViewById (R.id.btnSpeedUp);
        btnSpeedUp.setOnClickListener(this);

		btnSpeedDown = (Button) findViewById (R.id.btnSpeedDown);
        btnSpeedDown.setOnClickListener(this);

        btnAGPSReset = (Button) findViewById(R.id.btn_agps_reset);
        btnAGPSReset.setOnClickListener(this);

        tvAverageSpeed = (TextView) findViewById(R.id.tvAverageSpeed);
        tvAverageSpeed.setText( String.format( getString(R.string.text_average_speed), "---"));
        tvMaxSpeed = (TextView) findViewById(R.id.tvMaxSpeed);
        tvMaxSpeed.setText( String.format( getString(R.string.text_max_speed), "---"));

		tv_eq_bass = (TextView) findViewById (R.id.tv_eq_bass);
		tv_eq_mid = (TextView) findViewById (R.id.tv_eq_mid);
		tv_eq_tre = (TextView) findViewById (R.id.tv_eq_tre);

        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_RADIO_CHANGED));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_EQ_CHANGED));


        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_FIRST_FIX));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET));
	}

    @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.layout_waypoints:
                    App.mGlSets.totalDistance = 0;
                    tvGPSDistance.setText( "----.-" );
                    Toast.makeText(this, "Счетчик был сброшен", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.layout_tracktime:
	                switch(App.mGlSets.gpsTimeAtWay_Type) {
		                case 0:
			                App.mGlSets.gpsTimeAtWay = 0;
			                App.mGlSets.gpsPrevTimeAtWay = 0;
			                break;
		                case 1:
			                App.mGlSets.gpsTimeAtWayWithoutStops = 0;
			                App.mGlSets.gpsPrevTimeAtWayWithoutStops = 0;
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
                default:
                    return false;
            }

        }


    // анализируем, какая кнопка была нажата. Всего один метод для всех кнопок
    @Override
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.btnSpeedUp:
                //App.mGlSets.isFirstStart = true;
	            //App.mGlSets.prevTime = 3526000;
	            //App.mGlSets.gpsTimeAtWay = 3526000;
	            //TWUTIL_BROADCAST_ACTION_RADIO_CHANGED
	            Intent ri = new Intent();
	            ri.putExtra ("Frequency", "87.50");
	            ri.putExtra ("Title", "RELAX-FM");
	            ri.setAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_RADIO_CHANGED );
	            sendBroadcast(ri);
	            break;
            case R.id.btnSpeedDown:
                //App.mGlSets.isDebug = !App.mGlSets.isDebug;
	            //App.mGlSets.prevTime = 3526000;
	            //App.mGlSets.gpsTimeAtWay = 3526000;
	            Intent ri2 = new Intent();
	            ri2.putExtra ("Frequency", "101.20");
	            ri2.putExtra ("Title", (String) null);
	            ri2.setAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_RADIO_CHANGED );
	            sendBroadcast(ri2);
                break;
            case R.id.btn_agps_reset:
                Intent intent = new Intent();
                intent.setAction( GlSets.GPS_BROADCAST_ACTION_AGPS_RESET );
                sendBroadcast(intent);
                break;
            case R.id.layout_gps_speed:
                color_speed(tvGPSSpeed, App.mGlSets.gpsSpeed);
                App.mGlSets.isColorSpeed = ! App.mGlSets.isColorSpeed;
                Settings.System.putInt(this.getContentResolver(), App.mGlSets.GLOBAL_SETTINGS_COLOR_SPEED, App.mGlSets.isColorSpeed ? 1 : 0);
                break;
            case R.id.layout_tracktime:
                App.mGlSets.gpsTimeAtWay_Type++;
                if ( App.mGlSets.gpsTimeAtWay_Type > 1) App.mGlSets.gpsTimeAtWay_Type = 0;
                if ( App.mGlSets.gpsTimeAtWay_Type == 0) {
                    Toast.makeText(this, "Пройденное время с учетом простоя", Toast.LENGTH_SHORT).show();
                } else if (App.mGlSets.gpsTimeAtWay_Type == 1) {
                    Toast.makeText(this, "Пройденное время без учета простоя", Toast.LENGTH_SHORT).show();
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
                prefs.edit().putInt("CAR_SETTINGS__GPS_TIME_AT_WAY_TYPE", App.mGlSets.gpsTimeAtWay_Type);
                prefs.edit().commit();
                // сменить иконку, сменить текст
                showFormatedTrackTime( App.mGlSets.gpsTimeAtWay_Type );
                break;
            default:
                break;
        }
    }






    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        NotifyData notifyData = new NotifyData( getApplicationContext() );
        switch (id){
            case R.id.switch_show_notification_icon:
                Settings.System.putInt(this.getContentResolver(), NotifyData.OPTION_SHOW_NOTIFICATION_ICON, isChecked ? 1 : 0);
                App.mGlSets.isNotificationIconShow = isChecked;
                notifyData.smallIcon = (App.mGlSets.isNotificationIconShow) ? R.drawable.notify_auto : 0;
                notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                notifyData.show();
                break;
            default:
                break;
        }



    }

	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED ))
			{
				int vol = intent.getIntExtra (TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED, 0);
				tvCurrentVolume.setText (String.format( Integer.toString(vol)));
                setVolumeIcon(ivVolumeLevel, vol);
			}
            else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH ) )
            {
                tvReverseCount.setText( String.format(getString(R.string.text_reverse_count),
                                                      App.mGlSets.ReverseActivityCount) );
            }
            else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP ) )
            {
//                tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
//                        App.mGlSets.SleepModeCount) );

                if ( App.mGlSets.lastSleep == 0)
                {
                    tvSleepModeLastTime.setVisibility( View.INVISIBLE );
                }
                else
                {
                    Date date = new Date( App.mGlSets.lastSleep );
                    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
                    tvSleepModeLastTime.setText( String.format("%s", ft.format(date)) );
                    tvSleepModeLastTime.setVisibility( View.VISIBLE );
                }
            }
            else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP ) )
            {
	            tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
			            App.mGlSets.SleepModeCount) );

            }
			else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_EQ_CHANGED ) )
            {
	            byte[] bArr = intent.getByteArrayExtra ("EQ");

	            //bass bArr[1]
	            //mid  bArr[2]
	            //tree bArr[3]
	            //loud bArr[5]
	            /*
					0	1	2	3	4	5	6	7	8	9	10	11	12	13	14
					-7	-6	-5	-4	-3	-2	-1	0	1	2	3	4	5	6	7
	             */
	            tv_eq_bass.setText( Integer.toString ( bArr[1] - 7 ));
	            tv_eq_mid.setText( Integer.toString (bArr[2] - 7));
	            tv_eq_tre.setText( Integer.toString (bArr[3] - 7));

            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS))
            {
                int cntSats = intent.getIntExtra ("SatellitesTotal", 0);
                int goodSatellitesCount = intent.getIntExtra( "SatellitesGoodQATotal", 0 );
                int satellitesInUse = intent.getIntExtra( "SatellitesInUse", 0 );
                tvGPSSatellitesTotal.setText( Integer.toString(cntSats) );
                tvGPSSatellitesGoodQACount.setText( Integer.toString(goodSatellitesCount) );
                tvGPSSatellitesInUse.setText( Integer.toString(satellitesInUse) );

                if ( goodSatellitesCount  <  2 ) {
                    tvGPSSatellitesGoodQACount.setTextColor(Color.RED);
                } else if ( goodSatellitesCount < GpsProcessing.signal_quality ) {
                    tvGPSSatellitesGoodQACount.setTextColor(Color.YELLOW);
                } else {
                    tvGPSSatellitesGoodQACount.setTextColor(Color.GREEN);
                }

            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET)) {
				App.mGlSets.cntGpsHangs++;
				tvGPSHangs.setVisibility (View.VISIBLE);
                tvGPSHangs.setText(String.format(getString(R.string.text_gps_hangs), App.mGlSets.cntGpsHangs));
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_SPEED_CHANGED)) {
                int speed = intent.getIntExtra("Speed", 0);
                int grow = intent.getIntExtra("SpeedGrow", 0);
                processDynamicVoume (speed, grow);
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED)) {
                double latitude = intent.getDoubleExtra("Latitude", 0);
                if ( latitude < 0 ) {
                    //S - south latitude
                    tvGPSLatitude.setText( String.format("S %1$.5f", latitude*(-1)).replace(",", ".") );
                } else {
                    //N - north latitude
                    tvGPSLatitude.setText( String.format("N %1$.5f", latitude).replace(",", ".") );
                }

                double longitude = intent.getDoubleExtra("Longitude", 0);
                if ( longitude < 0 ) {
                    //W - west longitude
                    tvGPSLongitude.setText( String.format("W %1$.5f", longitude*(-1)).replace(",", ".") );
                } else {
                    //E - east longitude
                    tvGPSLongitude.setText( String.format("E %1$.5f", longitude).replace(",", ".") );
                }
                tvGPSAccuracy.setText( String.format(getString(R.string.text_gps_accuracy), intent.getStringExtra("Accuracy")) );
                tvGPSAltitude.setText( String.format(getString(R.string.text_gps_altitude), intent.getStringExtra("Altitude")).replace(",", ".") );

				int speed = intent.getIntExtra("Speed", 0);
                if ( speed > 80) {
                    ivSpeed.setImageResource(R.drawable.speedo_2);
                } else {
                    ivSpeed.setImageResource(R.drawable.speedo_1);
                }
				tvGPSSpeed.setText( speed > 0 ? String.format(getString(R.string.text_gps_speed_value), speed) : "---" );

				color_speed(tvGPSSpeed, speed);

                tvAverageSpeed.setText( String.format( getString(R.string.text_average_speed), Integer.toString( App.mGlSets.gpsAverageSpeed)));
                tvMaxSpeed.setText( String.format( getString(R.string.text_max_speed), Integer.toString( App.mGlSets.gpsMaxSpeed)));

                if ( !App.mGlSets.dsc_isAvailable ) {
                    ivSpeedChange.setVisibility(View.INVISIBLE);
                }
                float dist = App.mGlSets.totalDistance / 1000;

                tvGPSDistance.setText (  dist > 0 ? String.format(getString(R.string.text_gps_distance), dist).replace(",", ".") : "----.-" );
                showFormatedTrackTime( App.mGlSets.gpsTimeAtWay_Type );
            }
			else if ( action.equals( TWUtilConst.TWUTIL_BROADCAST_ACTION_RADIO_CHANGED)) {
				String title = intent.getStringExtra("Title");
				String freq = intent.getStringExtra ("Frequency");

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Операции для выбранного пункта меню
        switch (item.getItemId())
        {
            case R.id.menu_sound_settings:
                show_sound_settings();
                return true;
            case R.id.menu_poweramp_control:
                show_poweramp_control();
                return true;
	        case R.id.menu_dynamic_sound_control:
		        show_dynamic_sound_settings();
		        return true;
	        case R.id.menu_odb2_settings:
		        show_avalaible_BT_devices( MainActivity.this );
		        return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void show_sound_settings() {
        try {
            Intent it = new Intent();
            it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.SoundSettingsPreferenceActivity");
            //it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        } catch (Exception e) {
        }
    }

    private void show_poweramp_control() {
        try {
            Intent it = new Intent();
            it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.PowerAmpPreferenceActivity");
            //it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        } catch (Exception e) {
        }
    }

    private void show_dynamic_sound_settings() {
        try {
            Intent it = new Intent();
            it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.DynamicSoundSettingsActivity");
            //it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(it);
        } catch (Exception e) {
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

    private void  processDynamicVoume(int speed, int grow) {
        if ( !App.mGlSets.dsc_isAvailable) return;
        int  t = Math.abs(speed - App.mGlSets.dsc_FirstSpeed) / App.mGlSets.dsc_StepSpeed;
        switch ( grow ){
            case -1:
                ivSpeedChange.setImageResource(R.drawable.speed_down);
                ivSpeedChange.setVisibility(View.VISIBLE);


                //if ( Math.abs( speed - (App.mGlSets.dsc_FirstSpeed + t*App.mGlSets.dsc_StepSpeed) ) > App.mGlSets.dsc_DeltaToChange  ) {
                    //ivSpeedChange2.setImageResource(R.drawable.speed_down_2);
                    //ivSpeedChange2.setVisibility(View.VISIBLE);
                //} else {

                //}
                break;
            case 1:
                ivSpeedChange.setImageResource(R.drawable.speed_up);
                ivSpeedChange.setVisibility(View.VISIBLE);

                //if ( Math.abs( speed - (App.mGlSets.dsc_FirstSpeed + t*App.mGlSets.dsc_StepSpeed) ) > App.mGlSets.dsc_DeltaToChange  ) {
                    //ivSpeedChange2.setImageResource(R.drawable.speed_up_2);
                    //ivSpeedChange2.setVisibility(View.VISIBLE);
                //} else {

                //}


                break;
            default:
                ivSpeedChange.setVisibility(View.INVISIBLE);
                //ivSpeedChange2.setVisibility(View.INVISIBLE);

                break;
        }
        App.mGlSets.gpsPrevSpeed = speed;
    }

    private void show_avalaible_BT_devices( Context context) {

        // выбрать Bluetoth OBD2
        ArrayList<String> deviceStrs = new ArrayList<String>();
        final ArrayList<String> devices = new ArrayList<String>();

        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0)
        {
            for (BluetoothDevice device : pairedDevices)
            {
                deviceStrs.add(device.getName() + "\n" + device.getAddress());
                devices.add(device.getAddress());
            }
        }

// show list
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice,
                deviceStrs.toArray(new String[deviceStrs.size()]));

        alertDialog.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                String deviceAddress = devices.get(position);
                // TODO save deviceAddress
                App.mGlSets.BT_deviceAddress = deviceAddress;
            }
        });

        alertDialog.setTitle("Choose Bluetooth device");
        alertDialog.show();

        if ( App.mGlSets.BT_deviceAddress != "unknown" ) BT_connect(App.mGlSets.BT_deviceAddress);
    }

    private void BT_connect(String deviceAddress) {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        try {
            BluetoothSocket socket = device.createInsecureRfcommSocketToServiceRecord(uuid);

            socket.connect();

	        findViewById (R.id.layout_obd2).setVisibility ( View.VISIBLE);
            try {
                new EchoOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());
                new LineFeedOffObdCommand().run(socket.getInputStream(), socket.getOutputStream());
                new TimeoutObdCommand(0).run(socket.getInputStream(), socket.getOutputStream());
                new SelectProtocolObdCommand(ObdProtocols.AUTO).run(socket.getInputStream(), socket.getOutputStream());
                //new AmbientAirTemperatureObdCommand().run(socket.getInputStream(), socket.getOutputStream());


                EngineRPMObdCommand engineRpmCommand = new EngineRPMObdCommand();
                SpeedObdCommand speedCommand = new SpeedObdCommand();
                while (!Thread.currentThread().isInterrupted())
                {
                    engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
                    speedCommand.run(socket.getInputStream(), socket.getOutputStream());
                    // TODO handle commands result
                    Log.d("MainActivity", "RPM: " + engineRpmCommand.getFormattedResult());
                    Log.d("MainActivity", "Speed: " + speedCommand.getFormattedResult());
                    try {
                        tvOBD_RPM.setText( engineRpmCommand.getFormattedResult() );
                        tvOBD_Speed.setText( speedCommand.getFormattedResult() );
                    } catch (Exception e3) {

                    }
                }

            } catch (Exception e2) {

            }
        } catch (IOException e) {

        }
    }

	private void color_speed(TextView tv, int speed) {
		if ( App.mGlSets.isColorSpeed ) {
			if ( speed < 10 ) tv.setTextColor( Color.LTGRAY);
			else if ( speed < 40 ) tv.setTextColor( Color.rgb(0,255,255));
			else if ( speed < 60 ) tv.setTextColor( Color.rgb(0,255,144));
			else if ( speed < 80 ) tv.setTextColor( Color.rgb(182,255,0));
			else if ( speed < 100 ) tv.setTextColor( Color.rgb(255,216,0));
			else if ( speed < 120 ) tv.setTextColor( Color.rgb(155,106,0));
			else tv.setTextColor( Color.rgb(255,0,0));
		} else {
            tv.setTextColor( Color.LTGRAY);
        }
	}

    private void showFormatedTrackTime(int wayType) {
        long tm = 0;
        if ( wayType == 0)
        {
            tm = App.mGlSets.gpsTimeAtWay;// - TimeZone.getDefault().getOffset(0L);
            // поменять значек
            ivTrackTime.setImageResource(R.drawable.track_time_0);
        } else if ( wayType == 1) {
            tm =  App.mGlSets.gpsTimeAtWayWithoutStops;// - TimeZone.getDefault().getOffset(0L);
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
            tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));

        }
    }
}


