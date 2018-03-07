package main.commands.drivetrain;

import Util.GeneratedMotionProfile;
import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class DriveToDistance extends ImprovedCommand {
	
	protected void initialize() {
		Robot.dt.resetMP();
		Robot.dt.setMPMode(MPDisable);
    	Robot.oi.setInternalControl(true);
    	Robot.dt.fillMPE(GeneratedMotionProfile.Points, GeneratedMotionProfile.Points);
	}
	
	protected void execute() {
		Robot.dt.setMPMode(MPEnable);
	}
	
	protected void end() {
		Robot.dt.setMPMode(MPDisable);
	}

	@Override
	protected boolean isFinished() {
		return false; //TODO THIS
	}

}
