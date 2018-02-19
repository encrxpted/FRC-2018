package main;

import edu.wpi.first.wpilibj.command.CommandGroup;

import lib.joystick.XboxController;
import main.commands.commandgroups.IntakeCube;
import main.commands.commandgroups.IntakeCubeOff;
import main.commands.commandgroups.PushOutCube;
import main.commands.commandgroups.PushOutCubeOff;
import main.commands.pneumatics.shift.ShiftDown;
import main.commands.pneumatics.shift.ShiftUp;

public class OI extends CommandGroup implements Constants, HardwareAdapter {
	public static OI instance;
	public static boolean controllerMode = false;

	public void check() {
		xbox.check();
		xbox2.check();
	}
	
	public static XboxController getXbox() {
		return xbox;
	}
	
    public static XboxController getXbox2() {
		return xbox2;
	}
    
    public static void configure() {
		xbox.leftBumper.whenPressed(new ShiftUp());
		xbox.leftBumper.whenReleased(new ShiftDown());
		
    	if (controllerMode) {
			xbox.a.whenReleased(new IntakeCubeOff());
			xbox.a.whenPressed(new IntakeCube());
			xbox.x.whenPressed(new PushOutCube());
			xbox.x.whenReleased(new PushOutCubeOff());
		} 
    	else {
			xbox2.a.whenReleased(new IntakeCubeOff());
			xbox2.a.whenPressed(new IntakeCube());
			xbox2.x.whenPressed(new PushOutCube());
			xbox2.x.whenReleased(new PushOutCubeOff());
		}
    }
    
	
	/**************
	 * PLAY/RECORD *
	 ***************/
	public void setButtonValues(boolean a, boolean b, boolean x, boolean y, boolean leftBumper, boolean rightBumper,
			boolean select, boolean start, boolean leftJoystickPress, boolean rightJoystickPress,
			boolean leftTrigger, boolean rightTrigger) {
		xbox.setInternalControl(true);
		xbox.setButtonStatus(a, b, x, y, leftBumper, rightBumper, select, start, leftJoystickPress, rightJoystickPress, leftTrigger, rightTrigger);
	}
	
	public void setButtonValues2(boolean a, boolean b, boolean x, boolean y, boolean leftBumper, boolean rightBumper,
			boolean select, boolean start, boolean leftJoystickPress, boolean rightJoystickPress,
			boolean leftTrigger, boolean rightTrigger) {
		xbox2.setInternalControl(true);
		xbox2.setButtonStatus(a, b, x, y, leftBumper, rightBumper, select, start, leftJoystickPress, rightJoystickPress, leftTrigger, rightTrigger);
	}
	
	public void setInternalControl(boolean internalControl) {
		xbox.setInternalControl(internalControl);
		xbox2.setInternalControl(internalControl);
	}

	public static OI newInstance() {
		if (instance == null) {
			instance = new OI();
		}
		return instance;
	}
    public static boolean OneController(){
    	controllerMode = true;
    	configure();
    	return controllerMode;
    }
    
	public static boolean TwoController(){
    	controllerMode = false;
    	configure();
    	return controllerMode;
    }
}
 