package ru.d51x.kaierutils;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.Context;
import android.preference.PreferenceManager;

public class SoundSettingsPreferenceActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	//private static final boolean ALWAYS_SIMPLE_PREFS = false;


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.settings);

		Context context = getApplicationContext();
		SharedPreferences prefs =
				PreferenceManager.getDefaultSharedPreferences(context);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}

	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if ( key.equals ( "CAR_SETTINGS__VOLUME_AT_START_UP__DO_CHANGE" )) {
			App.mGlSets.isNeedSoundDecreaseAtStartUp = sharedPreferences.getBoolean ( key, false);
		} else if (key.equals ("CAR_SETTINGS__VOLUME_AT_START_UP__LEVEL")) {
			App.mGlSets.VolumeLevelAtStartUp = sharedPreferences.getInt (key, 3);
		} else if (key.equals ("CAR_SETTINGS__VOLUME_AT_WAKE_UP__DO_CHANGE")) {
			App.mGlSets.isNeedSoundDecreaseAtWakeUp = sharedPreferences.getBoolean (key, false);
		} else if (key.equals ("CAR_SETTINGS__VOLUME_AT_WAKE_UP__LEVEL")) {
			App.mGlSets.VolumeLevelAtWakeUp = sharedPreferences.getInt (key, 3);
		}  else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__DO_CHANGE")) {
			App.mGlSets.isNeedSoundDecreaseAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL_AVAILABLE")) {
			App.mGlSets.isFixedVolumeAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL_AVAILABLE")) {
			App.mGlSets.isPercentVolumeAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL")) {
			App.mGlSets.FixedVolumeLevelAtReverse = sharedPreferences.getInt(key, 3);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL")) {
			App.mGlSets.PercentVolumeLevelAtReverse = sharedPreferences.getInt(key, 30);
		}
	}

}
