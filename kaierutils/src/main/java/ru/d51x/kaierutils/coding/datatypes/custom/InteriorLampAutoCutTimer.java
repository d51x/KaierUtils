package ru.d51x.kaierutils.coding.datatypes.custom;

public enum InteriorLampAutoCutTimer {
    disable(0, "Disable"),
    time_min_3(1, "3 min"),
    time_min_30(2, "30 min (initial value)"),
    time_min_60(3, "60 min"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    InteriorLampAutoCutTimer(int idx, String title) {
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
