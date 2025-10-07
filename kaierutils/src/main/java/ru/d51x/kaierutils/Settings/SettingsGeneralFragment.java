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
		if ( key.equals ( "kaierutils_show_notification_icon" ) ) {
			App.GS.ui.isNotificationIconShow = sharedPreferences.getBoolean ( key, true);
			NotifyData notifyData = new NotifyData( App.getInstance () );
			notifyData.smallIcon = (App.GS.ui.isNotificationIconShow) ? R.drawable.notify_auto : 0;
			notifyData.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
			notifyData.show();
		}
		else if ( key.equals ( "kaierutils_auto_start" ) ) {
			App.GS.ui.isAutoStart = sharedPreferences.getBoolean ( key, false);
		}
		else if (key.equals("isAutoStartFloating")) {
			App.GS.ui.isAutoStartFloating = sharedPreferences.getBoolean(key, false);
		}
		else if (key.equals("kaierutils_floating_window_vertical")) {
			App.GS.ui.floatingWindowVertical = sharedPreferences.getBoolean(key, false);
		}
		else if ( key.equals ( "kaierutils_show_color_speed" ) ) {
			App.GS.ui.isColorSpeed = sharedPreferences.getBoolean ( key, false);
		}
		else if ( key.equals ( "kaierutils_show_statistics" ) ) {
			App.GS.ui.isShowStatistics = sharedPreferences.getBoolean ( key, true);
		}
        else if ( key.equals ( "kaierutils_hide_header" )  ) {
			App.GS.ui.isHideHeader = sharedPreferences.getBoolean ( key, false);
		}
	}
}
