package ru.d51x.kaierutils.etacs;


public class EtacsCustomData {
    public enum EtacsCustomData1 {
        acc_or_ig1(0,"ACC or IG1"),
        ig1(1, "IG1"),
        cannot_select(0x0F, "Cannot change");

        private int idx;
        private String title;
        EtacsCustomData1(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }

        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }
    public enum EtacsCustomData2 {
        coding_disable(0, "Disabled"),
        coding_enable(1, "Enable"),
        cannot_select(0x0F, "Cannot change");
        private int idx;
        private String title;
        EtacsCustomData2(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }
    public enum EtacsCustomData3 {
        lock_1_unlock_2(0, "Lock:1, Unlock:2" ),
        lock_1_unlock_0(1, "Lock:1, Unlock:0"),
        lock_0_unlock_2(2, "Lock:0, Unlock:2"),
        lock_2_unlock_1(3, "Lock:2, Unlock:1"),
        lock_0_unlock_1(4, "Lock:0, Unlock:1"),
        lock_2_unlock_0(5, "Lock:2, Unlock:0"),
        lock_0_unlock_0(6,"Lock:0, Unlock:0"),
        cannot_select(0x0F, "Cannot change");
        private int idx;
        private String title;
        EtacsCustomData3(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }
    public enum EtacsCustomData4 {
        normal_int(0, "Normal INT"),
        variable_int(1, "Variable INT"),
        speed_sensitive(2, "Speed Sensitive"),
        rain_sensitive(3, "Rain Sensitive"),
        cannot_select(0x0F, "Cannot change" );
        private int idx;
        private String title;
        EtacsCustomData4(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData5 {
        only_washer(0, "Only Washer"),
        washer_wiper(1,"Washer & Wiper"),
        with_after_wipe(2, "With after wipe"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData5(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData6 {
        time_sec_0(0, "0 sec"),
        time_sec_4(1, "4 sec"),
        time_sec_8(2, "8 sec"),
        time_sec_16(2, "16 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData6(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData7 {
        disabled(0, "Disabled"),
        enabled(1, "Enable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData7(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData8 {
        not_auto(0, "Not Auto"),
        open_by_vehicle_speed(1, "Open By Vehicle Speed"),
        open_close_by_ig(2, "Open/Close by IG"),
        open_close_by_keyless(3, "Open/Close by Keyless"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData8(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData9 {
        level1_bright(0, "Level1 bright"),
        level2_bright(1, "Level2 bright"),
        level3(2,"Level3"),
        level4_dark(3,"Level4 dark"),
        level5_dark(4,"Level5 dark"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData9(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData10 {
        disable(0, "Disabled"),
        enabled(1, "Enable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData10(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData11 {
        time_sec_0(0, "0 sec"),
        time_sec_7_5(1, "7.5 sec"),
        time_sec_15(2, "15 sec"),
        time_sec_30(3, "30 sec"),
        time_sec_60(4, "60 sec"),
        time_sec_120(5, "120 sec"),
        time_sec_180(6, "180 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData11(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData12 {
        disable(0, "Disable"),
        enable_a_spec(1, "Enable (A-spec.)"),
        enable_b_spec(2, "Enable (B-spec.)"),
        enable_c_spec(3, "Enable (C-spec.)"),
        enable_d_spec(4, "Enable (D-spec.)"),
        enable_e_spec(5, "Enable (E-spec.)"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData12(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData13 {
        disable(0, "Disable"),
        time_min_3(1, "3 min"),
        time_min_30(2, "30 min"),
        time_min_60(3, "60 min"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData13(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData14 {
        disable(0, "Disable"),
        relock(1, "Relock"),
        not_relock(2, "Not relock"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData14(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData15 {
        disable(0, "Disable"),
        always_p(1, "Always (P pos)"),
        p_w_unlock_p(2, "P/W unlock (P)"),
        always_lock(3, "Always (Lock pos)"),
        p_w_unlock_lock(4, "P/W unlock (Lock)"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData15(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData16 {
        all_door_unlock(0, "All Doors Unlock"),
        dr_door_unlock(1, "Dr door Unlock"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData16(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData17 {
        enable(0, "Enable"),
        disable(1, "Disable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData17(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData18 {
        twice(0, "Twice"),
        once(1, "Once"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData18(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData19 {
        not_sound_horn(0, "Not sound horn"),
        lock_any_time(1, "Lock any time"),
        lock_auto_on(2, "Lock/auto ON"),
        w_lock_any_time(3, "W lock any time"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData19(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData20 {
        not_sound_buzzer(0, "Not sound buzzer"),
        at_kos(1, "At KOS"),
        at_keyless(2, "At keyless"),
        at_both(3, "At Both"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData20(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData21 {
        time_sec_30(0, "30 sec"),
        time_sec_60(1, "60 sec"),
        time_sec_120(2, "120 sec"),
        time_sec_180(3, "180 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData21(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData22 {
        disabled(0, "Disabled"),
        enable(1, "Enable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData22(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData23 {
        time_sec_0(0, "0 sec"),
        time_sec_30(1, "30 sec"),
        time_sec_180(2, "180 sec"),
        time_sec_600(3, "600 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData23(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData24 {
        time_sec_10(0, "10 sec"),
        time_sec_6(1, "6 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData24(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData25 {
        disable(0, "Disable"),
        custom25_1(1, "P/W:O&C, D/M:O&C"),
        custom25_2(2, "P/W:O&C"),
        custom25_3(3, "P/W:C, D/M:O&C"),
        custom25_4(4, "D/M:O&C"),
        custom25_5(5, "P/W:C"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData25(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData26 {
        disable(0, "Disable"),
        enable(1, "Enable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData26(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData27 {
        custom_short(0, "Short"),
        custom_long(1, "Long"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData27(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData28 {
        enable(0, "Enable"),
        disable(1, "Disable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData28(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData29 {
        both_enable(0, "Both enable"),
        doorEntry_enable(1, "DoorEntry enable"),
        eng_start_enable(2, "ENG start enable"),
        both_disable(3, "Both disable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData29(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData30 {
        time_sec_0(0, "0 sec"),
        time_sec_3(1, "3 sec"),
        time_sec_5(2, "5 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData30(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData31 {
        disable(0, "Disable"),
        enable(1, "Enable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData31(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData32 {
        enable(0, "Enable"),
        disable(1, "Disable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData32(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData33 {
        enable(0, "Enable"),
        disable(1, "Disable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData33(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData34 {
        disable(0, "Disable"),
        time_min_30(1, "30 min"),
        time_min_60(2, "60 min"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData34(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData35 {
        custom_normal(0, "Normal"),
        custom_long(1, "Long"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData35(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData36 {
        disable(0, "Disable"),
        enable(1, "Enable"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData36(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData37 {
        same_exterior_lights(0, "Same Exterior lights"),
        independent_normal(1, "Independent Normal"),
        independent_late(2, "Independent Late"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData37(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData38 {
        disable(0, "Disable"),
        time_sec_15(1, "15 sec"),
        time_sec_30(2, "30 sec"),
        time_sec_60(3, "60 sec"),
        time_sec_180(4, "180 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData38(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData39 {
        disable(0, "Disable"),
        lamp_small(1, "Small lamp"),
        lamp_head(2, "Head lamp"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData39(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData40 {
        custom_40_0(0, "Enable(R wip.ON)"),
        custom_40_1(1, "Enable(R/F wip.)"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData40(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData41 {
        custom_41_0(0, "Level 1"),
        custom_41_1(1, "Level 2"),
        custom_41_2(2, "Level 3"),
        custom_41_3(3, "Level 4"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData41(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData42 {
        custom_42_0(0, "Volume 1"),
        custom_42_1(1, "Volume 2"),
        custom_42_2(2, "Volume 3"),
        custom_42_3(3, "Volume auto"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData42(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData43 {
        custom_43_0(0, "No sound"),
        custom_43_1(1, "Sound 1"),
        custom_43_2(2, "Sound 2"),
        custom_43_3(3, "Sound 3"),
        custom_43_4(4, "Sound 4"),
        custom_43_5(5, "Sound 5"),
        custom_43_6(6, "Sound 6"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData43(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData44 {
        custom_44_0(0, "Disable"),
        custom_44_1(1, "Small 30 sec"),
        custom_44_2(2, "Small 60 sec"),
        custom_44_3(3, "Small 180 sec"),
        custom_44_4(4, "Head 30 sec"),
        custom_44_5(5, "Head 60 sec"),
        custom_44_6(6, "Head 180 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData44(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }

    public enum EtacsCustomData45 {
        custom_45_0(0, "Disable"),
        custom_45_1(1, "15 sec"),
        custom_45_2(2, "30 sec"),
        custom_45_3(3, "45 sec"),
        cannot_select(0x0F,"Cannot change");
        private int idx;
        private String title;

        EtacsCustomData45(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() {
            return idx;
        }

        public String getTitle() {
            return title;
        }
    }
}
