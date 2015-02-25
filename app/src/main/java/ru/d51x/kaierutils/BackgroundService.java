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

	public BackgroundService () {
		//super("TWUtilService");
		twUtilProcessingThread = null;
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
        Notification notification = makeNotification(MainActivity.NOTIFY_ID, "KaierUtils", "", R.drawable.notify_auto);
		startForeground( MainActivity.NOTIFY_ID, notification);
		Toast.makeText(getApplicationContext(), "KaierUtils is started as foreground service", Toast.LENGTH_LONG).show();

		if ((flags & START_FLAG_RETRY) == 0) {
			// TODO Если это повторный запуск, выполнить какие-то действия.
			Log.d ("BackgroundService", "Second attempt or more...");
		}
		else {
			// TODO Альтернативные действия в фоновом режиме.
			Log.d ("BackgroundService", "First attempt ");
		}

		// starting TWUtilProcessingThread
		startTWUtilProcessingThread();

		// starting PowerAmpProcessingThread
		startPowerAmpProcessingThread();

		// starting RadioProcessingThread
		startRadioProcessingThread();

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
		twUtilProcessingThread.tryStop();
		twUtilProcessingThread = null;
	}

	private synchronized void startPowerAmpProcessingThread(){
	}

	private synchronized void stopPowerAmpProcessingThread(){
	}

	private synchronized void startRadioProcessingThread(){
	}

	private synchronized void stopRadioProcessingThread(){
	}

	@Override
	public void onDestroy() {
		Toast.makeText(getApplicationContext(), "KaierUtils is stopped", Toast.LENGTH_LONG).show();
		Log.d ("BackgroundService", "onDestroy");
		stopForeground(true);
		stopRadioProcessingThread();
		stopPowerAmpProcessingThread();
		stopTWUtilProcessingThread();
		super.onDestroy();
	}

	public Notification makeNotification(int notifyId, String Title, String Text, int smallIcon) {
        boolean showicon = Settings.System.getInt(getContentResolver(), "kaierutils_show_notification_icon", 0) == 1;

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle( Title );
        builder.setAutoCancel(false);
        if ( showicon ) builder.setSmallIcon( smallIcon );
		Notification notification = builder.build();

		notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);
		notification.setLatestEventInfo(this, Title, Text, pendingIntent);

		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify (notifyId, notification);
		return notification;
	}
}

