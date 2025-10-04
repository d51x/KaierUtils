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


    public static final String ACTION_ENGINE_2101 = ACTION_PREFIX + "OBD_ENGINE_2101";
    public static final String ACTION_ENGINE_2102 = ACTION_PREFIX + "OBD_ENGINE_2102";
    public static final String ACTION_ENGINE_2103 = ACTION_PREFIX + "OBD_ENGINE_2103";
    public static final String ACTION_ENGINE_211D = ACTION_PREFIX + "OBD_ENGINE_211D";
    public static final String ACTION_ENGINE_211E = ACTION_PREFIX + "OBD_ENGINE_211E";

    public static final String ACTION_CVT_2103 = ACTION_PREFIX + "OBD_CVT_2103";
    public static final String ACTION_CVT_2110 = ACTION_PREFIX + "OBD_CVT_2110";

    public static final String ACTION_AIR_COND_2110 = ACTION_PREFIX + "OBD_AIR_COND_2110";
    public static final String ACTION_AIR_COND_2111 = ACTION_PREFIX + "OBD_AIR_COND_2111";
    public static final String ACTION_AIR_COND_2113 = ACTION_PREFIX + "OBD_AIR_COND_2113";
    public static final String ACTION_AIR_COND_2132 = ACTION_PREFIX + "OBD_AIR_COND_2132";
    public static final String ACTION_AIR_COND_2160 = ACTION_PREFIX + "OBD_AIR_COND_2160";
    public static final String ACTION_AIR_COND_2161 = ACTION_PREFIX + "OBD_AIR_COND_2161";
    public static final String ACTION_AIR_COND_2180 = ACTION_PREFIX + "OBD_AIR_COND_2180";

    public static final String ACTION_METER_21A1 = ACTION_PREFIX + "OBD_METER_21A1";
    public static final String ACTION_METER_21A2 = ACTION_PREFIX + "OBD_METER_21A2";
    public static final String ACTION_METER_21A3 = ACTION_PREFIX + "OBD_METER_21A3";
    public static final String ACTION_METER_21A6 = ACTION_PREFIX + "OBD_METER_21A6";
    public static final String ACTION_METER_21A8 = ACTION_PREFIX + "OBD_METER_21A8";
    public static final String ACTION_METER_21AD = ACTION_PREFIX + "OBD_METER_21AD";
    public static final String ACTION_METER_21AE = ACTION_PREFIX + "OBD_METER_21AE";
    public static final String ACTION_METER_21AF = ACTION_PREFIX + "OBD_METER_21AF";
    public static final String ACTION_METER_21BC = ACTION_PREFIX + "OBD_METER_21BC";

    public static final String ACTION_PARKING_2101 = ACTION_PREFIX + "OBD_PARKING_2101";
    public static final String ACTION_AWC_2130 = ACTION_PREFIX + "OBD_AWC_2130";

    public static final String OBD_BROADCAST_ACTION_STATUS_CHANGED = ACTION_PREFIX + "OBD_STATUS_CHANGED";



    // MESSAGES CVT
    public static final int MESSAGE_OBD_CAN_CVT_2103 = 0x07E102103;
    public static final int MESSAGE_OBD_CAN_CVT_2110 = 0x07E102110;

    // MESSAGES COMBINE METER
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21A1 = 0x06A021A1;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21A2 = 0x06A021A2;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21A3 = 0x06A021A3;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21A6 = 0x06A021A6;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21A8 = 0x06A021A8;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21AD = 0x06A021AD;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21AE = 0x06A021AE;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21AF = 0x06A021AF;
    public static final int MESSAGE_OBD_CAN_COMBINE_METER_21BC = 0x06A021BC;

}
