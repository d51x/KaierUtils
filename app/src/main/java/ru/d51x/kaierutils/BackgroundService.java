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

    private MainActivity mainActivity;
	private TWUtilProcessingThread twUtilProcessingThread;
    private PowerAmpProcessingThread powerAmpProcessingThread;

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
//        NotifyData notifyData = new  NotifyData();
//        notifyData.smallIcon = App.mGlobalSettings.isNotificationIconShow ? R.drawable.notify_auto : 0;
//        notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
//        notifyData.number = App.mGlobalSettings.isVolumeShowOnNotificationIcon ? App.mGlobalSettings.getVolumeLevel() : 0;
//        Notification notification = ShowNotification(notifyData);
//		startForeground( notifyData.NOTIFY_ID, notification);
        Notification notification = makeNotification(NotifyData.NOTIFY_ID, NotifyData.NOTIFICATION_TITLE, "", R.drawable.notify_auto);
        startForeground( NotifyData.NOTIFY_ID, notification);
		//Toast.makeText(getApplicationContext(), "KaierUtils is started as foreground service", Toast.LENGTH_LONG).show();

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
        Log.d ("BackgroundService", "startPowerAmpProcessingThread");
        if ( powerAmpProcessingThread != null ) return;
        powerAmpProcessingThread = new PowerAmpProcessingThread ();
        powerAmpProcessingThread.start();
    }

	private synchronized void stopPowerAmpProcessingThread(){
        Log.d ("BackgroundService", "stopPowerAmpProcessingThread");
        if ( powerAmpProcessingThread == null) return;
        powerAmpProcessingThread.tryStop();
        powerAmpProcessingThread = null;
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

        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setContentTitle( Title );
        builder.setAutoCancel(false);
        if ( showicon ) builder.setSmallIcon( smallIcon );
        Notification notification = builder.build();

        notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(getApplicationContext(), MainActivity.class), 0);
        notification.setLatestEventInfo(getApplicationContext(), Title, Text, pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify (notifyId, notification);
        return notification;
    }

}

