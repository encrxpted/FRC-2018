package main.commands.controllerCommands;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class FilePicker extends ImprovedCommand {
	
	public FilePicker(String filePath) {
		Robot.lg.changePath(filePath, false);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
