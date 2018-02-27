package main;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import interfacesAndAbstracts.ImprovedClass;
import lib.joystick.XboxController;
import main.commands.commandgroups.cubeManipulator.DropCube;
import main.commands.commandgroups.cubeManipulator.DropCubeOff;
import main.commands.commandgroups.cubeManipulator.IntakeCube;
import main.commands.commandgroups.cubeManipulator.IntakeCubeOff;
import main.commands.commandgroups.cubeManipulator.PushOutCube;
import main.commands.commandgroups.cubeManipulator.PushOutCubeOff;
import main.commands.pneumatics.shift.ShiftDown;
import main.commands.pneumatics.shift.ShiftUp;

public class OI extends ImprovedClass {	
	public OI() {
		xbox.setInternalControl(false);
		xbox2.setInternalControl(false);
	
		
		xbox.leftBumper.whenPressed(new ShiftUp());
		xbox.leftBumper.whenReleased(new ShiftDown());

		xbox2.a.whenReleased(new IntakeCubeOff());
		xbox2.a.whenPressed(new IntakeCube());
		xbox2.x.whenPressed(new PushOutCube());
		xbox2.x.whenReleased(new PushOutCubeOff());
		xbox2.b.whenPressed(new DropCube());
		xbox2.b.whenReleased(new DropCubeOff());
		
	}
	
	public static XboxController getXbox() {
		return xbox;
	}
	
    public static XboxController getXbox2() {
		return xbox2;
	}
    
	public void check() {
		xbox.check();
		xbox2.check();
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
	
	public void xbox1Rumble(double intensity) {
		xbox.setRumble(LEFT_Rumble, intensity);
		xbox.setRumble(RIGHT_Rumble, intensity);
	}
	
	public void xbox1Rumble(RumbleType side, double intensity) {
		xbox.setRumble(side, intensity);
	}
	
	public void xbox2Rumble(double intensity) {
		xbox.setRumble(LEFT_Rumble, intensity);
		xbox.setRumble(RIGHT_Rumble, intensity);
	}
	
	public void xbox2Rumble(RumbleType side, double intensity) {
		xbox.setRumble(side, intensity);
	}
}
 