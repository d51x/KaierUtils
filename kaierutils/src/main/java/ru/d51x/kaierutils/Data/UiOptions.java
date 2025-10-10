package ru.d51x.kaierutils.Data;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;

public class UiOptions {
    public boolean isShowMusicInfo = false;
    public boolean dontShowMusicInfoWhenMainActive = true;
    public boolean isNotificationIconShow;
    public boolean isAutoStart;
    public boolean isAutoStartFloating;
    public boolean floatingWindowVertical;
    public boolean showFloatingOnMinimize;
    public boolean floatingWindowShowUnits;
    public boolean isHideHeader;
    public boolean isShowStatistics;

    public boolean isColorSpeed = false;

    public int floatingWindowLeft = 0;
    public int floatingWindowTop = 0;

    public UiOptions() {

    }

    public void load() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        isShowMusicInfo = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_MUSIC_INFO", false);
        dontShowMusicInfoWhenMainActive = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_TOAST2", true);
        isNotificationIconShow = prefs.getBoolean ("kaierutils_show_notification_icon", true);
        isAutoStart = prefs.getBoolean ("kaierutils_auto_start", false);
        isAutoStartFloating = prefs.getBoolean ("kaierutils_auto_start_floating", false);
        floatingWindowVertical = prefs.getBoolean ("floating_window_vertical", false);
        showFloatingOnMinimize = prefs.getBoolean ("floating_window_show_on_minimize", false);
        floatingWindowShowUnits = prefs.getBoolean ("floating_window_show_units", true);
        isHideHeader = prefs.getBoolean ("kaierutils_hide_header", false);
        isColorSpeed = prefs.getBoolean ("kaierutils_show_color_speed", false);
        floatingWindowLeft = prefs.getInt ("floating_window_left", 0);
        floatingWindowTop = prefs.getInt ("floating_window_top", 0);
    }

    public void save() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
    }
}
