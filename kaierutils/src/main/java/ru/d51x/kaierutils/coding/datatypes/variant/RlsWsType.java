package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RlsWsType {
    rlsWsType_0(0, "TYPE1 (Clear)"),
    rlsWsType_1(1, "TYPE2 (Green)"),
    rlsWsType_2(2, "TYPE3"),
    rlsWsType_3(3, "TYPE4"),
    ;
    private final int idx;
    private final String title;

    RlsWsType(int idx, String title) {
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
