package ru.d51x.kaierutils.coding.datatypes.custom;

public enum RearWiperInterval {
    time_sec_0(0, "0 sec"),
    time_sec_4(1, "4 sec"),
    time_sec_8(2, "8 sec"),
    time_sec_16(2, "16 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    RearWiperInterval(int idx, String title) {
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
