package ru.d51x.kaierutils.coding.datatypes.custom;

public enum AutoFoldMirror {
    not_auto(0, "Not Auto"),
    open_by_vehicle_speed(1, "Open By Vehicle Speed"),
    open_close_by_ig(2, "Open/Close by Ignition switch"), // Open/Close by IG
    open_close_by_keyless(3, "Open/Close by Keyless (initial condition)"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    AutoFoldMirror(int idx, String title) {
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
