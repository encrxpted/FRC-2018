package main.commands.pnuematics;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;

public class PTOOff extends CommandGroup implements Constants, HardwareAdapter {
	
	public PTOOff() {
    	addSequential(new PTO(RET));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new PTO(OFF));
	}

}

