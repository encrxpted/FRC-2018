package main.commands.driveAlerts;

import main.Robot;
import main.subsystems.DriverAlerts;

public class AlertLightOn {

	public AlertLightOn() {
		requires(Robot.da);
	}

	private void requires(DriverAlerts da) {
		// TODO Auto-generated method stub
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.da).setAlertLightState(true);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
	
}
