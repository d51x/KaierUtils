package ru.d51x.kaierutils.OBD2;

public class ObdConstants {

    public static final String BLOCK_7E0 = "7E0";   // ENGINE
    public static final String BLOCK_RX_7E8 = "7E8";   // ENGINE
    public static final String BLOCK_7E0_PID_2101 = "2101";   // ENGINE
    public static final String BLOCK_7E0_PID_2102 = "2102";   // ENGINE
    public static final String BLOCK_7E0_PID_2103 = "2103";   // ENGINE
    public static final String BLOCK_7E0_PID_211D = "211D";   // ENGINE
    public static final String BLOCK_7E0_PID_211E = "211E";   // ENGINE
    public static final String BLOCK_7E1 = "7E1";   // CVT
    public static final String BLOCK_RX_7E9 = "7E9";   // CVT
    public static final String BLOCK_7E1_PID_2103 = "2103";   // CVT
    public static final String BLOCK_7E1_PID_2110 = "2110";   // CVT
    public static final String BLOCK_6A0 = "6A0";   // METER
    public static final String BLOCK_RX_514 = "514";   // METER
    public static final String BLOCK_6A0_PID_21A1 = "21A1";   // METER
    public static final String BLOCK_6A0_PID_21A2 = "21A2";   // METER
    public static final String BLOCK_6A0_PID_21A3 = "21A3";   // METER
    public static final String BLOCK_6A0_PID_21A6 = "21A6";   // METER
    public static final String BLOCK_6A0_PID_21A8 = "21A8";   // METER
    public static final String BLOCK_6A0_PID_21AD = "21AD";   // METER
    public static final String BLOCK_6A0_PID_21AE = "21AE";   // METER
    public static final String BLOCK_6A0_PID_21AF = "21AF";   // METER
    public static final String BLOCK_6A0_PID_21BC = "21BC";   // METER
    public static final String BLOCK_688 = "688";   // CLIMATE
    public static final String BLOCK_RX_511 = "511";   // CLIMATE
    public static final String BLOCK_688_PID_2110 = "2110";   // CLIMATE
    public static final String BLOCK_688_PID_2111 = "2111";   // CLIMATE
    public static final String BLOCK_688_PID_2113 = "2113";   // CLIMATE
    public static final String BLOCK_688_PID_2132 = "2132";   // CLIMATE
    public static final String BLOCK_688_PID_2160 = "2160";   // CLIMATE
    public static final String BLOCK_688_PID_2161 = "2161";   // CLIMATE
    public static final String BLOCK_688_PID_2180 = "2180";   // CLIMATE
    public static final String BLOCK_7B6 = "7B6";   // AWC
    public static final String BLOCK_RX_7B7 = "7B7";   // AWC
    public static final String BLOCK_7B6_PID_21xx = "21XX";   // AWC
    public static final String BLOCK_763 = "763";   // PARKING
    public static final String BLOCK_RX_763 = "764";   // PARKING
    public static final String BLOCK_763_PID_2101 = "2101";   // PARKING


    public static final String ACTION_PREFIX = "ru.d51x.kaierutils.action.";

    public static final String ACTION_ENGINE_DATA_CHANGED = ACTION_PREFIX + "OBD_ENGINE_DATA_CHANGED";
    public static final String ACTION_CVT_DATA_CHANGED = ACTION_PREFIX + "OBD_CVT_DATA_CHANGED";
    public static final String ACTION_AIR_COND_DATA_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_DATA_CHANGED";
    public static final String ACTION_METER_DATA_CHANGED = ACTION_PREFIX + "OBD_METER_DATA_CHANGED";
    public static final String ACTION_PARKING_DATA_CHANGED = ACTION_PREFIX + "OBD_PARKING_DATA_CHANGED";
    public static final String ACTION_AWC_DATA_CHANGED = ACTION_PREFIX + "OBD_AWC_DATA_CHANGED";


    public static final String OBD_BROADCAST_ACTION_SPEED_CHANGED = ACTION_PREFIX + "OBD_SPEED_CHANGED";
    public static final String OBD_BROADCAST_ACTION_ENGINE_RPM_CHANGED = ACTION_PREFIX + "OBD_ENGINE_RPM_CHANGED";
    public static final String OBD_BROADCAST_ACTION_COOLANT_TEMP_CHANGED = ACTION_PREFIX + "OBD_COOLANT_TEMP_CHANGED";
    public static final String OBD_BROADCAST_ACTION_CMU_VOLTAGE_CHANGED = ACTION_PREFIX + "OBD_CMU_VOLTAGE_CHANGED";
    public static final String OBD_BROADCAST_ACTION_FUEL_CONSUMPTION_CHANGED = ACTION_PREFIX + "OBD_FUEL_CONSUMPTION_CHANGED";
    public static final String OBD_BROADCAST_ACTION_MAF_CHANGED = ACTION_PREFIX + "OBD_MAF_CHANGED";

    public static final String ACTION_OBD_ENGINE_2101_CHANGED = ACTION_PREFIX + "OBD_ENGINE_2101";
    public static final String ACTION_OBD_ENGINE_2102_CHANGED = ACTION_PREFIX + "OBD_ENGINE_2102";
    public static final String ACTION_OBD_ENGINE_2103_CHANGED = ACTION_PREFIX + "OBD_ENGINE_2103";
    public static final String ACTION_OBD_ENGINE_211D_CHANGED = ACTION_PREFIX + "OBD_ENGINE_211D";
    public static final String ACTION_OBD_ENGINE_211E_CHANGED = ACTION_PREFIX + "OBD_ENGINE_211E";

    public static final String ACTION_OBD_CVT_2103_CHANGED = ACTION_PREFIX + "OBD_CVT_2103";
    public static final String ACTION_OBD_CVT_2110_CHANGED = ACTION_PREFIX + "OBD_CVT_2110";

    public static final String ACTION_OBD_CLIMATE_2110_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_2110";
    public static final String ACTION_OBD_CLIMATE_2111_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_2111";
    public static final String ACTION_OBD_CLIMATE_2113_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_2113";
    public static final String ACTION_OBD_CLIMATE_2132_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_2132";
    public static final String ACTION_OBD_CLIMATE_2160_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_2160";
    public static final String ACTION_OBD_CLIMATE_2161_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_2161";
    public static final String ACTION_OBD_CLIMATE_2180_CHANGED = ACTION_PREFIX + "OBD_AIR_COND_2180";

    public static final String ACTION_OBD_METER_21A1_CHANGED = ACTION_PREFIX + "OBD_METER_21A1";
    public static final String ACTION_OBD_METER_21A2_CHANGED = ACTION_PREFIX + "OBD_METER_21A2";
    public static final String ACTION_OBD_METER_21A3_CHANGED = ACTION_PREFIX + "OBD_METER_21A3";
    public static final String ACTION_OBD_METER_21A6_CHANGED = ACTION_PREFIX + "OBD_METER_21A6";
    public static final String ACTION_OBD_METER_21A8_CHANGED = ACTION_PREFIX + "OBD_METER_21A8";
    public static final String ACTION_OBD_METER_21AD_CHANGED = ACTION_PREFIX + "OBD_METER_21AD";
    public static final String ACTION_OBD_METER_21AE_CHANGED = ACTION_PREFIX + "OBD_METER_21AE";
    public static final String ACTION_OBD_METER_21AF_CHANGED = ACTION_PREFIX + "OBD_METER_21AF";
    public static final String ACTION_OBD_METER_21BC_CHANGED = ACTION_PREFIX + "OBD_METER_21BC";

    public static final String ACTION_OBD_PARKING_2101_CHANGED = ACTION_PREFIX + "OBD_PARKING_2101";
    public static final String ACTION_OBD_AWC_2130_CHANGED = ACTION_PREFIX + "OBD_AWC_2130";

    public static final String OBD_BROADCAST_ACTION_STATUS_CHANGED = ACTION_PREFIX + "OBD_STATUS_CHANGED";



    // MESSAGES ENGINE
    public static final int MESSAGE_OBD_CAN_ENGINE = 0x07E00000;
    public static final int MESSAGE_OBD_ENGINE_2101 = 0x07E02101;
    public static final int MESSAGE_OBD_ENGINE_2102 = 0x07E02102;
    public static final int MESSAGE_OBD_ENGINE_2103 = 0x07E02103;
    public static final int MESSAGE_OBD_CAN_ENGINE_2110 = 0x07E02110;
    public static final int MESSAGE_OBD_ENGINE_211E = 0x07E0211E;
    public static final int MESSAGE_OBD_ENGINE_211D = 0x07E00001;

    // MESSAGES CVT
    public static final int MESSAGE_OBD_CAN_CVT = 0x07E10000;
    public static final int MESSAGE_OBD_CVT_2103 = 0x07E102103;
    public static final int MESSAGE_OBD_CVT_2110 = 0x07E102110;

    // MESSAGES COMBINE METER
    public static final int MESSAGE_OBD_COMBINE_METER = 0x06A00000;
    public static final int MESSAGE_OBD_COMBINE_METER_21A1 = 0x06A021A1;
    public static final int MESSAGE_OBD_COMBINE_METER_21A2 = 0x06A021A2;
    public static final int MESSAGE_OBD_COMBINE_METER_21A3 = 0x06A021A3;
    public static final int MESSAGE_OBD_COMBINE_METER_21A6 = 0x06A021A6;
    public static final int MESSAGE_OBD_COMBINE_METER_21A8 = 0x06A021A8;
    public static final int MESSAGE_OBD_COMBINE_METER_21AD = 0x06A021AD;
    public static final int MESSAGE_OBD_COMBINE_METER_21AE = 0x06A021AE;
    public static final int MESSAGE_OBD_COMBINE_METER_21AF = 0x06A021AF;
    public static final int MESSAGE_OBD_COMBINE_METER_21BC = 0x06A021BC;

    // MESSAGES CLIMATE
    public static final int MESSAGE_OBD_CLIMATE = 0x06880000;
    public static final int MESSAGE_OBD_CLIMATE_2110 = 0x068802110;
    public static final int MESSAGE_OBD_CLIMATE_2111 = 0x068802111;
    public static final int MESSAGE_OBD_CLIMATE_2113 = 0x068802113;
    public static final int MESSAGE_OBD_CLIMATE_2132 = 0x068802132;
    public static final int MESSAGE_OBD_CLIMATE_2160 = 0x068802160;
    public static final int MESSAGE_OBD_CLIMATE_2161 = 0x068802161;
    public static final int MESSAGE_OBD_CLIMATE_2180 = 0x068802180;


    // MESSAGES PARKING
    public static final int MESSAGE_OBD_PARKING_SENSORS = 0x07630000;
    public static final int MESSAGE_OBD_CAN_PARKING_SENSORS_2101 = 0x076302101;

    // MESSAGES AWC
    public static final int MESSAGE_OBD_CAN_AWC_2130 = 0x07B602130;









}
