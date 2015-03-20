package ru.d51x.kaierutils;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.view.View;

import java.util.ArrayList;
import java.util.Set;
import android.util.Log;


public class OBDIIActivity extends Activity implements View.OnClickListener {


    private Button btnSelectDevice;
    private Button btnConnect;
    private Button btnDisconnect;
    private TextView tvDeviceName;
    private TextView tvDeviceStatus;
    private TextView tvOBDEngineRPM;
    private TextView tvOBDSpeed;
    private TextView tvOBD_CoolantTemp;
    private TextView tvOBD_FuelLevel;
    private TextView tvOBD_CMUVoltage;
    private TextView tvOBD_FuelConsumption;
    private TextView tvOBD_FuelConsumption2;

    private TextView tvOBD_MAF;
    private TextView tvOBD_AirIntakeTemp;
    private Switch swUseOBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obdii);

        btnSelectDevice = (Button) findViewById(R.id.btnSelectDevice);
        btnSelectDevice.setOnClickListener(this);
        btnConnect = (Button) findViewById(R.id.btnOBDConnect);
        btnConnect.setOnClickListener(this);
        btnDisconnect = (Button) findViewById(R.id.btnOBDDisconnect);
        btnDisconnect.setOnClickListener(this);
        tvDeviceName = (TextView) findViewById(R.id.tvDeviceName);
        tvDeviceStatus = (TextView) findViewById(R.id.tvDeviceStatus);
        tvOBDEngineRPM = (TextView) findViewById(R.id.tvOBDEngineRPM);
        tvOBDSpeed = (TextView) findViewById(R.id.tvOBD_Speed);
        tvOBD_CoolantTemp = (TextView) findViewById(R.id.tvOBD_CoolantTemp);
        tvOBD_FuelLevel = (TextView) findViewById(R.id.tvOBD_FuelLevel);
        tvOBD_CMUVoltage = (TextView) findViewById(R.id.tvOBD_CMUVoltage);
        tvOBD_FuelConsumption = (TextView) findViewById(R.id.tvOBD_FuelConsumption);
        tvOBD_FuelConsumption2 = (TextView) findViewById(R.id.tvOBD_FuelConsumption2);

        swUseOBD = (Switch) findViewById(R.id.swUseOBD);
	    swUseOBD.setOnClickListener (this);
	    swUseOBD.setChecked (App.obd.useOBD);
        tvOBDEngineRPM.setText (String.format (getString (R.string.text_obd_engine_rpm), ""));
        tvOBDSpeed.setText( String.format( getString( R.string.text_obd_speed), ""));
        tvOBD_CoolantTemp.setText( String.format( getString( R.string.text_obd_coolant_temp), ""));
        tvOBD_FuelLevel.setText( String.format( getString( R.string.text_obd_fuel_level), ""));
        tvOBD_CMUVoltage.setText( String.format( getString( R.string.text_obd_cmu_voltage), ""));
        tvOBD_FuelConsumption.setText( String.format( getString( R.string.text_obd_fuel_consumption), ""));
        tvOBD_FuelConsumption2.setText( String.format( getString( R.string.text_obd_fuel_consumption), ""));

        tvOBD_MAF = (TextView) findViewById(R.id.tvOBD_MAF);
        tvOBD_MAF.setText("");
        tvOBD_AirIntakeTemp = (TextView) findViewById(R.id.tvOBD_AirIntakeTemp);
        tvOBD_AirIntakeTemp.setText("");



        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED));
    }

    public void onResume() {
        super.onResume();
        try {
            tvDeviceStatus.setText(String.format(getString(R.string.odbii_device_status), App.obd.isConnected ? "Подключен" : "Не подключен"));
            String str = "";
            if (App.obd.getDeviceName() != null) {
                str += App.obd.getDeviceName() + " / " + App.obd.getDeviceAddress();
            }
            tvDeviceName.setText(String.format(getString(R.string.odbii_device_name), str));
        } catch (Exception e) {
            Log.d("ODBActivity->OnResume", e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSelectDevice:
                show_odb_device_select_dialog( this );
                break;
            case R.id.btnOBDConnect:
                if ( !App.obd.isConnected) BackgroundService.startOBDThread();
                break;
            case R.id.btnOBDDisconnect:
                BackgroundService.stopOBDThread();
                break;
            case R.id.swUseOBD:
                App.obd.useOBD = swUseOBD.isChecked();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
                prefs.edit().putBoolean("ODBII_USE_BLUETOOTH", App.obd.useOBD).commit();
                break;
            default:
                break;
        }
    }

    public void show_odb_device_select_dialog(Context context) {
        ArrayList<String> deviceStrs = new ArrayList<String>();
        final ArrayList<String> devices = new ArrayList<String>();
        final ArrayList<String> devicesName = new ArrayList<String>();
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0)
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice,
                deviceStrs.toArray(new String[deviceStrs.size()]));

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
                prefs.edit().putString("ODBII_DEVICE_ADDRESS", App.obd.getDeviceAddress()).commit();
                prefs.edit().putString("ODBII_DEVICE_NAME", App.obd.getDeviceName()).commit();
            }
        });

        alertDialog.setTitle("Choose Bluetooth device");
        alertDialog.show();

    }

    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED)) {
                boolean res = intent.getBooleanExtra("Status", false);
                tvDeviceStatus.setText( String.format( getString( R.string.odbii_device_status ), res ? "Подключен" : "Не подключен"));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED)) {
                tvOBDEngineRPM.setText( String.format( getString( R.string.text_obd_engine_rpm), intent.getStringExtra("engineRPM")));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_SPEED_CHANGED)) {
                tvOBDSpeed.setText( String.format( getString( R.string.text_obd_speed), intent.getStringExtra("speed")));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED)) {
                tvOBD_CoolantTemp.setText( String.format( getString( R.string.text_obd_coolant_temp), intent.getStringExtra("coolantTemp")));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED)) {
                tvOBD_FuelLevel.setText( String.format( getString( R.string.text_obd_fuel_level), intent.getStringExtra("FuelLevel")));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED)) {
                tvOBD_CMUVoltage.setText( String.format( getString( R.string.text_obd_cmu_voltage), intent.getStringExtra("cmuVoltage")));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED)) {

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED)) {
                tvOBD_MAF.setText( String.format( "MAF: %1$s", intent.getStringExtra("sMAF")));
                tvOBD_FuelConsumption.setText( String.format( "Расход, л/ч: %1$.1f", App.obd.obdData.fuel_consump_lph));
                tvOBD_FuelConsumption2.setText( String.format( "Мгновенный расход, л/100км: %1$.1f", App.obd.obdData.fuel_consump_lpk_inst));

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED)) {
                tvOBD_AirIntakeTemp.setText( String.format( "AirIntakeTemp: %1$s", intent.getStringExtra("sAirIntakeTemp")));
            }
        }
    };


}
