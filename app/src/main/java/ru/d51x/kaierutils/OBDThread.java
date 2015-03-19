package ru.d51x.kaierutils;

import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class OBDThread extends Thread {

	public OBDThread() {	super("OBDThread");	}

	@Override
	public void run() {
		try {
			Log.d ("OBDThread", "run()");
            //while (true) {
            while (!Thread.currentThread().isInterrupted())
            {
                if ( App.obd.isConnected) {
                    // send or process data
                    App.obd.processData();
                } else {
                    //try to connect
                        App.obd.connect();
                        if ( App.obd.isConnected ) {
                            App.obd.init();
                            App.obd.prepareData();
                        } else {
                            Thread.sleep(5000);
                        }
                }
            }
		} catch (Exception e) {
			Log.d ("OBDThread", "ERROR: run() failed");
		}
	}

	public synchronized void finish() {
		Log.d ("OBDThread", "finish()");
        App.obd.disconnect();
	}
}


