package ru.d51x.kaierutils;

/**
 * Created by Dmitriy on 19.02.2015.
 */
public class TWUtilConst {

	protected static final String TWUTIL_BROADCAST_ACTION_WAKE_UP = "ru.d51x.kaierutils.action.WAKEUP";
	protected static final String TWUTIL_BROADCAST_ACTION_SLEEP = "ru.d51x.kaierutils.action.SLEEP";
	protected static final String TWUTIL_BROADCAST_ACTION_SHUTDOWN = "ru.d51x.kaierutils.action.REQUEST_SHUTDOWN";

	protected static final String TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START = "ru.d51x.kaierutils.action.REVERSE_ACTIVITY_START";
	protected static final String TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH = "ru.d51x.kaierutils.action.REVERSE_ACTIVITY_FINISH";

	protected static final String TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED = "ru.d51x.kaierutils.action.VOLUME_CHANGED";

    protected static final String TWUTIL_BROADCAST_ACTION_KEY_PRESSED = "ru.d51x.kaierutils.action.KEY_TW_PRESSED";
    protected static final String TWUTIL_BROADCAST_ACTION_RADIO_TITLE_CHANGED = "ru.d51x.kaierutils.action.RADIO_TITLE_CHANGED";

	protected static final int TWUTIL_CODE_NAVI = 32;

	protected static final int TWUTIL_CODE_RADIO = 33;
	protected static final int TWUTIL_CODE_IPOD = 35;
	protected static final int TWUTIL_CODE_DVD = 36;
	protected static final int TWUTIL_CODE_TV = 39;
	protected static final int TWUTIL_CODE_AUX = 40;
	protected static final int TWUTIL_CODE_MUSIC = 41;
	protected static final int TWUTIL_CODE_VIDEO = 44;
	protected static final int TWUTIL_CODE_PHONE = 47;









	protected static final int TWUTIL_CONTEXT_SLEEP = 514;
	protected static final int TWUTIL_COMMAND_SLEEP = 514;
    protected static final int TWUTIL_COMMAND_KEY_PRESS = 513;
	protected static final int TWUTIL_CONTEXT_VOLUME_CONTROL = 515;
	protected static final int TWUTIL_CONTEXT_BRIGHTNESS = 258;

	protected static final int TWUTIL_CONTEXT_REQUEST_SHUTDOWN = -24816;


	protected static final int TWUTIL_CONTEXT_REVERSE_ACTIVITY = -24804; // arg1 = 0 (772,0) 1 - start revers, 0 - stop revers
	protected static final int TWUTIL_COMMAND_REVERSE_ACTIVITY = 40732;
				// -24805 = 40731

	protected static final int TWUTIL_CONTEXT_REVERSE_TAG = 772;

	protected static final int TWUTIL_CONTEXT_ROOT_CMD = -24806;
	protected static final int TWUTIL_COMMAND_ROOT_CMD = 40730;

	protected static final int TWUTIL_CONTEXT_PRESS_BUTTON_1 = 513;
	protected static final int TWUTIL_CONTEXT_PRESS_BUTTON_2 = -32255;
	protected static final int TWUTIL_CONTEXT_PRESS_BUTTON_3 = 33281;

	protected static final int TWUTIL_CONTEXT_RADIO_CONTROL_1 = 1025;
	protected static final int TWUTIL_CONTEXT_RADIO_CONTROL_2 = 1028;
	protected static final int TWUTIL_CONTEXT_RADIO_PROGRAM = 1026;
    protected static final int TWUTIL_CONTEXT_RADIO_CHANNEL_TITLE = 1029; // radio channel name ???

    protected static final int TWUTIL_SVC_BUTTON_NEXT = 22;
    protected static final int TWUTIL_SVC_BUTTON_PREV = 23;

}
