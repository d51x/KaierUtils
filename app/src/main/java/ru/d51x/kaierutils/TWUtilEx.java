package ru.d51x.kaierutils;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.tw.john.TWUtil;
import android.widget.Toast;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class TWUtilEx {

	private TWUtil mTWUtil;

	protected static final String TWUTIL_HANDLER = "TWUtilHandler";



	private static final short[] twutil_contexts = new short[]{
			TWUtilConst.TWUTIL_CONTEXT_SLEEP,                   // 514
			TWUtilConst.TWUTIL_CONTEXT_REQUEST_SHUTDOWN,        // Shutdown 40720
			TWUtilConst.TWUTIL_CONTEXT_REVERSE_ACTIVITY,        // reverse activity 40732
			TWUtilConst.TWUTIL_CONTEXT_REVERSE_TAG              // 772
	};

	protected boolean isTWUtilOpened;
	private Handler mTWUtilHandler;


	public TWUtilEx() {
		this.mTWUtilHandler = null;
		isTWUtilOpened = false;

		mTWUtilHandler = new Handler(){
			private boolean isSleepMode = false;
			@Override
			public void handleMessage(Message message) {
				switch (message.what) {
					case TWUtilConst.TWUTIL_COMMAND_SLEEP:
						if ( (message.arg1 == 3) && (message.arg2 == 0) ) {
							if ( isSleepMode ) {
								SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP );
								isSleepMode = false;
							}
						} else if ((message.arg1 == 3) && (message.arg2 == 1)) {
							SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP );
							isSleepMode = true;
						}
						break;
					case TWUtilConst.TWUTIL_COMMAND_REQUEST_SHUTDOWN:
						SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN );
						break;
					case TWUtilConst.TWUTIL_CONTEXT_REVERSE_ACTIVITY:
						if ( message.arg1 == 0 ) {
							SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH );
						} else {
							SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START );
						}
						break;
					case TWUtilConst.TWUTIL_CONTEXT_REVERSE_TAG:
						if ( message.arg1 == 0 ) {
							SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_TAG_STOP );
						} else if ( message.arg1 == 1 ) {
							SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_TAG_START );
						}
						break;
					default:
						break;
				}
			}
		};
	}

	public void Init() {
		mTWUtil = new TWUtil();
		int result = mTWUtil.open(twutil_contexts);
		if ( result == 0) {
			isTWUtilOpened = true;
			mTWUtil.start();
			mTWUtil.addHandler(TWUTIL_HANDLER, mTWUtilHandler);
		}
	}

	public void Destroy() {
		if ( isTWUtilOpened ) {
			mTWUtil.removeHandler ( TWUTIL_HANDLER );
			mTWUtil.stop();
			mTWUtil.close();
			mTWUtil = null;
			isTWUtilOpened = false;
		}
	}


	private void SendBroadcastAction(String action) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
		intent.setAction(action);
		App.getInstance ().sendBroadcast(intent);
	}


}
