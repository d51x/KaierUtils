package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RadioFrequency {
    radioFrequency_0(0, "Undefined"),
    radioFrequency_1(1, "AM reception type MW/LW"),
    radioFrequency_2(2, "AM reception type 1kHz Step"),
    radioFrequency_3(3, "Other type"),
    ;
    private final int idx;
    private final String title;

    RadioFrequency(int idx, String title) {
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
