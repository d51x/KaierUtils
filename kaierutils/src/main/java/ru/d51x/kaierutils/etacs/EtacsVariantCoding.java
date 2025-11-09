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
    ETACS_VARIANT_998(998, 0, 1, 0, 0, false, true,null, "", ""),
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

    public static String getCurrentValue(int id, int val) {
        ArrayList<String> res = getAvailableOptions(id);
        if (val < res.size()) {
            return res.get(val);
        } else {
            return res.get(res.size()-1);
        }
    }

    public static ArrayList<String> getAvailableOptions(int id) {
        Stream<String> stream;

        switch (id) {
            case 1:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData1.values()).map(EtacsCustomData.EtacsCustomData1::getTitle);
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
            default: stream = Stream.of(-1);
        }
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
