package ru.d51x.kaierutils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.provider.Settings;

import com.maxmpz.poweramp.player.PowerampAPI;

import org.w3c.dom.Text;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{



	private Button btnSoundSettings;
    private Button btnPowerAmpControl;
    private TextView tvDeviceName;
    private TextView tvCurrentVolume;
    public static Handler mHandler;
    private Switch switch_show_notification_icon;

    private Switch switch_show_volume_on_notification_icon;
    private BackgroundService backgroundService;

    private TextView tvReverseCount;
    private TextView tvSleepModeCount;
    private TextView tvSleepModeLastTime;
    private TextView tvWorkingStart;

    private Button btnTestVolumeUp;
    private Button btnTestVolumeDown;
    private Button btnTestPressNext;
    private Button btnTestPressPrev;
    private Button btnTestPlay;
    private Button btnTestPause;

    private TextView tvGPSSatellitesTotal;
    private TextView tvGPSSatellitesGoodQACount;
    private TextView tvGPSAccuracy;
    private TextView tvGPSAltitude;
    private TextView tvGPSLatitude;
    private TextView tvGPSLongitude;
    private TextView tvGPSSpeed;

    private Button btnAGPSReset;

	public TextView getTextViewByID(int id) {

			return (TextView) findViewById(id);
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.main_activity);
		Log.d ("MainActivity", "onCreate");
		startService(new Intent(this, BackgroundService.class));
		Toast.makeText (this, "Service started", 0).show();

		btnSoundSettings = (Button) findViewById (R.id.id_button_sound_Settings);
        btnPowerAmpControl = (Button) findViewById(R.id.btn_poweramp_control);

        tvDeviceName = (TextView) findViewById(R.id.txtDeviceName);
        String string_device_name = String.format(getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

        tvCurrentVolume = (TextView) findViewById(R.id.tvCurrentVolum);
        tvCurrentVolume.setText( String.format(getString(R.string.text_current_level), App.mGlSets.getVolumeLevel()) );

        switch_show_notification_icon = (Switch) findViewById(R.id.switch_show_notification_icon);
        App.mGlSets.isNotificationIconShow = Settings.System.getInt(getContentResolver(), NotifyData.OPTION_SHOW_NOTIFICATION_ICON, 0) == 1;
        switch_show_notification_icon.setChecked(App.mGlSets.isNotificationIconShow);
        switch_show_notification_icon.setOnCheckedChangeListener(this);

        switch_show_volume_on_notification_icon = (Switch) findViewById(R.id.switch_show_volume_on_notification_icon);
        App.mGlSets.isVolumeShowOnNotificationIcon = Settings.System.getInt(getContentResolver(), NotifyData.OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON, 0) == 1;
        switch_show_volume_on_notification_icon.setChecked(App.mGlSets.isVolumeShowOnNotificationIcon);
        switch_show_volume_on_notification_icon.setOnCheckedChangeListener(this);

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
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy  hh:mm");
        tvWorkingStart.setText( String.format(getString(R.string.text_working_start), ft.format(date)) );

        btnTestVolumeUp = (Button) findViewById(R.id.tst_volume_up);
        btnTestVolumeDown = (Button) findViewById(R.id.tst_volume_down);
        btnTestPressNext = (Button) findViewById(R.id.tst_svc_next);
        btnTestPressPrev = (Button) findViewById(R.id.tst_svc_prev);
        btnTestPlay = (Button) findViewById(R.id.tst_pa_play);
        btnTestPause = (Button) findViewById(R.id.tst_pa_pause);

        tvGPSSatellitesTotal = (TextView) findViewById(R.id.text_satellites_total);
        tvGPSSatellitesGoodQACount = (TextView) findViewById(R.id.text_satellites_good);
        tvGPSSatellitesTotal.setText(String.format(getString(R.string.text_satellites_total), 0));
        tvGPSSatellitesGoodQACount.setText( String.format(getString(R.string.text_satellites_good), 0) );

        tvGPSAccuracy = (TextView) findViewById(R.id.text_gps_accuracy);
        tvGPSAccuracy.setText( String.format(getString(R.string.text_gps_accuracy), "") );
        tvGPSAltitude = (TextView) findViewById(R.id.text_gps_altitude);
        tvGPSAltitude.setText( String.format(getString(R.string.text_gps_altitude), "") );
        tvGPSLatitude = (TextView) findViewById(R.id.text_gps_latitude);
        tvGPSLatitude.setText( String.format(getString(R.string.text_gps_latitude), "") );
        tvGPSLongitude = (TextView) findViewById(R.id.text_gps_longitude);
        tvGPSLongitude.setText( String.format(getString(R.string.text_gps_longitude), "") );
        tvGPSSpeed = (TextView) findViewById(R.id.text_gps_speed_value);
        tvGPSSpeed.setText( String.format(getString(R.string.text_gps_speed_value), "") );


        this.btnSoundSettings.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) // клик на кнопку
			{

				try {
					Intent it = new Intent();
					//Intent it = new Intent(this, SoundSettingsPreferenceActivity.class);
					it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.SoundSettingsPreferenceActivity");
					//it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
					startActivity(it);
				} catch (Exception e) {
				}

			}
		});

        this.btnPowerAmpControl.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {

                try {
                    Intent it = new Intent();
                    //Intent it = new Intent(this, SoundSettingsPreferenceActivity.class);
                    it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.PowerAmpPreferenceActivity");
                    //it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    it.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(it);
                } catch (Exception e) {
                }

            }
        });

        this.btnTestVolumeUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                try {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    int vol = App.mGlSets.getVolumeLevel();
                    vol++;
                    App.mGlSets.setVolumeLevel(vol, false);
                    intent.putExtra(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED, vol);
                    intent.setAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED);
                    sendBroadcast(intent);
                } catch (Exception e) {
                }

            }
        });
        this.btnTestVolumeDown.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                try {
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    int vol = App.mGlSets.getVolumeLevel();
                    vol--;
                    App.mGlSets.setVolumeLevel(vol, false);
                    intent.putExtra(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED, vol);
                    intent.setAction(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED);
                    sendBroadcast(intent);
                } catch (Exception e) {
                }
            }
        });
        this.btnTestPressNext.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                getApplicationContext().startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.NEXT_IN_CAT));
            }
        });
        this.btnTestPressPrev.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                getApplicationContext().startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.PREVIOUS_IN_CAT));
            }
        });
        this.btnTestPlay.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                getApplicationContext().startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.TOGGLE_PLAY_PAUSE));
            }
        });
        this.btnTestPause.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {
                getApplicationContext().startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND, PowerampAPI.Commands.PAUSE));
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

        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS));
        registerReceiver(receiver, new IntentFilter(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED));
	}

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        NotifyData notifyData = new NotifyData( );
        switch (id){
            case R.id.switch_show_notification_icon:
                Settings.System.putInt(this.getContentResolver(), NotifyData.OPTION_SHOW_NOTIFICATION_ICON, isChecked ? 1 : 0);
                App.mGlSets.isNotificationIconShow = isChecked;


                //notifyData.NotifyID = App.mGlSets.isVolumeShowOnNotificationIcon ? NotifyData.NOTIFY_VOLUME_CHANGED_ID : NotifyData.NOTIFY_ID;
                notifyData.smallIcon = (App.mGlSets.isNotificationIconShow) ? R.drawable.notify_auto : 0;
                notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                notifyData.number = App.mGlSets.isVolumeShowOnNotificationIcon ? App.mGlSets.getVolumeLevel() : 0;
                ShowNotification(notifyData);
                break;
            case R.id.switch_show_volume_on_notification_icon:
                Settings.System.putInt(this.getContentResolver(), NotifyData.OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON, isChecked ? 1 : 0);
                App.mGlSets.isVolumeShowOnNotificationIcon = isChecked;


                //notifyData.NotifyID = App.mGlSets.isVolumeShowOnNotificationIcon ? NotifyData.NOTIFY_VOLUME_CHANGED_ID : NotifyData.NOTIFY_ID;
                notifyData.smallIcon = (App.mGlSets.isNotificationIconShow) ? R.drawable.notify_auto : 0;
                notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                notifyData.number = App.mGlSets.isVolumeShowOnNotificationIcon ? App.mGlSets.getVolumeLevel() : 0;
                ShowNotification(notifyData);
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
				tvCurrentVolume.setText (String.format( App.getInstance ().getString (R.string.text_current_level), vol));

                // отобразим изменное значение громкости на иконке уведомления
                if ( App.mGlSets.isVolumeShowOnNotificationIcon ) {
                    // возможно, надо не новое уведомление делать, а заменять старое
                    NotifyData notifyData = new NotifyData( );
                    //notifyData.NotifyID = isVolumeShowOnNotificationIcon ? NOTIFY_VOLUME_CHANGED_ID : NOTIFY_ID;
                    notifyData.smallIcon = App.mGlSets.isNotificationIconShow ? R.drawable.notify_auto : 0;
                    notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                    notifyData.number = App.mGlSets.isVolumeShowOnNotificationIcon ? vol : 0;
                    ShowNotification(notifyData);
                }
			}
            else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH ) )
            {
                tvReverseCount.setText( String.format(getString(R.string.text_reverse_count),
                                                      App.mGlSets.ReverseActivityCount) );
            }
            else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP ) )
            {
                tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
                                                        App.mGlSets.SleepModeCount) );
                if ( App.mGlSets.lastSleep == 0)
                {
                    tvSleepModeLastTime.setVisibility( View.INVISIBLE );
                }
                else
                {
                    Date date = new Date( App.mGlSets.lastSleep );
                    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy  HH:mm", Locale.US);
                    tvSleepModeLastTime.setText( String.format("%s", ft.format(date)) );
                    tvSleepModeLastTime.setVisibility( View.VISIBLE );
                }
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_SATELLITE_STATUS))
            {
                int cntSats = intent.getIntExtra ("SatellitesTotal", 0);
                int goodSatellitesCount = intent.getIntExtra( "SatellitesGoodQATotal", 0 );
                tvGPSSatellitesTotal.setText( String.format(getString(R.string.text_satellites_total), cntSats) );
                tvGPSSatellitesGoodQACount.setText( String.format(getString(R.string.text_satellites_good), goodSatellitesCount) );
            }
            else if (action.equals(GlSets.GPS_BROADCAST_ACTION_LOCATION_CHANGED)) {
                tvGPSAccuracy.setText( String.format(getString(R.string.text_gps_accuracy), intent.getStringExtra("Accuracy")) );
                tvGPSAltitude.setText( String.format(getString(R.string.text_gps_altitude), intent.getStringExtra("Altitude")) );
                tvGPSLatitude.setText( String.format(getString(R.string.text_gps_latitude), intent.getStringExtra("Latitude")) );
                tvGPSLongitude.setText( String.format(getString(R.string.text_gps_longitude), intent.getStringExtra("Longitude")) );
                tvGPSSpeed.setText( String.format(getString(R.string.text_gps_speed_value), intent.getStringExtra("Speed")) );
            }
		}
	};



    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    public Notification ShowNotification(NotifyData notifyData){
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.cancel(notifyData.NotifyID);
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle( notifyData.Title );
        builder.setContentText( notifyData.Text );
        builder.setAutoCancel(false);
        builder.setWhen( App.mGlSets.startDate);
        if ( notifyData.smallIcon > 0 ) builder.setSmallIcon( notifyData.smallIcon );
        Notification notification = builder.build();
        notification.flags |= notifyData.flags;
        notification.number =  notifyData.number;
        if ( notifyData.ActivityClass != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), notifyData.ActivityClass), 0);
            notification.setLatestEventInfo(getApplicationContext(),  notifyData.Title, notifyData.Text, pendingIntent);
        }
        notificationManager.notify(notifyData.NotifyID, notification);
        return notification;
    }

}


