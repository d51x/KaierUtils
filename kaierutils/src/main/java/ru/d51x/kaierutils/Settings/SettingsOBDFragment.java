package ru.d51x.kaierutils.Settings;


import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.app.Fragment;

import java.util.ArrayList;
import java.util.Set;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BackgroundService;
import ru.d51x.kaierutils.R;

public class SettingsOBDFragment extends  Fragment  implements View.OnClickListener, View.OnFocusChangeListener {

    private Switch swUseOBD2;
    private TextView tvOBDDevice;
    private TextView tvDeviceState;
    private CheckBox cbCanMMC;
    private CheckBox cbCanMMC_show_fuel;
    private CheckBox cbCanMMC_show_CVT_temp;
    private CheckBox cbCanMMC_cvt_oil_degradation;
    private CheckBox cbCanMMC_show_climate_data;
    private CheckBox cbCanMMC_show_parking_data;
    private CheckBox cbOBD_show_battery;
    private CheckBox cbOBD_show_engine_temp;
    private CheckBox cbOBD_show_fuel_detail;
    private CheckBox cbOBD_show_fuel_consump_detail;

    private EditText edtCanMMC_fueltank_update_time;
    private EditText edtCVT_temp_update_time;
    private EditText edtCanMMC_oil_degr_update_time;
    private EditText edtOBD_voltage_update_time;
    private EditText edtOBD_engine_temp_update_time;

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
        Context context = App.getInstance();

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

        cbCanMMC_show_parking_data = (CheckBox) mV.findViewById(R.id.cbCanMMC_show_parking_data);
        cbCanMMC_show_parking_data.setOnClickListener(this);
        cbCanMMC_show_parking_data.setChecked(App.obd.canMmcData.can_mmc_parking_data_show);

        cbOBD_show_battery = (CheckBox) mV.findViewById(R.id.cbOBD_show_battery);
        cbOBD_show_battery.setOnClickListener(this);
        cbOBD_show_battery.setChecked(App.obd.battery_show);

        cbOBD_show_engine_temp = (CheckBox) mV.findViewById(R.id.cbOBD_show_engine_temp);
        cbOBD_show_engine_temp.setOnClickListener(this);
        cbOBD_show_engine_temp.setChecked(App.obd.engine_temp_show);

        cbOBD_show_fuel_detail = (CheckBox) mV.findViewById(R.id.cbOBD_show_fuel_detail);
        cbOBD_show_fuel_detail.setOnClickListener(this);
        cbOBD_show_fuel_detail.setChecked(App.obd.fuel_data_show);

        cbOBD_show_fuel_consump_detail = (CheckBox) mV.findViewById(R.id.cbOBD_show_fuel_consump_detail);
        cbOBD_show_fuel_consump_detail.setOnClickListener(this);
        cbOBD_show_fuel_consump_detail.setChecked(App.obd.fuel_consump_show);

        edtCanMMC_fueltank_update_time = (EditText) mV.findViewById(R.id.edtCanMMC_fueltank_update_time);
        edtCanMMC_fueltank_update_time.setText(Integer.toString(App.obd.canMmcData.can_mmc_fuel_remain_update_time));
        edtCanMMC_fueltank_update_time.setOnFocusChangeListener( this );

        edtCVT_temp_update_time = (EditText) mV.findViewById(R.id.edtCVT_temp_update_time);
        edtCVT_temp_update_time.setText( Integer.toString(App.obd.canMmcData.can_mmc_cvt_temp_update_time));
        edtCVT_temp_update_time.setOnFocusChangeListener( this );

        edtCanMMC_oil_degr_update_time = (EditText) mV.findViewById(R.id.edtCanMMC_oil_degr_update_time);
        edtCanMMC_oil_degr_update_time.setText(Integer.toString(App.obd.canMmcData.can_mmc_cvt_degradation_update_time));
        edtCanMMC_oil_degr_update_time.setOnFocusChangeListener( this );


        edtOBD_voltage_update_time = (EditText) mV.findViewById(R.id.edtOBD_voltage_update_time);
        edtOBD_voltage_update_time.setText(Integer.toString(App.obd.voltage_update_time));
        edtOBD_voltage_update_time.setOnFocusChangeListener( this );

        edtOBD_engine_temp_update_time = (EditText) mV.findViewById(R.id.edtOBD_engine_temp_update_time);
        edtOBD_engine_temp_update_time.setText(Integer.toString(App.obd.engine_temp_update_time));
        edtOBD_engine_temp_update_time.setOnFocusChangeListener( this );

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
            case R.id.cbCanMMC_show_parking_data:
                App.obd.canMmcData.can_mmc_parking_data_show = cbCanMMC_show_parking_data.isChecked();
                prefs.edit().putBoolean("ODBII_CAN_MMC_PARKING_DATA_SHOW", App.obd.canMmcData.can_mmc_parking_data_show).apply();
                break;

            case R.id.btnSelectDevice2:
                show_odb_device_select_dialog( App.getInstance () );
                break;
            case R.id.btnOBDConnect2:
                if ( !App.obd.isConnected) BackgroundService.startOBDThread();
                break;
            case R.id.btnOBDDisconnect2:
                BackgroundService.stopOBDThread();
                break;
            case R.id.cbOBD_show_battery:
                App.obd.battery_show = cbOBD_show_battery.isChecked();
                prefs.edit().putBoolean("ODBII_BATTERY_SHOW", App.obd.battery_show).apply();
                break;
            case R.id.cbOBD_show_engine_temp:
                App.obd.engine_temp_show = cbOBD_show_engine_temp.isChecked();
                prefs.edit().putBoolean("ODBII_ENGINE_TEMP_SHOW", App.obd.engine_temp_show).apply();
                break;
            case R.id.cbOBD_show_fuel_detail:
                App.obd.fuel_data_show = cbOBD_show_fuel_detail.isChecked();
                prefs.edit().putBoolean("ODBII_FUEL_DATA_SHOW", App.obd.fuel_data_show).apply();
                break;
            case R.id.cbOBD_show_fuel_consump_detail:
                App.obd.fuel_consump_show = cbOBD_show_fuel_consump_detail.isChecked();
                prefs.edit().putBoolean("ODBII_FUEL_CONSUMP_SHOW", App.obd.fuel_consump_show).apply();
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        switch (v.getId()) {
            case R.id.edtCanMMC_fueltank_update_time:
                    // show dialog slider
                if ( hasFocus ) {
                    // появился фокус?
                } else {
                    App.obd.canMmcData.can_mmc_fuel_remain_update_time = Integer.parseInt( edtCanMMC_fueltank_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_CAN_MMC_FUEL_REMAIN_UPDATE_TIME", App.obd.canMmcData.can_mmc_fuel_remain_update_time).apply();
                }
                break;
            case R.id.edtCanMMC_oil_degr_update_time:
                if ( ! hasFocus ) {
                    App.obd.canMmcData.can_mmc_cvt_degradation_update_time = Integer.parseInt( edtCanMMC_oil_degr_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_CAN_MMC_CVT_DEGR_UPDATE_TIME", App.obd.canMmcData.can_mmc_cvt_degradation_update_time).apply();
                }
                break;
            case R.id.edtCVT_temp_update_time:
                if ( ! hasFocus ) {
                    App.obd.canMmcData.can_mmc_cvt_temp_update_time= Integer.parseInt( edtCVT_temp_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_CAN_MMC_CVT_TEMP_UPDATE_TIME", App.obd.canMmcData.can_mmc_cvt_temp_update_time).apply();
                }
                break;
            case R.id.edtOBD_engine_temp_update_time:
                if ( ! hasFocus ) {
                    App.obd.engine_temp_update_time = Integer.parseInt( edtOBD_engine_temp_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_ENGINE_TEMP_UPDATE_TIME", App.obd.engine_temp_update_time).apply();
                }
                break;
            case R.id.edtOBD_voltage_update_time:
                if ( ! hasFocus ) {
                    App.obd.voltage_update_time = Integer.parseInt( edtOBD_voltage_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_VOLTAGE_UPDATE_TIME", App.obd.voltage_update_time).apply();
                }
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

    public  void show_odb_device_select_dialog(Context context) {
        ArrayList<String> deviceStrs = new ArrayList<String>();
        final ArrayList<String> devices = new ArrayList<String>();
        final ArrayList<String> devicesName = new ArrayList<String>();
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (ActivityCompat.checkSelfPermission(App.getInstance().getApplicationContext(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (!pairedDevices.isEmpty())
        {
            for (BluetoothDevice device : pairedDevices)
            {
                deviceStrs.add(device.getName() + "\n" + device.getAddress());
                devices.add(device.getAddress());
                devicesName.add(device.getName());
            }
        }
        // show list
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder( context );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice,
                deviceStrs.toArray(new String[0]));

        alertDialog.setSingleChoiceItems(adapter, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
                int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                App.obd.setDeviceAddress(devices.get(position));
                App.obd.setDeviceName( devicesName.get(position));
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
                prefs.edit().putString("ODBII_DEVICE_ADDRESS", App.obd.getDeviceAddress()).apply();
                prefs.edit().putString("ODBII_DEVICE_NAME", App.obd.getDeviceName()).apply();
            }
        });

        alertDialog.setTitle("Choose Bluetooth device");
        alertDialog.show();

    }
}
