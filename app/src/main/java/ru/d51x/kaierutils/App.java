package ru.d51x.kaierutils;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Dmitriy on 18.02.2015.
 */

public class App extends Application {
	static App self;
	public static GlSets mGlSets;
	public static App getInstance() {
		return self;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d ("App", "onCreate");
		self = this;
		mGlSets = new GlSets();
		this.startService (new Intent (this, BackgroundService.class));
	}

}
