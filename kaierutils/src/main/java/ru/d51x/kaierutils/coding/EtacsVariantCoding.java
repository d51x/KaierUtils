package ru.d51x.kaierutils.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.d51x.kaierutils.coding.datatypes.variant.AccPowerAutoCut;
import ru.d51x.kaierutils.coding.datatypes.variant.AfsAclType;
import ru.d51x.kaierutils.coding.datatypes.variant.AutoFoldMirror;
import ru.d51x.kaierutils.coding.datatypes.variant.AutomaticDoorLockUnlock;
import ru.d51x.kaierutils.coding.datatypes.variant.AutomaticLightThreshold;
import ru.d51x.kaierutils.coding.datatypes.variant.CompressorType;
import ru.d51x.kaierutils.coding.datatypes.variant.CoolantTempGaugeThreshold;
import ru.d51x.kaierutils.coding.datatypes.variant.CoolingFanControl;
import ru.d51x.kaierutils.coding.datatypes.variant.DeadLockOperationCustomize;
import ru.d51x.kaierutils.coding.datatypes.variant.DisplayOpeningType;
import ru.d51x.kaierutils.coding.datatypes.variant.DoorLockSystem;
import ru.d51x.kaierutils.coding.datatypes.variant.DoorUnlockByIGCustomize;
import ru.d51x.kaierutils.coding.datatypes.variant.DrlFunction;
import ru.d51x.kaierutils.coding.datatypes.variant.DrlType;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsChassisTypeForAsc;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsDestinationRegion;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsEnginePower;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsEngineType;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsFinalDrive;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsHandleSide;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsModelYear;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsTransferType;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsTransmission;
import ru.d51x.kaierutils.coding.datatypes.variant.EtacsVehicleLine;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_Disable_Enable;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_Disable_Enable_defD_E;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_Enable_Disable;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_NotAvailable_Available;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_NotPresent2_Present;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_NotPresent_Present;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_NotPresent_PresentABC;
import ru.d51x.kaierutils.coding.datatypes.variant.Etacs_Present_NotPresent;
import ru.d51x.kaierutils.coding.datatypes.variant.FronFogLampMode;
import ru.d51x.kaierutils.coding.datatypes.variant.FrontDiff;
import ru.d51x.kaierutils.coding.datatypes.variant.FrontWiperControl;
import ru.d51x.kaierutils.coding.datatypes.variant.FrostWarningThreshold;
import ru.d51x.kaierutils.coding.datatypes.variant.FuelConsumptionScale;
import ru.d51x.kaierutils.coding.datatypes.variant.FuelTankSize;
import ru.d51x.kaierutils.coding.datatypes.variant.FuelTankType;
import ru.d51x.kaierutils.coding.datatypes.variant.FuelUnits;
import ru.d51x.kaierutils.coding.datatypes.variant.GateTrunkLamp;
import ru.d51x.kaierutils.coding.datatypes.variant.GateTrunkType;
import ru.d51x.kaierutils.coding.datatypes.variant.HeadLampAutoCutMode;
import ru.d51x.kaierutils.coding.datatypes.variant.HeadLampLevelingSystemType;
import ru.d51x.kaierutils.coding.datatypes.variant.HeadLampType;
import ru.d51x.kaierutils.coding.datatypes.variant.HeadLampWasher;
import ru.d51x.kaierutils.coding.datatypes.variant.HeadLightLevelingType;
import ru.d51x.kaierutils.coding.datatypes.variant.HornType;
import ru.d51x.kaierutils.coding.datatypes.variant.IgKeyIllumination;
import ru.d51x.kaierutils.coding.datatypes.variant.ImmobilizerType;
import ru.d51x.kaierutils.coding.datatypes.variant.KeyReminderUnlock;
import ru.d51x.kaierutils.coding.datatypes.variant.KosDoorEntryType;
import ru.d51x.kaierutils.coding.datatypes.variant.PowerWindowType;
import ru.d51x.kaierutils.coding.datatypes.variant.RadioFrequency;
import ru.d51x.kaierutils.coding.datatypes.variant.RearDiff;
import ru.d51x.kaierutils.coding.datatypes.variant.RearFogLamp;
import ru.d51x.kaierutils.coding.datatypes.variant.RearWiperMode;
import ru.d51x.kaierutils.coding.datatypes.variant.RearWiperReverseCustomize;
import ru.d51x.kaierutils.coding.datatypes.variant.RlsOverWipeType;
import ru.d51x.kaierutils.coding.datatypes.variant.RlsWsType;
import ru.d51x.kaierutils.coding.datatypes.variant.RoomLampControl;
import ru.d51x.kaierutils.coding.datatypes.variant.RoomLampDelayTimer;
import ru.d51x.kaierutils.coding.datatypes.variant.SeatBeltReminderControlType;
import ru.d51x.kaierutils.coding.datatypes.variant.SeatBeltReminderIndicator;
import ru.d51x.kaierutils.coding.datatypes.variant.SeatBeltReminderLogic;
import ru.d51x.kaierutils.coding.datatypes.variant.SeatMaterial;
import ru.d51x.kaierutils.coding.datatypes.variant.SecurityAlarmFunction;
import ru.d51x.kaierutils.coding.datatypes.variant.SecurityAlarmMode;
import ru.d51x.kaierutils.coding.datatypes.variant.SecuritySensorGainSetting;
import ru.d51x.kaierutils.coding.datatypes.variant.ServiceReminderScheduleTable;
import ru.d51x.kaierutils.coding.datatypes.variant.SpeakersNumber;
import ru.d51x.kaierutils.coding.datatypes.variant.SpeedMeterScale;
import ru.d51x.kaierutils.coding.datatypes.variant.SunRoofType;
import ru.d51x.kaierutils.coding.datatypes.variant.SwitchType4wd;
import ru.d51x.kaierutils.coding.datatypes.variant.TemperatureUnits;
import ru.d51x.kaierutils.coding.datatypes.variant.TireCircumference;
import ru.d51x.kaierutils.coding.datatypes.variant.TpmsInformation;
import ru.d51x.kaierutils.coding.datatypes.variant.TurnSignalBulb;
import ru.d51x.kaierutils.coding.datatypes.variant.VehicleLanguage;
import ru.d51x.kaierutils.coding.datatypes.variant.WelcomeLight;
import ru.d51x.kaierutils.coding.datatypes.variant.hornChirpType;

public enum EtacsVariantCoding {

    ETACS_VARIANT_001(1, 0, 7, 0, 0b01111111, false, true, "vehicle_line", "Vehicle line", "Вид автомобиля"),
    ETACS_VARIANT_002(2, 1, 7, 0, 0b01111111, false, true,"model_year", "Model year", "Модельный год"),
    ETACS_VARIANT_003(3, 1, 1, 7, 0b10000000, false, true, "sst_oil_cooling_fan", "SST oil cooling fan", ""),
    ETACS_VARIANT_004(4, 2, 4, 0, 0b00001111, false, true,"destination", "Destination", "Страна поставки"),
    ETACS_VARIANT_005(5, 2, 4, 4, 0b11110000, false, true,"transmission", "Transmission", "Коробка передач"),
    ETACS_VARIANT_006(6, 6, 8, 0, 0b11111111, false, true,"engine_type", "Engine type", "Тип двигателя"),
    ETACS_VARIANT_007(7, 4, 2, 0, 0b00000011, false, true,"engine_power", "Engine power", ""),
    ETACS_VARIANT_008(8, 4, 1, 2, 0b00000100, false, true,"handle_side", "Handle side", "Расположение органов управления"),
    ETACS_VARIANT_009(9, 4, 4, 3, 0b01111000, false, true,"chassis_type_for_asc", "Chassis Type for A.S.C.", "Тип шасси для системы Anti Slip Control"),
    ETACS_VARIANT_010(10, 4, 1, 7, 0b10000000, false, true, "oss", "OSS Module", "Модуль обработки команд со штатной кнопки START/STOP ENGINE"),
    ETACS_VARIANT_011(11, 5, 3, 0, 0b00000111, false, true,"final_drive", "Final Drive", "Тип привода (перед/зад/полный)"),
    ETACS_VARIANT_012(12, 5, 4, 3, 0b01111000, false, true,"transfer", "Transfer", "Тип раздатки"),
    ETACS_VARIANT_013(13, 5, 1, 7, 0b10000000, true, false, "ig_off_delay_control", "IG off delay control", "Управление задержкой отключения двигателя при выключении зажигания (примерно 1 сек)"),
    ETACS_VARIANT_014(14, 6, 2, 4, 0b00110000, true, false,"dead_lock_operation_customize", "Dead Lock Operation Customize", "Блокировка кнопки открытия на водительской двери"),
    ETACS_VARIANT_015(15, 6, 2, 6, 0b11000000, true, false,"after_wipe_customize", "After wipe customize", "Настройка после очистки стекол"),
    ETACS_VARIANT_016_1(16000, 7, 8, 0, 0b11111111, true, true,"tire_circumference", "Tire circumference", "Длина окружности шин, влияет на показания спидометра"),
    ETACS_VARIANT_016_2(16001, 8, 8, 0, 0b11111111, true, true,"tire_circumference", "Tire circumference", "Длина окружности шин, влияет на показания спидометра"),
    ETACS_VARIANT_017(17, 9, 8, 0, 0b11111111, false, true,"fuel_tank", "Fuel tank", "Емкость топливного бака"),
    ETACS_VARIANT_018(18, 10, 3, 0, 0b00000111, true, false,"drl_type", "DRL type", "Тип системы DRL"),
    ETACS_VARIANT_019(19, 10, 2, 3, 0b00011000, true, true,"smart_entry_system", "Smart entry system", "Интеллектуальная система доступа"),
    ETACS_VARIANT_020(20, 10, 1, 5, 0b00100000, false, true, "tpms", "TPMS", "Наличие блока TPMS"),
    ETACS_VARIANT_021(21, 10, 1, 6, 0b01000000, true, false, "rke_keyless_entry", "RKE Keyless entry <span color='red'>**</span>", "<red>**</red> Система доступа без использования ключа"),
    ETACS_VARIANT_022(22, 10, 1, 7, 0b10000000, true, false, "airbag_auto_hazard", "Airbag Auto Hazard", "Опастность автоматического срабатывания подушек безопасности"),
    ETACS_VARIANT_023(23, 11, 2, 0, 0b00000011, false, true,"immobilizer", "Immobilizer", "Блока иммобилайзера"),
    ETACS_VARIANT_024_1(24000, 11, 1, 2, 0b00000100, true, false, "cruise_control", "Cruise Control", "Блок круиз-контроля"),
    ETACS_VARIANT_024(24, 11, 1, 3, 0b00001000, true, false, "corner_sensor", "Corner sensor", "Блок парктроников (CAN) с отображением на ЦВЕТНОЙ приборке"),
    ETACS_VARIANT_025(25, 11, 1, 4, 0b00010000, true, true, "head_lamp_auto_leveling_device", "Head lamp auto leveling device", "Поддержка заданного направления света фар"),
    ETACS_VARIANT_026(26, 11, 1, 5, 0b00100000, false, true, "oil_level_warning", "Oil level warning", "Индикация предупреждения низкого уровня масла"),
    ETACS_VARIANT_027(27, 11, 1, 6, 0b01000000, false, true, "fuel_filter_warning", "Fuel filter warning", "? Индикация о наличии воды в сепараторе для дизелей ? низкий уровень омывателя ?"),
    ETACS_VARIANT_028(28, 11, 1, 7, 0b10000000, false, true,"speed_meter_scale", "Speed meter scale", "Единицы измерения скорости"),
    ETACS_VARIANT_029(29, 12, 1, 0, 0b0000000, true,false, "idle_neutral_control", "Idle neutral control", "Контроль нейтрали для 6AT ??"),
    ETACS_VARIANT_030(30, 12, 1, 1, 0b00000010, true, false, "security_alarm_sensor", "Security alarm sensor", "Ультразвуковые датчики объема штатной сигнализации"),
    ETACS_VARIANT_031(31, 12, 2, 2, 0b00001100, false, true,"tm_oil_cooler", "T/M oil cooler", "Охладитель масла КПП"),
    ETACS_VARIANT_032(32, 12, 1, 4, 0b00010000, true, false, "remote_light_on", "Remote Light ON", "Дистанционное управление освещением ?"),
    ETACS_VARIANT_033(33, 12, 1, 6, 0b01000000, false, true, "side_air_bag", "Side air bag", "Боковые подушки безопастности"),
    ETACS_VARIANT_034(34, 12, 1, 7, 0b10000000, true, false,"acc_power_auto_cut", "ACC power auto cut", "Автоотключение ACC"),
    ETACS_VARIANT_035(35, 13, 4, 0, 0b00001111, false, true,"number_of_speaker", "Number of speaker **", "** Количество динамиков"),
    ETACS_VARIANT_036(36, 13, 1, 4, 0b00010000, false, true,"seat_material", "Seat material", "Материал обшивки сидений"),
    ETACS_VARIANT_037(37, 13, 3, 5, 0b11100000, true, false,"auto_light_control", "Auto Light control", "Настройка уровня автоматического влючения света фар"),
    ETACS_VARIANT_038(38, 14, 2, 0, 0b00000011, false, true,"front_differential", "Front differential", "Тип переднего дифференциала"),
    ETACS_VARIANT_039(39, 14, 2, 4, 0b00110000, false, true,"rear_differential", "Rear differential", "Тип заднего дифференциала"),
    ETACS_VARIANT_040(40, 15, 3, 0, 0b00000111, true, false,"power_window_type", "Power window type", "Тип подачи питания для стеклоподъемников"),
    ETACS_VARIANT_041(41, 15, 3, 4, 0b01110000, true, false,"sun_roof_type", "Sun roof type", "Тип потолочного люка"),
    ETACS_VARIANT_042(42, 16, 1, 0, 0b00000001, false, true, "wcm", "WCM / KOS Module", "Блок Wireless Control module (KOS)"),
    ETACS_VARIANT_043(43, 16, 1, 1, 0b00000010, false, true, "ocm", "OCM - Occupant Classificate module", "Модуль определения наличия пассажира"),
    ETACS_VARIANT_044(44, 16, 1, 2, 0b00000100, false, true, "orc", "ORC - Occupant Restraint Controller", "Контроллер систем безопасности пассажиров"),
    ETACS_VARIANT_045(45, 16, 1, 3, 0b00001000, false, true, "ac", "A/C Module", "Модуль климат-контроля"),
    ETACS_VARIANT_046(46, 16, 1, 4, 0b00010000, false, true, "audio", "AUDIO **", "** Оригинальная аудиосистема"),
    ETACS_VARIANT_047(47, 16, 1, 5, 0b00100000, false, true, "and", "AVN/AND Module", "Аудиовизуальное головное устройство с навигацией"),
    ETACS_VARIANT_048(48, 16, 2, 6, 0b11000000, true, false,"siren_answer", "Siren answer", "Подтверждение сиреной при закрытии"),
    ETACS_VARIANT_049(49, 17, 1, 0, 0b00000001, true, false, "security_alarm_siren", "Security alarm siren", "Cирена охранной системы"),
    ETACS_VARIANT_050(50, 17, 1, 1, 0b00000010, false, true, "camera", "CAMERA", "Камера заднего вида"),
    ETACS_VARIANT_051(51, 17, 1, 2, 0b00000100, true, false, "corner_sensor_control_unit", "Corner sensor control unit", "Блок управления парктрониками"),
    ETACS_VARIANT_052(52, 17, 1, 3, 0b00001000, false, true, "electric_slide_door_left", "Electric Slide door (Left)", "Левая электроприводная сдвижная дверь"),
    ETACS_VARIANT_053(53, 17, 1, 4, 0b00010000, false, true, "electric_slide_door_right", "Electric Slide door (Right)", "Правая электроприводная сдвижная дверь"),
    ETACS_VARIANT_054(54, 17, 1, 5, 0b00100000, false, true, "etg", "ETG - Electronic Trunk/Gate", "Электропривод багажника"),
    ETACS_VARIANT_055(55, 17, 1, 6, 0b01000000, true, false, "ess_ecu", "ESS ECU", "Модуль аварийной сигнализация при экстренном торможении"),
    ETACS_VARIANT_056(56, 17, 1, 7, 0b10000000, false, true, "hfm", "HFM - HandsFree Module", "Модуль громкой связи"),
    ETACS_VARIANT_057(57, 18, 2, 0, 0b00000011, true, false,"intelligent_comfort_washer_custom", "Intelligent/Comfort washer custom", "Настройки функции омывателя"),
    ETACS_VARIANT_058(58, 18, 2, 2, 0b00001100, true, false,"head_lamp_leveling_system_type", "Head Lamp Leveling system type", "Тип системы поддержания заданного направления света фар"),
    ETACS_VARIANT_059(59, 18, 1, 4, 0b00010000, true, false,"rear_wiper_mode", "Rear wiper mode", "Режим работы заднего стеклоочистителя"),
    ETACS_VARIANT_060(60, 18, 1, 5, 0b00100000, true, false,"kos_door_entry_type", "KOS door entry type", "Тип системы доступа без использования ключа"),
    ETACS_VARIANT_061(61, 18, 2, 6, 0b11000000, true, false,"rear_wiper_by_reverse_customize", "Rear wiper by reverse customize", "Задний стеклоочиститель с функцией включения при заднем ходе"),
    ETACS_VARIANT_062(62, 19, 1, 0, 0b00000001, false, true, "abs", "ABS", "Система ABS"),
    ETACS_VARIANT_063(63, 19, 1, 1, 0b00000010, false, true, "asc", "ASC", "Система ASC"),
    ETACS_VARIANT_064(64, 19, 1, 2, 0b00000100, true, false,"auto_fold_mirror", "Auto fold mirror", "Складывающиеся зеркала с электроприводом"),
    ETACS_VARIANT_065(65, 19, 1, 3, 0b00001000, false, true, "sas", "SAS wheel angle sensor", "Датчик угла поворота руля"),
    ETACS_VARIANT_066(66, 19, 1, 4, 0b00010000, false, true, "awc_4wd", "4WD / AWC", ""),
    ETACS_VARIANT_067(67, 19, 1, 5, 0b00100000, false, true, "tcm", "TCM", ""),
    ETACS_VARIANT_068(68, 19, 1, 6, 0b01000000, false, true, "actv_stb", "ACTV_STB", ""),
    ETACS_VARIANT_069(69, 19, 1, 7, 0b10000000, true, false,"door_unlock_by_ig_lock_customize", "Door unlock by IG lock customize", "Отпирание дверей с соответствии с настройкой блокировки выключателя зажигания"),
    ETACS_VARIANT_070(70, 20, 1, 0, 0b00000001, false, true, "shift_lock", "Shift lock", "Блокировка переключения"),
    ETACS_VARIANT_071(71, 20, 1, 1, 0b00000010, false, true, "eps", "EPS (Electric Power Steering)", "Электро-усилитель руля"),
    ETACS_VARIANT_072(72, 20, 1, 2, 0b00000100, false, true, "acdayc", "ACD/AYC System", "Система полного привода AYC - ACD"),
    ETACS_VARIANT_073(73, 20, 2, 3, 0b00011000, true, false,"сoming_home_light_customize", "Coming home light customize", "Включение света фар на 30 сек при ключе в замке в положении Lock"),
    ETACS_VARIANT_074(74, 20, 2, 5, 0b01100000, true, false,"welcome_light_customize", "Welcome light customize", "Настройка освещения при отпирании дверей брелоком"),
    ETACS_VARIANT_075(75, 20, 1, 7, 0b10000000, true, false, "indirect_lamp", "Indirect lamp", "Подсветка"),
    ETACS_VARIANT_076(76, 21, 1, 0, 0b00000001, false, true, "power_window_dr", "Power window Dr", "Электрический стеклоподъемник двери водителя"),
    ETACS_VARIANT_077(77, 21, 1, 1, 0b00000010, false, true, "power_window_as", "Power window As", "Электрический стеклоподъемник двери пассажира"),
    ETACS_VARIANT_078(78, 21, 1, 2, 0b00000100, false, true, "power_window_rr", "Power window RR", "Электрический стеклоподъемник задней правой двери"),
    ETACS_VARIANT_079(79, 21, 1, 3, 0b00001000, false, true, "power_window_rl", "Power window RL", "Электрический стеклоподъемник задней левой двери"),
    ETACS_VARIANT_080(80, 21, 1, 4, 0b00010000, true, false, "ess_by_stop_lamp", "ESS by stop lamp", "Cигнализация ESS путем мигания стоп-сигнала"),
    ETACS_VARIANT_081(81, 21, 1, 5, 0b00100000, false, true, "sun_roof", "Sun roof", "Наличие потолочного люка"),
    ETACS_VARIANT_082(82, 21, 1, 6, 0b01000000, true, false, "rain_light_sensor", "Rain Light Sensor (RLS) **", "Датчик дождя"),
    ETACS_VARIANT_205(205, 21, 1, 7, 0b10000000, true, false, "washer_function_improvement", "Washer function improvement", "При коротком нажатии рычага 5 брызгов на стекло"),
    ETACS_VARIANT_083(83, 22, 2, 0, 0b00000011, true, false,"ig_key_illumination", "IG key illumination", "Подсветка выключателя зажигания"),
    ETACS_VARIANT_084(84, 22, 2, 2, 0b00001100, true, false,"turn_signal_bulb", "Turn signal bulb", "Тип ламп указателей поворота"),
    ETACS_VARIANT_085(85, 22, 1, 4, 0b00010000, true, false, "rear_wiper", "Rear wiper", "Задний стеклоочиститель"),
    ETACS_VARIANT_086(86, 22, 1, 5, 0b00100000, true, false, "fold_mirror", "Fold mirror", "Складываемые заркала"),
    ETACS_VARIANT_087(87, 22, 1, 6, 0b01000000, true, false,"head_lamp", "Head lamp", "Тип блока передних фар"),
    ETACS_VARIANT_088(88, 22, 1, 7, 0b10000000, true, false, "kos_function_customize_by_disp", "KOS function customize by Disp", "Отображение функций кастомизации на дисплее MMCS"),
    ETACS_VARIANT_089(89, 23, 4, 0, 0b00001111, true, false,"head_lamp_washer", "Head lamp washer", "Тип омывателя передних фар"),
    ETACS_VARIANT_090(90, 23, 1, 4, 0b00010000, true, false,"front_fog_lamp_mode", "Front fog lamp mode", "Режим работы передних противотуманных фар"),
    ETACS_VARIANT_091(91, 23, 1, 5, 0b00100000, true, false, "front_fog_lamp", "Front fog lamp **", "** Передние противотуманные фары"),
    ETACS_VARIANT_092(92, 23, 2, 6, 0b11000000, true, false,"rear_fog_lamp", "Rear fog lamp **", "** Задние противотуманные фары"),
    ETACS_VARIANT_093(93, 24, 1, 0, 0b00000001, true, false,"room_lamp_delay_timer_door_hl", "Room lamp delay timer/door & H/L", "Таймер задержки освещения/двери и высокая/низкая яркость"),
    ETACS_VARIANT_094(94, 24, 2, 1, 0b00000110, true, false,"room_lamp_by_hl", "Room lamp by H/L", "Режим яркости освещения салона (высокая/низкая яркость )"),
    ETACS_VARIANT_095(95, 24, 2, 3, 0b00011000, true, false,"gate_trunk_lamp", "Gate/Trunk lamp", "Плафон освещения задней двери/багажного отделения"),
    ETACS_VARIANT_096(96, 24, 2, 5, 0b01100000, true, false,"head_lamp_auto_cut_mode", "Head lamp auto cut mode", "Режим автоматического переключения света фар"),
    ETACS_VARIANT_097(97, 24, 1, 7, 0b10000000, true, false, "head_lamp_auto_cut", "Head lamp auto cut", "Автоматическое переключение света фар"),
    ETACS_VARIANT_098(98, 25, 3, 0, 0b00000111, true, false,"door_lock_system", "Door lock system", "Система блокировки дверей"),
    ETACS_VARIANT_099(99, 25, 2, 3, 0b00011000, true, false,"auto_door_lock_unlock", "Auto door lock/unlock", "Автоматическая блокировка / разюлокировка центрального замка"),
    ETACS_VARIANT_100(100, 25, 2, 5, 0b01100000, true, false,"key_reminder_unlock", "Key reminder unlock", "Предупреждение о ключе в замке зажигания при открывании двери"),
    ETACS_VARIANT_101(101, 25, 1, 7, 0b10000000, true, false,"horn_type", "Horn type **", "** Тип звукового сигнала"),
    ETACS_VARIANT_102(102, 26, 2, 0, 0b00000011, true, false, "gate_trunk_opener_mode", "Gate/trunk opener mode", "Режим работы электропривода двери багажного отделения"),
    ETACS_VARIANT_103(103, 26, 1, 2, 0b00000100, true, false,"cooling_fan", "Cooling fan", "Тип управления вентилятором системы охлаждения двигателя"),
    ETACS_VARIANT_104(104, 26, 2, 3, 0b00011000, true, false,"security_alarm_mode", "Security alarm mode", "Режим работы звуковой сигнализации охранной системы"),
    ETACS_VARIANT_105(105, 26, 2, 5, 0b01100000, true, false,"security_alarm_function", "Security alarm function", "Функция звуковой сигнализации охранной системы"),
    ETACS_VARIANT_106(106, 26, 1, 7, 0b10000000, true, false, "pre_alarm", "Pre-alarm", "Предварительный звуковой сигнал"),
    ETACS_VARIANT_107(107, 27, 1, 0, 0b00000001, true, false, "multi_mode_rke", "Multi mode RKE (remote key entry)", "Многорежимный RKE (remote key entry)"),
    ETACS_VARIANT_108(108, 27, 1, 1, 0b00000010, true, false,"gate_trunk", "Gate/Trunk", "Тип двери багажного отделения"),
    ETACS_VARIANT_109(109, 27, 2, 2, 0b00001100, true, false,"manner_switch", "Manner Switch", "Переключатель режима управления"),
    ETACS_VARIANT_110(110, 27, 2, 4, 0b00110000, true, false,"remote_engine_starter", "Remote engine starter **", "** Возможность дистанционного запуска двигателя"),
    ETACS_VARIANT_111(111, 27, 1, 6, 0b01000000, true, false, "panic_alarm", "Panic Alarm **", "** Предупредительная сигнализация"),
    ETACS_VARIANT_112(112, 27, 1, 7, 0b10000000, false, true, "right_side_view_camera", "Right side view camera", "Боковая правая камера"),
    ETACS_VARIANT_113(113, 28, 2, 0, 0b00000011, true, false,"front_wiper", "Front wiper **", "** Передний стеклоочиститель"),
    ETACS_VARIANT_114(114, 28, 2, 2, 0b00001100, true, false,"comfort_flasher_type", "Comfort flasher type", "Кратковременное касание к включению поворотника вызывает моргание 3 раза"),
    ETACS_VARIANT_115(115, 28, 1, 4, 0b00010000, true, false, "room_lamp_center_switch", "Room lamp center switch", "Центральный переключатель освещения салона"),
    ETACS_VARIANT_116(116, 28, 1, 5, 0b00100000, true, false, "wiper_washer_check_valve", "Wiper washer check valve", ""),
    ETACS_VARIANT_117(117, 28, 2, 6, 0b11000000, false, true,"audio_sradio_type", "AUDIO/S.RADIO type", ""),
    ETACS_VARIANT_118(118, 29, 3, 0, 0b00000111, false, true,"hl_leveling_type", "H/L leveling type", "Тип устройства поддержания направления света фар"),
    ETACS_VARIANT_119(119, 29, 3, 3, 0b00111000, false, true,"afs_acl_type", "AFS/ACL type", "Тип системы AFS (Adaptive Frontlight System)/ACL (Active Cornering Light)"),
    ETACS_VARIANT_120(120, 29, 1, 6, 0b01000000, true, false, "ess_by_turn_lamp", "ESS by turn lamp", "Cигнализация ESS путем включения указателей поворотов"),
    ETACS_VARIANT_121(121, 30, 2, 0, 0b00000011, false, true,"compressor_type", "Compressor type **", "** Тип компрессора"),
    ETACS_VARIANT_122(122, 30, 1, 3, 0b00001000, false, true,"temperature_type", "Temperature type", "Единицы измерения температуры"),
    ETACS_VARIANT_123(123, 30, 1, 4, 0b00010000, false, true, "rear_view_camera", "Rear view camera", "Камера заднего вида"),
    ETACS_VARIANT_124(124, 30, 1, 5, 0b00100000, false, true, "nose_view_camera", "Nose view camera", "Камера переднего вида"),
    ETACS_VARIANT_125(125, 30, 1, 6, 0b01000000, false, true, "left_side_view_camera", "Left side view camera", "Боковая левая камера"),
    ETACS_VARIANT_126(126, 30, 1, 7, 0b10000000, true, false, "average_speed", "Average speed", "Отображение средней скорости"),
    ETACS_VARIANT_127(127, 31, 4, 0, 0b00001111, false, true,"vehicle_language_status", "Vehicle language status", "Язык приборной панели"),
    ETACS_VARIANT_128(128, 31, 2, 4, 0b00110000, false, true,"fuel_amount", "Fuel amount", "Единицы измерения объема топлива"),
    ETACS_VARIANT_129(129, 31, 2, 6, 0b11000000, true, false,"fuel_consumption_scale", "Fuel consumption scale", "Единицы отображения расхода топлива"),
    ETACS_VARIANT_130(130, 32, 1, 0, 0b00000001, true, false,"seat_belt_reminder_logic", "Seat belt reminder logic", "Логика напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_131(131, 32, 1, 1, 0b00000010, true, false,"coolant_temp_gauge_threshold", "Coolant temp gauge threshold", "Предел температуры охлаждающей жидкости по указателю"),
    ETACS_VARIANT_132(132, 32, 3, 2, 0b00011100, true, false,"frost_warning_threshold", "Frost warning threshold", "Граница индикации опасности появления гололеда"),
    ETACS_VARIANT_133(133, 32, 1, 5, 0b00100000, true, false, "distance_to_empty", "Distance to empty", "Отображение запаса хода по топливу"),
    ETACS_VARIANT_134(134, 32, 1, 6, 0b01000000, true, false, "average_fuel_consumption", "Average fuel consumption", "Отображение среднего расход топлива"),
    ETACS_VARIANT_135(135, 32, 1, 7, 0b10000000, true, false, "instant_fuel_consumption", "Instant fuel consumption", "Отображение мгновенного расход топлива"),
    ETACS_VARIANT_136(136, 33, 1, 0, 0b00000001, true, false, "time_travelled", "Time travelled", "Отображение времени поездки"),
    ETACS_VARIANT_137(137, 33, 1, 1, 0b00000010, true, false, "distance_travelled", "Distance travelled", "Отображение пройденной дистанции"),
    ETACS_VARIANT_138(138, 33, 1, 2, 0b00000100, true, false, "fuel_used", "Fuel used", "Отображение кол-ва использованного топлива"),
    ETACS_VARIANT_139(139, 33, 1, 3, 0b00001000, true, false, "trip_autoreset_ig_off", "Trip autoreset IG OFF", "Выключение сброса показаний маршрутного компьютера при выключении зажигания"),
    ETACS_VARIANT_140(140, 33, 1, 4, 0b00010000, true, false, "variable_speed_alarm", "Variable Speed Alarm", "Сигнализация о превышении переменной скорости. Устанавливается долгим нажатием кнопки Info при движении"),
    ETACS_VARIANT_141(141, 33, 1, 5, 0b00100000, true, false, "rest_reminder", "Rest reminder", "Напоминание об отдыхе"),
    ETACS_VARIANT_142(142, 33, 1, 6, 0b01000000, true, false, "instant_speed", "Instant speed", "? мгновенная скорость ?"),
    ETACS_VARIANT_143(143, 33, 1, 7, 0b10000000, true, false,"seat_belt_reminder_control_type", "Seat belt reminder control type", "Тип управления системой напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_144(144, 34, 1, 0, 0b00000001, true, false, "seat_belt_reminder_flashing", "Seat belt reminder flashing", "Мигание индикатора напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_145(145, 34, 2, 1, 0b00000110, true, false,"seat_belt_reminder_indicator", "Seat belt reminder indicator", "Индикатор напоминания о непристегнутом ремне безопасности"),
    ETACS_VARIANT_146(146, 34, 1, 3, 0b00001000, true, false, "reverse_alarm", "Reverse alarm", "Звуковой сигнализатор заднего хода"),
    ETACS_VARIANT_147(147, 34, 1, 4, 0b00010000, true, false, "key_reminder", "Key reminder", "Напоминание о ключе в замке зажигания"),
    ETACS_VARIANT_148(148, 34, 1, 5, 0b00100000, true,false, "lighting_monitor", "Lighting monitor", "? Замок зажигания Pajero ?"),
    ETACS_VARIANT_149(149, 34, 1, 6, 0b01000000, true, false, "gcc_speed_alarm", "GCC speed alarm", "Сигнализация о превышении заданной величины скорости"),
    ETACS_VARIANT_150(150, 34, 1, 7, 0b10000000, true, false, "condition_buzzer", "Condition buzzer", ""),
    ETACS_VARIANT_151(151, 35, 1, 0, 0b00000001, false, true, "rent_a_car_mode_ig_off_always", "Rent-a-car mode IG-OFF always", "Специальный режим выключения зажигания для арендуемых автомобилей"),
    ETACS_VARIANT_152(152, 35, 1, 1, 0b00000010, false, true,"fuel_tank_type", "Fuel tank type", "Тип топливного бака"),
    ETACS_VARIANT_153(153, 35, 5, 2, 0b01111100, true, false,"service_reminder_schedule_table", "Service reminder schedule table", "Напоминания об очередном техническом обслуживании"),
    ETACS_VARIANT_154(154, 35, 1, 7, 0b10000000, true, false, "gcc_speed_alarm_indicator", "GCC speed alarm indicator", "Предупредительный сигнализатор превышения заданной скорости"),
    ETACS_VARIANT_155(155, 36, 4, 0, 0b00001111, false, true,"tpms_information", "TPMS information", "Давление в шинах для TPMS"),
    ETACS_VARIANT_156(156, 36, 2, 4, 0b00110000, true, false,"horn_chirp_by_keyless", "Horn chirp by keyless", "Подтверждение сигналом при открытии / закрытии"),
    ETACS_VARIANT_157(157, 36, 1, 6, 0b01000000, true, false, "rear_sr_unlock_output", "Rear S/R Unlock Output", "Выходной сигнал разблокировки заднего S/R"),
    ETACS_VARIANT_158(158, 36, 1, 7, 0b10000000, false, true, "trailer_turn_detection", "Trailer turn detection", "Обнаружение наличия прицепа"),
    ETACS_VARIANT_159(159, 37, 1, 0, 0b00000001, false, true, "shift_Lever", "Shift Lever", "Рычаг переключения передач ??"),
    ETACS_VARIANT_160(160, 37, 1, 1, 0b00000010, false, true, "adaptive_front_lighting_system", "AFS - Adaptive Front-Lighting System", "Модуль AFS (Adaptive Front-Lighting System)"),
    ETACS_VARIANT_161(161, 37, 1, 2, 0b00000100, false, true, "satellite_radio", "Satellite Radio", "Спутниковое радио"),
    ETACS_VARIANT_162(162, 37, 1, 3, 0b00001000, false, true, "auto_stop_go", "Auto Stop & Go (AS&G)", "Автоматическия система стоп-старт"),
    ETACS_VARIANT_163(163, 37, 3, 4, 0b01110000, false, true,"display_opening_type", "Display opening type", "Тип первоначального рабочего окна"),
    ETACS_VARIANT_164(164, 37, 1, 7, 0b10000000, true, false, "smart_entry_auto_lock_customize", "Smart entry auto lock customize", "Автопостановка на охрану (при наличии KOS)"), //GSI system  [Система GSI]
    ETACS_VARIANT_165(165, 38, 2, 0, 0b00000011, true, false,"drl_function", "DRL function **", "** Функция DRL"),
    ETACS_VARIANT_166(166, 38, 1, 2, 0b00000100, false, true, "facu", "FACU", ""),
    ETACS_VARIANT_167(167, 38, 1, 3, 0b00001000, true, false, "s_awc_control_display", "S-AWC Control display", "Отображение режимов S-AWC на дисплее"),
    ETACS_VARIANT_168(168, 38, 1, 4, 0b00010000, false, true, "diesel_particulate_filter", "Diesel particulate filter", "Сажевый фильтр"),
    ETACS_VARIANT_169(169, 38, 1, 5, 0b00100000, false, true, "language_mode", "Language mode", "Режим настройки языка интерфейса"),
    ETACS_VARIANT_170(170, 38, 1, 6, 0b01000000, false, true, "wss", "Steering Wheel Switch ?", "? переключатель на рулевом колесе ?"),
    ETACS_VARIANT_171(171, 38, 1, 7, 0b10000000, true, false, "door_unlock_mode_customize", "Door Unlock Mode Customize **", "** Настройка режима отпирания дверей"),
    ETACS_VARIANT_172(172, 39, 3, 0, 0b00000111, false, true,"rls_overwipe_type", "RLS overwipe type", "со стеклоочистителем RLS"),
    ETACS_VARIANT_173(173, 39, 2, 3, 0b00011000, false, true,"rls_ws_type", "RLS WS type", "тип RLS WS"),
    ETACS_VARIANT_174(174, 39, 1, 7, 0b10000000, false, true, "launch_gear_block_alarm", "Launch gear block alarm", "Сигнализатор блокировки на первой передаче"),
    ETACS_VARIANT_175(175, 40, 4, 0, 0b00001111, false, true,"security_sensor_gain_setting", "Security sensor gain setting", "Настройка чувствительности датчика охранной системы"),
    ETACS_VARIANT_176(176, 40, 1, 5, 0b00100000, false, true,"indirect_lamp_type", "Indirect lamp type", "Тип подсветки"),
    ETACS_VARIANT_177(177, 40, 2, 6, 0b11000000, false, true,"4wd_switch_type", "4WD switch type", "Тип переключателя 4WD"),
    ETACS_VARIANT_178(178, 40, 1, 0, 0b00000001, false, true, "miev_remote_ecu", "MiEV REMOTE ECU **", "** Блок дистанционной системы доступа"),
    ETACS_VARIANT_179(179, 40, 1, 1, 0b00000010, false, true, "automatic_high_beam", "Automatic High Beam", "Автопереключение дальнего света"),
    ETACS_VARIANT_180(180, 40, 1, 2, 0b00000100, false, true, "automatic_high_beam_function", "Automatic High Beam function", "Функции переключения автоматического дальнего света"),
    ETACS_VARIANT_UNKNOWN(999, 0, 0, 0, 0, false, true, "unknown", "unknown", "");
    private final int idx;
    private final String name;
    private final String title;
    private final String summary;
    private final int byteIdx;
    private final int length;
    private final int startBit;
    private final int mask;
    private final boolean visible;
    private final boolean extended;

    EtacsVariantCoding(int idx, int byteIdx, int length,
                      int startBit, int mask, boolean visible, boolean extended,
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
            case 1 -> Arrays.stream(EtacsVehicleLine.values()).map(EtacsVehicleLine::getTitle);
            case 2 -> Arrays.stream(EtacsModelYear.values()).map(EtacsModelYear::getTitle);
            case 3 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 4 -> Arrays.stream(EtacsDestinationRegion.values()).map(EtacsDestinationRegion::getTitle);
            case 5 -> Arrays.stream(EtacsTransmission.values()).map(EtacsTransmission::getTitle);
            case 6 -> Arrays.stream(EtacsEngineType.values()).map(EtacsEngineType::getTitle);
            case 7 -> Arrays.stream(EtacsEnginePower.values()).map(EtacsEnginePower::getTitle);
            case 8 -> Arrays.stream(EtacsHandleSide.values()).map(EtacsHandleSide::getTitle);
            case 9 -> Arrays.stream(EtacsChassisTypeForAsc.values()).map(EtacsChassisTypeForAsc::getTitle);
            case 10 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 11 -> Arrays.stream(EtacsFinalDrive.values()).map(EtacsFinalDrive::getTitle);
            case 12 -> Arrays.stream(EtacsTransferType.values()).map(EtacsTransferType::getTitle);
            case 13 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 14 -> Arrays.stream(DeadLockOperationCustomize.values()).map(DeadLockOperationCustomize::getTitle);
            case 15 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getTitle);
            case 16 -> Arrays.stream(TireCircumference.values()).map(TireCircumference::getTitle);
            case 17 -> Arrays.stream(FuelTankSize.values()).map(FuelTankSize::getTitle);
            case 18 -> Arrays.stream(DrlType.values()).map(DrlType::getTitle);
            case 19 -> Arrays.stream(Etacs_NotPresent_PresentABC.values()).map(Etacs_NotPresent_PresentABC::getTitle);
            case 20 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 21 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 22 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 23 -> Arrays.stream(ImmobilizerType.values()).map(ImmobilizerType::getTitle);
            case 24 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 25 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 26 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 27 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 28 -> Arrays.stream(SpeedMeterScale.values()).map(SpeedMeterScale::getTitle);
            case 29 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 30 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 31 -> Arrays.stream(Etacs_NotPresent_PresentABC.values()).map(Etacs_NotPresent_PresentABC::getTitle);
            case 32 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 33 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 34 -> Arrays.stream(AccPowerAutoCut.values()).map(AccPowerAutoCut::getTitle);
            case 35 -> Arrays.stream(SpeakersNumber.values()).map(SpeakersNumber::getTitle);
            case 36 -> Arrays.stream(SeatMaterial.values()).map(SeatMaterial::getTitle);
            case 37 -> Arrays.stream(AutomaticLightThreshold.values()).map(AutomaticLightThreshold::getTitle);
            case 38 -> Arrays.stream(FrontDiff.values()).map(FrontDiff::getTitle);
            case 39 -> Arrays.stream(RearDiff.values()).map(RearDiff::getTitle);
            case 40 -> Arrays.stream(PowerWindowType.values()).map(PowerWindowType::getTitle);
            case 41 -> Arrays.stream(SunRoofType.values()).map(SunRoofType::getTitle);
            case 42 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 43 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 44 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 45 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 46 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 47 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 48 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getTitle);
            case 49 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 50 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 51 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 52 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 53 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 54 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 55 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 56 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 57 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getTitle);
            case 58 -> Arrays.stream(HeadLampLevelingSystemType.values()).map(HeadLampLevelingSystemType::getTitle);
            case 59 -> Arrays.stream(RearWiperMode.values()).map(RearWiperMode::getTitle);
            case 60 -> Arrays.stream(KosDoorEntryType.values()).map(KosDoorEntryType::getTitle);
            case 61 -> Arrays.stream(RearWiperReverseCustomize.values()).map(RearWiperReverseCustomize::getTitle);
            case 62 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 63 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 64 -> Arrays.stream(AutoFoldMirror.values()).map(AutoFoldMirror::getTitle);
            case 65 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 66 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 67 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 68 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 69 -> Arrays.stream(DoorUnlockByIGCustomize.values()).map(DoorUnlockByIGCustomize::getTitle);
            case 70 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 71 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 72 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 73 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getTitle);
            case 74 -> Arrays.stream(WelcomeLight.values()).map(WelcomeLight::getTitle);
            case 75 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 76 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 77 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 78 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 79 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 80 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 81 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 82 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 83 -> Arrays.stream(IgKeyIllumination.values()).map(IgKeyIllumination::getTitle);
            case 84 -> Arrays.stream(TurnSignalBulb.values()).map(TurnSignalBulb::getTitle);
            case 85 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 86 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 87 -> Arrays.stream(HeadLampType.values()).map(HeadLampType::getTitle);
            case 88 -> Arrays.stream(Etacs_Enable_Disable.values()).map(Etacs_Enable_Disable::getTitle);
            case 89 -> Arrays.stream(HeadLampWasher.values()).map(HeadLampWasher::getTitle);
            case 90 -> Arrays.stream(FronFogLampMode.values()).map(FronFogLampMode::getTitle);
            case 91 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 92 -> Arrays.stream(RearFogLamp.values()).map(RearFogLamp::getTitle);
            case 93 -> Arrays.stream(RoomLampDelayTimer.values()).map(RoomLampDelayTimer::getTitle);
            case 94 -> Arrays.stream(RoomLampControl.values()).map(RoomLampControl::getTitle);
            case 95 -> Arrays.stream(GateTrunkLamp.values()).map(GateTrunkLamp::getTitle);
            case 96 -> Arrays.stream(HeadLampAutoCutMode.values()).map(HeadLampAutoCutMode::getTitle);
            case 97 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 98 -> Arrays.stream(DoorLockSystem.values()).map(DoorLockSystem::getTitle);
            case 99 -> Arrays.stream(AutomaticDoorLockUnlock.values()).map(AutomaticDoorLockUnlock::getTitle);
            case 100 -> Arrays.stream(KeyReminderUnlock.values()).map(KeyReminderUnlock::getTitle);
            case 101 -> Arrays.stream(HornType.values()).map(HornType::getTitle);
            case 102 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 103 -> Arrays.stream(CoolingFanControl.values()).map(CoolingFanControl::getTitle);
            case 104 -> Arrays.stream(SecurityAlarmMode.values()).map(SecurityAlarmMode::getTitle);
            case 105 -> Arrays.stream(SecurityAlarmFunction.values()).map(SecurityAlarmFunction::getTitle);
            case 106 -> Arrays.stream(Etacs_Present_NotPresent.values()).map(Etacs_Present_NotPresent::getTitle);
            case 107 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 108 -> Arrays.stream(GateTrunkType.values()).map(GateTrunkType::getTitle);
            case 109 -> Arrays.stream(Etacs_NotPresent2_Present.values()).map(Etacs_NotPresent2_Present::getTitle);
            case 110 -> Arrays.stream(Etacs_NotPresent2_Present.values()).map(Etacs_NotPresent2_Present::getTitle);
            case 111 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 112 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 113 -> Arrays.stream(FrontWiperControl.values()).map(FrontWiperControl::getTitle);
            case 114 -> Arrays.stream(Etacs_NotPresent2_Present.values()).map(Etacs_NotPresent2_Present::getTitle);
            case 115 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 116 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 117 -> Arrays.stream(RadioFrequency.values()).map(RadioFrequency::getTitle);
            case 118 -> Arrays.stream(HeadLightLevelingType.values()).map(HeadLightLevelingType::getTitle);
            case 119 -> Arrays.stream(AfsAclType.values()).map(AfsAclType::getTitle);
            case 120 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 121 -> Arrays.stream(CompressorType.values()).map(CompressorType::getTitle);
            case 122 -> Arrays.stream(TemperatureUnits.values()).map(TemperatureUnits::getTitle);
            case 123 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 124 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 125 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 126 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 127 -> Arrays.stream(VehicleLanguage.values()).map(VehicleLanguage::getTitle);
            case 128 -> Arrays.stream(FuelUnits.values()).map(FuelUnits::getTitle);
            case 129 -> Arrays.stream(FuelConsumptionScale.values()).map(FuelConsumptionScale::getTitle);
            case 130 -> Arrays.stream(SeatBeltReminderLogic.values()).map(SeatBeltReminderLogic::getTitle);
            case 131 -> Arrays.stream(CoolantTempGaugeThreshold.values()).map(CoolantTempGaugeThreshold::getTitle);
            case 132 -> Arrays.stream(FrostWarningThreshold.values()).map(FrostWarningThreshold::getTitle);
            case 133 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 134 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 135 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 136 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 137 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 138 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 139 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 140 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 141 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 142 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 143 -> Arrays.stream(SeatBeltReminderControlType.values()).map(SeatBeltReminderControlType::getTitle);
            case 144 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 145 -> Arrays.stream(SeatBeltReminderIndicator.values()).map(SeatBeltReminderIndicator::getTitle);
            case 146 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 147 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 148 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 149 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 150 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 151 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 152 -> Arrays.stream(FuelTankType.values()).map(FuelTankType::getTitle);
            case 153 -> Arrays.stream(ServiceReminderScheduleTable.values()).map(ServiceReminderScheduleTable::getTitle);
            case 154 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 155 -> Arrays.stream(TpmsInformation.values()).map(TpmsInformation::getTitle);
            case 156 -> Arrays.stream(hornChirpType.values()).map(hornChirpType::getTitle);
            case 157 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 158 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 159 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 160 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 161 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 162 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 163 -> Arrays.stream(DisplayOpeningType.values()).map(DisplayOpeningType::getTitle);
            case 164 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 165 -> Arrays.stream(DrlFunction.values()).map(DrlFunction::getTitle);
            case 166 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 167 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getTitle);
            case 168 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 169 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 170 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 171 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            case 172 -> Arrays.stream(RlsOverWipeType.values()).map(RlsOverWipeType::getTitle);
            case 173 -> Arrays.stream(RlsWsType.values()).map(RlsWsType::getTitle);
            case 174 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 175 -> Arrays.stream(SecuritySensorGainSetting.values()).map(SecuritySensorGainSetting::getTitle);
            case 176 -> Arrays.stream(IndirectLampType.values()).map(IndirectLampType::getTitle);
            case 177 -> Arrays.stream(SwitchType4wd.values()).map(SwitchType4wd::getTitle);
            case 178 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 179 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 180 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getTitle);
            case 205 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getTitle);
            default -> Stream.of("unknown");
        };

        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Integer> getAvailableValues(int id) {
        Stream<Integer> stream = switch (id) {
            case 1 -> Arrays.stream(EtacsVehicleLine.values()).map(EtacsVehicleLine::getIdx);
            case 2 -> Arrays.stream(EtacsModelYear.values()).map(EtacsModelYear::getIdx);
            case 3 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 4 -> Arrays.stream(EtacsDestinationRegion.values()).map(EtacsDestinationRegion::getIdx);
            case 5 -> Arrays.stream(EtacsTransmission.values()).map(EtacsTransmission::getIdx);
            case 6 -> Arrays.stream(EtacsEngineType.values()).map(EtacsEngineType::getIdx);
            case 7 -> Arrays.stream(EtacsEnginePower.values()).map(EtacsEnginePower::getIdx);
            case 8 -> Arrays.stream(EtacsHandleSide.values()).map(EtacsHandleSide::getIdx);
            case 9 -> Arrays.stream(EtacsChassisTypeForAsc.values()).map(EtacsChassisTypeForAsc::getIdx);
            case 10 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 11 -> Arrays.stream(EtacsFinalDrive.values()).map(EtacsFinalDrive::getIdx);
            case 12 -> Arrays.stream(EtacsTransferType.values()).map(EtacsTransferType::getIdx);
            case 13 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 14 -> Arrays.stream(DeadLockOperationCustomize.values()).map(DeadLockOperationCustomize::getIdx);
            case 15 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getIdx);
            case 16 -> Arrays.stream(TireCircumference.values()).map(TireCircumference::getIdx);
            case 17 -> Arrays.stream(FuelTankSize.values()).map(FuelTankSize::getIdx);
            case 18 -> Arrays.stream(DrlType.values()).map(DrlType::getIdx);
            case 19 -> Arrays.stream(Etacs_NotPresent_PresentABC.values()).map(Etacs_NotPresent_PresentABC::getIdx);
            case 20 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 21 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 22 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 23 -> Arrays.stream(ImmobilizerType.values()).map(ImmobilizerType::getIdx);
            case 24 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 25 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 26 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 27 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 28 -> Arrays.stream(SpeedMeterScale.values()).map(SpeedMeterScale::getIdx);
            case 29 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 30 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 31 -> Arrays.stream(Etacs_NotPresent_PresentABC.values()).map(Etacs_NotPresent_PresentABC::getIdx);
            case 32 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 33 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 34 -> Arrays.stream(AccPowerAutoCut.values()).map(AccPowerAutoCut::getIdx);
            case 35 -> Arrays.stream(SpeakersNumber.values()).map(SpeakersNumber::getIdx);
            case 36 -> Arrays.stream(SeatMaterial.values()).map(SeatMaterial::getIdx);
            case 37 -> Arrays.stream(AutomaticLightThreshold.values()).map(AutomaticLightThreshold::getIdx);
            case 38 -> Arrays.stream(FrontDiff.values()).map(FrontDiff::getIdx);
            case 39 -> Arrays.stream(RearDiff.values()).map(RearDiff::getIdx);
            case 40 -> Arrays.stream(PowerWindowType.values()).map(PowerWindowType::getIdx);
            case 41 -> Arrays.stream(SunRoofType.values()).map(SunRoofType::getIdx);
            case 42 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 43 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 44 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 45 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 46 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 47 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 48 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getIdx);
            case 49 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 50 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 51 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 52 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 53 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 54 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 55 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 56 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 57 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getIdx);
            case 58 -> Arrays.stream(HeadLampLevelingSystemType.values()).map(HeadLampLevelingSystemType::getIdx);
            case 59 -> Arrays.stream(RearWiperMode.values()).map(RearWiperMode::getIdx);
            case 60 -> Arrays.stream(KosDoorEntryType.values()).map(KosDoorEntryType::getIdx);
            case 61 -> Arrays.stream(RearWiperReverseCustomize.values()).map(RearWiperReverseCustomize::getIdx);
            case 62 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 63 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 64 -> Arrays.stream(AutoFoldMirror.values()).map(AutoFoldMirror::getIdx);
            case 65 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 66 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 67 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 68 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 69 -> Arrays.stream(DoorUnlockByIGCustomize.values()).map(DoorUnlockByIGCustomize::getIdx);
            case 70 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 71 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 72 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 73 -> Arrays.stream(Etacs_Disable_Enable_defD_E.values()).map(Etacs_Disable_Enable_defD_E::getIdx);
            case 74 -> Arrays.stream(WelcomeLight.values()).map(WelcomeLight::getIdx);
            case 75 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 76 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 77 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 78 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 79 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 80 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 81 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 82 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 83 -> Arrays.stream(IgKeyIllumination.values()).map(IgKeyIllumination::getIdx);
            case 84 -> Arrays.stream(TurnSignalBulb.values()).map(TurnSignalBulb::getIdx);
            case 85 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 86 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 87 -> Arrays.stream(HeadLampType.values()).map(HeadLampType::getIdx);
            case 88 -> Arrays.stream(Etacs_Enable_Disable.values()).map(Etacs_Enable_Disable::getIdx);
            case 89 -> Arrays.stream(HeadLampWasher.values()).map(HeadLampWasher::getIdx);
            case 90 -> Arrays.stream(FronFogLampMode.values()).map(FronFogLampMode::getIdx);
            case 91 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 92 -> Arrays.stream(RearFogLamp.values()).map(RearFogLamp::getIdx);
            case 93 -> Arrays.stream(RoomLampDelayTimer.values()).map(RoomLampDelayTimer::getIdx);
            case 94 -> Arrays.stream(RoomLampControl.values()).map(RoomLampControl::getIdx);
            case 95 -> Arrays.stream(GateTrunkLamp.values()).map(GateTrunkLamp::getIdx);
            case 96 -> Arrays.stream(HeadLampAutoCutMode.values()).map(HeadLampAutoCutMode::getIdx);
            case 97 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 98 -> Arrays.stream(DoorLockSystem.values()).map(DoorLockSystem::getIdx);
            case 99 -> Arrays.stream(AutomaticDoorLockUnlock.values()).map(AutomaticDoorLockUnlock::getIdx);
            case 100 -> Arrays.stream(KeyReminderUnlock.values()).map(KeyReminderUnlock::getIdx);
            case 101 -> Arrays.stream(HornType.values()).map(HornType::getIdx);
            case 102 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 103 -> Arrays.stream(CoolingFanControl.values()).map(CoolingFanControl::getIdx);
            case 104 -> Arrays.stream(SecurityAlarmMode.values()).map(SecurityAlarmMode::getIdx);
            case 105 -> Arrays.stream(SecurityAlarmFunction.values()).map(SecurityAlarmFunction::getIdx);
            case 106 -> Arrays.stream(Etacs_Present_NotPresent.values()).map(Etacs_Present_NotPresent::getIdx);
            case 107 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 108 -> Arrays.stream(GateTrunkType.values()).map(GateTrunkType::getIdx);
            case 109 -> Arrays.stream(Etacs_NotPresent2_Present.values()).map(Etacs_NotPresent2_Present::getIdx);
            case 110 -> Arrays.stream(Etacs_NotPresent2_Present.values()).map(Etacs_NotPresent2_Present::getIdx);
            case 111 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 112 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 113 -> Arrays.stream(FrontWiperControl.values()).map(FrontWiperControl::getIdx);
            case 114 -> Arrays.stream(Etacs_NotPresent2_Present.values()).map(Etacs_NotPresent2_Present::getIdx);
            case 115 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 116 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 117 -> Arrays.stream(RadioFrequency.values()).map(RadioFrequency::getIdx);
            case 118 -> Arrays.stream(HeadLightLevelingType.values()).map(HeadLightLevelingType::getIdx);
            case 119 -> Arrays.stream(AfsAclType.values()).map(AfsAclType::getIdx);
            case 120 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 121 -> Arrays.stream(CompressorType.values()).map(CompressorType::getIdx);
            case 122 -> Arrays.stream(TemperatureUnits.values()).map(TemperatureUnits::getIdx);
            case 123 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 124 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 125 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 126 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 127 -> Arrays.stream(VehicleLanguage.values()).map(VehicleLanguage::getIdx);
            case 128 -> Arrays.stream(FuelUnits.values()).map(FuelUnits::getIdx);
            case 129 -> Arrays.stream(FuelConsumptionScale.values()).map(FuelConsumptionScale::getIdx);
            case 130 -> Arrays.stream(SeatBeltReminderLogic.values()).map(SeatBeltReminderLogic::getIdx);
            case 131 -> Arrays.stream(CoolantTempGaugeThreshold.values()).map(CoolantTempGaugeThreshold::getIdx);
            case 132 -> Arrays.stream(FrostWarningThreshold.values()).map(FrostWarningThreshold::getIdx);
            case 133 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 134 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 135 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 136 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 137 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 138 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 139 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 140 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 141 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 142 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 143 -> Arrays.stream(SeatBeltReminderControlType.values()).map(SeatBeltReminderControlType::getIdx);
            case 144 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 145 -> Arrays.stream(SeatBeltReminderIndicator.values()).map(SeatBeltReminderIndicator::getIdx);
            case 146 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 147 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 148 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 149 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 150 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 151 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 152 -> Arrays.stream(FuelTankType.values()).map(FuelTankType::getIdx);
            case 153 -> Arrays.stream(ServiceReminderScheduleTable.values()).map(ServiceReminderScheduleTable::getIdx);
            case 154 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 155 -> Arrays.stream(TpmsInformation.values()).map(TpmsInformation::getIdx);
            case 156 -> Arrays.stream(hornChirpType.values()).map(hornChirpType::getIdx);
            case 157 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 158 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 159 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 160 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 161 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 162 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 163 -> Arrays.stream(DisplayOpeningType.values()).map(DisplayOpeningType::getIdx);
            case 164 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 165 -> Arrays.stream(DrlFunction.values()).map(DrlFunction::getIdx);
            case 166 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 167 -> Arrays.stream(Etacs_NotAvailable_Available.values()).map(Etacs_NotAvailable_Available::getIdx);
            case 168 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 169 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 170 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 171 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
            case 172 -> Arrays.stream(RlsOverWipeType.values()).map(RlsOverWipeType::getIdx);
            case 173 -> Arrays.stream(RlsWsType.values()).map(RlsWsType::getIdx);
            case 174 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 175 -> Arrays.stream(SecuritySensorGainSetting.values()).map(SecuritySensorGainSetting::getIdx);
            case 176 -> Arrays.stream(IndirectLampType.values()).map(IndirectLampType::getIdx);
            case 177 -> Arrays.stream(SwitchType4wd.values()).map(SwitchType4wd::getIdx);
            case 178 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 179 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 180 -> Arrays.stream(Etacs_NotPresent_Present.values()).map(Etacs_NotPresent_Present::getIdx);
            case 205 -> Arrays.stream(Etacs_Disable_Enable.values()).map(Etacs_Disable_Enable::getIdx);
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
