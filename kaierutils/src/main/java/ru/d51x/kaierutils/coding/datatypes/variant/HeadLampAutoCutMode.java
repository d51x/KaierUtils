package ru.d51x.kaierutils.coding.datatypes.variant;

public enum HeadLampAutoCutMode {
    headLampAutoCutMode_0(0, "E-spec"),
    headLampAutoCutMode_1(1, "B-spec"),
    headLampAutoCutMode_2(2, "C-spec"),
    headLampAutoCutMode_3(3, "D-spec"),
    ;
    private final int idx;
    private final String title;

    HeadLampAutoCutMode(int idx, String title) {
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
