package main.commands.drivetrain;

import com.ctre.phoenix.motion.TrajectoryPoint;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class DriveMotionProfile extends ImprovedCommand {
	private TrajectoryPoint leftPoint, rightPoint;
	
	public DriveMotionProfile(TrajectoryPoint leftPoint, TrajectoryPoint rightPoint) {
		this.leftPoint = leftPoint;
		this.rightPoint = rightPoint;
	}
	
	protected void execute() {
		Robot.dt.pushPoints(leftPoint, rightPoint);
		Robot.dt.setMPMode(MPEnable);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
