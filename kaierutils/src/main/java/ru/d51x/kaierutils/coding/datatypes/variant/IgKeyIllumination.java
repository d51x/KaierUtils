package ru.d51x.kaierutils.coding.datatypes.variant;

public enum IgKeyIllumination {
    igKeyIllumination_0(0, "Disabled"),
    igKeyIllumination_1(1, "Without getting off operation"),
    igKeyIllumination_2(2, "With getting off operation"),
    ;
    private final int idx;
    private final String title;

    IgKeyIllumination(int idx, String title) {
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
