package ru.d51x.kaierutils.coding.datatypes.variant;

public enum AutoFoldMirror {
    autoFoldMirror_0(0, "Open By Vehicle Speed ot Not Present"),
    autoFoldMirror_1(1, "Keyless/KOS "),
    ;
    private final int idx;
    private final String title;

    AutoFoldMirror(int idx, String title) {
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
