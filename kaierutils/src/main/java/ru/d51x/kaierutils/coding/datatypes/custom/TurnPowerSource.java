package ru.d51x.kaierutils.coding.datatypes.custom;

public enum TurnPowerSource {
    acc_or_ig1(0, "Operable with ACC or ON position"), //ACC or IG1
    ig1(1, "Operable with ON position (initial condition)"), // IG1
    cannot_select(0x0F, "Cannot change"); // в мануале нет

    private final int idx;
    private final String title;

    TurnPowerSource(int idx, String title) {
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
