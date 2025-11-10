package ru.d51x.kaierutils.coding.datatypes.custom;

public enum TimerLockTimer {
    time_sec_30(0, "30 sec (initial value)"),
    time_sec_60(1, "60 sec"),
    time_sec_120(2, "120 sec"),
    time_sec_180(3, "180 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    TimerLockTimer(int idx, String title) {
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
