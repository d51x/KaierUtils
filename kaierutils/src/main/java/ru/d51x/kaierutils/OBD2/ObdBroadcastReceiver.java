package ru.d51x.kaierutils.OBD2;

import static ru.d51x.kaierutils.OBD2.ObdConstants.ACTION_OBD_PARKING_2101_CHANGED;
import static ru.d51x.kaierutils.OBD2.ObdConstants.KEY_OBD_PARKING_2101;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

import ru.d51x.kaierutils.App;

public class ObdBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "ObdBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if ( ACTION_OBD_PARKING_2101_CHANGED.equals(action)) {
            ArrayList<Integer> buffer = intent.getIntegerArrayListExtra(KEY_OBD_PARKING_2101);
            if (buffer == null) return;

            if (App.GS.isReverseMode ) {
                App.sensorsToast.cancel();
                App.sensorsToast.SetSensors( buffer );
                App.sensorsToast.showToast();
            }

        }
    }
}