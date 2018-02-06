package main.commands.drivetrain;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;
import main.OI;

/**
 *go ask alex if you can change record/play to a toggle button and change isFinished to false so the 
 *command only runs once and setting voltage can be done the easy way
 */
public class Drive extends ImprovedCommand {

    public Drive() {
    	requires(Robot.dt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	Robot.dt.driveVelocity(-OI.getXbox().getSmoothedMainY(), -OI.getXbox().getSmoothedAltX());
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