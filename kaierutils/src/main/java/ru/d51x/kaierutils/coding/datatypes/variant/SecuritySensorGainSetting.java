package ru.d51x.kaierutils.coding.datatypes.variant;

public enum SecuritySensorGainSetting {
    securitySensorGainSetting_0(0, "TYPE1"),
    securitySensorGainSetting_1(1, "TYPE2"),
    securitySensorGainSetting_2(2, "TYPE3"),
    securitySensorGainSetting_3(3, "TYPE4"),
    securitySensorGainSetting_4(4, "TYPE5"),
    securitySensorGainSetting_5(5, "TYPE6"),
    securitySensorGainSetting_6(6, "TYPE7"),
    securitySensorGainSetting_7(7, "TYPE8"),
    securitySensorGainSetting_8(8, "SNA"),
    ;
    private final int idx;
    private final String title;

    SecuritySensorGainSetting(int idx, String title) {
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
