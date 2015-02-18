package ru.d51x.kaierutils;

import android.app.Application;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class App extends Application {
	static App self;

	public static App getInstance() {
		return self;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		self = this;
	}

}
