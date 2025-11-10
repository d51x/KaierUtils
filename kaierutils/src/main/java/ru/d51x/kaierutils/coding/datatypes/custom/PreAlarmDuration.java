package ru.d51x.kaierutils.coding.datatypes.custom;

public enum PreAlarmDuration {
    time_sec_10(0, "10 sec"),
    time_sec_6(1, "6 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    PreAlarmDuration(int idx, String title) {
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
