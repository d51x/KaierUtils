package ru.d51x.kaierutils;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */

public class App extends Application {
	static App self;

	public static GlobalSettings mGlobalSettings;
	public static App getInstance() {
		return self;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d ("App", "onCreate");
		self = this;
		mGlobalSettings = new GlobalSettings();
		this.startService (new Intent (this, TWUtilService.class));
	}

}
