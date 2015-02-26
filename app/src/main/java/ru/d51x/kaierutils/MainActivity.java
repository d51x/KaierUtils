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

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{

	public static final int NOTIFY_ID = 1;
    public static final int NOTIFY_VOLUME_CHANGED_ID = 2;
    public static final String NOTIFICATION_TITLE = "KaierUtils";
    public static final String OPTION_SHOW_NOTIFICATION_ICON = "kaierutils_show_notification_icon";
    public static final String OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON = "kaierutils_show_volume_on_notification_icon";

	private Button btnSoundSettings;
    private TextView tvDeviceName;
    private TextView tvCurrentVolume;
    public static Handler mHandler;
    private Switch switch_show_notification_icon;
    private boolean isNotificationIconShow;
    private Switch switch_show_volume_on_notification_icon;
    private boolean isVolumeShowOnNotificationIcon;
    private TextView tvReverseCount;
    private TextView tvSleepModeCount;
    private TextView tvSleepModeLastTime;
    private TextView tvWorkingStart;

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
        tvDeviceName = (TextView) findViewById(R.id.txtDeviceName);
        String string_device_name = String.format(getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

        tvCurrentVolume = (TextView) findViewById(R.id.tvCurrentVolum);
        tvCurrentVolume.setText( String.format(getString(R.string.text_current_level), App.mGlobalSettings.getVolumeLevel()) );

        switch_show_notification_icon = (Switch) findViewById(R.id.switch_show_notification_icon);
        isNotificationIconShow = Settings.System.getInt(getContentResolver(), OPTION_SHOW_NOTIFICATION_ICON, 0) == 1;
        switch_show_notification_icon.setChecked(isNotificationIconShow);
        switch_show_notification_icon.setOnCheckedChangeListener(this);

        switch_show_volume_on_notification_icon = (Switch) findViewById(R.id.switch_show_volume_on_notification_icon);
        isVolumeShowOnNotificationIcon = Settings.System.getInt(getContentResolver(), OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON, 0) == 1;
        switch_show_volume_on_notification_icon.setChecked(isVolumeShowOnNotificationIcon);
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

		registerReceiver(receiver, new IntentFilter(TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED));
	}

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id){
            case R.id.switch_show_notification_icon:
                Settings.System.putInt(this.getContentResolver(), OPTION_SHOW_NOTIFICATION_ICON, isChecked ? 1 : 0);
                isNotificationIconShow = isChecked;

                NotifyData notifyData = new NotifyData();
                notifyData.NotifyID = NOTIFY_ID;
                notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                notifyData.smallIcon = isChecked ? R.drawable.notify_auto : -1;
                ShowNotification(notifyData);
                break;
            case R.id.switch_show_volume_on_notification_icon:
                Settings.System.putInt(this.getContentResolver(), OPTION_SHOW_VOLUME_ON_NOTIFICATION_ICON, isChecked ? 1 : 0);
                isVolumeShowOnNotificationIcon = isChecked;
                // возможно, надо не новое уведомление делать, а заменять старое
                // при этом учитывать условия отображения
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
                if ( isVolumeShowOnNotificationIcon ) {
                    // возможно, надо не новое уведомление делать, а заменять старое
                    NotifyData notifyData = new NotifyData();
                    notifyData.flags = Notification.FLAG_ONGOING_EVENT;
                    notifyData.NotifyID = NOTIFY_VOLUME_CHANGED_ID;
                    notifyData.number = vol;
                    ShowNotification(notifyData);
                }
			} else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START ) ) {
                tvReverseCount.setText( String.format(getString(R.string.text_reverse_count), App.mGlobalSettings.ReverseActivityCount) );
            } else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP ) ) {
                tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count), App.mGlobalSettings.SleepModeCount) );
                if ( App.mGlobalSettings.lastSleep == 0) {
                    tvSleepModeLastTime.setVisibility( View.INVISIBLE );
                } else {
                    Date date = new Date( App.mGlobalSettings.lastSleep );
                    SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy  hh:mm");
                    tvSleepModeLastTime.setText( String.format("%s", ft.format(date)) );
                    tvSleepModeLastTime.setVisibility( View.VISIBLE );
                }
            }
		}
	};

    private Notification ShowNotification(NotifyData notifyData){
        if ( notifyData == null ) return null;
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle( notifyData.Title );
        builder.setContentText( notifyData.Text );
        builder.setAutoCancel(false);
        builder.setWhen( App.mGlobalSettings.startDate);
        if ( notifyData.smallIcon > 0 ) builder.setSmallIcon( notifyData.smallIcon );
        Notification notification = builder.build();
        notification.flags |= notifyData.flags;

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if ( notifyData.ActivityClass != null) {
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, notifyData.ActivityClass), Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            notification.setLatestEventInfo(this,  notifyData.Title, notifyData.Text, pendingIntent);
        }
        notificationManager.notify ( notifyData.NotifyID, notification);
        return notification;
    }

	@Override
//	protected void onStop() {
//		//Отписываемся от событий сервиса
//        // Возможно, тут падает
//		unregisterReceiver(receiver);
//		super.onStop();
//	}

    protected void onDestroy() {
        //Отписываемся от событий сервиса
        // Возможно, тут падает
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    public class NotifyData {
        public int NotifyID;
        public int smallIcon; // -1 - нет иконки
        public String Title;
        public String Text;
        public int volumeLevel;
        public int flags;
        public Class ActivityClass;
        public int number;

        public void NotifyData() {
            NotifyID = MainActivity.NOTIFY_ID;
            smallIcon = R.drawable.notify_auto;
            Title = NOTIFICATION_TITLE;
            Text = "";
            volumeLevel = -1;
            flags =  0;
            ActivityClass = MainActivity.class;
            number = -1;
        }
    }
}


