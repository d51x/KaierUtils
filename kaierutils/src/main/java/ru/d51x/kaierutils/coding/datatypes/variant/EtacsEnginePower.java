package ru.d51x.kaierutils.coding.datatypes.variant;

public enum EtacsEnginePower {
    enginePower_1(0, "Normal"),
    enginePower_2(1, "Low Power"),
    enginePower_3(2, "High Power"),
    ;
    private final int idx;
    private final String title;

    EtacsEnginePower(int idx, String title) {
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
