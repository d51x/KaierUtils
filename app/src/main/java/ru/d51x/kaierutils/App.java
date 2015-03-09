package ru.d51x.kaierutils;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by Dmitriy on 18.02.2015.
 */

public class App extends Application {
	static App self;
	public static GlSets GS;
	public static RadioToast rToast;
	public static MusicToast mToast;

	private WindowManager.LayoutParams mWindowManagerLayoutParams = new WindowManager.LayoutParams();
	public WindowManager.LayoutParams getWindowManagerLayoutParams() {
		return mWindowManagerLayoutParams;
	}

	public static App getInstance() {
		return self;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d ("App", "onCreate");
		self = this;
		GS = new GlSets();
		this.startService (new Intent (this, BackgroundService.class));
		rToast = new RadioToast( self );
		mToast = new MusicToast( self );
	}

}
