package ru.d51x.kaierutils.etacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private int idx;
    private String name;
    private String title;
    private String summary;
    private int byteIdx;
    private int length;
    private int startBit;
    private int mask;
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
        //ArrayList<String> res = getAvailableOptions(id);
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
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData1.values()).map(EtacsCustomData.EtacsCustomData1::getTitle);
                break;
            case 2:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData2.values()).map(EtacsCustomData.EtacsCustomData2::getTitle);
                break;
            case 3:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData3.values()).map(EtacsCustomData.EtacsCustomData3::getTitle);
                break;
            case 4:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData4.values()).map(EtacsCustomData.EtacsCustomData4::getTitle);
                break;
            case 5:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData5.values()).map(EtacsCustomData.EtacsCustomData5::getTitle);
                break;
            case 6:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData6.values()).map(EtacsCustomData.EtacsCustomData6::getTitle);
                break;
            case 7:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData7.values()).map(EtacsCustomData.EtacsCustomData7::getTitle);
                break;
            case 8:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData8.values()).map(EtacsCustomData.EtacsCustomData8::getTitle);
                break;
            case 9:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData9.values()).map(EtacsCustomData.EtacsCustomData9::getTitle);
                break;
            case 10:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData10.values()).map(EtacsCustomData.EtacsCustomData10::getTitle);
                break;
            case 11:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData11.values()).map(EtacsCustomData.EtacsCustomData11::getTitle);
                break;
            case 12:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData12.values()).map(EtacsCustomData.EtacsCustomData12::getTitle);
                break;
            case 13:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData13.values()).map(EtacsCustomData.EtacsCustomData13::getTitle);
                break;
            case 14:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData14.values()).map(EtacsCustomData.EtacsCustomData14::getTitle);
                break;
            case 15:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData15.values()).map(EtacsCustomData.EtacsCustomData15::getTitle);
                break;
            case 16:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData16.values()).map(EtacsCustomData.EtacsCustomData16::getTitle);
                break;
            case 17:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData17.values()).map(EtacsCustomData.EtacsCustomData17::getTitle);
                break;
            case 18:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData18.values()).map(EtacsCustomData.EtacsCustomData18::getTitle);
                break;
            case 19:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData19.values()).map(EtacsCustomData.EtacsCustomData19::getTitle);
                break;
            case 20:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData20.values()).map(EtacsCustomData.EtacsCustomData20::getTitle);
                break;
            case 21:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData21.values()).map(EtacsCustomData.EtacsCustomData21::getTitle);
                break;
            case 22:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData22.values()).map(EtacsCustomData.EtacsCustomData22::getTitle);
                break;
            case 23:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData23.values()).map(EtacsCustomData.EtacsCustomData23::getTitle);
                break;
            case 24:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData24.values()).map(EtacsCustomData.EtacsCustomData24::getTitle);
                break;
            case 25:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData25.values()).map(EtacsCustomData.EtacsCustomData25::getTitle);
                break;
            case 26:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData26.values()).map(EtacsCustomData.EtacsCustomData26::getTitle);
                break;
            case 27:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData27.values()).map(EtacsCustomData.EtacsCustomData27::getTitle);
                break;
            case 28:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData28.values()).map(EtacsCustomData.EtacsCustomData28::getTitle);
                break;
            case 29:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData29.values()).map(EtacsCustomData.EtacsCustomData29::getTitle);
                break;
            case 30:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData30.values()).map(EtacsCustomData.EtacsCustomData30::getTitle);
                break;
            case 31:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData31.values()).map(EtacsCustomData.EtacsCustomData31::getTitle);
                break;
            case 32:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData32.values()).map(EtacsCustomData.EtacsCustomData32::getTitle);
                break;
            case 33:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData33.values()).map(EtacsCustomData.EtacsCustomData33::getTitle);
                break;
            case 34:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData34.values()).map(EtacsCustomData.EtacsCustomData34::getTitle);
                break;
            case 35:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData35.values()).map(EtacsCustomData.EtacsCustomData35::getTitle);
                break;
            case 36:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData36.values()).map(EtacsCustomData.EtacsCustomData36::getTitle);
                break;
            case 37:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData37.values()).map(EtacsCustomData.EtacsCustomData37::getTitle);
                break;
            case 38:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData38.values()).map(EtacsCustomData.EtacsCustomData38::getTitle);
                break;
            case 39:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData39.values()).map(EtacsCustomData.EtacsCustomData39::getTitle);
                break;
            case 40:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData40.values()).map(EtacsCustomData.EtacsCustomData40::getTitle);
                break;
            case 41:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData41.values()).map(EtacsCustomData.EtacsCustomData41::getTitle);
                break;
            case 42:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData42.values()).map(EtacsCustomData.EtacsCustomData42::getTitle);
                break;
            case 43:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData43.values()).map(EtacsCustomData.EtacsCustomData43::getTitle);
                break;
            case 44:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData44.values()).map(EtacsCustomData.EtacsCustomData44::getTitle);
                break;
            case 45:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData45.values()).map(EtacsCustomData.EtacsCustomData45::getTitle);
                break;
            default: stream = Stream.of("unknown");
        }
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }

    public static ArrayList<Integer> getAvailableValues(int id) {
        Stream<Integer> stream;

        switch (id) {
            case 1:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData1.values()).map(EtacsCustomData.EtacsCustomData1::getIdx);
                break;
            case 2:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData2.values()).map(EtacsCustomData.EtacsCustomData2::getIdx);
                break;
            case 3:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData3.values()).map(EtacsCustomData.EtacsCustomData3::getIdx);
                break;
            case 4:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData4.values()).map(EtacsCustomData.EtacsCustomData4::getIdx);
                break;
            case 5:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData5.values()).map(EtacsCustomData.EtacsCustomData5::getIdx);
                break;
            case 6:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData6.values()).map(EtacsCustomData.EtacsCustomData6::getIdx);
                break;
            case 7:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData7.values()).map(EtacsCustomData.EtacsCustomData7::getIdx);
                break;
            case 8:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData8.values()).map(EtacsCustomData.EtacsCustomData8::getIdx);
                break;
            case 9:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData9.values()).map(EtacsCustomData.EtacsCustomData9::getIdx);
                break;
            case 10:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData10.values()).map(EtacsCustomData.EtacsCustomData10::getIdx);
                break;
            case 11:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData11.values()).map(EtacsCustomData.EtacsCustomData11::getIdx);
                break;
            case 12:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData12.values()).map(EtacsCustomData.EtacsCustomData12::getIdx);
                break;
            case 13:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData13.values()).map(EtacsCustomData.EtacsCustomData13::getIdx);
                break;
            case 14:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData14.values()).map(EtacsCustomData.EtacsCustomData14::getIdx);
                break;
            case 15:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData15.values()).map(EtacsCustomData.EtacsCustomData15::getIdx);
                break;
            case 16:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData16.values()).map(EtacsCustomData.EtacsCustomData16::getIdx);
                break;
            case 17:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData17.values()).map(EtacsCustomData.EtacsCustomData17::getIdx);
                break;
            case 18:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData18.values()).map(EtacsCustomData.EtacsCustomData18::getIdx);
                break;
            case 19:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData19.values()).map(EtacsCustomData.EtacsCustomData19::getIdx);
                break;
            case 20:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData20.values()).map(EtacsCustomData.EtacsCustomData20::getIdx);
                break;
            case 21:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData21.values()).map(EtacsCustomData.EtacsCustomData21::getIdx);
                break;
            case 22:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData22.values()).map(EtacsCustomData.EtacsCustomData22::getIdx);
                break;
            case 23:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData23.values()).map(EtacsCustomData.EtacsCustomData23::getIdx);
                break;
            case 24:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData24.values()).map(EtacsCustomData.EtacsCustomData24::getIdx);
                break;
            case 25:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData25.values()).map(EtacsCustomData.EtacsCustomData25::getIdx);
                break;
            case 26:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData26.values()).map(EtacsCustomData.EtacsCustomData26::getIdx);
                break;
            case 27:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData27.values()).map(EtacsCustomData.EtacsCustomData27::getIdx);
                break;
            case 28:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData28.values()).map(EtacsCustomData.EtacsCustomData28::getIdx);
                break;
            case 29:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData29.values()).map(EtacsCustomData.EtacsCustomData29::getIdx);
                break;
            case 30:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData30.values()).map(EtacsCustomData.EtacsCustomData30::getIdx);
                break;
            case 31:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData31.values()).map(EtacsCustomData.EtacsCustomData31::getIdx);
                break;
            case 32:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData32.values()).map(EtacsCustomData.EtacsCustomData32::getIdx);
                break;
            case 33:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData33.values()).map(EtacsCustomData.EtacsCustomData33::getIdx);
                break;
            case 34:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData34.values()).map(EtacsCustomData.EtacsCustomData34::getIdx);
                break;
            case 35:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData35.values()).map(EtacsCustomData.EtacsCustomData35::getIdx);
                break;
            case 36:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData36.values()).map(EtacsCustomData.EtacsCustomData36::getIdx);
                break;
            case 37:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData37.values()).map(EtacsCustomData.EtacsCustomData37::getIdx);
                break;
            case 38:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData38.values()).map(EtacsCustomData.EtacsCustomData38::getIdx);
                break;
            case 39:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData39.values()).map(EtacsCustomData.EtacsCustomData39::getIdx);
                break;
            case 40:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData40.values()).map(EtacsCustomData.EtacsCustomData40::getIdx);
                break;
            case 41:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData41.values()).map(EtacsCustomData.EtacsCustomData41::getIdx);
                break;
            case 42:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData42.values()).map(EtacsCustomData.EtacsCustomData42::getIdx);
                break;
            case 43:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData43.values()).map(EtacsCustomData.EtacsCustomData43::getIdx);
                break;
            case 44:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData44.values()).map(EtacsCustomData.EtacsCustomData44::getIdx);
                break;
            case 45:
                stream = Arrays.stream(EtacsCustomData.EtacsCustomData45.values()).map(EtacsCustomData.EtacsCustomData45::getIdx);
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

    public String getSummary() {
        return summary;
    }
}
