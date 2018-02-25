package main.commands.commandgroups.cubeManipulator;

import interfacesAndAbstracts.ImprovedCommandGroup;
import main.commands.intake.SpinIn;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.*;

public class IntakeCube extends ImprovedCommandGroup {
	public IntakeCube() {
    	addSequential(new TiltDown());
    	addSequential(new ArmOpen());
    	addSequential(new SpinIn());
    }
}
