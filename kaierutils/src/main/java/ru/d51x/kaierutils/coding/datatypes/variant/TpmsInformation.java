package ru.d51x.kaierutils.coding.datatypes.variant;

public enum TpmsInformation {
    tpmsInformation_0(0, "N/A"),
    tpmsInformation_1(1, "190 kPa"),
    tpmsInformation_2(2, "200 kPa"),
    tpmsInformation_3(3, "210 kPa"),
    tpmsInformation_4(4, "220 kPa"),
    tpmsInformation_5(5, "230 kPa"),
    tpmsInformation_6(6, "240 kPa"),
    tpmsInformation_7(7, "250 kPa"),
    ;
    private final int idx;
    private final String title;

    TpmsInformation(int idx, String title) {
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
