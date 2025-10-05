package ru.d51x.kaierutils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import ru.d51x.kaierutils.GPS.GpsProcessingThread;
import ru.d51x.kaierutils.OBD2.OBDThread;
import ru.d51x.kaierutils.PowerAmp.PowerAmpProcessingThread;
import ru.d51x.kaierutils.TWUtils.TWUtilProcessingThread;

public class BackgroundService extends Service {
	private static Integer NOTIFICATION_SERVICE_ID = 1;
	private static String NOTIFICATION_CHANNEL_ID = "KaierUtils";
	private TWUtilProcessingThread twUtilProcessingThread;
    private PowerAmpProcessingThread powerAmpProcessingThread;
    private GpsProcessingThread gpsProcessingThread;
    public static OBDThread obdiiThread;

	private int startCount = -1;

	public BackgroundService () {
		//twUtilProcessingThread = null;
        powerAmpProcessingThread = null;
        gpsProcessingThread = null;
        obdiiThread = null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public IBinder onBind (Intent intent) {
		return null;
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d ("BackgroundService", "onStartCommand");

		startCount = startId;

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
		App.GS.uiOptions.isNotificationIconShow = prefs.getBoolean ("kaierutils_show_notification_icon", true);
        App.GS.curAudioFocusID = prefs.getInt("last_audio_focus_id", -1);



		NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "KaierUtils", NotificationManager.IMPORTANCE_HIGH);
		channel.setDescription("KaierUtils background service");
		NotificationManager manager = getSystemService(NotificationManager.class);
		manager.createNotificationChannel(channel);

		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
		Notification notification = notificationBuilder.setOngoing(true)
				.setContentTitle("KaierUtils")
				.setContentText("KaierUtils")
				.setAutoCancel(false)
				.setPriority(NotificationManager.IMPORTANCE_HIGH)
				.setCategory(Notification.CATEGORY_SERVICE)
				.setSmallIcon(App.GS.uiOptions.isNotificationIconShow ? R.drawable.notify_auto : 0)
				.setWhen(System.currentTimeMillis())
				.build();
		startForeground( NOTIFICATION_SERVICE_ID, notification);


		if ((flags & START_FLAG_RETRY) == 0) {
			// TODO Если это повторный запуск, выполнить какие-то действия.
			Log.d ("BackgroundService", "Second attempt or more...");
		}
		else {
			// TODO Альтернативные действия в фоновом режиме.
			Log.d ("BackgroundService", "First attempt ");

		}

		//startTWUtilProcessingThread();
		//startPowerAmpProcessingThread();
		startRadioProcessingThread();
        startGpsProcessingThread();
        startOBDThread();

        return Service.START_STICKY;
	}

	private synchronized void startTWUtilProcessingThread(){
		Log.d ("BackgroundService", "startTWUtilProcessingThread");
		if ( twUtilProcessingThread != null ) return;
		twUtilProcessingThread = new TWUtilProcessingThread ();
		twUtilProcessingThread.start();
	}

	private synchronized void stopTWUtilProcessingThread(){
		Log.d ("BackgroundService", "stopTWUtilProcessingThread");
		if ( twUtilProcessingThread == null) return;
		twUtilProcessingThread.finish();
		twUtilProcessingThread = null;
	}

	private synchronized void startPowerAmpProcessingThread(){
        Log.d ("BackgroundService", "startPowerAmpProcessingThread");
        if ( powerAmpProcessingThread != null ) return;
        powerAmpProcessingThread = new PowerAmpProcessingThread ( startCount );
        powerAmpProcessingThread.start();
    }

	private synchronized void stopPowerAmpProcessingThread(){
        Log.d ("BackgroundService", "stopPowerAmpProcessingThread");
        if ( powerAmpProcessingThread == null) return;
        powerAmpProcessingThread.finish();
        powerAmpProcessingThread = null;
	}

	private synchronized void startRadioProcessingThread(){
	}

	private synchronized void stopRadioProcessingThread(){
	}

    private synchronized void startGpsProcessingThread(){
        Log.d ("BackgroundService", "startGpsProcessingThread");
        if ( gpsProcessingThread != null ) return;
        gpsProcessingThread = new GpsProcessingThread ();
        gpsProcessingThread.start();
    }

    private synchronized void stopGpsProcessingThread(){
        Log.d ("BackgroundService", "stopGpsProcessingThread");
        if ( gpsProcessingThread == null) return;
        gpsProcessingThread.finish();
        gpsProcessingThread = null;
    }


    public static synchronized void startOBDThread(){
        Log.d ("BackgroundService", "startOBDThread");
        if ( obdiiThread != null ) return;
        obdiiThread = new OBDThread ();
        obdiiThread.start();
    }

    public static synchronized void stopOBDThread(){
        Log.d ("BackgroundService", "stopOBDThread");
        if ( obdiiThread == null) return;
        obdiiThread.finish();

        obdiiThread = null;
    }

	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "KaierUtils is stopped", Toast.LENGTH_LONG).show();
		Log.d ("BackgroundService", "onDestroy");
		stopForeground(true);
        stopGpsProcessingThread();
		stopRadioProcessingThread();
		stopPowerAmpProcessingThread();
		stopTWUtilProcessingThread();
        stopOBDThread();
		super.onDestroy();
	}

}

