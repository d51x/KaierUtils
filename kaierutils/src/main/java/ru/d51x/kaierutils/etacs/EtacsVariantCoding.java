package ru.d51x.kaierutils.etacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum EtacsVariantCoding {

    ETACS_VARIANT_001(1, 0, 7, 0, 0b01111111, false, true, null, "vehicle_line", "Vehicle line", "Вид автомобиля"),
    ETACS_VARIANT_002(2, 1, 7, 0, 0b01111111, false, true,null, "model_year", "Model year", "Модельный год"),
    ETACS_VARIANT_003(3, 1, 1, 7, 0b10000000, false, true, null, "sst_oil_cooling_fan", "SST oil cooling fan", ""),
    ETACS_VARIANT_004(4, 2, 4, 0, 0b00001111, false, true, null, "destination", "Destination", "Страна поставки"),
    ETACS_VARIANT_005(5, 2, 4, 4, 0b11110000, false, true, null, "transmission", "Transmission", "Коробка передач"),
    ETACS_VARIANT_006(6, 6, 8, 0, 0b11111111, false, true, null, "engine_type", "Engine type", "Тип двигателя"),
    ETACS_VARIANT_007(7, 4, 2, 0, 0b00000011, false, true, null, "engine_power", "Engine power", ""),
    ETACS_VARIANT_008(8, 4, 1, 2, 0b00000100, false, true, null, "handle_side", "Handle side", "Расположение органов управления"),
    ETACS_VARIANT_009(9, 4, 4, 3, 0b01111000, false, true, null, "chassis_type_for_asc", "Chassis Type for A.S.C.", "Тип шасси для системы Anti Slip Control"),
    ETACS_VARIANT_010(10, 4, 1, 7, 0b10000000, false, true, null, "oss", "OSS Module", "Модуль обработки команд со штатной кнопки START/STOP ENGINE"),
    ETACS_VARIANT_011(11, 5, 3, 0, 0b00000111, false, true, null, "final_drive", "Final Drive", "Тип привода (перед/зад/полный)"),
    ETACS_VARIANT_012(12, 5, 4, 3, 0b01111000, false, true, null, "transfer", "Transfer", "Тип раздатки"),
    ETACS_VARIANT_013(13, 5, 1, 7, 0b10000000, true, false, null, "ig_off_delay_control", "IG off delay control", "Управление задержкой отключения двигателя при выключении зажигания (примерно 1 сек)"),
    ETACS_VARIANT_014(14, 6, 2, 4, 0b00110000, true, false, null, "dead_lock_operation_customize", "Dead Lock Operation Customize", "Блокировка кнопки открытия на водительской двери"),
    ETACS_VARIANT_015(15, 6, 2, 6, 0b11000000, true, false, null, "after_wipe_customize", "After wipe customize", "Настройка после очистки стекол"),
    ETACS_VARIANT_016_1(16000, 7, 8, 0, 0b11111111, true, true, null, "tire_circumference", "Tire circumference", "Длина окружности шин, влияет на показания спидометра"),
    ETACS_VARIANT_016_2(16001, 8, 8, 0, 0b11111111, true, true, null, "tire_circumference", "Tire circumference", "Длина окружности шин, влияет на показания спидометра"),
    ETACS_VARIANT_017(17, 9, 8, 0, 0b11111111, false, true, null, "fuel_tank", "Fuel tank", "Емкость топливного бака"),
    ETACS_VARIANT_018(18, 10, 3, 0, 0b00000111, true, false, null, "drl_type", "DRL type", "Тип системы DRL"),
    ETACS_VARIANT_019(19, 10, 2, 3, 0b00011000, true, true,null, "smart_entry_system", "Smart entry system", "Интеллектуальная система доступа"),
    ETACS_VARIANT_020(20, 10, 1, 5, 0b00100000, false, true, null, "tpms", "TPMS", "Наличие блока TPMS"),
    ETACS_VARIANT_021(21, 10, 1, 6, 0b01000000, true, false, null, "rke_keyless_entry", "RKE Keyless entry <span color='red'>**</span>", "<red>**</red> Система доступа без использования ключа"),
    ETACS_VARIANT_022(22, 10, 1, 7, 0b10000000, true, false, null, "airbag_auto_hazard", "Airbag Auto Hazard", "Опастность автоматического срабатывания подушек безопасности"),
    ETACS_VARIANT_023(23, 11, 2, 0, 0b00000011, false, true, null, "immobilizer", "Immobilizer", "Блока иммобилайзера"),
    ETACS_VARIANT_024_1(24000, 11, 1, 2, 0b00000100, true, false, null, "cruise_control", "Cruise Control", "Блок круиз-контроля"),
    ETACS_VARIANT_024(24, 11, 1, 3, 0b00001000, true, false, null, "corner_sensor", "Corner sensor", "Блок парктроников (CAN) с отображением на ЦВЕТНОЙ приборке"),
    ETACS_VARIANT_025(25, 11, 1, 4, 0b00010000, true, true, null, "head_lamp_auto_leveling_device", "Head lamp auto leveling device", "Поддержка заданного направления света фар"),
    ETACS_VARIANT_026(26, 11, 1, 5, 0b00100000, false, true, null, "oil_level_warning", "Oil level warning", "Индикация предупреждения низкого уровня масла"),
    ETACS_VARIANT_027(27, 11, 1, 6, 0b01000000, false, true, null, "fuel_filter_warning", "Fuel filter warning", "? Индикация о наличии воды в сепараторе для дизелей ? низкий уровень омывателя ?"),
    ETACS_VARIANT_028(28, 11, 1, 7, 0b10000000, false, true, null, "speed_meter_scale", "Speed meter scale", "Единицы измерения скорости"),
    ETACS_VARIANT_029(29, 12, 1, 0, 0b0000000, true,false, null, "idle_neutral_control", "Idle neutral control", "Контроль нейтрали для 6AT ??"),
    ETACS_VARIANT_030(30, 12, 1, 1, 0b00000010, true, false, null, "security_alarm_sensor", "Security alarm sensor", "Ультразвуковые датчики объема штатной сигнализации"),
    ETACS_VARIANT_031(31, 12, 2, 2, 0b00001100, false, true, null, "tm_oil_cooler", "T/M oil cooler", "Охладитель масла КПП"),
    ETACS_VARIANT_032(32, 12, 1, 4, 0b00010000, true, false, null, "remote_light_on", "Remote Light ON", "Дистанционное управление освещением ?"),
    ETACS_VARIANT_033(33, 12, 1, 6, 0b01000000, false, true,null, "side_air_bag", "Side air bag", "Боковые подушки безопастности"),
    ETACS_VARIANT_034(34, 12, 1, 7, 0b10000000, true, false, null, "acc_power_auto_cut", "ACC power auto cut", "Автоотключение ACC"),
    ETACS_VARIANT_035(35, 13, 4, 0, 0b00001111, false, true,null, "number_of_speaker", "Number of speaker **", "** Количество динамиков"),
    ETACS_VARIANT_036(36, 13, 1, 4, 0b00010000, false, true, null, "seat_material", "Seat material", "Материал обшивки сидений"),
    ETACS_VARIANT_037(37, 13, 3, 5, 0b11100000, true, false,null, "auto_light_control", "Auto Light control", "Настройка уровня автоматического влючения света фар"),
    ETACS_VARIANT_038(38, 14, 2, 0, 0b00000011, false, true, null, "front_differential", "Front differential", "Тип переднего дифференциала"),
    ETACS_VARIANT_039(39, 14, 2, 4, 0b00110000, false, true, null, "rear_differential", "Rear differential", "Тип заднего дифференциала"),
    ETACS_VARIANT_040(40, 15, 3, 0, 0b00000111, true, false, null, "power_window_type", "Power window type", "Тип подачи питания для стеклоподъемников"),
    ETACS_VARIANT_041(41, 15, 3, 4, 0b01110000, true, false, null, "sun_roof_type", "Sun roof type", "Тип потолочного люка"),
    ETACS_VARIANT_042(42, 16, 1, 0, 0b00000001, false, true, null, "wcm", "WCM / KOS Module", "Блок Wireless Control module (KOS)"),
    ETACS_VARIANT_043(43, 16, 1, 1, 0b00000010, false, true, null, "ocm", "OCM - Occupant Classificate module", "Модуль определения наличия пассажира"),
    ETACS_VARIANT_044(44, 16, 1, 2, 0b00000100, false, true, null, "orc", "ORC - Occupant Restraint Controller", "Контроллер систем безопасности пассажиров"),
    ETACS_VARIANT_045(45, 16, 1, 3, 0b00001000, false, true, null, "ac", "A/C Module", "Модуль климат-контроля"),
    ETACS_VARIANT_046(46, 16, 1, 4, 0b00010000, false, true,null, "audio", "AUDIO **", "** Оригинальная аудиосистема"),
    ETACS_VARIANT_047(47, 16, 1, 5, 0b00100000, false, true, null, "and", "AVN/AND Module", "Аудиовизуальное головное устройство с навигацией"),
    ETACS_VARIANT_048(48, 16, 2, 6, 0b11000000, true, false, null, "siren_answer", "Siren answer", "Подтверждение сиреной при закрытии"),
    ETACS_VARIANT_049(49, 17, 1, 0, 0b00000001, true, false, null, "security_alarm_siren", "Security alarm siren", "Cирена охранной системы"),
    ETACS_VARIANT_050(50, 17, 1, 1, 0b00000010, false, true, null, "camera", "CAMERA", "Камера заднего вида"),
    ETACS_VARIANT_051(51, 17, 1, 2, 0b00000100, true, false, null, "corner_sensor_control_unit", "Corner sensor control unit", "Блок управления парктрониками"),
    ETACS_VARIANT_052(52, 17, 1, 3, 0b00001000, false, true, null, "electric_slide_door_left", "Electric Slide door (Left)", "Левая электроприводная сдвижная дверь"),
    ETACS_VARIANT_053(53, 17, 1, 4, 0b00010000, false, true, null, "electric_slide_door_right", "Electric Slide door (Right)", "Правая электроприводная сдвижная дверь"),
    ETACS_VARIANT_054(54, 17, 1, 5, 0b00100000, false, true, null, "etg", "ETG - Electronic Trunk/Gate", "Электропривод багажника"),
    ETACS_VARIANT_055(55, 17, 1, 6, 0b01000000, true, false, null, "ess_ecu", "ESS ECU", "Модуль аварийной сигнализация при экстренном торможении"),
    ETACS_VARIANT_056(56, 17, 1, 7, 0b10000000, false, true, null, "hfm", "HFM - HandsFree Module", "Модуль громкой связи"),
    ETACS_VARIANT_057(57, 18, 2, 0, 0b00000011, true, false, null, "intelligent_comfort_washer_custom", "Intelligent/Comfort washer custom", "Настройки функции омывателя"),
    ETACS_VARIANT_058(58, 18, 2, 2, 0b00001100, true, false, null, "head_lamp_leveling_system_type", "Head Lamp Leveling system type", "Тип системы поддержания заданного направления света фар"),
    ETACS_VARIANT_059(59, 18, 1, 4, 0b00010000, true, false, null, "rear_wiper_mode", "Rear wiper mode", "Режим работы заднего стеклоочистителя"),
    ETACS_VARIANT_060(60, 18, 1, 5, 0b00100000, true, false, null, "kos_door_entry_type", "KOS door entry type", "Тип системы доступа без использования ключа"),
    ETACS_VARIANT_061(61, 18, 2, 6, 0b11000000, true, false, null, "rear_wiper_by_reverse_customize", "Rear wiper by reverse customize", "Задний стеклоочиститель с функцией включения при заднем ходе"),
    ETACS_VARIANT_062(62, 19, 1, 0, 0b00000001, false, true, null, "abs", "ABS", "Система ABS"),
    ETACS_VARIANT_063(63, 19, 1, 1, 0b00000010, false, true, null, "asc", "ASC", "Система ASC"),
    ETACS_VARIANT_064(64, 19, 1, 2, 0b00000100, true, false,null, "auto_fold_mirror", "Auto fold mirror", "Складывающиеся зеркала с электроприводом"),
    ETACS_VARIANT_065(65, 19, 1, 3, 0b00001000, false, true, null, "sas", "SAS wheel angle sensor", "Датчик угла поворота руля"),
    ETACS_VARIANT_066(66, 19, 1, 4, 0b00010000, false, true, null, "awc_4wd", "4WD / AWC", ""),
    ETACS_VARIANT_067(67, 19, 1, 5, 0b00100000, false, true,null, "tcm", "TCM", ""),
    ETACS_VARIANT_068(68, 19, 1, 6, 0b01000000, false, true,null, "actv_stb", "ACTV_STB", ""),
    ETACS_VARIANT_069(69, 19, 1, 7, 0b10000000, true, false,null, "door_unlock_by_ig_lock_customize", "Door unlock by IG lock customize", "Отпирание дверей с соответствии с настройкой блокировки выключателя зажигания"),
    ETACS_VARIANT_070(70, 20, 1, 0, 0b00000001, false, true,null, "shift_lock", "Shift lock", "Блокировка переключения"),
    ETACS_VARIANT_071(71, 20, 1, 1, 0b00000010, false, true,null, "eps", "EPS (Electric Power Steering)", "Электро-усилитель руля"),
    ETACS_VARIANT_072(72, 20, 1, 2, 0b00000100, false, true,null, "acdayc", "ACD/AYC System", "Система полного привода AYC - ACD"),
    ETACS_VARIANT_073(73, 20, 2, 3, 0b00011000, true, false, null, "сoming_home_light_customize", "Coming home light customize", "Включение света фар на 30 сек при ключе в замке в положении Lock"),
    ETACS_VARIANT_074(74, 20, 2, 5, 0b01100000, true, false,null, "welcome_light_customize", "Welcome light customize", "Настройка освещения при отпирании дверей брелоком"),
    ETACS_VARIANT_075(75, 20, 1, 7, 0b10000000, true, false,null, "indirect_lamp", "Indirect lamp", "Подсветка"),
    ETACS_VARIANT_076(76, 21, 1, 0, 0b00000001, false, true,null, "power_window_dr", "Power window Dr", "Электрический стеклоподъемник двери водителя"),
    ETACS_VARIANT_077(77, 21, 1, 1, 0b00000010, false, true,null, "power_window_as", "Power window As", "Электрический стеклоподъемник двери пассажира"),
    ETACS_VARIANT_078(78, 21, 1, 2, 0b00000100, false, true,null, "power_window_rr", "Power window RR", "Электрический стеклоподъемник задней правой двери"),
    ETACS_VARIANT_079(79, 21, 1, 3, 0b00001000, false, true,null, "power_window_rl", "Power window RL", "Электрический стеклоподъемник задней левой двери"),
    ETACS_VARIANT_080(80, 21, 1, 4, 0b00010000, true, false,null, "ess_by_stop_lamp", "ESS by stop lamp", "Cигнализация ESS путем мигания стоп-сигнала"),
    ETACS_VARIANT_081(81, 21, 1, 5, 0b00100000, false, true,null, "sun_roof", "Sun roof", "Наличие потолочного люка"),
    ETACS_VARIANT_082(82, 21, 1, 6, 0b01000000, true, false,null, "rain_light_sensor", "Rain Light Sensor (RLS) **", "Датчик дождя"),
    ETACS_VARIANT_205(205, 21, 1, 7, 0b10000000, true, false,null, "washer_function_improvement", "Washer function improvement", "При коротком нажатии рычага 5 брызгов на стекло"),
    ETACS_VARIANT_083(83, 22, 2, 0, 0b00000011, true, false, null, "ig_key_illumination", "IG key illumination", "Подсветка выключателя зажигания"),
    ETACS_VARIANT_084(84, 22, 2, 2, 0b00001100, true, false,null, "turn_signal_bulb", "Turn signal bulb", "Тип ламп указателей поворота"),
    ETACS_VARIANT_085(85, 22, 1, 4, 0b00010000, true, false,null, "rear_wiper", "Rear wiper", "Задний стеклоочиститель"),
    ETACS_VARIANT_086(86, 22, 1, 5, 0b00100000, true, false,null, "fold_mirror", "Fold mirror", "Складываемые заркала"),
    ETACS_VARIANT_087(87, 22, 1, 6, 0b01000000, true, false,null, "head_lamp", "Head lamp", "Тип блока передних фар"),
    ETACS_VARIANT_088(88, 22, 1, 7, 0b10000000, true, false, null, "kos_function_customize_by_disp", "KOS function customize by Disp", "Отображение функций кастомизации на дисплее MMCS"),
    ETACS_VARIANT_089(89, 23, 4, 0, 0b00001111, true, false,null, "head_lamp_washer", "Head lamp washer", "Тип омывателя передних фар"),
    ETACS_VARIANT_090(90, 23, 1, 4, 0b00010000, true, false,null, "front_fog_lamp_mode", "Front fog lamp mode", "Режим работы передних противотуманных фар"),
    ETACS_VARIANT_091(91, 23, 1, 5, 0b00100000, true, false,null, "front_fog_lamp", "Front fog lamp **", "** Передние противотуманные фары"),
    ETACS_VARIANT_092(92, 23, 2, 6, 0b11000000, true, false,null, "rear_fog_lamp", "Rear fog lamp **", "** Задние противотуманные фары"),
    ETACS_VARIANT_093(93, 24, 1, 0, 0b00000001, true, false, null, "room_lamp_delay_timer_door_hl", "Room lamp delay timer/door & H/L", "Таймер задержки освещения/двери и высокая/низкая яркость"),
    ETACS_VARIANT_094(94, 24, 2, 1, 0b00000110, true, false, null, "room_lamp_by_hl", "Room lamp by H/L", "Режим яркости освещения салона (высокая/низкая яркость )"),
    ETACS_VARIANT_095(95, 24, 2, 3, 0b00011000, true, false,null, "gate_trunk_lamp", "Gate/Trunk lamp", "Плафон освещения задней двери/багажного отделения"),
    ETACS_VARIANT_096(96, 24, 2, 5, 0b01100000, true, false,null, "head_lamp_auto_cut_mode", "Head lamp auto cut mode", "Режим автоматического переключения света фар"),
    ETACS_VARIANT_097(97, 24, 1, 7, 0b10000000, true, false,null, "head_lamp_auto_cut", "Head lamp auto cut", "Автоматическое переключение света фар"),
    ETACS_VARIANT_098(98, 25, 3, 0, 0b00000111, true, false, null, "door_lock_system", "Door lock system", "Система блокировки дверей"),
    ETACS_VARIANT_099(99, 25, 2, 3, 0b00011000, true, false,null, "auto_door_lock_unlock", "Auto door lock/unlock", "Автоматическая блокировка / разюлокировка центрального замка"),
    ETACS_VARIANT_100(100, 25, 2, 5, 0b01100000, true, false,null, "key_reminder_unlock", "Key reminder unlock", "Предупреждение о ключе в замке зажигания при открывании двери"),
    ETACS_VARIANT_101(101, 25, 1, 7, 0b10000000, true, false,null, "horn_type", "Horn type **", "** Тип звукового сигнала"),
    ETACS_VARIANT_102(102, 26, 2, 0, 0b00000011, true, false,null, "gate_trunk_opener_mode", "Gate/trunk opener mode", "Режим работы электропривода двери багажного отделения"),
    ETACS_VARIANT_103(103, 26, 1, 2, 0b00000100, true, false,null, "cooling_fan", "Cooling fan", "Тип управления вентилятором системы охлаждения двигателя"),
    ETACS_VARIANT_104(104, 26, 2, 3, 0b00011000, true, false,null, "security_alarm_mode", "Security alarm mode", "Режим работы звуковой сигнализации охранной системы"),
    ETACS_VARIANT_105(105, 26, 2, 5, 0b01100000, true, false,null, "security_alarm_function", "Security alarm function", "Функция звуковой сигнализации охранной системы"),
    ETACS_VARIANT_106(106, 26, 1, 7, 0b10000000, true, false,null, "pre_alarm", "Pre-alarm", "Предварительный звуковой сигнал"),
    ETACS_VARIANT_107(107, 27, 1, 0, 0b00000001, true, false,null, "multi_mode_rke", "Multi mode RKE (remote key entry)", "Многорежимный RKE (remote key entry)"),
    ETACS_VARIANT_108(108, 27, 1, 1, 0b00000010, true, false,null, "gate_trunk", "Gate/Trunk", "Тип двери багажного отделения"),
    ETACS_VARIANT_109(109, 27, 2, 2, 0b00001100, true, false,null, "manner_switch", "Manner Switch", "Переключатель режима управления"),
    ETACS_VARIANT_110(110, 27, 2, 4, 0b00110000, true, false,null, "remote_engine_starter", "Remote engine starter **", "** Возможность дистанционного запуска двигателя"),
    ETACS_VARIANT_111(111, 27, 1, 6, 0b01000000, true, false,null, "panic_alarm", "Panic Alarm **", "** Предупредительная сигнализация"),
    ETACS_VARIANT_112(112, 27, 1, 7, 0b10000000, false, true, null, "right_side_view_camera", "Right side view camera", "Боковая правая камера"),
    ETACS_VARIANT_113(113, 28, 2, 0, 0b00000011, true, false,null, "front_wiper", "Front wiper **", "** Передний стеклоочиститель"),
    ETACS_VARIANT_114(114, 28, 2, 2, 0b00001100, true, false,null, "comfort_flasher_type", "Comfort flasher type", "Кратковременное касание к включению поворотника вызывает моргание 3 раза"),
    ETACS_VARIANT_115(115, 28, 1, 4, 0b00010000, true, false,null, "room_lamp_center_switch", "Room lamp center switch", "Центральный переключатель освещения салона"),
    ETACS_VARIANT_116(116, 28, 1, 5, 0b00100000, true, false,null, "wiper_washer_check_valve", "Wiper washer check valve", ""),
    ETACS_VARIANT_117(117, 28, 2, 6, 0b11000000, false, true,null, "audio_sradio_type", "AUDIO/S.RADIO type", ""),
    ETACS_VARIANT_118(118, 29, 3, 0, 0b00000111, false, true,null, "hl_leveling_type", "H/L leveling type", "Тип устройства поддержания направления света фар"),
    ETACS_VARIANT_119(119, 29, 3, 3, 0b00111000, false, true,null, "afs_acl_type", "AFS/ACL type", "Тип системы AFS (Adaptive Frontlight System)/ACL (Active Cornering Light)"),
    ETACS_VARIANT_120(120, 29, 1, 6, 0b01000000, true, false,null, "ess_by_turn_lamp", "ESS by turn lamp", "Cигнализация ESS путем включения указателей поворотов"),
    ETACS_VARIANT_121(121, 30, 2, 0, 0b00000011, false, true,null, "compressor_type", "Compressor type **", "** Тип компрессора"),
    ETACS_VARIANT_122(122, 30, 1, 3, 0b00001000, false, true,null, "temperature_type", "Temperature type", "Единицы измерения температуры"),
    ETACS_VARIANT_123(123, 30, 1, 4, 0b00010000, false, true,null, "rear_view_camera", "Rear view camera", "Камера заднего вида"),
    ETACS_VARIANT_124(124, 30, 1, 5, 0b00100000, false, true,null, "nose_view_camera", "Nose view camera", "Камера переднего вида"),
    ETACS_VARIANT_125(125, 30, 1, 6, 0b01000000, false, true,null, "left_side_view_camera", "Left side view camera", "Боковая левая камера"),
    ETACS_VARIANT_126(126, 30, 1, 7, 0b10000000, true, false,null, "average_speed", "Average speed", "Отображение средней скорости"),
    ETACS_VARIANT_127(127, 31, 4, 0, 0b00001111, false, true,null, "vehicle_language_status", "Vehicle language status", "Язык приборной панели"),
    ETACS_VARIANT_128(128, 31, 2, 4, 0b00110000, false, true,null, "fuel_amount", "Fuel amount", "Единицы измерения объема топлива"),
    ETACS_VARIANT_129(129, 31, 2, 6, 0b11000000, true, false,null, "fuel_consumption_scale", "Fuel consumption scale", "Единицы отображения расхода топлива"),
    ETACS_VARIANT_130(130, 32, 1, 0, 0b00000001, true, false,null, "seat_belt_reminder_logic", "Seat belt reminder logic", "Логика напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_131(131, 32, 1, 1, 0b00000010, true, false,null, "coolant_temp_gauge_threshold", "Coolant temp gauge threshold", "Предел температуры охлаждающей жидкости по указателю"),
    ETACS_VARIANT_132(132, 32, 3, 2, 0b00011100, true, false,null, "frost_warning_threshold", "Frost warning threshold", "Граница индикации опасности появления гололеда"),
    ETACS_VARIANT_133(133, 32, 1, 5, 0b00100000, true, false,null, "distance_to_empty", "Distance to empty", "Отображение запаса хода по топливу"),
    ETACS_VARIANT_134(134, 32, 1, 6, 0b01000000, true, false,null, "average_fuel_consumption", "Average fuel consumption", "Отображение среднего расход топлива"),
    ETACS_VARIANT_135(135, 32, 1, 7, 0b10000000, true, false,null, "instant_fuel_consumption", "Instant fuel consumption", "Отображение мгновенного расход топлива"),
    ETACS_VARIANT_136(136, 33, 1, 0, 0b00000001, true, false,null, "time_travelled", "Time travelled", "Отображение времени поездки"),
    ETACS_VARIANT_137(137, 33, 1, 1, 0b00000010, true, false,null, "distance_travelled", "Distance travelled", "Отображение пройденной дистанции"),
    ETACS_VARIANT_138(138, 33, 1, 2, 0b00000100, true, false,null, "fuel_used", "Fuel used", "Отображение кол-ва использованного топлива"),
    ETACS_VARIANT_139(139, 33, 1, 3, 0b00001000, true, false,null, "trip_autoreset_ig_off", "Trip autoreset IG OFF", "Выключение сброса показаний маршрутного компьютера при выключении зажигания"),
    ETACS_VARIANT_140(140, 33, 1, 4, 0b00010000, true, false,null, "variable_speed_alarm", "Variable Speed Alarm", "Сигнализация о превышении переменной скорости. Устанавливается долгим нажатием кнопки Info при движении"),
    ETACS_VARIANT_141(141, 33, 1, 5, 0b00100000, true, false, null, "rest_reminder", "Rest reminder", "Напоминание об отдыхе"),
    ETACS_VARIANT_142(142, 33, 1, 6, 0b01000000, true, false,null, "instant_speed", "Instant speed", "? мгновенная скорость ?"),
    ETACS_VARIANT_143(143, 33, 1, 7, 0b10000000, true, false,null, "seat_belt_reminder_control_type", "Seat belt reminder control type", "Тип управления системой напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_144(144, 34, 1, 0, 0b00000001, true, false,null, "seat_belt_reminder_flashing", "Seat belt reminder flashing", "Мигание индикатора напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_145(145, 34, 2, 1, 0b00000110, true, false,null, "seat_belt_reminder_indicator", "Seat belt reminder indicator", "Индикатор напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_146(146, 34, 1, 3, 0b00001000, true, false,null, "reverse_alarm", "Reverse alarm", "Звуковой сигнализатор заднего хода"),
    ETACS_VARIANT_147(147, 34, 1, 4, 0b00010000, true, false,null, "key_reminder", "Key reminder", "Напоминание о ключе в замке зажигания"),
    ETACS_VARIANT_148(148, 34, 1, 5, 0b00100000, true,false, null, "lighting_monitor", "Lighting monitor", "? Замок зажигания Pajero ?"),
    ETACS_VARIANT_149(149, 34, 1, 6, 0b01000000, true, false,null, "gcc_speed_alarm", "GCC speed alarm", "Сигнализация о превышении заданной величины скорости"),
    ETACS_VARIANT_150(150, 34, 1, 7, 0b10000000, true, false,null, "condition_buzzer", "Condition buzzer", ""),
    ETACS_VARIANT_151(151, 35, 1, 0, 0b00000001, false, true,null, "rent_a_car_mode_ig_off_always", "Rent-a-car mode IG-OFF always", "Специальный режим выключения зажигания для арендуемых автомобилей"),
    ETACS_VARIANT_152(152, 35, 1, 1, 0b00000010, false, true,null, "fuel_tank_type", "Fuel tank type", "Тип топливного бака"),
    ETACS_VARIANT_153(153, 35, 5, 2, 0b01111100, true, false,null, "service_reminder_schedule_table", "Service reminder schedule table", "Напоминания об очередном техническом обслуживании"),
    ETACS_VARIANT_154(154, 35, 1, 7, 0b10000000, true, false,null, "gcc_speed_alarm_indicator", "GCC speed alarm indicator", "Предупредительный сигнализатор превышения заданной скорости"),
    ETACS_VARIANT_155(155, 36, 4, 0, 0b00001111, false, true,null, "tpms_information", "TPMS information", "Давление в шинах для TPMS"),
    ETACS_VARIANT_156(156, 36, 2, 4, 0b00110000, true, false,null, "horn_chirp_by_keyless", "Horn chirp by keyless", "Подтверждение сигналом при открытии / закрытии"),
    ETACS_VARIANT_157(157, 36, 1, 6, 0b01000000, true, false,null, "rear_sr_unlock_output", "Rear S/R Unlock Output", "Выходной сигнал разблокировки заднего S/R"),
    ETACS_VARIANT_158(158, 36, 1, 7, 0b10000000, false, true,null, "trailer_turn_detection", "Trailer turn detection", "Обнаружение наличия прицепа"),
    ETACS_VARIANT_159(159, 37, 1, 0, 0b00000001, false, true,null, "shift_Lever", "Shift Lever", "Рычаг переключения передач ??"),
    ETACS_VARIANT_160(160, 37, 1, 1, 0b00000010, false, true,null, "adaptive_front_lighting_system", "AFS - Adaptive Front-Lighting System", "Модуль AFS (Adaptive Front-Lighting System)"),
    ETACS_VARIANT_161(161, 37, 1, 2, 0b00000100, false, true,null, "satellite_radio", "Satellite Radio", "Спутниковое радио"),
    ETACS_VARIANT_162(162, 37, 1, 3, 0b00001000, false, true,null, "auto_stop_go", "Auto Stop & Go (AS&G)", "Автоматическия система стоп-старт"),
    ETACS_VARIANT_163(163, 37, 3, 4, 0b01110000, false, true,null, "display_opening_type", "Display opening type", "Тип первоначального рабочего окна"),
    ETACS_VARIANT_164(164, 37, 1, 7, 0b10000000, true, false,null, "smart_entry_auto_lock_customize", "Smart entry auto lock customize", "Автопостановка на охрану (при наличии KOS)"), //GSI system  [Система GSI]
    ETACS_VARIANT_165(165, 38, 2, 0, 0b00000011, true, false,null, "drl_function", "DRL function **", "** Функция DRL"),
    ETACS_VARIANT_166(166, 38, 1, 2, 0b00000100, false, true,null, "facu", "FACU", ""),
    ETACS_VARIANT_167(167, 38, 1, 3, 0b00001000, true, false,null, "s_awc_control_display", "S-AWC Control display", "Отображение режимов S-AWC на дисплее"),
    ETACS_VARIANT_168(168, 38, 1, 4, 0b00010000, false, true,null, "diesel_particulate_filter", "Diesel particulate filter", "Сажевый фильтр"),
    ETACS_VARIANT_169(169, 38, 1, 5, 0b00100000, false, true,null, "language_mode", "Language mode", "Режим настройки языка интерфейса"),
    ETACS_VARIANT_170(170, 38, 1, 6, 0b01000000, false, true,null, "wss", "Steering Wheel Switch ?", "? переключатель на рулевом колесе ?"),
    ETACS_VARIANT_171(171, 38, 1, 7, 0b10000000, true, false,null, "door_unlock_mode_customize", "Door Unlock Mode Customize **", "** Настройка режима отпирания дверей"),
    ETACS_VARIANT_172(172, 39, 3, 0, 0b00000111, false, true,null, "rls_overwipe_type", "RLS overwipe type", "со стеклоочистителем RLS"),
    ETACS_VARIANT_173(173, 39, 2, 3, 0b00011000, false, true,null, "rls_ws_type", "RLS WS type", "тип RLS WS"),
    ETACS_VARIANT_174(174, 39, 1, 7, 0b10000000, false, true,null, "launch_gear_block_alarm", "Launch gear block alarm", "Сигнализатор блокировки на первой передаче"),
    ETACS_VARIANT_175(175, 40, 4, 0, 0b00001111, false, true,null, "security_sensor_gain_setting", "Security sensor gain setting", "Настройка чувствительности датчика охранной системы"),
    ETACS_VARIANT_176(176, 40, 1, 5, 0b00100000, false, true,null, "indirect_lamp_type", "Indirect lamp type", "Тип подсветки"),
    ETACS_VARIANT_177(177, 40, 2, 6, 0b11000000, false, true,null, "4wd_switch_type", "4WD switch type", "Тип переключателя 4WD"),
    ETACS_VARIANT_178(178, 40, 1, 0, 0b00000001, false, true,null, "miev_remote_ecu", "MiEV REMOTE ECU **", "** Блок дистанционной системы доступа"),
    ETACS_VARIANT_179(179, 40, 1, 1, 0b00000010, false, true,null, "automatic_high_beam", "Automatic High Beam", "Автопереключение дальнего света"),
    ETACS_VARIANT_180(180, 40, 1, 2, 0b00000100, false, true,null, "automatic_high_beam_function", "Automatic High Beam function", "Функции переключения автоматического дальнего света"),
    ETACS_VARIANT_UNKNOWN(999, 0, 0, 0, 0, false, true, null, "unknown", "unknown", "");
    private final int idx;
    private final String name;
    private final String title;
    private final String summary;
    private final int byteIdx;
    private final int length;
    private final int startBit;
    private final int mask;
    private Class dataType;
    private final boolean visible;
    private final boolean extended;

    EtacsVariantCoding(int idx, int byteIdx, int length,
                      int startBit, int mask, boolean visible, boolean extended,
                       Class dataType,
                       String name, String title, String summary) {
        this.idx = idx;
        this.name = name;
        this.title = title;
        this.summary = summary;
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
        Stream<String> stream = switch (id) {
            case 1 -> Arrays.stream(EtacsVariantData.EtacsVariantData1.values()).map(EtacsVariantData.EtacsVariantData1::getTitle);
            case 2 -> Arrays.stream(EtacsVariantData.EtacsVariantData2.values()).map(EtacsVariantData.EtacsVariantData2::getTitle);
            case 3 -> Arrays.stream(EtacsVariantData.EtacsVariantData3.values()).map(EtacsVariantData.EtacsVariantData3::getTitle);
            case 4 -> Arrays.stream(EtacsVariantData.EtacsVariantData4.values()).map(EtacsVariantData.EtacsVariantData4::getTitle);
            case 5 -> Arrays.stream(EtacsVariantData.EtacsVariantData5.values()).map(EtacsVariantData.EtacsVariantData5::getTitle);
            case 6 -> Arrays.stream(EtacsVariantData.EtacsVariantData6.values()).map(EtacsVariantData.EtacsVariantData6::getTitle);
            case 7 -> Arrays.stream(EtacsVariantData.EtacsVariantData7.values()).map(EtacsVariantData.EtacsVariantData7::getTitle);
            case 8 -> Arrays.stream(EtacsVariantData.EtacsVariantData8.values()).map(EtacsVariantData.EtacsVariantData8::getTitle);
            case 9 -> Arrays.stream(EtacsVariantData.EtacsVariantData9.values()).map(EtacsVariantData.EtacsVariantData9::getTitle);
            case 10 -> Arrays.stream(EtacsVariantData.EtacsVariantData10.values()).map(EtacsVariantData.EtacsVariantData10::getTitle);
            case 11 -> Arrays.stream(EtacsVariantData.EtacsVariantData11.values()).map(EtacsVariantData.EtacsVariantData11::getTitle);
            case 12 -> Arrays.stream(EtacsVariantData.EtacsVariantData12.values()).map(EtacsVariantData.EtacsVariantData12::getTitle);
            case 13 -> Arrays.stream(EtacsVariantData.EtacsVariantData13.values()).map(EtacsVariantData.EtacsVariantData13::getTitle);
            case 14 -> Arrays.stream(EtacsVariantData.EtacsVariantData14.values()).map(EtacsVariantData.EtacsVariantData14::getTitle);
            case 15 -> Arrays.stream(EtacsVariantData.EtacsVariantData15.values()).map(EtacsVariantData.EtacsVariantData15::getTitle);
            case 16 -> Arrays.stream(EtacsVariantData.EtacsVariantData16.values()).map(EtacsVariantData.EtacsVariantData16::getTitle);
            case 17 -> Arrays.stream(EtacsVariantData.EtacsVariantData17.values()).map(EtacsVariantData.EtacsVariantData17::getTitle);
            case 18 -> Arrays.stream(EtacsVariantData.EtacsVariantData18.values()).map(EtacsVariantData.EtacsVariantData18::getTitle);
            case 19 -> Arrays.stream(EtacsVariantData.EtacsVariantData19.values()).map(EtacsVariantData.EtacsVariantData19::getTitle);
            case 20 -> Arrays.stream(EtacsVariantData.EtacsVariantData20.values()).map(EtacsVariantData.EtacsVariantData20::getTitle);
            case 21 -> Arrays.stream(EtacsVariantData.EtacsVariantData21.values()).map(EtacsVariantData.EtacsVariantData21::getTitle);
            case 22 -> Arrays.stream(EtacsVariantData.EtacsVariantData22.values()).map(EtacsVariantData.EtacsVariantData22::getTitle);
            case 23 -> Arrays.stream(EtacsVariantData.EtacsVariantData23.values()).map(EtacsVariantData.EtacsVariantData23::getTitle);
            case 24 -> Arrays.stream(EtacsVariantData.EtacsVariantData24.values()).map(EtacsVariantData.EtacsVariantData24::getTitle);
            case 25 -> Arrays.stream(EtacsVariantData.EtacsVariantData25.values()).map(EtacsVariantData.EtacsVariantData25::getTitle);
            case 26 -> Arrays.stream(EtacsVariantData.EtacsVariantData26.values()).map(EtacsVariantData.EtacsVariantData26::getTitle);
            case 27 -> Arrays.stream(EtacsVariantData.EtacsVariantData27.values()).map(EtacsVariantData.EtacsVariantData27::getTitle);
            case 28 -> Arrays.stream(EtacsVariantData.EtacsVariantData28.values()).map(EtacsVariantData.EtacsVariantData28::getTitle);
            case 29 -> Arrays.stream(EtacsVariantData.EtacsVariantData29.values()).map(EtacsVariantData.EtacsVariantData29::getTitle);
            case 30 -> Arrays.stream(EtacsVariantData.EtacsVariantData30.values()).map(EtacsVariantData.EtacsVariantData30::getTitle);
            case 31 -> Arrays.stream(EtacsVariantData.EtacsVariantData31.values()).map(EtacsVariantData.EtacsVariantData31::getTitle);
            case 32 -> Arrays.stream(EtacsVariantData.EtacsVariantData32.values()).map(EtacsVariantData.EtacsVariantData32::getTitle);
            case 33 -> Arrays.stream(EtacsVariantData.EtacsVariantData33.values()).map(EtacsVariantData.EtacsVariantData33::getTitle);
            case 34 -> Arrays.stream(EtacsVariantData.EtacsVariantData34.values()).map(EtacsVariantData.EtacsVariantData34::getTitle);
            case 35 -> Arrays.stream(EtacsVariantData.EtacsVariantData35.values()).map(EtacsVariantData.EtacsVariantData35::getTitle);
            case 36 -> Arrays.stream(EtacsVariantData.EtacsVariantData36.values()).map(EtacsVariantData.EtacsVariantData36::getTitle);
            case 37 -> Arrays.stream(EtacsVariantData.EtacsVariantData37.values()).map(EtacsVariantData.EtacsVariantData37::getTitle);
            case 38 -> Arrays.stream(EtacsVariantData.EtacsVariantData38.values()).map(EtacsVariantData.EtacsVariantData38::getTitle);
            case 39 -> Arrays.stream(EtacsVariantData.EtacsVariantData39.values()).map(EtacsVariantData.EtacsVariantData39::getTitle);
            case 40 -> Arrays.stream(EtacsVariantData.EtacsVariantData40.values()).map(EtacsVariantData.EtacsVariantData40::getTitle);
            case 41 -> Arrays.stream(EtacsVariantData.EtacsVariantData41.values()).map(EtacsVariantData.EtacsVariantData41::getTitle);
            case 42 -> Arrays.stream(EtacsVariantData.EtacsVariantData42.values()).map(EtacsVariantData.EtacsVariantData42::getTitle);
            case 43 -> Arrays.stream(EtacsVariantData.EtacsVariantData43.values()).map(EtacsVariantData.EtacsVariantData43::getTitle);
            case 44 -> Arrays.stream(EtacsVariantData.EtacsVariantData44.values()).map(EtacsVariantData.EtacsVariantData44::getTitle);
            case 45 -> Arrays.stream(EtacsVariantData.EtacsVariantData45.values()).map(EtacsVariantData.EtacsVariantData45::getTitle);
            case 46 -> Arrays.stream(EtacsVariantData.EtacsVariantData46.values()).map(EtacsVariantData.EtacsVariantData46::getTitle);
            case 47 -> Arrays.stream(EtacsVariantData.EtacsVariantData47.values()).map(EtacsVariantData.EtacsVariantData47::getTitle);
            case 48 -> Arrays.stream(EtacsVariantData.EtacsVariantData48.values()).map(EtacsVariantData.EtacsVariantData48::getTitle);
            case 49 -> Arrays.stream(EtacsVariantData.EtacsVariantData49.values()).map(EtacsVariantData.EtacsVariantData49::getTitle);
            case 50 -> Arrays.stream(EtacsVariantData.EtacsVariantData50.values()).map(EtacsVariantData.EtacsVariantData50::getTitle);
            case 51 -> Arrays.stream(EtacsVariantData.EtacsVariantData51.values()).map(EtacsVariantData.EtacsVariantData51::getTitle);
            case 52 -> Arrays.stream(EtacsVariantData.EtacsVariantData52.values()).map(EtacsVariantData.EtacsVariantData52::getTitle);
            case 53 -> Arrays.stream(EtacsVariantData.EtacsVariantData53.values()).map(EtacsVariantData.EtacsVariantData53::getTitle);
            case 54 -> Arrays.stream(EtacsVariantData.EtacsVariantData54.values()).map(EtacsVariantData.EtacsVariantData54::getTitle);
            case 55 -> Arrays.stream(EtacsVariantData.EtacsVariantData55.values()).map(EtacsVariantData.EtacsVariantData55::getTitle);
            case 56 -> Arrays.stream(EtacsVariantData.EtacsVariantData56.values()).map(EtacsVariantData.EtacsVariantData56::getTitle);
            case 57 -> Arrays.stream(EtacsVariantData.EtacsVariantData57.values()).map(EtacsVariantData.EtacsVariantData57::getTitle);
            case 58 -> Arrays.stream(EtacsVariantData.EtacsVariantData58.values()).map(EtacsVariantData.EtacsVariantData58::getTitle);
            case 59 -> Arrays.stream(EtacsVariantData.EtacsVariantData59.values()).map(EtacsVariantData.EtacsVariantData59::getTitle);
            case 60 -> Arrays.stream(EtacsVariantData.EtacsVariantData60.values()).map(EtacsVariantData.EtacsVariantData60::getTitle);
            case 61 -> Arrays.stream(EtacsVariantData.EtacsVariantData61.values()).map(EtacsVariantData.EtacsVariantData61::getTitle);
            case 62 -> Arrays.stream(EtacsVariantData.EtacsVariantData62.values()).map(EtacsVariantData.EtacsVariantData62::getTitle);
            case 63 -> Arrays.stream(EtacsVariantData.EtacsVariantData63.values()).map(EtacsVariantData.EtacsVariantData63::getTitle);
            case 64 -> Arrays.stream(EtacsVariantData.EtacsVariantData64.values()).map(EtacsVariantData.EtacsVariantData64::getTitle);
            case 65 -> Arrays.stream(EtacsVariantData.EtacsVariantData65.values()).map(EtacsVariantData.EtacsVariantData65::getTitle);
            case 66 -> Arrays.stream(EtacsVariantData.EtacsVariantData66.values()).map(EtacsVariantData.EtacsVariantData66::getTitle);
            case 67 -> Arrays.stream(EtacsVariantData.EtacsVariantData67.values()).map(EtacsVariantData.EtacsVariantData67::getTitle);
            case 68 -> Arrays.stream(EtacsVariantData.EtacsVariantData68.values()).map(EtacsVariantData.EtacsVariantData68::getTitle);
            case 69 -> Arrays.stream(EtacsVariantData.EtacsVariantData69.values()).map(EtacsVariantData.EtacsVariantData69::getTitle);
            case 70 -> Arrays.stream(EtacsVariantData.EtacsVariantData70.values()).map(EtacsVariantData.EtacsVariantData70::getTitle);
            case 71 -> Arrays.stream(EtacsVariantData.EtacsVariantData71.values()).map(EtacsVariantData.EtacsVariantData71::getTitle);
            case 72 -> Arrays.stream(EtacsVariantData.EtacsVariantData72.values()).map(EtacsVariantData.EtacsVariantData72::getTitle);
            case 73 -> Arrays.stream(EtacsVariantData.EtacsVariantData73.values()).map(EtacsVariantData.EtacsVariantData73::getTitle);
            case 74 -> Arrays.stream(EtacsVariantData.EtacsVariantData74.values()).map(EtacsVariantData.EtacsVariantData74::getTitle);
            case 75 -> Arrays.stream(EtacsVariantData.EtacsVariantData75.values()).map(EtacsVariantData.EtacsVariantData75::getTitle);
            case 76 -> Arrays.stream(EtacsVariantData.EtacsVariantData76.values()).map(EtacsVariantData.EtacsVariantData76::getTitle);
            case 77 -> Arrays.stream(EtacsVariantData.EtacsVariantData77.values()).map(EtacsVariantData.EtacsVariantData77::getTitle);
            case 78 -> Arrays.stream(EtacsVariantData.EtacsVariantData78.values()).map(EtacsVariantData.EtacsVariantData78::getTitle);
            case 79 -> Arrays.stream(EtacsVariantData.EtacsVariantData79.values()).map(EtacsVariantData.EtacsVariantData79::getTitle);
            case 80 -> Arrays.stream(EtacsVariantData.EtacsVariantData80.values()).map(EtacsVariantData.EtacsVariantData80::getTitle);
            case 81 -> Arrays.stream(EtacsVariantData.EtacsVariantData81.values()).map(EtacsVariantData.EtacsVariantData81::getTitle);
            case 82 -> Arrays.stream(EtacsVariantData.EtacsVariantData82.values()).map(EtacsVariantData.EtacsVariantData82::getTitle);
            case 83 -> Arrays.stream(EtacsVariantData.EtacsVariantData83.values()).map(EtacsVariantData.EtacsVariantData83::getTitle);
            case 84 -> Arrays.stream(EtacsVariantData.EtacsVariantData84.values()).map(EtacsVariantData.EtacsVariantData84::getTitle);
            case 85 -> Arrays.stream(EtacsVariantData.EtacsVariantData85.values()).map(EtacsVariantData.EtacsVariantData85::getTitle);
            case 86 -> Arrays.stream(EtacsVariantData.EtacsVariantData86.values()).map(EtacsVariantData.EtacsVariantData86::getTitle);
            case 87 -> Arrays.stream(EtacsVariantData.EtacsVariantData87.values()).map(EtacsVariantData.EtacsVariantData87::getTitle);
            case 88 -> Arrays.stream(EtacsVariantData.EtacsVariantData88.values()).map(EtacsVariantData.EtacsVariantData88::getTitle);
            case 89 -> Arrays.stream(EtacsVariantData.EtacsVariantData89.values()).map(EtacsVariantData.EtacsVariantData89::getTitle);
            case 90 -> Arrays.stream(EtacsVariantData.EtacsVariantData90.values()).map(EtacsVariantData.EtacsVariantData90::getTitle);
            case 91 -> Arrays.stream(EtacsVariantData.EtacsVariantData91.values()).map(EtacsVariantData.EtacsVariantData91::getTitle);
            case 92 -> Arrays.stream(EtacsVariantData.EtacsVariantData92.values()).map(EtacsVariantData.EtacsVariantData92::getTitle);
            case 93 -> Arrays.stream(EtacsVariantData.EtacsVariantData93.values()).map(EtacsVariantData.EtacsVariantData93::getTitle);
            case 94 -> Arrays.stream(EtacsVariantData.EtacsVariantData94.values()).map(EtacsVariantData.EtacsVariantData94::getTitle);
            case 95 -> Arrays.stream(EtacsVariantData.EtacsVariantData95.values()).map(EtacsVariantData.EtacsVariantData95::getTitle);
            case 96 -> Arrays.stream(EtacsVariantData.EtacsVariantData96.values()).map(EtacsVariantData.EtacsVariantData96::getTitle);
            case 97 -> Arrays.stream(EtacsVariantData.EtacsVariantData97.values()).map(EtacsVariantData.EtacsVariantData97::getTitle);
            case 98 -> Arrays.stream(EtacsVariantData.EtacsVariantData98.values()).map(EtacsVariantData.EtacsVariantData98::getTitle);
            case 99 -> Arrays.stream(EtacsVariantData.EtacsVariantData99.values()).map(EtacsVariantData.EtacsVariantData99::getTitle);
            case 100 -> Arrays.stream(EtacsVariantData.EtacsVariantData100.values()).map(EtacsVariantData.EtacsVariantData100::getTitle);
            case 101 -> Arrays.stream(EtacsVariantData.EtacsVariantData101.values()).map(EtacsVariantData.EtacsVariantData101::getTitle);
            case 102 -> Arrays.stream(EtacsVariantData.EtacsVariantData102.values()).map(EtacsVariantData.EtacsVariantData102::getTitle);
            case 103 -> Arrays.stream(EtacsVariantData.EtacsVariantData103.values()).map(EtacsVariantData.EtacsVariantData103::getTitle);
            case 104 -> Arrays.stream(EtacsVariantData.EtacsVariantData104.values()).map(EtacsVariantData.EtacsVariantData104::getTitle);
            case 105 -> Arrays.stream(EtacsVariantData.EtacsVariantData105.values()).map(EtacsVariantData.EtacsVariantData105::getTitle);
            case 106 -> Arrays.stream(EtacsVariantData.EtacsVariantData106.values()).map(EtacsVariantData.EtacsVariantData106::getTitle);
            case 107 -> Arrays.stream(EtacsVariantData.EtacsVariantData107.values()).map(EtacsVariantData.EtacsVariantData107::getTitle);
            case 108 -> Arrays.stream(EtacsVariantData.EtacsVariantData108.values()).map(EtacsVariantData.EtacsVariantData108::getTitle);
            case 109 -> Arrays.stream(EtacsVariantData.EtacsVariantData109.values()).map(EtacsVariantData.EtacsVariantData109::getTitle);
            case 110 -> Arrays.stream(EtacsVariantData.EtacsVariantData110.values()).map(EtacsVariantData.EtacsVariantData110::getTitle);
            case 111 -> Arrays.stream(EtacsVariantData.EtacsVariantData111.values()).map(EtacsVariantData.EtacsVariantData111::getTitle);
            case 112 -> Arrays.stream(EtacsVariantData.EtacsVariantData112.values()).map(EtacsVariantData.EtacsVariantData112::getTitle);
            case 113 -> Arrays.stream(EtacsVariantData.EtacsVariantData113.values()).map(EtacsVariantData.EtacsVariantData113::getTitle);
            case 114 -> Arrays.stream(EtacsVariantData.EtacsVariantData114.values()).map(EtacsVariantData.EtacsVariantData114::getTitle);
            case 115 -> Arrays.stream(EtacsVariantData.EtacsVariantData115.values()).map(EtacsVariantData.EtacsVariantData115::getTitle);
            case 116 -> Arrays.stream(EtacsVariantData.EtacsVariantData116.values()).map(EtacsVariantData.EtacsVariantData116::getTitle);
            case 117 -> Arrays.stream(EtacsVariantData.EtacsVariantData117.values()).map(EtacsVariantData.EtacsVariantData117::getTitle);
            case 118 -> Arrays.stream(EtacsVariantData.EtacsVariantData118.values()).map(EtacsVariantData.EtacsVariantData118::getTitle);
            case 119 -> Arrays.stream(EtacsVariantData.EtacsVariantData119.values()).map(EtacsVariantData.EtacsVariantData119::getTitle);
            case 120 -> Arrays.stream(EtacsVariantData.EtacsVariantData120.values()).map(EtacsVariantData.EtacsVariantData120::getTitle);
            case 121 -> Arrays.stream(EtacsVariantData.EtacsVariantData121.values()).map(EtacsVariantData.EtacsVariantData121::getTitle);
            case 122 -> Arrays.stream(EtacsVariantData.EtacsVariantData122.values()).map(EtacsVariantData.EtacsVariantData122::getTitle);
            case 123 -> Arrays.stream(EtacsVariantData.EtacsVariantData123.values()).map(EtacsVariantData.EtacsVariantData123::getTitle);
            case 124 -> Arrays.stream(EtacsVariantData.EtacsVariantData124.values()).map(EtacsVariantData.EtacsVariantData124::getTitle);
            case 125 -> Arrays.stream(EtacsVariantData.EtacsVariantData125.values()).map(EtacsVariantData.EtacsVariantData125::getTitle);
            case 126 -> Arrays.stream(EtacsVariantData.EtacsVariantData126.values()).map(EtacsVariantData.EtacsVariantData126::getTitle);
            case 127 -> Arrays.stream(EtacsVariantData.EtacsVariantData127.values()).map(EtacsVariantData.EtacsVariantData127::getTitle);
            case 128 -> Arrays.stream(EtacsVariantData.EtacsVariantData128.values()).map(EtacsVariantData.EtacsVariantData128::getTitle);
            case 129 -> Arrays.stream(EtacsVariantData.EtacsVariantData129.values()).map(EtacsVariantData.EtacsVariantData129::getTitle);
            case 130 -> Arrays.stream(EtacsVariantData.EtacsVariantData130.values()).map(EtacsVariantData.EtacsVariantData130::getTitle);
            case 131 -> Arrays.stream(EtacsVariantData.EtacsVariantData131.values()).map(EtacsVariantData.EtacsVariantData131::getTitle);
            case 132 -> Arrays.stream(EtacsVariantData.EtacsVariantData132.values()).map(EtacsVariantData.EtacsVariantData132::getTitle);
            case 133 -> Arrays.stream(EtacsVariantData.EtacsVariantData133.values()).map(EtacsVariantData.EtacsVariantData133::getTitle);
            case 134 -> Arrays.stream(EtacsVariantData.EtacsVariantData134.values()).map(EtacsVariantData.EtacsVariantData134::getTitle);
            case 135 -> Arrays.stream(EtacsVariantData.EtacsVariantData135.values()).map(EtacsVariantData.EtacsVariantData135::getTitle);
            case 136 -> Arrays.stream(EtacsVariantData.EtacsVariantData136.values()).map(EtacsVariantData.EtacsVariantData136::getTitle);
            case 137 -> Arrays.stream(EtacsVariantData.EtacsVariantData137.values()).map(EtacsVariantData.EtacsVariantData137::getTitle);
            case 138 -> Arrays.stream(EtacsVariantData.EtacsVariantData138.values()).map(EtacsVariantData.EtacsVariantData138::getTitle);
            case 139 -> Arrays.stream(EtacsVariantData.EtacsVariantData139.values()).map(EtacsVariantData.EtacsVariantData139::getTitle);
            case 140 -> Arrays.stream(EtacsVariantData.EtacsVariantData140.values()).map(EtacsVariantData.EtacsVariantData140::getTitle);
            case 141 -> Arrays.stream(EtacsVariantData.EtacsVariantData141.values()).map(EtacsVariantData.EtacsVariantData141::getTitle);
            case 142 -> Arrays.stream(EtacsVariantData.EtacsVariantData142.values()).map(EtacsVariantData.EtacsVariantData142::getTitle);
            case 143 -> Arrays.stream(EtacsVariantData.EtacsVariantData143.values()).map(EtacsVariantData.EtacsVariantData143::getTitle);
            case 144 -> Arrays.stream(EtacsVariantData.EtacsVariantData144.values()).map(EtacsVariantData.EtacsVariantData144::getTitle);
            case 145 -> Arrays.stream(EtacsVariantData.EtacsVariantData145.values()).map(EtacsVariantData.EtacsVariantData145::getTitle);
            case 146 -> Arrays.stream(EtacsVariantData.EtacsVariantData146.values()).map(EtacsVariantData.EtacsVariantData146::getTitle);
            case 147 -> Arrays.stream(EtacsVariantData.EtacsVariantData147.values()).map(EtacsVariantData.EtacsVariantData147::getTitle);
            case 148 -> Arrays.stream(EtacsVariantData.EtacsVariantData148.values()).map(EtacsVariantData.EtacsVariantData148::getTitle);
            case 149 -> Arrays.stream(EtacsVariantData.EtacsVariantData149.values()).map(EtacsVariantData.EtacsVariantData149::getTitle);
            case 150 -> Arrays.stream(EtacsVariantData.EtacsVariantData150.values()).map(EtacsVariantData.EtacsVariantData150::getTitle);
            case 151 -> Arrays.stream(EtacsVariantData.EtacsVariantData151.values()).map(EtacsVariantData.EtacsVariantData151::getTitle);
            case 152 -> Arrays.stream(EtacsVariantData.EtacsVariantData152.values()).map(EtacsVariantData.EtacsVariantData152::getTitle);
            case 153 -> Arrays.stream(EtacsVariantData.EtacsVariantData153.values()).map(EtacsVariantData.EtacsVariantData153::getTitle);
            case 154 -> Arrays.stream(EtacsVariantData.EtacsVariantData154.values()).map(EtacsVariantData.EtacsVariantData154::getTitle);
            case 155 -> Arrays.stream(EtacsVariantData.EtacsVariantData155.values()).map(EtacsVariantData.EtacsVariantData155::getTitle);
            case 156 -> Arrays.stream(EtacsVariantData.EtacsVariantData156.values()).map(EtacsVariantData.EtacsVariantData156::getTitle);
            case 157 -> Arrays.stream(EtacsVariantData.EtacsVariantData157.values()).map(EtacsVariantData.EtacsVariantData157::getTitle);
            case 158 -> Arrays.stream(EtacsVariantData.EtacsVariantData158.values()).map(EtacsVariantData.EtacsVariantData158::getTitle);
            case 159 -> Arrays.stream(EtacsVariantData.EtacsVariantData159.values()).map(EtacsVariantData.EtacsVariantData159::getTitle);
            case 160 -> Arrays.stream(EtacsVariantData.EtacsVariantData160.values()).map(EtacsVariantData.EtacsVariantData160::getTitle);
            case 161 -> Arrays.stream(EtacsVariantData.EtacsVariantData161.values()).map(EtacsVariantData.EtacsVariantData161::getTitle);
            case 162 -> Arrays.stream(EtacsVariantData.EtacsVariantData162.values()).map(EtacsVariantData.EtacsVariantData162::getTitle);
            case 163 -> Arrays.stream(EtacsVariantData.EtacsVariantData163.values()).map(EtacsVariantData.EtacsVariantData163::getTitle);
            case 164 -> Arrays.stream(EtacsVariantData.EtacsVariantData164.values()).map(EtacsVariantData.EtacsVariantData164::getTitle);
            case 165 -> Arrays.stream(EtacsVariantData.EtacsVariantData165.values()).map(EtacsVariantData.EtacsVariantData165::getTitle);
            case 166 -> Arrays.stream(EtacsVariantData.EtacsVariantData166.values()).map(EtacsVariantData.EtacsVariantData166::getTitle);
            case 167 -> Arrays.stream(EtacsVariantData.EtacsVariantData167.values()).map(EtacsVariantData.EtacsVariantData167::getTitle);
            case 168 -> Arrays.stream(EtacsVariantData.EtacsVariantData168.values()).map(EtacsVariantData.EtacsVariantData168::getTitle);
            case 169 -> Arrays.stream(EtacsVariantData.EtacsVariantData169.values()).map(EtacsVariantData.EtacsVariantData169::getTitle);
            case 170 -> Arrays.stream(EtacsVariantData.EtacsVariantData170.values()).map(EtacsVariantData.EtacsVariantData170::getTitle);
            case 171 -> Arrays.stream(EtacsVariantData.EtacsVariantData171.values()).map(EtacsVariantData.EtacsVariantData171::getTitle);
            case 172 -> Arrays.stream(EtacsVariantData.EtacsVariantData172.values()).map(EtacsVariantData.EtacsVariantData172::getTitle);
            case 173 -> Arrays.stream(EtacsVariantData.EtacsVariantData173.values()).map(EtacsVariantData.EtacsVariantData173::getTitle);
            case 174 -> Arrays.stream(EtacsVariantData.EtacsVariantData174.values()).map(EtacsVariantData.EtacsVariantData174::getTitle);
            case 175 -> Arrays.stream(EtacsVariantData.EtacsVariantData175.values()).map(EtacsVariantData.EtacsVariantData175::getTitle);
            case 176 -> Arrays.stream(EtacsVariantData.EtacsVariantData176.values()).map(EtacsVariantData.EtacsVariantData176::getTitle);
            case 177 -> Arrays.stream(EtacsVariantData.EtacsVariantData177.values()).map(EtacsVariantData.EtacsVariantData177::getTitle);
            case 178 -> Arrays.stream(EtacsVariantData.EtacsVariantData178.values()).map(EtacsVariantData.EtacsVariantData178::getTitle);
            case 179 -> Arrays.stream(EtacsVariantData.EtacsVariantData179.values()).map(EtacsVariantData.EtacsVariantData179::getTitle);
            case 180 -> Arrays.stream(EtacsVariantData.EtacsVariantData180.values()).map(EtacsVariantData.EtacsVariantData180::getTitle);
            case 205 -> Arrays.stream(EtacsVariantData.EtacsVariantData205.values()).map(EtacsVariantData.EtacsVariantData205::getTitle);
            default -> Stream.of("unknown");
        };

        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Integer> getAvailableValues(int id) {
        Stream<Integer> stream = switch (id) {
            case 1 -> Arrays.stream(EtacsVariantData.EtacsVariantData1.values()).map(EtacsVariantData.EtacsVariantData1::getIdx);
            case 2 -> Arrays.stream(EtacsVariantData.EtacsVariantData2.values()).map(EtacsVariantData.EtacsVariantData2::getIdx);
            case 3 -> Arrays.stream(EtacsVariantData.EtacsVariantData3.values()).map(EtacsVariantData.EtacsVariantData3::getIdx);
            case 4 -> Arrays.stream(EtacsVariantData.EtacsVariantData4.values()).map(EtacsVariantData.EtacsVariantData4::getIdx);
            case 5 -> Arrays.stream(EtacsVariantData.EtacsVariantData5.values()).map(EtacsVariantData.EtacsVariantData5::getIdx);
            case 6 -> Arrays.stream(EtacsVariantData.EtacsVariantData6.values()).map(EtacsVariantData.EtacsVariantData6::getIdx);
            case 7 -> Arrays.stream(EtacsVariantData.EtacsVariantData7.values()).map(EtacsVariantData.EtacsVariantData7::getIdx);
            case 8 -> Arrays.stream(EtacsVariantData.EtacsVariantData8.values()).map(EtacsVariantData.EtacsVariantData8::getIdx);
            case 9 -> Arrays.stream(EtacsVariantData.EtacsVariantData9.values()).map(EtacsVariantData.EtacsVariantData9::getIdx);
            case 10 -> Arrays.stream(EtacsVariantData.EtacsVariantData10.values()).map(EtacsVariantData.EtacsVariantData10::getIdx);
            case 11 -> Arrays.stream(EtacsVariantData.EtacsVariantData11.values()).map(EtacsVariantData.EtacsVariantData11::getIdx);
            case 12 -> Arrays.stream(EtacsVariantData.EtacsVariantData12.values()).map(EtacsVariantData.EtacsVariantData12::getIdx);
            case 13 -> Arrays.stream(EtacsVariantData.EtacsVariantData13.values()).map(EtacsVariantData.EtacsVariantData13::getIdx);
            case 14 -> Arrays.stream(EtacsVariantData.EtacsVariantData14.values()).map(EtacsVariantData.EtacsVariantData14::getIdx);
            case 15 -> Arrays.stream(EtacsVariantData.EtacsVariantData15.values()).map(EtacsVariantData.EtacsVariantData15::getIdx);
            case 16 -> Arrays.stream(EtacsVariantData.EtacsVariantData16.values()).map(EtacsVariantData.EtacsVariantData16::getIdx);
            case 17 -> Arrays.stream(EtacsVariantData.EtacsVariantData17.values()).map(EtacsVariantData.EtacsVariantData17::getIdx);
            case 18 -> Arrays.stream(EtacsVariantData.EtacsVariantData18.values()).map(EtacsVariantData.EtacsVariantData18::getIdx);
            case 19 -> Arrays.stream(EtacsVariantData.EtacsVariantData19.values()).map(EtacsVariantData.EtacsVariantData19::getIdx);
            case 20 -> Arrays.stream(EtacsVariantData.EtacsVariantData20.values()).map(EtacsVariantData.EtacsVariantData20::getIdx);
            case 21 -> Arrays.stream(EtacsVariantData.EtacsVariantData21.values()).map(EtacsVariantData.EtacsVariantData21::getIdx);
            case 22 -> Arrays.stream(EtacsVariantData.EtacsVariantData22.values()).map(EtacsVariantData.EtacsVariantData22::getIdx);
            case 23 -> Arrays.stream(EtacsVariantData.EtacsVariantData23.values()).map(EtacsVariantData.EtacsVariantData23::getIdx);
            case 24 -> Arrays.stream(EtacsVariantData.EtacsVariantData24.values()).map(EtacsVariantData.EtacsVariantData24::getIdx);
            case 25 -> Arrays.stream(EtacsVariantData.EtacsVariantData25.values()).map(EtacsVariantData.EtacsVariantData25::getIdx);
            case 26 -> Arrays.stream(EtacsVariantData.EtacsVariantData26.values()).map(EtacsVariantData.EtacsVariantData26::getIdx);
            case 27 -> Arrays.stream(EtacsVariantData.EtacsVariantData27.values()).map(EtacsVariantData.EtacsVariantData27::getIdx);
            case 28 -> Arrays.stream(EtacsVariantData.EtacsVariantData28.values()).map(EtacsVariantData.EtacsVariantData28::getIdx);
            case 29 -> Arrays.stream(EtacsVariantData.EtacsVariantData29.values()).map(EtacsVariantData.EtacsVariantData29::getIdx);
            case 30 -> Arrays.stream(EtacsVariantData.EtacsVariantData30.values()).map(EtacsVariantData.EtacsVariantData30::getIdx);
            case 31 -> Arrays.stream(EtacsVariantData.EtacsVariantData31.values()).map(EtacsVariantData.EtacsVariantData31::getIdx);
            case 32 -> Arrays.stream(EtacsVariantData.EtacsVariantData32.values()).map(EtacsVariantData.EtacsVariantData32::getIdx);
            case 33 -> Arrays.stream(EtacsVariantData.EtacsVariantData33.values()).map(EtacsVariantData.EtacsVariantData33::getIdx);
            case 34 -> Arrays.stream(EtacsVariantData.EtacsVariantData34.values()).map(EtacsVariantData.EtacsVariantData34::getIdx);
            case 35 -> Arrays.stream(EtacsVariantData.EtacsVariantData35.values()).map(EtacsVariantData.EtacsVariantData35::getIdx);
            case 36 -> Arrays.stream(EtacsVariantData.EtacsVariantData36.values()).map(EtacsVariantData.EtacsVariantData36::getIdx);
            case 37 -> Arrays.stream(EtacsVariantData.EtacsVariantData37.values()).map(EtacsVariantData.EtacsVariantData37::getIdx);
            case 38 -> Arrays.stream(EtacsVariantData.EtacsVariantData38.values()).map(EtacsVariantData.EtacsVariantData38::getIdx);
            case 39 -> Arrays.stream(EtacsVariantData.EtacsVariantData39.values()).map(EtacsVariantData.EtacsVariantData39::getIdx);
            case 40 -> Arrays.stream(EtacsVariantData.EtacsVariantData40.values()).map(EtacsVariantData.EtacsVariantData40::getIdx);
            case 41 -> Arrays.stream(EtacsVariantData.EtacsVariantData41.values()).map(EtacsVariantData.EtacsVariantData41::getIdx);
            case 42 -> Arrays.stream(EtacsVariantData.EtacsVariantData42.values()).map(EtacsVariantData.EtacsVariantData42::getIdx);
            case 43 -> Arrays.stream(EtacsVariantData.EtacsVariantData43.values()).map(EtacsVariantData.EtacsVariantData43::getIdx);
            case 44 -> Arrays.stream(EtacsVariantData.EtacsVariantData44.values()).map(EtacsVariantData.EtacsVariantData44::getIdx);
            case 45 -> Arrays.stream(EtacsVariantData.EtacsVariantData45.values()).map(EtacsVariantData.EtacsVariantData45::getIdx);
            case 46 -> Arrays.stream(EtacsVariantData.EtacsVariantData46.values()).map(EtacsVariantData.EtacsVariantData46::getIdx);
            case 47 -> Arrays.stream(EtacsVariantData.EtacsVariantData47.values()).map(EtacsVariantData.EtacsVariantData47::getIdx);
            case 48 -> Arrays.stream(EtacsVariantData.EtacsVariantData48.values()).map(EtacsVariantData.EtacsVariantData48::getIdx);
            case 49 -> Arrays.stream(EtacsVariantData.EtacsVariantData49.values()).map(EtacsVariantData.EtacsVariantData49::getIdx);
            case 50 -> Arrays.stream(EtacsVariantData.EtacsVariantData50.values()).map(EtacsVariantData.EtacsVariantData50::getIdx);
            case 51 -> Arrays.stream(EtacsVariantData.EtacsVariantData51.values()).map(EtacsVariantData.EtacsVariantData51::getIdx);
            case 52 -> Arrays.stream(EtacsVariantData.EtacsVariantData52.values()).map(EtacsVariantData.EtacsVariantData52::getIdx);
            case 53 -> Arrays.stream(EtacsVariantData.EtacsVariantData53.values()).map(EtacsVariantData.EtacsVariantData53::getIdx);
            case 54 -> Arrays.stream(EtacsVariantData.EtacsVariantData54.values()).map(EtacsVariantData.EtacsVariantData54::getIdx);
            case 55 -> Arrays.stream(EtacsVariantData.EtacsVariantData55.values()).map(EtacsVariantData.EtacsVariantData55::getIdx);
            case 56 -> Arrays.stream(EtacsVariantData.EtacsVariantData56.values()).map(EtacsVariantData.EtacsVariantData56::getIdx);
            case 57 -> Arrays.stream(EtacsVariantData.EtacsVariantData57.values()).map(EtacsVariantData.EtacsVariantData57::getIdx);
            case 58 -> Arrays.stream(EtacsVariantData.EtacsVariantData58.values()).map(EtacsVariantData.EtacsVariantData58::getIdx);
            case 59 -> Arrays.stream(EtacsVariantData.EtacsVariantData59.values()).map(EtacsVariantData.EtacsVariantData59::getIdx);
            case 60 -> Arrays.stream(EtacsVariantData.EtacsVariantData60.values()).map(EtacsVariantData.EtacsVariantData60::getIdx);
            case 61 -> Arrays.stream(EtacsVariantData.EtacsVariantData61.values()).map(EtacsVariantData.EtacsVariantData61::getIdx);
            case 62 -> Arrays.stream(EtacsVariantData.EtacsVariantData62.values()).map(EtacsVariantData.EtacsVariantData62::getIdx);
            case 63 -> Arrays.stream(EtacsVariantData.EtacsVariantData63.values()).map(EtacsVariantData.EtacsVariantData63::getIdx);
            case 64 -> Arrays.stream(EtacsVariantData.EtacsVariantData64.values()).map(EtacsVariantData.EtacsVariantData64::getIdx);
            case 65 -> Arrays.stream(EtacsVariantData.EtacsVariantData65.values()).map(EtacsVariantData.EtacsVariantData65::getIdx);
            case 66 -> Arrays.stream(EtacsVariantData.EtacsVariantData66.values()).map(EtacsVariantData.EtacsVariantData66::getIdx);
            case 67 -> Arrays.stream(EtacsVariantData.EtacsVariantData67.values()).map(EtacsVariantData.EtacsVariantData67::getIdx);
            case 68 -> Arrays.stream(EtacsVariantData.EtacsVariantData68.values()).map(EtacsVariantData.EtacsVariantData68::getIdx);
            case 69 -> Arrays.stream(EtacsVariantData.EtacsVariantData69.values()).map(EtacsVariantData.EtacsVariantData69::getIdx);
            case 70 -> Arrays.stream(EtacsVariantData.EtacsVariantData70.values()).map(EtacsVariantData.EtacsVariantData70::getIdx);
            case 71 -> Arrays.stream(EtacsVariantData.EtacsVariantData71.values()).map(EtacsVariantData.EtacsVariantData71::getIdx);
            case 72 -> Arrays.stream(EtacsVariantData.EtacsVariantData72.values()).map(EtacsVariantData.EtacsVariantData72::getIdx);
            case 73 -> Arrays.stream(EtacsVariantData.EtacsVariantData73.values()).map(EtacsVariantData.EtacsVariantData73::getIdx);
            case 74 -> Arrays.stream(EtacsVariantData.EtacsVariantData74.values()).map(EtacsVariantData.EtacsVariantData74::getIdx);
            case 75 -> Arrays.stream(EtacsVariantData.EtacsVariantData75.values()).map(EtacsVariantData.EtacsVariantData75::getIdx);
            case 76 -> Arrays.stream(EtacsVariantData.EtacsVariantData76.values()).map(EtacsVariantData.EtacsVariantData76::getIdx);
            case 77 -> Arrays.stream(EtacsVariantData.EtacsVariantData77.values()).map(EtacsVariantData.EtacsVariantData77::getIdx);
            case 78 -> Arrays.stream(EtacsVariantData.EtacsVariantData78.values()).map(EtacsVariantData.EtacsVariantData78::getIdx);
            case 79 -> Arrays.stream(EtacsVariantData.EtacsVariantData79.values()).map(EtacsVariantData.EtacsVariantData79::getIdx);
            case 80 -> Arrays.stream(EtacsVariantData.EtacsVariantData80.values()).map(EtacsVariantData.EtacsVariantData80::getIdx);
            case 81 -> Arrays.stream(EtacsVariantData.EtacsVariantData81.values()).map(EtacsVariantData.EtacsVariantData81::getIdx);
            case 82 -> Arrays.stream(EtacsVariantData.EtacsVariantData82.values()).map(EtacsVariantData.EtacsVariantData82::getIdx);
            case 83 -> Arrays.stream(EtacsVariantData.EtacsVariantData83.values()).map(EtacsVariantData.EtacsVariantData83::getIdx);
            case 84 -> Arrays.stream(EtacsVariantData.EtacsVariantData84.values()).map(EtacsVariantData.EtacsVariantData84::getIdx);
            case 85 -> Arrays.stream(EtacsVariantData.EtacsVariantData85.values()).map(EtacsVariantData.EtacsVariantData85::getIdx);
            case 86 -> Arrays.stream(EtacsVariantData.EtacsVariantData86.values()).map(EtacsVariantData.EtacsVariantData86::getIdx);
            case 87 -> Arrays.stream(EtacsVariantData.EtacsVariantData87.values()).map(EtacsVariantData.EtacsVariantData87::getIdx);
            case 88 -> Arrays.stream(EtacsVariantData.EtacsVariantData88.values()).map(EtacsVariantData.EtacsVariantData88::getIdx);
            case 89 -> Arrays.stream(EtacsVariantData.EtacsVariantData89.values()).map(EtacsVariantData.EtacsVariantData89::getIdx);
            case 90 -> Arrays.stream(EtacsVariantData.EtacsVariantData90.values()).map(EtacsVariantData.EtacsVariantData90::getIdx);
            case 91 -> Arrays.stream(EtacsVariantData.EtacsVariantData91.values()).map(EtacsVariantData.EtacsVariantData91::getIdx);
            case 92 -> Arrays.stream(EtacsVariantData.EtacsVariantData92.values()).map(EtacsVariantData.EtacsVariantData92::getIdx);
            case 93 -> Arrays.stream(EtacsVariantData.EtacsVariantData93.values()).map(EtacsVariantData.EtacsVariantData93::getIdx);
            case 94 -> Arrays.stream(EtacsVariantData.EtacsVariantData94.values()).map(EtacsVariantData.EtacsVariantData94::getIdx);
            case 95 -> Arrays.stream(EtacsVariantData.EtacsVariantData95.values()).map(EtacsVariantData.EtacsVariantData95::getIdx);
            case 96 -> Arrays.stream(EtacsVariantData.EtacsVariantData96.values()).map(EtacsVariantData.EtacsVariantData96::getIdx);
            case 97 -> Arrays.stream(EtacsVariantData.EtacsVariantData97.values()).map(EtacsVariantData.EtacsVariantData97::getIdx);
            case 98 -> Arrays.stream(EtacsVariantData.EtacsVariantData98.values()).map(EtacsVariantData.EtacsVariantData98::getIdx);
            case 99 -> Arrays.stream(EtacsVariantData.EtacsVariantData99.values()).map(EtacsVariantData.EtacsVariantData99::getIdx);
            case 100 -> Arrays.stream(EtacsVariantData.EtacsVariantData100.values()).map(EtacsVariantData.EtacsVariantData100::getIdx);
            case 101 -> Arrays.stream(EtacsVariantData.EtacsVariantData101.values()).map(EtacsVariantData.EtacsVariantData101::getIdx);
            case 102 -> Arrays.stream(EtacsVariantData.EtacsVariantData102.values()).map(EtacsVariantData.EtacsVariantData102::getIdx);
            case 103 -> Arrays.stream(EtacsVariantData.EtacsVariantData103.values()).map(EtacsVariantData.EtacsVariantData103::getIdx);
            case 104 -> Arrays.stream(EtacsVariantData.EtacsVariantData104.values()).map(EtacsVariantData.EtacsVariantData104::getIdx);
            case 105 -> Arrays.stream(EtacsVariantData.EtacsVariantData105.values()).map(EtacsVariantData.EtacsVariantData105::getIdx);
            case 106 -> Arrays.stream(EtacsVariantData.EtacsVariantData106.values()).map(EtacsVariantData.EtacsVariantData106::getIdx);
            case 107 -> Arrays.stream(EtacsVariantData.EtacsVariantData107.values()).map(EtacsVariantData.EtacsVariantData107::getIdx);
            case 108 -> Arrays.stream(EtacsVariantData.EtacsVariantData108.values()).map(EtacsVariantData.EtacsVariantData108::getIdx);
            case 109 -> Arrays.stream(EtacsVariantData.EtacsVariantData109.values()).map(EtacsVariantData.EtacsVariantData109::getIdx);
            case 110 -> Arrays.stream(EtacsVariantData.EtacsVariantData110.values()).map(EtacsVariantData.EtacsVariantData110::getIdx);
            case 111 -> Arrays.stream(EtacsVariantData.EtacsVariantData111.values()).map(EtacsVariantData.EtacsVariantData111::getIdx);
            case 112 -> Arrays.stream(EtacsVariantData.EtacsVariantData112.values()).map(EtacsVariantData.EtacsVariantData112::getIdx);
            case 113 -> Arrays.stream(EtacsVariantData.EtacsVariantData113.values()).map(EtacsVariantData.EtacsVariantData113::getIdx);
            case 114 -> Arrays.stream(EtacsVariantData.EtacsVariantData114.values()).map(EtacsVariantData.EtacsVariantData114::getIdx);
            case 115 -> Arrays.stream(EtacsVariantData.EtacsVariantData115.values()).map(EtacsVariantData.EtacsVariantData115::getIdx);
            case 116 -> Arrays.stream(EtacsVariantData.EtacsVariantData116.values()).map(EtacsVariantData.EtacsVariantData116::getIdx);
            case 117 -> Arrays.stream(EtacsVariantData.EtacsVariantData117.values()).map(EtacsVariantData.EtacsVariantData117::getIdx);
            case 118 -> Arrays.stream(EtacsVariantData.EtacsVariantData118.values()).map(EtacsVariantData.EtacsVariantData118::getIdx);
            case 119 -> Arrays.stream(EtacsVariantData.EtacsVariantData119.values()).map(EtacsVariantData.EtacsVariantData119::getIdx);
            case 120 -> Arrays.stream(EtacsVariantData.EtacsVariantData120.values()).map(EtacsVariantData.EtacsVariantData120::getIdx);
            case 121 -> Arrays.stream(EtacsVariantData.EtacsVariantData121.values()).map(EtacsVariantData.EtacsVariantData121::getIdx);
            case 122 -> Arrays.stream(EtacsVariantData.EtacsVariantData122.values()).map(EtacsVariantData.EtacsVariantData122::getIdx);
            case 123 -> Arrays.stream(EtacsVariantData.EtacsVariantData123.values()).map(EtacsVariantData.EtacsVariantData123::getIdx);
            case 124 -> Arrays.stream(EtacsVariantData.EtacsVariantData124.values()).map(EtacsVariantData.EtacsVariantData124::getIdx);
            case 125 -> Arrays.stream(EtacsVariantData.EtacsVariantData125.values()).map(EtacsVariantData.EtacsVariantData125::getIdx);
            case 126 -> Arrays.stream(EtacsVariantData.EtacsVariantData126.values()).map(EtacsVariantData.EtacsVariantData126::getIdx);
            case 127 -> Arrays.stream(EtacsVariantData.EtacsVariantData127.values()).map(EtacsVariantData.EtacsVariantData127::getIdx);
            case 128 -> Arrays.stream(EtacsVariantData.EtacsVariantData128.values()).map(EtacsVariantData.EtacsVariantData128::getIdx);
            case 129 -> Arrays.stream(EtacsVariantData.EtacsVariantData129.values()).map(EtacsVariantData.EtacsVariantData129::getIdx);
            case 130 -> Arrays.stream(EtacsVariantData.EtacsVariantData130.values()).map(EtacsVariantData.EtacsVariantData130::getIdx);
            case 131 -> Arrays.stream(EtacsVariantData.EtacsVariantData131.values()).map(EtacsVariantData.EtacsVariantData131::getIdx);
            case 132 -> Arrays.stream(EtacsVariantData.EtacsVariantData132.values()).map(EtacsVariantData.EtacsVariantData132::getIdx);
            case 133 -> Arrays.stream(EtacsVariantData.EtacsVariantData133.values()).map(EtacsVariantData.EtacsVariantData133::getIdx);
            case 134 -> Arrays.stream(EtacsVariantData.EtacsVariantData134.values()).map(EtacsVariantData.EtacsVariantData134::getIdx);
            case 135 -> Arrays.stream(EtacsVariantData.EtacsVariantData135.values()).map(EtacsVariantData.EtacsVariantData135::getIdx);
            case 136 -> Arrays.stream(EtacsVariantData.EtacsVariantData136.values()).map(EtacsVariantData.EtacsVariantData136::getIdx);
            case 137 -> Arrays.stream(EtacsVariantData.EtacsVariantData137.values()).map(EtacsVariantData.EtacsVariantData137::getIdx);
            case 138 -> Arrays.stream(EtacsVariantData.EtacsVariantData138.values()).map(EtacsVariantData.EtacsVariantData138::getIdx);
            case 139 -> Arrays.stream(EtacsVariantData.EtacsVariantData139.values()).map(EtacsVariantData.EtacsVariantData139::getIdx);
            case 140 -> Arrays.stream(EtacsVariantData.EtacsVariantData140.values()).map(EtacsVariantData.EtacsVariantData140::getIdx);
            case 141 -> Arrays.stream(EtacsVariantData.EtacsVariantData141.values()).map(EtacsVariantData.EtacsVariantData141::getIdx);
            case 142 -> Arrays.stream(EtacsVariantData.EtacsVariantData142.values()).map(EtacsVariantData.EtacsVariantData142::getIdx);
            case 143 -> Arrays.stream(EtacsVariantData.EtacsVariantData143.values()).map(EtacsVariantData.EtacsVariantData143::getIdx);
            case 144 -> Arrays.stream(EtacsVariantData.EtacsVariantData144.values()).map(EtacsVariantData.EtacsVariantData144::getIdx);
            case 145 -> Arrays.stream(EtacsVariantData.EtacsVariantData145.values()).map(EtacsVariantData.EtacsVariantData145::getIdx);
            case 146 -> Arrays.stream(EtacsVariantData.EtacsVariantData146.values()).map(EtacsVariantData.EtacsVariantData146::getIdx);
            case 147 -> Arrays.stream(EtacsVariantData.EtacsVariantData147.values()).map(EtacsVariantData.EtacsVariantData147::getIdx);
            case 148 -> Arrays.stream(EtacsVariantData.EtacsVariantData148.values()).map(EtacsVariantData.EtacsVariantData148::getIdx);
            case 149 -> Arrays.stream(EtacsVariantData.EtacsVariantData149.values()).map(EtacsVariantData.EtacsVariantData149::getIdx);
            case 150 -> Arrays.stream(EtacsVariantData.EtacsVariantData150.values()).map(EtacsVariantData.EtacsVariantData150::getIdx);
            case 151 -> Arrays.stream(EtacsVariantData.EtacsVariantData151.values()).map(EtacsVariantData.EtacsVariantData151::getIdx);
            case 152 -> Arrays.stream(EtacsVariantData.EtacsVariantData152.values()).map(EtacsVariantData.EtacsVariantData152::getIdx);
            case 153 -> Arrays.stream(EtacsVariantData.EtacsVariantData153.values()).map(EtacsVariantData.EtacsVariantData153::getIdx);
            case 154 -> Arrays.stream(EtacsVariantData.EtacsVariantData154.values()).map(EtacsVariantData.EtacsVariantData154::getIdx);
            case 155 -> Arrays.stream(EtacsVariantData.EtacsVariantData155.values()).map(EtacsVariantData.EtacsVariantData155::getIdx);
            case 156 -> Arrays.stream(EtacsVariantData.EtacsVariantData156.values()).map(EtacsVariantData.EtacsVariantData156::getIdx);
            case 157 -> Arrays.stream(EtacsVariantData.EtacsVariantData157.values()).map(EtacsVariantData.EtacsVariantData157::getIdx);
            case 158 -> Arrays.stream(EtacsVariantData.EtacsVariantData158.values()).map(EtacsVariantData.EtacsVariantData158::getIdx);
            case 159 -> Arrays.stream(EtacsVariantData.EtacsVariantData159.values()).map(EtacsVariantData.EtacsVariantData159::getIdx);
            case 160 -> Arrays.stream(EtacsVariantData.EtacsVariantData160.values()).map(EtacsVariantData.EtacsVariantData160::getIdx);
            case 161 -> Arrays.stream(EtacsVariantData.EtacsVariantData161.values()).map(EtacsVariantData.EtacsVariantData161::getIdx);
            case 162 -> Arrays.stream(EtacsVariantData.EtacsVariantData162.values()).map(EtacsVariantData.EtacsVariantData162::getIdx);
            case 163 -> Arrays.stream(EtacsVariantData.EtacsVariantData163.values()).map(EtacsVariantData.EtacsVariantData163::getIdx);
            case 164 -> Arrays.stream(EtacsVariantData.EtacsVariantData164.values()).map(EtacsVariantData.EtacsVariantData164::getIdx);
            case 165 -> Arrays.stream(EtacsVariantData.EtacsVariantData165.values()).map(EtacsVariantData.EtacsVariantData165::getIdx);
            case 166 -> Arrays.stream(EtacsVariantData.EtacsVariantData166.values()).map(EtacsVariantData.EtacsVariantData166::getIdx);
            case 167 -> Arrays.stream(EtacsVariantData.EtacsVariantData167.values()).map(EtacsVariantData.EtacsVariantData167::getIdx);
            case 168 -> Arrays.stream(EtacsVariantData.EtacsVariantData168.values()).map(EtacsVariantData.EtacsVariantData168::getIdx);
            case 169 -> Arrays.stream(EtacsVariantData.EtacsVariantData169.values()).map(EtacsVariantData.EtacsVariantData169::getIdx);
            case 170 -> Arrays.stream(EtacsVariantData.EtacsVariantData170.values()).map(EtacsVariantData.EtacsVariantData170::getIdx);
            case 171 -> Arrays.stream(EtacsVariantData.EtacsVariantData171.values()).map(EtacsVariantData.EtacsVariantData171::getIdx);
            case 172 -> Arrays.stream(EtacsVariantData.EtacsVariantData172.values()).map(EtacsVariantData.EtacsVariantData172::getIdx);
            case 173 -> Arrays.stream(EtacsVariantData.EtacsVariantData173.values()).map(EtacsVariantData.EtacsVariantData173::getIdx);
            case 174 -> Arrays.stream(EtacsVariantData.EtacsVariantData174.values()).map(EtacsVariantData.EtacsVariantData174::getIdx);
            case 175 -> Arrays.stream(EtacsVariantData.EtacsVariantData175.values()).map(EtacsVariantData.EtacsVariantData175::getIdx);
            case 176 -> Arrays.stream(EtacsVariantData.EtacsVariantData176.values()).map(EtacsVariantData.EtacsVariantData176::getIdx);
            case 177 -> Arrays.stream(EtacsVariantData.EtacsVariantData177.values()).map(EtacsVariantData.EtacsVariantData177::getIdx);
            case 178 -> Arrays.stream(EtacsVariantData.EtacsVariantData178.values()).map(EtacsVariantData.EtacsVariantData178::getIdx);
            case 179 -> Arrays.stream(EtacsVariantData.EtacsVariantData179.values()).map(EtacsVariantData.EtacsVariantData179::getIdx);
            case 180 -> Arrays.stream(EtacsVariantData.EtacsVariantData180.values()).map(EtacsVariantData.EtacsVariantData180::getIdx);
            case 205 -> Arrays.stream(EtacsVariantData.EtacsVariantData205.values()).map(EtacsVariantData.EtacsVariantData205::getIdx);
            default -> Stream.of(-1);
        };

        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isExtended() {
        return extended;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getSummary() {
        return summary;
    }
}
