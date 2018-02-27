package main.commands.rumble;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class Xbox1Rumble extends ImprovedCommand {
	private double intensity;
	
	public Xbox1Rumble(double intensity) {
		this.intensity = intensity;
		requires(Robot.rb);
	}
	
	protected void execute() {
		Robot.rb.xbox1Rumble(intensity);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	

}
