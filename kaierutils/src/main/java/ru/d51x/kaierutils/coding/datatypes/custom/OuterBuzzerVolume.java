package ru.d51x.kaierutils.coding.datatypes.custom;

public enum OuterBuzzerVolume {
    custom_42_0(0, "Volume 1"),
    custom_42_1(1, "Volume 2"),
    custom_42_2(2, "Volume 3"),
    custom_42_3(3, "Volume auto"), // нет такого в документации
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    OuterBuzzerVolume(int idx, String title) {
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
