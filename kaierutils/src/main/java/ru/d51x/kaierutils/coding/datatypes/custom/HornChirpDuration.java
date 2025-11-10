package ru.d51x.kaierutils.coding.datatypes.custom;

public enum HornChirpDuration {
    custom_short(0, "Short"),
    custom_long(1, "Long"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    HornChirpDuration(int idx, String title) {
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
