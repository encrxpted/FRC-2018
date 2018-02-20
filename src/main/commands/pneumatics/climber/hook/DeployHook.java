package main.commands.pneumatics.climber.hook;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;

public class DeployHook extends CommandGroup implements Constants {
	
	public DeployHook() {
		addSequential(new Hook(RET));
		addSequential(new WaitCommand(0.1));
		addSequential(new Hook(OFF));
	}

}
