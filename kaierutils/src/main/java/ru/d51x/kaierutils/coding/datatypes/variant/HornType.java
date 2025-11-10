package ru.d51x.kaierutils.coding.datatypes.variant;

public enum HornType {
    hornType_0(0, "Single horn"),
    hornType_1(1, "Dual horn"),
    ;
    private final int idx;
    private final String title;

    HornType(int idx, String title) {
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
