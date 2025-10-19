package ru.d51x.kaierutils.Settings;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsReverseFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.reverse);

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
		if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__DO_CHANGE")) {
			App.GS.volumeOptions.isNeedSoundDecreaseAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL_AVAILABLE")) {
			App.GS.volumeOptions.isFixedVolumeAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL_AVAILABLE")) {
			App.GS.volumeOptions.isPercentVolumeAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL")) {
			App.GS.volumeOptions.fixedVolumeLevelAtReverse = sharedPreferences.getInt(key, 4);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL")) {
			App.GS.volumeOptions.percentVolumeLevelAtReverse = sharedPreferences.getInt(key, 40);
		}
	}
}
