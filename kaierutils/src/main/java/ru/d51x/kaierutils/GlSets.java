package ru.d51x.kaierutils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.graphics.Bitmap;

import ru.d51x.kaierutils.Radio.Radio;
import ru.d51x.kaierutils.TWUtils.TWUtilEx;

/**
 * Created by Dmitriy on 21.02.2015.
 */
public class GlSets {

    public static final String GPS_BROADCAST_ACTION_LOCATION_CHANGED = "ru.d51x.kaierutils.action.LOCATION_CHANGED";
    public static final String GPS_BROADCAST_ACTION_FIRST_FIX = "ru.d51x.kaierutils.action.GPS_EVENT_FIRST_FIX";
    public static final String GPS_BROADCAST_ACTION_AGPS_RESET = "ru.d51x.kaierutils.action.GPS_EVENT_AGPS_RESET";
    public static final String PWRAMP_BROADCAST_ACTION_TRACK_CHANGED = "ru.d51x.kaierutils.action.TRACK_CHANGED";


	public static final String GLOBAL_SETTINGS_COLOR_SPEED = "kaierutils_show_color_speed";


    public Radio radio;
	//public static final boolean IN_EMULATOR = true;
	private int Volume;
	//private int Brightness;
	//private int BrightnessMode;

	public boolean isNeedSoundDecreaseAtStartUp;
	public int VolumeLevelAtStartUp;
	public boolean isNeedSoundDecreaseAtWakeUp;
	public int VolumeLevelAtWakeUp;

    public boolean isNotificationIconShow;
    public boolean isAutoStart;
    public boolean isHideHeader;
	public boolean isShowStatistics;

    public boolean isColorSpeed = false;


	public boolean isNeedSoundDecreaseAtReverse;
	public boolean isFixedVolumeAtReverse;
	public boolean isPercentVolumeAtReverse;
	public int FixedVolumeLevelAtReverse;
	public int PercentVolumeLevelAtReverse;

    public int ReverseActivityCount;
    public int SleepModeCount;
    public boolean isStopedAfterWakeUp = false;
    public boolean isSleepMode = false;
    public boolean isReverseMode = false;
    public boolean isMoving = false; // едем сейчас или стоим больше
    public boolean isFirstStart = false; // едем сейчас или стоим больше
    public long prevTime = 0;
    public long SpeedStopTime = 4*60*1000;  // интервал с нулевой скоростью в минутах, если простояли дольше, значит вообще остановилилсь и не движемся, по хорошему, это надо вынести в настройки

    public long startDate;
    public long workTime;
    public long lastSleep;
    public long wakeUpTime;

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


    public int gpsSpeed = 0;
    public int gpsMaxSpeed = 0;
    public long gpsPrevTimeAtWay = 0;
    public long gpsTimeAtWay = 0;
    public long gpsTimeAtWayWithoutStops = 0;
    public long gpsPrevTimeAtWayWithoutStops = 0;
	public long gpsTimeForAverageSpeed = 0;
    public long gpsPrevTimeForAverageSpeed = 0;
    public long gpsFirstTimeAtWay = 0;
    public int gpsAverageSpeed = 0;
    public int gpsTimeAtWay_Type = 0;   // 0 - не учитывать простои, 1 - учитывать простои
	public float totalDistance = 0;
	public float totalDistanceForAverageSpeed = 0;
    public boolean isGpsHangs = false;
    public int cntGpsHangs = 0;
    //public boolean isFirstRunGPS = false;
    public boolean isFirstFixGPS = false;
    public long locRequestUpdateTime = 1; //0; // msec
    public float locRequestMinDistance = 1f; // meters

    String BT_deviceAddress = "unknown";

    // --- dynamic sound control
    public boolean dsc_isAvailable = false;
    public int dsc_FirstSpeed = 40;    // отсюда начинаем увеличивать громкость
    public int dsc_StepSpeed = 20;     // шаг скорости
    public int dsc_StepVolume = 1;    // шаг изменения громкости
    public int dsc_TimeToChange = 10;   // время в секундах, при котором не меняем громкость, если скорость упала, ниже порога или возросла выше и при этом не менялась
    public int dsc_DeltaToChange = 5;  // дельта изменения скорости, внутри которой не сработает изменение громкости


	public int radioToastLine1TextSize;
	public int radioToastLine2TextSize;

	public int musicToastLine1TextSize;
	public int musicToastLine2TextSize;
	public int musicToastPictureWidth;
	public int musicToastPictureHeight;

    public int curAudioFocusID = -1;
    public boolean isShowMusicInfo = false;
    public boolean dontShowMusicInfoWhenMainActive = true;


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

        radio = new Radio(App.getInstance());

		try {
			Volume = getVolumeLevel ();
			//Brightness = getBrightnessLevel ();
			//BrightnessMode = getBrightnessMode ();

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());

			// load general settings
			isNotificationIconShow = prefs.getBoolean ("kaierutils_show_notification_icon", true);
			isAutoStart = prefs.getBoolean ("kaierutils_auto_start", false);
			isHideHeader = prefs.getBoolean ("kaierutils_hide_header", false);
			isColorSpeed = prefs.getBoolean ("kaierutils_show_color_speed", false);
			isShowStatistics = prefs.getBoolean ("kaierutils_show_statistics", true);

			// load reverse settings
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
            isShowTrackInfoToast = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_TOAST", false);
            isShowMusicInfo = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_MUSIC_INFO", false);
			dontShowMusicInfoWhenMainActive = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_SHOW_TOAST2", true);

            needWatchSleepPowerAmp = prefs.getBoolean ( "CAR_SETTINGS__CONTROL_POWERAMP_WATCH_SLEEP", false);
            needWatchWakeUpPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_WAKEUP", false);
            needWatchBootUpPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_WATCH_BOOTUP", false);
            pressNextFolderPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_NEXT_FOLDER", false);
            pressPrevFolderPowerAmp = prefs.getBoolean ("CAR_SETTINGS__CONTROL_POWERAMP_PREV_FOLDER", false);
            resumeDelayForPowerAmp = prefs.getInt("CAR_SETTINGS__CONTROL_POWERAMP_WAKEUP_DELAY", 3000);
			startDelayForPowerAmp = prefs.getInt("CAR_SETTINGS__CONTROL_POWERAMP_START_DELAY", 3000);

			codeNextFolder = Integer.parseInt (prefs.getString ("CAR_SETTINGS__CONTROL_POWERAMP_CODE_NEXT_CAT", "22"));
			codePrevFolder = Integer.parseInt (prefs.getString ("CAR_SETTINGS__CONTROL_POWERAMP_CODE_PREV_CAT", "23"));

			radio.showToast = prefs.getBoolean("CAR_SETTINGS__RADIO_SHOW_TOAST", false);
			radio.skipSeekingMode = prefs.getBoolean("CAR_SETTINGS__RADIO_SKIP_SEEKING_MODE", true);
            radio.showInfo = prefs.getBoolean("CAR_SETTINGS__RADIO_SHOW_INFO", false);
            radio.dontShowToastOnMainActivity = prefs.getBoolean("CAR_SETTINGS__RADIO_SHOW_TOAST_2", true);

            dsc_isAvailable = prefs.getBoolean("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DO_CHAGE", false);
            dsc_FirstSpeed = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__FIRST_SPEED", 40);
            dsc_StepSpeed = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_SPEED", 20);
            dsc_StepVolume = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__STEP_VOLUME", 1);
            dsc_TimeToChange = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__TIME_TO_CHANGE", 10);
            dsc_DeltaToChange = prefs.getInt("CAR_SETTINGS__DYNAMIC_SOUND_CONTROL__DELTA_TO_CHANGE", 5);

            gpsTimeAtWay_Type = prefs.getInt("CAR_SETTINGS__GPS_TIME_AT_WAY_TYPE", 0);

			radioToastLine1TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__RADIO_TOAST_TEXT1_SIZE", "48"));
			radioToastLine2TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__RADIO_TOAST_TEXT2_SIZE", "25"));

			musicToastLine1TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_TEXT1_SIZE", "32"));
			musicToastLine2TextSize  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_TEXT2_SIZE", "22"));
			musicToastPictureWidth  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_PICTURE_WIDTH", "128"));
			musicToastPictureHeight  = Integer.parseInt (prefs.getString("CAR_SETTINGS__MUSIC_TOAST_PICTURE_HEIGHT", "128"));



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
		if ( TWUtilEx.setVolumeLevel(level)) {
			Volume = level;
			return true;
		} else { return false;}
	}

    public void setGPSMAxSpeed(int speed) {

        if ( speed > gpsMaxSpeed ) gpsMaxSpeed = speed;
    }

    public void setGPSAverageSpeed(int speed) {
        try {
            if ( speed > 0) gpsAverageSpeed = Math.round(totalDistanceForAverageSpeed * 3600 / gpsTimeForAverageSpeed);
        } catch (Exception e) {

        }
    }

}
