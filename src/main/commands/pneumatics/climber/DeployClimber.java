package main.commands.pneumatics.climber;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class DeployClimber extends ImprovedCommand {
	
	protected void execute() {
		Robot.cl.check();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	

}
