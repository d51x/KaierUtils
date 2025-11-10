package ru.d51x.kaierutils.coding.datatypes.custom;

public enum AutomaticLightSensivity {
    level1_bright(0, "High-high ambient brightness"), //"Level1 bright"),
    level2_bright(1, "High ambient brightness"), //"Level2 bright"),
    level3(2, "Standard ambient brightness (initial condition)"), // "Level3"), (initial condition)
    level4_dark(3, "Low ambient brightness"), // "Level4 dark"),
    level5_dark(4, "Low-low ambient brightness"), // "Level5 dark"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    AutomaticLightSensivity(int idx, String title) {
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
