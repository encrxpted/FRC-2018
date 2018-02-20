package main.commands.pneumatics.climber;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class DeployClimber extends ImprovedCommand {
	public DeployClimber() {
		requires(Robot.cl);
	}
	
	protected void execute() {
		Robot.cl.check();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	

}
