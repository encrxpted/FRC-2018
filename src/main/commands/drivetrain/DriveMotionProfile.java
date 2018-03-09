package main.commands.drivetrain;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

/*
 * This class is to help out with the PID tuning. It uses generated motion profiles (not recorded)
 * This command simply drives to a motion profile- it's not used in play/record
 */
public class DriveMotionProfile extends ImprovedCommand {
	private double [][]leftProfile;
	private double [][]rightProfile;

	public DriveMotionProfile(double [][]leftProfile, double [][]rightProfile) {
		this.leftProfile = leftProfile;
		this.rightProfile = rightProfile;
	}
	
	protected void initialize() {
		Robot.dt.setMPMode(MPDisable);
		Robot.dt.resetMP();
		Robot.dt.fillMPE(leftProfile, rightProfile);
		if(Robot.dt.isEnoughPoints()) Robot.dt.setMPMode(MPEnable);
	}
	
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
		Robot.dt.setMPMode(MPDisable);
	}

}
