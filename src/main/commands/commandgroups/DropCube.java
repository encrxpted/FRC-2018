package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import main.Constants;
import main.HardwareAdapter;
import main.commands.intake.SpinIn;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.TiltDown;;

public class DropCube extends CommandGroup implements Constants, HardwareAdapter {
	public DropCube() {
		addParallel(new TiltDown());
		addParallel(new ArmOpen());
	}
}

