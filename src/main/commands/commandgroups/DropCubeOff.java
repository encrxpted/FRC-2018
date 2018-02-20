package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.pneumatics.arm.ArmClose;
import main.commands.pneumatics.tilt.TiltUp;

public class DropCubeOff extends CommandGroup {
	public DropCubeOff() {
		addSequential(new ArmClose());
    	addSequential(new TiltUp());
    }
}
