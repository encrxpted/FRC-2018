package main.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;
import main.Robot;

// TODO: implement this class
public class DriveForward extends Command {
	// private double dist = 0;
	
    public DriveForward(double dist) {
    	requires(Robot.dt);
    	// this.dist = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	// updates as execution continues... add driveDistance
    }
    
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
