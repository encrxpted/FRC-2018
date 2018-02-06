package controllers;

import lib.joystick.XboxController;
import loopController.Loop;
import main.Constants;
import main.OI;
import main.Robot;

public class Record implements Loop, Constants {
	private boolean initialized = false;
	private static boolean recordOK = false;
	private XboxController controller;

	public static void okToRecord(boolean okToRecord) {
		recordOK = okToRecord;
		if(recordOK) System.out.println("Ok To Record");
		else System.out.println("Not Ok To Record");
	}
	
	@Override
	public void onStart() {
		initialized = false;
	}

	@Override
	public void onLoop() {
		if(recordOK) {
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
		System.out.println("Record Start");
		initialized = true;
		controller = OI.getXbox();
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TankDefault)
			Robot.dt.setTankDefaults();
	}
	
	private void execute() {
		System.out.println("Running record");
		Robot.lg.writeLine(Robot.dt.getLeftVoltage() + "," + Robot.dt.getRightVoltage() + "," + controller.a.get() + "," + controller.b.get() + "," + controller.x.get() 
				 + "," + controller.y.get() + "," + controller.leftBumper.get() + "," + controller.rightBumper.get() + "," + controller.select.get() + "," 
				 + controller.start.get() + "," + controller.leftJoystickPress.get() + "," + controller.rightJoystickPress.get() + "," + controller.leftTrigger.get()
				 + "," + controller.rightTrigger.get());
	}
	
	private void end() {
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TalonDefault)
			Robot.dt.setTalonDefaults();
	}
}

