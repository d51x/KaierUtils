package ru.d51x.kaierutils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build.VERSION;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
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
        tvReverseCount = (TextView) mV.findViewById(R.id.tv_reverse_count);
        tvSleepModeCount = (TextView) mV.findViewById(R.id.tv_sleep_mode_count);
        tvSleepModeLastTime = (TextView) mV.findViewById(R.id.tv_sleep_mode_last_time);
        tvWorkingStart = (TextView) mV.findViewById(R.id.tv_working_start);
        tvDeviceName = (TextView) mV.findViewById(R.id.txtDeviceName);
        tvProgramVersion = (TextView) mV.findViewById(R.id.tvProgramVersion);

        process_statistics_layout_and_elements();


        return mV;
    }

    private void  process_statistics_layout_and_elements() {

        String string_device_name = String.format(getString(R.string.text_device_name), TWUtilEx.GetDeviceID());
        tvDeviceName.setText( string_device_name );

            tvReverseCount.setText( String.format(getString(R.string.text_reverse_count), App.GS.ReverseActivityCount) );
            tvSleepModeCount.setText( String.format(getString(R.string.text_sleep_mode_count), App.GS.SleepModeCount) );

            if ( App.GS.lastSleep == 0) {
                tvSleepModeLastTime.setVisibility( View.INVISIBLE );
            } else {
                Date date = new Date( App.GS.lastSleep );
                SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
                tvSleepModeLastTime.setText( String.format(getString(R.string.text_sleep_mode_last_time), ft.format(date)) );
                tvSleepModeLastTime.setVisibility( View.VISIBLE );
            }
            Date date = new Date( App.GS.startDate );
            SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy HH:mm");
            tvWorkingStart.setText( String.format(getString(R.string.text_working_start), ft.format(date)) );

            tvProgramVersion.setText( "KaierUtils ver. " +  BuildConfig.VERSION_NAME);
    }
}
