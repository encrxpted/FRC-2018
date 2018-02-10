package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;
import main.commands.elevator.MoveUp;
import main.commands.intake.SpinIn;
import main.commands.intake.SpinOff;
import main.commands.intake.SpinOut;
import main.commands.pnuematics.Arm;
import main.commands.pnuematics.ArmClose;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.Tilt;
import main.commands.pnuematics.TiltUp;

public class DropWhenReleased extends CommandGroup implements Constants, HardwareAdapter{

	    public DropWhenReleased() {
	    	addSequential(new SpinIn());
	    	addSequential(new WaitCommand(0.15));
	    	addSequential(new SpinOff());
	    	addSequential(new MoveUp());
	    	addSequential(new SpinOff());
	    	addSequential(new SpinOut());
	    	addSequential(new WaitCommand(3.0));
	    	addSequential(new SpinOff());
	   
	    }

}