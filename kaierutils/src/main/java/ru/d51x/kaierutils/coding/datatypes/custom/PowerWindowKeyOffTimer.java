package ru.d51x.kaierutils.coding.datatypes.custom;

public enum PowerWindowKeyOffTimer {
    time_sec_0(0, "0 sec"),
    time_sec_30(1, "30 sec"),
    time_sec_180(2, "180 sec"),
    time_sec_600(3, "600 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    PowerWindowKeyOffTimer(int idx, String title) {
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
