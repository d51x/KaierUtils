package ru.d51x.kaierutils.Data;

public class ObdData {
    public float speed;
    public float rpm;
    public float coolant;
    public float maf;
    public float voltage;
    public float fuel_tank; // читаем из преференсов


    public ObdData() {
        speed = 0;
        rpm = 0;
        coolant = 0;
        maf = 0;
        voltage = 0;
        fuel_tank = 0;
    }
}
