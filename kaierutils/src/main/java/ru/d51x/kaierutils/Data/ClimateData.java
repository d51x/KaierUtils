package ru.d51x.kaierutils.Data;


import java.io.Serializable;

public class ClimateData implements Serializable {

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
    public double temperature ;
    public int ext_temperature ;
    public State ac_state ;
    public State recirculation_state ;
    public State defogger_state ;


  public ClimateData() {
      fan_speed = FanSpeed.unknown;
      blow_direction = BlowDirection.unknown;
      temperature = -255;
      ext_temperature = -255;
      ac_state = State.off;
      recirculation_state = State.off;
      defogger_state = State.off;
      fan_mode = FanMode.unknown;
      blow_mode = BlowMode.unknown;
  }


}
