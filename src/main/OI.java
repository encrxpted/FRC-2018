package main;

import lib.joystick.XboxController;
import main.commands.intake.*;
import main.commands.pnuematics.ArmClose;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.ShiftDown;
import main.commands.pnuematics.ShiftUp;
import main.commands.pnuematics.TiltDown;
import main.commands.pnuematics.TiltUp;

public class OI implements Constants, HardwareAdapter {
	
	public OI() {
		check();
	}
	
	public static XboxController getXbox() {
		return xbox; 
	}
	
	// important
	public void check() {
		// pneumatics
		xbox.leftBumper.whenPressed(new ShiftUp());
		xbox.leftBumper.whenReleased(new ShiftDown());
		xbox.b.whenPressed(new ArmClose());
		xbox.x.whenPressed(new ArmOpen());
		xbox.leftTrigger.whenPressed(new TiltUp());
		xbox.rightTrigger.whenPressed(new TiltDown());
		// intake
		xbox.a.whenPressed(new SpinIn());
		xbox.y.whenPressed(new SpinOut());
		xbox.a.whenReleased(new SpinOff());
		xbox.y.whenReleased(new SpinOff());
		// Elevator
	}

}
 