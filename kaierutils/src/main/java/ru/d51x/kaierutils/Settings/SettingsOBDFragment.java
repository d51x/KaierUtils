package ru.d51x.kaierutils.Settings;


import static androidx.preference.PreferenceManager.getDefaultSharedPreferences;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;
import java.util.Set;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.BackgroundService;
import ru.d51x.kaierutils.R;
import ru.d51x.kaierutils.utils.Permissions;

public class SettingsOBDFragment extends  Fragment  implements View.OnClickListener, View.OnFocusChangeListener {

    private SwitchCompat swUseOBD2;
    private TextView tvOBDDevice;
    private TextView tvDeviceState;
    private SwitchCompat cbNewObdProcess;

    private SwitchCompat cbCanMMC_show_climate_data;
    private SwitchCompat cbCanMMC_show_parking_data;
    private SwitchCompat cbOBD_show_fuel_consump_detail;

    private EditText edtCanMMC_fueltank_update_time;
    private EditText edtCVT_temp_update_time;
    private EditText edtCanMMC_oil_degr_update_time;
    private EditText etSettingsFuelTankCapacity;

    private SwitchCompat cbSpeedFromMeter;
    private SwitchCompat cbSpeedFromEngine;
    private SwitchCompat cbSpeedFromAC;
    private SwitchCompat cbSpeedFromCVT;

    public SettingsOBDFragment() {

    }

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mV = inflater.inflate(R.layout.fragment_obdii, container, false);
        if (getActivity() != null) {
            getActivity().setTitle(getString(R.string.header_obd_settings_title));
        }

        Context context = App.getInstance();

        swUseOBD2 = mV.findViewById(R.id.swUseOBD2);
        swUseOBD2.setOnClickListener(this);
        swUseOBD2.setChecked(App.obd.useOBD);

        cbNewObdProcess = mV.findViewById(R.id.cbNewObdProcess);
        cbNewObdProcess.setOnClickListener(this);
        cbNewObdProcess.setChecked(App.obd.newObdProcess);

        tvOBDDevice = mV.findViewById(R.id.tvOBDDevice);
        tvDeviceState = mV.findViewById(R.id.tvDeviceState);

        cbCanMMC_show_climate_data = mV.findViewById(R.id.cbCanMMC_show_climate_data);
        cbCanMMC_show_climate_data.setOnClickListener(this);
        cbCanMMC_show_climate_data.setChecked(App.obd.can.can_mmc_ac_data_show);

        cbCanMMC_show_parking_data = mV.findViewById(R.id.cbCanMMC_show_parking_data);
        cbCanMMC_show_parking_data.setOnClickListener(this);
        cbCanMMC_show_parking_data.setChecked(App.obd.can.can_mmc_parking_data_show);

        cbOBD_show_fuel_consump_detail = mV.findViewById(R.id.cbOBD_show_fuel_consump_detail);
        cbOBD_show_fuel_consump_detail.setOnClickListener(this);
        cbOBD_show_fuel_consump_detail.setChecked(App.obd.fuelConsumpShow);

        edtCanMMC_fueltank_update_time = mV.findViewById(R.id.edtCanMMC_fueltank_update_time);
        edtCanMMC_fueltank_update_time.setText(Integer.toString(App.obd.can.can_mmc_fuel_remain_update_time));
        edtCanMMC_fueltank_update_time.setOnFocusChangeListener( this );

        edtCVT_temp_update_time = mV.findViewById(R.id.edtCVT_temp_update_time);
        edtCVT_temp_update_time.setText( Integer.toString(App.obd.can.can_mmc_cvt_temp_update_time));
        edtCVT_temp_update_time.setOnFocusChangeListener( this );

        edtCanMMC_oil_degr_update_time = mV.findViewById(R.id.edtCanMMC_oil_degr_update_time);
        edtCanMMC_oil_degr_update_time.setText(Integer.toString(App.obd.can.can_mmc_cvt_degradation_update_time));
        edtCanMMC_oil_degr_update_time.setOnFocusChangeListener( this );

        Button btnOBDSelectDevice2 = mV.findViewById(R.id.btnSelectDevice2);
        btnOBDSelectDevice2.setOnClickListener(this);

        Button btnOBDConnect2 = mV.findViewById(R.id.btnOBDConnect2);
        btnOBDConnect2.setOnClickListener(this);
        Button btnOBDDisconnect2 = mV.findViewById(R.id.btnOBDDisconnect2);
        btnOBDDisconnect2.setOnClickListener(this);

        etSettingsFuelTankCapacity = mV.findViewById(R.id.etSettingsFuelTankCapacity);
        etSettingsFuelTankCapacity.setText(Integer.toString(App.obd.fuelTankCapacity));
        etSettingsFuelTankCapacity.setOnFocusChangeListener( this );

        cbSpeedFromMeter = mV.findViewById(R.id.cbUseSpeedFromMeter);
        cbSpeedFromMeter.setOnClickListener(this);
        cbSpeedFromMeter.setChecked(App.obd.speedFromMeter);

        cbSpeedFromEngine = mV.findViewById(R.id.cbUseSpeedFromEngine);
        cbSpeedFromEngine.setOnClickListener(this);
        cbSpeedFromEngine.setChecked(App.obd.speedFromEngine);

        cbSpeedFromAC = mV.findViewById(R.id.cbUseSpeedFromAC);
        cbSpeedFromAC.setOnClickListener(this);
        cbSpeedFromAC.setChecked(App.obd.speedFromClimate);

        cbSpeedFromCVT = mV.findViewById(R.id.cbUseSpeedFromCVT);
        cbSpeedFromCVT.setOnClickListener(this);
        cbSpeedFromCVT.setChecked(App.obd.speedFromCVT);

        return mV;
    }

	public void onResume() {
		super.onResume();

        String str = "";
        if (App.obd.getDeviceName() != null) {
            str += App.obd.getDeviceName() + " / " + App.obd.getDeviceAddress();
        }
        tvOBDDevice.setText(String.format(getString(R.string.odbii_device_name), str));
        tvDeviceState.setText(String.format(getString(R.string.odbii_device_status), App.obd.isConnected ? "Подключен" : "Не подключен"));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        SharedPreferences prefs = getDefaultSharedPreferences(App.getInstance());
        switch (v.getId()) {
            case R.id.swUseOBD2:
                App.obd.useOBD = swUseOBD2.isChecked();
                prefs.edit().putBoolean("ODBII_USE_BLUETOOTH", App.obd.useOBD).apply();
                // connect or disconnect
                setOnOffOBD( App.obd.useOBD );
                break;
            case R.id.cbNewObdProcess:
                App.obd.newObdProcess = cbNewObdProcess.isChecked();
                prefs.edit().putBoolean("ODBII_NEW_PROCESS", App.obd.newObdProcess).apply();
                break;
            case R.id.cbCanMMC_show_climate_data:
                App.obd.can.can_mmc_ac_data_show = cbCanMMC_show_climate_data.isChecked();
                prefs.edit().putBoolean("ODBII_CAN_MMC_AC_DATA_SHOW", App.obd.can.can_mmc_ac_data_show).apply();
                break;
            case R.id.cbCanMMC_show_parking_data:
                App.obd.can.can_mmc_parking_data_show = cbCanMMC_show_parking_data.isChecked();
                prefs.edit().putBoolean("ODBII_CAN_MMC_PARKING_DATA_SHOW", App.obd.can.can_mmc_parking_data_show).apply();
                break;

            case R.id.btnSelectDevice2:
                show_odb_device_select_dialog( App.getInstance().getApplicationContext() );
                break;
            case R.id.btnOBDConnect2:
                if ( !App.obd.isConnected) BackgroundService.startOBDThread();
                break;
            case R.id.btnOBDDisconnect2:
                BackgroundService.stopOBDThread();
                break;
            case R.id.cbOBD_show_fuel_consump_detail:
                App.obd.fuelConsumpShow = cbOBD_show_fuel_consump_detail.isChecked();
                prefs.edit().putBoolean("ODBII_FUEL_CONSUMP_SHOW", App.obd.fuelConsumpShow).apply();
                break;
            case R.id.cbUseSpeedFromEngine:
                App.obd.speedFromEngine = cbSpeedFromEngine.isChecked();
                prefs.edit().putBoolean("ODB_SPEED_FROM_ENGINE", App.obd.speedFromEngine).apply();
                break;
            case R.id.cbUseSpeedFromMeter:
                App.obd.speedFromMeter = cbSpeedFromMeter.isChecked();
                prefs.edit().putBoolean("ODB_SPEED_FROM_METER", App.obd.speedFromMeter).apply();
                break;
            case R.id.cbUseSpeedFromAC:
                App.obd.speedFromClimate = cbSpeedFromAC.isChecked();
                prefs.edit().putBoolean("ODB_SPEED_FROM_AC", App.obd.speedFromClimate).apply();
                break;
            case R.id.cbUseSpeedFromCVT:
                App.obd.speedFromCVT = cbSpeedFromCVT.isChecked();
                prefs.edit().putBoolean("ODB_SPEED_FROM_CVT", App.obd.speedFromCVT).apply();
                break;
            default:
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        SharedPreferences prefs = getDefaultSharedPreferences(App.getInstance());
        switch (v.getId()) {
            case R.id.edtCanMMC_fueltank_update_time:
                    // show dialog slider
                if ( !hasFocus ) {
                    App.obd.can.can_mmc_fuel_remain_update_time = Integer.parseInt( edtCanMMC_fueltank_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_CAN_MMC_FUEL_REMAIN_UPDATE_TIME", App.obd.can.can_mmc_fuel_remain_update_time).apply();
                }
                break;
            case R.id.edtCanMMC_oil_degr_update_time:
                if ( ! hasFocus ) {
                    App.obd.can.can_mmc_cvt_degradation_update_time = Integer.parseInt( edtCanMMC_oil_degr_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_CAN_MMC_CVT_DEGR_UPDATE_TIME", App.obd.can.can_mmc_cvt_degradation_update_time).apply();
                }
                break;
            case R.id.edtCVT_temp_update_time:
                if ( ! hasFocus ) {
                    App.obd.can.can_mmc_cvt_temp_update_time= Integer.parseInt( edtCVT_temp_update_time.getText().toString() );
                    prefs.edit().putInt("ODBII_CAN_MMC_CVT_TEMP_UPDATE_TIME", App.obd.can.can_mmc_cvt_temp_update_time).apply();
                }
                break;
            case R.id.etSettingsFuelTankCapacity:
                if ( ! hasFocus ) {
                    App.obd.fuelTankCapacity = Integer.parseInt( etSettingsFuelTankCapacity.getText().toString() );
                    prefs.edit().putInt("ODBII_FUEL_TANK_CAPACITY", App.obd.fuelTankCapacity).apply();
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
        ArrayList<String> deviceStrs = new ArrayList<>();
        final ArrayList<String> devices = new ArrayList<>();
        final ArrayList<String> devicesName = new ArrayList<>();
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        //if (ActivityCompat.checkSelfPermission(App.getInstance().getApplicationContext(),
        //        Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Permissions permissions = new Permissions(SettingsOBDFragment.this);
            permissions.requestPermissions2();
            //return;
        //}
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

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.select_dialog_singlechoice,
                deviceStrs.toArray(new String[0]));

        alertDialog.setSingleChoiceItems(adapter, -1, (dialog, which) -> {
            dialog.dismiss();
            int position = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
            App.obd.setDeviceAddress(devices.get(position));
            App.obd.setDeviceName( devicesName.get(position));
            SharedPreferences prefs = getDefaultSharedPreferences(App.getInstance());
            prefs.edit().putString("ODBII_DEVICE_ADDRESS", App.obd.getDeviceAddress()).apply();
            prefs.edit().putString("ODBII_DEVICE_NAME", App.obd.getDeviceName()).apply();
        });

        alertDialog.setTitle("Choose Bluetooth device");
        alertDialog.show();

    }
}
