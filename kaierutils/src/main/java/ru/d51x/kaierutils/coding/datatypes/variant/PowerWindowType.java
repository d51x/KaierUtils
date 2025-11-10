package ru.d51x.kaierutils.coding.datatypes.variant;

public enum PowerWindowType {
    powerWindowType_0(0, "Not Present"),
    powerWindowType_1(1, "Type P1"),
    powerWindowType_2(2, "Type P2"),
    powerWindowType_3(3, "Type P3"),
    powerWindowType_4(4, "Type P4"),
    powerWindowType_5(5, "Type P3 MMAL"),
    powerWindowType_6(6, "Type P4 MMAL"),
    ;
    private final int idx;
    private final String title;

    PowerWindowType(int idx, String title) {
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
