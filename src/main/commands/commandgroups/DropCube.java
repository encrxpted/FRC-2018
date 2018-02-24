package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import main.Constants;
import main.HardwareAdapter;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.TiltDown;;

public class DropCube extends CommandGroup implements Constants, HardwareAdapter {
	public DropCube() {
		addSequential(new TiltDown());
		addSequential(new ArmOpen());
	}
}

