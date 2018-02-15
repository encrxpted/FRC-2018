package main.commands.controllerCommands;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class FileCreator extends ImprovedCommand {
	public  FileCreator() {
		Robot.lg.createNewFile(Robot.getNewFileName());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
