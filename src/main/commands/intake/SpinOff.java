package main.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import main.Constants;
import main.HardwareAdapter;

public class SpinOff extends Command implements Constants, HardwareAdapter{
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftIntakeMotor.set(0.0);
    	rightIntakeMotor.set(0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
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
