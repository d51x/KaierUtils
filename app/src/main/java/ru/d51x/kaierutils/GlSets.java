package ru.d51x.kaierutils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Dmitriy on 21.02.2015.
 */
public class GlSets {

    protected static final String GPS_BROADCAST_ACTION_LOCATION_CHANGED = "ru.d51x.kaierutils.action.LOCATION_CHANGED";
    protected static final String GPS_BROADCAST_ACTION_SPEED_CHANGED = "ru.d51x.kaierutils.action.SPEED_CHANGED";
    protected static final String GPS_BROADCAST_ACTION_FIRST_FIX = "ru.d51x.kaierutils.action.GPS_EVENT_FIRST_FIX";
    protected static final String GPS_BROADCAST_ACTION_EVENT_STATUS = "ru.d51x.kaierutils.action.GPS_STATUS";
    protected static final String GPS_BROADCAST_ACTION_SATELLITE_STATUS = "ru.d51x.kaierutils.action.GPS_EVENT_SATELLITE_STATUS";
    protected static final String GPS_BROADCAST_ACTION_AGPS_RESET = "ru.d51x.kaierutils.action.GPS_EVENT_AGPS_RESET";

	//public static final boolean IN_EMULATOR = true;
	private int Volume;
	//private int Brightness;
	//private int BrightnessMode;

	public boolean isNeedSoundDecreaseAtStartUp;
	public int VolumeLevelAtStartUp;
	public boolean isNeedSoundDecreaseAtWakeUp;
	public int VolumeLevelAtWakeUp;


    public boolean isNotificationIconShow;
    public boolean isVolumeShowOnNotificationIcon;

	public boolean isNeedSoundDecreaseAtReverse;
	public boolean isFixedVolumeAtReverse;
	public boolean isPercentVolumeAtReverse;
	public int FixedVolumeLevelAtReverse;
	public int PercentVolumeLevelAtReverse;

    public int ReverseActivityCount;
    public int SleepModeCount;
    public long startDate;
    public long workTime;
    public long lastSleep;

    public boolean isPowerAmpPlaying;
    public boolean interactWithPowerAmp;
    public boolean needWatchSleepPowerAmp;
    public boolean needWatchWakeUpPowerAmp;
    public boolean needWatchBootUpPowerAmp;

    public boolean pressNextFolderPowerAmp;
    public boolean pressPrevFolderPowerAmp;

    public boolean pa_isPlaying;
    public boolean pa_isStarted;

    public int resumeDelayForPowerAmp = 3000;


    public double gpsSpeed = 0;
    public double gpsPrevSpeed = 0;
    public int gpsSpeedGrow = 0;

    public boolean isGpsHangs;
    public int cntGpsHangs = 0;
    public boolean isFirstRunGPS = false;
    public boolean isFirstFixGPS = false;
    public long locRequestUpdateTime = 0; // msec
    public int locRequestMinDistance = 0; // meters


    // --- dynamic sound control
    public boolean dsc_isAvailable = false;
    public int dsc_FirstSpeed = 40;    // отсюда начинаем увеличивать громкость
    public int dsc_StepSpeed = 20;     // шаг скорости
    public int dsc_StepVolume = 1;    // шаг изменения громкости
    public int dsc_TimeToChange = 10;   // время в секундах, при котором не меняем громкость, если скорость упала, ниже порога или возросла выше и при этом не менялась
    public int dsc_DeltaToChange = 5;  // дельта изменения скорости, внутри которой не сработает изменение громкости

	public GlSets() {
		Volume = 3;
		//Brightness = 7;
		//BrightnessMode = 0;
		isNeedSoundDecreaseAtStartUp = false;
		isNeedSoundDecreaseAtWakeUp = false;
		VolumeLevelAtStartUp = 3;
		VolumeLevelAtWakeUp = 3;

		isNeedSoundDecreaseAtReverse = false;
		isFixedVolumeAtReverse = false;
		isPercentVolumeAtReverse = false;
		FixedVolumeLevelAtReverse = 3;
		PercentVolumeLevelAtReverse = 30;
        ReverseActivityCount = 0;
        SleepModeCount = 0;
        startDate = System.currentTimeMillis();
        lastSleep = 0;
        workTime = System.currentTimeMillis() - startDate;

        isPowerAmpPlaying = false;
        interactWithPowerAmp = false;
        needWatchSleepPowerAmp = false;
        needWatchWakeUpPowerAmp = false;
        needWatchBootUpPowerAmp = false;
        pressNextFolderPowerAmp = false;
        pressPrevFolderPowerAmp = false;




        pa_isPlaying = false;
        pa_isStarted = false;

		try {
			Volume = getVolumeLevel ();
			//Brightness = getBrightnessLevel ();
			//BrightnessMode = getBrightnessMode ();

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
			isNeedSoundDecreaseAtStartUp = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_START_UP__DO_CHANGE", false);
			VolumeLevelAtStartUp = prefs.getInt("CAR_SETTINGS__VOLUME_AT_START_UP__LEVEL", 3);
			isNeedSoundDecreaseAtWakeUp = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_WAKE_UP__DO_CHANGE", false);
			VolumeLevelAtWakeUp = prefs.getInt("CAR_SETTINGS__VOLUME_AT_WAKE_UP__LEVEL", 3);


			isNeedSoundDecreaseAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__DO_CHANGE", false);
			isFixedVolumeAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL_AVAILABLE", false);
			isPercentVolumeAtReverse = prefs.getBoolean ("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL_AVAILABLE", false);
			FixedVolumeLevelAtReverse = prefs.getInt("CAR_SETTINGS__VOLUME_AT_REVERSE__FIXED_LEVEL", 3);
			PercentVolumeLevelAtReverse = prefs.getInt("CAR_SETTINGS__VOLUME_AT_REVERSE__PERCENTAGE_LEVEL", 30);

            interactWithPowerAmp = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_AVAILABLE", false);
            needWatchSleepPowerAmp = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_WATCH_SLEEP", false);
            needWatchWakeUpPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_WAKEUP", false);
            needWatchBootUpPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_BOOTUP", false);
            pressNextFolderPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_NEXT_FOLDER", false);
            pressPrevFolderPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_PREV_FOLDER", false);
            resumeDelayForPowerAmp = prefs.getInt("CAR_SETTINGS__CONTROL_POWERAMP_START_DELAY", 3000);


            dsc_isAvailable = prefs.getBoolean("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DO_CHAGE", false);
            dsc_FirstSpeed = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__FIRST_SPEED", 40);
            dsc_StepSpeed = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_SPEED", 20);
            dsc_StepVolume = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_VOLUME", 1);
            dsc_TimeToChange = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__TIME_TO_CHANGE", 10);
            dsc_DeltaToChange = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DELTA_TO_CHANGE", 5);

		} catch (Exception e) {

		}
	}

	private void InitValues() {

	}

	public int getVolumeLevel () {
		return Volume;
	}

	public boolean setVolumeLevel (int level, boolean change) {
		if ( !change ) {
			Volume = level;
			return true;
		}
		if ( TWUtilEx.setVolumeLevel( level )) {
			Volume = level;
			return true;
		} else { return false;}
	}

//	public int getBrightnessLevel () {
//		return Brightness;
//	}

//	public boolean setBrightnessLevel (int level, boolean change) {
//		if ( !change ) {
//			Brightness = level;
//			return true;
//		}
//
//		if ( TWUtilEx.setBrightnessLevel (level)) {
//			Brightness = level;
//			return true;
//		} else { return false;}
//	}

//	public int getBrightnessMode() {
//		return BrightnessMode;
//	}

//	public boolean setBrightnessMode(int mode, boolean change) {
//		if ( !change ) {
//			BrightnessMode = mode;
//			return true;
//		}
//		if ( TWUtilEx.setBrightnessMode ( mode ) ) {
//			BrightnessMode = mode;
//			return true;
//		} else { return false; }
//	}

}
