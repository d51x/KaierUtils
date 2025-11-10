package ru.d51x.kaierutils.coding.datatypes.custom;

public enum FrontWiperOperation {
    normal_int(0, "fixed to 4 seconds"), // Normal INT
    variable_int(1, "by the wiper volume control"), // Variable INT
    speed_sensitive(2, "to the intermittent wiper volume \ncontrol and vehicle speed"), //Speed Sensitive
    rain_sensitive(3, "to the intermittent wiper volume \ncontrol and lighting control sensor"), // Rain Sensitive
    cannot_select(0x0F, "Cannot change"); // нет в мануале
    private final int idx;
    private final String title;

    FrontWiperOperation(int idx, String title) {
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
