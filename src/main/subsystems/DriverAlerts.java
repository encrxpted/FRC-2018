package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class DriverAlerts extends Subsystem {

 public void setAlertLightState(boolean state) {
	alertRelay.set(!state);
 }

 public boolean getAlertLightState() {
	return alertRelay.get();
 }

 public void pulseAlertLight(int pulseLength) {
	alertRelay.pulse(pulseLength);
 }

 public boolean isPulsing() {
	return alertRelay.isPulsing();
}

 @Override
 protected void initDefaultCommand() {
 }

}


