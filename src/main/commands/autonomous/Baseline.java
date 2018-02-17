package main.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.drivetrain.TimedDrive;

/**
 *
 */
public class Baseline extends CommandGroup {

    public Baseline() {
    	addSequential(new TimedDrive(0.6, 3));
    }
}
