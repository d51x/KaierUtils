package ru.d51x.kaierutils.coding.datatypes.variant;

public enum WelcomeLight {
    welcomeLight_0(0, "Disable"),
    welcomeLight_1(1, "Enable (default D)"),
    welcomeLight_2(2, "Enable small"),
    welcomeLight_3(3, "Enable head"),
    ;
    private final int idx;
    private final String title;

    WelcomeLight(int idx, String title) {
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
