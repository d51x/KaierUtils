/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pt.lighthouselabs.obd.commands.control;

import pt.lighthouselabs.obd.commands.ObdCommand;
import pt.lighthouselabs.obd.enums.AvailableCommandNames;

public class ControlModuleVoltageObdCommand extends ObdCommand {


  private float voltage = 0.00f;

  /**
   * Default ctor.
   */
  public ControlModuleVoltageObdCommand() {
    super("01 42");
  }

  /**
   * Copy ctor.
   *
   * @param other a {@link pt.lighthouselabs.obd.commands.control.ControlModuleVoltageObdCommand} object.
   */
  public ControlModuleVoltageObdCommand(ControlModuleVoltageObdCommand other) {
    super(other);
  }

  @Override
  protected void performCalculations() {
    // ignore first two bytes [hh hh] of the response
      // ((A*256)+B)/1000
    int a = buffer.get(2);
    int b = buffer.get(3);
      voltage = (a * 256 + b) / 1000;
  }

  /**
	 * 
	 */
  @Override
  public String getFormattedResult() {
    return String.format("%.1f%s", voltage, "v");
  }

  /**
   * @return a double.
   */
  public float getVoltage() {
    return voltage;
  }

  @Override
  public String getName() {
    return AvailableCommandNames.CMU_VOLTAGE.getValue();
  }

}
