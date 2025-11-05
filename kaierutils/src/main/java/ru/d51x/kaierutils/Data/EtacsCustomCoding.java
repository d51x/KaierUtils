package ru.d51x.kaierutils.Data;

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
}
