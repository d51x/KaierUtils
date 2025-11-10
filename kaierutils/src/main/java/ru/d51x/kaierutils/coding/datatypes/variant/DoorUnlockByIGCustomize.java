package ru.d51x.kaierutils.coding.datatypes.variant;

public enum DoorUnlockByIGCustomize {
    doorUnlockByIGCustomize_0(0, "Disable"),
    doorUnlockByIGCustomize_1(1, "Enable (default D)"),
    ;
    private final int idx;
    private final String title;

    DoorUnlockByIGCustomize(int idx, String title) {
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
