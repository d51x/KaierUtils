package ru.d51x.kaierutils.coding.datatypes.variant;

public enum DeadLockOperationCustomize {
    deadLockOperationCustomize_0(0, "Disable"),
    deadLockOperationCustomize_1(1, "Enable (Twice)"),
    deadLockOperationCustomize_2(2, "Enable (Once)"),
    ;
    private final int idx;
    private final String title;

    DeadLockOperationCustomize(int idx, String title) {
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
