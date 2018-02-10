package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;
import main.commands.pnuematics.Arm;
import main.commands.pnuematics.Tilt;

public class  Dropwhenpressed extends CommandGroup implements Constants, HardwareAdapter{
	    // Called just before this Command runs the first time
	    public Dropwhenpressed() {
	    	addSequential(new Tilt(EXT));
	    	addSequential(new WaitCommand(0.1));
	    	addSequential(new Tilt(OFF));
	    	addSequential(new Arm(RET));
	    	addSequential(new WaitCommand(0.1));
	    	addSequential(new Arm(OFF)); 
	    }
}
