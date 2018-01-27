package main.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;

public class OpenIntake extends CommandGroup implements Constants, HardwareAdapter{
    // Called just before this Command runs the first time
    public OpenIntake() {
    	clawOpener.set(EXT);
    	addSequential(new WaitCommand(0.1));
    	clawOpener.set(OFF);
    }

}
