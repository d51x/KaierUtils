package ru.d51x.kaierutils.coding.datatypes.variant;

public enum CoolantTempGaugeThreshold {
    coolantTempGaugeThreshold_0(0, "Normal"),
    coolantTempGaugeThreshold_1(1, "Hot"),
    ;
    private final int idx;
    private final String title;

    CoolantTempGaugeThreshold(int idx, String title) {
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
