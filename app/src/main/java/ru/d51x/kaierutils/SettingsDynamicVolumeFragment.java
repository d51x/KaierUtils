package ru.d51x.kaierutils;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsDynamicVolumeFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.dynamic_volume);

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
		if ( key.equals ( "CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DO_CHAGE" )) {
			App.GS.dsc_isAvailable = sharedPreferences.getBoolean ( key, false);
		} else if (key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__FIRST_SPEED")) {
			App.GS.dsc_FirstSpeed = sharedPreferences.getInt (key, 40);
		} else if (key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_SPEED")) {
			App.GS.dsc_StepSpeed = sharedPreferences.getInt (key, 20);
		}  else if ( key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_VOLUME")) {
			App.GS.dsc_StepVolume = sharedPreferences.getInt (key, 1);
		} else if ( key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__TIME_TO_CHANGE")) {
			App.GS.dsc_TimeToChange = sharedPreferences.getInt (key, 10);
		} else if ( key.equals ("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DELTA_TO_CHANGE")) {
			App.GS.dsc_DeltaToChange = sharedPreferences.getInt (key, 5);
		}
	}
}
