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
    public boolean isHideHeader;
    public boolean isShowStatistics;

    public boolean isColorSpeed = false;


    public UiOptions() {

    }

    public void load() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        isShowMusicInfo = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_MUSIC_INFO", false);
        dontShowMusicInfoWhenMainActive = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_TOAST2", true);
        isNotificationIconShow = prefs.getBoolean ("kaierutils_show_notification_icon", true);
        isAutoStart = prefs.getBoolean ("kaierutils_auto_start", false);
        isAutoStartFloating = prefs.getBoolean ("kaierutils_auto_start_floating", false);
        isHideHeader = prefs.getBoolean ("kaierutils_hide_header", false);
        isColorSpeed = prefs.getBoolean ("kaierutils_show_color_speed", false);
        isShowStatistics = prefs.getBoolean ("kaierutils_show_statistics", true);
    }

    public void save() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
    }
}
