package ru.d51x.kaierutils.Data;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ru.d51x.kaierutils.App;
import ru.d51x.kaierutils.R;

public enum EtacsCustomCoding {


    ETACS_CUSTOM_001(1, 0, 4, 0, 0b00001111, "turn_power_source", "Turn power source"),
    ETACS_CUSTOM_002(2,  0, 4, 4, 0b11110000, "comfort_flasher", "Comfort flasher"),
    ETACS_CUSTOM_003(3,  1, 4, 0, 0b00001111, "hazard_answer_back", "Hazard answer back"),
    ETACS_CUSTOM_004(4,  1, 4, 4, 0b11110000, "front_wiper_operation", "Front wiper operation"),
    ETACS_CUSTOM_005(5,  2, 4, 0, 0b00001111, "front_rear_wiper_washer", "Front/rear wiper washer"),
    ETACS_CUSTOM_006(6,  2, 4, 4, 0b11110000, "Intermittent_time_of_rear_wiper", "Intermittent time of rear wiper"),
    ETACS_CUSTOM_007(7,  3, 4, 0, 0b00001111, "rear_wiper_low_speed_mode", "Rear wiper Low speed mode"),
    ETACS_CUSTOM_008(8,  3,4,4, 0b11110000, "auto_fold_mirror", "Auto fold mirror"),
    ETACS_CUSTOM_009(9, 4, 4, 0, 0b00001111, "sensitivity_for_auto_lamp", "Sensitivity for auto lamp"),
    ETACS_CUSTOM_010(10, 4, 4, 4, 0b11110000, "auto_lamp_linked_wiper", "Auto lamp linked wiper"),
    ETACS_CUSTOM_011(11, 5, 4, 0, 0b00001111, "room_lamp_delay_timer_with_door", "Room lamp delay timer with door"),
    ETACS_CUSTOM_012(12, 5, 4, 4, 0b11110000, "head_lamp_auto_cut_customize", "Head lamp auto cut customize"),
    ETACS_CUSTOM_013(13, 6, 4, 0, 0b00001111, "interior_lamp_auto_cut_timer", "Interior lamp auto cut timer"),
    ETACS_CUSTOM_014(14, 6, 4, 4, 0b11110000, "auto_door_lock_by_vehicle_speed", "Auto door lock by vehicle speed"),
    ETACS_CUSTOM_015(15, 7, 4, 4, 0b11110000, "auto_door_unlock", "Auto door unlock"),
    ETACS_CUSTOM_016(16, 8, 4, 0, 0b00001111, "door_unlock_mode", "Door unlock mode"),
    ETACS_CUSTOM_017(17, 8, 4, 4, 0b11110000, "power_door_unlock_by_knob", "Power Door Unlock by Knob"),
    ETACS_CUSTOM_018(18, 9, 4, 0, 0b00001111, "deadlock_button_operation", "Deadlock Button Operation"),
    ETACS_CUSTOM_019(19, 9, 4, 4, 0b11110000, "horn_chirp_by_keyless", "Horn chirp by keyless"),
    ETACS_CUSTOM_020(20, 10, 4, 0, 0b00001111, "buzzer_answer_back", "Buzzer answer back"),
    ETACS_CUSTOM_021(21, 10, 4, 4, 0b11110000, "timer_lock_timer", "Timer lock timer"),
    ETACS_CUSTOM_022(22, 11, 4, 0, 0b00001111, "alarm", "Alarm"),
    ETACS_CUSTOM_023(23, 11, 4, 4, 0b11110000, "power_window_key_off_timer", "Power window key off timer"),
    ETACS_CUSTOM_024(24, 12, 4, 0, 0b00001111, "duration_of_pre-alarm", "Duration of pre-alarm"),
    ETACS_CUSTOM_025(25, 12, 4, 4, 0b11110000, "multi_mode", "Multi mode"),
    ETACS_CUSTOM_026(26, 13, 4, 0, 0b00001111, "panic_alarm_switch", "Panic alarm switch"),
    ETACS_CUSTOM_027(27, 13, 4, 4, 0b11110000, "duration_of_horn_chirp", "Duration of horn chirp"),
    ETACS_CUSTOM_028(28, 14, 4, 4, 0b11110000, "kos_key_detect_out_from_window", "KOS key detect out from window"),
    ETACS_CUSTOM_029(29, 15, 4, 0, 0b00001111, "kos_feature", "KOS feature"),
    ETACS_CUSTOM_030(30, 15, 4, 4, 0b11110000, "kos_unlock_disable_time", "KOS unlock disable time"),
    ETACS_CUSTOM_031(31, 16, 4, 0, 0b00001111, "remote_eng_starter_answer_back", "Remote ENG starter answer back"),
    ETACS_CUSTOM_032(32, 16, 4, 4, 0b11110000, "p_w_down_operation_after_ig_off", "P/W down operation after IG OFF"),
    ETACS_CUSTOM_033(33, 17, 4, 0, 0b00001111, "pw_main_sw_during_pw_locked", "P/W main SW during P/W locked"),
    ETACS_CUSTOM_034(34, 17, 4, 4, 0b11110000, "acc_power_auto_cut", "ACC power auto cut"),
    ETACS_CUSTOM_035(35, 18, 4, 4, 0b11110000, "comfort_flasher_switch_time", "Comfort flasher switch time"),
    ETACS_CUSTOM_036(36, 19, 4, 4, 0b11110000, "intelligent_comfort_washer_custom", "Intelligent/Comfort washer custom"),
    ETACS_CUSTOM_037(37, 20, 4, 0, 0b00001111, "interior_illumination_control", "Interior illumination control"),
    ETACS_CUSTOM_038(38, 20, 4, 4, 0b11110000, "coming_home_light", "Coming home light"),
    ETACS_CUSTOM_039(39, 21, 4, 0, 0b00001111, "welcome_light", "Welcome light"),
    ETACS_CUSTOM_040(40, 22, 4, 0, 0b00001111, "rear_wiper_linked_reverse_gear", "Rear wiper(linked reverse gear)"),
    ETACS_CUSTOM_041(41, 22, 4, 4, 0b11110000, "sensitivity_for_security_sensor", "Sensitivity for security sensor"),
    ETACS_CUSTOM_042(42, 23, 4, 0, 0b00001111, "outer_buzzer_volume", "Outer buzzer volume"),
    ETACS_CUSTOM_043(43, 23, 4, 4, 0b11110000, "siren_answer_back", "Siren answer back"),
    ETACS_CUSTOM_044(44, 24, 4, 0, 0b00001111, "remote_light_on", "Remote Light ON"),
    ETACS_CUSTOM_045(45, 24, 4, 4, 0b11110000, "hl_ev_remote_car_finder", "H/L(EV REMOTE Car finder)"),
    //ETACS_CUSTOM_998(998, "", "", 0, 4, 0, 0, 0),
    ETACS_CUSTOM_UNKNOWN(999, 0, 0, 0, 0, "unknown", "unknown");
    private int idx;
    private String name;
    private String title;
    private int byteIdx;
    private int length;
    private int startBit;
    private int mask;

    EtacsCustomCoding(int idx, int byteIdx, int length,
                      int startBit, int mask, String name, String title) {
        this.idx = idx;
        this.name = name;
        this.title = title;
        this.byteIdx = byteIdx;
        this.length = length;
        this.startBit = startBit;
        this.mask = mask;
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
}
