package ru.d51x.kaierutils.coding.datatypes.custom;

public enum AccPowerAutoCut {
    disable(0, "Disable"),
    time_min_30(1, "30 min"),
    time_min_60(2, "60 min"),
    cannot_select(0x0F, "Cannot change");
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
