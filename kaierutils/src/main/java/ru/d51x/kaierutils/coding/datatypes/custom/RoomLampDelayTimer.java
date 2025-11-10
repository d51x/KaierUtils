package ru.d51x.kaierutils.coding.datatypes.custom;

public enum RoomLampDelayTimer {
    time_sec_0(0, "0 sec"),
    time_sec_7_5(1, "7.5 sec"),
    time_sec_15(2, "15 sec (initial condition)"), // initial condition
    time_sec_30(3, "30 sec"),
    time_sec_60(4, "60 sec"),
    time_sec_120(5, "120 sec"),
    time_sec_180(6, "180 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    RoomLampDelayTimer(int idx, String title) {
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
