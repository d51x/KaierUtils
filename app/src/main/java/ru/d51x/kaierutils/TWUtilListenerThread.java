package ru.d51x.kaierutils;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class TWUtilListenerThread extends Thread {

	private Handler handler;
	public TWUtilEx tw;



	public TWUtilListenerThread() {
		super("TWUtilListenerThread");
		tw = null;
	}

	@Override
	public void run() {
		Log.d ("TWUtilListenerThread", "run()");
		try {
			Looper.prepare();
			handler = new Handler();
			if ( tw == null) {
				tw = new TWUtilEx();
				tw.Init ();
			}
			Looper.loop();
		} catch (Exception e) {

		}
	}

//	public synchronized void tryStop() {
//		Log.d ("TWUtilListenerThread", "tryStop()");
//		handler.post(new Runnable() {
//			@Override
//			public void run() {
////				if ( tw != null){
////					tw.Destroy ();
////					tw = null;
////				}
//				//Looper.myLooper().quit();
//			}
//		});
//	}

}
