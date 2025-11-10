package ru.d51x.kaierutils.coding.datatypes.variant;

public enum HeadLampType {
    headLampType_0(0, "2 beam"),
    headLampType_1(1, "4 beam"),
    ;
    private final int idx;
    private final String title;

    HeadLampType(int idx, String title) {
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
