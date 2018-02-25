package main.commands.commandgroups.cubeManipulator;

import interfacesAndAbstracts.ImprovedCommandGroup;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.TiltDown;;

public class DropCube extends ImprovedCommandGroup {
	public DropCube() {
		addSequential(new TiltDown());
		addSequential(new ArmOpen());
	}
}

