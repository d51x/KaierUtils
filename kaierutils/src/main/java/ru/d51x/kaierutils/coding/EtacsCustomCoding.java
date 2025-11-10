package ru.d51x.kaierutils.coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.d51x.kaierutils.coding.datatypes.custom.AccPowerAutoCut;
import ru.d51x.kaierutils.coding.datatypes.custom.AutoDoorLockBySpeed;
import ru.d51x.kaierutils.coding.datatypes.custom.AutoDoorUnlock;
import ru.d51x.kaierutils.coding.datatypes.custom.AutoFoldMirror;
import ru.d51x.kaierutils.coding.datatypes.custom.AutomaticLightSensivity;
import ru.d51x.kaierutils.coding.datatypes.custom.BuzzerAnswerBack;
import ru.d51x.kaierutils.coding.datatypes.custom.ComfortFlasherSwitchTime;
import ru.d51x.kaierutils.coding.datatypes.custom.ComingHomeLightTime;
import ru.d51x.kaierutils.coding.datatypes.custom.DeadLockButtonOperation;
import ru.d51x.kaierutils.coding.datatypes.custom.DoorUnlockMode;
import ru.d51x.kaierutils.coding.datatypes.custom.EtacsCustom_Disable_Enable;
import ru.d51x.kaierutils.coding.datatypes.custom.EtacsCustom_Enable_Disable;
import ru.d51x.kaierutils.coding.datatypes.custom.FrontRearWiper;
import ru.d51x.kaierutils.coding.datatypes.custom.FrontWiperOperation;
import ru.d51x.kaierutils.coding.datatypes.custom.HazardAnswerBack;
import ru.d51x.kaierutils.coding.datatypes.custom.HeadLampAutoCutCustomize;
import ru.d51x.kaierutils.coding.datatypes.custom.HeadLightEvRemoteCarFinder;
import ru.d51x.kaierutils.coding.datatypes.custom.HornChirp;
import ru.d51x.kaierutils.coding.datatypes.custom.HornChirpDuration;
import ru.d51x.kaierutils.coding.datatypes.custom.InteriorIlluminationControl;
import ru.d51x.kaierutils.coding.datatypes.custom.InteriorLampAutoCutTimer;
import ru.d51x.kaierutils.coding.datatypes.custom.KosFeature;
import ru.d51x.kaierutils.coding.datatypes.custom.KosUnlockDisableTime;
import ru.d51x.kaierutils.coding.datatypes.custom.MultiModeRke;
import ru.d51x.kaierutils.coding.datatypes.custom.OuterBuzzerVolume;
import ru.d51x.kaierutils.coding.datatypes.custom.PowerWindowKeyOffTimer;
import ru.d51x.kaierutils.coding.datatypes.custom.PreAlarmDuration;
import ru.d51x.kaierutils.coding.datatypes.custom.RearWiperInterval;
import ru.d51x.kaierutils.coding.datatypes.custom.RearWiperReverse;
import ru.d51x.kaierutils.coding.datatypes.custom.RemoteLightOnTime;
import ru.d51x.kaierutils.coding.datatypes.custom.RoomLampDelayTimer;
import ru.d51x.kaierutils.coding.datatypes.custom.SecuritySensorSensivity;
import ru.d51x.kaierutils.coding.datatypes.custom.SirenAnswerBack;
import ru.d51x.kaierutils.coding.datatypes.custom.TimerLockTimer;
import ru.d51x.kaierutils.coding.datatypes.custom.TurnPowerSource;
import ru.d51x.kaierutils.coding.datatypes.custom.WelcomeLight;

public enum EtacsCustomCoding {


    ETACS_CUSTOM_001(1, 0, 4, 0, 0b00001111, false, "turn_power_source", "Turn power source", "Регулировка условий работы указателей поворота"),
    ETACS_CUSTOM_002(2,  0, 4, 4, 0b11110000, false, "comfort_flasher", "Comfort flasher", "Комфортный омыватель"),
    ETACS_CUSTOM_003(3,  1, 4, 0, 0b00001111, false, "hazard_answer_back", "Hazard answer back", "Регулировка количества миганий аварийной лампы при постановке/снятии на/с охраны"),
    ETACS_CUSTOM_004(4,  1, 4, 4, 0b11110000, false, "front_wiper_operation", "Front wiper operation", "Регулировка работы прерывистого режима стеклоочистителей"),
    ETACS_CUSTOM_005(5,  2, 4, 0, 0b00001111, false, "front_rear_wiper_washer", "Front/rear wiper washer", "Отключение или включение функции стеклоочистителя, связанной с омывателем"),
    ETACS_CUSTOM_006(6,  2, 4, 4, 0b11110000, false, "Intermittent_time_of_rear_wiper", "Intermittent time of rear wiper", "Регулировка интервала работы заднего стеклоочистителя"),
    ETACS_CUSTOM_007(7,  3, 4, 0, 0b00001111, false, "rear_wiper_low_speed_mode", "Rear wiper Low speed mode", "Режим отключения или включения заднего стеклоочистителя в медленном непрерывном режиме"),
    ETACS_CUSTOM_008(8,  3,4,4, 0b11110000, false, "auto_fold_mirror", "Auto fold mirror", "Функция авто-складывания зеркал"),
    ETACS_CUSTOM_009(9, 4, 4, 0, 0b00001111, false, "sensitivity_for_auto_lamp", "Sensitivity for auto lamp", "Настройка чувствительности фотосенсора для автоматического включения света"),
    ETACS_CUSTOM_010(10, 4, 4, 4, 0b11110000, false, "auto_lamp_linked_wiper", "Auto lamp linked wiper", "Автоматичское омывание фар при включении стеклоочистителя"),
    ETACS_CUSTOM_011(11, 5, 4, 0, 0b00001111, false, "room_lamp_delay_timer_with_door", "Room lamp delay timer with door", "Регулировка задержки выключения внутреннего освещения"),
    ETACS_CUSTOM_012(12, 5, 4, 4, 0b11110000, false, "head_lamp_auto_cut_customize", "Head lamp auto cut customize", "Настройка регулировки автоматического отключения головного света"),
    ETACS_CUSTOM_013(13, 6, 4, 0, 0b00001111, false, "interior_lamp_auto_cut_timer", "Interior lamp auto cut timer", "Настройка таймера автоматического отключения внутренней подсветки"),
    ETACS_CUSTOM_014(14, 6, 4, 4, 0b11110000, false, "auto_door_lock_by_vehicle_speed", "Auto door lock by vehicle speed", "Автоматическая блокировка дверей в движении"),
    ETACS_CUSTOM_015(15, 7, 4, 4, 0b11110000, false, "auto_door_unlock", "Auto door unlock", "Функции настройки автоматической разблокировки дверей"),
    ETACS_CUSTOM_016(16, 8, 4, 0, 0b00001111, false, "door_unlock_mode", "Door unlock mode", "Настройка отпирания дверей"),
    ETACS_CUSTOM_017(17, 8, 4, 4, 0b11110000, false, "power_door_unlock_by_knob", "Power Door Unlock by Knob", "Настройка кнопки разблокировки"),
    ETACS_CUSTOM_018(18, 9, 4, 0, 0b00001111, false, "deadlock_button_operation", "Deadlock Button Operation", "Количество нажатий кнопки для блокировки"),
    ETACS_CUSTOM_019(19, 9, 4, 4, 0b11110000, false, "horn_chirp_by_keyless", "Horn chirp by keyless", "Подтверждение сигналом кнопок брелка"),
    ETACS_CUSTOM_020(20, 10, 4, 0, 0b00001111, false, "buzzer_answer_back", "Buzzer answer back", ""),
    ETACS_CUSTOM_021(21, 10, 4, 4, 0b11110000, false, "timer_lock_timer", "Timer lock timer", "Настройка таймера автоматической блокировки дверей"),
    ETACS_CUSTOM_022(22, 11, 4, 0, 0b00001111, false, "alarm", "Alarm", "Включить/выключить штатную противоугонную систему"),
    ETACS_CUSTOM_023(23, 11, 4, 4, 0b11110000, false, "power_window_key_off_timer", "Power window key off timer", "Время отключения кнопок стеклоподъемников при выключенном зажигании"),
    ETACS_CUSTOM_024(24, 12, 4, 0, 0b00001111, false, "duration_of_pre-alarm", "Duration of pre-alarm", "Настройка длительности сигнала (внутри салона) после постановки на охрану"),
    ETACS_CUSTOM_025(25, 12, 4, 4, 0b11110000, false, "multi_mode", "Multi mode", "Настройка многорежимного бесключевого доступа для машин с электроприводом зеркал и электростеклоподъемниками"),
    ETACS_CUSTOM_026(26, 13, 4, 0, 0b00001111, false, "panic_alarm_switch", "Panic alarm switch", ""),
    ETACS_CUSTOM_027(27, 13, 4, 4, 0b11110000, false, "duration_of_horn_chirp", "Duration of horn chirp", "Время звучания гудка"),
    ETACS_CUSTOM_028(28, 14, 4, 4, 0b11110000, false, "kos_key_detect_out_from_window", "KOS key detect out from window", "Обнаружение брелка KOS вне машины"),
    ETACS_CUSTOM_029(29, 15, 4, 0, 0b00001111, false, "kos_feature", "KOS feature", "Функции KOS"),
    ETACS_CUSTOM_030(30, 15, 4, 4, 0b11110000, false, "kos_unlock_disable_time", "KOS unlock disable time", "Настройка времени разблокировки дверей после блокировки"),
    ETACS_CUSTOM_031(31, 16, 4, 0, 0b00001111, false, "remote_eng_starter_answer_back", "Remote ENG starter answer back", "Настройка дистанционого запуска двигателя"),
    ETACS_CUSTOM_032(32, 16, 4, 4, 0b11110000, false, "p_w_down_operation_after_ig_off", "P/W down operation after IG OFF", "Включить/выключить возможность управлять стеклоподъемниками при выключенном зажигании"),
    ETACS_CUSTOM_033(33, 17, 4, 0, 0b00001111, false, "pw_main_sw_during_pw_locked", "P/W main SW during P/W locked", ""),
    ETACS_CUSTOM_034(34, 17, 4, 4, 0b11110000, false, "acc_power_auto_cut", "ACC power auto cut", "Настройка времени отключение питания при ключе в положении ACC"),
    ETACS_CUSTOM_035(35, 18, 4, 4, 0b11110000, false, "comfort_flasher_switch_time", "Comfort flasher switch time", "Время активации комфортного поворотника"),
    ETACS_CUSTOM_036(36, 19, 4, 4, 0b11110000, false, "intelligent_comfort_washer_custom", "Intelligent/Comfort washer custom", "Настройки комфортного омывателя"),
    ETACS_CUSTOM_037(37, 20, 4, 0, 0b00001111, false, "interior_illumination_control", "Interior illumination control", "Управление внутренним освещением"),
    ETACS_CUSTOM_038(38, 20, 4, 4, 0b11110000, false, "coming_home_light", "Coming home light", "Настройка длительности свечения фар фукции \"Coming home light\""),
    ETACS_CUSTOM_039(39, 21, 4, 0, 0b00001111, false, "welcome_light", "Welcome light", "Выбор типа фар для функции \"Приветственный свет\""),
    ETACS_CUSTOM_040(40, 22, 4, 0, 0b00001111, false, "rear_wiper_linked_reverse_gear", "Rear wiper(linked reverse gear)", "Настройка автоматического режима работы заднего стеклоочистителя при включенной передаче заднего хода"),
    ETACS_CUSTOM_041(41, 22, 4, 4, 0b11110000, false, "sensitivity_for_security_sensor", "Sensitivity for security sensor", "Настройка уровня чувствительности датчиков штатной сигнализации"),
    ETACS_CUSTOM_042(42, 23, 4, 0, 0b00001111, false, "outer_buzzer_volume", "Outer buzzer volume", "KOS outer buzzer volume adjustment"),
    ETACS_CUSTOM_043(43, 23, 4, 4, 0b11110000, false, "siren_answer_back", "Siren answer back", ""),
    ETACS_CUSTOM_044(44, 24, 4, 0, 0b00001111, false, "remote_light_on", "Remote Light ON", ""),
    ETACS_CUSTOM_045(45, 24, 4, 4, 0b11110000, false, "hl_ev_remote_car_finder", "H/L(EV REMOTE Car finder)", ""),
    //ETACS_CUSTOM_998(998, "", "", 0, 4, 0, 0, 0),

    // A/C Recirculation Control: With/without inside/outside air automatic control function - disable/enable
    // A/C Switch Control: With/without A/C automatic control function - disable/enable
    // Eco Mode: Function to activate ECO mode air-conditioner - Eco / Comfort
    ETACS_CUSTOM_UNKNOWN(999, 0, 0, 0, 0, true, "unknown", "unknown", "");
    private final int idx;
    private final String name;
    private final String title;
    private final String summary;
    private final int byteIdx;
    private final int length;
    private final int startBit;
    private final int mask;
    private boolean extended;

    EtacsCustomCoding(int idx, int byteIdx, int length,
                      int startBit, int mask, boolean extended, String name, String title, String summary) {
        this.idx = idx;
        this.name = name;
        this.title = title;
        this.summary = summary;
        this.byteIdx = byteIdx;
        this.length = length;
        this.startBit = startBit;
        this.mask = mask;
        this.extended = extended;
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
            case 1 -> Arrays.stream(TurnPowerSource.values()).map(TurnPowerSource::getTitle);
            case 2, 36, 31, 26, 22, 10, 7 -> Arrays.stream(EtacsCustom_Disable_Enable.values()).map(EtacsCustom_Disable_Enable::getTitle);
            case 3 -> Arrays.stream(HazardAnswerBack.values()).map(HazardAnswerBack::getTitle);
            case 4 -> Arrays.stream(FrontWiperOperation.values()).map(FrontWiperOperation::getTitle);
            case 5 -> Arrays.stream(FrontRearWiper.values()).map(FrontRearWiper::getTitle);
            case 6 -> Arrays.stream(RearWiperInterval.values()).map(RearWiperInterval::getTitle);
            case 8 -> Arrays.stream(AutoFoldMirror.values()).map(AutoFoldMirror::getTitle);
            case 9 -> Arrays.stream(AutomaticLightSensivity.values()).map(AutomaticLightSensivity::getTitle);
            case 11 -> Arrays.stream(RoomLampDelayTimer.values()).map(RoomLampDelayTimer::getTitle);
            case 12 -> Arrays.stream(HeadLampAutoCutCustomize.values()).map(HeadLampAutoCutCustomize::getTitle);
            case 13 -> Arrays.stream(InteriorLampAutoCutTimer.values()).map(InteriorLampAutoCutTimer::getTitle);
            case 14 -> Arrays.stream(AutoDoorLockBySpeed.values()).map(AutoDoorLockBySpeed::getTitle);
            case 15 -> Arrays.stream(AutoDoorUnlock.values()).map(AutoDoorUnlock::getTitle);
            case 16 -> Arrays.stream(DoorUnlockMode.values()).map(DoorUnlockMode::getTitle);
            case 17 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getTitle);
            case 18 -> Arrays.stream(DeadLockButtonOperation.values()).map(DeadLockButtonOperation::getTitle);
            case 19 -> Arrays.stream(HornChirp.values()).map(HornChirp::getTitle);
            case 20 -> Arrays.stream(BuzzerAnswerBack.values()).map(BuzzerAnswerBack::getTitle);
            case 21 -> Arrays.stream(TimerLockTimer.values()).map(TimerLockTimer::getTitle);
            case 23 -> Arrays.stream(PowerWindowKeyOffTimer.values()).map(PowerWindowKeyOffTimer::getTitle);
            case 24 -> Arrays.stream(PreAlarmDuration.values()).map(PreAlarmDuration::getTitle);
            case 25 -> Arrays.stream(MultiModeRke.values()).map(MultiModeRke::getTitle);
            case 27 -> Arrays.stream(HornChirpDuration.values()).map(HornChirpDuration::getTitle);
            case 28 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getTitle);
            case 29 -> Arrays.stream(KosFeature.values()).map(KosFeature::getTitle);
            case 30 -> Arrays.stream(KosUnlockDisableTime.values()).map(KosUnlockDisableTime::getTitle);
            case 32 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getTitle);
            case 33 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getTitle);
            case 34 -> Arrays.stream(AccPowerAutoCut.values()).map(AccPowerAutoCut::getTitle);
            case 35 -> Arrays.stream(ComfortFlasherSwitchTime.values()).map(ComfortFlasherSwitchTime::getTitle);
            case 37 -> Arrays.stream(InteriorIlluminationControl.values()).map(InteriorIlluminationControl::getTitle);
            case 38 -> Arrays.stream(ComingHomeLightTime.values()).map(ComingHomeLightTime::getTitle);
            case 39 -> Arrays.stream(WelcomeLight.values()).map(WelcomeLight::getTitle);
            case 40 -> Arrays.stream(RearWiperReverse.values()).map(RearWiperReverse::getTitle);
            case 41 -> Arrays.stream(SecuritySensorSensivity.values()).map(SecuritySensorSensivity::getTitle);
            case 42 -> Arrays.stream(OuterBuzzerVolume.values()).map(OuterBuzzerVolume::getTitle);
            case 43 -> Arrays.stream(SirenAnswerBack.values()).map(SirenAnswerBack::getTitle);
            case 44 -> Arrays.stream(RemoteLightOnTime.values()).map(RemoteLightOnTime::getTitle);
            case 45 -> Arrays.stream(HeadLightEvRemoteCarFinder.values()).map(HeadLightEvRemoteCarFinder::getTitle);
            default -> Stream.of("unknown");
        };

        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Integer> getAvailableValues(int id) {
        Stream<Integer> stream = switch (id) {
            case 1 -> Arrays.stream(TurnPowerSource.values()).map(TurnPowerSource::getIdx);
            case 2, 31, 26, 22, 36, 10, 7 -> Arrays.stream(EtacsCustom_Disable_Enable.values()).map(EtacsCustom_Disable_Enable::getIdx);
            case 3 -> Arrays.stream(HazardAnswerBack.values()).map(HazardAnswerBack::getIdx);
            case 4 -> Arrays.stream(FrontWiperOperation.values()).map(FrontWiperOperation::getIdx);
            case 5 -> Arrays.stream(FrontRearWiper.values()).map(FrontRearWiper::getIdx);
            case 6 -> Arrays.stream(RearWiperInterval.values()).map(RearWiperInterval::getIdx);
            case 8 -> Arrays.stream(AutoFoldMirror.values()).map(AutoFoldMirror::getIdx);
            case 9 -> Arrays.stream(AutomaticLightSensivity.values()).map(AutomaticLightSensivity::getIdx);
            case 11 -> Arrays.stream(RoomLampDelayTimer.values()).map(RoomLampDelayTimer::getIdx);
            case 12 -> Arrays.stream(HeadLampAutoCutCustomize.values()).map(HeadLampAutoCutCustomize::getIdx);
            case 13 -> Arrays.stream(InteriorLampAutoCutTimer.values()).map(InteriorLampAutoCutTimer::getIdx);
            case 14 -> Arrays.stream(AutoDoorLockBySpeed.values()).map(AutoDoorLockBySpeed::getIdx);
            case 15 -> Arrays.stream(AutoDoorUnlock.values()).map(AutoDoorUnlock::getIdx);
            case 16 -> Arrays.stream(DoorUnlockMode.values()).map(DoorUnlockMode::getIdx);
            case 17 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getIdx);
            case 18 -> Arrays.stream(DeadLockButtonOperation.values()).map(DeadLockButtonOperation::getIdx);
            case 19 -> Arrays.stream(HornChirp.values()).map(HornChirp::getIdx);
            case 20 -> Arrays.stream(BuzzerAnswerBack.values()).map(BuzzerAnswerBack::getIdx);
            case 21 -> Arrays.stream(TimerLockTimer.values()).map(TimerLockTimer::getIdx);
            case 23 -> Arrays.stream(PowerWindowKeyOffTimer.values()).map(PowerWindowKeyOffTimer::getIdx);
            case 24 -> Arrays.stream(PreAlarmDuration.values()).map(PreAlarmDuration::getIdx);
            case 25 -> Arrays.stream(MultiModeRke.values()).map(MultiModeRke::getIdx);
            case 27 -> Arrays.stream(HornChirpDuration.values()).map(HornChirpDuration::getIdx);
            case 28 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getIdx);
            case 29 -> Arrays.stream(KosFeature.values()).map(KosFeature::getIdx);
            case 30 -> Arrays.stream(KosUnlockDisableTime.values()).map(KosUnlockDisableTime::getIdx);
            case 32 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getIdx);
            case 33 -> Arrays.stream(EtacsCustom_Enable_Disable.values()).map(EtacsCustom_Enable_Disable::getIdx);
            case 34 -> Arrays.stream(AccPowerAutoCut.values()).map(AccPowerAutoCut::getIdx);
            case 35 -> Arrays.stream(ComfortFlasherSwitchTime.values()).map(ComfortFlasherSwitchTime::getIdx);
            case 37 -> Arrays.stream(InteriorIlluminationControl.values()).map(InteriorIlluminationControl::getIdx);
            case 38 -> Arrays.stream(ComingHomeLightTime.values()).map(ComingHomeLightTime::getIdx);
            case 39 -> Arrays.stream(WelcomeLight.values()).map(WelcomeLight::getIdx);
            case 40 -> Arrays.stream(RearWiperReverse.values()).map(RearWiperReverse::getIdx);
            case 41 -> Arrays.stream(SecuritySensorSensivity.values()).map(SecuritySensorSensivity::getIdx);
            case 42 -> Arrays.stream(OuterBuzzerVolume.values()).map(OuterBuzzerVolume::getIdx);
            case 43 -> Arrays.stream(SirenAnswerBack.values()).map(SirenAnswerBack::getIdx);
            case 44 -> Arrays.stream(RemoteLightOnTime.values()).map(RemoteLightOnTime::getIdx);
            case 45 -> Arrays.stream(HeadLightEvRemoteCarFinder.values()).map(HeadLightEvRemoteCarFinder::getIdx);
            default -> Stream.of(-1);
        };

        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public boolean isExtended() {
        return extended;
    }

    public void setExtended(boolean extended) {
        this.extended = extended;
    }

    public String getSummary() {
        return summary;
    }
}
