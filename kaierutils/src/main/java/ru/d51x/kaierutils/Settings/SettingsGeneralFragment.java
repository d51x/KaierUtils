package ru.d51x.kaierutils.Settings;


import android.app.Notification;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.Data.NotifyData;
import ru.d51x.kaierutils.R;

public class SettingsGeneralFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


	public void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		addPreferencesFromResource (R.xml.settings_general);

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
		if ( "kaierutils_show_notification_icon".equals ( key ) ) {
			App.GS.ui.isNotificationIconShow = sharedPreferences.getBoolean ( key, true);
			NotifyData notifyData = new NotifyData( App.getInstance () );
			notifyData.smallIcon = (App.GS.ui.isNotificationIconShow) ? R.drawable.notify_auto : 0;
			notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
			notifyData.show();
		}
		else if ( "kaierutils_auto_start".equals ( key ) ) {
			App.GS.ui.isAutoStart = sharedPreferences.getBoolean ( key, false);
		}
		else if ("kaierutils_auto_start_floating".equals(key)) {
			App.GS.ui.isAutoStartFloating = sharedPreferences.getBoolean(key, false);
		}
		else if ("floating_window_show_on_minimize".equals(key)) {
			App.GS.ui.showFloatingOnMinimize = sharedPreferences.getBoolean(key, false);
		}
		else if ("kaierutils_floating_window_vertical".equals(key)) {
			App.GS.ui.floatingWindowVertical = sharedPreferences.getBoolean(key, true);
		}
		else if ("floating_window_show_units".equals(key)) {
			App.GS.ui.floatingWindowShowUnits = sharedPreferences.getBoolean(key, true);
		}
		else if ( "kaierutils_show_color_speed".equals ( key ) ) {
			App.GS.ui.isColorSpeed = sharedPreferences.getBoolean ( key, false);
		}
		else if ( "kaierutils_show_statistics".equals ( key ) ) {
			App.GS.ui.isShowStatistics = sharedPreferences.getBoolean ( key, true);
		}
        else if ( "kaierutils_hide_header".equals ( key )  ) {
			App.GS.ui.isHideHeader = sharedPreferences.getBoolean ( key, false);
		}
        else if ( "isGpsSpeed".equals ( key )  ) {
			App.GS.isGpsSpeed = sharedPreferences.getBoolean ( key, false);
		}
	}
}
