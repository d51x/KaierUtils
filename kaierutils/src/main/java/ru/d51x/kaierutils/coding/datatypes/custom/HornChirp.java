package ru.d51x.kaierutils.coding.datatypes.custom;

public enum HornChirp {
    not_sound_horn(0, "Not sound horn"),
    lock_any_time(1, "Lock any time"),
    lock_auto_on(2, "Lock/auto ON"),
    w_lock_any_time(3, "W lock any time"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    HornChirp(int idx, String title) {
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
