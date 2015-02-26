package ru.d51x.kaierutils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class PowerAmpProcessingThread extends Thread {

	private Handler handler;




	public PowerAmpProcessingThread() {
		super("PowerAmpProcessingThread");

	}

	@Override
	public void run() {
		try {
			Log.d ("PowerAmpProcessingThread", "run()");
			Looper.prepare();
			handler = new Handler();

			Looper.loop();
		} catch (Exception e) {
			Log.d ("PowerAmpProcessingThread", "ERROR: run() failed");
		}
	}

	public synchronized void tryStop() {
		Log.d ("PowerAmpProcessingThread", "tryStop()");
		handler.post(new Runnable() {
			@Override
			public void run() {

			Looper.myLooper().quit();
			}
		});
	}

}
