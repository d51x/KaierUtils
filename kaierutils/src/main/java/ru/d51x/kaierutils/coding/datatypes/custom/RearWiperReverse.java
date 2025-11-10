package ru.d51x.kaierutils.coding.datatypes.custom;

public enum RearWiperReverse {
    custom_40_0(0, "Enable(rear wiper switch is ON)"), // Enable(R wip.ON)
    custom_40_1(1, "Enable(front or rear wiper switch is ON)"), // Enable(R/F wip.)
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    RearWiperReverse(int idx, String title) {
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
