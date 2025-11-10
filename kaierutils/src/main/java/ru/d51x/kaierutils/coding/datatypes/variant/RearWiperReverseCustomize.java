package ru.d51x.kaierutils.coding.datatypes.variant;

public enum RearWiperReverseCustomize {
    rearWiperReverseCustomize_0(0, "Disable"),
    rearWiperReverseCustomize_1(1, "Enable (default RR only) - активирован (по умолчанию при движении только назад)"),
    rearWiperReverseCustomize_2(2, "Enable (default FR & RR) - активирован (по умолчанию при движении вперед и назад)"),
    ;
    private final int idx;
    private final String title;

    RearWiperReverseCustomize(int idx, String title) {
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
