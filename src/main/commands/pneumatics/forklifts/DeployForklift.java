package main.commands.pneumatics.forklifts;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;

public class DeployForklift extends CommandGroup implements Constants {
	
	public DeployForklift() {
		addSequential(new Forklift(RET));
		addSequential(new WaitCommand(0.1));
		addSequential(new Forklift(OFF));
	}


}
