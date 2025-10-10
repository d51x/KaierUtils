package ru.d51x.kaierutils.Settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public class SettingsFloatingWindowFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.floating_panel);

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
		if ( key.equals ( "floating_panel_show_speed" )) {
			App.floatingWindow.showSpeed = sharedPreferences.getBoolean ( key, true);
		}
		else if ( key.equals ( "floating_panel_show_battery_level" )) {
			App.floatingWindow.showBatteryLevel = sharedPreferences.getBoolean ( key, true);
		}
		else if ( key.equals ( "floating_panel_show_coolant_temperature" )) {
			App.floatingWindow.showCoolantTemperature = sharedPreferences.getBoolean ( key, true);
		}
		else if ( key.equals ( "floating_panel_show_cvt_temperature" )) {
			App.floatingWindow.showCvtTemperature = sharedPreferences.getBoolean ( key, true);
		}
		else if ( key.equals ( "floating_panel_show_fuel_level" )) {
			App.floatingWindow.showFuelLevel = sharedPreferences.getBoolean ( key, true);
		}
		else if ( key.equals ( "floating_panel_show_fuel_consumption" )) {
			App.floatingWindow.showFuelConsumption = sharedPreferences.getBoolean ( key, true);
		}
		else if ( key.equals ( "floating_panel_show_distance" )) {
			App.floatingWindow.showDistance = sharedPreferences.getBoolean ( key, true);
		}
	}
}
