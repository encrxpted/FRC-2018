package main.commands.pneumatics.pto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;

public class EngagePTO extends CommandGroup implements Constants, HardwareAdapter {
	
	public EngagePTO() {
    	addSequential(new PTO(EXT));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new PTO(OFF));
	}

}