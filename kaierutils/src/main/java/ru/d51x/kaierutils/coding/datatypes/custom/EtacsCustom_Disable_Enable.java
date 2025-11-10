package ru.d51x.kaierutils.coding.datatypes.custom;

public enum EtacsCustom_Disable_Enable {
    coding_disable(0, "Disabled"),
    coding_enable(1, "Enable"),
    cannot_select(0x0F, "Cannot change"); // в мануале нет
    private final int idx;
    private final String title;

    EtacsCustom_Disable_Enable(int idx, String title) {
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
