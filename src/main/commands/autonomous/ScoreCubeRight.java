package main.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.commands.commandgroups.IntakeCube;
import main.commands.commandgroups.IntakeCubeOff;
import main.commands.drivetrain.TimedDrive;

/**
 *
 */
public class ScoreCubeRight extends CommandGroup {

    public ScoreCubeRight() {
		addSequential(new TimedDrive(0.6, 3));
		addSequential(new TimedDrive(0.5, 0.6, 3));
		addSequential(new IntakeCube());
		addSequential(new WaitCommand(3));
		addSequential(new IntakeCubeOff());
    }
}
