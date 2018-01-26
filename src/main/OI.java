package main;

import lib.joystick.XboxController;
import main.commands.intake.*;
import main.commands.pnuematics.ShiftDown;
import main.commands.pnuematics.ShiftUp;

public class OI implements Constants, HardwareAdapter {
	
	public OI() {
		check();
	}
	
	public static XboxController getXbox() {
		return xbox; 
	}
	
	public void check() {
		xbox.leftBumper.whenPressed(new ShiftUp());
		xbox.leftBumper.whenReleased(new ShiftDown());
		xbox.b.whenPressed(new CloseIntake());
		xbox.x.whenPressed(new OpenIntake());
		xbox.a.whenPressed(new SpinIn());
		xbox.y.whenPressed(new SpinOut());
	}

}
 