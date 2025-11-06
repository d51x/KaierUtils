package ru.d51x.kaierutils.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EtacsCustomCoding {


    ETACS_CUSTOM_001(1, "turn_power_source", "Turn power source", 0, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_002(2, "comfort_flasher", "Comfort flasher", 0, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_003(3, "hazard_answer_back", "Hazard answer back", 1, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_004(4, "front_wiper_operation", "Front wiper operation", 1, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_005(5, "front_rear_wiper_washer", "Front/rear wiper washer", 2, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_006(6, "Intermittent_time_of_rear_wiper", "Intermittent time of rear wiper", 2, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_007(7, "rear_wiper_low_speed_mode", "Rear wiper Low speed mode", 3, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_008(8, "auto_fold_mirror", "Auto fold mirror", 3,4,4, 0b11110000, 4),
    ETACS_CUSTOM_009(9, "sensitivity_for_auto_lamp", "Sensitivity for auto lamp", 4, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_010(10, "auto_lamp_linked_wiper", "Auto lamp linked wiper", 4, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_011(11, "room_lamp_delay_timer_with_door", "Room lamp delay timer with door", 5, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_012(12, "head_lamp_auto_cut_customize", "Head lamp auto cut customize", 5, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_013(13, "interior_lamp_auto_cut_timer", "Interior lamp auto cut timer", 6, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_014(14, "auto_door_lock_by_vehicle_speed", "Auto door lock by vehicle speed", 6, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_015(15, "auto_door_unlock", "Auto door unlock", 7, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_016(16, "door_unlock_mode", "Door unlock mode", 8, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_017(17, "power_door_unlock_by_knob", "Power Door Unlock by Knob", 8, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_018(18, "deadlock_button_operation", "Deadlock Button Operation", 9, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_019(19, "horn_chirp_by_keyless", "Horn chirp by keyless", 9, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_020(20, "buzzer_answer_back", "Buzzer answer back", 10, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_021(21, "timer_lock_timer", "Timer lock timer", 10, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_022(22, "alarm", "Alarm", 11, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_023(23, "power_window_key_off_timer", "Power window key off timer", 11, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_024(24, "duration_of_pre-alarm", "Duration of pre-alarm", 12, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_025(25, "multi_mode", "Multi mode", 12, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_026(26, "panic_alarm_switch", "Panic alarm switch", 13, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_027(27, "duration_of_horn_chirp", "Duration of horn chirp", 13, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_028(28, "kos_key_detect_out_from_window", "KOS key detect out from window", 14, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_029(29, "kos_feature", "KOS feature", 15, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_030(30, "kos_unlock_disable_time", "KOS unlock disable time", 15, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_031(31, "remote_eng_starter_answer_back", "Remote ENG starter answer back", 16, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_032(32, "p_w_down_operation_after_ig_off", "P/W down operation after IG OFF", 16, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_033(33, "pw_main_sw_during_pw_locked", "P/W main SW during P/W locked", 17, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_034(34, "acc_power_auto_cut", "ACC power auto cut", 17, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_035(35, "comfort_flasher_switch_time", "Comfort flasher switch time", 18, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_036(36, "intelligent_comfort_washer_custom", "Intelligent/Comfort washer custom", 19, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_037(37, "interior_illumination_control", "Interior illumination control", 20, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_038(38, "coming_home_light", "Coming home light", 20, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_039(39, "welcome_light", "Welcome light", 21, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_040(40, "rear_wiper_linked_reverse_gear", "Rear wiper(linked reverse gear)", 22, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_041(41, "sensitivity_for_security_sensor", "Sensitivity for security sensor", 22, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_042(42, "outer_buzzer_volume", "Outer buzzer volume", 23, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_043(43, "siren_answer_back", "Siren answer back", 23, 4, 4, 0b11110000, 4),
    ETACS_CUSTOM_044(44, "remote_light_on", "Remote Light ON", 24, 4, 0, 0b00001111, 0),
    ETACS_CUSTOM_045(45, "hl_ev_remote_car_finder", "H/L(EV REMOTE Car finder)", 24, 4, 4, 0b11110000, 4),
    //ETACS_CUSTOM_998(998, "", "", 0, 4, 0, 0, 0),
    ETACS_CUSTOM_UNKNOWN(999, "unknown", "unknown", 0, 0, 0, 0, 0);
    private int idx;
    private String name;
    private String title;
    private int byteIdx;
    private int length;
    private int startBit;
    private int mask;
    private int leftShift;

    EtacsCustomCoding(int idx, String name, String title, int byteIdx, int length,
                      int startBit, int mask, int leftShift) {
        this.idx = idx;
        this.name = name;
        this.title = title;
        this.byteIdx = byteIdx;
        this.length = length;
        this.startBit = startBit;
        this.mask = mask;
        this.leftShift = leftShift;
    }

    public static EtacsCustomCoding getById(int id) {
        for (EtacsCustomCoding e: values()) {
            if (e.idx == id) {
                return e;
            }
        }
        return EtacsCustomCoding.ETACS_CUSTOM_UNKNOWN;
    }

    public int getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public int getByteIdx() {
        return byteIdx;
    }

    public int getLength() {
        return length;
    }

    public int getStartBit() {
        return startBit;
    }

    public int getMask() {
        return mask;
    }

    public static String getCurrentValue(int id, int val) {
        switch (id) {
            case 1:
                switch(val) {
                    case 0: return "ACC or IG1";
                    case 1: return "IG1";
                    default: return "Cannot change";
                }
            case 2:
                switch(val) {
                    case 0: return "Disabled";
                    case 1: return "Enable";
                    default: return "Cannot change";
                }
            case 3:
                switch(val) {
                    case 0: return "Lock:1, Unlock:2";
                    case 1: return "Lock:1, Unlock:0";
                    case 2: return "Lock:0, Unlock:2";
                    case 3: return "Lock:2, Unlock:1";
                    case 4: return "Lock:0, Unlock:1";
                    case 5: return "Lock:2, Unlock:0";
                    case 6: return "Lock:0, Unlock:0";
                    default: return "Cannot change";
                }
            case 4:
                switch(val) {
                    case 0: return "Normal INT";
                    case 1: return "Variable INT";
                    case 2: return "Speed Sensitive";
                    case 3: return "Rain Sensitive";
                    default: return "Cannot change";
                }
            case 5:
                switch(val) {
                    case 0: return "Only Washer";
                    case 1: return "Washer & Wiper";
                    case 2: return "With after wipe";
                    default: return "Cannot change";
                }
            case 6:
                switch(val) {
                    case 0: return "0 sec";
                    case 1: return "4 sec";
                    case 2: return "8 sec";
                    case 3: return "16 sec";
                    default: return "Cannot change";
                }
            case 7:
                switch(val) {
                    case 0: return "Disabled";
                    case 1: return "Enable";
                    default: return "Cannot change";
                }
            case 8:
                switch(val) {
                    case 0: return "Not Auto";
                    case 1: return "Open Vehicle SPD";
                    case 2: return "Open/Close by IG";
                    case 3: return "OPN/CLS Keyless";
                    default: return "Cannot change";
                }
            case 9:
                switch(val) {
                    case 0: return "Level1 bright";
                    case 1: return "Level2 bright";
                    case 2: return "Level3";
                    case 3: return "Level4 dark";
                    case 4: return "Level5 dark";
                    default: return "Cannot change";
                }
            case 10:
                switch(val) {
                    case 0: return "Disabled";
                    case 1: return "Enable";
                    default: return "Cannot select";
                }
            case 11:
                switch(val) {
                    case 0: return "0 sec";
                    case 1: return "7.5 sect";
                    case 2: return "15 sec";
                    case 3: return "30 sec";
                    case 4: return "60 sec";
                    case 5: return "120 sec";
                    case 6: return "180 sec";
                    default: return "Cannot change";
                }
            case 12:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Enable (A-spec.)";
                    case 2: return "Enable (B-spec.)";
                    case 3: return "Enable (C-spec.)";
                    case 4: return "Enable (D-spec.)";
                    case 5: return "Enable (E-spec.)";
                    default: return "Cannot change";
                }
            case 13:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "3 min";
                    case 2: return "30 min";
                    case 3: return "60 min";
                    default: return "Cannot change";
                }
            case 14:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Relock";
                    case 2: return "Not relock";
                    default: return "Cannot change";
                }
            case 15:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Always (P pos)";
                    case 2: return "P/W unlock (P)";
                    case 3: return "Always (Lock pos)";
                    case 4: return "P/W unlock (Lock)";
                    default: return "Cannot change";
                }
            case 16:
                switch(val) {
                    case 0: return "All Doors Unlock";
                    case 1: return "Dr door Unlock";
                    default: return "Cannot change";
                }
            case 17:
                switch(val) {
                    case 0: return "Enable";
                    case 1: return "Disable";
                    default: return "Cannot select";
                }
            case 18:
                switch(val) {
                    case 0: return "Twice";
                    case 1: return "Once";
                    default: return "Cannot select";
                }
            case 19:
                switch(val) {
                    case 0: return "Not sound horn";
                    case 1: return "Lock any time";
                    case 2: return "Lock/auto ON";
                    case 3: return "W lock any time";
                    default: return "Cannot select";
                }
            case 20:
                switch(val) {
                    case 0: return "Not sound buzzer";
                    case 1: return "At KOS";
                    case 2: return "At keyless";
                    case 3: return "At Both";
                    default: return "Cannot select";
                }
            case 21:
                switch(val) {
                    case 0: return "30 sec";
                    case 1: return "60 sec";
                    case 2: return "120 sec";
                    case 3: return "180 sec";
                    default: return "Cannot select";
                }
            case 22:
                switch(val) {
                    case 0: return "Disabled";
                    case 1: return "Enable";
                    default: return "Cannot change";
                }
            case 23:
                switch(val) {
                    case 0: return "0 sec";
                    case 1: return "30 sec";
                    case 2: return "180 sec";
                    case 3: return "600 sec";
                    default: return "Cannot change";
                }
            case 24:
                switch(val) {
                    case 0: return "10 sec";
                    case 1: return "6 sec";
                    default: return "Cannot change";
                }
            case 25:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "P/W:O&C, D/M:O&C";
                    case 2: return "P/W:O&C";
                    case 3: return "P/W:C, D/M:O&C";
                    case 4: return "D/M:O&C";
                    case 5: return "P/W:C";
                    default: return "Cannot change";
                }
            case 26:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Enable";
                    default: return "Cannot change";
                }
            case 27:
                switch(val) {
                    case 0: return "Short";
                    case 1: return "Long";
                    default: return "Cannot change";
                }
            case 28:
                switch(val) {
                    case 0: return "Enable";
                    case 1: return "Disable";
                    default: return "Cannot change";
                }
            case 29:
                switch(val) {
                    case 0: return "Both enable";
                    case 1: return "DoorEntry enable";
                    case 2: return "ENG start enable";
                    case 3: return "Both disable";
                    default: return "Cannot change";
                }
            case 30:
                switch(val) {
                    case 0: return "0 sec";
                    case 1: return "3 sec";
                    case 2: return "5 sec";
                    default: return "Cannot change";
                }
            case 31:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Enable";
                    default: return "Cannot change";
                }
            case 32:
                switch(val) {
                    case 0: return "Enable";
                    case 1: return "Disable";
                    default: return "Cannot change";
                }
            case 33:
                switch(val) {
                    case 0: return "Enable";
                    case 1: return "Disable";
                    default: return "Cannot change";
                }
            case 34:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "30 min";
                    case 2: return "60 min";
                    default: return "Cannot change";
                }
            case 35:
                switch(val) {
                    case 0: return "Normal";
                    case 1: return "Long";
                    default: return "Cannot change";
                }
            case 36:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Enable";
                    default: return "Cannot change";
                }
            case 37:
                switch(val) {
                    case 0: return "Same EXT lights";
                    case 1: return "Independe.Normal";
                    case 2: return "Independent late";
                    default: return "Cannot change";
                }
            case 38:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "15 sec";
                    case 2: return "30 sec";
                    case 3: return "60 sec";
                    case 4: return "180 sec";
                    default: return "Cannot change";
                }
            case 39:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Small lamp";
                    case 2: return "Head lamp";
                    default: return "Cannot change";
                }
            case 40:
                switch(val) {
                    case 0: return "Enable(R wip.ON)";
                    case 1: return "Enable(R/F wip.)";
                    default: return "Cannot change";
                }
            case 41:
                switch(val) {
                    case 0: return "Level 1";
                    case 1: return "Level 2";
                    case 2: return "Level 3";
                    case 3: return "Level 4";
                    default: return "Cannot change";
                }
            case 42:
                switch(val) {
                    case 0: return "Volume 1";
                    case 1: return "Volume 2";
                    case 2: return "Volume 3";
                    case 3: return "Volume auto";
                    default: return "Cannot change";
                }
            case 43:
                switch(val) {
                    case 0: return "No sound";
                    case 1: return "Sound 1";
                    case 2: return "Sound 2";
                    case 3: return "Sound 3";
                    case 4: return "Sound 4";
                    case 5: return "Sound 5";
                    case 6: return "Sound 6";
                    default: return "Cannot change";
                }
            case 44:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "Small 30 sec";
                    case 2: return "Small 60 sec";
                    case 3: return "Small 180 sec";
                    case 4: return "Head 30 sec";
                    case 5: return "Head 60 sec";
                    case 6: return "Head 180 sec";
                    default: return "Cannot change";
                }
            case 45:
                switch(val) {
                    case 0: return "Disable";
                    case 1: return "15 sec";
                    case 2: return "30 sec";
                    case 3: return "45 sec";
                    default: return "Cannot change";
                }
            default: return "unknown";
        }
    }

    public static ArrayList<String> getAvailableOptions(int id) {
        Stream<String> stream;

        switch (id) {
            case 1:
                stream = Stream.of("ACC or IG1", "IG1", "Cannot change" );
                break;
            case 2:
                stream = Stream.of("Disabled", "Enable", "Cannot change" );
                break;
            case 3:
                stream = Stream.of(   "Lock:1, Unlock:2",
                                                "Lock:1, Unlock:0",
                                                "Lock:0, Unlock:2",
                                                "Lock:2, Unlock:1",
                                                "Lock:0, Unlock:1",
                                                "Lock:2, Unlock:0",
                                                "Lock:0, Unlock:0",
                                                "Cannot change" );
                break;
            case 4:
                stream = Stream.of("Normal INT",
                                                "Variable INT",
                                                "Speed Sensitive",
                                                "Rain Sensitive",
                                                "Cannot change" );
                break;
            case 5:
                stream = Stream.of("Only Washer",
                                                "Washer & Wiper",
                                                "With after wipe",
                                                "Cannot change" );
                break;
            case 6:
                stream = Stream.of("0 sec",
                                                "4 sec",
                                                "8 sec",
                                                "16 sec",
                                                "Cannot change" );
                break;
            case 7:
                stream = Stream.of("Disabled",
                                                "Enable",
                                                "Cannot change");
                break;
            case 8:
                stream = Stream.of("Not Auto",
                                                "Open Vehicle SPD",
                                                "Open/Close by IG",
                                                "OPN/CLS Keyless",
                                                "Cannot change");
                break;
            case 9:
                stream = Stream.of("Level1 bright",
                                            "Level2 bright",
                                            "Level3",
                                            "Level4 dark",
                                            "Level5 dark",
                                            "Cannot change");
                break;
            case 10:
                stream = Stream.of("Disabled",
                                                "Enable",
                                                "Cannot select");
                break;
            case 11:
                stream = Stream.of("0 sec",
                                            "7.5 sect",
                                            "15 sec",
                                            "30 sec",
                                            "60 sec",
                                            "120 sec",
                                            "180 sec",
                                            "Cannot change");
                break;
            case 12:
                stream = Stream.of("Disable",
                                           "Enable (A-spec.)",
                                           "Enable (B-spec.)",
                                           "Enable (C-spec.)",
                                           "Enable (D-spec.)",
                                           "Enable (E-spec.)",
                                            "Cannot change");
                break;
            case 13:
                stream = Stream.of("Disable",
                                            "3 min",
                                            "30 min",
                                            "60 min",
                                            "Cannot change");
                break;
            case 14:
                stream = Stream.of("Disable",
                                            "Relock",
                                            "Not relock",
                                             "Cannot change");
                break;
            case 15:
                stream = Stream.of("Disable",
                    "Always (P pos)",
                    "P/W unlock (P)",
                    "Always (Lock pos)",
                    "P/W unlock (Lock)",
                     "Cannot change");
                break;
            case 16:
                stream = Stream.of(
                    "All Doors Unlock",
                    "Dr door Unlock",
                     "Cannot change");
                break;
            case 17:
                stream = Stream.of(
                   "Enable",
                   "Disable",
                    "Cannot select");
                break;
            case 18:
                stream = Stream.of(
                    "Twice",
                    "Once",
                     "Cannot select");
                break;
            case 19:
                stream = Stream.of(
                    "Not sound horn",
                    "Lock any time",
                    "Lock/auto ON",
                    "W lock any time",
                    "Cannot select");
                break;
            case 20:
                stream = Stream.of(
                    "Not sound buzzer",
                    "At KOS",
                    "At keyless",
                    "At Both",
                     "Cannot select");
                break;
            case 21:
                stream = Stream.of(
                    "30 sec",
                    "60 sec",
                    "120 sec",
                    "180 sec",
                     "Cannot select");
                break;
            case 22:
                stream = Stream.of(
                    "Disabled",
                    "Enable",
                     "Cannot change");
                break;
            case 23:
                stream = Stream.of(
                    "0 sec",
                    "30 sec",
                    "180 sec",
                    "600 sec",
                     "Cannot change");
                break;
            case 24:
                stream = Stream.of(
                    "10 sec",
                    "6 sec",
                     "Cannot change");
                break;
            case 25:
                stream = Stream.of(
                    "Disable",
                    "P/W:O&C, D/M:O&C",
                    "P/W:O&C",
                    "P/W:C, D/M:O&C",
                    "D/M:O&C",
                    "P/W:C",
                     "Cannot change");
                break;
            case 26:
                stream = Stream.of(
                    "Disable",
                    "Enable",
                     "Cannot change");
                break;
            case 27:
                stream = Stream.of(
                    "Short",
                    "Long",
                     "Cannot change");
                break;
            case 28:
                stream = Stream.of(
                    "Enable",
                    "Disable",
                     "Cannot change");
                break;
            case 29:
                stream = Stream.of(
                    "Both enable",
                    "DoorEntry enable",
                    "ENG start enable",
                    "Both disable",
                     "Cannot change");
                break;
            case 30:
                stream = Stream.of(
                    "0 sec",
                    "3 sec",
                    "5 sec",
                     "Cannot change");
                break;
            case 31:
                stream = Stream.of(
                    "Disable",
                    "Enable",
                     "Cannot change");
                break;
            case 32:
                stream = Stream.of(
                   "Enable",
                   "Disable",
                    "Cannot change");
                break;
            case 33:
                stream = Stream.of(
                    "Enable",
                    "Disable",
                     "Cannot change");
                break;
            case 34:
                stream = Stream.of(
                    "Disable",
                    "30 min",
                    "60 min",
                     "Cannot change");
                break;
            case 35:
                stream = Stream.of(
                    "Normal",
                    "Long",
                     "Cannot change");
                break;
            case 36:
                stream = Stream.of(
                    "Disable",
                    "Enable",
                     "Cannot change");
                break;
            case 37:
                stream = Stream.of(
                    "Same EXT lights",
                    "Independe.Normal",
                    "Independent late",
                     "Cannot change");
                break;
            case 38:
                stream = Stream.of(
                    "Disable",
                    "15 sec",
                    "30 sec",
                    "60 sec",
                    "180 sec",
                     "Cannot change");
                break;
            case 39:
                stream = Stream.of(
                    "Disable",
                    "Small lamp",
                    "Head lamp",
                     "Cannot change");
                break;
            case 40:
                stream = Stream.of(
                    "Enable(R wip.ON)",
                    "Enable(R/F wip.)",
                     "Cannot change");
                break;
            case 41:
                stream = Stream.of(
                    "Level 1",
                    "Level 2",
                    "Level 3",
                    "Level 4",
                     "Cannot change");
                break;
            case 42:
                stream = Stream.of(
                    "Volume 1",
                    "Volume 2",
                    "Volume 3",
                    "Volume auto",
                     "Cannot change");
                break;
            case 43:
                stream = Stream.of(
                    "No sound",
                    "Sound 1",
                    "Sound 2",
                    "Sound 3",
                    "Sound 4",
                    "Sound 5",
                    "Sound 6",
                     "Cannot change");
                break;
            case 44:
                stream = Stream.of(
                    "Disable",
                    "Small 30 sec",
                    "Small 60 sec",
                    "Small 180 sec",
                    "Head 30 sec",
                    "Head 60 sec",
                    "Head 180 sec",
                     "Cannot change");
                break;
            case 45:
                stream = Stream.of(
                    "Disable",
                    "15 sec",
                    "30 sec",
                    "45 sec",
                     "Cannot change");
                break;
            default: stream = Stream.of("unknown");
        }
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
