package ru.d51x.kaierutils.dtc;

public class DtcHistoryError {
    private final String code;
    private String description;
    private final int atMileage;

    public DtcHistoryError(String code, int mileage) {
        this.code = code;
        this.atMileage = mileage;
    }

    public String getCode() {
        return code;
    }
    public int getAtMileage() {
        return atMileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
