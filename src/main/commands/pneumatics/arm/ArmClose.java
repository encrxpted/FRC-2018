package main.commands.pneumatics.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;

public class ArmClose extends CommandGroup implements Constants, HardwareAdapter{
    // Called just before this Command runs the first time
    public ArmClose() {
    	addSequential(new Arm(EXT));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Arm(OFF));
    }

}
