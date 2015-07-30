package ru.d51x.kaierutils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


import pt.lighthouselabs.obd.commands.engine.EngineRPMObdCommand;
import pt.lighthouselabs.obd.commands.SpeedObdCommand;
import pt.lighthouselabs.obd.commands.can.CanObdCommand;
import pt.lighthouselabs.obd.commands.engine.MassAirFlowObdCommand;
import pt.lighthouselabs.obd.commands.temperature.EngineCoolantTemperatureObdCommand;
import pt.lighthouselabs.obd.commands.temperature.AirIntakeTemperatureObdCommand;
import pt.lighthouselabs.obd.commands.control.ControlModuleVoltageObdCommand;


import pt.lighthouselabs.obd.commands.protocol.EchoOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.LineFeedOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectProtocolObdCommand;
import pt.lighthouselabs.obd.enums.ObdProtocols;
import pt.lighthouselabs.obd.exceptions.*;
/**
 * Created by pyatyh_ds on 18.03.2015.
 */
public class OBDII {

    protected static final boolean newMethod = true;

    protected static final String OBD_BROADCAST_ACTION_STATUS_CHANGED = "ru.d51x.kaierutils.action.OBD_STATUS_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_SPEED_CHANGED = "ru.d51x.kaierutils.action.OBD_SPEED_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED = "ru.d51x.kaierutils.action.OBD_ENGINE_RPM_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_COOLANT_TEMP_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_FUEL_LEVEL_CHANGED = "ru.d51x.kaierutils.action.OBD_FUEL_LEVEL_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED = "ru.d51x.kaierutils.action.OBD_CMU_VOLTAGE_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED = "ru.d51x.kaierutils.action.OBD_FUEL_CONSUMPTION_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_MAF_CHANGED = "ru.d51x.kaierutils.action.OBD_MAF_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_AIR_INTAKE_TEMP_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AC_STATE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_AC_STATE_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ENGINE_FAN_STATE_CHANGED";

    private Context mContext;
    private String deviceName;
    private String deviceAddress;
    public boolean isConnected;

    private BluetoothSocket socket;

    public CanObdCommand can_211E_ObdCommand;
    public CanObdCommand can_211D_ObdCommand;

    public EngineRPMObdCommand engineRpmCommand;
    public SpeedObdCommand speedCommand;
    public EngineCoolantTemperatureObdCommand coolantTempCommand;
    // private FuelLevelObdCommand fuelLevelCommand;
    public ControlModuleVoltageObdCommand cmuVoltageCommand;
    //  private FuelEconomyObdCommand fuelEconomyObdCommand;
    public MassAirFlowObdCommand MAFObdCommand;
    public AirIntakeTemperatureObdCommand airIntakeTemperatureObdCommand;
    private Handler mHandler;

    public boolean useOBD;
    public OBDData obdData;

    public boolean MMC_CAN;

    public TripData totalTrip;
    public TripData oneTrip;


    public class OBDData {
        public float speed;
        public float rpm;
        public float coolant;
        public float maf;
        public float voltage;
        public float airIntake;
        public float fuel_tank; // читаем из преференсов

        public OBDData() {
            speed = 0;
            rpm = 0;
            coolant = 0;
            maf = 0;
            voltage = 0;
            airIntake = 0;
            fuel_tank = 0;
        }
    };


    public OBDII(Context context) {
        mContext = context;
        deviceName = null;
        deviceAddress = null;
        isConnected = false;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        deviceAddress = prefs.getString("ODBII_DEVICE_ADDRESS", null);
        deviceName = prefs.getString("ODBII_DEVICE_NAME", null);
        socket = null;
        mHandler = new Handler();
        useOBD = prefs.getBoolean("ODBII_USE_BLUETOOTH", false);
        MMC_CAN = prefs.getBoolean("ODBII_USE_MMC_CAN", false);
        obdData = new OBDData();
        totalTrip = new TripData("total", true);
        oneTrip = new TripData("trip", false);
        loadFuelTank();
        // запустить бесконечный цикл
        // если подключены, то выполняем команды
        // если не подключены, то пытаемся подключиться

    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String name) {
        deviceName = name;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String address) {
        deviceAddress = address;
    }

    public boolean connect() {
	    Log.d("OBD2->connect()", "useOBD is " + String.valueOf (useOBD));
        if ( !useOBD ) return false;
        if ( deviceAddress == null) return false;
	    Log.d("OBD2->connect()", "isConnected is " + String.valueOf (isConnected));
        //if ( isConnected ) return;
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        try {
            try {
                btAdapter.cancelDiscovery();
            } catch (Exception e11) {
                Log.d("OBD2->connect()", e11.getMessage());
                return false;
            }
            socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
            //
            if ( socket != null ) {
                Thread.sleep(1000);
                Log.d("OBD2->connect()", "try to connect to socket ");
                socket.connect();
                Log.d("OBD2->connect()", "sleep after connect, waiting....");
                Thread.sleep(1000);
                boolean res = init();
                SendBroadcastAction(OBD_BROADCAST_ACTION_STATUS_CHANGED, "Status", res);
                return res;
            } else {
                Log.d("OBD2->connect()", "socket is NULL....");
                return false;
            }

       } catch (Exception e) {
            Log.d("OBDII-->connect()", e.toString());
            return false;
        }
    }

    public void disconnect() {
        try {
	        Log.d("OBD2->connect()", "try to close socked ");
            socket.close();
	        Log.d("OBD2->connect()", "socket is closed");

        } catch (Exception e) {
            Log.d("OBDII-->disconnect()", e.toString());
        }
        notify_disconnect();
    }

    public boolean init() {
        boolean res = true;
	    Log.d("OBD2->init()", "_");
        try {

            // echo off: "AT E0"
            EchoOffObdCommand cmd1 = new EchoOffObdCommand();
            cmd1.run(socket.getInputStream(), socket.getOutputStream());
            Log.d("OBD2->init(): ", "echoOff result:  !" + cmd1.getFormattedResult());
            res = res && (cmd1.getFormattedResult().equalsIgnoreCase("OK"));
            if ( !res ) return res;

            // "AT L0"
            LineFeedOffObdCommand cmd2 = new LineFeedOffObdCommand();
            cmd2.run(socket.getInputStream(), socket.getOutputStream());
            Log.d("OBD2->init(): ", "LineFeedOff result:  !" + cmd2.getFormattedResult());
            res = res && (cmd2.getFormattedResult().equalsIgnoreCase("OK"));
            if ( !res ) return res;

            // AT ST
            //new TimeoutObdCommand(0).run(socket.getInputStream(), socket.getOutputStream());

            // AT SP
	        Log.d("OBD2->init()", "set protocol to ISO 15765-4 CAN");
            SelectProtocolObdCommand cmd3 = new SelectProtocolObdCommand(ObdProtocols.ISO_15765_4_CAN);
            cmd3.run(socket.getInputStream(), socket.getOutputStream());
            Log.d("OBD2->init(): ", "SelectProtocol result:  !" + cmd3.getFormattedResult());
            res = res && (cmd3.getFormattedResult().equalsIgnoreCase("OK"));
            if ( !res ) return res;
            return res;
        } catch (Exception e2) {
            Log.d("OBDII-->init()", e2.toString());
            return false;
        }
    }

    public void prepareData() {
        if ( !isConnected ) return;
        engineRpmCommand = new EngineRPMObdCommand();
        speedCommand = new SpeedObdCommand();
        coolantTempCommand = new EngineCoolantTemperatureObdCommand();
        // fuelLevelCommand = new FuelLevelObdCommand();        // not working  01 2f  no data
        cmuVoltageCommand = new ControlModuleVoltageObdCommand();
        // fuelEconomyObdCommand = new FuelEconomyObdCommand();   // not working  01 5e   n0 data
        MAFObdCommand = new MassAirFlowObdCommand();
        airIntakeTemperatureObdCommand = new AirIntakeTemperatureObdCommand();
        can_211E_ObdCommand = new CanObdCommand("21 1E");  // реле компрессора кондея, реле вентилятора радиатора
        can_211D_ObdCommand = new CanObdCommand("21 1D");  // выключатели компрессора кондея, вентилятора радиатора


    }

    public void processData() {
        if ( !isConnected ) return;
        //Log.d("OBDII-->processData()", "_");
        processOBD_EngineRPM();         // RPM:             01 0C
        processOBD_Speed();             // Speed:           01 0D
        processOBD_coolantTemp();       // Coolant:         01 05
        processOBD_CMVoltage();         // Voltage:         01 42
        processOBD_AirIntake();         // AirIntake:       01 0F
        // к этому моменту уже должна пройти 1 сек = 5 функций с задержкой 200 мсек
        //processOBD_MAF();               // MAF:             01 10

        processCAN_211D();
        processCAN_211E();
        //Thread.sleep(500);
    }



    private void processOBD_EngineRPM() {
        try {
            engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.rpm = engineRpmCommand.getRPM();
            SendBroadcastAction(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED, "engineRPM", engineRpmCommand.getFormattedResult());
           // Log.d("OBDII-->processOBD_EngineRPM()", "RPM: " + engineRpmCommand.getFormattedResult());
        } catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_EngineRPM()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_EngineRPM()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_EngineRPM()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_EngineRPM()", e2.toString());

        }
    }

    private void processOBD_Speed() {
        try {
            speedCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.speed = speedCommand.getMetricSpeed();
            SendBroadcastAction(OBD_BROADCAST_ACTION_SPEED_CHANGED, "speed", speedCommand.getFormattedResult());
            //Log.d("OBDII-->processOBD_Speed()", "Speed: " + speedCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("processOBD_Speed()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("processOBD_Speed()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("processOBD_Speed()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("processOBD_Speed()", e2.toString());
        }
    }

    private void processOBD_coolantTemp() {
        try {
            coolantTempCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.coolant = coolantTempCommand.getTemperature();
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("coolantTemp", coolantTempCommand.getFormattedResult());
            intent.putExtra("coolantTempD", obdData.coolant);
            intent.setAction(OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED);
            App.getInstance ().sendBroadcast(intent);

            //Log.d("OBDII-->processOBD_coolantTemp()", "coolantTemp: " + coolantTempCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("processOBD_coolantTemp()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("processOBD_coolantTemp()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("processOBD_coolantTemp()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("processOBD_coolantTemp()", e2.toString());
        }
    }

    private void processOBD_CMVoltage() {
        try {
            cmuVoltageCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.voltage = cmuVoltageCommand.getVoltage();
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("cmuVoltage", cmuVoltageCommand.getFormattedResult());
            intent.putExtra("cmuVoltageD", obdData.voltage);
            intent.setAction(OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            //Log.d("OBDII-->processOBD_CMVoltage()", "cmuVoltage: " + cmuVoltageCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_CMVoltage()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_CMVoltage()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_CMVoltage()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_CMVoltage()", e2.toString());
        }
    }

    public void processOBD_MAF() {
        try {
            MAFObdCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.maf = MAFObdCommand.getMAF();

            oneTrip.calculateData(obdData.speed, obdData.maf, 0);
            totalTrip.calculateData(obdData.speed, obdData.maf, 0);

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("sMAF", MAFObdCommand.getFormattedResult());
            intent.putExtra("dMAF", obdData.maf);
            intent.setAction(OBD_BROADCAST_ACTION_MAF_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->processOBD_MAF()", "MAF: " + MAFObdCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_MAF()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_MAF()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_MAF()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_MAF()", e2.toString());
        }
    }

    private void processOBD_AirIntake() {
        try {
            airIntakeTemperatureObdCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.airIntake = airIntakeTemperatureObdCommand.getTemperature();
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("sAirIntakeTemp", airIntakeTemperatureObdCommand.getFormattedResult());
            intent.putExtra("dAirIntakeTemp", obdData.airIntake);
            intent.setAction(OBD_BROADCAST_ACTION_AIR_INTAKE_TEMP_CHANGED);
            App.getInstance ().sendBroadcast(intent);
          //  Log.d("OBDII-->processOBD_AirIntake()", "AirIntakeTemperature: " + airIntakeTemperatureObdCommand.getFormattedResult());
        }   catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        } catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_AirIntake()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_AirIntake()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_AirIntake()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_AirIntake()", e2.toString());
        }
    }


    public void processCAN_211D() {
        try {
            can_211D_ObdCommand.run(socket.getInputStream(), socket.getOutputStream());


            ArrayList<Integer> buffer_211D = null;
            buffer_211D = can_211D_ObdCommand.getBuffer();

            boolean EngineFan = ( buffer_211D.get(2) & 3) > 0 ? true: false;

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("EngineFan", EngineFan);
            //intent.putExtra("dMAF", obdData.maf);
            intent.setAction(OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->processCAN_211D()", "EngineFan: " + Boolean.toString( EngineFan ));
        }  catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processCAN_211D()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("OBDII-->processCAN_211D()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processCAN_211D()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processCAN_211D()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processCAN_211D()", e2.toString());
        }
    }

    public void processCAN_211E() {
        try {
            can_211E_ObdCommand.run(socket.getInputStream(), socket.getOutputStream());


            ArrayList<Integer> buffer_211E = null;
            buffer_211E = can_211E_ObdCommand.getBuffer();

            boolean AirCond = ( buffer_211E.get(2) & 3) > 0 ? true: false;

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("AirCond", AirCond);
            //intent.putExtra("dMAF", obdData.maf);
            intent.setAction(OBD_BROADCAST_ACTION_AC_STATE_CHANGED);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->processCAN_211E()", "AirCond: " + Boolean.toString( AirCond ));
        }  catch ( NonNumericResponseException e5) {
            disconnect();
            Log.d("OBDII-->processCAN_211E()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("OBDII-->processCAN_211E()", e.toString());
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processCAN_211E()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processCAN_211E()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processCAN_211E()", e2.toString());
        }
    }

    private void notify_disconnect() {
        isConnected = false;
        socket = null;
        SendBroadcastAction(OBD_BROADCAST_ACTION_STATUS_CHANGED, "Status", false);
    }

    private void SendBroadcastAction(String action, String key, String value) {
       // Log.d ("OBDII", "SendBroadcastAction " + action + " key = " + key + " value = " + value);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }

    private void SendBroadcastAction(String action, String key, boolean value) {
        // Log.d ("OBDII", "SendBroadcastAction " + action + " key = " + key + " value = " + value);
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        if ( key != null ) {
            intent.putExtra(key, value);
        }
        intent.setAction(action);
        App.getInstance ().sendBroadcast(intent);
    }


    public void saveFuelTank() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getInstance());
        prefs.edit().putFloat("kaierutils_fuel_tank", obdData.fuel_tank).apply();
        //prefs.edit().putFloat("kaierutils_fuel_usage", obdData.fuel_usage2).apply();

    }

    public void loadFuelTank() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences (App.getInstance ());
        obdData.fuel_tank = prefs.getFloat("kaierutils_fuel_tank", 60f);
        //obdData.fuel_usage2 = prefs.getFloat("kaierutils_fuel_usage", 0f);
    }


    // заправили полный бак
    public void setFullTank() {

        oneTrip.updateData( true, obdData.fuel_tank, obdData.fuel_tank);
        totalTrip.updateData( true, obdData.fuel_tank, obdData.fuel_tank);
        saveFuelTank();
    }

    // заправили не полный бак / коррекция значений
    public void setCustomTank(float tank_volume, float fuel_remain) {
        obdData.fuel_tank = tank_volume;

        oneTrip.updateData( false, fuel_remain, tank_volume);
        totalTrip.updateData( false, fuel_remain, tank_volume);
        saveFuelTank();
    }
}
