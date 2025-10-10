package ru.d51x.kaierutils.TWUtils;

import static android.os.Looper.getMainLooper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.tw.john.TWUtil;
import android.util.Log;

import com.maxmpz.poweramp.player.PowerampAPI;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BuildConfig;


/**
 * Created by Dmitriy on 18.02.2015.
 */
public class TWUtilEx {
	private Context context;
	private Handler mHandler;

	public static boolean isTWUtilAvailable() {
		try {
			new TWUtil();
			return true;
		}
		catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
			return false;
		}
	}

	private TWUtil mTWUtil;
	private TWUtil mTWUtilRadio;
	private static int curVolume;
	protected static final String TWUTIL_HANDLER = "TWUtilHandler";



	private static final short[] twutil_contexts = new short[]{
			TWUtilConst.TW_CONTEXT_SLEEP,                   // 514
			TWUtilConst.TW_CONTEXT_REQUEST_SHUTDOWN,        // Shutdown 40720
			TWUtilConst.TW_CONTEXT_REVERSE_ACTIVITY,        // reverse activity 40732
			TWUtilConst.TW_CONTEXT_VOLUME_CONTROL,           // 515
            TWUtilConst.TW_COMMAND_KEY_PRESS,                // 513
			//TWUtilConst.TW_CONTEXT_BRIGHTNESS              // 258
			(short) TWUtilConst.TW_CONTEXT_PRESS_BUTTON_3,                       // 33281 - запуск стандартных приложений
            TWUtilConst.TW_CONTEXT_AUDIO_FOCUS_TAG                      // 769 - audio focus
	};

	protected boolean isTWUtilOpened;
	private Handler mTWUtilHandler;


	@SuppressLint("HandlerLeak")
    public TWUtilEx(Context context) {

		this.context = context;
		mHandler = new Handler();

		mTWUtil = null;
		mTWUtilRadio = null;
		this.mTWUtilHandler = null;
		isTWUtilOpened = false;
		curVolume = -1;
		mTWUtilHandler = new Handler(getMainLooper()){
			@SuppressLint({"HandlerLeak", "DefaultLocale"})
            @Override
			public void handleMessage(Message message) {
				switch (message.what) {
					case TWUtilConst.TW_COMMAND_SLEEP:
						if ( (message.arg1 == 3) && (message.arg2 == 0) ) {
							if ( App.GS.isSleepMode ) {
								SendBroadcastAction( TWUtilConst.TW_BROADCAST_ACTION_WAKE_UP);
                                App.GS.isSleepMode = false;  //wakeup
								App.GS.isMoving = false;
								App.GS.isStopedAfterWakeUp = true;
							}
						} else if ((message.arg1 == 3) && (message.arg2 == 1)) {
							SendBroadcastAction( TWUtilConst.TW_BROADCAST_ACTION_SLEEP);
                            App.GS.isSleepMode = true;
                            App.GS.isMoving = false; // если спим, значит стоим :)
						} else if ( (message.arg1 == 1) && (message.arg2 == 0) ) {
							SendBroadcastAction( TWUtilConst.TW_BROADCAST_ACTION_SHUTDOWN);
						}
						break;
					case TWUtilConst.TW_COMMAND_REVERSE_ACTIVITY:
						if ( message.arg1 == 0 ) {
							SendBroadcastAction( TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH);
                            App.GS.isReverseMode = false;
                            App.sensorsToast.cancel();
						} else {
							if ( !App.GS.isReverseMode ) {
								SendBroadcastAction( TWUtilConst.TW_BROADCAST_ACTION_REVERSE_ACTIVITY_START);
                                App.GS.isReverseMode = true;
							}
						}
						break;
					case TWUtilConst.TW_CONTEXT_VOLUME_CONTROL:
                        if ( App.GS.isReverseMode ) break;
                        curVolume = message.arg1 & Integer.MAX_VALUE;
                        App.GS.setVolumeLevel(curVolume, false);
						SendBroadcastAction( TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED,
								             TWUtilConst.TW_BROADCAST_ACTION_VOLUME_CHANGED, curVolume);
						break;

                    case TWUtilConst.TW_COMMAND_KEY_PRESS:
                        if ( message.arg2 == App.GS.powerAmpOpt.codeNextFolder ) {
                            //case TWUtilConst.TW_SVC_BUTTON_NEXT:
                                if ( message.arg1 == 2) {  // долгое нажатие
                                    SendBroadcastAction(TWUtilConst.TW_BROADCAST_ACTION_KEY_PRESSED,
                                            TWUtilConst.TW_BROADCAST_ACTION_KEY_PRESSED,
                                            TWUtilConst.TW_SVC_BUTTON_NEXT);
                                }
                        } else if ( message.arg2 == App.GS.powerAmpOpt.codePrevFolder ) {
                            //case TWUtilConst.TW_SVC_BUTTON_PREV:
                            if ( message.arg1 == 2) {  // долгое нажатие
                                SendBroadcastAction(TWUtilConst.TW_BROADCAST_ACTION_KEY_PRESSED,
                                        TWUtilConst.TW_BROADCAST_ACTION_KEY_PRESSED,
                                        TWUtilConst.TW_SVC_BUTTON_PREV);
                            }
                        }
//                        if ( message.arg2 == 19 ) {
//                            //case TWUtilConst.TW_SVC_BUTTON_NEXT:
//                            if ( message.arg1 == 2) {  // долгое нажатие
//                            } else if ( message.arg1 == 1 && !App.GS.radio.activityRunning){ // короткое нажатие
//                                mTWUtil.write( TWUtilConst.TW_CONTEXT_RADIO_DATA, 2, 0);
//                            }
//                        } else if ( message.arg2 == 21 ) {
//                            //case TWUtilConst.TW_SVC_BUTTON_PREV:
//                            if ( message.arg1 == 2) {  // долгое нажатие
//                            } else if ( message.arg1 == 1 && !App.GS.radio.activityRunning) { //короткое нажатие
//                                mTWUtil.write( TWUtilConst.TW_CONTEXT_RADIO_DATA, 2, 1);
//                            }
//                        }
                        switch ( message.arg2 ) {
                            case TWUtilConst.TW_CODE_MUSIC:             // = 41;
                                setPowerAmpPlayed();
                                break;
                            case TWUtilConst.TW_CODE_RADIO:             // = 33;
                            case TWUtilConst.TW_CODE_IPOD:              // = 35;
                            case TWUtilConst.TW_CODE_DVD:               // = 36;
                            case TWUtilConst.TW_CODE_TV:                // = 39;
                            case TWUtilConst.TW_CODE_AUX:               // = 40;
                            case TWUtilConst.TW_CODE_VIDEO:             // = 44;
                            case TWUtilConst.TW_CODE_PHONE:             // = 47;
                                setPowerAmpPaused();
                                break;
                            default:
                                break;
                        }

                        break;
                    case TWUtilConst.TW_CONTEXT_PRESS_BUTTON_3:             // 33281
	                    if ( message.arg1 == 1) {
		                    switch (message.arg2) {
			                    case TWUtilConst.TW_CODE_RADIO:             // = 33;
			                    case TWUtilConst.TW_CODE_IPOD:              // = 35;
			                    case TWUtilConst.TW_CODE_DVD:               // = 36;
			                    case TWUtilConst.TW_CODE_TV:                // = 39;
			                    case TWUtilConst.TW_CODE_AUX:               // = 40;
			                    case TWUtilConst.TW_CODE_VIDEO:             // = 44;
			                    case TWUtilConst.TW_CODE_PHONE:             // = 47;
				                    setPowerAmpPaused();
				                    break;
			                    case TWUtilConst.TW_CODE_MUSIC:             // = 41;
				                    setPowerAmpPlayed();
				                    break;
			                    default:
				                    break;
		                    }
	                    }
	                    break;
                    case TWUtilConst.TW_CONTEXT_RADIO_DATA:    // 1025
	                    if ( message.arg1 == 2) {   // и переключение и поиск
		                    String rdata = (String) message.obj;
                            Log.d("TWUtil.handleMessage:", "received message 1025 (radio) arg1 = 2  obj = " + rdata);

		                    Intent ri = new Intent();
		                    ri.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
	                        ri.putExtra ("Frequency", String.format ("%1.2f", (float) message.arg2 / 100));
	                        ri.putExtra("Title", rdata);
		                    ri.setAction(TWUtilConst.TW_BROADCAST_ACTION_RADIO_CHANGED);
		                    App.getInstance ().sendBroadcast(ri);
	                    }
                        break;
                    case TWUtilConst.TW_CONTEXT_AUDIO_FOCUS_TAG:    // 769 - audio focus
                        App.GS.curAudioFocusID = message.arg1;
                        Log.d("TWUtil.handleMessage:", "received message 769 (audiofocus) agr1 = " + String.valueOf(App.GS.curAudioFocusID));
                        SendBroadcastAction(TWUtilConst.TW_BROADCAST_ACTION_AUDIO_FOCUS_CHANGED, "audio_focus_id", message.arg1);

						SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
						prefs.edit().putInt("last_audio_focus_id", App.GS.curAudioFocusID).apply();

                        break;
					default:
						break;
				}
			}
		};
	}

	public void Init() {
		Log.d ("TWUtilEx", "Init ");
		try {
			mTWUtil = new TWUtil();
			mTWUtilRadio = new TWUtil(1);
			int result = mTWUtil.open(twutil_contexts);
			if (result == 0) {
				isTWUtilOpened = true;
				mTWUtil.start();
				mTWUtil.addHandler(TWUTIL_HANDLER, mTWUtilHandler);
				mTWUtil.write(TWUtilConst.TW_CONTEXT_VOLUME_CONTROL, 255);
				//mTWUtil.write (TWUtilConst.TW_CONTEXT_BRIGHTNESS, 255);
				Log.d("TWUtilEx", "Init --> requestEQData (257, 255)");
				mTWUtil.write(TWUtilConst.TW_CONTEXT_EQ, 255);
				Log.d("TWUtilEx", "Init --> requestAudioFocus (769, 255)");
				mTWUtil.write(TWUtilConst.TW_CONTEXT_AUDIO_FOCUS_TAG, 255);
			}
			result = mTWUtilRadio.open(new short[]{(short) TWUtilConst.TW_CONTEXT_RADIO_DATA});
			if (result == 0) {
				mTWUtilRadio.start();
				mTWUtilRadio.addHandler(TWUTIL_HANDLER, mTWUtilHandler);
				Log.d("TWUtilRadio", "Init --> requestRadioData (1025, 255)");
				mTWUtilRadio.write(TWUtilConst.TW_CONTEXT_RADIO_DATA, 255);
			}
		} catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
		}
	}

	public void Destroy() {
		Log.d ("TWUtil", "Destroy()");
		if ( isTWUtilOpened ) {
			mTWUtil.removeHandler ( TWUTIL_HANDLER );
			mTWUtilRadio.removeHandler ( TWUTIL_HANDLER );
			mTWUtil.stop ();
			mTWUtilRadio.stop ();
			mTWUtil.close ();
			mTWUtilRadio.close ();
			mTWUtil = null;
			mTWUtilRadio = null;
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

    private void SendBroadcastAction(String action, String key, String value) {
        Log.d ("TWUtilEx", "SendBroadcastAction ");
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }

	private void SendBroadcastAction(String action, String key, byte[] value) {
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
		try {
			TWUtil mTW = new TWUtil();
			if (mTW.open(new short[]{(short) TWUtilConst.TW_CONTEXT_VOLUME_CONTROL}) == 0) {
				try {
					mTW.start();
					mTW.write(TWUtilConst.TW_CONTEXT_VOLUME_CONTROL, 1, value);
					//mTW.write ( TWUtilConst.TW_CONTEXT_VOLUME_CONTROL, 255);
					mTW.stop();
					mTW.close();
					return true;
				} catch (Exception e) {
					return false;
				}
			}
		} catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
		}
		return  false;
	}

	public static String GetDeviceID () {
		Log.d ("TWUtilEx", "GetDeviceID ");
		String rstr = "<Unknown>";
        if ( ! TWUtilEx.isTWUtilAvailable() ) return rstr;
		try {
			TWUtil mTW = new TWUtil();
			if (mTW.open(new short[]{(short) 65521}) == 0) {
				try {
					mTW.start();
					int res = mTW.write(65521);
					mTW.stop();
					mTW.close();

					switch (res) {
						case 17:
						case 14:
							rstr = String.format("Kaier (ID: %d)", res);
							break;
						case 1:
							rstr = String.format("Create (ID: %d)", res);
							break;
						case 3:
							rstr = String.format("Anstar (ID: %d)", res);
							break;
						case 7:
						case 48:
							rstr = String.format("Waybo (ID: %d)", res);
							break;
						case 6:
							rstr = String.format("Waybo (ID: %d)", res);
							break;
						case 22:
							rstr = String.format("Infidini (ID: %d)", res);
							break;
						default:
							rstr = String.format("<Unknown> (ID: %d)", res);
							break;
					}
				} catch (Exception e) {
					Log.e("TW", e.getMessage());
				}
			}
		} catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
		}
		return rstr;
	}

	private void setPowerAmpPaused() {
		context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
	                                                                              PowerampAPI.Commands.PAUSE));
	}
	private void setPowerAmpPlayed() {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				context.startService(new Intent(PowerampAPI.ACTION_API_COMMAND).putExtra(PowerampAPI.COMMAND,
						PowerampAPI.Commands.RESUME));
			}
		}, 500);
       // Radio.checkRadioActivityStarted( true );
	}

	public static void initEqData() {
        Log.d ("TWUtilEx", "initEqData ");
		if ( ! isTWUtilAvailable() ) return;
		try {
			TWUtil mTW = new TWUtil();
			if (mTW.open(new short[]{(short) TWUtilConst.TW_CONTEXT_EQ}) == 0) {
				try {
					mTW.start();
					mTW.write(TWUtilConst.TW_CONTEXT_EQ, 255);
					mTW.stop();
					mTW.close();
				} catch (Exception e) {
				}
			}
		} catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
		}
	}

    public static void setAudioFocus(int id) {
        Log.d ("TWUtilEx", "setAudioFocus (40465, 192, " + String.valueOf(id) + ")");
        if ( ! isTWUtilAvailable() ) return;
		try {
			TWUtil mTW = new TWUtil();
			if (mTW.open(new short[]{(short) TWUtilConst.TW_CONTEXT_AUDIO_FOCUS_TAG}) == 0) {
				try {
					mTW.start();
					mTW.write(TWUtilConst.TW_COMMAND_AUDIO_FOCUS, 192, id);
					mTW.stop();
					mTW.close();
				} catch (Exception e) {
				}
			}
		} catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
		}
    }

    public static void requestRadioInfo() {
        Log.d ("TWUtilEx", "requestRadioInfo (1025, 255)");
        if ( ! isTWUtilAvailable() ) return;
		try {
			TWUtil mTW = new TWUtil();
			if (mTW.open(new short[]{(short) TWUtilConst.TW_CONTEXT_RADIO_DATA}) == 0) {
				try {
					mTW.start();
					//mTW.write ( TWUtilConst.TW_CONTEXT_RADIO_DATA, 4, 255);
					//mTW.write ( 1030, 0);  // ????
					mTW.write(TWUtilConst.TW_CONTEXT_RADIO_DATA, 255);

					mTW.stop();
					mTW.close();
				} catch (Exception e) {
				}
			}
		} catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
		}
    }
    public static void requestAudioFocusState() {
        Log.d ("TWUtilEx", "requestAudioFocusState (769, 255)");
        if ( ! isTWUtilAvailable() ) return;
		try {
			TWUtil mTW = new TWUtil();
			if (mTW.open(new short[]{(short) TWUtilConst.TW_CONTEXT_AUDIO_FOCUS_TAG}) == 0) {
				try {
					mTW.start();
					mTW.write(TWUtilConst.TW_CONTEXT_AUDIO_FOCUS_TAG, 255);
					mTW.stop();
					mTW.close();
				} catch (Exception e) {
				}
			}
		} catch (UnsatisfiedLinkError e) {
			Log.e("TW", e.getMessage());
		}
    }
}
