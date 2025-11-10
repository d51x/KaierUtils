package ru.d51x.kaierutils.coding.datatypes.variant;

public enum GateTrunkLamp {
    gateTrunkLamp_0(0, "Mode1 (trunk) - дверь"),
    gateTrunkLamp_1(1, "Mode2 (cargo) - в багажнике"),
    gateTrunkLamp_2(2, "Mode3 (cabin) - в кабине"),
    gateTrunkLamp_3(3, "Mode4 (side step lamp) - боковая лампа"),
    ;
    private final int idx;
    private final String title;

    GateTrunkLamp(int idx, String title) {
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
