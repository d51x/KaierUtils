package ru.d51x.kaierutils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsReverseFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.reverse);

		Context context = App.getInstance ();
		SharedPreferences prefs =
				PreferenceManager.getDefaultSharedPreferences(context);
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
			App.GS.isNeedSoundDecreaseAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL_AVAILABLE")) {
			App.GS.isFixedVolumeAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL_AVAILABLE")) {
			App.GS.isPercentVolumeAtReverse = sharedPreferences.getBoolean (key, false);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL")) {
			App.GS.FixedVolumeLevelAtReverse = sharedPreferences.getInt(key, 4);
		} else if ( key.equals ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL")) {
			App.GS.PercentVolumeLevelAtReverse = sharedPreferences.getInt(key, 40);
		}
	}
}
