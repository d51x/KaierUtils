package ru.d51x.kaierutils.Settings;


import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_ICON_SIZE;
import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_MAIN_TEXT_SIZE;
import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_SECOND_TEXT_SIZE;
import static ru.d51x.kaierutils.FloatingWindow.DEFAULT_UNITS_TEXT_SIZE;

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
		if ( "floating_panel_show_speed".equals(key)) {
			App.floatingWindow.showSpeed = sharedPreferences.getBoolean ( key, true);
		}
		else if ("floating_panel_show_battery_level".equals(key)) {
			App.floatingWindow.showBatteryLevel = sharedPreferences.getBoolean ( key, true);
		}
		else if ("floating_panel_show_coolant_temperature".equals(key)) {
			App.floatingWindow.showCoolantTemperature = sharedPreferences.getBoolean ( key, true);
		}
		else if ("floating_panel_show_cvt_temperature".equals(key)) {
			App.floatingWindow.showCvtTemperature = sharedPreferences.getBoolean ( key, true);
		}
		else if ("floating_panel_show_fuel_level".equals(key)) {
			App.floatingWindow.showFuelLevel = sharedPreferences.getBoolean ( key, true);
		}
		else if ("floating_panel_show_fuel_consumption".equals(key)) {
			App.floatingWindow.showFuelConsumption = sharedPreferences.getBoolean ( key, true);
		}
		else if ("floating_panel_show_distance".equals(key)) {
			App.floatingWindow.showDistance = sharedPreferences.getBoolean ( key, true);
		}
		else if ("FLOATING_PANEL_ICON_SIZE".equals(key)) {
			App.floatingWindow.iconSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_ICON_SIZE)));
		}
		else if ("FLOATING_PANEL_MAIN_TEXT_SIZE".equals(key)) {
			App.floatingWindow.mainTextSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_MAIN_TEXT_SIZE)));
		}
		else if ("FLOATING_PANEL_SECOND_TEXT_SIZE".equals(key)) {
			App.floatingWindow.secondTextSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_SECOND_TEXT_SIZE)));
		}
		else if ("FLOATING_PANEL_UNITS_SIZE".equals(key)) {
			App.floatingWindow.unitsTextSize = Integer.parseInt(sharedPreferences.getString(key, Integer.toString(DEFAULT_UNITS_TEXT_SIZE)));
		}
		else if ("floating_window_show_units".equals(key)) {
			App.floatingWindow.floatingWindowShowUnits = sharedPreferences.getBoolean(key, true);
		}
		else if ("kaierutils_floating_window_vertical".equals(key)) {
			App.GS.ui.floatingWindowVertical = sharedPreferences.getBoolean(key, true);
		}
	}
}
