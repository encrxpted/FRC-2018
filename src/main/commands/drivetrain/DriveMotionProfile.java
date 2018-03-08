package main.commands.drivetrain;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class DriveMotionProfile extends ImprovedCommand {
	private double [][]leftProfile;
	private double [][]rightProfile;

	public DriveMotionProfile(double [][]leftProfile, double [][]rightProfile) {
		this.leftProfile = leftProfile;
		this.rightProfile = rightProfile;
	}
	
	protected void initialize() {
		Robot.dt.resetMP();
		Robot.dt.setMPMode(MPDisable);
		Robot.dt.fillMPE(leftProfile, rightProfile);
	}
	
	protected void execute() {
		Robot.dt.setMPMode(MPEnable);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
