package main;

import interfacesAndAbstracts.RobotClass;
import lib.joystick.XboxController;
import main.commands.pnuematics.ShiftDown;
import main.commands.pnuematics.ShiftUp;

public class OI extends RobotClass {
	private static OI instance;
	
	public OI() {
		xbox.setInternalControl(false);
		xbox.leftBumper.whenPressed(new ShiftUp());
		xbox.leftBumper.whenReleased(new ShiftDown());		
	}
	
	public static XboxController getXbox() {
		return xbox; 
	}
	
	public void check() {
		xbox.check();	
	}
	
	public void setButtonValues(boolean a, boolean b, boolean x, boolean y, boolean leftBumper, boolean rightBumper,
			boolean select, boolean start, boolean leftJoystickPress, boolean rightJoystickPress,
			boolean leftTrigger, boolean rightTrigger) {
		xbox.setInternalControl(true);
		xbox.setButtonStatus(a, b, x, y, leftBumper, rightBumper, select, start, leftJoystickPress, rightJoystickPress, leftTrigger, rightTrigger);
	}
	
	public void setInternalControl(boolean internalControl) {
		xbox.setInternalControl(internalControl);
	}
	
	public static boolean recordOn() {
		return xbox.a.get();
	}
	
	public static boolean playOn() {
		return xbox.b.get();
	}

	public static OI newInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}
}
 