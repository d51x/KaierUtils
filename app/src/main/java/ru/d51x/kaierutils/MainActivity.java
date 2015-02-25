package ru.d51x.kaierutils;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
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

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{

	private Button btnSoundSettings;
    private TextView tvDeviceName;
    private TextView tvCurrentVolume;
    public static Handler mHandler;
    private Switch switch_show_notification_icon;
    private boolean isNotificationIconShow;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.main_activity);
		Log.d ("SettingsActivity", "onCreate");
		startService(new Intent(this, BackgroundService.class));
		Toast.makeText (this, "Service started", 0);

		btnSoundSettings = (Button) findViewById (R.id.id_button_sound_Settings);
        tvDeviceName = (TextView) findViewById(R.id.txtDeviceName);
        String string_device_name = String.format(getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

        tvCurrentVolume = (TextView) findViewById(R.id.tvCurrentVolum);
        String string_current_volume = String.format(getString(R.string.text_current_level), App.mGlobalSettings.getVolumeLevel());
        tvCurrentVolume.setText( string_current_volume );

        switch_show_notification_icon = (Switch) findViewById(R.id.switch_show_notification_icon);
        isNotificationIconShow = Settings.System.getInt(getContentResolver(), "kaierutils_show_notification_icon", 0) == 1;
        switch_show_notification_icon.setChecked(isNotificationIconShow);
        switch_show_notification_icon.setOnCheckedChangeListener(this);

        this.btnSoundSettings.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) // клик на кнопку
			{

				try {
					Intent it = new Intent();
					//Intent it = new Intent(this, SoundSettingsPreferenceActivity.class);
					it.setClassName("ru.d51x.kaierutils", "ru.d51x.kaierutils.SoundSettingsPreferenceActivity");
					it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(it);
				} catch (Exception e) {
				}

			}
		});


        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL:
                        String string_current_volume = String.format(getString(R.string.text_current_level), msg.arg1 & Integer.MAX_VALUE);
                        tvCurrentVolume.setText( string_current_volume );
                        break;
                    default:
                        break;
                }
            }
        };



	}

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id){
            case R.id.switch_show_notification_icon:
                Settings.System.putInt(this.getContentResolver(), "kaierutils_show_notification_icon", isChecked ? 1 : 0);


                Notification.Builder builder = new Notification.Builder(this);
                builder.setContentTitle( "KaierUtils" );
                builder.setAutoCancel(false);
                if ( isChecked ) builder.setSmallIcon( R.drawable.notify_auto );
                // setNumber
                // setPriority

                Notification notification = builder.build();

                notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(42 ,notification);

                break;
            default:
                break;
        }

    }
}


