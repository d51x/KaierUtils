package ru.d51x.kaierutils.coding.datatypes.variant;

public enum DrlType {
    drlType_0(0, "DRL not present"),
    drlType_1(1, "Normal DRL present"),
    drlType_2(2, "Dimming DRL present"),
    drlType_3(4, "Independent DRL present"),
    drlType_4(5, "Dimming DRL with P (Parking)"),
    drlType_5(6, "Independent DRL with P (Parking)"),
    drlType_6(7, "Normal DRL with P (Parking)"),
    ;
    private final int idx;
    private final String title;

    DrlType(int idx, String title) {
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
