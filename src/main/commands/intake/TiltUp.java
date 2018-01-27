package main.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;
import main.Robot;

public class TiltUp extends CommandGroup implements Constants, HardwareAdapter{
    // Called just before this Command runs the first time
    public TiltUp() {
    	tilter.set(RET);
    	addSequential(new WaitCommand(0.1));
    	tilter.set(OFF);
    }
}