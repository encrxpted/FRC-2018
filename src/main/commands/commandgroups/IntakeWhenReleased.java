package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.commands.intake.SpinIn;
import main.commands.intake.SpinOff;
import main.commands.pnuematics.ArmClose;
import main.commands.pnuematics.TiltUp;

public class IntakeWhenReleased extends CommandGroup implements Constants, HardwareAdapter {
	private double secondsToSpinIn = 0.5;
	
	public IntakeWhenReleased() {
		addSequential(new SpinIn(), secondsToSpinIn);
		addParallel(new ArmClose());
		addSequential(new SpinOff());
		addSequential(new TiltUp());
	}
	

}
