package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.commands.intake.SpinOff;

public class DanceOff extends CommandGroup implements Constants, HardwareAdapter {
	public DanceOff() {
		addSequential(new SpinOff());
	}
}
