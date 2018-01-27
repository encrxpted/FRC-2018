package main.commands.pnuematics;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;

public class TiltDown extends CommandGroup implements Constants, HardwareAdapter{
    // Called just before this Command runs the first time
    public TiltDown() {
    	addSequential(new Tilt(EXT));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Tilt(OFF));
    }
}