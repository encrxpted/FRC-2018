package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.commands.intake.SpinIn;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pneumatics.tilt.*;

public class IntakeCube extends CommandGroup implements Constants, HardwareAdapter {
	public IntakeCube() {
    	addSequential(new TiltDown());
    	addSequential(new ArmOpen());
    	addSequential(new SpinIn());
    }
}
