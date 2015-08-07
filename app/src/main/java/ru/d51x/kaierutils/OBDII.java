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
import pt.lighthouselabs.obd.commands.protocol.ObdResetCommand;
import pt.lighthouselabs.obd.commands.temperature.EngineCoolantTemperatureObdCommand;
import pt.lighthouselabs.obd.commands.temperature.AirIntakeTemperatureObdCommand;
import pt.lighthouselabs.obd.commands.control.ControlModuleVoltageObdCommand;


import pt.lighthouselabs.obd.commands.protocol.EchoOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.LineFeedOffObdCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectProtocolObdCommand;
import pt.lighthouselabs.obd.commands.protocol.SelectHeaderObdCommand;
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

    protected static final String OBD_BROADCAST_ACTION_ENGINE_FAN_STATE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ENGINE_FAN_STATE_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_ENGINE_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ECU_CVT_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_CVT_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_COMBINEMETER_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_AIRCOND_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_ECU_AWC_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_ECU_AWC_CHANGED";

    protected static final String OBD_BROADCAST_ACTION_AC_FAN_SPEED_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_FAN_SPEED_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AC_TEMP_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_TEMP_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AC_BLOW_DIRECTION_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_BLOW_DIRECTION_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AC_DEFOGGER_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_DEFOGGER_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AC_RECIRCULATION_CHANGED = "ru.d51x.kaierutils.action.OBD_AC_RECIRCULATION_CHANGED";
    protected static final String OBD_BROADCAST_ACTION_AC_STATE_CHANGED = "ru.d51x.kaierutils.action.OBD_CAN_STATE_CHANGED";


    private Context mContext;
    private String deviceName;
    private String deviceAddress;
    public boolean isConnected;
    protected int timeout;

    private BluetoothSocket socket;


    public EngineRPMObdCommand engineRpmCommand;
    public SpeedObdCommand speedCommand;
    public EngineCoolantTemperatureObdCommand coolantTempCommand;
    public ControlModuleVoltageObdCommand cmuVoltageCommand;
    public MassAirFlowObdCommand MAFObdCommand;
    private Handler mHandler;

    public boolean useOBD;
    public OBDData obdData;

    public boolean activeMAF = false;
    public boolean activeOther = true;

    public boolean MMC_CAN;

    public TripData totalTrip;
    public TripData oneTrip;
    public ClimateData climateData;


    public class OBDData {
        public float speed;
        public float rpm;
        public float coolant;
        public float maf;
        public float voltage;
        public float fuel_tank; // читаем из преференсов



        public OBDData() {
            speed = 0;
            rpm = 0;
            coolant = 0;
            maf = 0;
            voltage = 0;
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
        climateData = new ClimateData();

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
            new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
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

            new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
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
        cmuVoltageCommand = new ControlModuleVoltageObdCommand();
        MAFObdCommand = new MassAirFlowObdCommand();

     }

    public void processData() {
        if ( !isConnected ) return;
        //Log.d("OBDII-->processData()", "_");

        if ( MMC_CAN )  {
            // переходим на режим работы MMC CAN и пиды 21хх
            request_MMC_ECU_ENGINE();

           // request_MMC_ECU_CVT();
            request_MMC_ECU_COMBINATION_METER();
            request_MMC_ECU_AIR_COND();
            //request_MMC_ECU_AWC();

        } else {
            // переходим на дефолтный режим работы по стандартным пидам 01 хх
            SetHeaders("7E0", "7E8", false); // TODO: сделать это 1 раз после включения галки

            processOBD_EngineRPM();         // RPM:             01 0C
            processOBD_Speed();             // Speed:           01 0D
            processOBD_coolantTemp();       // Coolant:         01 05
            processOBD_CMVoltage();         // Voltage:         01 42
            // к этому моменту уже должна пройти 1 сек = 5 функций с задержкой 200 мсек
            //processOBD_MAF();               // MAF:             01 10
        }
    }



    private void processOBD_EngineRPM() {
        if ( activeMAF ) return;
        activeOther = true;
        try {
            engineRpmCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.rpm = engineRpmCommand.getRPM();
            SendBroadcastAction(OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED, "engineRPM", engineRpmCommand.getFormattedResult());
           // Log.d("OBDII-->processOBD_EngineRPM()", "RPM: " + engineRpmCommand.getFormattedResult());
        } catch ( NonNumericResponseException e5) {
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            activeOther = false;
            Log.d("OBDII-->processOBD_EngineRPM()", e.toString());
        } catch (UnableToConnectException e3) {
            activeOther = false;
            Log.d("OBDII-->processOBD_EngineRPM()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            activeOther = false;
            Log.d("OBDII-->processOBD_EngineRPM()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("OBDII-->processOBD_EngineRPM()", e2.toString());

        }
        activeOther = false;
    }

    private void processOBD_Speed() {
        if ( activeMAF ) return;
        activeOther = true;
        try {

            speedCommand.run(socket.getInputStream(), socket.getOutputStream());
            obdData.speed = speedCommand.getMetricSpeed();
            SendBroadcastAction(OBD_BROADCAST_ACTION_SPEED_CHANGED, "speed", speedCommand.getFormattedResult());
            //Log.d("OBDII-->processOBD_Speed()", "Speed: " + speedCommand.getFormattedResult());
        }  catch ( NonNumericResponseException e5) {
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            activeOther = false;
            Log.d("processOBD_Speed()", e.toString());
        } catch (UnableToConnectException e3) {
            activeOther = false;
            Log.d("processOBD_Speed()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            activeOther = false;
            Log.d("processOBD_Speed()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("processOBD_Speed()", e2.toString());
        }
        activeOther = false;
    }

    private void processOBD_coolantTemp() {
        if ( activeMAF ) return;
        activeOther = true;
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
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            activeOther = false;
            Log.d("processOBD_coolantTemp()", e.toString());
        } catch (UnableToConnectException e3) {
            activeOther = false;
            Log.d("processOBD_coolantTemp()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            activeOther = false;
            Log.d("processOBD_coolantTemp()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("processOBD_coolantTemp()", e2.toString());
        }
    }

    private void processOBD_CMVoltage() {
        if ( activeMAF ) return;
        activeOther = true;
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
            activeOther = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            activeOther = false;
            Log.d("OBDII-->processOBD_CMVoltage()", e.toString());
        } catch (UnableToConnectException e3) {
            activeOther = false;
            Log.d("OBDII-->processOBD_CMVoltage()", e3.toString());
            disconnect();
        } catch (IOException e4) {
            activeOther = false;
            Log.d("OBDII-->processOBD_CMVoltage()", e4.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("OBDII-->processOBD_CMVoltage()", e2.toString());
        }
        activeOther = false;
    }

    public void processOBD_MAF() {
        //if ( activeOther ) return;
        try {

            // TODO: убрать костыль, когда найду нужный пид
            SetHeaders("7E0", "7E8", false);
            activeMAF = true;
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
            activeMAF = false;

        }  catch ( NonNumericResponseException e5) {
            activeMAF = false;
            disconnect();
            Log.d("OBDII-->processOBD_EngineRPM()", e5.toString());
        }
        catch ( NoDataException e) {
            Log.d("OBDII-->processOBD_MAF()", e.toString());
            activeMAF = false;
        } catch (UnableToConnectException e3) {
            Log.d("OBDII-->processOBD_MAF()", e3.toString());
            activeMAF = false;
            disconnect();
        } catch (IOException e4) {
            Log.d("OBDII-->processOBD_MAF()", e4.toString());
            activeMAF = false;
            disconnect();
        } catch ( Exception e2) {
            Log.d("OBDII-->processOBD_MAF()", e2.toString());
            activeMAF = false;
        } finally {
            activeMAF = false;
        }

    }

    public void request_CAN_ECU(String PID, String ReceiverAddress, String SenderAddress, boolean flowControl, String BroadcastID) {
        if ( activeMAF ) return;
        activeOther = true;
        String function = "processCAN_" + PID + "__" + ReceiverAddress + "()";

        try {
              //SetHeaders(ReceiverAddress, SenderAddress, flowControl);

            CanObdCommand cmd =  new CanObdCommand(PID);
            cmd.run(socket.getInputStream(), socket.getOutputStream());

            ArrayList<Integer> buffer = null;
            buffer = cmd.getBuffer();

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
            intent.putExtra("PID", PID + ":" + ReceiverAddress);
            intent.putExtra("Buffer", buffer);
            intent.setAction(BroadcastID);
            App.getInstance ().sendBroadcast(intent);
            Log.d("OBDII-->" + function, "PID: " + PID + ", H: " + ReceiverAddress + ": " + cmd.getFormattedResult());
            activeOther = false;
        }  catch ( NonNumericResponseException e5) {
            activeOther = false;
            //disconnect();
            try {
                new ObdResetCommand().run(socket.getInputStream(), socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("OBDII-->" + function, e5.toString());
        }
        catch ( NoDataException e) {
            activeOther = false;
            Log.d("OBDII-->" + function, e.toString());
        } catch (UnableToConnectException e3) {
            activeOther = false;
            Log.d("OBDII-->" + function, e3.toString());
            disconnect();
        } catch (IOException e4) {
            activeOther = false;
            Log.d("OBDII-->" + function, e4.toString());
            disconnect();
        } catch ( Exception e2) {
            activeOther = false;
            Log.d("OBDII-->" + function, e2.toString());
        }
        activeOther = false;
    }


    private void notify_disconnect() {
        isConnected = false;
        //App.obd.isConnected = false;
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

    private void SetHeaders(String receiver, String sender, boolean flowControl) {
        if ( activeMAF ) return;
        activeOther = true;
        try {
            new SelectHeaderObdCommand("ATSH " + receiver).run(socket.getInputStream(), socket.getOutputStream());
            if (flowControl) {
                new SelectHeaderObdCommand("ATFCSH " + receiver).run(socket.getInputStream(), socket.getOutputStream());
                new SelectHeaderObdCommand("ATFCSD 30080A").run(socket.getInputStream(), socket.getOutputStream());
                new SelectHeaderObdCommand("ATFCSM 1").run(socket.getInputStream(), socket.getOutputStream());
            }
            new SelectHeaderObdCommand("ATCRA " + sender).run(socket.getInputStream(), socket.getOutputStream());
            activeOther = false;
        } catch (InterruptedException e) {
            activeOther = false;
            e.printStackTrace();
        } catch (IOException e) {
            activeOther = false;
            e.printStackTrace();
        } finally {
            activeOther = false;
        }
        activeOther = false;
    }

    private void request_MMC_ECU_ENGINE(){
        if ( activeMAF ) return;
        // set ENGINE ECU addresses
        SetHeaders("7E0", "7E8", false);

        processOBD_EngineRPM();         // RPM:             01 0C
        processOBD_Speed();             // Speed:           01 0D
        processOBD_coolantTemp();       // Coolant:         01 05
        processOBD_CMVoltage();         // Voltage:         01 42

        request_CAN_ECU("211D", "7E0", "7E8", false, OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED);
        request_CAN_ECU("211E", "7E0", "7E8", false, OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED);

        // TODO брать данные для:
        // RPM (01 0C)
        // Speed (01 0D)            Speed	2101	B*2
        // Coolant (01 05)          Coolant	2102	B-40
        // Voltage (01 42)          Volts	2101	A*18.68/255
        // MAF (01 10)

    }

    private void request_MMC_ECU_CVT(){
        if ( activeMAF ) return;
        // set CVT/AT ECU addresses
        SetHeaders("7E1", "7E9", true);
        request_CAN_ECU("2103", "7E1", "7E9", true, OBD_BROADCAST_ACTION_ECU_CVT_CHANGED); // // cvt_temp_count
        // request_CAN_ECU("2107", "7E1", "7E9", false, OBD_BROADCAST_ACTION_ECU_CVT_CHANGED);  // selector position

        // TODO: можно выполнять не часто, раз в 10 сек вполне достаточно или даже реже
        request_CAN_ECU("2110", "7E1", "7E9", true, OBD_BROADCAST_ACTION_ECU_CVT_CHANGED); // cvt_oil_degradation
    }

    private void request_MMC_ECU_COMBINATION_METER(){
        if ( activeMAF ) return;
        // set COMBINE METER ECU addresses
        SetHeaders("6A0", "514", false);
        request_CAN_ECU("21A3", "6A0", "514", false, OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED);
    }

    private void request_MMC_ECU_AIR_COND(){
        if ( activeMAF ) return;
        SetHeaders( "688", "511", false);
        // внешняя температура
        request_CAN_ECU("2111", "688", "511", false, OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED);
        //request_CAN_ECU("2123", "688", "511", true, OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED);
        // положения крутилок
        request_CAN_ECU("2160", "688", "511", false, OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED);
        // состояния индикаторов
        request_CAN_ECU("2161", "688", "511", false, OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED);

    }

    private void request_MMC_ECU_AWC(){
        if ( activeMAF ) return;
        SetHeaders( "7B6", "7B7", true);
        request_CAN_ECU("2130", "7B6", "7B7", true, OBD_BROADCAST_ACTION_ECU_AWC_CHANGED);
    }

    public String process_MMC_ECU_data(String param, String action, String extra, ArrayList<Integer> buffer) {
        String res = "";
        if ( param.isEmpty() || action.isEmpty() || extra.isEmpty() ) return res;

        try {
            if (action.equalsIgnoreCase(OBD_BROADCAST_ACTION_ECU_ENGINE_CHANGED)) {
                res = process_ENGINE_data(extra, param, buffer);
            } else if (action.equalsIgnoreCase(OBD_BROADCAST_ACTION_ECU_CVT_CHANGED)) {
                res = process_CVT_data(extra, param, buffer);
            } else if (action.equalsIgnoreCase(OBD_BROADCAST_ACTION_ECU_COMBINEMETER_CHANGED)) {
                res = process_combine_meter_data( extra, param, buffer);
            } else if (action.equalsIgnoreCase(OBD_BROADCAST_ACTION_ECU_AIRCOND_CHANGED)) {
                res = process_AC_data( extra, param, buffer);
            } else if (action.equalsIgnoreCase(OBD_BROADCAST_ACTION_ECU_AWC_CHANGED)) {
                res = process_awc_data( extra, param, buffer);
            }
        } finally {
            return res;
        }
     }


    protected String process_awc_data(String PID, String param, ArrayList<Integer> buffer) {
       String res ="";
        if ( PID.equalsIgnoreCase( "2130:7B6" ) ) {
            if (param.equalsIgnoreCase("awc_4wd_mode")) {
                int a = buffer.get(2);
                switch ( a ) {
                    case 0x00: res = "SNA"; break;
                    case 0x01: res = "active"; break;
                    case 0x02: res = "4WD ECO"; break;
                    case 0x03: res = "4WD AUTO"; break;
                    case 0x04: res = "4WD FULL BLOCK"; break;
                    case 0x05: res = "AWC ECO"; break;
                    case 0x06: res = "normal"; break;
                    case 0x07: res = "snow"; break;
                    case 0x08: res = "block"; break;
                    case 0x09: res = "active"; break;
                    case 0x0A: res = "active"; break;
                } // switch
            }
        }
        return res;
    }

    protected String process_AC_data(String PID, String param, ArrayList<Integer> buffer) {
        String res ="";
        if (PID.equalsIgnoreCase("2111:688")) {
            // "C-Cross External temperature","Ext temp","2111","A/2-40","-40","87.5"," grad C","688"
            if (param.equalsIgnoreCase("air_cond_external_temp")) {
                int temp = buffer.get(2)/2 - 40;
                res = Integer.toString(temp);
            }
        }

        else if (PID.equalsIgnoreCase("2161:688")) {
            if (param.equalsIgnoreCase("air_cond_request_indicator_light")) {
                // индикации

            }
            else if (param.equalsIgnoreCase("air_cond_state")) {
                // A/C state: On/Off
                int A = buffer.get(2);
                Log.d("OBDII-->process_AC_data() --- A/C state:", Integer.toString( A ));
                //A = A & 0x20;   // А:5
                A = A & 0x10;   // А:5
                if ( A > 0) {
                    res = "ВКЛ";
                    climateData.ac_state = true;
                } else {
                    res = "ВЫКЛ";
                    climateData.ac_state = false;
                }

                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction(OBD_BROADCAST_ACTION_AC_STATE_CHANGED);
                App.getInstance ().sendBroadcast(intent);
            }
            else if (param.equalsIgnoreCase("air_cond_defogger_state")) {
                // Defogger state: On/Off
                //int A = buffer.get(2) & 0x2;   // А:1
                int A = buffer.get(2) & 0x1;   // А:1
                if ( A > 0) {
                    res = "ВКЛ";
                    climateData.defogger_state = true;
                } else {
                    res = "ВЫКЛ";
                    climateData.defogger_state = false;
                }
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction(OBD_BROADCAST_ACTION_AC_DEFOGGER_CHANGED);
                App.getInstance ().sendBroadcast(intent);
            }
            else if (param.equalsIgnoreCase("air_cond_recirculation_state")) {
                // Re-circulation state: On/Off
                //int A = buffer.get(2) & 0x80;   // А:7
                int A = buffer.get(2) & 0x40;   // А:7
                if ( A > 0) {
                    res = "ВКЛ";
                    climateData.recirculation_state = true;
                } else {
                    res = "ВЫКЛ";
                    climateData.recirculation_state = false;
                }
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction(OBD_BROADCAST_ACTION_AC_RECIRCULATION_CHANGED);
                App.getInstance ().sendBroadcast(intent);
            }
            else if (param.equalsIgnoreCase("air_cond_blow_direction_state")) {
                // Blow direction
                res = getBlowValue( buffer.get(3) );
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction(OBD_BROADCAST_ACTION_AC_BLOW_DIRECTION_CHANGED);
                App.getInstance ().sendBroadcast(intent);
            }
            else if (param.equalsIgnoreCase("air_cond_fan_speed_state")) {
                // fan speed
                res = getFanValue( buffer.get(3) );
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction(OBD_BROADCAST_ACTION_AC_FAN_SPEED_CHANGED);
                App.getInstance ().sendBroadcast(intent);
            }
        }
        else if (PID.equalsIgnoreCase("2160:688")) {
            // положение переключателей
            if (param.equalsIgnoreCase("air_cond_set_temperature")) {
                double A = buffer.get(2)/2f;
                A = A - 50;
                res = String.format("%1$.1f гр.", A);
                climateData.temperature = A;
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                intent.setAction(OBD_BROADCAST_ACTION_AC_TEMP_CHANGED);
                App.getInstance ().sendBroadcast(intent);
            }
            else if (param.equalsIgnoreCase("air_cond_fan_speed_position")) {
                // fan speed
                res = getFanValue( buffer.get(3) );
            }
            else if (param.equalsIgnoreCase("air_cond_blow_direction_position")) {
                // Blow direction
                res = getBlowValue2( buffer.get(3) );
            }
        }
        return res;
    }

    private String getFanValue(int value) {
        String res = "---";
        int B = value & 0xF; // bits 1-4, mask 0xF
        climateData.fan_speed = B;
        switch ( B ) {
            case 0x0: res = "АВТО"; break;
            case 0x1: res = "ВЫКЛ"; break;
            case 0x2: res = "поз. 1"; break;
            case 0x3: res = "поз. 2"; break;
            case 0x4: res = "поз. 3"; break;
            case 0x5: res = "поз. 4"; break;
            case 0x6: res = "поз. 5"; break;
            case 0x7: res = "поз. 6"; break;
            case 0x8: res = "поз. 7"; break;
            case 0x9: res = "поз. 8"; break;
            default: res = "---"; break;
        }
        return res;
    }

    private String getBlowValue(int value) {

        String res = "---";
        int B = value >> 4; // bits 5-8
        climateData.blow_direction = B;
        switch ( B ) {
            case 0x0: res = "ВЫКЛ"; break;
            case 0x1: res = "в лицо"; break;
            case 0x3: res = "лицо | ноги-лицо"; break;
            case 0x4: res = "ноги-лицо"; break;
            case 0x5: res = "ноги-лицо | ноги"; break;
            case 0x7: res = "ноги"; break;
            case 0x9: res = "ноги | ноги-стекло"; break;
            case 0xA: res = "ноги-стекло"; break;
            case 0xB: res = "ноги-стекло | стекло"; break;
            case 0xD: res = "стекло"; break;
            default: res = "---"; break;
        }
        return res;
    }

    private String getBlowValue2(int value) {

        String res = "---";
        int B = value >> 4; // bits 5-8


        switch ( B ) {
            case 0x0: res = "АТВО"; break;
            case 0x1: res = "в лицо"; break;
            case 0x3: res = "лицо | ноги-лицо"; break;
            case 0x4: res = "ноги-лицо"; break;
            case 0x5: res = "ноги-лицо | ноги"; break;
            case 0x7: res = "ноги"; break;
            case 0x9: res = "ноги | ноги-стекло"; break;
            case 0xA: res = "ноги-стекло"; break;
            case 0xB: res = "ноги-стекло | стекло"; break;
            case 0xD: res = "стекло"; break;
            default: res = "---"; break;
        }
        return res;
    }

    protected String process_combine_meter_data(String PID, String param, ArrayList<Integer> buffer) {
        String res ="";
        if (PID.equalsIgnoreCase("21A3:6A0")) {
            if (param.equalsIgnoreCase("combine_meter_fuel_level")) {
                double fuel = buffer.get(4);
                fuel = (fuel - 16) * 0.6;
                res = String.format("%1$.0f", fuel);
            }
        }
        return res;
    }

    protected String process_CVT_data(String PID, String param, ArrayList<Integer> buffer) {
        String res ="";
        if (PID.equalsIgnoreCase("2110:7E1")) {
            if (param.equalsIgnoreCase("cvt_oil_degradation")) {
                // CVT oil degradation 2110   AB*65536 + AC*256 + AD
                int degr = buffer.get(29) * 65536 + buffer.get(30) * 256 + buffer.get(31);
                res = Integer.toString(degr);
            }
        } else if (PID.equalsIgnoreCase("2103:7E1")) {
            if (param.equalsIgnoreCase("cvt_temp_count")) {
                // cvt_temp_count
                int N = buffer.get(15);
                res = Integer.toString(N);
            } else if (param.equalsIgnoreCase("cvt_temp_grad")) {
                // cvt_temp_grad
                int N = buffer.get(15);
                double temp_1 = -21.592 + (1.137 * N) - (0.0063 * N * N) + (0.0000195 * N * N * N);
                res = String.format("%1$.0f", temp_1);
            }
        }
        return res;
    }

    protected String process_ENGINE_data(String PID, String param, ArrayList<Integer> buffer) {
        String res ="";
        if (PID.equalsIgnoreCase("211D:7E0")) {
            if (param.equalsIgnoreCase("engine_aircond_state")) {
                // Выключатель кондиционера 211D {A:3}
                if ((buffer.get(2) & 0x8) > 0) res = "ВКЛ";
                else res = "ВЫКЛ";
            }
//            else if (param.equalsIgnoreCase("engine_gur_state")) {
//                // Выключатель гидроусилителя руля 211D {A:4}
//                if ((buffer.get(2) & 0x10) > 0) res = "ВКЛ";
//                else res = "ВЫКЛ";
//            }
//            else if (param.equalsIgnoreCase("engine_brake_state")) {
//                // Выключательлампы тормоза 211D {D:0}
//                if ((buffer.get(5) & 0x1) > 0) res = "ДА";
//                else res = "НЕТ";
//            }
        }
        return res;
    }
}
