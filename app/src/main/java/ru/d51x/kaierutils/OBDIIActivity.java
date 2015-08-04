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
import android.widget.CheckBox;
import android.widget.LinearLayout;
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
    private TextView tvOBD_FuelUsage;
    private TextView tvOBD_FuelUsage2;
    private TextView tvOBD_CMUVoltage;
    private TextView tvGPS_Distanse;
    private TextView tvOBD_FuelConsumption_lph;
    private TextView tvOBD_FuelConsumption_mpg;
    private TextView tvOBD_FuelConsumption_avg;
    private TextView tvOBD_FuelConsumption_avg2;
    private TextView tvOBD_FuelConsumption_total;
    private TextView tvOBD_FuelConsumption_total2;
    private TextView tvOBD_FuelUsageTotal;
    private TextView tvOBD_FuelUsageTotal2;

    private TextView tv_can_211D_A2;
    private TextView tv_can_211D_A3;
    private TextView tv_can_211D_A4;
    private TextView tv_can_211D_D0;

    private TextView tv_can_211E_A0;
    private TextView tv_can_211E_A3;
    private TextView tv_can_211E_A5;

    private TextView tv_can_2110_cvt_oil_degr;
    private TextView tv_can_2103_cvt_temp_count;
    private TextView tv_can_2103_cvt_temp_1;

    private TextView tvOBD_MAF;
    private TextView tvOBD_AirIntakeTemp;
    private Switch swUseOBD;
    private CheckBox cbCanMMC;
    private LinearLayout layoutCanMMC;

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
        tvOBD_FuelUsage = (TextView) findViewById(R.id.tvOBD_FuelUsage);
        tvOBD_FuelUsage2 = (TextView) findViewById(R.id.tvOBD_FuelUsage2);
        tvOBD_CMUVoltage = (TextView) findViewById(R.id.tvOBD_CMUVoltage);
        tvGPS_Distanse = (TextView) findViewById(R.id.tvGPS_Distanse);
        tvOBD_FuelConsumption_lph = (TextView) findViewById(R.id.tvOBD_FuelConsumption_lph);
        tvOBD_FuelConsumption_mpg = (TextView) findViewById(R.id.tvOBD_FuelConsumption_mpg);
        tvOBD_FuelConsumption_avg = (TextView) findViewById(R.id.tvOBD_FuelConsumption_avg);
        tvOBD_FuelConsumption_avg2 = (TextView) findViewById(R.id.tvOBD_FuelConsumption_avg2);
        tvOBD_FuelConsumption_total = (TextView) findViewById(R.id.tvOBD_FuelConsumption_total);
        tvOBD_FuelConsumption_total2 = (TextView) findViewById(R.id.tvOBD_FuelConsumption_total2);
        tvOBD_FuelUsageTotal = (TextView) findViewById(R.id.tvOBD_FuelUsageTotal);
        tvOBD_FuelUsageTotal2 = (TextView) findViewById(R.id.tvOBD_FuelUsageTotal2);

        tv_can_211D_A2 = (TextView) findViewById(R.id.tv_can_211D_A2);
        tv_can_211D_A3 = (TextView) findViewById(R.id.tv_can_211D_A3);
        tv_can_211D_A4 = (TextView) findViewById(R.id.tv_can_211D_A4);
        tv_can_211D_D0 = (TextView) findViewById(R.id.tv_can_211D_D0);

        tv_can_211E_A0 = (TextView) findViewById(R.id.tv_can_211E_A0);
        tv_can_211E_A3 = (TextView) findViewById(R.id.tv_can_211E_A3);
        tv_can_211E_A5 = (TextView) findViewById(R.id.tv_can_211E_A5);

        tv_can_2110_cvt_oil_degr = (TextView) findViewById(R.id.tv_can_2110_cvt_oil_degr);
        tv_can_2103_cvt_temp_count = (TextView) findViewById(R.id.tv_can_2103_cvt_temp_count);
        tv_can_2103_cvt_temp_1 = (TextView) findViewById(R.id.tv_can_2103_cvt_temp_1);

        swUseOBD = (Switch) findViewById(R.id.swUseOBD);
	    swUseOBD.setOnClickListener(this);
	    swUseOBD.setChecked(App.obd.useOBD);

        cbCanMMC = (CheckBox) findViewById(R.id.cbCanMMC);
        cbCanMMC.setOnClickListener(this);
        cbCanMMC.setChecked( App.obd.MMC_CAN);

        layoutCanMMC = (LinearLayout) findViewById(R.id.layoutCanMMC);
        layoutCanMMC.setVisibility( App.obd.MMC_CAN ? View.VISIBLE : View.GONE);

        tvOBDEngineRPM.setText (String.format (getString (R.string.text_obd_engine_rpm), ""));
        tvOBDSpeed.setText( String.format( getString( R.string.text_obd_speed), ""));
        tvOBD_CoolantTemp.setText( String.format( getString( R.string.text_obd_coolant_temp), ""));
        tvOBD_FuelUsage.setText(String.format(getString(R.string.text_obd_fuel_usage), 0.0f));
        tvOBD_FuelUsage2.setText(String.format(getString(R.string.text_obd_fuel_usage_with_stops), 0.0f));
        tvOBD_CMUVoltage.setText( String.format( getString( R.string.text_obd_cmu_voltage), ""));
        tvGPS_Distanse.setText( String.format( getString( R.string.text_obd_distanse), 0.0f));
        tvOBD_FuelConsumption_lph.setText( String.format( getString( R.string.text_obd_fuel_consumption_lph), 0.0f));
        tvOBD_FuelConsumption_mpg.setText( String.format( getString( R.string.text_obd_fuel_consumption_mpg), 0.0f));
        tvOBD_FuelConsumption_avg.setText( String.format( getString( R.string.text_obd_fuel_consumption_avg), 0.0f));
        tvOBD_FuelConsumption_avg2.setText( String.format( getString( R.string.text_obd_fuel_consumption_avg2), 0.0f));
        tvOBD_FuelConsumption_total.setText( String.format( getString( R.string.text_obd_fuel_consumption_avg), 0.0f));
        tvOBD_FuelConsumption_total2.setText( String.format( getString( R.string.text_obd_fuel_consumption_avg2), 0.0f));
        tvOBD_FuelUsageTotal.setText(String.format(getString(R.string.text_obd_fuel_usage), 0.0f));
        tvOBD_FuelUsageTotal2.setText(String.format(getString(R.string.text_obd_fuel_usage_with_stops), 0.0f));

        tvOBD_MAF = (TextView) findViewById(R.id.tvOBD_MAF);
        tvOBD_MAF.setText("");
        tvOBD_AirIntakeTemp = (TextView) findViewById(R.id.tvOBD_AirIntakeTemp);
        tvOBD_AirIntakeTemp.setText("");

        tv_can_211D_A2.setText(String.format( getString( R.string.text_can_211D_A2), "---"));
        tv_can_211D_A3.setText(String.format( getString( R.string.text_can_211D_A3), "---"));
        tv_can_211D_A4.setText(String.format( getString( R.string.text_can_211D_A4), "---"));
        tv_can_211D_D0.setText(String.format( getString( R.string.text_can_211D_D0), "---"));

        tv_can_211E_A0.setText(String.format( getString( R.string.text_can_211E_A0), "---"));
        tv_can_211E_A3.setText(String.format( getString( R.string.text_can_211E_A3), "---"));
        tv_can_211E_A5.setText(String.format( getString( R.string.text_can_211E_A5), "---"));

        tv_can_2110_cvt_oil_degr.setText(String.format( getString( R.string.text_can_2110_cvt_oil_degr), "---"));
        tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), "---"));
        tv_can_2103_cvt_temp_1.setText(String.format( getString( R.string.text_can_2103_cvt_temp_1), "---"));



        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_STATE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_PID_211D_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_PID_211E_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_PID_2103__7E1_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_PID_2110__7E1_CHANGED));
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
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
                prefs.edit().putBoolean("ODBII_USE_BLUETOOTH", App.obd.useOBD).apply();
                // connect or disconnect
                setOnOffOBD( App.obd.useOBD );
                break;
            case R.id.cbCanMMC:
                App.obd.MMC_CAN = cbCanMMC.isChecked();
                layoutCanMMC.setVisibility( App.obd.MMC_CAN ? View.VISIBLE : View.GONE);
                prefs.edit().putBoolean("ODBII_CAN_MMC", App.obd.MMC_CAN).apply();
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

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED)) {
                tvOBD_CMUVoltage.setText( String.format( getString( R.string.text_obd_cmu_voltage), intent.getStringExtra("cmuVoltage")));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED)) {

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED)) {
                tvOBD_MAF.setText( String.format( "MAF: %1$s", intent.getStringExtra("sMAF")));
                tvOBD_FuelConsumption_lph.setText(String.format(getString(R.string.text_obd_fuel_consumption_lph), App.obd.oneTrip.fuel_cons_lph));
                tvOBD_FuelConsumption_mpg.setText(String.format(getString(R.string.text_obd_fuel_consumption_mpg), App.obd.oneTrip.fuel_cons_lp100km_inst));
                tvOBD_FuelConsumption_avg.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg), App.obd.oneTrip.fuel_cons_lp100km_avg));
                tvOBD_FuelConsumption_avg2.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg2), App.obd.oneTrip.fuel_cons_lp100km_avg_wo_stops));
                tvOBD_FuelUsage.setText(String.format(getString(R.string.text_obd_fuel_usage), App.obd.oneTrip.fuel_usage));
                tvOBD_FuelUsage2.setText(String.format(getString(R.string.text_obd_fuel_usage_with_stops), App.obd.oneTrip.fuel_usage_wo_stops));
                tvGPS_Distanse.setText(String.format(getString(R.string.text_obd_distanse), App.obd.oneTrip.distance / 1000f));

                tvOBD_FuelConsumption_total.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg), App.obd.totalTrip.fuel_cons_lp100km_avg));
                tvOBD_FuelConsumption_total2.setText(String.format(getString(R.string.text_obd_fuel_consumption_avg2), App.obd.totalTrip.fuel_cons_lp100km_avg_wo_stops));
                tvOBD_FuelUsageTotal.setText(String.format(getString(R.string.text_obd_fuel_usage), App.obd.totalTrip.fuel_usage));
                tvOBD_FuelUsageTotal2.setText(String.format(getString(R.string.text_obd_fuel_usage_with_stops), App.obd.totalTrip.fuel_usage_wo_stops));

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED)) {
                tvOBD_AirIntakeTemp.setText( String.format( "AirIntakeTemp: %1$s", intent.getStringExtra("sAirIntakeTemp")));
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_AC_STATE_CHANGED) ) {
//                boolean ac = intent.getBooleanExtra("AirCond", false);
//                if ( ac ) tv_can_211D_A3.setText(String.format( getString( R.string.text_can_211D_A3), "ВКЛ"));
//                else tv_can_211D_A3.setText(String.format( getString( R.string.text_can_211D_A3), "ВЫКЛ"));

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED) ) {
//                boolean fan = intent.getBooleanExtra("EngineFan", false);
//                if ( fan ) tv_can_211E_A3.setText(String.format( getString( R.string.text_can_211E_A3), "ВКЛ"));
//                else tv_can_211E_A3.setText(String.format( getString( R.string.text_can_211E_A3), "ВЫКЛ"));

                //boolean AirCond = ( buffer_211D.get(2) & 0x8) == 0 ? false : true;
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_PID_211D_CHANGED) ) {
                ArrayList<Integer> buffer_211D = intent.getIntegerArrayListExtra("PID_211D");
                update_can_211D_data(buffer_211D);

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_PID_211E_CHANGED) ) {
                ArrayList<Integer> buffer_211E = intent.getIntegerArrayListExtra("PID_211E");
                update_can_211D_data(buffer_211E);
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_PID_2110__7E1_CHANGED) ) {
                ArrayList<Integer> buffer_2110 = intent.getIntegerArrayListExtra("PID_2110__7E1");
                update_can_2110_data(buffer_2110);
            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_PID_2103__7E1_CHANGED) ) {
                ArrayList<Integer> buffer_2103 = intent.getIntegerArrayListExtra("PID_2103__7E1");
                update_can_2103_data(buffer_2103);
            }

        }
    };

    public void update_can_211D_data(ArrayList<Integer> buffer) {
        // Сигнал нагрузки кондиционера 211D {A:2}
        if (( buffer.get(2) & 0x4) > 0 ) {
            tv_can_211D_A2.setText(String.format(getString(R.string.text_can_211D_A2), "ВКЛ"));
        } else {
            tv_can_211D_A2.setText(String.format( getString( R.string.text_can_211D_A2), "ВЫКЛ"));
        }

        // Выключатель кондиционера 211D {A:3}
        if (( buffer.get(2) & 0x8) > 0 ) {
            tv_can_211D_A3.setText(String.format(getString(R.string.text_can_211D_A3), "ВКЛ"));
        } else {
            tv_can_211D_A3.setText(String.format( getString( R.string.text_can_211D_A3), "ВЫКЛ"));
        }

        // Выключатель гидроусилителя руля 211D {A:4}
        if (( buffer.get(2) & 0x16) > 0 ) {
            tv_can_211D_A4.setText(String.format(getString(R.string.text_can_211D_A4), "ВКЛ"));
        } else {
            tv_can_211D_A4.setText(String.format( getString( R.string.text_can_211D_A4), "ВЫКЛ"));
        }

        // Выключательлампы тормоза 211D {D:0}
        // A - 2, B - 3, C - 4, В- 5
        if (( buffer.get(5) & 0x1) > 0 ) {
            tv_can_211D_D0.setText(String.format(getString(R.string.text_can_211D_D0), "ВКЛ"));
        } else {
            tv_can_211D_D0.setText(String.format( getString( R.string.text_can_211D_D0), "ВЫКЛ"));
        }
    }

    public void update_can_211E_data(ArrayList<Integer> buffer) {
        // Реле компрессора кондиционера 211E {A:0}
        if (( buffer.get(2) & 0x1) > 0 ) {
            tv_can_211E_A0.setText(String.format(getString(R.string.text_can_211E_A0), "ВКЛ"));
        } else {
            tv_can_211E_A0.setText(String.format( getString( R.string.text_can_211E_A0), "ВЫКЛ"));
        }

        // Реле вентилятора радиатора(низкая) 211E {A:3}
        if (( buffer.get(2) & 0x8) > 0 ) {
            tv_can_211E_A3.setText(String.format(getString(R.string.text_can_211E_A3), "ВКЛ"));
        } else {
            tv_can_211E_A3.setText(String.format( getString( R.string.text_can_211E_A3), "ВЫКЛ"));
        }

        // Реле управления двигателем 211E {A:5}
        if (( buffer.get(2) & 32) > 0 ) {
            tv_can_211E_A5.setText(String.format(getString(R.string.text_can_211E_A5), "ВКЛ"));
        } else {
            tv_can_211E_A5.setText(String.format( getString( R.string.text_can_211E_A5), "ВЫКЛ"));
        }
    }

    public void update_can_2110_data(ArrayList<Integer> buffer) {
        // CVT oil degradation 2110   AB*6553 + AC*256+AD
        int degr = buffer.get(29)*65536 + buffer.get(30)*256 + buffer.get(31);
        tv_can_2110_cvt_oil_degr.setText(String.format( getString( R.string.text_can_2110_cvt_oil_degr), Integer.toString( degr)));
  }

    public void update_can_2103_data(ArrayList<Integer> buffer) {
        // CVT temp count 2103   N
        int T = buffer.get(15);
        tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), Integer.toString( T )));

        // CVT temp 1 2103
        double temp_1 = (0.000000002344*( T ^5))+(-0.000001387*(T^4))+(0.0003193*(T^3))+(-0.03501*(T^2))+(2.302*T)+(-36.6);
        tv_can_2103_cvt_temp_1.setText(String.format(getString(R.string.text_can_2103_cvt_temp_1), Double.toString(temp_1)));
    }

}
