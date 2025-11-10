package ru.d51x.kaierutils.coding.datatypes.custom;

public enum EtacsCustom_Enable_Disable {
    enable(0, "Enable"),
    disable(1, "Disable"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    EtacsCustom_Enable_Disable(int idx, String title) {
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
