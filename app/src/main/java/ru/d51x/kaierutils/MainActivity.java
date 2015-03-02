package ru.d51x.kaierutils;

import android.app.Activity;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.GpsStatus;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.maxmpz.poweramp.player.PowerampAPI;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{



	private Button btnSoundSettings;

    private TextView tvDeviceName;
    private TextView tvCurrentVolume;
    private Switch switch_show_notification_icon;

    private TextView tvReverseCount;
    private TextView tvSleepModeCount;
    private TextView tvSleepModeLastTime;
    private TextView tvWorkingStart;

    private TextView tvGPSSatellitesTotal;
    private TextView tvGPSSatellitesGoodQACount;

    private TextView tvGPSAccuracy;
    private TextView tvGPSAltitude;
    private TextView tvGPSLatitude;
    private TextView tvGPSLongitude;
    private TextView tvGPSSpeed;

    private Button btnAGPSReset;
    private Button btnPowerAmpControl;

    private ImageView ivGPSStatus;
    private ImageView ivVolumeLevel;
    private ImageView ivGPSHangs;
    private TextView tvGPSHangs;
    private Button btnDynamicSound;
    private ImageView ivSpeedChange;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);

        /*
        // Убираем заголовок
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Убираем панель уведомлений
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        * */
		setContentView (R.layout.main_activity);
		Log.d ("MainActivity", "onCreate");
		startService(new Intent(this, BackgroundService.class));
		Toast.makeText (this, "Service started", 0).show();

		btnSoundSettings = (Button) findViewById (R.id.id_button_sound_Settings);

        tvDeviceName = (TextView) findViewById(R.id.txtDeviceName);
        String string_device_name = String.format(getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

        tvCurrentVolume = (TextView) findViewById(R.id.tvCurrentVolum);
        tvCurrentVolume.setText(Integer.toString(App.mGlSets.getVolumeLevel()) );

        switch_show_notification_icon = (Switch) findViewById(R.id.switch_show_notification_icon);
        App.mGlSets.isNotificationIconShow = Settings.System.getInt(getContentResolver(), NotifyData.OPTION_SHOW_NOTIFICATION_ICON, 0) == 1;
        switch_show_notification_icon.setChecked(App.mGlSets.isNotificationIconShow);
        switch_show_notification_icon.setOnCheckedChangeListener(this);

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
        tvGPSSatellitesTotal.setText( "0");
        tvGPSSatellitesGoodQACount.setText( "0" );

        tvGPSAccuracy = (TextView) findViewById(R.id.text_gps_accuracy);
        tvGPSAccuracy.setText( String.format(getString(R.string.text_gps_accuracy), "0") );
        tvGPSAltitude = (TextView) findViewById(R.id.text_gps_altitude);
        tvGPSAltitude.setText( String.format(getString(R.string.text_gps_altitude), "0") );
        tvGPSLatitude = (TextView) findViewById(R.id.text_gps_latitude);
        tvGPSLatitude.setText( "0" );
        tvGPSLongitude = (TextView) findViewById(R.id.text_gps_longitude);
        tvGPSLongitude.setText( "0" );
        tvGPSSpeed = (TextView) findViewById(R.id.text_gps_speed_value);
        tvGPSSpeed.setText( String.format(getString(R.string.text_gps_speed_value), "0") );

        ivGPSStatus = (ImageView) findViewById(R.id.ivGPSStatus);
        ivGPSStatus.setImageResource(R.drawable.gps_disconnected);

        ivVolumeLevel = (ImageView) findViewById(R.id.ivVolumeLevel);
        setVolumeIcon(ivVolumeLevel, App.mGlSets.getVolumeLevel());

        ivGPSHangs = (ImageView) findViewById(R.id.ivGPSHangs);
        ivGPSHangs.setImageResource(R.drawable.gps_disconnected_filled);

        ivSpeedChange = (ImageView) findViewById(R.id.ivSpeedChange);
        ivSpeedChange.setVisibility(View.INVISIBLE);

        tvGPSHangs = (TextView) findViewById(R.id.tvGPSHangs);
        tvGPSHangs.setText(String.format(getString(R.string.text_gps_hangs), 0));

        btnPowerAmpControl = (Button) findViewById(R.id.id_button_poweramp_control);
        btnDynamicSound = (Button) findViewById(R.id.id_button_dynamic_sound_settings);

        this.btnSoundSettings.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) // клик на кнопку
			{

                show_sound_settings();

			}
		});

        this.btnPowerAmpControl.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {

                show_poweramp_control();

            }
        });


        btnAGPSReset = (Button) findViewById(R.id.btn_agps_reset);
        this.btnAGPSReset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                Intent intent = new Intent();
                intent.setAction( GlSets.GPS_BROADCAST_ACTION_AGPS_RESET );
                sendBroadcast(intent);
            }
        });

        this.btnDynamicSound.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
               // DynamicSoundSettingsActivity
                show_dynamic_sound_settings();
            }
        });

        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_FIRST_FIX));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_EVENT_STATUS));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET));
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
                tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
                        App.mGlSets.SleepModeCount) );
                tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
                        App.mGlSets.SleepModeCount) );
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

            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS))
            {
                int cntSats = intent.getIntExtra ("SatellitesTotal", 0);
                int goodSatellitesCount = intent.getIntExtra( "SatellitesGoodQATotal", 0 );
                tvGPSSatellitesTotal.setText( Integer.toString(cntSats) );
                tvGPSSatellitesGoodQACount.setText( Integer.toString(goodSatellitesCount) );

                if ( goodSatellitesCount  <  2 ) {
                    tvGPSSatellitesGoodQACount.setTextColor(Color.RED);
                } else if ( goodSatellitesCount < GpsProcessing.signal_quality ) {
                    tvGPSSatellitesGoodQACount.setTextColor(Color.YELLOW);
                } else {
                    tvGPSSatellitesGoodQACount.setTextColor(Color.GREEN);
                }

            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_EVENT_STATUS))
            {
                int status = intent.getIntExtra("gps_event", 0);
                switch ( status ) {
                    case GpsStatus.GPS_EVENT_STARTED:
                        ivGPSStatus.setImageResource(R.drawable.gps_searching);  // запуск таймера, мигаем
                        break;
                    case GpsStatus.GPS_EVENT_STOPPED:
                        ivGPSStatus.setImageResource(R.drawable.gps_disconnected);
                        break;
                    case GpsStatus.GPS_EVENT_FIRST_FIX:
                        ivGPSStatus.setImageResource(R.drawable.gps_connected);
                        break;
                    default:
                        ivGPSStatus.setImageResource(R.drawable.gps_disconnected);
                        break;
                }

            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_FIRST_FIX))
            {
                ivGPSHangs.setImageResource(R.drawable.gps_connected_filled);
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_AGPS_RESET)) {
                ivGPSHangs.setImageResource(R.drawable.gps_disconnected_filled);
                tvGPSHangs.setText(String.format(getString(R.string.text_gps_hangs), App.mGlSets.cntGpsHangs));
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_SPEED_CHANGED)) {
                double speed = intent.getDoubleExtra("Speed", 0);
                int grow = intent.getIntExtra("SpeedGrow", 0);
                processDynamicVoume(speed, grow);
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED)) {
                double latitude = intent.getDoubleExtra("Latitude", 0);
                if ( latitude < 0 ) {
                    //S - south latitude
                    tvGPSLatitude.setText( String.format("S %1$.6f", latitude*(-1)) );
                } else {
                    //N - north latitude
                    tvGPSLatitude.setText( String.format("N %1$.6f", latitude) );
                }

                double longitude = intent.getDoubleExtra("Longitude", 0);
                if ( longitude < 0 ) {
                    //W - west longitude
                    tvGPSLongitude.setText( String.format("W %1$.6f", longitude*(-1)) );
                } else {
                    //E - east longitude
                    tvGPSLongitude.setText( String.format("E %1$.6f", longitude) );
                }
                tvGPSAccuracy.setText( String.format(getString(R.string.text_gps_accuracy), intent.getStringExtra("Accuracy")) );
                tvGPSAltitude.setText( String.format(getString(R.string.text_gps_altitude), intent.getStringExtra("Altitude")) );
                tvGPSSpeed.setText( String.format(getString(R.string.text_gps_speed_value), intent.getStringExtra("Speed")) );

                if ( !App.mGlSets.dsc_isAvailable ) ivSpeedChange.setVisibility(View.INVISIBLE);
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

    private void  processDynamicVoume(double speed, int grow) {
        if ( !App.mGlSets.dsc_isAvailable) return;
        switch ( grow ){
            case -1:
                ivSpeedChange.setImageResource(R.drawable.speed_down);
                ivSpeedChange.setVisibility(View.VISIBLE);
                break;
            case 1:
                ivSpeedChange.setImageResource(R.drawable.speed_up);
                ivSpeedChange.setVisibility(View.VISIBLE);
                break;
            default:
                ivSpeedChange.setVisibility(View.INVISIBLE);
                break;
        }
        App.mGlSets.gpsPrevSpeed = speed;
    }
}


