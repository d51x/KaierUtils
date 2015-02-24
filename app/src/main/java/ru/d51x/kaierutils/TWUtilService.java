package ru.d51x.kaierutils;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;

public class TWUtilService extends IntentService {

	private TWUtilListenerThread twUtilListenerThread;

	public TWUtilService () {
		super("TWUtilService");
		twUtilListenerThread = null;
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

        //return super.onStartCommand(intent, flags, startId);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("KaierUtils")
                .setSmallIcon(R.drawable.notify_auto)
                .setAutoCancel(false)
                .build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(42 ,notification);
        startForeground(42, notification);

//        Notification note = new Notification( 0, null, System.currentTimeMillis() );
//        note.flags |= Notification.FLAG_NO_CLEAR;
//
//        startForeground( 42, note );

        return Service.START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		if ( ! GlobalSettings.IN_EMULATOR ) {
			if ( twUtilListenerThread == null ) {
				twUtilListenerThread = new TWUtilListenerThread();
				twUtilListenerThread.start();
			}
			// TODO: Запустить в приоритете, чтобы всегда висел в фоне. А надо ли?
		}
	}

}
