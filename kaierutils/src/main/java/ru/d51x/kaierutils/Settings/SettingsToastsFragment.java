package ru.d51x.kaierutils.Settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsToastsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.toasts);

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
		if ( key.equals ( "CAR_SETTINGS__RADIO_TOAST_TEXT1_SIZE" )) {
			App.GS.popupWindowOption.radioToastLine1TextSize = Integer.parseInt(sharedPreferences.getString ( key, "48"));
		} else if ( key.equals ( "CAR_SETTINGS__RADIO_TOAST_TEXT2_SIZE" )) {
			App.GS.popupWindowOption.radioToastLine2TextSize = Integer.parseInt(sharedPreferences.getString ( key, "25"));
		} else if ( key.equals ( "CAR_SETTINGS__MUSIC_TOAST_TEXT1_SIZE" )) {
			App.GS.popupWindowOption.musicToastLine1TextSize = Integer.parseInt(sharedPreferences.getString ( key, "32"));
		} else if ( key.equals ( "CAR_SETTINGS__MUSIC_TOAST_TEXT2_SIZE" )) {
			App.GS.popupWindowOption.musicToastLine2TextSize = Integer.parseInt(sharedPreferences.getString ( key, "22"));
		} else if ( key.equals ( "CAR_SETTINGS__MUSIC_TOAST_PICTURE_WIDTH" )) {
			App.GS.popupWindowOption.musicToastPictureWidth = Integer.parseInt(sharedPreferences.getString ( key, "128"));
		} else if ( key.equals ( "CAR_SETTINGS__MUSIC_TOAST_PICTURE_HEIGHT" )) {
			App.GS.popupWindowOption.musicToastPictureHeight = Integer.parseInt(sharedPreferences.getString ( key, "128"));
		}
	}
}
