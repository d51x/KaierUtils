package ru.d51x.kaierutils.coding.datatypes.custom;

public enum RemoteLightOnTime {
    custom_44_0(0, "Disable"),
    custom_44_1(1, "Small 30 sec"),
    custom_44_2(2, "Small 60 sec"),
    custom_44_3(3, "Small 180 sec"),
    custom_44_4(4, "Head 30 sec"),
    custom_44_5(5, "Head 60 sec"),
    custom_44_6(6, "Head 180 sec"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    RemoteLightOnTime(int idx, String title) {
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
