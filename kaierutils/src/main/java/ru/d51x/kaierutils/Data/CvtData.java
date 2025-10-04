package ru.d51x.kaierutils.Data;

import java.io.Serializable;
import java.util.Objects;

public class CvtData implements Serializable {

    private int temperature;
    private int oilDegradation;

    private float workHoursTotal;
    private float workHoursHot;

    public CvtData() {
        temperature = -255;
        oilDegradation = 0;
        workHoursHot = 0f;
        workHoursTotal = 0f;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getOilDegradation() {
        return oilDegradation;
    }

    public void setOilDegradation(int oilDegradation) {
        this.oilDegradation = oilDegradation;
    }

    public float getWorkHoursTotal() {
        return workHoursTotal;
    }

    public void setWorkHoursTotal(float workHoursTotal) {
        this.workHoursTotal = workHoursTotal;
    }

    public float getWorkHoursHot() {
        return workHoursHot;
    }

    public void setWorkHoursHot(float workHoursHot) {
        this.workHoursHot = workHoursHot;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CvtData that = (CvtData) o;
        return temperature == that.temperature && oilDegradation == that.oilDegradation && Float.compare(workHoursTotal, that.workHoursTotal) == 0 && Float.compare(workHoursHot, that.workHoursHot) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, oilDegradation, workHoursTotal, workHoursHot);
    }
}
