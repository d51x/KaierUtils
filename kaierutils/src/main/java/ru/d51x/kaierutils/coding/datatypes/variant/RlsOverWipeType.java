package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RlsOverWipeType {
    rlsOverwipeType_0(0, "TYPE1"),
    rlsOverwipeType_1(1, "TYPE2"),
    rlsOverwipeType_2(2, "TYPE3"),
    rlsOverwipeType_3(3, "TYPE4"),
    rlsOverwipeType_4(4, "TYPE5"),
    rlsOverwipeType_5(5, "TYPE6"),
    rlsOverwipeType_6(6, "TYPE7"),
    rlsOverwipeType_7(7, "TYPE8"),
    ;
    private final int idx;
    private final String title;

    RlsOverWipeType(int idx, String title) {
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
