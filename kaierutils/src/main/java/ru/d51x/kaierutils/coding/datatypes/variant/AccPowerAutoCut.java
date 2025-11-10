package ru.d51x.kaierutils.coding.datatypes.variant;

public enum AccPowerAutoCut {
    accPowerAutoCut_0(0, "Default disable"),
    accPowerAutoCut_1(1, "Default enable"),
    ;
    private final int idx;
    private final String title;

    AccPowerAutoCut(int idx, String title) {
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
