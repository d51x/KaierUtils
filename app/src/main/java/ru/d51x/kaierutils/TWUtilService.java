package ru.d51x.kaierutils;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
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
		return super.onStartCommand(intent, flags, startId);
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
