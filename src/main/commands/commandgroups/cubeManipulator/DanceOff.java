package main.commands.commandgroups.cubeManipulator;

import interfacesAndAbstracts.ImprovedCommandGroup;
import main.commands.intake.SpinOff;

public class DanceOff extends ImprovedCommandGroup {
	public DanceOff() {
		addSequential(new SpinOff());
	}
}
