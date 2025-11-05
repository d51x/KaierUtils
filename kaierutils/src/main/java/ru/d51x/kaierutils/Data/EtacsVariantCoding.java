package ru.d51x.kaierutils.Data;

public enum EtacsVariantCoding {

    ETACS_VARIANT_001(1, 0, 8, 0, 0, "vehicle_line", "Vehicle line"),
    ETACS_VARIANT_002(2, 1, 7, 0, 0b01111111, "model_year", "Model year"),
    ETACS_VARIANT_003(3, 1, 1, 7, 0b10000000, "sst_oil_cooling_fan", "SST oil cooling fan"),
    ETACS_VARIANT_004(4, 2, 4, 0, 0b00001111, "destination", "Destination"),
    ETACS_VARIANT_005(5, 2, 4, 4, 0b11110000, "transmission", "Transmission"),
    ETACS_VARIANT_006(6, 6, 8, 0, 0b11111111, "engine_type", "Engine type"),
    ETACS_VARIANT_007(7, 4, 2, 0, 0b00000011, "engine_power", "Engine power"),
    ETACS_VARIANT_008(8, 4, 1, 2, 0b00000100, "handle_side", "Handle side"),
    ETACS_VARIANT_009(9, 4, 4, 3, 0b01111000, "chassis_type_for_asc", "Chassis Type for A.S.C."),
    ETACS_VARIANT_010(10, 4, 1, 7, 0b10000000, "oss", "OSS"),
    ETACS_VARIANT_011(11, 5, 3, 0, 0b00000111, "final_drive", "Final drive"),
    ETACS_VARIANT_012(12, 5, 4, 3, 0b01111000, "transfer", "Transfer"),
    ETACS_VARIANT_013(13, 5, 1, 7, 0b10000000, "ig_off_delay_control", "IG off delay control"),
    ETACS_VARIANT_014(14, 6, 2, 4, 0b00110000, "dead_lock_operation_customize", "Dead Lock Operation Customize"),
    ETACS_VARIANT_015(15, 6, 2, 6, 0b11000000, "after_wipe_customize", "After wipe customize"),
    ETACS_VARIANT_016_1(16000, 7, 8, 0, 0b11111111, "tire_circumference", "Tire circumference"),
    ETACS_VARIANT_016_2(16001, 8, 8, 0, 0b11111111, "tire_circumference", "Tire circumference"),
    ETACS_VARIANT_017(17, 9, 8, 0, 0b11111111, "fuel_tank", "Fuel tank"),
    ETACS_VARIANT_018(18, 10, 3, 0, 0b00000111, "drl_type", "DRL type"),
    ETACS_VARIANT_019(19, 10, 2, 3, 0b00011000, "smart_entry_system", "Smart entry system"),
    ETACS_VARIANT_020(20, 10, 1, 5, 0b00100000, "tpms", "TPMS"),
    ETACS_VARIANT_021(21, 10, 1, 6, 0b01000000, "rke_keyless_entry", "RKE Keyless entry"),
    ETACS_VARIANT_022(22, 10, 1, 7, 0b10000000, "airbag_auto_hazard", "Airbag Auto Hazard"),
    ETACS_VARIANT_023(23, 11, 2, 0, 0b00000011, "immobilizer", "Immobilizer"),
    ETACS_VARIANT_024_1(24000, 11, 1, 2, 0b00000100, "cruise_control", "Cruise Control"),
    ETACS_VARIANT_024(24, 11, 1, 3, 0b00001000, "corner_sensor", "Corner sensor"),
    ETACS_VARIANT_025(25, 11, 1, 4, 0b00010000, "head_lamp_auto_leveling_device", "Head lamp auto leveling device"),
    ETACS_VARIANT_026(26, 11, 1, 5, 0b00100000, "oil_level_warning", "Oil level warning"),
    ETACS_VARIANT_027(27, 11, 1, 6, 0b01000000, "fuel_filter_warning", "Fuel filter warning"),
    ETACS_VARIANT_028(28, 11, 1, 7, 0b10000000, "speed_meter_scale", "Speed meter scale"),
    ETACS_VARIANT_029(29, 12, 1, 0, 0b0000000, "idle_neutral_control", "Idle neutral control"),
    ETACS_VARIANT_030(30, 12, 1, 1, 0b00000010, "security_alarm_sensor", "Security alarm sensor"),
    ETACS_VARIANT_031(31, 12, 2, 2, 0b00001100, "tm_oil_cooler", "T/M oil cooler"),
    ETACS_VARIANT_032(32, 12, 1, 4, 0b00010000, "remote_light_on", "Remote Light ON"),
    ETACS_VARIANT_033(33, 12, 1, 6, 0b01000000, "side_air_bag", "Side air bag"),
    ETACS_VARIANT_034(34, 12, 1, 7, 0b10000000, "acc_power_auto_cut", "ACC power auto cut"),
    ETACS_VARIANT_035(35, 13, 4, 0, 0b00001111, "number_of_speaker", "Number of speaker"),
    ETACS_VARIANT_036(36, 13, 1, 4, 0b00010000, "seat_material", "Seat material"),
    ETACS_VARIANT_037(37, 13, 3, 5, 0b11100000, "auto_lamp_control", "Auto lamp control"),
    ETACS_VARIANT_038(38, 14, 2, 0, 0b00000011, "front_differential", "Front differential"),
    ETACS_VARIANT_039(39, 14, 2, 4, 0b00110000, "rear_differential", "Rear differential"),
    ETACS_VARIANT_040(40, 15, 3, 0, 0b00000111, "power_window_type", "Power window type"),
    ETACS_VARIANT_041(41, 15, 3, 4, 0b01110000, "sun_roof_type", "Sun roof type"),
    ETACS_VARIANT_042(42, 16, 1, 0, 0b00000001, "wcm", "WCM"),
    ETACS_VARIANT_043(43, 16, 1, 1, 0b00000010, "ocm", "OCM"),
    ETACS_VARIANT_044(44, 16, 1, 2, 0b00000100, "orc", "ORC"),
    ETACS_VARIANT_045(45, 16, 1, 3, 0b00001000, "ac", "A/C"),
    ETACS_VARIANT_046(46, 16, 1, 4, 0b00010000, "audio", "AUDIO"),
    ETACS_VARIANT_047(47, 16, 1, 5, 0b00100000, "and", "AND"),
    ETACS_VARIANT_048(48, 16, 2, 6, 0b11000000, "siren_answer", "Siren answer"),
    ETACS_VARIANT_049(49, 17, 1, 0, 0b00000001, "security_alarm_siren", "Security alarm siren"),
    ETACS_VARIANT_050(50, 17, 1, 1, 0b00000010, "camera", "CAMERA"),
    ETACS_VARIANT_051(51, 17, 1, 2, 0b00000100, "corner_sensor_control_unit", "Corner sensor control unit"),
    ETACS_VARIANT_052(52, 17, 1, 3, 0b00001000, "electric_slide_door_left", "Electric Slide door (Left)"),
    ETACS_VARIANT_053(53, 17, 1, 4, 0b00010000, "electric_slide_door_right", "Electric Slide door (Right)"),
    ETACS_VARIANT_054(54, 17, 1, 5, 0b00100000, "etg", "ETG"),
    ETACS_VARIANT_055(55, 17, 1, 6, 0b01000000, "ess_ecu", "ESS ECU"),
    ETACS_VARIANT_056(56, 17, 1, 7, 0b10000000, "hfm", "HFM"),
    ETACS_VARIANT_057(57, 18, 2, 0, 0b00000011, "intelligent_comfort_washer_custom", "Intelligent/Comfort washer custom"),
    ETACS_VARIANT_058(58, 18, 2, 2, 0b00001100, "head_lamp_leveling_system_type", "Head Lamp Leveling system type"),
    ETACS_VARIANT_059(59, 18, 1, 4, 0b00010000, "rear_wiper_mode", "Rear wiper mode"),
    ETACS_VARIANT_060(60, 18, 1, 5, 0b00100000, "kos_door_entry_type", "KOS door entry type"),
    ETACS_VARIANT_061(61, 18, 2, 6, 0b11000000, "rear_wiper_by_reverse_customize", "Rear wiper by reverse customize"),
    ETACS_VARIANT_062(62, 19, 1, 0, 0b00000001, "abs", "ABS"),
    ETACS_VARIANT_063(63, 19, 1, 1, 0b00000010, "asc", "ASC"),
    ETACS_VARIANT_064(64, 19, 1, 2, 0b00000100, "auto_fold_mirror", "Auto fold mirror"),
    ETACS_VARIANT_065(65, 19, 1, 3, 0b00001000, "sas", "SAS wheel angle sensor"),
    ETACS_VARIANT_066(66, 19, 1, 4, 0b00010000, "awc_4wd", "4WD / AWC"),
    ETACS_VARIANT_067(67, 19, 1, 5, 0b00100000, "tcm", "TCM"),
    ETACS_VARIANT_068(68, 19, 1, 6, 0b01000000, "actv_stb", "ACTV_STB"),
    ETACS_VARIANT_069(69, 19, 1, 7, 0b10000000, "door_unlock_by_ig_lock_customize", "Door unlock by IG lock customize"),
    ETACS_VARIANT_070(70, 20, 1, 0, 0b00000001, "shift_lock", "Shift lock"),
    ETACS_VARIANT_071(71, 20, 1, 1, 0b00000010, "eps", "EPS  [Усилитель рулевого управления с электронным управлением]"),
    ETACS_VARIANT_072(72, 20, 1, 2, 0b00000100, "acdayc", "ACDAYC"),
    ETACS_VARIANT_073(73, 20, 2, 3, 0b00011000, "сoming_home_light_customize", "Coming home light customize"),
    ETACS_VARIANT_074(74, 20, 2, 5, 0b01100000, "welcome_light_customize", "Welcome light customize"),
    ETACS_VARIANT_075(75, 20, 1, 7, 0b10000000, "indirect_lamp", "Indirect lamp"),
    ETACS_VARIANT_998(998, 0, 1, 0, 0, "", ""),
    ETACS_VARIANT_UNKNOWN(999, 0, 0, 0, 0, "unknown", "unknown");
    private int idx;
    private String name;
    private String title;
    private int byteIdx;
    private int length;
    private int startBit;
    private int mask;

    EtacsVariantCoding(int idx, int byteIdx, int length,
                      int startBit, int mask, String name, String title) {
        this.idx = idx;
        this.name = name;
        this.title = title;
        this.byteIdx = byteIdx;
        this.length = length;
        this.startBit = startBit;
        this.mask = mask;
    }

}
