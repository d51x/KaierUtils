package ru.d51x.kaierutils.dtc;

public class DtcHeader {
    private String title;
    private int count;
    private int mileage;

    public DtcHeader(String title, int count, int mileage) {
        this.count = count;
        this.mileage = mileage;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
