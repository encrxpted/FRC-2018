package main.subsystems;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import interfacesAndAbstracts.ImprovedSubsystem;

public class Rumble extends ImprovedSubsystem {
	
	public void xbox1Rumble(double intensity) {
		xbox.setRumble(LEFT_Rumble, intensity);
		xbox.setRumble(RIGHT_Rumble, intensity);
	}
	
	public void xbox1Rumble(RumbleType side, double intensity) {
		xbox.setRumble(side, intensity);
	}
	
	public void xbox2Rumble(double intensity) {
		xbox2.setRumble(LEFT_Rumble, intensity);
		xbox2.setRumble(RIGHT_Rumble, intensity);
	}
	
	public void xbox2Rumble(RumbleType side, double intensity) {
		xbox2.setRumble(side, intensity);
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
