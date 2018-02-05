package controllers;

import lib.Loop;
import lib.joystick.XboxController;
import main.Constants;
import main.OI;
import main.Robot;

public class Record implements Loop, Constants {
	private boolean initialized = false;
	
	@Override
	public void onStart() {
		initialized = false;
	}

	@Override
	public void onLoop() {
		if(OI.recordOn()) {
			if(!initialized)
				initialize();
			execute();
		}
		else
			end();
	}
	
	@Override
	public void onStop() {
		initialized = false;
	}
	
	//Helper Methods
	private void initialize() {
		initialized = true;
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TankDefault)
			Robot.dt.setTankDefaults();
	}
	
	private void execute() {
		XboxController controller = OI.getXbox();
		Robot.dt.driveVelocity(OI.getXbox().getSmoothedMainY(), OI.getXbox().getSmoothedAltX());
		Robot.lg.writeLine(Robot.dt.getLeftVoltage() + "," + Robot.dt.getRightVoltage() + "," + controller.a
							+ "," + controller.b + "," + controller.leftBumper + "," + controller.leftJoystickButton
							+ "," + controller.leftTrigger + "," + controller.rightBumper + "," + controller.rightJoystickButton
							+ "," + controller.rightTrigger + "," + controller.select + "," + controller.start 
							+ "," + controller.x + "," + controller.y);
	}
	
	private void end() {
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TalonDefault)
			Robot.dt.setTalonDefaults();
	}
}

