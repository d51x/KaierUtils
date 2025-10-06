package ru.d51x.kaierutils.Data;

import java.io.Serializable;
import java.util.Objects;

public class CombineMeterData implements Serializable {

    private float vehicleSpeed;
    private int engineRpm;
    private float voltage;
    private int fuelLevel;
    private int mileage;
    private float tripA;
    private float tripB;

    private int serviceReminderDistance;
    private int serviceReminderPeriod;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CombineMeterData that = (CombineMeterData) o;
        return Float.compare(vehicleSpeed, that.vehicleSpeed) == 0 && engineRpm == that.engineRpm && Float.compare(voltage, that.voltage) == 0 && fuelLevel == that.fuelLevel && mileage == that.mileage && Float.compare(tripA, that.tripA) == 0 && Float.compare(tripB, that.tripB) == 0 && serviceReminderDistance == that.serviceReminderDistance && serviceReminderPeriod == that.serviceReminderPeriod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleSpeed, engineRpm, voltage, fuelLevel, mileage, tripA, tripB, serviceReminderDistance, serviceReminderPeriod);
    }

    public float getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(float vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public int getEngineRpm() {
        return engineRpm;
    }

    public void setEngineRpm(int engineRpm) {
        this.engineRpm = engineRpm;
    }

    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public int getFuelLevel() {
        return fuelLevel < 67 ? fuelLevel : 0;
    }

    public void setFuelLevel(int fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getTripA() {
        return tripA;
    }

    public void setTripA(float tripA) {
        this.tripA = tripA;
    }

    public float getTripB() {
        return tripB;
    }

    public void setTripB(float tripB) {
        this.tripB = tripB;
    }

    public int getServiceReminderDistance() {
        return serviceReminderDistance;
    }

    public void setServiceReminderDistance(int serviceReminderDistance) {
        this.serviceReminderDistance = serviceReminderDistance;
    }

    public int getServiceReminderPeriod() {
        return serviceReminderPeriod;
    }

    public void setServiceReminderPeriod(int serviceReminderPeriod) {
        this.serviceReminderPeriod = serviceReminderPeriod;
    }

    public CombineMeterData() {
        vehicleSpeed = 0;
        engineRpm = 0;
        voltage = 0;
        fuelLevel = 0;
        mileage = 0;
        tripA = 0;
        tripB = 0;

        serviceReminderDistance = 0;
        serviceReminderPeriod = 0;
    }


}
