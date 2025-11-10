package ru.d51x.kaierutils.coding.datatypes.custom;

public enum MultiModeRke {
    disable(0, "Disable"),
    custom25_1(1, "Power window: open & close; \nDoor Mirror: open & close"), // P/W:O&C, D/M:O&C
    custom25_2(2, "Power window: open & close"), // P/W:O&C
    custom25_3(3, "Power window: lose; \nDoor Mirror: open & close"), // P/W:C, D/M:O&C
    custom25_4(4, "Door mirror: fold/unfold only"), // D/M:O&C
    custom25_5(5, "Power window: close only"), // P/W:C
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    MultiModeRke(int idx, String title) {
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
