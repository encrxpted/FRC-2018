package main.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.commands.commandgroups.PushOutCube;
import main.commands.commandgroups.PushOutCubeOff;
import main.commands.drivetrain.TimedDrive;

/**
 * The whole logic for auto.
 * 
 * @author TheSuperDuck
 *
 */
public class Auto extends CommandGroup {

	public Auto() {
		addSequential(new TimedDrive(0.3, 1));
		addSequential(new TimedDrive(0.3, 0.5, 1));
		addSequential(new PushOutCube());
		addSequential(new WaitCommand(1));
		addSequential(new PushOutCubeOff());
	}
}
