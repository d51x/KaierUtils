package ru.d51x.kaierutils.Data;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

import ru.d51x.kaierutils.App;

public class PowerAmpOptions {
    public boolean isPowerAmpPlaying;
    public boolean isNeedPowerAmpToPlayAfterWakeup = false;
    public boolean interactWithPowerAmp;
    public boolean needWatchSleepPowerAmp;
    public boolean needWatchWakeUpPowerAmp;
    public boolean needWatchBootUpPowerAmp;

    public boolean pressNextFolderPowerAmp;
    public boolean pressPrevFolderPowerAmp;
    public int codeNextFolder;
    public int codePrevFolder;
    public boolean isShowTrackInfoToast;

    public boolean pa_isPlaying;
    public boolean pa_isStarted;
    public String PowerAmp_TrackTitle = "";  //TODO перевести кучу переменных на классы (структуры)
    public String PowerAmp_AlbumArtist = "";
    public Bitmap PowerAmp_AlbumArt = null;

    public int resumeDelayForPowerAmp = 3000;
    public int startDelayForPowerAmp = 3000;

    public PowerAmpOptions() {
        isPowerAmpPlaying = false;
        interactWithPowerAmp = false;
        needWatchSleepPowerAmp = false;
        needWatchWakeUpPowerAmp = false;
        needWatchBootUpPowerAmp = false;
        pressNextFolderPowerAmp = false;
        pressPrevFolderPowerAmp = false;
        pa_isPlaying = false;
        pa_isStarted = false;
    }

    public void save() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
    }

    public void load() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        interactWithPowerAmp = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_AVAILABLE", false);
        isShowTrackInfoToast = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_TOAST", false);
        needWatchSleepPowerAmp = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_WATCH_SLEEP", false);
        needWatchWakeUpPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_WAKEUP", false);
        needWatchBootUpPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_BOOTUP", false);
        pressNextFolderPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_NEXT_FOLDER", false);
        pressPrevFolderPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_PREV_FOLDER", false);
        resumeDelayForPowerAmp = prefs.getInt("CAR_SETTINGS__CONTROL_POWERAMP_WAKEUP_DELAY", 3000);
        startDelayForPowerAmp = prefs.getInt("CAR_SETTINGS__CONTROL_POWERAMP_START_DELAY", 3000);

        codeNextFolder = Integer.parseInt (prefs.getString ("CAR_SETTINGS__CONTROL_POWERAMP_CODE_NEXT_CAT", "22"));
        codePrevFolder = Integer.parseInt (prefs.getString ("CAR_SETTINGS__CONTROL_POWERAMP_CODE_PREV_CAT", "23"));
    }
}
