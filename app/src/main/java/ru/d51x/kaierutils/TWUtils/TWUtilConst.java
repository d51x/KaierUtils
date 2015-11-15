package ru.d51x.kaierutils.TWUtils;

/**
 * Created by Dmitriy on 19.02.2015.
 */
public class TWUtilConst {

	public static final String TW_BROADCAST_ACTION_WAKE_UP = "ru.d51x.kaierutils.action.WAKEUP";
	public static final String TW_BROADCAST_ACTION_SLEEP = "ru.d51x.kaierutils.action.SLEEP";
	public static final String TW_BROADCAST_ACTION_SHUTDOWN = "ru.d51x.kaierutils.action.REQUEST_SHUTDOWN";

	public static final String TW_BROADCAST_ACTION_REVERSE_ACTIVITY_START = "ru.d51x.kaierutils.action.REVERSE_ACTIVITY_START";
	public static final String TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH = "ru.d51x.kaierutils.action.REVERSE_ACTIVITY_FINISH";

	public static final String TW_BROADCAST_ACTION_VOLUME_CHANGED = "ru.d51x.kaierutils.action.VOLUME_CHANGED";

	public static final String TW_BROADCAST_ACTION_EQ_CHANGED = "ru.d51x.kaierutils.action.EQ_CHANGED";

	public static final String TW_BROADCAST_ACTION_KEY_PRESSED = "ru.d51x.kaierutils.action.KEY_TW_PRESSED";
	public static final String TW_BROADCAST_ACTION_RADIO_CHANGED = "ru.d51x.kaierutils.action.RADIO_CHANGED";
	public static final String TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED = "ru.d51x.kaierutils.action.AUDIO_FOCUS_CHANGED";

	public static final int TW_CODE_NAVI = 32;

	public static final int TW_CODE_RADIO = 33;
	public static final int TW_CODE_IPOD = 35;
	public static final int TW_CODE_DVD = 36;
	public static final int TW_CODE_TV = 39;
	public static final int TW_CODE_AUX = 40;
	public static final int TW_CODE_MUSIC = 41;
	public static final int TW_CODE_VIDEO = 44;
	public static final int TW_CODE_PHONE = 47;








	public static final int TW_CONTEXT_EQ = 257;
	public static final int TW_CONTEXT_SLEEP = 514;
	public static final int TW_COMMAND_SLEEP = 514;
	public static final int TW_COMMAND_KEY_PRESS = 513;
	public static final int TW_CONTEXT_VOLUME_CONTROL = 515;
	public static final int TW_CONTEXT_BRIGHTNESS = 258;

	public static final int TW_CONTEXT_REQUEST_SHUTDOWN = -24816;


	public static final int TW_CONTEXT_REVERSE_ACTIVITY = -24804; // arg1 = 0 (772,0) 1 - start revers, 0 - stop revers
	public static final int TW_COMMAND_REVERSE_ACTIVITY = 40732;
				// -24805 = 40731

	public static final int TW_CONTEXT_AUDIO_FOCUS_TAG = 769;
	public static final int TW_COMMAND_AUDIO_FOCUS = 40465;
	public static final int TW_CONTEXT_REVERSE_TAG = 772;

	public static final int TW_CONTEXT_ROOT_CMD = -24806;
	public static final int TW_COMMAND_ROOT_CMD = 40730;

	public static final int TW_CONTEXT_PRESS_BUTTON_1 = 513;
	public static final int TW_CONTEXT_PRESS_BUTTON_2 = -32255;
	public static final int TW_CONTEXT_PRESS_BUTTON_3 = 33281;

	public static final int TW_CONTEXT_RADIO_DATA = 1025; // radio channel name ???

	public static final int TW_SVC_BUTTON_NEXT = 22; //19
	public static final int TW_SVC_BUTTON_PREV = 23; //18


	public static final int TW_AUDIO_FOCUS_RADIO_ID = 1;
	public static final int TW_AUDIO_FOCUS_DVD_ID = 2;
	public static final int TW_AUDIO_FOCUS_MUSIC_ID = 3;
	public static final int TW_AUDIO_FOCUS_IPOD_ID = 4;
	public static final int TW_AUDIO_FOCUS_TV_ID = 5;
	public static final int TW_AUDIO_FOCUS_AUX_ID = 7;
	public static final int TW_AUDIO_FOCUS_BT_ID = 8;
	public static final int TW_AUDIO_FOCUS_VIDEO_ID = 9;


}
