package main.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import interfacesAndAbstracts.ImprovedCommand;
import main.Constants;
import main.HardwareAdapter;
import main.Robot;

/**
 *
 */
public class MoveUp extends ImprovedCommand implements Constants, HardwareAdapter{	
    public MoveUp() {
    	requires(Robot.el);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//System.out.println("D-Pad Up Pressed");
    	//Robot.el.moveUp();
    	Robot.el.up();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
