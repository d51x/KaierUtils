package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FrostWarningThreshold {
    frostWarningThreshold_0(0, "Threshold for Jpn"),
    frostWarningThreshold_1(1, "Threshold for EU"),
    frostWarningThreshold_2(2, "Threshold for Nas"),
    frostWarningThreshold_3(3, "Threshold for Aus"),
    frostWarningThreshold_4(4, "Threshold for Gcc"),
    frostWarningThreshold_5(5, "Threshold for Exp"),
    ;
    private final int idx;
    private final String title;

    FrostWarningThreshold(int idx, String title) {
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
