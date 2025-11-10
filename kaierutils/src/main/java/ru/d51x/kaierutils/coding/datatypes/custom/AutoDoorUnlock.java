package ru.d51x.kaierutils.coding.datatypes.custom;

public enum AutoDoorUnlock {
    disable(0, "Disable"),
    always_p(1, "Always (P pos)"),
    p_w_unlock_p(2, "P/W unlock (P)"),
    always_lock(3, "Always (Lock pos)"),
    p_w_unlock_lock(4, "P/W unlock (Lock)"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    AutoDoorUnlock(int idx, String title) {
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
