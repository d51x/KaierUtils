package ru.d51x.kaierutils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.tw.john.TWUtil;
import android.widget.Toast;
import android.provider.Settings.System;

import android.preference.PreferenceManager;


public class TWUtilBroadcastReceiver extends BroadcastReceiver {
	public TWUtilBroadcastReceiver () {
	}

	@Override
	public void onReceive (Context context, Intent intent) {
		String action = intent.getAction();
		if ( action.equals (Intent.ACTION_BOOT_COMPLETED ) ) {
				// автозапуск
				// TODO: проверка на автозапуск сервиса
				context.startService(new Intent(context, TWUtilService.class));
		}
		else if ( action.equals ( Intent.ACTION_SHUTDOWN ) ||
				    action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_SHUTDOWN ))
		{
			Toast.makeText (context, "KaierUtils: action shutdown", Toast.LENGTH_LONG).show();
			// изменить натсройку звука
			SetVolume( GetVolumePreference() );
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_WAKE_UP ))
		{
			Toast.makeText (context, "KaierUtils: action wakeup", Toast.LENGTH_LONG).show();
			// TODO: что-то делаем при просыпании

		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_SLEEP ))
		{
			Toast.makeText (context, "KaierUtils: action sleep", Toast.LENGTH_LONG).show();
			// изменить натсройку звука
			SetVolume( GetVolumePreference() );
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_START ))
		{
			Toast.makeText (context, String.format("KaierUtils: action reverse activity start,  %i", TWUtilConst.TWUTIL_COMMAND_REVERSE_ACTIVITY), Toast.LENGTH_LONG).show();
			SetVolume( 5 );
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_ACTIVITY_FINISH ))
		{
			Toast.makeText (context, String.format("KaierUtils: action reverse activity finish,  %i", TWUtilConst.TWUTIL_COMMAND_REVERSE_ACTIVITY), Toast.LENGTH_LONG).show();
			SetVolume( 8 );
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_TAG_START ))
		{
			Toast.makeText (context, String.format("KaierUtils: action reverse activity start %i", TWUtilConst.TWUTIL_CONTEXT_REVERSE_TAG), Toast.LENGTH_LONG).show();
			SetVolume( 4 );
		}
		else if ( action.equals ( TWUtilConst.TWUTIL_BROADCAST_ACTION_REVERSE_TAG_STOP ))
		{
			Toast.makeText (context, String.format("KaierUtils: action reverse activity stop %i", TWUtilConst.TWUTIL_CONTEXT_REVERSE_TAG), Toast.LENGTH_LONG).show();
			SetVolume( 7 );

		}

	}

	private void SetVolume(int value) {
		TWUtil mTW = new TWUtil ();
		if (mTW.open (new short[]{(short) TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL}) == 0) {
			mTW.start ();
			mTW.write ( TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL, 1, value);

			Toast.makeText (App.getInstance (), String.format("KaierUtils: volume changed to %s", value), Toast.LENGTH_LONG).show();

			mTW.write ( TWUtilConst.TWUTIL_CONTEXT_VOLUME_CONTROL, 255);
			mTW.stop ();
			mTW.close ();
		}
		mTW = null;
	}

	private int GetVolumePreference() {
		int res = -0;
		// res = PreferenceManager.getDefaultSharedPreferences(App.getInstance()).getInt("ru.d51x.volume_at_startup", 3);
		res =  System.getInt(App.getInstance().getContentResolver(), "CAR_SETTINGS__VOLUME_AT_START_UP", 3);
		return res;
	}
}
