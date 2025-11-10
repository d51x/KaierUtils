package ru.d51x.kaierutils.coding.datatypes.custom;

public enum ComfortFlasherSwitchTime {
    custom_normal(0, "Normal - 0.4 second"),
    custom_long(1, "Long - 0.8 second"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    ComfortFlasherSwitchTime(int idx, String title) {
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
