package ru.d51x.kaierutils.Settings;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsSleepWakeUpFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.sleep_wakeup);

		Context context = App.getInstance();
		SharedPreferences prefs = getDefaultSharedPreferences(context);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	public void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	public void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if ( key.equals ( "CAR_SETTINGS__VOLUME_AT_START_UP__DO_CHANGE" )) {
			App.GS.volumeOptions.isNeedSoundDecreaseAtStartUp = sharedPreferences.getBoolean ( key, false);
		} else if (key.equals ("CAR_SETTINGS__VOLUME_AT_START_UP__LEVEL")) {
			App.GS.volumeOptions.volumeLevelAtStartUp = sharedPreferences.getInt (key, 3);
		} else if (key.equals ("CAR_SETTINGS__VOLUME_AT_WAKE_UP__DO_CHANGE")) {
			App.GS.volumeOptions.isNeedSoundDecreaseAtWakeUp = sharedPreferences.getBoolean (key, false);
		} else if (key.equals ("CAR_SETTINGS__VOLUME_AT_WAKE_UP__LEVEL")) {
			App.GS.volumeOptions.volumeLevelAtWakeUp = sharedPreferences.getInt (key, 3);
		}
	}
}
