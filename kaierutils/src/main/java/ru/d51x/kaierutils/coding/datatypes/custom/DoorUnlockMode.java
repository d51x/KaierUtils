package ru.d51x.kaierutils.coding.datatypes.custom;

public enum DoorUnlockMode {
    all_door_unlock(0, "All Doors Unlock"),
    dr_door_unlock(1, "Dr door Unlock"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    DoorUnlockMode(int idx, String title) {
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
