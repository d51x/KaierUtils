package ru.d51x.kaierutils.coding.datatypes.variant;

public enum VehicleLanguage {
    vehicleLanguage_0(0, "No request"),
    vehicleLanguage_1(1, "Japanese"),
    vehicleLanguage_2(2, "English"),
    vehicleLanguage_3(3, "French"),
    vehicleLanguage_4(4, "Spanish"),
    vehicleLanguage_5(5, "German"),
    vehicleLanguage_6(6, "Portuguese"),
    vehicleLanguage_7(7, "Dutch"),
    vehicleLanguage_8(8, "Italian"),
    vehicleLanguage_9(9, "Swedish"),
    vehicleLanguage_10(10, "Danish"),
    vehicleLanguage_11(11, "Russian"),
    vehicleLanguage_12(12, "Chinese"),
    vehicleLanguage_13(13, "Arabic"),
    ;
    private final int idx;
    private final String title;

    VehicleLanguage(int idx, String title) {
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
