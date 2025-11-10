package ru.d51x.kaierutils.coding.datatypes.custom;

public enum SirenAnswerBack {
    custom_43_0(0, "No sound"),
    custom_43_1(1, "Sound 1"),
    custom_43_2(2, "Sound 2"),
    custom_43_3(3, "Sound 3"),
    custom_43_4(4, "Sound 4"),
    custom_43_5(5, "Sound 5"),
    custom_43_6(6, "Sound 6"),
    cannot_select(0x0F, "Cannot change");
    private final int idx;
    private final String title;

    SirenAnswerBack(int idx, String title) {
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
