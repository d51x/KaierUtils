package ru.d51x.kaierutils.coding.datatypes.custom;

public enum BuzzerAnswerBack {
    not_sound_buzzer(0, "Not sound buzzer"),
    at_kos(1, "At KOS"),
    at_keyless(2, "At keyless"),
    at_both(3, "At Both"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    BuzzerAnswerBack(int idx, String title) {
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
