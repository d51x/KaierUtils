package ru.d51x.kaierutils.coding.datatypes.custom;

public enum DeadLockButtonOperation {
    twice(0, "Twice"),
    once(1, "Once"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    DeadLockButtonOperation(int idx, String title) {
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
