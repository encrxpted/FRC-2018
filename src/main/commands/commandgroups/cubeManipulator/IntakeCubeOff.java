package main.commands.commandgroups.cubeManipulator;

import interfacesAndAbstracts.ImprovedCommandGroup;
import main.commands.intake.SpinOff;
import main.commands.pneumatics.arm.ArmClose;
import main.commands.pneumatics.tilt.TiltUp;

public class IntakeCubeOff extends ImprovedCommandGroup {
	public IntakeCubeOff() {
		addSequential(new ArmClose());
    	addSequential(new TiltUp());
    	addSequential(new SpinOff());
    }
}
