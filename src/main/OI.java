package main;

import lib.joystick.XboxController;

public class OI implements Constants, HardwareAdapter {
	
	public OI() {
		check();
	}
	
	public static XboxController getXbox() {
		return xbox; 
	}
	
	public void check() {
		
	}

}
 