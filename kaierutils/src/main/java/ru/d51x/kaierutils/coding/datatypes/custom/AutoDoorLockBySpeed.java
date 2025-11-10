package ru.d51x.kaierutils.coding.datatypes.custom;

public enum AutoDoorLockBySpeed {
    disable(0, "Disable"),
    relock(1, "Relock"),
    not_relock(2, "Not relock"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    AutoDoorLockBySpeed(int idx, String title) {
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
