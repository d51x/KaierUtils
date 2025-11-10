package ru.d51x.kaierutils.coding.datatypes.variant;

public enum FronFogLampMode {
    fronFogLampMode_0(0, "A-spec"),
    fronFogLampMode_1(1, "B-spec"),
    ;
    private final int idx;
    private final String title;

    FronFogLampMode(int idx, String title) {
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
