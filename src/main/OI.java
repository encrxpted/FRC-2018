package main;

import lib.joystick.XboxController;
import main.commands.drivetrain.Play;
import main.commands.drivetrain.Record;
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
		xbox.rightBumper.whileHeld(new Record());
		xbox.a.whileHeld(new Play());
	}

}
 