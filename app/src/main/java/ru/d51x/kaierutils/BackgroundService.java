package ru.d51x.kaierutils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;
import android.app.Notification;

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

        Notification notification = makeNotification(42, "KaierUtils", "", R.drawable.notify_auto);
		startForeground(42, notification);
		Toast.makeText(getApplicationContext(), "KaierUtils is started as foreground service", Toast.LENGTH_LONG).show();

		if ((flags & START_FLAG_RETRY) == 0) {
			// TODO Если это повторный запуск, выполнить какие-то действия.
		}
		else {
			// TODO Альтернативные действия в фоновом режиме.
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
		if ( twUtilProcessingThread != null ) return;
		twUtilProcessingThread = new TWUtilProcessingThread ();
		twUtilProcessingThread.start();
	}

	private synchronized void stopTWUtilProcessingThread(){
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
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(notifyId ,notification);
		return notification;
	}
}

