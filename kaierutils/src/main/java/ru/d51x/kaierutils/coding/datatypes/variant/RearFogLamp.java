package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RearFogLamp {
    rearFogLamp_0(0, "Not Present (can change)"),
    rearFogLamp_1(1, "Present (can change)"),
    rearFogLamp_2(2, "Present (cannot change)"),
    ;
    private final int idx;
    private final String title;

    RearFogLamp(int idx, String title) {
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
