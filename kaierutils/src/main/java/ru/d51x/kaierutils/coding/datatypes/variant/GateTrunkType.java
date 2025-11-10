package ru.d51x.kaierutils.coding.datatypes.variant;

public enum GateTrunkType {
    gateTrunkType_0(0, "gate type - открывание в бок"),
    gateTrunkType_1(1, "trunk type - открывание в верх"),
    ;
    private final int idx;
    private final String title;

    GateTrunkType(int idx, String title) {
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
