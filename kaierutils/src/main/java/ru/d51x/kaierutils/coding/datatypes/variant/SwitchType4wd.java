package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SwitchType4wd {
    switchType4wd_0(0, "Undefined"),
    switchType4wd_1(1, "Dial/Seesaw type"),
    switchType4wd_2(2, "Push type"),
    switchType4wd_3(3, "SNA"),
    ;
    private final int idx;
    private final String title;

    SwitchType4wd(int idx, String title) {
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
