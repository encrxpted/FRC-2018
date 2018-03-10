package main.commands.drivetrain;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class DriveFromMPPlayer extends ImprovedCommand {
	
	public DriveFromMPPlayer() {
		requires(Robot.dt);
	}
	
	protected void execute() {
    	if(Robot.dt.isLastPoint()) Robot.dt.setMPMode(MPHold);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	

}
