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
import android.os.Message;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;
import android.provider.Settings;
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

	public TextView getTextViewByID(int id) {

			return (TextView) findViewById(id);
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.main_activity);
		Log.d ("MainActivity", "onCreate");
		startService(new Intent(this, BackgroundService.class));
		Toast.makeText (this, "Service started", 0);

		btnSoundSettings = (Button) findViewById (R.id.id_button_sound_Settings);
        btnPowerAmpControl = (Button) findViewById(R.id.btn_poweramp_control);

        tvDeviceName = (TextView) findViewById(R.id.txtDeviceName);
        String string_device_name = String.format(getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

        tvCurrentVolume = (TextView) findViewById(R.id.tvCurrentVolum);
        tvCurrentVolume.setText( String.format(getString(R.string.text_current_level), App.mGlobalSettings.getVolumeLevel()) );

        switch_show_notification_icon = (Switch) findViewById(R.id.switch_show_notification_icon);
        App.mGlobalSettings.isNotificationIconShow = Settings.System.getInt(getContentResolver(), NotifyData.OPTION_SHOW_NOTIFICATION_ICON, 0) == 1;
        switch_show_notification_icon.setChecked(App.mGlobalSettings.isNotificationIconShow);
        switch_show_notification_icon.setOnCheckedChangeListener(this);

        switch_show_volume_on_notification_icon = (Switch) findViewById(R.id.switch_show_volume_on_notification_icon);
        App.mGlobalSettings.isVolumeShowOnNotificationIcon = Settings.System.getInt(getContentResolver(), NotifyData.OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON, 0) == 1;
        switch_show_volume_on_notification_icon.setChecked(App.mGlobalSettings.isVolumeShowOnNotificationIcon);
        switch_show_volume_on_notification_icon.setOnCheckedChangeListener(this);

        tvReverseCount = (TextView) findViewById(R.id.tv_reverse_count);
        tvReverseCount.setText( String.format(getString(R.string.text_reverse_count), App.mGlobalSettings.ReverseActivityCount) );

        tvSleepModeCount = (TextView) findViewById(R.id.tv_sleep_mode_count);
        tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count), App.mGlobalSettings.SleepModeCount) );

        tvSleepModeLastTime = (TextView) findViewById(R.id.tv_sleep_mode_last_time);
        if ( App.mGlobalSettings.lastSleep == 0) {
            tvSleepModeLastTime.setVisibility( View.INVISIBLE );
        } else {
            Date date = new Date( App.mGlobalSettings.lastSleep );
            SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy  hh:mm");
            tvSleepModeLastTime.setText( String.format(getString(R.string.text_sleep_mode_last_time), ft.format(date)) );
            tvSleepModeLastTime.setVisibility( View.VISIBLE );
        }

        tvWorkingStart = (TextView) findViewById(R.id.tv_working_start);
        Date date = new Date( App.mGlobalSettings.startDate );
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy  hh:mm");
        tvWorkingStart.setText( String.format(getString(R.string.text_working_start), ft.format(date)) );

        btnTestVolumeUp = (Button) findViewById(R.id.tst_volume_up);
        btnTestVolumeDown = (Button) findViewById(R.id.tst_volume_down);
        btnTestPressNext = (Button) findViewById(R.id.tst_svc_next);
        btnTestPressPrev = (Button) findViewById(R.id.tst_svc_prev);

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
                    int vol = App.mGlobalSettings.getVolumeLevel();
                    vol++;
                    App.mGlobalSettings.setVolumeLevel(vol, false);
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
                    int vol = App.mGlobalSettings.getVolumeLevel();
                    vol--;
                    App.mGlobalSettings.setVolumeLevel(vol, false);
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

            }
        });
        this.btnTestPressPrev.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) // клик на кнопку
            {

            }
        });


        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH));
        registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP));
	}

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        NotifyData notifyData = new NotifyData( );
        switch (id){
            case R.id.switch_show_notification_icon:
                Settings.System.putInt(this.getContentResolver(), NotifyData.OPTION_SHOW_NOTIFICATION_ICON, isChecked ? 1 : 0);
                App.mGlobalSettings.isNotificationIconShow = isChecked;


                //notifyData.NotifyID = App.mGlobalSettings.isVolumeShowOnNotificationIcon ? NotifyData.NOTIFY_VOLUME_CHANGED_ID : NotifyData.NOTIFY_ID;
                notifyData.smallIcon = (App.mGlobalSettings.isNotificationIconShow) ? R.drawable.notify_auto : 0;
                notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                notifyData.number = App.mGlobalSettings.isVolumeShowOnNotificationIcon ? App.mGlobalSettings.getVolumeLevel() : 0;
                ShowNotification(notifyData);
                break;
            case R.id.switch_show_volume_on_notification_icon:
                Settings.System.putInt(this.getContentResolver(), NotifyData.OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON, isChecked ? 1 : 0);
                App.mGlobalSettings.isVolumeShowOnNotificationIcon = isChecked;


                //notifyData.NotifyID = App.mGlobalSettings.isVolumeShowOnNotificationIcon ? NotifyData.NOTIFY_VOLUME_CHANGED_ID : NotifyData.NOTIFY_ID;
                notifyData.smallIcon = (App.mGlobalSettings.isNotificationIconShow) ? R.drawable.notify_auto : 0;
                notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                notifyData.number = App.mGlobalSettings.isVolumeShowOnNotificationIcon ? App.mGlobalSettings.getVolumeLevel() : 0;
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
                if ( App.mGlobalSettings.isVolumeShowOnNotificationIcon ) {
                    // возможно, надо не новое уведомление делать, а заменять старое
                    NotifyData notifyData = new NotifyData( );
                    //notifyData.NotifyID = isVolumeShowOnNotificationIcon ? NOTIFY_VOLUME_CHANGED_ID : NOTIFY_ID;
                    notifyData.smallIcon = App.mGlobalSettings.isNotificationIconShow ? R.drawable.notify_auto : 0;
                    notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                    notifyData.number = App.mGlobalSettings.isVolumeShowOnNotificationIcon ? vol : 0;
                    ShowNotification(notifyData);
                }
			}
            else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH ) )
            {
                tvReverseCount.setText( String.format(getString(R.string.text_reverse_count),
                                                      App.mGlobalSettings.ReverseActivityCount) );
            }
            else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP ) )
            {
                tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count),
                                                        App.mGlobalSettings.SleepModeCount) );
                if ( App.mGlobalSettings.lastSleep == 0)
                {
                    tvSleepModeLastTime.setVisibility( View.INVISIBLE );
                }
                else
                {
                    Date date = new Date( App.mGlobalSettings.lastSleep );
                    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy  HH:mm", Locale.US);
                    tvSleepModeLastTime.setText( String.format("%s", ft.format(date)) );
                    tvSleepModeLastTime.setVisibility( View.VISIBLE );
                }
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
        builder.setWhen( App.mGlobalSettings.startDate);
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


