package main.commands.controllerCommands;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class DoNothing extends ImprovedCommand {
	protected void initialize() {
		Robot.lg.changePath("00000000000000000000000000000000000000000000000000000000000", true);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
