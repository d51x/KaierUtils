package ru.d51x.kaierutils.coding.datatypes.custom;

public enum KosUnlockDisableTime {
    time_sec_0(0, "0 sec"),
    time_sec_3(1, "3 sec"),
    time_sec_5(2, "5 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    KosUnlockDisableTime(int idx, String title) {
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
