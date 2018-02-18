package main.commands.controllerCommands;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class FilePicker extends ImprovedCommand {
	private String filePath = "";
	
	public FilePicker(String filePath) {
		this.filePath = filePath;
		this.setName(filePath);
		System.out.println("filepicker here");
	}
	
	protected void initialize() {
		Robot.lg.changePath(filePath, false);
		System.out.println("Changed Path");
		System.out.println(filePath);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
