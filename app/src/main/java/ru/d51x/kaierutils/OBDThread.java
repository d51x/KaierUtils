package ru.d51x.kaierutils;

import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class OBDThread extends Thread {

    private long TimeStamp1, TimeStamp2;

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
            TimeStamp1 = System.currentTimeMillis();
            TimeStamp2 = System.currentTimeMillis();

            while (!Thread.currentThread().isInterrupted() || isActive )
            {
                if ( App.obd.isConnected) {
                    // send or process data

                    App.obd.processData();
                    //Thread.sleep(500);

                    TimeStamp2 = System.currentTimeMillis();
                    if ( (TimeStamp2 - TimeStamp1) > (1000*60*10)) // 10 min
                    {
                        App.obd.oneTrip.saveData();
                        App.obd.totalTrip.saveData();
                        TimeStamp1 = TimeStamp2;
                    }

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


