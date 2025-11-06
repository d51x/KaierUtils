package ru.d51x.kaierutils.Data;

public class EtacsDataTypes {

    public enum Present {
        NOT_PRESENT(0, "not present"),
        PRESENT(1,"present");
        int id;
        String name;
        Present(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public enum State {
        DISABLE(0, "disable"),
        ENABLE(1,"enable");
        int id;
        String name;
        State(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public enum State2 {
        DISABLE(0, "disable"),
        ENABLE1(1,"enable_twice"),
        ENABLE2(2,"enable_once");
        int id;
        String name;
        State2(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public enum State3 {
        DISABLE(0, "disable"),
        ENABLE1(1,"enable_def_d"),
        ENABLE2(2,"enable_def_e");
        int id;
        String name;
        State3(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public enum DrlType {
        DRL_TYPE_0(0, "DRL not present"),
        DRL_TYPE_1(1,"Normal DRL present"),
        DRL_TYPE_2(2,"Dimming DRL present"),
        DRL_TYPE_4(4, "Independent DRL present"),
        DRL_TYPE_6(6, "Independent DRL with P (Parking)"),
        DRL_TYPE_7(7, "Normal DRL with P (Parking)");
        int id;
        String name;
        DrlType(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }
}
