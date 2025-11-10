package ru.d51x.kaierutils.coding.datatypes.custom;

public enum ComingHomeLightTime {
    disable(0, "Disable"),
    time_sec_15(1, "15 sec"),
    time_sec_30(2, "30 sec"),
    time_sec_60(3, "60 sec"),
    time_sec_180(4, "180 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    ComingHomeLightTime(int idx, String title) {
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
