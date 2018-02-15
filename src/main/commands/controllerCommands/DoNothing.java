package main.commands.controllerCommands;

import interfacesAndAbstracts.ImprovedCommand;

public class DoNothing extends ImprovedCommand {

	@Override
	protected boolean isFinished() {
		return false;
	}

}
