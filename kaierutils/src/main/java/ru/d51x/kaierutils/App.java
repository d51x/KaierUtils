package ru.d51x.kaierutils;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_PARKING_2101_CHANGED;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import ru.d51x.kaierutils.OBD2.Obd2;
import ru.d51x.kaierutils.OBD2.ObdBroadcastReceiver;
import ru.d51x.kaierutils.Toasts.SensorsToast;

/**
 * Created by Dmitriy on 18.02.2015.
 */

public class App extends Application implements Application.ActivityLifecycleCallbacks {
	static App self;
	public static GlSets GS;
    public static Obd2 obd;
	@SuppressLint("StaticFieldLeak")
    public static SensorsToast sensorsToast;
	public static Toast gToast;
    private final ObdBroadcastReceiver obdBroadcastReceiver = new ObdBroadcastReceiver();
	private final static int REQUEST_ENABLE_BT = 1;
	@SuppressLint("StaticFieldLeak")
    public static FloatingWindow floatingWindow;

	private WindowManager.LayoutParams mWindowManagerLayoutParams = new WindowManager.LayoutParams();
	public WindowManager.LayoutParams getWindowManagerLayoutParams() {
		return mWindowManagerLayoutParams;
	}
	private Handler handler;

	public static App getInstance() {
		return self;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d("App", "onCreate");
		self = this;
		GS = new GlSets();

		registerActivityLifecycleCallbacks(this);
		handler = new Handler(getMainLooper());

		BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
		BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
		if (bluetoothAdapter != null) {
			if (!bluetoothAdapter.isEnabled()) {
				Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
				//startActivityForResult(MainActivity, enableBtIntent, REQUEST_ENABLE_BT);
			}
		}
        obd = new Obd2( self );
        this.startForegroundService(new Intent(this, BackgroundService.class));
        sensorsToast = new SensorsToast( self );
        gToast = new Toast( self );
		floatingWindow = new FloatingWindow(getApplicationContext(), App.GS.ui.floatingWindowVertical);

		getApplicationContext().registerReceiver(obdBroadcastReceiver, new IntentFilter(ACTION_OBD_PARKING_2101_CHANGED));
	}

	private final Runnable appMinimize = () -> {
        if (App.GS.ui.showFloatingOnMinimize) {
            App.floatingWindow.show();
        }
    };

	@Override
	public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
		handler.removeCallbacks(appMinimize);
		if (App.GS.ui.showFloatingOnMinimize) {
			App.floatingWindow.dismiss();
		}
	}

	@Override
	public void onActivityStarted(Activity activity) {
		handler.removeCallbacks(appMinimize);
		if (App.GS.ui.showFloatingOnMinimize) {
			App.floatingWindow.dismiss();
		}
	}

	@Override
	public void onActivityResumed(Activity activity) {
		handler.removeCallbacks(appMinimize);
		if (App.GS.ui.showFloatingOnMinimize) {
			App.floatingWindow.dismiss();
		}
	}

	@Override
	public void onActivityPaused(Activity activity) {
		handler.postDelayed(appMinimize, 500);
	}

	@Override
	public void onActivityStopped(Activity activity) {

	}

	@Override
	public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

	}

	@Override
	public void onActivityDestroyed(Activity activity) {

	}
}
