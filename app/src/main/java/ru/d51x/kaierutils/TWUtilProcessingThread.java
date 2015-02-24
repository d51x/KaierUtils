package ru.d51x.kaierutils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class TWUtilProcessingThread extends Thread {

	private Handler handler;
	public TWUtilEx tw;



	public TWUtilProcessingThread () {
		super("TWUtilProcessingThread");
		tw = null;
	}

	@Override
	public void run() {
		try {
			Log.d ("TWUtilProcessingThread", "run()");
			Looper.prepare();
			handler = new Handler();
			if (( tw == null ) & ( TWUtilEx.isTWUtilAvailable() ) ) {
				tw = new TWUtilEx();
				tw.Init ();
			}
			Looper.loop();
		} catch (Exception e) {
			Log.d ("TWUtilProcessingThread", "ERROR: run() failed");
		}
	}

	public synchronized void tryStop() {
		Log.d ("TWUtilProcessingThread", "tryStop()");
		handler.post(new Runnable() {
			@Override
			public void run() {
				if ( tw != null){
					tw.Destroy ();
					tw = null;
				}
			Looper.myLooper().quit();
			}
		});
	}

}
