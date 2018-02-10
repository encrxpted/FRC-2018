package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.TiltDown;

public class IntakeWhileHeld extends CommandGroup implements Constants, HardwareAdapter {
	public IntakeWhileHeld() {
		addSequential(new TiltDown());
		addSequential(new ArmOpen());
	}
	
	public void execute() {
		
	}

}
