package main.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.drivetrain.DriveForward;

public class Auto1 extends CommandGroup{

	public Auto1() {
		addSequential(new DriveForward(1.5));
	}
}
