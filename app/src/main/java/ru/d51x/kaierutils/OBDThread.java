package ru.d51x.kaierutils;

import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class OBDThread extends Thread {

	public OBDThread() {
        super("OBDThread");
        isActive = true;
    }
    public volatile boolean isActive = false;

	@Override
	public void run() {
		try {
			Log.d ("OBDThread", "run()");
            //while (true) {
            while (!Thread.currentThread().isInterrupted() || isActive )
            {
                if ( App.obd.isConnected) {
                    // send or process data
                    App.obd.processData();
                } else {
                    //try to connect
                        App.obd.isConnected = App.obd.connect();
                        if ( App.obd.isConnected ) {
                                App.obd.prepareData();
                        } else {
                            Thread.sleep(3000);
                        }
                }
            }
            App.obd.disconnect();
		} catch (Exception e) {
			Log.d ("OBDThread", "ERROR: run() failed");
		}
	}

	public synchronized void finish() {
		Log.d ("OBDThread", "finish()");
        interrupt();
        isActive = false;
        App.obd.disconnect();

	}
}


