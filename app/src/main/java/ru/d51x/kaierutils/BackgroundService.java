package ru.d51x.kaierutils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.NotificationManager;

public class BackgroundService extends Service {

	private TWUtilProcessingThread twUtilProcessingThread;
    private PowerAmpProcessingThread powerAmpProcessingThread;
    private GpsProcessingThread gpsProcessingThread;

	public BackgroundService () {
		twUtilProcessingThread = null;
        powerAmpProcessingThread = null;
        gpsProcessingThread = null;
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
        NotifyData notifyData = new  NotifyData( getApplicationContext() );
        notifyData.NotifyID = NotifyData.NOTIFY_ID;
        notifyData.Title = NotifyData.NOTIFICATION_TITLE;
        notifyData.smallIcon = App.mGlSets.isNotificationIconShow ? R.drawable.notify_auto : 0;
        notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        Notification notification = notifyData.show();
        startForeground( NotifyData.NOTIFY_ID, notification);

		if ((flags & START_FLAG_RETRY) == 0) {
			// TODO Если это повторный запуск, выполнить какие-то действия.
			Log.d ("BackgroundService", "Second attempt or more...");
		}
		else {
			// TODO Альтернативные действия в фоновом режиме.
			Log.d ("BackgroundService", "First attempt ");
		}

		startTWUtilProcessingThread();
		startPowerAmpProcessingThread();
		startRadioProcessingThread();
        startGpsProcessingThread();

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
        powerAmpProcessingThread = new PowerAmpProcessingThread ();
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

	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "KaierUtils is stopped", Toast.LENGTH_LONG).show();
		Log.d ("BackgroundService", "onDestroy");
		stopForeground(true);
        stopGpsProcessingThread();
		stopRadioProcessingThread();
		stopPowerAmpProcessingThread();
		stopTWUtilProcessingThread();
		super.onDestroy();
	}

}

