package ru.d51x.kaierutils.etacs;

public class EtacsVariantData {

    public enum EtacsVariantData1 {
        etacs_variant_data1_0(0,"3H41"),
        etacs_variant_data1_1(1,"3H41EVO"),
        etacs_variant_data1_2(2,"3H44Z"),
        etacs_variant_data1_3(3,"3H44SX"),
        etacs_variant_data1_4(4,"3H45X"),
        etacs_variant_data1_5(5,"3H45W"),
        etacs_variant_data1_6(6,"3X45"),
        etacs_variant_data1_7(0x20,"3R00"),
        etacs_variant_data1_8(0x40,"3M00"),
        etacs_variant_data1_9(0x41,"4A00");

        private final int idx;
        private final String title;
        EtacsVariantData1(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }

        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData2 {
        model_year_06(0x0C,"06");
        // стартовое значение 0x0C = 12, делим на 2, получим 6 - 06 модельный год
        // следующее 0x0D = 13, делим на 2, получим 6.5 - 06.5 модельный год и т.д.
        private final int idx;
        private final String title;
        EtacsVariantData2(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }

        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData_NotPresent_Present {
        not_present(0, "Not Present" ),
        present(1, "Present");
        private final int idx;
        private final String title;
        EtacsVariantData_NotPresent_Present(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData4 {
        etacs_variant_data4_0(0, "DOM" ),
        etacs_variant_data4_1(1, "EXP - Экспорт"),
        etacs_variant_data4_2(2, "NAS - Австралия"),
        etacs_variant_data4_3(3, "EU - Европа"),
        etacs_variant_data4_4(4, "MMAL - Америка"),
        etacs_variant_data4_5(5, "GCC - Арабские Эмираты"),
        ;
        private final int idx;
        private final String title;
        EtacsVariantData4(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
// XXXXX
    public enum EtacsVariantData5 {
        etacs_variant_data_1(1, "5MT"),
        etacs_variant_data_2(2, "6MT"),
        etacs_variant_data_3(3, "CVT"),
        etacs_variant_data_4(4, "6AT"),
        etacs_variant_data_5(5, "TC-SST"),
        etacs_variant_data_6(6, "4AT"),
        ;
        private final int idx;
        private final String title;
        EtacsVariantData5(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
// XXXXX
    public enum EtacsVariantData6 {
        etacs_variant_data_1(1, "3.0L S4 MIVEC"),
        etacs_variant_data_2(2, "DIESEL 2.0L [BSY or BWC]"),
        etacs_variant_data_3(3, "1.8L MPI D4/S4"),
        etacs_variant_data_4(4, "2.0L MPI D4/S4"),
        etacs_variant_data_5(5, "2.4L D4 MPI VVT"),
        etacs_variant_data_6(6, "1.5L D4 MPI VVT"),
        etacs_variant_data_7(7, "2.0L D4 VVT T/C"),
        etacs_variant_data_8(8, "2.2L DI I/C T/C"),
        etacs_variant_data_9(9, "1.8L DI-DC MIVEC"),
        etacs_variant_data_10(0x0A, "2.2L DI-DC MIVEC"),
        etacs_variant_data_11(0x0B, "1.6L D4 MPI VVT"),
        etacs_variant_data_12(0x0C, "-"),
        etacs_variant_data_13(0x0D, "1.6L DI I/C T/C"),
        etacs_variant_data_14(0xFF, "--"),
        ;
        private final int idx;
        private final String title;
        EtacsVariantData6(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData7 {
        etacs_variant_data_1(0, "Normal"),
        etacs_variant_data_2(1, "Low Power"),
        etacs_variant_data_3(2, "High Power"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData7(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData8 {
        etacs_variant_data_1(0, "LHD (Левый руль)"),
        etacs_variant_data_2(1, "RHD (Правый руль)"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData8(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData9 {
        etacs_variant_data_1(0, "Undefined"),
        etacs_variant_data_2(1, "Type1"),
        etacs_variant_data_3(2, "Type2"),
        etacs_variant_data_4(3, "Type3"),
        etacs_variant_data_5(4, "Type4"),
        etacs_variant_data_6(5, "Type5"),
        etacs_variant_data_7(6, "Type6"),
        etacs_variant_data_8(7, "Type7"),
        etacs_variant_data_9(8, "Type8"),
        etacs_variant_data_10(9, "Type9"),
        etacs_variant_data_11(10, "Type10"),
        etacs_variant_data_12(11, "Type11"),
        etacs_variant_data_13(12, "Type12"),
        etacs_variant_data_14(13, "Type13"),
        etacs_variant_data_15(14, "Type14"),
        etacs_variant_data_16(15, "Type15"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData9(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

// XXXXXX
    public enum EtacsVariantData11 {
        etacs_variant_data_1(1, "Front Drive" ),
        etacs_variant_data_2(2, "Rear Drive"),
        etacs_variant_data_3(3, "4WD FF Base"),
        etacs_variant_data_4(4, "4WD FR Base"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData11(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData12 {
        etacs_variant_data_1(1, "2WD" ),
        etacs_variant_data_2(2, "ECC - электронная муфтв"),
        etacs_variant_data_3(3, "Center Diff + VCU"),
        etacs_variant_data_4(4, "ACD"),
        etacs_variant_data_5(5, "SS4-3"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData12(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData_Disable_Enable {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData_Disable_Enable(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData14 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (Twice)"),
        etacs_variant_data_2(2, "Enable (Once)"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData14(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData15 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (default D)"),
        etacs_variant_data_2(2, "Enable (default E)"),        ;
        private final int idx;
        private final String title;
        EtacsVariantData15(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    // длина окружности шин - 2 байта
    public enum EtacsVariantData16 {
        etacs_variant_data_0(0, "Disable" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData16(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData17 {
        etacs_variant_data_0(0, "Not used" ),
        etacs_variant_data_1(0x13, "55L" ),
        etacs_variant_data_2(0x15, "57L" ),
        etacs_variant_data_3(0x16, "58L" ),
        etacs_variant_data_4(0x17, "59L" ),
        etacs_variant_data_5(0x18, "60L" ),
        etacs_variant_data_6(0x1A, "62L" ),
        etacs_variant_data_7(0x1B, "63L" ),
        etacs_variant_data_8(0x1E, "66L" ),
        etacs_variant_data_9(0x22, "70L" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData17(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData18 {
        etacs_variant_data_0(0, "DRL not present" ),
        etacs_variant_data_1(1, "Normal DRL present" ),
        etacs_variant_data_2(2, "Dimming DRL present" ),
        etacs_variant_data_3(4, "Independent DRL present" ),
        etacs_variant_data_4(5, "Dimming DRL with P (Parking)" ),
        etacs_variant_data_5(6, "Independent DRL with P (Parking)" ),
        etacs_variant_data_6(7, "Normal DRL with P (Parking)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData18(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData19 {
        etacs_variant_data_0(0, "Not Present" ),
        etacs_variant_data_1(1, "Present (Type A)" ),
        etacs_variant_data_2(2, "Present (Type B)" ),
        etacs_variant_data_3(3, "Present (Type C)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData19(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData23 {
        etacs_variant_data_0(0, "Not Present" ),
        etacs_variant_data_1(1, "Present (Type A)" ),
        etacs_variant_data_2(2, "Present (Type B)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData23(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData28 {
        etacs_variant_data_0(0, "km/h" ),
        etacs_variant_data_1(1, "mph" ),
        ;
        private final int idx;
        private final String title;
        EtacsVariantData28(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData31 {
        etacs_variant_data_0(0, "Not Present" ),
        etacs_variant_data_1(1, "Present A" ),
        etacs_variant_data_2(2, "Present B" ),
        etacs_variant_data_3(3, "Present C" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData31(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData34 {
        etacs_variant_data_0(0, "Default disable" ),
        etacs_variant_data_1(1, "Default enable" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData34(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData35 {
        etacs_variant_data_0(0, "Premium" ),
        etacs_variant_data_1(1, "1 speaker" ),
        etacs_variant_data_2(2, "2 speaker" ),
        etacs_variant_data_3(3, "3 speaker" ),
        etacs_variant_data_4(4, "4 speaker" ),
        etacs_variant_data_5(5, "5 speaker" ),
        etacs_variant_data_6(6, "6 speaker" ),
        etacs_variant_data_7(7, "7 speaker" ),
        etacs_variant_data_8(8, "8 speaker" ),
        etacs_variant_data_9(9, "9 speaker" ),
        etacs_variant_data_10(10, "10 speaker" ),
        etacs_variant_data_11(11, "11 speaker" ),
        etacs_variant_data_12(12, "12 speaker" ),
        etacs_variant_data_13(13, "13 speaker" ),
        etacs_variant_data_14(14, "14 speaker" ),
        etacs_variant_data_15(15, "15 speaker" ),
        ;
        private final int idx;
        private final String title;
        EtacsVariantData35(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData36 {
        etacs_variant_data_0(0, "Fabric" ),
        etacs_variant_data_1(1, "Leather" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData36(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData37 {
        etacs_variant_data_0(0, "Not present (low threshold) (can change)" ),
        etacs_variant_data_1(1, "Not present (high threshold) (cannot change)" ),
        etacs_variant_data_2(2, "Low threshold (analog) (can change)" ),
        etacs_variant_data_3(3, "Low threshold (RLS) (cannot change)" ),
        etacs_variant_data_4(4, "High threshold (analog) (cannot change)" ),
        etacs_variant_data_54(5, "High threshold (RLS) (cannot change)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData37(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData38 {
        etacs_variant_data_0(0, "Undefined" ),
        etacs_variant_data_1(1, "Open - открытый дифференциал (обычный)" ),
        etacs_variant_data_2(2, "Helical - самоблокирующийся дифференциал" ),
        etacs_variant_data_3(3, "ELSD - электронный дифференциал повышенного трения" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData38(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData39 {
        etacs_variant_data_0(0, "Undefined" ),
        etacs_variant_data_1(1, "Open - открытый дифференциал (обычный)" ),
        etacs_variant_data_2(2, "AYC – Активный" ),
        etacs_variant_data_3(3, "LSD LOM" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData39(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData40 {
        etacs_variant_data_0(0, "Not Present" ),
        etacs_variant_data_1(1, "Type P1" ),
        etacs_variant_data_2(2, "Type P2" ),
        etacs_variant_data_3(3, "Type P3" ),
        etacs_variant_data_4(4, "Type P4" ),
        etacs_variant_data_5(5, "Type P3 MMAL" ),
        etacs_variant_data_6(6, "Type P4 MMAL" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData40(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData41 {
        etacs_variant_data_0(0, "Not Present" ),
        etacs_variant_data_1(1, "Type S1" ),
        etacs_variant_data_2(2, "Type S2" ),
        etacs_variant_data_3(3, "Type S3" ),
        etacs_variant_data_4(4, "Type S4" ),
        etacs_variant_data_5(5, "Type S3 MMAL" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData41(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData48 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (default D)" ),
        etacs_variant_data_2(2, "Enable (default E)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData48(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData57 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (default D)" ),
        etacs_variant_data_2(2, "Enable (default E)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData57(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData58 {
        etacs_variant_data_0(0, "Type1/No present" ),
        etacs_variant_data_1(1, "Type 2" ),
        etacs_variant_data_2(2, "Type 3" ),
        etacs_variant_data_3(2, "Type 4" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData58(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData59 {
        etacs_variant_data_0(0, "Conventional or Not Present" ),
        etacs_variant_data_1(1, "With Lo control - с управлением на низкой скорости" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData59(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData60 {
        etacs_variant_data_0(0, "Touch sensor & request sw" ),
        etacs_variant_data_1(1, "Request sw" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData60(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData61 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (default RR only) - активирован (по умолчанию при движении только назад)" ),
        etacs_variant_data_2(2, "Enable (default FR & RR) - активирован (по умолчанию при движении вперед и назад)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData61(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData64 {
        etacs_variant_data_0(0, "Open By Vehicle Speed ot Not Present" ),
        etacs_variant_data_1(1, "Keyless/KOS " ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData64(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData69 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (default D)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData69(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData73 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (default D)" ),
        etacs_variant_data_2(2, "Enable (default E)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData73(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData74 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Enable (default D)" ),
        etacs_variant_data_2(2, "Enable small" ),
        etacs_variant_data_3(3, "Enable head" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData74(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData83 {
        etacs_variant_data_0(0, "Disabled" ),
        etacs_variant_data_1(1, "Without getting off operation" ),
        etacs_variant_data_2(2, "With getting off operation" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData83(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData84 {
        etacs_variant_data_0(0, "21W plus 21W plus 5W" ),
        etacs_variant_data_1(1, "21W plus 16W plus 5W" ),
        etacs_variant_data_2(2, "21W plus 21W or 21W plus 21W plus 0.36W" ),
        etacs_variant_data_3(3, "21W plus 16W or 21W plus 16W plus 0.36W" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData84(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData87 {
        etacs_variant_data_0(0, "2 beam" ),
        etacs_variant_data_1(1, "4 beam" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData87(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }


    public enum EtacsVariantData_Enable_Disable {
        etacs_variant_data_0(0, "Enable" ),
        etacs_variant_data_1(1, "Disable" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData_Enable_Disable(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }


    public enum EtacsVariantData89 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "Pop up control 1" ),
        etacs_variant_data_2(2, "Pop up control1 with washer" ),
        etacs_variant_data_3(3, "Non pop up control 1" ),
        etacs_variant_data_4(4, "Non pop up control 1 with washer" ),
        etacs_variant_data_5(5, "Pop up control 2" ),
        etacs_variant_data_6(6, "Pop up control 2 with washer" ),
        etacs_variant_data_7(7, "Non pop up control 2" ),
        etacs_variant_data_8(8, "Non pop up control 2 with washer" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData89(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData90 {
        etacs_variant_data_0(0, "A-spec" ),
        etacs_variant_data_1(1, "B-spec" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData90(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData92 {
        etacs_variant_data_0(0, "Not Present (can change)" ),
        etacs_variant_data_1(1, "Present (can change)" ),
        etacs_variant_data_2(2, "Present (cannot change)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData92(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData93 {
        etacs_variant_data_0(0, "Short" ),
        etacs_variant_data_1(1, "Long" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData93(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData94 {
        etacs_variant_data_0(0, "Disabled (вручную)" ),
        etacs_variant_data_1(1, "In dimming (плавно)" ),
        etacs_variant_data_2(2, "Full (сразу)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData94(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData95 {
        etacs_variant_data_0(0, "Mode1 (trunk) - дверь" ),
        etacs_variant_data_1(1, "Mode2 (cargo) - в багажнике" ),
        etacs_variant_data_2(2, "Mode3 (cabin) - в кабине" ),
        etacs_variant_data_3(3, "Mode4 (side step lamp) - боковая лампа" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData95(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData96 {
        etacs_variant_data_0(0, "E-spec" ),
        etacs_variant_data_1(1, "B-spec" ),
        etacs_variant_data_2(2, "C-spec" ),
        etacs_variant_data_3(3, "D-spec" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData96(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData98 {
        etacs_variant_data_0(0, "Not Present" ),
        etacs_variant_data_1(1, "A-spec. (for NAS)" ),
        etacs_variant_data_2(2, "B-spec. (for except NAS high line)" ),
        etacs_variant_data_3(3, "C-spec. (for except NAS low line)" ),
        etacs_variant_data_4(4, "D-spec. (for dead lock)" ),
        etacs_variant_data_5(5, "E-spec. (for dead lock high line)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData98(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData99 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "crash unlock" ),
        etacs_variant_data_2(2, "crash unlock + speed lock" ),
        etacs_variant_data_3(3, "Speed lock" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData99(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData100 {
        etacs_variant_data_0(0, "Disable" ),
        etacs_variant_data_1(1, "A-spec. (Dr only)" ),
        etacs_variant_data_2(2, "B-spec. (Dr and As)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData100(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData101 {
        etacs_variant_data_0(0, "Single horn" ),
        etacs_variant_data_1(1, "Dual horn" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData101(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData103 {
        etacs_variant_data_0(0, "Relay control" ),
        etacs_variant_data_1(1, "PWM control" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData103(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData104 {
        etacs_variant_data_0(0, "Not Present" ),
        etacs_variant_data_1(1, "A-spec. (DOM)" ),
        etacs_variant_data_2(2, "B-spec. (EU)" ),
        etacs_variant_data_3(3, "C-spec. (NAS)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData104(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData105 {
        etacs_variant_data_0(0, "Not present (can change)" ),
        etacs_variant_data_1(1, "Not present (cannot change)" ),
        etacs_variant_data_2(2, "Present (can change)" ),
        etacs_variant_data_3(3, " Present (cannot change)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData105(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData_Present_NotPresent {
        etacs_variant_data_0(0, "Present" ),
        etacs_variant_data_1(1, "Not present" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData_Present_NotPresent(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData108 {
        etacs_variant_data_0(0, "gate type - открывание в бок" ),
        etacs_variant_data_1(1, "trunk type - открывание в верх" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData108(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData109 {
        etacs_variant_data_0(0, "Not present (cannot change)" ),
        etacs_variant_data_1(1, "Not present (can change)" ),
        etacs_variant_data_2(2, "Present (can change))" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData109(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData110 {
        etacs_variant_data_0(0, "Not present (cannot change)" ),
        etacs_variant_data_1(1, "Not present (can change)" ),
        etacs_variant_data_2(2, "Present (can change))" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData110(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData113 {
        etacs_variant_data_0(0, "Normal INT" ),
        etacs_variant_data_1(1, "Variable INT" ),
        etacs_variant_data_2(2, "Speed Sensitive" ),
        etacs_variant_data_3(3, "Rain Sensitive" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData113(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData114 {
        etacs_variant_data_0(0, "Not present (cannot change)" ),
        etacs_variant_data_1(1, "Not present (can change)" ),
        etacs_variant_data_2(2, "Present (can change)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData114(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData117 {
        etacs_variant_data_0(0, "Undefined" ),
        etacs_variant_data_1(1, "AM reception type MW/LW" ),
        etacs_variant_data_2(2, "AM reception type 1kHz Step" ),
        etacs_variant_data_3(3, "Other type" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData117(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData118 { //Not present or 2 height sensor
        etacs_variant_data_0(0, "Not present" ),
        etacs_variant_data_1(1, "Communication less and static type" ),
        etacs_variant_data_2(2, "Communication less and dynamic type" ),
        etacs_variant_data_3(3, "CAN communication and static type" ),
        etacs_variant_data_4(4, "CAN communication and dynamic type" ),
        etacs_variant_data_7(7, "SNA" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData118(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData119 {
        etacs_variant_data_0(0, "Not present" ),
        etacs_variant_data_1(1, "Swivel RHD type" ),
        etacs_variant_data_2(2, "Swivel LHD type" ),
        etacs_variant_data_3(3, "Fixed bending lamp type" ),
        etacs_variant_data_4(4, "Cornering lamp type" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData119(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData121 {
        etacs_variant_data_0(0, "No Compressor" ),
        etacs_variant_data_1(1, "Scroll type 60cc/rev" ),
        etacs_variant_data_2(2, "Scroll type 90cc/rev" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData121(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData122 {
        etacs_variant_data_0(0, "Celecious" ),
        etacs_variant_data_1(1, "Fahrenheit" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData122(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData_NotAvailable_Available {
        etacs_variant_data_0(0, "Not available" ),
        etacs_variant_data_1(1, "Available" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData_NotAvailable_Available(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData127 {
        etacs_variant_data_0(0, "No request" ),
        etacs_variant_data_1(1, "Japanese" ),
        etacs_variant_data_2(2, "English" ),
        etacs_variant_data_3(3, "French" ),
        etacs_variant_data_4(4, "Spanish" ),
        etacs_variant_data_5(5, "German" ),
        etacs_variant_data_6(6, "Portuguese" ),
        etacs_variant_data_7(7, "Dutch" ),
        etacs_variant_data_8(8, "Italian" ),
        etacs_variant_data_9(9, "Swedish" ),
        etacs_variant_data_10(10, "Danish" ),
        etacs_variant_data_11(11, "Russian" ),
        etacs_variant_data_12(12, "Chinese" ),
        etacs_variant_data_13(13, "Arabic" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData127(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData128 {
        etacs_variant_data_0(0, "Litter" ),
        etacs_variant_data_1(1, "US gallon" ),
        etacs_variant_data_2(2, "UK gallon" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData128(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData129 {
        etacs_variant_data_0(0, "km/L" ),
        etacs_variant_data_1(1, "L/100km" ),
        etacs_variant_data_2(2, "MPG (US)" ),
        etacs_variant_data_3(3, "MPG (UK)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData129(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData130 {
        etacs_variant_data_0(0, "DOM / EU" ),
        etacs_variant_data_1(1, "NAS (Except EU)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData130(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData131 {
        etacs_variant_data_0(0, "Normal" ),
        etacs_variant_data_1(1, "Hot" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData131(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData132 {
        etacs_variant_data_0(0, "Threshold for Jpn" ),
        etacs_variant_data_1(1, "Threshold for EU" ),
        etacs_variant_data_2(2, "Threshold for Nas" ),
        etacs_variant_data_3(3, "Threshold for Aus" ),
        etacs_variant_data_4(4, "Threshold for Gcc" ),
        etacs_variant_data_5(5, "Threshold for Exp" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData132(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData143 {
        etacs_variant_data_0(0, "Type 0" ),
        etacs_variant_data_1(1, "Type 1" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData143(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData145 {
        etacs_variant_data_0(0, "DRV and PSG indicator independent" ),
        etacs_variant_data_1(1, "DRV and PSG indicator integrate" ),
        etacs_variant_data_2(2, "Indicator not available" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData145(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData152 {
        etacs_variant_data_0(0, "Small" ),
        etacs_variant_data_1(1, "Normal" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData152(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData153 {
        etacs_variant_data_0(0, "The function is not available" ),
        etacs_variant_data_1(1, "Japan 10" ),
        etacs_variant_data_2(2, "Japan 11" ),
        etacs_variant_data_3(3, "Japan 20" ),
        etacs_variant_data_4(4, "Japan 30" ),
        etacs_variant_data_5(5, "Japan 31" ),
        etacs_variant_data_6(6, "Japan 40" ),
        etacs_variant_data_7(7, "Reserved 1" ),
        etacs_variant_data_8(8, "GCC_EXP 10" ),
        etacs_variant_data_9(9, "AUS" ),
        etacs_variant_data_10(10, "Reserved 2" ),
        etacs_variant_data_11(11, "NAS 10 - 12.000 km" ),
        etacs_variant_data_12(12, "NAS 11 - 6.000 km" ),
        etacs_variant_data_13(13, "NAS 20 - 8.000 km" ),
        etacs_variant_data_14(14, "NAS 21 - 6.000 km" ),
        etacs_variant_data_15(15, "Reserved 3" ),
        etacs_variant_data_16(16, "EU 10" ),
        etacs_variant_data_17(17, "EU 11" ),
        etacs_variant_data_18(18, "Optional Schedule" ),
        etacs_variant_data_19(19, "EU 20" ),
        etacs_variant_data_20(20, "GCC_EXP 20" ),
        etacs_variant_data_21(0x1F, "Function Off" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData153(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData155 {
        etacs_variant_data_0(0, "N/A" ),
        etacs_variant_data_1(1, "190 kPa" ),
        etacs_variant_data_2(2, "200 kPa" ),
        etacs_variant_data_3(3, "210 kPa" ),
        etacs_variant_data_4(4, "220 kPa" ),
        etacs_variant_data_5(5, "230 kPa" ),
        etacs_variant_data_6(6, "240 kPa" ),
        etacs_variant_data_7(7, "250 kPa" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData155(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData156 {
        etacs_variant_data_0(0, "Not present (cannot change)" ),
        etacs_variant_data_1(1, "Not present (can change)" ),
        etacs_variant_data_2(2, "Present (can change)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData156(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData163 {
        etacs_variant_data_0(0, "Not opening display" ),
        etacs_variant_data_1(1, "Opening for MMC" ),
        etacs_variant_data_2(2, "Opening for OEM1" ),
        etacs_variant_data_3(3, "Opening for OEM2" ),
        etacs_variant_data_4(4, "Opening for Reserved" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData163(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData165 {
        etacs_variant_data_0(0, "Present (cannot change)" ),
        etacs_variant_data_1(1, "Not present (cannot change)" ),
        etacs_variant_data_2(2, "Present (can change)" ),
        etacs_variant_data_3(3, "Not present (can change)" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData165(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData172 {
        etacs_variant_data_0(0, "TYPE1" ),
        etacs_variant_data_1(1, "TYPE2" ),
        etacs_variant_data_2(2, "TYPE3" ),
        etacs_variant_data_3(3, "TYPE4" ),
        etacs_variant_data_4(4, "TYPE5" ),
        etacs_variant_data_5(5, "TYPE6" ),
        etacs_variant_data_6(6, "TYPE7" ),
        etacs_variant_data_7(7, "TYPE8" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData172(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData173 {
        etacs_variant_data_0(0, "TYPE1 (Clear)" ),
        etacs_variant_data_1(1, "TYPE2 (Green)" ),
        etacs_variant_data_2(2, "TYPE3" ),
        etacs_variant_data_3(3, "TYPE4" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData173(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

    public enum EtacsVariantData175 {
        etacs_variant_data_0(0, "TYPE1" ),
        etacs_variant_data_1(1, "TYPE2" ),
        etacs_variant_data_2(2, "TYPE3" ),
        etacs_variant_data_3(3, "TYPE4" ),
        etacs_variant_data_4(4, "TYPE5" ),
        etacs_variant_data_5(5, "TYPE6" ),
        etacs_variant_data_6(6, "TYPE7" ),
        etacs_variant_data_7(7, "TYPE8" ),
        etacs_variant_data_8(8, "SNA" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData175(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData176 {
        etacs_variant_data_0(0, "Not Present or Type1" ),
        etacs_variant_data_1(1, "Type2" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData176(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }
    public enum EtacsVariantData177 {
        etacs_variant_data_0(0, "Undefined" ),
        etacs_variant_data_1(1, "Dial/Seesaw type" ),
        etacs_variant_data_2(2, "Push type" ),
        etacs_variant_data_3(3, "SNA" ),        ;
        private final int idx;
        private final String title;
        EtacsVariantData177(int idx, String title) {
            this.idx = idx;
            this.title = title;
        }
        public int getIdx() { return idx; }
        public String getTitle() { return title; }
    }

}
