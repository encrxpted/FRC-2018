package controllers;

import lib.joystick.XboxController;
import loopController.Loop;
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
		Robot.lg.writeLine(Robot.dt.getLeftVoltage() + "," + Robot.dt.getRightVoltage() + "," + controller.a + "," + controller.b + "," + controller.x 
				 + "," + controller.y + "," + controller.leftBumper + "," + controller.rightBumper + "," + controller.select + "," + controller.start
				 + "," + controller.leftJoystickPress + "," + controller.rightJoystickPress + "," + controller.leftTrigger + "," + controller.rightTrigger);
	}
	
	private void end() {
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TalonDefault)
			Robot.dt.setTalonDefaults();
	}
}

