package ru.d51x.kaierutils.coding.datatypes.variant;

public enum EtacsModelYear {
    model_year_06(0x0C, "06");
    // стартовое значение 0x0C = 12, делим на 2, получим 6 - 06 модельный год
    // следующее 0x0D = 13, делим на 2, получим 6.5 - 06.5 модельный год и т.д.
    private final int idx;
    private final String title;

    EtacsModelYear(int idx, String title) {
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
