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

    private TextView tv_can_211D_A3;



    private TextView tv_can_2110_cvt_oil_degr;
    private TextView tv_can_2103_cvt_temp_count;


    private TextView tv_air_cond_external_temp;

    private TextView tv_air_cond_fan_speed_state;
    private TextView tv_air_cond_fan_speed_position;
    private TextView tv_air_cond_blow_direction_state;
    private TextView tv_air_cond_blow_direction_position;
    private TextView tv_air_cond_set_temperature;
    private TextView tv_air_cond_recirculation_state;
    private TextView tv_air_cond_state;
    private TextView tv_air_cond_defogger_state;

    private TextView tv_awc_4wd_mode;

    private TextView tvFuelLevel;

    private TextView tvOBD_MAF;
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

        tv_can_211D_A3 = (TextView) findViewById(R.id.tv_can_211D_A3);


        tv_can_2110_cvt_oil_degr = (TextView) findViewById(R.id.tv_can_2110_cvt_oil_degr);
        tv_can_2103_cvt_temp_count = (TextView) findViewById(R.id.tv_can_2103_cvt_temp_count);

        tv_air_cond_external_temp = (TextView) findViewById(R.id.tv_air_cond_external_temp);

        tv_air_cond_fan_speed_state = (TextView) findViewById(R.id.tv_air_cond_fan_speed_state);
        tv_air_cond_fan_speed_position = (TextView) findViewById(R.id.tv_air_cond_fan_speed_position);
        tv_air_cond_blow_direction_state = (TextView) findViewById(R.id.tv_air_cond_blow_direction_state);
        tv_air_cond_blow_direction_position = (TextView) findViewById(R.id.tv_air_cond_blow_direction_position);
        tv_air_cond_set_temperature = (TextView) findViewById(R.id.tv_air_cond_set_temperature);
        tv_air_cond_recirculation_state = (TextView) findViewById(R.id.tv_air_cond_recirculation_state);
        tv_air_cond_state = (TextView) findViewById(R.id.tv_air_cond_state);
        tv_air_cond_defogger_state = (TextView) findViewById(R.id.tv_air_cond_defogger_state);

        tv_awc_4wd_mode = (TextView) findViewById(R.id.tv_awc_4wd_mode);

        tvFuelLevel = (TextView) findViewById(R.id.tvFuelLevel);


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


        tv_can_211D_A3.setText(String.format( getString( R.string.text_can_211D_A3), "---"));


        tv_can_2110_cvt_oil_degr.setText(String.format( getString( R.string.text_can_2110_cvt_oil_degr), "---"));
        tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), "---", "---"));

        tv_air_cond_external_temp.setText(String.format( getString( R.string.text_air_cond_external_temp), "---"));



        tv_air_cond_fan_speed_state.setText(String.format( getString( R.string.text_air_cond_fan_speed), "---"));
        tv_air_cond_fan_speed_position.setText(String.format( getString( R.string.text_air_cond_fan_speed), "---"));
        tv_air_cond_blow_direction_state.setText(String.format( getString( R.string.text_air_cond_blow_direction), "---"));
        tv_air_cond_blow_direction_position.setText(String.format( getString( R.string.text_air_cond_blow_direction), "---"));
        tv_air_cond_set_temperature.setText(String.format( getString( R.string.text_air_cond_set_temperature), "---"));
        tv_air_cond_recirculation_state.setText(String.format( getString( R.string.text_air_cond_recirculation_state), "---"));
        tv_air_cond_state.setText(String.format( getString( R.string.text_air_cond_state), "---"));
        tv_air_cond_defogger_state.setText(String.format( getString( R.string.text_air_cond_defogger_state), "---"));




        tv_awc_4wd_mode.setText(String.format( getString( R.string.text_awc_4wd_mode), "---"));

        tvFuelLevel.setText(String.format( getString( R.string.text_obd_can_fuel_level), "---"));



        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_STATUS_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_SPEED_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_MAF_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_AC_STATE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED));

        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_CVT_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED));
        registerReceiver(receiver, new IntentFilter(OBDII.OBD_BROADCAST_ACTION_ECU_AWC_CHANGED));

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
                prefs.edit().putBoolean("ODBII_USE_MMC_CAN", App.obd.MMC_CAN).apply();
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

    public static void show_odb_device_select_dialog(Context context) {
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.select_dialog_singlechoice,
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

            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_AC_STATE_CHANGED) ) {


            } else if ( action.equals(OBDII.OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED) ) {

            }
            else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED )) {
                String extra = intent.getStringExtra("PID");
                ArrayList<Integer> buffer = intent.getIntegerArrayListExtra("Buffer");
                String res = "";

                if ( extra.equalsIgnoreCase( "211D:7E0" ) ) {
                    // get "engine_aircond_state"
                    res = App.obd.process_MMC_ECU_data( "engine_aircond_state", action, extra, buffer);
                    tv_can_211D_A3.setText(String.format(getString(R.string.text_can_211D_A3), res));

                }
            }
            else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_ECU_CVT_CHANGED )) {
                String extra = intent.getStringExtra("PID");
                ArrayList<Integer> buffer = intent.getIntegerArrayListExtra("Buffer");
                String res = "";

                if ( extra.equalsIgnoreCase( "2110:7E1" ) ) {
                    // cvt_oil_degradation
                    res = App.obd.process_MMC_ECU_data( "cvt_oil_degradation", action, extra, buffer);
                    tv_can_2110_cvt_oil_degr.setText(String.format(getString(R.string.text_can_2110_cvt_oil_degr), res));
                }
                else if ( extra.equalsIgnoreCase( "2103:7E1" ) ) {
                    // cvt_temp_count
                    res = App.obd.process_MMC_ECU_data( "cvt_temp_count", action, extra, buffer);

                    // cvt_temp_grad
                    String res2 = App.obd.process_MMC_ECU_data( "cvt_temp_grad", action, extra, buffer);

                    tv_can_2103_cvt_temp_count.setText(String.format( getString( R.string.text_can_2103_cvt_temp_count), res, res2));
                }
            }
            else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED )) {
                String extra = intent.getStringExtra("PID");
                ArrayList<Integer> buffer = intent.getIntegerArrayListExtra("Buffer");
                String res = "";

                if ( extra.equalsIgnoreCase( "2111:688" ) ) {
                    // air_cond_external_temp
                    res = App.obd.process_MMC_ECU_data( "air_cond_external_temp", action, extra, buffer);
                    tv_air_cond_external_temp.setText(String.format( getString( R.string.text_air_cond_external_temp), res));
                }
                else if ( extra.equalsIgnoreCase( "2161:688" ) ) {
                    // air_cond_request_indicator_light
                    res = App.obd.process_MMC_ECU_data( "air_cond_fan_speed_state", action, extra, buffer);
                    tv_air_cond_fan_speed_state.setText(String.format( getString( R.string.text_air_cond_fan_speed), res));

                    res = App.obd.process_MMC_ECU_data( "air_cond_blow_direction_state", action, extra, buffer);
                    tv_air_cond_blow_direction_state.setText(String.format( getString( R.string.text_air_cond_blow_direction), res));

                    res = App.obd.process_MMC_ECU_data( "air_cond_recirculation_state", action, extra, buffer);
                    tv_air_cond_recirculation_state.setText(String.format( getString( R.string.text_air_cond_recirculation_state), res));

                    res = App.obd.process_MMC_ECU_data( "air_cond_state", action, extra, buffer);
                    tv_air_cond_state.setText(String.format( getString( R.string.text_air_cond_state), res));

                    res = App.obd.process_MMC_ECU_data( "air_cond_defogger_state", action, extra, buffer);
                    tv_air_cond_defogger_state.setText(String.format( getString( R.string.text_air_cond_defogger_state), res));

                }
                else if ( extra.equalsIgnoreCase( "2160:688" ) ) {
                    // air_cond_oper_state
                    res = App.obd.process_MMC_ECU_data( "air_cond_fan_speed_position", action, extra, buffer);
                    tv_air_cond_fan_speed_position.setText(String.format( getString( R.string.text_air_cond_fan_speed), res));

                    res = App.obd.process_MMC_ECU_data( "air_cond_blow_direction_position", action, extra, buffer);
                    tv_air_cond_blow_direction_position.setText(String.format( getString( R.string.text_air_cond_blow_direction), res));

                    res = App.obd.process_MMC_ECU_data( "air_cond_set_temperature", action, extra, buffer);
                    tv_air_cond_set_temperature.setText(String.format( getString( R.string.text_air_cond_set_temperature), res));
                }
            }
            else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED )) {
                String extra = intent.getStringExtra("PID");
                ArrayList<Integer> buffer = intent.getIntegerArrayListExtra("Buffer");
                String res = "";

                if ( extra.equalsIgnoreCase( "21A3:6A0" ) ) {
                    // combine_meter_fuel_level
                    res = App.obd.process_MMC_ECU_data( "combine_meter_fuel_level", action, extra, buffer);
                    tvFuelLevel.setText(String.format( getString( R.string.text_obd_can_fuel_level), res));
                }
            }
            else if ( action.equals( OBDII.OBD_BROADCAST_ACTION_ECU_AWC_CHANGED)) {
                String extra = intent.getStringExtra("PID");
                ArrayList<Integer> buffer = intent.getIntegerArrayListExtra("Buffer");
                String res = "";

                if ( extra.equalsIgnoreCase( "2130:7B6" ) ) {
                    res = App.obd.process_MMC_ECU_data( "awc_4wd_mode", action, extra, buffer);
                    tv_awc_4wd_mode.setText(String.format(getString(R.string.text_awc_4wd_mode), res));
                }
            }
        }
    };


}
