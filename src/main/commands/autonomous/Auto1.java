package main.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.drivetrain.DriveForward;
import main.commands.drivetrain.Drive;


public class Auto1 extends CommandGroup{

	public void Auto1() {
		addSequential(new DriveForward(1.5));
	}
}
