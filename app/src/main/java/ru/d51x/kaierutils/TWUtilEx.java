package ru.d51x.kaierutils;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.tw.john.TWUtil;
import android.util.Log;
import java.lang.reflect.Type;

/**
 * Created by Dmitriy on 18.02.2015.
 */
public class TWUtilEx {

	public static boolean isTWUtilAvailable() {
		try {
			Type type = TWUtil.class;
			String name = type.getClass().getName();
			Log.d("TWUtilEx", "PASS: TWUtil is available!");
			return true;
		}
		catch (Throwable ex){
			Log.d("TWUtilEx", "ERROR: TWUtil is not available");
			return false;
		}
	}

	private TWUtil mTWUtil;
	private static int curVolume;
	//private static int curBrightness;
	//private static int curBrightnessMode;
	protected static final String TWUTIL_HANDLER = "TWUtilHandler";



	private static final short[] twutil_contexts = new short[]{
			TWUtilConst.TWUTIL_CONTEXT_SLEEP,                   // 514
			TWUtilConst.TWUTIL_CONTEXT_REQUEST_SHUTDOWN,        // Shutdown 40720
			TWUtilConst.TWUTIL_CONTEXT_REVERSE_ACTIVITY,        // reverse activity 40732
			TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL           // 515
			//TWUtilConst.TWUTIL_CONTEXT_BRIGHTNESS              // 258
	};

	protected boolean isTWUtilOpened;
	private Handler mTWUtilHandler;


	public TWUtilEx() {
		mTWUtil = null;
		this.mTWUtilHandler = null;
		isTWUtilOpened = false;
		curVolume = -1;
		mTWUtilHandler = new Handler(){
			private boolean isSleepMode = false;
			private boolean isReverseMode = false;
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
						} else if ( (message.arg1 == 1) && (message.arg2 == 0) ) {
							SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN );
						}
						break;
					//case TWUtilConst.TWUTIL_COMMAND_REQUEST_SHUTDOWN:
					//	SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN );
					//	break;
					case TWUtilConst.TWUTIL_COMMAND_REVERSE_ACTIVITY:
						if ( message.arg1 == 0 ) {
							SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH );
							isReverseMode = false;
						} else {
							if ( !isReverseMode ) {
								SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START );
								isReverseMode = true;
							}
						}
						break;
					case TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL:
                        if ( isReverseMode ) break;
                        curVolume = message.arg1 & Integer.MAX_VALUE;
                        App.mGlobalSettings.setVolumeLevel(curVolume, false);
						SendBroadcastAction( TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED,
								TWUtilConst.TWUTIL_BROADCAST_ACTION_VOLUME_CHANGED, curVolume);
						break;
//					case TWUtilConst.TWUTIL_CONTEXT_BRIGHTNESS:
//						curBrightness = message.arg1;
//						curBrightnessMode = message.arg2;
//						App.mGlobalSettings.setBrightnessLevel (curBrightness, false);
//						App.mGlobalSettings.setBrightnessMode (curBrightnessMode, false);
//						break;
					default:
						break;
				}
			}
		};
	}

	public void Init() {
		Log.d ("TWUtilEx", "Init ");
		mTWUtil = new TWUtil();
		int result = mTWUtil.open(twutil_contexts);
		if ( result == 0) {
			isTWUtilOpened = true;
			mTWUtil.start();
			mTWUtil.addHandler(TWUTIL_HANDLER, mTWUtilHandler);
			mTWUtil.write (TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL, 255);
			//mTWUtil.write (TWUtilConst.TWUTIL_CONTEXT_BRIGHTNESS, 255);
		}
	}

	public void Destroy() {
		Log.d ("TWUtil", "Destroy()");
		if ( isTWUtilOpened ) {
			mTWUtil.removeHandler ( TWUTIL_HANDLER );
			mTWUtil.stop();
			mTWUtil.close();
			mTWUtil = null;
			isTWUtilOpened = false;
		}
	}


	private void SendBroadcastAction(String action) {
		Log.d ("TWUtilEx", "SendBroadcastAction ");
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
		intent.setAction(action);
		App.getInstance ().sendBroadcast(intent);
	}

	private void SendBroadcastAction(String action, String key, int value) {
		Log.d ("TWUtilEx", "SendBroadcastAction ");
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
		if ( key != null ) {
			intent.putExtra(key, value);
		}
		intent.setAction(action);
		App.getInstance ().sendBroadcast(intent);
	}

	public static int getVolumeLevel () { return curVolume; 	}
//	public static int getBrightnessLevel () { return curBrightness; 	}
//	public static int getBrightnessModeLevel () { return curBrightnessMode;	}

	public static boolean setVolumeLevel(int value) {
		Log.d ("TWUtilEx", "setVolumeLevel ");
		if ( ! isTWUtilAvailable() ) return false;
		TWUtil mTW = new TWUtil ();
		if (mTW.open (new short[]{(short) TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL}) == 0) {
			try {
				mTW.start ();
				mTW.write ( TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL, 1, value);
				//mTW.write ( TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL, 255);
				mTW.stop ();
				mTW.close ();
				return true;
			} catch (Exception e) {
				return false;
			}
		} else { return false; }

	}

//	public static boolean setBrightnessLevel (int value) {
//		if ( GlobalSettings.IN_EMULATOR ) return true;
//		TWUtil mTW = new TWUtil ();
//		if (mTW.open (new short[]{(short) TWUtilConst.TWUTIL_CONTEXT_BRIGHTNESS}) == 0) {
//			try {
//				mTW.start ();
//				mTW.write (TWUtilConst.TWUTIL_CONTEXT_BRIGHTNESS, 0, value);
//				mTW.stop ();
//				mTW.close ();
//				return true;
//			}  catch ( Exception e ) {
//				return false;
//			}
//		} else { return false; }
//	}


//	public static boolean setBrightnessMode (int mode) {
//		if ( GlobalSettings.IN_EMULATOR ) return true;
//		TWUtil mTW = new TWUtil ();
//		if (mTW.open (new short[]{(short) TWUtilConst.TWUTIL_CONTEXT_BRIGHTNESS}) == 0) {
//			try {
//				mTW.start ();
//				mTW.write (TWUtilConst.TWUTIL_CONTEXT_BRIGHTNESS, 1, mode);
//				mTW.stop ();
//				mTW.close ();
//				return true;
//			} catch ( Exception e ) {
//				return false;
//			}
//		} else { return false; }
//	}

	public static String GetDeviceID () {
		Log.d ("TWUtilEx", "GetDeviceID ");
        if ( ! TWUtilEx.isTWUtilAvailable() ) return "<Unknown>";
		TWUtil mTW = new TWUtil ();
		if (mTW.open (new short[]{(short) 65521}) == 0) {
			try {
				mTW.start ();
				int res = mTW.write (65521);
				mTW.stop ();
				mTW.close ();
                String rstr = "";
                switch (res) {
                    case 17:
                    case 14:
                        rstr = "Kaier (RP)";
                        break;
                    case 1:
                        rstr = "Create";
                        break;
                    case 7:
                    case 48:
                        rstr = "Waybo";
                        break;
                    case 6:
                        rstr = "Waybo";
                        break;
                    default:
                        rstr = String.format("<Unknown> (ID: %d)", res);
                        break;
                }
				return rstr;
			} catch ( Exception e ) {
				return "<Unknown>";
			}
		} else { return "<Unknown>"; }
	}
}
