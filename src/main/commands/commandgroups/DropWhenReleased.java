package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.HardwareAdapter;
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
	    	addSequential(new SpinIn(), 5);
	    	addSequential(new WaitCommand(0.1));
	    	addSequential(new SpinOut(), 5);
	    	addSequential(new WaitCommand(0.1));
	    	addSequential(new SpinIn(), 5);
	    	addSequential(new SpinOff());

	   
	    }

}