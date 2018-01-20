package main;

import edu.wpi.first.wpilibj.XboxController;

public class oi implements Constants, HardwareAdapter {
	
	public oi() {
		check();
	}
	
	public static XboxController getXbox (){
		return xbox;
	}
	
	public void check() {
		
	}

}
