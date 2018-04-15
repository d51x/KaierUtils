package ru.d51x.kaierutils.Settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsRadioFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.radio);

		Context context = App.getInstance();
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
		if ( key.equals ( "CAR_SETTINGS__RADIO_SHOW_TOAST" )) {
			App.GS.radio.showToast = sharedPreferences.getBoolean ( key, false);
		} else if ( key.equals ( "CAR_SETTINGS__RADIO_SKIP_SEEKING_MODE" )) {
            App.GS.radio.skipSeekingMode = sharedPreferences.getBoolean ( key, true);
        } else if ( key.equals ( "CAR_SETTINGS__RADIO_SHOW_INFO" )) {
            App.GS.radio.showInfo = sharedPreferences.getBoolean ( key, false);
        }
        else if ( key.equals ( "CAR_SETTINGS__RADIO_SHOW_TOAST_2" )) {
            App.GS.radio.dontShowToastOnMainActivity = sharedPreferences.getBoolean ( key, true);
        }

	}
}
