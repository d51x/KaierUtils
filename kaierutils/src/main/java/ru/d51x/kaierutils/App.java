package ru.d51x.kaierutils;

import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import ru.d51x.kaierutils.OBD2.Obd2;
import ru.d51x.kaierutils.Toasts.MusicToast;
import ru.d51x.kaierutils.Toasts.RadioToast;
import ru.d51x.kaierutils.Toasts.SensorsToast;

/**
 * Created by Dmitriy on 18.02.2015.
 */

public class App extends Application implements Application.ActivityLifecycleCallbacks {
	static App self;
	public static GlSets GS;
    public static Obd2 obd;
	public static RadioToast rToast;
	public static MusicToast mToast;
	public static SensorsToast sensorsToast;
	public static Toast gToast;

	private final static int REQUEST_ENABLE_BT = 1;
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
		Log.d ("App", "onCreate");
		self = this;
		GS = new GlSets();

		registerActivityLifecycleCallbacks(this);
		handler = new Handler(getMainLooper());

		boolean bluetoothAvailable = getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH);
		//boolean bluetoothLEAvailable = getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                BluetoothManager bluetoothManager = getSystemService(BluetoothManager.class);
				BluetoothAdapter bluetoothAdapter = bluetoothManager.getAdapter();
				if (bluetoothAdapter == null) {
					// Device doesn't support Bluetooth
				}
				if (!bluetoothAdapter.isEnabled()) {
					Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					//startActivityForResult(MainActivity, enableBtIntent, REQUEST_ENABLE_BT);
				}
            }
        }

        obd = new Obd2( self );
		//this.startService (new Intent (this, BackgroundService.class));
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			this.startForegroundService(new Intent(this, BackgroundService.class));
		} else {
			this.startService(new Intent(this, BackgroundService.class));
		}
		rToast = new RadioToast( self );
		mToast = new MusicToast( self );
        sensorsToast = new SensorsToast( self );
        gToast = new Toast( self );
		floatingWindow = new FloatingWindow(getApplicationContext(), App.GS.ui.floatingWindowVertical);
	}

	private Runnable appMinimize = new Runnable() {
		@Override
		public void run() {
			if (App.GS.ui.showFloatingOnMinimize) {
				App.floatingWindow.show();
			}
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
