package ru.d51x.kaierutils.Settings;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BuildConfig;
import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.TWUtils.TWUtilEx;


public class AboutFragment extends Fragment {

    private TextView tvReverseCount;
    private TextView tvSleepModeCount;
    private TextView tvSleepModeLastTime;
    private TextView tvWorkingStart;
    private TextView tvDeviceName;
    private TextView tvProgramVersion;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mV = inflater.inflate(R.layout.fragment_about, container, false);
        // statistics
        tvReverseCount = mV.findViewById(R.id.tv_reverse_count);
        tvSleepModeCount = mV.findViewById(R.id.tv_sleep_mode_count);
        tvSleepModeLastTime = mV.findViewById(R.id.tv_sleep_mode_last_time);
        tvWorkingStart = mV.findViewById(R.id.tv_working_start);
        tvDeviceName = mV.findViewById(R.id.txtDeviceName);
        tvProgramVersion = mV.findViewById(R.id.tvProgramVersion);

        showStatistics();


        return mV;
    }

    @SuppressLint("SetTextI18n")
    private void showStatistics() {

        String string_device_name = String.format(getResources().getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

            tvReverseCount.setText( String.format(getResources().getString(R.string.text_reverse_count), App.GS.ReverseActivityCount) );
            tvSleepModeCount.setText( String.format(getResources().getString(R.string.text_sleep_mode_count), App.GS.SleepModeCount) );

            if ( App.GS.lastSleep == 0) {
                tvSleepModeLastTime.setVisibility( View.INVISIBLE );
            } else {
                Date date = new Date( App.GS.lastSleep );
                @SuppressLint("SimpleDateFormat") SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
                tvSleepModeLastTime.setText( String.format(getResources().getString(R.string.text_sleep_mode_last_time), ft.format(date)) );
                tvSleepModeLastTime.setVisibility( View.VISIBLE );
            }
            Date date = new Date( App.GS.startDate );
            @SuppressLint("SimpleDateFormat") SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
            tvWorkingStart.setText( String.format(getResources().getString(R.string.text_working_start), ft.format(date)) );

            tvProgramVersion.setText( "KaierUtils ver. " +  BuildConfig.VERSION_NAME);
    }
}
