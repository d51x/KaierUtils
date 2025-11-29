package ru.d51x.kaierutils.dtc;

public class DtcError {
    private final String code;
    private String block;

    private String description;
    private final boolean isActive;
    private int atMileage;

    public DtcError(String code, boolean isActive) {
        this.code = code;
        this.isActive = isActive;
        this.atMileage = 0;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getAtMileage() {
        return atMileage;
    }

    public void setAtMileage(int atMileage) {
        this.atMileage = atMileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
