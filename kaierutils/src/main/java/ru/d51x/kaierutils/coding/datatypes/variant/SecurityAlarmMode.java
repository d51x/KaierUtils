package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SecurityAlarmMode {
    securityAlarmMode_0(0, "Not Present"),
    securityAlarmMode_1(1, "A-spec. (DOM)"),
    securityAlarmMode_2(2, "B-spec. (EU)"),
    securityAlarmMode_3(3, "C-spec. (NAS)"),
    ;
    private final int idx;
    private final String title;

    SecurityAlarmMode(int idx, String title) {
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
