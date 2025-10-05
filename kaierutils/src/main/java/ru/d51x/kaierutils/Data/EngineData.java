package ru.d51x.kaierutils.Data;

import java.io.Serializable;
import java.util.Objects;

public class EngineData implements Serializable {

    private float voltage;
    private int speed;
    private int rpm;
    private int coolantTemperature;

    private float airFlowSensor;
    private CanMmcData.State acFanRelay;
    private CanMmcData.State fuelPumpRelay;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        EngineData that = (EngineData) o;
        return Float.compare(voltage, that.voltage) == 0 && speed == that.speed && rpm == that.rpm && coolantTemperature == that.coolantTemperature && Float.compare(airFlowSensor, that.airFlowSensor) == 0 && acFanRelay == that.acFanRelay && fuelPumpRelay == that.fuelPumpRelay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(voltage, speed, rpm, coolantTemperature, airFlowSensor, acFanRelay, fuelPumpRelay);
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public int getCoolantTemperature() {
        return coolantTemperature;
    }

    public void setCoolantTemperature(int coolantTemperature) {
        this.coolantTemperature = coolantTemperature;
    }

    public float getAirFlowSensor() {
        return airFlowSensor;
    }

    public void setAirFlowSensor(float airFlowSensor) {
        this.airFlowSensor = airFlowSensor;
    }

    public CanMmcData.State isAcFanRelay() {
        return acFanRelay;
    }

    public void setAcFanRelay(CanMmcData.State acFanRelay) {
        this.acFanRelay = acFanRelay;
    }

    public CanMmcData.State isFuelPumpRelay() {
        return fuelPumpRelay;
    }

    public void setFuelPumpRelay(CanMmcData.State fuelPumpRelay) {
        this.fuelPumpRelay = fuelPumpRelay;
    }

    public EngineData() {
        voltage = 0;
        speed = -1;
        rpm = 0;
        coolantTemperature = -255;
        airFlowSensor = 0;
        acFanRelay = CanMmcData.State.off;
        fuelPumpRelay = CanMmcData.State.off;
    }
}
