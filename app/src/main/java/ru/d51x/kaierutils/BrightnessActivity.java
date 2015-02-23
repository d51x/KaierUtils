package ru.d51x.kaierutils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.provider.Settings.System;

public class BrightnessActivity extends Activity {

	SeekBar mBar;
	int curBrightness = 0;
	int curBrightnessMode = 0;

	private static final String BRIGHTNESS_PREFERENCE_KEY = "screen_brightness";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_brightness);

		curBrightness = App.mGlobalSettings.getBrightnessLevel ();
		curBrightnessMode = App.mGlobalSettings.getBrightnessMode ();
		DebugLogger.ToLog ("Log", String.format("Current brightness is %d", curBrightness));
		DebugLogger.ToLog ("Log", String.format("Current brightnessMode is %d", curBrightnessMode));

		mBar = (SeekBar) findViewById(R.id.id_seekBar_brightness);
		mBar.setProgress ( curBrightness );
		this.mBar.setOnSeekBarChangeListener (new SeekBar.OnSeekBarChangeListener () {

			@Override
			public void onStopTrackingTouch (SeekBar seekBar) {}

			@Override
			public void onStartTrackingTouch (SeekBar seekBar) {}

			@Override
			public void onProgressChanged (SeekBar seekBar, int progress,
			                               boolean fromUser) {
				DebugLogger.ToLog ("Log", progress + "");

				curBrightness = progress;
				System.putInt (App.getInstance ().getContentResolver (), BRIGHTNESS_PREFERENCE_KEY, curBrightness * 25);
				App.mGlobalSettings.setBrightnessLevel (curBrightness, true);
			}
		});

	}


}
