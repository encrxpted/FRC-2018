package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.commands.intake.SpinIn;
import main.commands.pneumatics.arm.ArmOpen;
import main.commands.pnuematics.tilt.*;

/**
 *
 */
public class IntakeCube extends CommandGroup implements Constants, HardwareAdapter {

    public IntakeCube() {
    	//addParallel(new TiltDown());
    	addParallel(new ArmOpen());
    	addParallel(new SpinIn());
    }
}
