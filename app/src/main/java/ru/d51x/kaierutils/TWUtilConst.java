package ru.d51x.kaierutils;

/**
 * Created by Dmitriy on 19.02.2015.
 */
public class TWUtilConst {

	protected static final String TWUTIL_BROADCAST_ACTION_WAKE_UP = "com.tw.service.action.WAKEUP";
	protected static final String TWUTIL_BROADCAST_ACTION_SLEEP = "com.tw.service.action.SLEEP";
	protected static final String TWUTIL_BROADCAST_ACTION_SHUTDOWN = "com.tw.service.action.REQUEST_SHUTDOWN";

	protected static final String TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START = "com.tw.service.action.REVERSE_ACTIVITY_START";
	protected static final String TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH = "com.tw.service.action.REVERSE_ACTIVITY_FINISH";
	protected static final String TWUTIL_BROADCAST_ACTION_REVERSE_TAG_START = "com.tw.service.action.REVERSE_TAG_START";
	protected static final String TWUTIL_BROADCAST_ACTION_REVERSE_TAG_STOP = "com.tw.service.action.REVERSE_TAG_STOP";

	protected static final int TWUTIL_CONTEXT_SLEEP = 514;
	protected static final int TWUTIL_COMMAND_SLEEP = 514;

	protected static final int TWUTIL_CONTEXT_VOLUME_CONTROL = 515;

	protected static final int TWUTIL_CONTEXT_REQUEST_SHUTDOWN = -24816;
	protected static final int TWUTIL_COMMAND_REQUEST_SHUTDOWN = 40720;


	protected static final int TWUTIL_CONTEXT_REVERSE_ACTIVITY = -24804; // arg1 = 0 (772,0) 1 - start revers, 0 - stop revers
	protected static final int TWUTIL_COMMAND_REVERSE_ACTIVITY = 40732;

	protected static final int TWUTIL_CONTEXT_REVERSE_TAG = 772;
}
