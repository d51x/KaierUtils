package ru.d51x.kaierutils;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.app.Fragment;

public class SettingsOBDFragment extends  Fragment  implements View.OnClickListener {

    private Switch swUseOBD2;
    private TextView tvOBDDevice;
    private TextView tvDeviceState;
    private CheckBox cbCanMMC;
    private CheckBox cbCanMMC_show_fuel;
    private CheckBox cbCanMMC_show_CVT_temp;
    private CheckBox cbCanMMC_cvt_oil_degradation;
    private CheckBox cbCanMMC_show_climate_data;

    private EditText edtCanMMC_fueltank_update_time;
    private EditText edtCVT_temp_update_time;
    private EditText edtCanMMC_oil_degr_update_time;

    private Button btnOBDSelectDevice2;
    private Button btnOBDConnect2;
    private Button btnOBDDisconnect2;


    public SettingsOBDFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mV = inflater.inflate(R.layout.fragment_obdii, container, false);
        // statistics
       // tvReverseCount = (TextView) mV.findViewById(R.id.tv_reverse_count);
        Context context = App.getInstance ();

        swUseOBD2 = (Switch) mV.findViewById(R.id.swUseOBD2);
        swUseOBD2.setOnClickListener(this);
        swUseOBD2.setChecked(App.obd.useOBD);

        cbCanMMC = (CheckBox) mV.findViewById(R.id.cbCanMMC);
        cbCanMMC.setOnClickListener(this);
        cbCanMMC.setChecked(App.obd.MMC_CAN);

        cbCanMMC_show_fuel = (CheckBox) mV.findViewById(R.id.cbCanMMC_show_fuel);
        cbCanMMC_show_fuel.setOnClickListener(this);
        cbCanMMC_show_fuel.setChecked(App.obd.canMmcData.can_mmc_fuel_remain_show);

        tvOBDDevice = (TextView) mV.findViewById(R.id.tvOBDDevice);
        tvDeviceState = (TextView) mV.findViewById(R.id.tvDeviceState);


        cbCanMMC_show_CVT_temp = (CheckBox) mV.findViewById(R.id.cbCanMMC_show_CVT_temp);
        cbCanMMC_show_CVT_temp.setOnClickListener(this);
        cbCanMMC_show_CVT_temp.setChecked(App.obd.canMmcData.can_mmc_cvt_temp_show);

        cbCanMMC_cvt_oil_degradation = (CheckBox) mV.findViewById(R.id.cbCanMMC_cvt_oil_degradation);
        cbCanMMC_cvt_oil_degradation.setOnClickListener(this);
        cbCanMMC_cvt_oil_degradation.setChecked(App.obd.canMmcData.can_mmc_cvt_degr_show);

        cbCanMMC_show_climate_data = (CheckBox) mV.findViewById(R.id.cbCanMMC_show_climate_data);
        cbCanMMC_show_climate_data.setOnClickListener(this);
        cbCanMMC_show_climate_data.setChecked(App.obd.canMmcData.can_mmc_ac_data_show);

        edtCanMMC_fueltank_update_time = (EditText) mV.findViewById(R.id.edtCanMMC_fueltank_update_time);
        edtCanMMC_fueltank_update_time.setText(Integer.toString(App.obd.canMmcData.can_mmc_fuel_remain_update_time));

        edtCVT_temp_update_time = (EditText) mV.findViewById(R.id.edtCVT_temp_update_time);
        edtCVT_temp_update_time.setText( Integer.toString(App.obd.canMmcData.can_mmc_cvt_temp_update_time));


        edtCanMMC_oil_degr_update_time = (EditText) mV.findViewById(R.id.edtCanMMC_oil_degr_update_time);
        edtCanMMC_oil_degr_update_time.setText(Integer.toString(App.obd.canMmcData.can_mmc_cvt_degradation_update_time));

        btnOBDSelectDevice2 = (Button) mV.findViewById(R.id.btnSelectDevice2);
        btnOBDSelectDevice2.setOnClickListener(this);

        btnOBDConnect2 = (Button) mV.findViewById(R.id.btnOBDConnect2);
        btnOBDConnect2.setOnClickListener(this);
        btnOBDDisconnect2 = (Button) mV.findViewById(R.id.btnOBDDisconnect2);
        btnOBDDisconnect2.setOnClickListener(this);

        return mV;
    }


	//	super.onPause();
//getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
//	}

	public void onResume() {
		super.onResume();

        String str = "";
        if (App.obd.getDeviceName() != null) {
            str += App.obd.getDeviceName() + " / " + App.obd.getDeviceAddress();
        }
        tvOBDDevice.setText(String.format(getString(R.string.odbii_device_name), str));
        tvDeviceState.setText(String.format(getString(R.string.odbii_device_status), App.obd.isConnected ? "Подключен" : "Не подключен"));


    }

//	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	//	if ( key.equals ( "CAR_SETTINGS__RADIO_SHOW_TOAST" )) {
	//		App.GS.radio.showToast = sharedPreferences.getBoolean ( key, false);


//	}

    @Override
    public void onClick(View v) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        switch (v.getId()) {
            case R.id.swUseOBD2:
                App.obd.useOBD = swUseOBD2.isChecked();
                prefs.edit().putBoolean("ODBII_USE_BLUETOOTH", App.obd.useOBD).apply();
                // connect or disconnect
                setOnOffOBD( App.obd.useOBD );
                break;
            case R.id.cbCanMMC:
                App.obd.MMC_CAN = cbCanMMC.isChecked();
                prefs.edit().putBoolean("ODBII_USE_MMC_CAN", App.obd.MMC_CAN).apply();
                break;
            case R.id.cbCanMMC_show_fuel:
                App.obd.canMmcData.can_mmc_fuel_remain_show = cbCanMMC_show_fuel.isChecked();
                prefs.edit().putBoolean("ODBII_CAN_MMC_FUEL_REMAIN_SHOW", App.obd.canMmcData.can_mmc_fuel_remain_show).apply();
                break;
            case R.id.cbCanMMC_show_CVT_temp:
                App.obd.canMmcData.can_mmc_cvt_temp_show = cbCanMMC_show_CVT_temp.isChecked();
                prefs.edit().putBoolean("ODBII_CAN_MMC_CVT_TEMP_SHOW", App.obd.canMmcData.can_mmc_cvt_temp_show).apply();
                break;
            case R.id.cbCanMMC_cvt_oil_degradation:
                App.obd.canMmcData.can_mmc_cvt_degr_show = cbCanMMC_cvt_oil_degradation.isChecked();
                prefs.edit().putBoolean("ODBII_CAN_MMC_CVT_DEGR_SHOW", App.obd.canMmcData.can_mmc_cvt_degr_show).apply();
                break;
            case R.id.cbCanMMC_show_climate_data:
                App.obd.canMmcData.can_mmc_ac_data_show = cbCanMMC_show_climate_data.isChecked();
                prefs.edit().putBoolean("ODBII_CAN_MMC_AC_DATA_SHOW", App.obd.canMmcData.can_mmc_ac_data_show).apply();
                break;

            case R.id.btnSelectDevice2:
                OBDIIActivity.show_odb_device_select_dialog( App.getInstance() );
                break;
            case R.id.btnOBDConnect2:
                if ( !App.obd.isConnected) BackgroundService.startOBDThread();
                break;
            case R.id.btnOBDDisconnect2:
                BackgroundService.stopOBDThread();
                break;

            default:
                break;
        }
    }


    public void setOnOffOBD(boolean state) {
        if ( state )
        {
            //on
            if ( !App.obd.isConnected) BackgroundService.startOBDThread();
        } else {
            //off
            if ( App.obd.isConnected) BackgroundService.stopOBDThread();
        }
    }
}
