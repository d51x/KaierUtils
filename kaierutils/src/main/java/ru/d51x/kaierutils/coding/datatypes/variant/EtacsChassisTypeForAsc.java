package ru.d51x.kaierutils.coding.datatypes.variant;

public enum EtacsChassisTypeForAsc {
    chassisTypeForAsc_1(0, "Undefined"),
    chassisTypeForAsc_2(1, "Type1"),
    chassisTypeForAsc_3(2, "Type2"),
    chassisTypeForAsc_4(3, "Type3"),
    chassisTypeForAsc_5(4, "Type4"),
    chassisTypeForAsc_6(5, "Type5"),
    chassisTypeForAsc_7(6, "Type6"),
    chassisTypeForAsc_8(7, "Type7"),
    chassisTypeForAsc_9(8, "Type8"),
    chassisTypeForAsc_10(9, "Type9"),
    chassisTypeForAsc_11(10, "Type10"),
    chassisTypeForAsc_12(11, "Type11"),
    chassisTypeForAsc_13(12, "Type12"),
    chassisTypeForAsc_14(13, "Type13"),
    chassisTypeForAsc_15(14, "Type14"),
    chassisTypeForAsc_16(15, "Type15"),
    ;
    private final int idx;
    private final String title;

    EtacsChassisTypeForAsc(int idx, String title) {
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
