package controllers;

import lib.joystick.XboxController;

import loopController.Loop;
import main.Constants;
import main.OI;
import main.Robot;

public class Record implements Loop, Constants {
	private static boolean recordOK = false;
	private static XboxController controller = OI.getXbox();

	public static void okToRecord(boolean okToRecord) {
		recordOK = okToRecord;
		if(recordOK) System.out.println("Ok To Record");
		else System.out.println("Not Ok To Record");
	}
	
	@Override
	public void onStart() {
	}

	@Override
	public void onLoop() {
		if(recordOK)
			execute();
	}
	
	@Override
	public void onStop() {
	}
	
	private void execute() {
		System.out.println("Running record");
		Robot.lg.writeLine(Robot.dt.getLeftVoltage() + "," + Robot.dt.getRightVoltage() + "," + controller.a.get() + "," + controller.b.get() + "," + controller.x.get() 
				 + "," + controller.y.get() + "," + controller.leftBumper.get() + "," + controller.rightBumper.get() + "," + controller.select.get() + "," 
				 + controller.start.get() + "," + controller.leftJoystickPress.get() + "," + controller.rightJoystickPress.get() + "," + controller.leftTrigger.get()
				 + "," + controller.rightTrigger.get());
	}
}

