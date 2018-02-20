package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import main.commands.intake.SpinOut;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.TiltDown;

public class PushOutCube extends CommandGroup {
	public PushOutCube() {
    	addSequential(new SpinOut());
    	addSequential(new TiltDown());
    	addSequential(new ArmOpen());
    }
}
