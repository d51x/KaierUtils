package ru.d51x.kaierutils;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

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
		Toast.makeText(getApplicationContext(), "KaierUtils: boot up complete", Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		if ( twUtilListenerThread != null) {
			twUtilListenerThread.tryStop();
			twUtilListenerThread = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		if ( twUtilListenerThread == null ) {
			twUtilListenerThread = new TWUtilListenerThread();
			twUtilListenerThread.start();
		}
		// TODO: Запустить в приоритете, чтобы всегда висел в фоне. А надо ли?
	}

}
