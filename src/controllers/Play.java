
package controllers;

import loopController.Loop;
import main.Constants;
import main.OI;
import main.Robot;

public class Play implements Loop, Constants {
private boolean initialized = false;
	
	@Override
	public void onStart() {
		initialized = false;
	}

	@Override
	public void onLoop() {
		if(OI.playOn()) {
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
		String line = "";
		if((line = Robot.lg.readLine()) != null) {
			String[] robotState = line.split(",");
			double leftVoltage = Double.parseDouble(robotState[0]);
			double rightVoltage = Double.parseDouble(robotState[1]);
			boolean a = Boolean.parseBoolean(robotState[2]);
			boolean b = Boolean.parseBoolean(robotState[3]);
			boolean x = Boolean.parseBoolean(robotState[4]);
			boolean y = Boolean.parseBoolean(robotState[5]);
			boolean leftBumper = Boolean.parseBoolean(robotState[6]);
			boolean rightBumper = Boolean.parseBoolean(robotState[7]);
			boolean select = Boolean.parseBoolean(robotState[8]);
			boolean start = Boolean.parseBoolean(robotState[9]);
			boolean leftJoystickPress = Boolean.parseBoolean(robotState[10]);
			boolean rightJoystickPress = Boolean.parseBoolean(robotState[11]);
			boolean leftTrigger = Boolean.parseBoolean(robotState[12]);
			boolean rightTrigger = Boolean.parseBoolean(robotState[13]);
			
			Robot.dt.driveVoltageTank(leftVoltage, rightVoltage);
			Robot.oi.setButtonValues(a, b, x, y, leftBumper, rightBumper, select, start, leftJoystickPress, rightJoystickPress, leftTrigger, rightTrigger);
		}
		else
			end();
	}
	
	private void end() {
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TalonDefault)
			Robot.dt.setTalonDefaults();
	}
}

