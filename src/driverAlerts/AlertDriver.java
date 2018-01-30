package driverAlerts;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;


public class AlertDriver {

	public class AlertDrivers extends CommandGroup implements Constants {
		public AlertDrivers() {// Utilize pulseAlertLight method to simplify this.
			for (int i = 0; i < 4; i++) {
			}
		}
	}


