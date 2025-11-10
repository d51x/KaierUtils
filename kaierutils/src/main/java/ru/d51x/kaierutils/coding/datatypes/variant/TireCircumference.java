package ru.d51x.kaierutils.coding.datatypes.variant;

// длина окружности шин - 2 байта
public enum TireCircumference {
    etacs_variant_data_0(0, "Disable"),
    ;
    private final int idx;
    private final String title;

    TireCircumference(int idx, String title) {
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
