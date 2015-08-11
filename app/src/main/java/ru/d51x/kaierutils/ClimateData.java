package ru.d51x.kaierutils;


public class ClimateData {

    public enum FanMode { auto, off, manual, unknown};
    public enum BlowMode { auto, manual, unknown};
    public enum FanSpeed { unknown, auto, off, speed1, speed2, speed3, speed4, speed5, speed6, speed7, speed8}
    public enum BlowDirection {    unknown,
                            off,
                            face,
                            from_face_to_feet_and_face,
                            feet_and_face,
                            from_feet_and_face_to_feet,
                            feet,
                            from_feet_to_feet_and_window,
                            feet_and_window,
                            from_feet_and_window_to_window,
                            window
                       }

    public enum State { off, on }

    public FanSpeed fan_speed;
    public FanMode fan_mode;
    public BlowDirection blow_direction;
    public BlowMode blow_mode;
    public double temperature ;                  // остаток топлива в баке
    public int ext_temperature ;                  // остаток топлива в баке
    public State ac_state ;                  // остаток топлива в баке
    public State recirculation_state ;                  // остаток топлива в баке
    public State defogger_state ;                  // остаток топлива в баке


  public ClimateData() {
      fan_speed = FanSpeed.unknown;                  // остаток топлива в баке
      blow_direction = BlowDirection.unknown;                  // остаток топлива в баке
      temperature = -255;                  // остаток топлива в баке
      ext_temperature = -255;                  // остаток топлива в баке
      ac_state = State.off;                  // остаток топлива в баке
      recirculation_state = State.off;                  // остаток топлива в баке
      defogger_state = State.off;
      fan_mode = FanMode.unknown;
      blow_mode = BlowMode.unknown;
  }


}
