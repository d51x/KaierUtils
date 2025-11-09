package ru.d51x.kaierutils.etacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EtacsVariantCoding {

    ETACS_VARIANT_001(1, 0, 7, 0, 0b01111111, false, true, null, "vehicle_line", "Vehicle line"),
    ETACS_VARIANT_002(2, 1, 7, 0, 0b01111111, false, true,null, "model_year", "Model year"),
    ETACS_VARIANT_003(3, 1, 1, 7, 0b10000000, false, true, null, "sst_oil_cooling_fan", "SST oil cooling fan"),
    ETACS_VARIANT_004(4, 2, 4, 0, 0b00001111, false, true, null, "destination", "Destination"),
    ETACS_VARIANT_005(5, 2, 4, 4, 0b11110000, false, true, null, "transmission", "Transmission"),
    ETACS_VARIANT_006(6, 6, 8, 0, 0b11111111, false, true, null, "engine_type", "Engine type"),
    ETACS_VARIANT_007(7, 4, 2, 0, 0b00000011, false, true, null, "engine_power", "Engine power"),
    ETACS_VARIANT_008(8, 4, 1, 2, 0b00000100, false, true, null, "handle_side", "Handle side"),
    ETACS_VARIANT_009(9, 4, 4, 3, 0b01111000, false, true, null, "chassis_type_for_asc", "Chassis Type for A.S.C."),
    ETACS_VARIANT_010(10, 4, 1, 7, 0b10000000, false, true, null, "oss", "OSS"),
    ETACS_VARIANT_011(11, 5, 3, 0, 0b00000111, false, true, null, "final_drive", "Final drive"),
    ETACS_VARIANT_012(12, 5, 4, 3, 0b01111000, false, true, null, "transfer", "Transfer"),
    ETACS_VARIANT_013(13, 5, 1, 7, 0b10000000, true, false, null, "ig_off_delay_control", "IG off delay control"),
    ETACS_VARIANT_014(14, 6, 2, 4, 0b00110000, true, false, null, "dead_lock_operation_customize", "Dead Lock Operation Customize"),
    ETACS_VARIANT_015(15, 6, 2, 6, 0b11000000, true, false, null, "after_wipe_customize", "After wipe customize"),
    ETACS_VARIANT_016_1(16000, 7, 8, 0, 0b11111111, true, true, null, "tire_circumference", "Tire circumference"),
    ETACS_VARIANT_016_2(16001, 8, 8, 0, 0b11111111, true, true, null, "tire_circumference", "Tire circumference"),
    ETACS_VARIANT_017(17, 9, 8, 0, 0b11111111, false, true, null, "fuel_tank", "Fuel tank"),
    ETACS_VARIANT_018(18, 10, 3, 0, 0b00000111, true, false, null, "drl_type", "DRL type"),
    ETACS_VARIANT_019(19, 10, 2, 3, 0b00011000, true, true,null, "smart_entry_system", "Smart entry system"),
    ETACS_VARIANT_020(20, 10, 1, 5, 0b00100000, false, true, null, "tpms", "TPMS"),
    ETACS_VARIANT_021(21, 10, 1, 6, 0b01000000, true, false, null, "rke_keyless_entry", "RKE Keyless entry"),
    ETACS_VARIANT_022(22, 10, 1, 7, 0b10000000, true, false, null, "airbag_auto_hazard", "Airbag Auto Hazard"),
    ETACS_VARIANT_023(23, 11, 2, 0, 0b00000011, false, true, null, "immobilizer", "Immobilizer"),
    ETACS_VARIANT_024_1(24000, 11, 1, 2, 0b00000100, true, false, null, "cruise_control", "Cruise Control"),
    ETACS_VARIANT_024(24, 11, 1, 3, 0b00001000, true, false, null, "corner_sensor", "Corner sensor"),
    ETACS_VARIANT_025(25, 11, 1, 4, 0b00010000, true, true, null, "head_lamp_auto_leveling_device", "Head lamp auto leveling device"),
    ETACS_VARIANT_026(26, 11, 1, 5, 0b00100000, false, true, null, "oil_level_warning", "Oil level warning"),
    ETACS_VARIANT_027(27, 11, 1, 6, 0b01000000, false, true, null, "fuel_filter_warning", "Fuel filter warning"),
    ETACS_VARIANT_028(28, 11, 1, 7, 0b10000000, false, true, null, "speed_meter_scale", "Speed meter scale"),
    ETACS_VARIANT_029(29, 12, 1, 0, 0b0000000, true,false, null, "idle_neutral_control", "Idle neutral control"),
    ETACS_VARIANT_030(30, 12, 1, 1, 0b00000010, true, false, null, "security_alarm_sensor", "Security alarm sensor"),
    ETACS_VARIANT_031(31, 12, 2, 2, 0b00001100, false, true, null, "tm_oil_cooler", "T/M oil cooler"),
    ETACS_VARIANT_032(32, 12, 1, 4, 0b00010000, true, false, null, "remote_light_on", "Remote Light ON"),
    ETACS_VARIANT_033(33, 12, 1, 6, 0b01000000, false, true,null, "side_air_bag", "Side air bag"),
    ETACS_VARIANT_034(34, 12, 1, 7, 0b10000000, true, false, null, "acc_power_auto_cut", "ACC power auto cut"),
    ETACS_VARIANT_035(35, 13, 4, 0, 0b00001111, false, true,null, "number_of_speaker", "Number of speaker"),
    ETACS_VARIANT_036(36, 13, 1, 4, 0b00010000, false, true, null, "seat_material", "Seat material"),
    ETACS_VARIANT_037(37, 13, 3, 5, 0b11100000, true, false,null, "auto_lamp_control", "Auto lamp control"),
    ETACS_VARIANT_038(38, 14, 2, 0, 0b00000011, false, true, null, "front_differential", "Front differential"),
    ETACS_VARIANT_039(39, 14, 2, 4, 0b00110000, false, true, null, "rear_differential", "Rear differential"),
    ETACS_VARIANT_040(40, 15, 3, 0, 0b00000111, true, false, null, "power_window_type", "Power window type"),
    ETACS_VARIANT_041(41, 15, 3, 4, 0b01110000, true, false, null, "sun_roof_type", "Sun roof type"),
    ETACS_VARIANT_042(42, 16, 1, 0, 0b00000001, false, true, null, "wcm", "WCM"),
    ETACS_VARIANT_043(43, 16, 1, 1, 0b00000010, false, true, null, "ocm", "OCM"),
    ETACS_VARIANT_044(44, 16, 1, 2, 0b00000100, false, true, null, "orc", "ORC"),
    ETACS_VARIANT_045(45, 16, 1, 3, 0b00001000, false, true, null, "ac", "A/C"),
    ETACS_VARIANT_046(46, 16, 1, 4, 0b00010000, false, true,null, "audio", "AUDIO"),
    ETACS_VARIANT_047(47, 16, 1, 5, 0b00100000, false, true, null, "and", "AND"),
    ETACS_VARIANT_048(48, 16, 2, 6, 0b11000000, true, false, null, "siren_answer", "Siren answer"),
    ETACS_VARIANT_049(49, 17, 1, 0, 0b00000001, true, false, null, "security_alarm_siren", "Security alarm siren"),
    ETACS_VARIANT_050(50, 17, 1, 1, 0b00000010, false, true, null, "camera", "CAMERA"),
    ETACS_VARIANT_051(51, 17, 1, 2, 0b00000100, true, false, null, "corner_sensor_control_unit", "Corner sensor control unit"),
    ETACS_VARIANT_052(52, 17, 1, 3, 0b00001000, false, true, null, "electric_slide_door_left", "Electric Slide door (Left)"),
    ETACS_VARIANT_053(53, 17, 1, 4, 0b00010000, false, true, null, "electric_slide_door_right", "Electric Slide door (Right)"),
    ETACS_VARIANT_054(54, 17, 1, 5, 0b00100000, false, true, null, "etg", "ETG"),
    ETACS_VARIANT_055(55, 17, 1, 6, 0b01000000, true, false, null, "ess_ecu", "ESS ECU"),
    ETACS_VARIANT_056(56, 17, 1, 7, 0b10000000, false, true, null, "hfm", "HFM"),
    ETACS_VARIANT_057(57, 18, 2, 0, 0b00000011, true, false, null, "intelligent_comfort_washer_custom", "Intelligent/Comfort washer custom"),
    ETACS_VARIANT_058(58, 18, 2, 2, 0b00001100, true, false, null, "head_lamp_leveling_system_type", "Head Lamp Leveling system type"),
    ETACS_VARIANT_059(59, 18, 1, 4, 0b00010000, true, false, null, "rear_wiper_mode", "Rear wiper mode"),
    ETACS_VARIANT_060(60, 18, 1, 5, 0b00100000, true, false, null, "kos_door_entry_type", "KOS door entry type"),
    ETACS_VARIANT_061(61, 18, 2, 6, 0b11000000, true, false, null, "rear_wiper_by_reverse_customize", "Rear wiper by reverse customize"),
    ETACS_VARIANT_062(62, 19, 1, 0, 0b00000001, false, true, null, "abs", "ABS"),
    ETACS_VARIANT_063(63, 19, 1, 1, 0b00000010, false, true, null, "asc", "ASC"),
    ETACS_VARIANT_064(64, 19, 1, 2, 0b00000100, true, false,null, "auto_fold_mirror", "Auto fold mirror"),
    ETACS_VARIANT_065(65, 19, 1, 3, 0b00001000, false, true, null, "sas", "SAS wheel angle sensor"),
    ETACS_VARIANT_066(66, 19, 1, 4, 0b00010000, false, true, null, "awc_4wd", "4WD / AWC"),
    ETACS_VARIANT_067(67, 19, 1, 5, 0b00100000, false, true,null, "tcm", "TCM"),
    ETACS_VARIANT_068(68, 19, 1, 6, 0b01000000, false, true,null, "actv_stb", "ACTV_STB"),
    ETACS_VARIANT_069(69, 19, 1, 7, 0b10000000, true, false,null, "door_unlock_by_ig_lock_customize", "Door unlock by IG lock customize"),
    ETACS_VARIANT_070(70, 20, 1, 0, 0b00000001, false, true,null, "shift_lock", "Shift lock"),
    ETACS_VARIANT_071(71, 20, 1, 1, 0b00000010, false, true,null, "eps", "EPS  [Усилитель рулевого управления с электронным управлением]"),
    ETACS_VARIANT_072(72, 20, 1, 2, 0b00000100, false, true,null, "acdayc", "ACDAYC"),
    ETACS_VARIANT_073(73, 20, 2, 3, 0b00011000, true, false, null, "сoming_home_light_customize", "Coming home light customize"),
    ETACS_VARIANT_074(74, 20, 2, 5, 0b01100000, true, false,null, "welcome_light_customize", "Welcome light customize"),
    ETACS_VARIANT_075(75, 20, 1, 7, 0b10000000, true, false,null, "indirect_lamp", "Indirect lamp"),
    ETACS_VARIANT_076(76, 21, 1, 0, 0b00000001, false, true,null, "power_window_dr", "Power window Dr"),
    ETACS_VARIANT_077(77, 21, 1, 1, 0b00000010, false, true,null, "power_window_as", "Power window As"),
    ETACS_VARIANT_078(78, 21, 1, 2, 0b00000100, false, true,null, "power_window_rr", "Power window RR"),
    ETACS_VARIANT_079(79, 21, 1, 3, 0b00001000, false, true,null, "power_window_rl", "Power window RL"),
    ETACS_VARIANT_080(80, 21, 1, 4, 0b00010000, true, false,null, "ess_by_stop_lamp", "ESS by stop lamp"),
    ETACS_VARIANT_081(81, 21, 1, 5, 0b00100000, false, true,null, "sun_roof", "Sun roof"),
    ETACS_VARIANT_082(82, 21, 1, 6, 0b01000000, true, false,null, "rain_light_sensor", "Rain Light Sensor (RLS)"),
    ETACS_VARIANT_0820(820, 21, 1, 7, 0b10000000, true, false,null, "washer_function_improvement", "Washer function improvement"),
    ETACS_VARIANT_083(83, 22, 2, 0, 0b00000011, true, false, null, "ig_key_illumination", "IG key illumination"),
    ETACS_VARIANT_084(84, 22, 2, 2, 0b00001100, true, false,null, "turn_signal_bulb", "Turn signal bulb"),
    ETACS_VARIANT_085(85, 22, 1, 4, 0b00010000, true, false,null, "rear_wiper", "Rear wiper"),
    ETACS_VARIANT_086(86, 22, 1, 5, 0b00100000, true, false,null, "fold_mirror", "Fold mirror"),
    ETACS_VARIANT_087(87, 22, 1, 6, 0b01000000, true, false,null, "head_lamp", "Fold Head lamp"),
    ETACS_VARIANT_088(88, 22, 1, 7, 0b10000000, true, false, null, "kos_function_customize_by_disp", "KOS function customize by Disp"),
    ETACS_VARIANT_089(89, 23, 4, 0, 0b00001111, true, false,null, "head_lamp_washer", "Head lamp washer"),
    ETACS_VARIANT_090(90, 23, 1, 4, 0b00010000, true, false,null, "front_fog_lamp_mode", "Front fog lamp mode"),
    ETACS_VARIANT_091(91, 23, 1, 5, 0b00100000, true, false,null, "front_fog_lamp", "Front fog lamp"),
    ETACS_VARIANT_092(92, 23, 2, 6, 0b11000000, true, false,null, "rear_fog_lamp", "Rear fog lamp"),
    ETACS_VARIANT_093(93, 24, 1, 0, 0b00000001, true, false, null, "room_lamp_delay_timer_door_hl", "Room lamp delay timer/door & H/L"),
    ETACS_VARIANT_094(94, 24, 2, 1, 0b00000110, true, false, null, "room_lamp_by_hl", "Room lamp by H/L"),
    ETACS_VARIANT_095(95, 24, 2, 3, 0b00011000, true, false,null, "gate_trunk_lamp", "Gate/Trunk lamp"),
    ETACS_VARIANT_096(96, 24, 2, 5, 0b01100000, true, false,null, "head_lamp_auto_cut_mode", "Head lamp auto cut mode"),
    ETACS_VARIANT_097(97, 24, 1, 7, 0b10000000, true, false,null, "head_lamp_auto_cut", "Head lamp auto cut"),
    ETACS_VARIANT_098(98, 25, 3, 0, 0b00000111, true, false, null, "door_lock_system", "Door lock system"),
    ETACS_VARIANT_099(99, 25, 2, 3, 0b00011000, true, false,null, "auto_door_lock_unlock", "Auto door lock/unlock"),
    ETACS_VARIANT_100(100, 25, 2, 5, 0b01100000, true, false,null, "key_reminder_unlock", "Key reminder unlock"),
    ETACS_VARIANT_101(101, 25, 1, 7, 0b10000000, true, false,null, "horn_type", "Horn type"),
    ETACS_VARIANT_102(102, 26, 2, 0, 0b00000011, true, false,null, "gate_trunk_opener_mode", "Gate/trunk opener mode"),
    ETACS_VARIANT_103(103, 26, 1, 2, 0b00000100, true, false,null, "cooling_fan", "Cooling fan"),
    ETACS_VARIANT_104(104, 26, 2, 3, 0b00011000, true, false,null, "security_alarm_mode", "Security alarm mode"),
    ETACS_VARIANT_105(105, 26, 2, 5, 0b01100000, true, false,null, "security_alarm_function", "Security alarm function"),
    ETACS_VARIANT_106(106, 26, 1, 7, 0b10000000, true, false,null, "pre_alarm", "Pre-alarm"),
    ETACS_VARIANT_107(107, 27, 1, 0, 0b00000001, true, false,null, "multi_mode_rke", "Multi mode RKE"),
    ETACS_VARIANT_108(108, 27, 1, 1, 0b00000010, true, false,null, "gate_trunk", "Gate/Trunk"),
    ETACS_VARIANT_109(109, 27, 2, 2, 0b00001100, true, false,null, "manner_switch", "Manner Switch"),
    ETACS_VARIANT_110(110, 27, 2, 4, 0b00110000, true, false,null, "remote_engine_starter", "Remote engine starter"),
    ETACS_VARIANT_111(111, 27, 1, 6, 0b01000000, true, false,null, "panic_alarm", "Panic Alarm"),
    ETACS_VARIANT_112(112, 27, 1, 7, 0b10000000, false, true, null, "right_side_view_camera", "Right side view camera"),
    ETACS_VARIANT_113(113, 28, 2, 0, 0b00000011, true, false,null, "front_wiper", "Front wiper"),
    ETACS_VARIANT_114(114, 28, 2, 2, 0b00001100, true, false,null, "comfort_flasher_type", "Comfort flasher type"),
    ETACS_VARIANT_115(115, 28, 1, 4, 0b00010000, true, false,null, "room_lamp_center_switch", "Room lamp center switch"),
    ETACS_VARIANT_116(116, 28, 1, 5, 0b00100000, true, false,null, "wiper_washer_check_valve", "Wiper washer check valve"),
    ETACS_VARIANT_117(117, 28, 2, 6, 0b11000000, false, true,null, "audio_sradio_type", "AUDIO/S.RADIO type"),
    ETACS_VARIANT_118(118, 29, 3, 0, 0b00000111, false, true,null, "hl_leveling_type", "H/L leveling type"),
    ETACS_VARIANT_119(119, 29, 3, 3, 0b00111000, false, true,null, "afs_acl_type", "AFS/ACL type"),
    ETACS_VARIANT_120(120, 29, 1, 6, 0b01000000, true, false,null, "ess_by_turn_lamp", "ESS by turn lamp"),
    ETACS_VARIANT_121(121, 30, 2, 0, 0b00000011, false, true,null, "compressor_type", "Compressor type"),
    ETACS_VARIANT_122(122, 30, 1, 3, 0b00001000, false, true,null, "temperature_type", "Temperature type"),
    ETACS_VARIANT_123(123, 30, 1, 4, 0b00010000, false, true,null, "rear_view_camera", "Rear view camera"),
    ETACS_VARIANT_124(124, 30, 1, 5, 0b00100000, false, true,null, "nose_view_camera", "Nose view camera"),
    ETACS_VARIANT_125(125, 30, 1, 6, 0b01000000, false, true,null, "left_side_view_camera", "Left side view camera"),
    ETACS_VARIANT_126(126, 30, 1, 7, 0b10000000, true, false,null, "average_speed", "Average speed"),
    ETACS_VARIANT_127(127, 31, 4, 0, 0b00001111, false, true,null, "vehicle_language_status", "Vehicle language status"),
    ETACS_VARIANT_128(128, 31, 2, 4, 0b00110000, false, true,null, "fuel_amount", "Fuel amount"),
    ETACS_VARIANT_129(129, 31, 2, 6, 0b11000000, true, false,null, "fuel_consumption_scale", "Fuel consumption scale"),
    ETACS_VARIANT_130(130, 32, 1, 0, 0b00000001, true, false,null, "seat_belt_reminder_logic", "Seat belt reminder logic"),
    ETACS_VARIANT_131(131, 32, 1, 1, 0b00000010, true, false,null, "coolant_temp_gauge_threshold", "Coolant temp gauge threshold"),
    ETACS_VARIANT_132(132, 32, 3, 2, 0b00011100, true, false,null, "frost_warning_threshold", "Frost warning threshold"),
    ETACS_VARIANT_133(133, 32, 1, 5, 0b00100000, true, false,null, "distance_to_empty", "Distance to empty"),
    ETACS_VARIANT_134(134, 32, 1, 6, 0b01000000, true, false,null, "average_fuel_consumption", "Average fuel consumption"),
    ETACS_VARIANT_135(135, 32, 1, 7, 0b10000000, true, false,null, "instant_fuel_consumption", "Instant fuel consumption"),
    ETACS_VARIANT_136(136, 33, 1, 0, 0b00000001, true, false,null, "time_travelled", "Time travelled"),
    ETACS_VARIANT_137(137, 33, 1, 1, 0b00000010, true, false,null, "distance_travelled", "Distance travelled"),
    ETACS_VARIANT_138(138, 33, 1, 2, 0b00000100, true, false,null, "fuel_used", "Fuel used"),
    ETACS_VARIANT_139(139, 33, 1, 3, 0b00001000, true, false,null, "trip_autoreset_ig_off", "Trip autoreset IG OFF"),
    ETACS_VARIANT_140(140, 33, 1, 4, 0b00010000, true, false,null, "variable_speed_alarm", "Variable Speed Alarm"),
    ETACS_VARIANT_141(141, 33, 1, 5, 0b00100000, true, false, null, "rest_reminder", "Rest reminder"),
    ETACS_VARIANT_142(142, 33, 1, 6, 0b01000000, true, false,null, "instant_speed", "Instant speed"),
    ETACS_VARIANT_143(143, 33, 1, 7, 0b10000000, true, false,null, "seat_belt_reminder_control_type", "Seat belt reminder control type"),
    ETACS_VARIANT_144(144, 34, 1, 0, 0b00000001, true, false,null, "seat_belt_reminder_flashing", "Seat belt reminder flashing"),
    ETACS_VARIANT_145(145, 34, 2, 1, 0b00000110, true, false,null, "seat_belt_reminder_indicator", "Seat belt reminder indicator"),
    ETACS_VARIANT_146(146, 34, 1, 3, 0b00001000, true, false,null, "reverse_alarm", "Reverse alarm"),
    ETACS_VARIANT_147(147, 34, 1, 4, 0b00010000, true, false,null, "key_reminder", "Key reminder"),
    ETACS_VARIANT_148(148, 34, 1, 5, 0b00100000, true,false, null, "lighting_monitor", "Lighting monitor"),
    ETACS_VARIANT_149(149, 34, 1, 6, 0b01000000, true, false,null, "gcc_speed_alarm", "GCC speed alarm"),
    ETACS_VARIANT_150(150, 34, 1, 7, 0b10000000, true, false,null, "condition_buzzer", "Condition buzzer"),
    ETACS_VARIANT_151(151, 35, 1, 0, 0b00000001, false, true,null, "rent_a_car_mode_ig_off_always", "Rent-a-car mode IG-OFF always"),
    ETACS_VARIANT_152(152, 35, 1, 1, 0b00000010, false, true,null, "fuel_tank_type", "Fuel tank type"),
    ETACS_VARIANT_153(153, 35, 5, 2, 0b01111100, true, false,null, "service_reminder_schedule_table", "Service reminder schedule table"),
    ETACS_VARIANT_154(154, 35, 1, 7, 0b10000000, true, false,null, "gcc_speed_alarm_indicator", "GCC speed alarm indicator"),
    ETACS_VARIANT_155(155, 36, 4, 0, 0b00001111, false, true,null, "tpms_information", "TPMS information"),
    ETACS_VARIANT_156(156, 36, 2, 4, 0b00110000, true, false,null, "horn_chirp_by_keyless", "Horn chirp by keyless"),
    ETACS_VARIANT_157(157, 36, 1, 6, 0b01000000, true, false,null, "rear_sr_unlock_output", "Rear S/R Unlock Output"),
    ETACS_VARIANT_158(158, 36, 1, 7, 0b10000000, false, true,null, "trailer_turn_detection", "Trailer turn detection"),
    ETACS_VARIANT_159(159, 37, 1, 0, 0b00000001, false, true,null, "shift_Lever", "Shift Lever"),
    ETACS_VARIANT_160(160, 37, 1, 1, 0b00000010, false, true,null, "adaptive_front_lighting_system", "Adaptive Front-Lighting System"),
    ETACS_VARIANT_161(161, 37, 1, 2, 0b00000100, false, true,null, "satellite_radio", "Satellite Radio"),
    ETACS_VARIANT_162(162, 37, 1, 3, 0b00001000, false, true,null, "auto_stop_go", "Auto Stop & Go (AS&G)"),
    ETACS_VARIANT_163(163, 37, 3, 4, 0b01110000, false, true,null, "display_opening_type", "Display opening type"),
    ETACS_VARIANT_164(164, 37, 1, 7, 0b10000000, true, false,null, "smart_entry_auto_lock_customize", "Smart entry auto lock customize"), //GSI system  [Система GSI]
    ETACS_VARIANT_165(165, 38, 2, 0, 0b00000011, true, false,null, "drl_function", "DRL function"),
    ETACS_VARIANT_166(166, 38, 1, 2, 0b00000100, false, true,null, "facu", "FACU"),
    ETACS_VARIANT_167(167, 38, 1, 3, 0b00001000, true, false,null, "s_awc_control_display", "S-AWC Control display"),
    ETACS_VARIANT_168(168, 38, 1, 4, 0b00010000, false, true,null, "diesel_particulate_filter", "Diesel particulate filter"),
    ETACS_VARIANT_169(169, 38, 1, 5, 0b00100000, false, true,null, "language_mode", "Language mode"),
    ETACS_VARIANT_170(170, 38, 1, 6, 0b01000000, false, true,null, "wss", "Wheel Speed Sensor"),
    ETACS_VARIANT_171(171, 38, 1, 7, 0b10000000, true, false,null, "door_unlock_mode_customize", "Door Unlock Mode Customize"),
    ETACS_VARIANT_172(172, 39, 3, 0, 0b00000111, false, true,null, "rls_overwipe_type", "RLS overwipe type"),
    ETACS_VARIANT_173(173, 39, 2, 3, 0b00011000, false, true,null, "rls_ws_type", "RLS WS type"),
    ETACS_VARIANT_174(174, 39, 1, 7, 0b10000000, false, true,null, "launch_gear_block_alarm", "Launch gear block alarm"),
    ETACS_VARIANT_175(175, 40, 4, 0, 0b00001111, false, true,null, "security_sensor_gain_setting", "Security sensor gain setting"),
    ETACS_VARIANT_176(176, 40, 1, 5, 0b00100000, false, true,null, "indirect_lamp_type", "Indirect lamp type"),
    ETACS_VARIANT_177(177, 40, 2, 6, 0b11000000, false, true,null, "4wd_switch_type", "4WD switch type"),
    ETACS_VARIANT_178(178, 40, 1, 0, 0b00000001, false, true,null, "ev_remote_ecu", "EV REMOTE-ECU"),
    ETACS_VARIANT_179(179, 40, 1, 1, 0b00000010, false, true,null, "automatic_high_beam", "Automatic High Beam"),
    ETACS_VARIANT_180(180, 40, 1, 2, 0b00000100, false, true,null, "automatic_high_beam_function", "Automatic High Beam function"),
    ETACS_VARIANT_UNKNOWN(999, 0, 0, 0, 0, false, true, null, "unknown", "unknown");
    private int idx;
    private String name;
    private String title;
    private int byteIdx;
    private int length;
    private int startBit;
    private int mask;
    private Class dataType;
    private boolean visible;
    private boolean extended;

    EtacsVariantCoding(int idx, int byteIdx, int length,
                      int startBit, int mask, boolean visible, boolean extended,
                       Class dataType,
                       String name, String title) {
        this.idx = idx;
        this.name = name;
        this.title = title;
        this.byteIdx = byteIdx;
        this.length = length;
        this.startBit = startBit;
        this.mask = mask;
        this.visible = visible;
        this.extended = extended;
        this.dataType = dataType;
    }

    public static EtacsVariantCoding getById(int id) {
        for (EtacsVariantCoding e: values()) {
            if (e.idx == id) {
                return e;
            }
        }
        return EtacsVariantCoding.ETACS_VARIANT_UNKNOWN;
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
        //int valuePos = getAvailableValues(id).stream().filter(v -> v == val).findFirst().orElse(0);
        int valuePos = getAvailableValues(id).indexOf(val);
        if (valuePos == -1) return "unknown";
        ArrayList<String> res = getAvailableOptions(id);
        if (valuePos < res.size()) {
            return res.get(valuePos);
        } else {
            return res.get(res.size()-1);
        }
    }



    public static ArrayList<String> getAvailableOptions(int id) {
        Stream<String> stream;

        switch (id) {
            case 1:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData1.values()).map(EtacsVariantData.EtacsVariantData1::getTitle);
                break;
            case 2:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData2.values()).map(EtacsVariantData.EtacsVariantData2::getTitle);
                break;
            case 3:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData3.values()).map(EtacsVariantData.EtacsVariantData3::getTitle);
                break;
            case 4:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData4.values()).map(EtacsVariantData.EtacsVariantData4::getTitle);
                break;
            case 5:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData5.values()).map(EtacsVariantData.EtacsVariantData5::getTitle);
                break;
            case 6:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData6.values()).map(EtacsVariantData.EtacsVariantData6::getTitle);
                break;
            case 7:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData7.values()).map(EtacsVariantData.EtacsVariantData7::getTitle);
                break;
            case 8:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData8.values()).map(EtacsVariantData.EtacsVariantData8::getTitle);
                break;
            case 9:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData9.values()).map(EtacsVariantData.EtacsVariantData9::getTitle);
                break;
            case 10:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData10.values()).map(EtacsVariantData.EtacsVariantData10::getTitle);
                break;
            case 11:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData11.values()).map(EtacsVariantData.EtacsVariantData11::getTitle);
                break;
            case 12:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData12.values()).map(EtacsVariantData.EtacsVariantData12::getTitle);
                break;
            case 13:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData13.values()).map(EtacsVariantData.EtacsVariantData13::getTitle);
                break;
            case 14:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData14.values()).map(EtacsVariantData.EtacsVariantData14::getTitle);
                break;
            case 15:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData15.values()).map(EtacsVariantData.EtacsVariantData15::getTitle);
                break;
            case 16:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData16.values()).map(EtacsVariantData.EtacsVariantData16::getTitle);
                break;
            case 17:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData17.values()).map(EtacsVariantData.EtacsVariantData17::getTitle);
                break;
            case 18:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData18.values()).map(EtacsVariantData.EtacsVariantData18::getTitle);
                break;
            case 19:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData19.values()).map(EtacsVariantData.EtacsVariantData19::getTitle);
                break;
            case 20:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData20.values()).map(EtacsVariantData.EtacsVariantData20::getTitle);
                break;
            case 21:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData21.values()).map(EtacsVariantData.EtacsVariantData21::getTitle);
                break;
            case 22:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData22.values()).map(EtacsVariantData.EtacsVariantData22::getTitle);
                break;
            case 23:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData23.values()).map(EtacsVariantData.EtacsVariantData23::getTitle);
                break;
            case 24:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData24.values()).map(EtacsVariantData.EtacsVariantData24::getTitle);
                break;
            case 25:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData25.values()).map(EtacsVariantData.EtacsVariantData25::getTitle);
                break;
            case 26:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData26.values()).map(EtacsVariantData.EtacsVariantData26::getTitle);
                break;
            case 27:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData27.values()).map(EtacsVariantData.EtacsVariantData27::getTitle);
                break;
            case 28:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData28.values()).map(EtacsVariantData.EtacsVariantData28::getTitle);
                break;
            case 29:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData29.values()).map(EtacsVariantData.EtacsVariantData29::getTitle);
                break;
            case 30:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData30.values()).map(EtacsVariantData.EtacsVariantData30::getTitle);
                break;
            case 31:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData31.values()).map(EtacsVariantData.EtacsVariantData31::getTitle);
                break;
            case 32:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData32.values()).map(EtacsVariantData.EtacsVariantData32::getTitle);
                break;
            case 33:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData33.values()).map(EtacsVariantData.EtacsVariantData33::getTitle);
                break;
            case 34:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData34.values()).map(EtacsVariantData.EtacsVariantData34::getTitle);
                break;
            case 35:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData35.values()).map(EtacsVariantData.EtacsVariantData35::getTitle);
                break;
            case 36:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData36.values()).map(EtacsVariantData.EtacsVariantData36::getTitle);
                break;
            case 37:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData37.values()).map(EtacsVariantData.EtacsVariantData37::getTitle);
                break;
            case 38:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData38.values()).map(EtacsVariantData.EtacsVariantData38::getTitle);
                break;
            case 39:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData39.values()).map(EtacsVariantData.EtacsVariantData39::getTitle);
                break;
            case 40:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData40.values()).map(EtacsVariantData.EtacsVariantData40::getTitle);
                break;
            case 41:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData41.values()).map(EtacsVariantData.EtacsVariantData41::getTitle);
                break;
            case 42:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData42.values()).map(EtacsVariantData.EtacsVariantData42::getTitle);
                break;
            case 43:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData43.values()).map(EtacsVariantData.EtacsVariantData43::getTitle);
                break;
            case 44:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData44.values()).map(EtacsVariantData.EtacsVariantData44::getTitle);
                break;
            case 45:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData45.values()).map(EtacsVariantData.EtacsVariantData45::getTitle);
                break;
            case 46:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData46.values()).map(EtacsVariantData.EtacsVariantData46::getTitle);
                break;
            case 47:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData47.values()).map(EtacsVariantData.EtacsVariantData47::getTitle);
                break;
            case 48:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData48.values()).map(EtacsVariantData.EtacsVariantData48::getTitle);
                break;
            case 49:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData49.values()).map(EtacsVariantData.EtacsVariantData49::getTitle);
                break;
            case 50:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData50.values()).map(EtacsVariantData.EtacsVariantData50::getTitle);
                break;
            case 51:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData51.values()).map(EtacsVariantData.EtacsVariantData51::getTitle);
                break;
            case 52:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData52.values()).map(EtacsVariantData.EtacsVariantData52::getTitle);
                break;
            case 53:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData53.values()).map(EtacsVariantData.EtacsVariantData53::getTitle);
                break;
            case 54:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData54.values()).map(EtacsVariantData.EtacsVariantData54::getTitle);
                break;
            case 55:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData55.values()).map(EtacsVariantData.EtacsVariantData55::getTitle);
                break;
            case 56:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData56.values()).map(EtacsVariantData.EtacsVariantData56::getTitle);
                break;
            case 57:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData57.values()).map(EtacsVariantData.EtacsVariantData57::getTitle);
                break;
            case 58:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData58.values()).map(EtacsVariantData.EtacsVariantData58::getTitle);
                break;
            case 59:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData59.values()).map(EtacsVariantData.EtacsVariantData59::getTitle);
                break;
            case 60:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData60.values()).map(EtacsVariantData.EtacsVariantData60::getTitle);
                break;
            case 61:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData61.values()).map(EtacsVariantData.EtacsVariantData61::getTitle);
                break;
            case 62:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData62.values()).map(EtacsVariantData.EtacsVariantData62::getTitle);
                break;
            case 63:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData63.values()).map(EtacsVariantData.EtacsVariantData63::getTitle);
                break;
            case 64:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData64.values()).map(EtacsVariantData.EtacsVariantData64::getTitle);
                break;
            case 65:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData65.values()).map(EtacsVariantData.EtacsVariantData65::getTitle);
                break;
            case 66:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData66.values()).map(EtacsVariantData.EtacsVariantData66::getTitle);
                break;
            case 67:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData67.values()).map(EtacsVariantData.EtacsVariantData67::getTitle);
                break;
            case 68:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData68.values()).map(EtacsVariantData.EtacsVariantData68::getTitle);
                break;
            case 69:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData69.values()).map(EtacsVariantData.EtacsVariantData69::getTitle);
                break;
            case 70:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData70.values()).map(EtacsVariantData.EtacsVariantData70::getTitle);
                break;
            case 71:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData71.values()).map(EtacsVariantData.EtacsVariantData71::getTitle);
                break;
            case 72:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData72.values()).map(EtacsVariantData.EtacsVariantData72::getTitle);
                break;
            case 73:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData73.values()).map(EtacsVariantData.EtacsVariantData73::getTitle);
                break;
            case 74:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData74.values()).map(EtacsVariantData.EtacsVariantData74::getTitle);
                break;
            case 75:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData75.values()).map(EtacsVariantData.EtacsVariantData75::getTitle);
                break;
            case 76:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData76.values()).map(EtacsVariantData.EtacsVariantData76::getTitle);
                break;
            case 77:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData77.values()).map(EtacsVariantData.EtacsVariantData77::getTitle);
                break;
            case 78:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData78.values()).map(EtacsVariantData.EtacsVariantData78::getTitle);
                break;
            case 79:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData79.values()).map(EtacsVariantData.EtacsVariantData79::getTitle);
                break;
            case 80:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData80.values()).map(EtacsVariantData.EtacsVariantData80::getTitle);
                break;
            case 81:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData81.values()).map(EtacsVariantData.EtacsVariantData81::getTitle);
                break;
            case 82:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData82.values()).map(EtacsVariantData.EtacsVariantData82::getTitle);
                break;
            case 83:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData83.values()).map(EtacsVariantData.EtacsVariantData83::getTitle);
                break;
            case 84:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData84.values()).map(EtacsVariantData.EtacsVariantData84::getTitle);
                break;
            case 85:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData85.values()).map(EtacsVariantData.EtacsVariantData85::getTitle);
                break;
            case 86:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData86.values()).map(EtacsVariantData.EtacsVariantData86::getTitle);
                break;
            case 87:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData87.values()).map(EtacsVariantData.EtacsVariantData87::getTitle);
                break;
            case 88:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData88.values()).map(EtacsVariantData.EtacsVariantData88::getTitle);
                break;
            case 89:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData89.values()).map(EtacsVariantData.EtacsVariantData89::getTitle);
                break;
            case 90:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData90.values()).map(EtacsVariantData.EtacsVariantData90::getTitle);
                break;
            case 91:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData91.values()).map(EtacsVariantData.EtacsVariantData91::getTitle);
                break;
            case 92:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData92.values()).map(EtacsVariantData.EtacsVariantData92::getTitle);
                break;
            case 93:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData93.values()).map(EtacsVariantData.EtacsVariantData93::getTitle);
                break;
            case 94:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData94.values()).map(EtacsVariantData.EtacsVariantData94::getTitle);
                break;
            case 95:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData95.values()).map(EtacsVariantData.EtacsVariantData95::getTitle);
                break;
            case 96:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData96.values()).map(EtacsVariantData.EtacsVariantData96::getTitle);
                break;
            case 97:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData97.values()).map(EtacsVariantData.EtacsVariantData97::getTitle);
                break;
            case 98:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData98.values()).map(EtacsVariantData.EtacsVariantData98::getTitle);
                break;
            case 99:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData99.values()).map(EtacsVariantData.EtacsVariantData99::getTitle);
                break;
            case 100:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData100.values()).map(EtacsVariantData.EtacsVariantData100::getTitle);
                break;
            case 101:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData101.values()).map(EtacsVariantData.EtacsVariantData101::getTitle);
                break;
            case 102:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData102.values()).map(EtacsVariantData.EtacsVariantData102::getTitle);
                break;
            case 103:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData103.values()).map(EtacsVariantData.EtacsVariantData103::getTitle);
                break;
            case 104:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData104.values()).map(EtacsVariantData.EtacsVariantData104::getTitle);
                break;
            case 105:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData105.values()).map(EtacsVariantData.EtacsVariantData105::getTitle);
                break;
            case 106:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData106.values()).map(EtacsVariantData.EtacsVariantData106::getTitle);
                break;
            case 107:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData107.values()).map(EtacsVariantData.EtacsVariantData107::getTitle);
                break;
            case 108:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData108.values()).map(EtacsVariantData.EtacsVariantData108::getTitle);
                break;
            case 109:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData109.values()).map(EtacsVariantData.EtacsVariantData109::getTitle);
                break;
            case 110:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData110.values()).map(EtacsVariantData.EtacsVariantData110::getTitle);
                break;
            case 111:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData111.values()).map(EtacsVariantData.EtacsVariantData111::getTitle);
                break;
            case 112:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData112.values()).map(EtacsVariantData.EtacsVariantData112::getTitle);
                break;
            case 113:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData113.values()).map(EtacsVariantData.EtacsVariantData113::getTitle);
                break;
            case 114:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData114.values()).map(EtacsVariantData.EtacsVariantData114::getTitle);
                break;
            case 115:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData115.values()).map(EtacsVariantData.EtacsVariantData115::getTitle);
                break;
            case 116:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData116.values()).map(EtacsVariantData.EtacsVariantData116::getTitle);
                break;
            case 117:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData117.values()).map(EtacsVariantData.EtacsVariantData117::getTitle);
                break;
            case 118:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData118.values()).map(EtacsVariantData.EtacsVariantData118::getTitle);
                break;
            case 119:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData119.values()).map(EtacsVariantData.EtacsVariantData119::getTitle);
                break;
            case 120:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData120.values()).map(EtacsVariantData.EtacsVariantData120::getTitle);
                break;
            case 121:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData121.values()).map(EtacsVariantData.EtacsVariantData121::getTitle);
                break;
            case 122:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData122.values()).map(EtacsVariantData.EtacsVariantData122::getTitle);
                break;
            case 123:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData123.values()).map(EtacsVariantData.EtacsVariantData123::getTitle);
                break;
            case 124:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData124.values()).map(EtacsVariantData.EtacsVariantData124::getTitle);
                break;
            case 125:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData125.values()).map(EtacsVariantData.EtacsVariantData125::getTitle);
                break;
            case 126:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData126.values()).map(EtacsVariantData.EtacsVariantData126::getTitle);
                break;
            case 127:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData127.values()).map(EtacsVariantData.EtacsVariantData127::getTitle);
                break;
            case 128:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData128.values()).map(EtacsVariantData.EtacsVariantData128::getTitle);
                break;
            case 129:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData129.values()).map(EtacsVariantData.EtacsVariantData129::getTitle);
                break;
            case 130:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData130.values()).map(EtacsVariantData.EtacsVariantData130::getTitle);
                break;
            case 131:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData131.values()).map(EtacsVariantData.EtacsVariantData131::getTitle);
                break;
            case 132:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData132.values()).map(EtacsVariantData.EtacsVariantData132::getTitle);
                break;
            case 133:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData133.values()).map(EtacsVariantData.EtacsVariantData133::getTitle);
                break;
            case 134:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData134.values()).map(EtacsVariantData.EtacsVariantData134::getTitle);
                break;
            case 135:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData135.values()).map(EtacsVariantData.EtacsVariantData135::getTitle);
                break;
            case 136:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData136.values()).map(EtacsVariantData.EtacsVariantData136::getTitle);
                break;
            case 137:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData137.values()).map(EtacsVariantData.EtacsVariantData137::getTitle);
                break;
            case 138:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData138.values()).map(EtacsVariantData.EtacsVariantData138::getTitle);
                break;
            case 139:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData139.values()).map(EtacsVariantData.EtacsVariantData139::getTitle);
                break;
            case 140:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData140.values()).map(EtacsVariantData.EtacsVariantData140::getTitle);
                break;
            case 141:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData141.values()).map(EtacsVariantData.EtacsVariantData141::getTitle);
                break;
            case 142:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData142.values()).map(EtacsVariantData.EtacsVariantData142::getTitle);
                break;
            case 143:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData143.values()).map(EtacsVariantData.EtacsVariantData143::getTitle);
                break;
            case 144:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData144.values()).map(EtacsVariantData.EtacsVariantData144::getTitle);
                break;
            case 145:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData145.values()).map(EtacsVariantData.EtacsVariantData145::getTitle);
                break;
            case 146:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData146.values()).map(EtacsVariantData.EtacsVariantData146::getTitle);
                break;
            case 147:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData147.values()).map(EtacsVariantData.EtacsVariantData147::getTitle);
                break;
            case 148:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData148.values()).map(EtacsVariantData.EtacsVariantData148::getTitle);
                break;
            case 149:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData149.values()).map(EtacsVariantData.EtacsVariantData149::getTitle);
                break;
            case 150:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData150.values()).map(EtacsVariantData.EtacsVariantData150::getTitle);
                break;
            case 151:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData151.values()).map(EtacsVariantData.EtacsVariantData151::getTitle);
                break;
            case 152:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData152.values()).map(EtacsVariantData.EtacsVariantData152::getTitle);
                break;
            case 153:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData153.values()).map(EtacsVariantData.EtacsVariantData153::getTitle);
                break;
            case 154:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData154.values()).map(EtacsVariantData.EtacsVariantData154::getTitle);
                break;
            case 155:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData155.values()).map(EtacsVariantData.EtacsVariantData155::getTitle);
                break;
            case 156:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData156.values()).map(EtacsVariantData.EtacsVariantData156::getTitle);
                break;
            case 157:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData157.values()).map(EtacsVariantData.EtacsVariantData157::getTitle);
                break;
            case 158:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData158.values()).map(EtacsVariantData.EtacsVariantData158::getTitle);
                break;
            case 159:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData159.values()).map(EtacsVariantData.EtacsVariantData159::getTitle);
                break;
            case 160:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData160.values()).map(EtacsVariantData.EtacsVariantData160::getTitle);
                break;
            case 161:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData161.values()).map(EtacsVariantData.EtacsVariantData161::getTitle);
                break;
            case 162:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData162.values()).map(EtacsVariantData.EtacsVariantData162::getTitle);
                break;
            case 163:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData163.values()).map(EtacsVariantData.EtacsVariantData163::getTitle);
                break;
            case 164:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData164.values()).map(EtacsVariantData.EtacsVariantData164::getTitle);
                break;
            case 165:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData165.values()).map(EtacsVariantData.EtacsVariantData165::getTitle);
                break;
            case 166:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData166.values()).map(EtacsVariantData.EtacsVariantData166::getTitle);
                break;
            case 167:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData167.values()).map(EtacsVariantData.EtacsVariantData167::getTitle);
                break;
            case 168:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData168.values()).map(EtacsVariantData.EtacsVariantData168::getTitle);
                break;
            case 169:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData169.values()).map(EtacsVariantData.EtacsVariantData169::getTitle);
                break;
            case 170:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData170.values()).map(EtacsVariantData.EtacsVariantData170::getTitle);
                break;
            case 171:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData171.values()).map(EtacsVariantData.EtacsVariantData171::getTitle);
                break;
            case 172:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData172.values()).map(EtacsVariantData.EtacsVariantData172::getTitle);
                break;
            case 173:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData173.values()).map(EtacsVariantData.EtacsVariantData173::getTitle);
                break;
            case 174:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData174.values()).map(EtacsVariantData.EtacsVariantData174::getTitle);
                break;
            case 175:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData175.values()).map(EtacsVariantData.EtacsVariantData175::getTitle);
                break;
            case 176:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData176.values()).map(EtacsVariantData.EtacsVariantData176::getTitle);
                break;
            case 177:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData177.values()).map(EtacsVariantData.EtacsVariantData177::getTitle);
                break;
            case 178:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData178.values()).map(EtacsVariantData.EtacsVariantData178::getTitle);
                break;
            case 179:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData179.values()).map(EtacsVariantData.EtacsVariantData179::getTitle);
                break;
            case 180:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData180.values()).map(EtacsVariantData.EtacsVariantData180::getTitle);
                break;
            default: stream = Stream.of("unknown");
        }
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Integer> getAvailableValues(int id) {
        Stream<Integer> stream;

        switch (id) {
            case 1:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData1.values()).map(EtacsVariantData.EtacsVariantData1::getIdx);
                break;
            case 2:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData2.values()).map(EtacsVariantData.EtacsVariantData2::getIdx);
                break;
            case 3:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData3.values()).map(EtacsVariantData.EtacsVariantData3::getIdx);
                break;
            case 4:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData4.values()).map(EtacsVariantData.EtacsVariantData4::getIdx);
                break;
            case 5:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData5.values()).map(EtacsVariantData.EtacsVariantData5::getIdx);
                break;
            case 6:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData6.values()).map(EtacsVariantData.EtacsVariantData6::getIdx);
                break;
            case 7:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData7.values()).map(EtacsVariantData.EtacsVariantData7::getIdx);
                break;
            case 8:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData8.values()).map(EtacsVariantData.EtacsVariantData8::getIdx);
                break;
            case 9:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData9.values()).map(EtacsVariantData.EtacsVariantData9::getIdx);
                break;
            case 10:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData10.values()).map(EtacsVariantData.EtacsVariantData10::getIdx);
                break;
            case 11:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData11.values()).map(EtacsVariantData.EtacsVariantData11::getIdx);
                break;
            case 12:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData12.values()).map(EtacsVariantData.EtacsVariantData12::getIdx);
                break;
            case 13:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData13.values()).map(EtacsVariantData.EtacsVariantData13::getIdx);
                break;
            case 14:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData14.values()).map(EtacsVariantData.EtacsVariantData14::getIdx);
                break;
            case 15:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData15.values()).map(EtacsVariantData.EtacsVariantData15::getIdx);
                break;
            case 16:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData16.values()).map(EtacsVariantData.EtacsVariantData16::getIdx);
                break;
            case 17:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData17.values()).map(EtacsVariantData.EtacsVariantData17::getIdx);
                break;
            case 18:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData18.values()).map(EtacsVariantData.EtacsVariantData18::getIdx);
                break;
            case 19:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData19.values()).map(EtacsVariantData.EtacsVariantData19::getIdx);
                break;
            case 20:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData20.values()).map(EtacsVariantData.EtacsVariantData20::getIdx);
                break;
            case 21:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData21.values()).map(EtacsVariantData.EtacsVariantData21::getIdx);
                break;
            case 22:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData22.values()).map(EtacsVariantData.EtacsVariantData22::getIdx);
                break;
            case 23:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData23.values()).map(EtacsVariantData.EtacsVariantData23::getIdx);
                break;
            case 24:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData24.values()).map(EtacsVariantData.EtacsVariantData24::getIdx);
                break;
            case 25:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData25.values()).map(EtacsVariantData.EtacsVariantData25::getIdx);
                break;
            case 26:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData26.values()).map(EtacsVariantData.EtacsVariantData26::getIdx);
                break;
            case 27:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData27.values()).map(EtacsVariantData.EtacsVariantData27::getIdx);
                break;
            case 28:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData28.values()).map(EtacsVariantData.EtacsVariantData28::getIdx);
                break;
            case 29:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData29.values()).map(EtacsVariantData.EtacsVariantData29::getIdx);
                break;
            case 30:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData30.values()).map(EtacsVariantData.EtacsVariantData30::getIdx);
                break;
            case 31:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData31.values()).map(EtacsVariantData.EtacsVariantData31::getIdx);
                break;
            case 32:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData32.values()).map(EtacsVariantData.EtacsVariantData32::getIdx);
                break;
            case 33:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData33.values()).map(EtacsVariantData.EtacsVariantData33::getIdx);
                break;
            case 34:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData34.values()).map(EtacsVariantData.EtacsVariantData34::getIdx);
                break;
            case 35:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData35.values()).map(EtacsVariantData.EtacsVariantData35::getIdx);
                break;
            case 36:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData36.values()).map(EtacsVariantData.EtacsVariantData36::getIdx);
                break;
            case 37:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData37.values()).map(EtacsVariantData.EtacsVariantData37::getIdx);
                break;
            case 38:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData38.values()).map(EtacsVariantData.EtacsVariantData38::getIdx);
                break;
            case 39:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData39.values()).map(EtacsVariantData.EtacsVariantData39::getIdx);
                break;
            case 40:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData40.values()).map(EtacsVariantData.EtacsVariantData40::getIdx);
                break;
            case 41:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData41.values()).map(EtacsVariantData.EtacsVariantData41::getIdx);
                break;
            case 42:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData42.values()).map(EtacsVariantData.EtacsVariantData42::getIdx);
                break;
            case 43:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData43.values()).map(EtacsVariantData.EtacsVariantData43::getIdx);
                break;
            case 44:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData44.values()).map(EtacsVariantData.EtacsVariantData44::getIdx);
                break;
            case 45:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData45.values()).map(EtacsVariantData.EtacsVariantData45::getIdx);
                break;
            case 46:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData46.values()).map(EtacsVariantData.EtacsVariantData46::getIdx);
                break;
            case 47:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData47.values()).map(EtacsVariantData.EtacsVariantData47::getIdx);
                break;
            case 48:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData48.values()).map(EtacsVariantData.EtacsVariantData48::getIdx);
                break;
            case 49:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData49.values()).map(EtacsVariantData.EtacsVariantData49::getIdx);
                break;
            case 50:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData50.values()).map(EtacsVariantData.EtacsVariantData50::getIdx);
                break;
            case 51:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData51.values()).map(EtacsVariantData.EtacsVariantData51::getIdx);
                break;
            case 52:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData52.values()).map(EtacsVariantData.EtacsVariantData52::getIdx);
                break;
            case 53:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData53.values()).map(EtacsVariantData.EtacsVariantData53::getIdx);
                break;
            case 54:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData54.values()).map(EtacsVariantData.EtacsVariantData54::getIdx);
                break;
            case 55:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData55.values()).map(EtacsVariantData.EtacsVariantData55::getIdx);
                break;
            case 56:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData56.values()).map(EtacsVariantData.EtacsVariantData56::getIdx);
                break;
            case 57:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData57.values()).map(EtacsVariantData.EtacsVariantData57::getIdx);
                break;
            case 58:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData58.values()).map(EtacsVariantData.EtacsVariantData58::getIdx);
                break;
            case 59:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData59.values()).map(EtacsVariantData.EtacsVariantData59::getIdx);
                break;
            case 60:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData60.values()).map(EtacsVariantData.EtacsVariantData60::getIdx);
                break;
            case 61:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData61.values()).map(EtacsVariantData.EtacsVariantData61::getIdx);
                break;
            case 62:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData62.values()).map(EtacsVariantData.EtacsVariantData62::getIdx);
                break;
            case 63:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData63.values()).map(EtacsVariantData.EtacsVariantData63::getIdx);
                break;
            case 64:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData64.values()).map(EtacsVariantData.EtacsVariantData64::getIdx);
                break;
            case 65:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData65.values()).map(EtacsVariantData.EtacsVariantData65::getIdx);
                break;
            case 66:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData66.values()).map(EtacsVariantData.EtacsVariantData66::getIdx);
                break;
            case 67:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData67.values()).map(EtacsVariantData.EtacsVariantData67::getIdx);
                break;
            case 68:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData68.values()).map(EtacsVariantData.EtacsVariantData68::getIdx);
                break;
            case 69:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData69.values()).map(EtacsVariantData.EtacsVariantData69::getIdx);
                break;
            case 70:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData70.values()).map(EtacsVariantData.EtacsVariantData70::getIdx);
                break;
            case 71:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData71.values()).map(EtacsVariantData.EtacsVariantData71::getIdx);
                break;
            case 72:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData72.values()).map(EtacsVariantData.EtacsVariantData72::getIdx);
                break;
            case 73:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData73.values()).map(EtacsVariantData.EtacsVariantData73::getIdx);
                break;
            case 74:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData74.values()).map(EtacsVariantData.EtacsVariantData74::getIdx);
                break;
            case 75:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData75.values()).map(EtacsVariantData.EtacsVariantData75::getIdx);
                break;
            case 76:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData76.values()).map(EtacsVariantData.EtacsVariantData76::getIdx);
                break;
            case 77:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData77.values()).map(EtacsVariantData.EtacsVariantData77::getIdx);
                break;
            case 78:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData78.values()).map(EtacsVariantData.EtacsVariantData78::getIdx);
                break;
            case 79:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData79.values()).map(EtacsVariantData.EtacsVariantData79::getIdx);
                break;
            case 80:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData80.values()).map(EtacsVariantData.EtacsVariantData80::getIdx);
                break;
            case 81:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData81.values()).map(EtacsVariantData.EtacsVariantData81::getIdx);
                break;
            case 82:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData82.values()).map(EtacsVariantData.EtacsVariantData82::getIdx);
                break;
            case 83:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData83.values()).map(EtacsVariantData.EtacsVariantData83::getIdx);
                break;
            case 84:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData84.values()).map(EtacsVariantData.EtacsVariantData84::getIdx);
                break;
            case 85:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData85.values()).map(EtacsVariantData.EtacsVariantData85::getIdx);
                break;
            case 86:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData86.values()).map(EtacsVariantData.EtacsVariantData86::getIdx);
                break;
            case 87:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData87.values()).map(EtacsVariantData.EtacsVariantData87::getIdx);
                break;
            case 88:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData88.values()).map(EtacsVariantData.EtacsVariantData88::getIdx);
                break;
            case 89:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData89.values()).map(EtacsVariantData.EtacsVariantData89::getIdx);
                break;
            case 90:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData90.values()).map(EtacsVariantData.EtacsVariantData90::getIdx);
                break;
            case 91:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData91.values()).map(EtacsVariantData.EtacsVariantData91::getIdx);
                break;
            case 92:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData92.values()).map(EtacsVariantData.EtacsVariantData92::getIdx);
                break;
            case 93:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData93.values()).map(EtacsVariantData.EtacsVariantData93::getIdx);
                break;
            case 94:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData94.values()).map(EtacsVariantData.EtacsVariantData94::getIdx);
                break;
            case 95:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData95.values()).map(EtacsVariantData.EtacsVariantData95::getIdx);
                break;
            case 96:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData96.values()).map(EtacsVariantData.EtacsVariantData96::getIdx);
                break;
            case 97:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData97.values()).map(EtacsVariantData.EtacsVariantData97::getIdx);
                break;
            case 98:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData98.values()).map(EtacsVariantData.EtacsVariantData98::getIdx);
                break;
            case 99:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData99.values()).map(EtacsVariantData.EtacsVariantData99::getIdx);
                break;
            case 100:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData100.values()).map(EtacsVariantData.EtacsVariantData100::getIdx);
                break;
            case 101:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData101.values()).map(EtacsVariantData.EtacsVariantData101::getIdx);
                break;
            case 102:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData102.values()).map(EtacsVariantData.EtacsVariantData102::getIdx);
                break;
            case 103:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData103.values()).map(EtacsVariantData.EtacsVariantData103::getIdx);
                break;
            case 104:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData104.values()).map(EtacsVariantData.EtacsVariantData104::getIdx);
                break;
            case 105:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData105.values()).map(EtacsVariantData.EtacsVariantData105::getIdx);
                break;
            case 106:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData106.values()).map(EtacsVariantData.EtacsVariantData106::getIdx);
                break;
            case 107:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData107.values()).map(EtacsVariantData.EtacsVariantData107::getIdx);
                break;
            case 108:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData108.values()).map(EtacsVariantData.EtacsVariantData108::getIdx);
                break;
            case 109:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData109.values()).map(EtacsVariantData.EtacsVariantData109::getIdx);
                break;
            case 110:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData110.values()).map(EtacsVariantData.EtacsVariantData110::getIdx);
                break;
            case 111:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData111.values()).map(EtacsVariantData.EtacsVariantData111::getIdx);
                break;
            case 112:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData112.values()).map(EtacsVariantData.EtacsVariantData112::getIdx);
                break;
            case 113:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData113.values()).map(EtacsVariantData.EtacsVariantData113::getIdx);
                break;
            case 114:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData114.values()).map(EtacsVariantData.EtacsVariantData114::getIdx);
                break;
            case 115:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData115.values()).map(EtacsVariantData.EtacsVariantData115::getIdx);
                break;
            case 116:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData116.values()).map(EtacsVariantData.EtacsVariantData116::getIdx);
                break;
            case 117:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData117.values()).map(EtacsVariantData.EtacsVariantData117::getIdx);
                break;
            case 118:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData118.values()).map(EtacsVariantData.EtacsVariantData118::getIdx);
                break;
            case 119:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData119.values()).map(EtacsVariantData.EtacsVariantData119::getIdx);
                break;
            case 120:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData120.values()).map(EtacsVariantData.EtacsVariantData120::getIdx);
                break;
            case 121:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData121.values()).map(EtacsVariantData.EtacsVariantData121::getIdx);
                break;
            case 122:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData122.values()).map(EtacsVariantData.EtacsVariantData122::getIdx);
                break;
            case 123:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData123.values()).map(EtacsVariantData.EtacsVariantData123::getIdx);
                break;
            case 124:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData124.values()).map(EtacsVariantData.EtacsVariantData124::getIdx);
                break;
            case 125:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData125.values()).map(EtacsVariantData.EtacsVariantData125::getIdx);
                break;
            case 126:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData126.values()).map(EtacsVariantData.EtacsVariantData126::getIdx);
                break;
            case 127:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData127.values()).map(EtacsVariantData.EtacsVariantData127::getIdx);
                break;
            case 128:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData128.values()).map(EtacsVariantData.EtacsVariantData128::getIdx);
                break;
            case 129:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData129.values()).map(EtacsVariantData.EtacsVariantData129::getIdx);
                break;
            case 130:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData130.values()).map(EtacsVariantData.EtacsVariantData130::getIdx);
                break;
            case 131:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData131.values()).map(EtacsVariantData.EtacsVariantData131::getIdx);
                break;
            case 132:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData132.values()).map(EtacsVariantData.EtacsVariantData132::getIdx);
                break;
            case 133:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData133.values()).map(EtacsVariantData.EtacsVariantData133::getIdx);
                break;
            case 134:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData134.values()).map(EtacsVariantData.EtacsVariantData134::getIdx);
                break;
            case 135:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData135.values()).map(EtacsVariantData.EtacsVariantData135::getIdx);
                break;
            case 136:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData136.values()).map(EtacsVariantData.EtacsVariantData136::getIdx);
                break;
            case 137:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData137.values()).map(EtacsVariantData.EtacsVariantData137::getIdx);
                break;
            case 138:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData138.values()).map(EtacsVariantData.EtacsVariantData138::getIdx);
                break;
            case 139:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData139.values()).map(EtacsVariantData.EtacsVariantData139::getIdx);
                break;
            case 140:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData140.values()).map(EtacsVariantData.EtacsVariantData140::getIdx);
                break;
            case 141:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData141.values()).map(EtacsVariantData.EtacsVariantData141::getIdx);
                break;
            case 142:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData142.values()).map(EtacsVariantData.EtacsVariantData142::getIdx);
                break;
            case 143:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData143.values()).map(EtacsVariantData.EtacsVariantData143::getIdx);
                break;
            case 144:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData144.values()).map(EtacsVariantData.EtacsVariantData144::getIdx);
                break;
            case 145:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData145.values()).map(EtacsVariantData.EtacsVariantData145::getIdx);
                break;
            case 146:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData146.values()).map(EtacsVariantData.EtacsVariantData146::getIdx);
                break;
            case 147:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData147.values()).map(EtacsVariantData.EtacsVariantData147::getIdx);
                break;
            case 148:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData148.values()).map(EtacsVariantData.EtacsVariantData148::getIdx);
                break;
            case 149:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData149.values()).map(EtacsVariantData.EtacsVariantData149::getIdx);
                break;
            case 150:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData150.values()).map(EtacsVariantData.EtacsVariantData150::getIdx);
                break;
            case 151:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData151.values()).map(EtacsVariantData.EtacsVariantData151::getIdx);
                break;
            case 152:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData152.values()).map(EtacsVariantData.EtacsVariantData152::getIdx);
                break;
            case 153:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData153.values()).map(EtacsVariantData.EtacsVariantData153::getIdx);
                break;
            case 154:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData154.values()).map(EtacsVariantData.EtacsVariantData154::getIdx);
                break;
            case 155:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData155.values()).map(EtacsVariantData.EtacsVariantData155::getIdx);
                break;
            case 156:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData156.values()).map(EtacsVariantData.EtacsVariantData156::getIdx);
                break;
            case 157:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData157.values()).map(EtacsVariantData.EtacsVariantData157::getIdx);
                break;
            case 158:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData158.values()).map(EtacsVariantData.EtacsVariantData158::getIdx);
                break;
            case 159:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData159.values()).map(EtacsVariantData.EtacsVariantData159::getIdx);
                break;
            case 160:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData160.values()).map(EtacsVariantData.EtacsVariantData160::getIdx);
                break;
            case 161:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData161.values()).map(EtacsVariantData.EtacsVariantData161::getIdx);
                break;
            case 162:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData162.values()).map(EtacsVariantData.EtacsVariantData162::getIdx);
                break;
            case 163:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData163.values()).map(EtacsVariantData.EtacsVariantData163::getIdx);
                break;
            case 164:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData164.values()).map(EtacsVariantData.EtacsVariantData164::getIdx);
                break;
            case 165:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData165.values()).map(EtacsVariantData.EtacsVariantData165::getIdx);
                break;
            case 166:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData166.values()).map(EtacsVariantData.EtacsVariantData166::getIdx);
                break;
            case 167:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData167.values()).map(EtacsVariantData.EtacsVariantData167::getIdx);
                break;
            case 168:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData168.values()).map(EtacsVariantData.EtacsVariantData168::getIdx);
                break;
            case 169:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData169.values()).map(EtacsVariantData.EtacsVariantData169::getIdx);
                break;
            case 170:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData170.values()).map(EtacsVariantData.EtacsVariantData170::getIdx);
                break;
            case 171:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData171.values()).map(EtacsVariantData.EtacsVariantData171::getIdx);
                break;
            case 172:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData172.values()).map(EtacsVariantData.EtacsVariantData172::getIdx);
                break;
            case 173:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData173.values()).map(EtacsVariantData.EtacsVariantData173::getIdx);
                break;
            case 174:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData174.values()).map(EtacsVariantData.EtacsVariantData174::getIdx);
                break;
            case 175:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData175.values()).map(EtacsVariantData.EtacsVariantData175::getIdx);
                break;
            case 176:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData176.values()).map(EtacsVariantData.EtacsVariantData176::getIdx);
                break;
            case 177:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData177.values()).map(EtacsVariantData.EtacsVariantData177::getIdx);
                break;
            case 178:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData178.values()).map(EtacsVariantData.EtacsVariantData178::getIdx);
                break;
            case 179:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData179.values()).map(EtacsVariantData.EtacsVariantData179::getIdx);
                break;
            case 180:
                stream = Arrays.stream(EtacsVariantData.EtacsVariantData180.values()).map(EtacsVariantData.EtacsVariantData180::getIdx);
                break;
            default: stream = Stream.of(-1);
        }
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
