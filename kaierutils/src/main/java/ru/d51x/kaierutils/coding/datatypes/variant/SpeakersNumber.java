package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SpeakersNumber {
    speakersNumber_0(0, "Premium"),
    speakersNumber_1(1, "1 speaker"),
    speakersNumber_2(2, "2 speaker"),
    speakersNumber_3(3, "3 speaker"),
    speakersNumber_4(4, "4 speaker"),
    speakersNumber_5(5, "5 speaker"),
    speakersNumber_6(6, "6 speaker"),
    speakersNumber_7(7, "7 speaker"),
    speakersNumber_8(8, "8 speaker"),
    speakersNumber_9(9, "9 speaker"),
    speakersNumber_10(10, "10 speaker"),
    speakersNumber_11(11, "11 speaker"),
    speakersNumber_12(12, "12 speaker"),
    speakersNumber_13(13, "13 speaker"),
    speakersNumber_14(14, "14 speaker"),
    speakersNumber_15(15, "15 speaker"),
    ;
    private final int idx;
    private final String title;

    SpeakersNumber(int idx, String title) {
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
