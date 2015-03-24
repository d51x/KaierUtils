package ru.d51x.kaierutils;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.app.AlertDialog;
import android.view.Window;
import android.view.Menu;
import android.view.MenuItem;
import android.content.DialogInterface;

import java.util.Date;

import java.util.TimeZone;
import java.util.Calendar;
import android.widget.PopupWindow;

import com.maxmpz.poweramp.player.PowerampAPI;

public class MainActivity extends Activity implements View.OnClickListener,
													  OnLongClickListener {

	private Handler mHandler;
	private PopupWindow pwindo;
    private TextView tvCurrentVolume;
    private LinearLayout layout_gps_speed;
    private LinearLayout layout_waypoints;
    private LinearLayout layout_tracktime;

    private int modeFuelTank = 0;
    private int modeFuelConsump = 0;

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


	private LinearLayout layout_eq_data;

	private TextView tv_eq_bass;
	private TextView tv_eq_mid;
	private TextView tv_eq_tre;

    private Button btnAGPSReset;

    private ImageView ivVolumeLevel;
    private ImageView ivSpeed;
    private ImageView ivSpeedChange;

    private TextView tvRadioInfo1;
    private TextView tvRadioInfo2;
    private TextView tvMusicInfo1;
    private TextView tvMusicInfo2;
    private ImageView ivAlbumArt;
    private LinearLayout layout_radio_info;
    private LinearLayout layout_music_info;

    private LinearLayout layout_obd_fuel;
    private LinearLayout layout_fuel_consump;

    private ImageView ivOBD2Status;
    private ImageView ivOBD_CarBattery;
    private TextView tvOBD_CarBattery;
    private ImageView ivOBD_CoolantTemp;
    private TextView tvOBD_CoolantTemp;
    private ImageView ivOBD_FuelTank;
    private TextView tvOBD_FuelTank;
    private ImageView ivOBD_FuelConsump;
    private TextView tvOBD_FuelConsump;
    private TextView tvOBD_FuelConsump2;

	private Button btnTest2, btnTest1;
	private SharedPreferences prefs;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
        // Убираем заголовок
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Убираем панель уведомлений
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView (R.layout.main_activity);
		Log.d ("MainActivity", "onCreate");
		prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance ());
		startService(new Intent(this, BackgroundService.class));
		TWUtilEx.initEqData ();
		initComponents();
		setInitData();
		registerReceivers(receiver);
	}

	public void initComponents() {
		tvCurrentVolume = (TextView) findViewById(R.id.tvCurrentVolum);

		// color speed
		layout_gps_speed = (LinearLayout) findViewById(R.id.layout_gps_speed);
		layout_gps_speed.setOnClickListener(this);

		// track time
		layout_tracktime = (LinearLayout) findViewById(R.id.layout_tracktime);
		layout_tracktime.setOnLongClickListener (this);
		layout_tracktime.setOnClickListener(this);



		layout_eq_data = (LinearLayout) findViewById (R.id.layout_eq_data);
		tv_eq_bass = (TextView) findViewById (R.id.tv_eq_bass);
		tv_eq_mid = (TextView) findViewById (R.id.tv_eq_mid);
		tv_eq_tre = (TextView) findViewById (R.id.tv_eq_tre);


		// gps info
		tvGPSSatellitesTotal = (TextView) findViewById(R.id.text_satellites_total);
		tvGPSSatellitesGoodQACount = (TextView) findViewById(R.id.text_satellites_good);
		tvGPSSatellitesInUse = (TextView) findViewById(R.id.text_satellites_inuse);
		tvGPSAccuracy = (TextView) findViewById(R.id.text_gps_accuracy);
		tvGPSAltitude = (TextView) findViewById(R.id.text_gps_altitude);
		tvGPSLatitude = (TextView) findViewById(R.id.text_gps_latitude);
		tvGPSLongitude = (TextView) findViewById(R.id.text_gps_longitude);
		tvGPSSpeed = (TextView) findViewById(R.id.text_gps_speed_value);

		// track distance
		tvTrackTime = (TextView) findViewById(R.id.tvTrackTime);
		tvTrackTime2 = (TextView) findViewById(R.id.tvTrackTime2);
		tvTrackTimeMinOrSec = (TextView) findViewById(R.id.tvTrackTimeMinOrSec);
		tvTrackTimeHourOrMin = (TextView) findViewById(R.id.tvTrackTimeHourOrMin);
		tvGPSDistance = (TextView) findViewById(R.id.tvGPSDistance);
		layout_waypoints = (LinearLayout) findViewById(R.id.layout_waypoints);
		layout_waypoints.setOnLongClickListener(this);
		ivTrackTime = (ImageView) findViewById(R.id.ivTrackTime);
		tvAverageSpeed = (TextView) findViewById(R.id.tvAverageSpeed);
		tvMaxSpeed = (TextView) findViewById(R.id.tvMaxSpeed);

		ivVolumeLevel = (ImageView) findViewById(R.id.ivVolumeLevel);
		ivSpeed = (ImageView) findViewById(R.id.ivSpeed);
		ivSpeedChange = (ImageView) findViewById(R.id.ivSpeedChange);
		ivSpeedChange.setVisibility(View.INVISIBLE);

        ivOBD2Status = (ImageView) findViewById(R.id.ivOBD2Status);
        ivOBD2Status.setOnClickListener(this);
        ivOBD2Status.setOnLongClickListener(this);

        ivOBD_CarBattery = (ImageView) findViewById(R.id.ivOBD_CarBattery);
        tvOBD_CarBattery = (TextView) findViewById(R.id.tvOBD_CarBattery);
        tvOBD_CarBattery.setText("--");

        ivOBD_CoolantTemp = (ImageView) findViewById(R.id.ivOBD_CoolantTemp);
        tvOBD_CoolantTemp = (TextView) findViewById(R.id.tvOBD_CoolantTemp);
        tvOBD_CoolantTemp.setText("--");


        ivOBD_FuelTank = (ImageView) findViewById(R.id.ivOBD_FuelTank);
        tvOBD_FuelTank = (TextView) findViewById(R.id.tvOBD_FuelTank);
        tvOBD_FuelTank.setText("--");

        ivOBD_FuelConsump = (ImageView) findViewById(R.id.ivOBD_FuelConsump);
        tvOBD_FuelConsump = (TextView) findViewById(R.id.tvOBD_FuelConsump);
        tvOBD_FuelConsump.setText("--");
        tvOBD_FuelConsump2 = (TextView) findViewById(R.id.tvOBD_FuelConsump2);
        tvOBD_FuelConsump2.setText("--");
        tvOBD_FuelConsump2.setVisibility(View.GONE);

		btnTest2 = (Button) findViewById (R.id.btnTest2);
		btnTest2.setOnClickListener(this);

		btnTest1 = (Button) findViewById (R.id.btnTest1);
		btnTest1.setOnClickListener(this);

        tvRadioInfo1 = (TextView) findViewById(R.id.tvRadioInfo1);
        tvRadioInfo2 = (TextView) findViewById(R.id.tvRadioInfo2);
        tvMusicInfo1 = (TextView) findViewById(R.id.tvMusicInfo1);
        tvMusicInfo2 = (TextView) findViewById(R.id.tvMusicInfo2);
        tvMusicInfo1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvMusicInfo1.setMarqueeRepeatLimit(-1);
        tvMusicInfo1.setHorizontallyScrolling(true);
        tvMusicInfo1.setSelected(true);
//        tvMusicInfo2.setEllipsize(TextUtils.TruncateAt.MARQUEE);
//        tvMusicInfo2.setMarqueeRepeatLimit(-1);
//        tvMusicInfo2.setHorizontallyScrolling(true);
//        tvMusicInfo2.setSelected(true);

        ivAlbumArt = (ImageView) findViewById(R.id.ivAlbumArt);

        layout_radio_info = (LinearLayout) findViewById(R.id.layout_radio_info);
        layout_music_info = (LinearLayout) findViewById(R.id.layout_music_info);
        layout_radio_info.setVisibility( View.GONE );
        layout_music_info.setVisibility( View.GONE );

        layout_obd_fuel = (LinearLayout) findViewById(R.id.layout_fuel_data);
        layout_obd_fuel.setOnLongClickListener (this);
        layout_obd_fuel.setOnClickListener (this);

        layout_fuel_consump = (LinearLayout) findViewById(R.id.layout_fuel_consump);
        layout_fuel_consump.setOnClickListener (this);
	}

	public void setInitData() {


		// gps info
		tvGPSSatellitesTotal.setText( "--");
		tvGPSSatellitesInUse.setText( "--");
		tvGPSSatellitesGoodQACount.setText( "--" );
		tvGPSAccuracy.setText( "---" );
		tvGPSAltitude.setText( "---" );
		tvGPSLatitude.setText( "--.-----" );
		tvGPSLongitude.setText( "--.-----" );
		tvGPSSpeed.setText( "---" );

		//track distance
		tvTrackTime.setText( getString(R.string.text_gps_track_time_null));
		tvTrackTime2.setText( getString(R.string.text_gps_track_time_null));
		tvTrackTimeMinOrSec.setText(getString(R.string.text_gps_track_time_format_sec));
		tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));
		tvGPSDistance.setText( "----.-" );
		tvMaxSpeed.setText( String.format( getString(R.string.text_max_speed), "---"));
		tvAverageSpeed.setText( String.format( getString(R.string.text_average_speed), "---"));

		setEQData(App.GS.eqData);
        tvRadioInfo1.setText("");
        tvRadioInfo2.setText("");
        tvMusicInfo1.setText("");
        tvMusicInfo2.setText("");
        ivAlbumArt.setImageResource(R.drawable.toast_music);

        ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst);
        ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_in_tank_full);
	}

	public void updataData() {
        updateOBD_FuelTank(App.obd.oneTrip.fuel_remains);
	}

	public void onStart() {
		super.onStart();
		updataData();
	}

	public void onPause() {
		super.onPause();
	}

	public void onResume() {
		super.onResume();

		tvCurrentVolume.setText(Integer.toString (App.GS.getVolumeLevel ()) );
		layout_eq_data.setVisibility ( App.GS.isShowEQData ? View.VISIBLE : View.INVISIBLE );
		setVolumeIcon(ivVolumeLevel, App.GS.getVolumeLevel());
        TWUtilEx.requestAudioFocusState();
        // определим, запущено ли радио
        Radio.checkRadioActivityStarted();
        updateOBDStatus(App.obd.isConnected);


	}

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
                default:
                    return false;
            }

        }


    // анализируем, какая кнопка была нажата. Всего один метод для всех кнопок
    @Override
    public void onClick(View v){

        switch (v.getId()) {
	        case R.id.btnTest1:
                // radio audio focus
                if (App.GS.curAudioFocusID > 0) TWUtilEx.setAudioFocus(128 & App.GS.curAudioFocusID);
                TWUtilEx.setAudioFocus(1);
                startService(new Intent( Radio.PACKAGE_NAME ));
                TWUtilEx.requestRadioInfo();
		        break;
            case R.id.btnTest2:
                // music audio focus
                if (App.GS.curAudioFocusID > 0) TWUtilEx.setAudioFocus(128 & App.GS.curAudioFocusID);
                TWUtilEx.setAudioFocus(3);
                startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
                        PowerampAPI.Commands.TOGGLE_PLAY_PAUSE));

	            break;
            case R.id.layout_gps_speed:
                color_speed(tvGPSSpeed, App.GS.gpsSpeed);
                App.GS.isColorSpeed = ! App.GS.isColorSpeed;
	            PreferenceManager.getDefaultSharedPreferences (App.getInstance ()).edit().putBoolean ("kaierutils_show_color_speed", App.GS.isColorSpeed).commit ();
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
                prefs.edit().commit();
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
                switch_fuel_tank_mode();
                break;
            case R.id.layout_fuel_consump:
                switch_fuel_consump_mode();
                break;
            default:
                break;
        }
    }


	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED))
			{
				int vol = intent.getIntExtra (TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED, 0);
				tvCurrentVolume.setText (String.format( Integer.toString(vol)));
                setVolumeIcon(ivVolumeLevel, vol);
			}
            else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH) )
            {
                //tvReverseCount.setText( String.format(getString(R.string.text_reverse_count),
                //                                      App.GS.ReverseActivityCount) );
            }
            else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_SLEEP) )
            {
//                tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
//                        App.GS.SleepModeCount) );

                //if ( App.GS.lastSleep == 0)
                //{
                    //tvSleepModeLastTime.setVisibility( View.INVISIBLE );
                //}
                //else
                //{
                    //Date date = new Date( App.GS.lastSleep );
                   // SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
                   // tvSleepModeLastTime.setText( String.format("%s", ft.format(date)) );
                   // tvSleepModeLastTime.setVisibility( View.VISIBLE );
                //}
            }
            else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP) )
            {
	           // tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
			   //         App.GS.SleepModeCount) );

            }
			else if ( action.equals ( TWUtilConst.TW_BROADCAST_ACTION_EQ_CHANGED) )
            {
	            App.GS.eqData = intent.getByteArrayExtra ("EQ");
	            setEQData(App.GS.eqData);
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
				App.GS.cntGpsHangs++;
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

                tvAverageSpeed.setText( String.format( getString(R.string.text_average_speed), Integer.toString( App.GS.gpsAverageSpeed)));
                tvMaxSpeed.setText( String.format( getString(R.string.text_max_speed), Integer.toString( App.GS.gpsMaxSpeed)));

                if ( !App.GS.dsc_isAvailable ) {
                    ivSpeedChange.setVisibility(View.INVISIBLE);
                }
                float dist = App.GS.totalDistance / 1000;

                tvGPSDistance.setText (  dist > 0 ? String.format(getString(R.string.text_gps_distance), dist).replace(",", ".") : "----.-" );
                showFormatedTrackTime( App.GS.gpsTimeAtWay_Type );
            }
			else if ( action.equals( TWUtilConst.TW_BROADCAST_ACTION_RADIO_CHANGED)) {
				String title = intent.getStringExtra("Title");
				String freq = intent.getStringExtra ("Frequency");
                tvRadioInfo1.setText( title );
                tvRadioInfo1.setVisibility( title.contentEquals( Radio.BLANK_STATION_NAME) ? View.GONE : View.VISIBLE);
                tvRadioInfo2.setText( freq + " MHz");
            }
            else if ( action.equals( TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED)) {
				int af_id = intent.getIntExtra("audio_focus_id", -1);
                // update screen
                updateAudioModeInfo(App.GS.curAudioFocusID);

            }
            else if ( action.equals( GlSets.PWRAMP_BROADCAST_ACTION_TRACK_CHANGED)) {
                if ( App.GS.interactWithPowerAmp && App.GS.isShowMusicInfo && App.GS.isPowerAmpPlaying )
                {
                    //String TrackTitle = intent.getStringExtra("TrackTitle");
                    //String AlbumArtist = intent.getStringExtra ("AlbumArtist");
                    Bitmap AlbumArt = (Bitmap) intent.getParcelableExtra("AlbumArt");
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
            } else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED )) {
                boolean obd_status = intent.getBooleanExtra("Status", false);
                updateOBDStatus(obd_status);
            } else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED )) {
                updateOBD_CarBattery(App.obd.obdData.voltage);
            } else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED )) {
                updateOBD_CoolantTemp(App.obd.obdData.coolant);
            } else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED )) {
                updateOBD_FuelConsump( App.obd.oneTrip.fuel_cons_lph );
                updateOBD_FuelTank( App.obd.oneTrip.fuel_remains );
            } else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED )) {

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
			it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.SettingsActivity");
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
        if ( !App.GS.dsc_isAvailable) return;
        int  t = Math.abs(speed - App.GS.dsc_FirstSpeed) / App.GS.dsc_StepSpeed;
        switch ( grow ){
            case -1:
                ivSpeedChange.setImageResource(R.drawable.speed_down);
                ivSpeedChange.setVisibility(View.VISIBLE);


                //if ( Math.abs( speed - (App.GS.dsc_FirstSpeed + t*App.GS.dsc_StepSpeed) ) > App.GS.dsc_DeltaToChange  ) {
                    //ivSpeedChange2.setImageResource(R.drawable.speed_down_2);
                    //ivSpeedChange2.setVisibility(View.VISIBLE);
                //} else {

                //}
                break;
            case 1:
                ivSpeedChange.setImageResource(R.drawable.speed_up);
                ivSpeedChange.setVisibility(View.VISIBLE);

                //if ( Math.abs( speed - (App.GS.dsc_FirstSpeed + t*App.GS.dsc_StepSpeed) ) > App.GS.dsc_DeltaToChange  ) {
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
        App.GS.gpsPrevSpeed = speed;
    }

    private void show_obdii_activity(Context context) {

        try {
            Intent it = new Intent();
            it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.OBDIIActivity");
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
            tv.setTextColor( Color.LTGRAY);
        }
	}

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
            tvTrackTimeHourOrMin.setText( getString(R.string.text_gps_track_time_format_min));

        }
    }

	private void registerReceivers(BroadcastReceiver receiver) {
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_RADIO_CHANGED));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_EQ_CHANGED));
		registerReceiver(receiver, new IntentFilter(TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED));


		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS));
		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED));
		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_SPEED_CHANGED));
		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_FIRST_FIX));
		registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET));
		registerReceiver(receiver, new IntentFilter(GlSets.PWRAMP_BROADCAST_ACTION_TRACK_CHANGED));

		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED));
		registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));
	}




	public void setEQData(byte[] bArr) {
	//bass bArr[1], mid  bArr[2], tree bArr[3], loud bArr[5]
	            /*
					0	1	2	3	4	5	6	7	8	9	10	11	12	13	14
					-7	-6	-5	-4	-3	-2	-1	0	1	2	3	4	5	6	7
	             */
		if ( bArr != null ) {
			tv_eq_bass.setText( Integer.toString ( bArr[1] - 7 ));
			tv_eq_mid.setText( Integer.toString (bArr[2] - 7));
			tv_eq_tre.setText( Integer.toString (bArr[3] - 7));
		}
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
            ivOBD2Status.setImageResource( R.drawable.obd_connected);
        } else {
            ivOBD2Status.setImageResource( R.drawable.obd_disconnected);
        }
    }

    public void updateOBD_CarBattery(double voltage){
        if ( voltage < 12 ) {
            ivOBD_CarBattery.setImageResource( R.drawable.car_battery_bad);
        } else {
            ivOBD_CarBattery.setImageResource( R.drawable.car_battery_good);
        }
        tvOBD_CarBattery.setText( String.format("%1$.1f", voltage));
    }

    public void updateOBD_CoolantTemp(float temp){
        if ( temp < 80 ) {
            ivOBD_CoolantTemp.setImageResource( R.drawable.coolant_temp_min);
        } else if (temp < 99) {
            ivOBD_CoolantTemp.setImageResource( R.drawable.coolant_temp_norm);
        } else {
            ivOBD_CoolantTemp.setImageResource( R.drawable.coolant_temp_hot);
        }
        tvOBD_CoolantTemp.setText( String.format("%1$.0f", temp));
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
    }

    public void updateOBD_FuelConsump(float consump){
//        if ( consump < 9 ) {
//            ivOBD_FuelConsump.setImageResource( R.drawable.fuel_consump_min);
//        } else {
//            ivOBD_FuelConsump.setImageResource( R.drawable.fuel_consump_max);
//        }
        //tvOBD_FuelConsump.setText( String.format("%1$.1f", consump));
        show_fuel_consumption(modeFuelConsump);
    }

    // диалог с впросом о заправке (полный бак или нет)
    public void show_obd_fuel_dialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle(getString(R.string.text_fuel_tank_title));
        alertDialogBuilder.setMessage( getString(R.string.text_fuel_tank_full));
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
        etFuelTankRemain.setText( String.format("%1$.0f", App.obd.oneTrip.fuel_remains));
        seekBarFuel.setMax( 100 );
        int percent = Math.round(App.obd.oneTrip.fuel_remains * 100 / App.obd.obdData.fuel_tank);
        seekBarFuel.setProgress( percent );
        tvFuelPercent.setText(String.valueOf(percent) + " %");
        seekBarFuel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // TODO Auto-generated method stub
                float fltank  = Float.parseFloat(etFuelTankCapacity.getText().toString());
                float flremain  = fltank * progress / 100;

                etFuelTankRemain.setText(String.format("%1$.0f", flremain));
                tvFuelPercent.setText( String.valueOf(progress) + " %");
            }
        });
        fuelDialog.setPositiveButton("Сохранить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        App.obd.setCustomTank(Float.parseFloat(etFuelTankCapacity.getText().toString()),
                                Float.parseFloat(etFuelTankRemain.getText().toString()));
                        updateOBD_FuelTank(App.obd.oneTrip.fuel_remains);
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

    // переключение режима показаний топлива в баке
    private void switch_fuel_tank_mode() {
        // режим отображения уровня топлива
        // 0 - осталось в литрах
        // 1 - осталось в процентах
        // 2 - микс-режим (первая строка - литры, вторая строка - проценты
        // 3 - потрачено за поездку (л)
        modeFuelTank++;
        if ( modeFuelTank > 2) modeFuelTank = 0;
        show_fuel_tank_data(modeFuelTank);

        switch ( modeFuelTank ) {
            case 0:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_in_tank_full);
                //Toast.makeText(this, "Остаток в баке(л) ", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_in_tank_percent_full);
                //Toast.makeText(this, "Остаток в баке (%)", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_used_full);
                //Toast.makeText(this, "Израсходовано топлива (л)", Toast.LENGTH_SHORT).show();
                break;
            default:
                ivOBD_FuelTank.setImageResource(R.drawable.fuel_tank_full);
                break;
        }
    }

    // переключение режима показаний расхода
    private void switch_fuel_consump_mode() {
        // режим отображения расхода
        // 0 - мгновенный
        // 1 - средний за поездку с учетом sleep
        // 2 - отображаем литры в час
        // 3 - комбинированный режим, первая строка - средний, вторая мгновенный
        modeFuelConsump++;
        if ( modeFuelConsump > 3) modeFuelConsump = 0;

        show_fuel_consumption(modeFuelConsump);

        // инфу о переключении надо отображать здесь
        switch ( modeFuelConsump ) {
            case 0:
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst);
                show_hide_fuel_consump_line_2( modeFuelConsump == 3);
                break;
            case 1:
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_avg);
                show_hide_fuel_consump_line_2( modeFuelConsump == 3);
                break;
            case 2:
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lph);
                show_hide_fuel_consump_line_2( modeFuelConsump == 3);
                break;
            case 3:
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump_lpk_inst_avg);
                show_hide_fuel_consump_line_2( modeFuelConsump == 3);
                break;
            default:
                show_hide_fuel_consump_line_2( modeFuelConsump == 3);
                ivOBD_FuelConsump.setImageResource(R.drawable.fuel_consump);
                break;
        }
    }

    // отобразить данные о текущем расходе топлива
    private void show_fuel_consumption(int mode) {
        switch (mode) {
            case 0:
                tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_inst) );
                break;
            case 1:
                tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg) );
                break;
            case 2:
                tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lph) );
                break;
            case 3:
                tvOBD_FuelConsump.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_avg) );
                tvOBD_FuelConsump2.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_cons_lp100km_inst) );
                break;
            default:
                break;
        }
    }

    // отобразить данные  о количестве топлива в баке
    private void show_fuel_tank_data(int mode) {
        switch (mode) {
            case 0:
                tvOBD_FuelTank.setText( String.format("%1$.1f", App.obd.oneTrip.fuel_remains));
                break;
            case 1:
                tvOBD_FuelTank.setText( String.format("%1$.0f", (float) ((App.obd.oneTrip.fuel_remains * 100) / App.obd.obdData.fuel_tank))  + "%");
                break;
            case 2:
                tvOBD_FuelTank.setText( String.format("%1$.3f", App.obd.oneTrip.fuel_usage));
                break;
            default:
                break;
        }
    }

    // отобразить/спрятать второй показатель расхода при включении/выключении комбинированного режима
    private void show_hide_fuel_consump_line_2 (boolean line2) {
        if ( line2 ) {
            // show line 2
            tvOBD_FuelConsump.setTextSize( 16 );
            tvOBD_FuelConsump2.setTextSize( 12 );
            tvOBD_FuelConsump2.setVisibility(View.VISIBLE);
        } else {
            // hide line 2
            tvOBD_FuelConsump.setTextSize( 32 );
            tvOBD_FuelConsump2.setVisibility(View.GONE);
        }
    }
}


