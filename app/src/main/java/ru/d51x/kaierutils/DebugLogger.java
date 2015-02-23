package ru.d51x.kaierutils;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by Dmitriy on 20.02.2015.
 */
public class DebugLogger {

	public static final boolean isDebug = false;
	public static final boolean isShowToast = false;

	public static void ToLog(String TAG, String statement){
		if (isDebug) {
			Log.d(TAG, statement);
		}
	}

	public static void ShowToast(String statement, int duration){
		if (isShowToast) {
			Toast.makeText (App.getInstance (), String.format ("KaierUtils: %s ", statement), duration).show ();
		}
	}
}
