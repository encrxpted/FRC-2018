package main.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.commands.pnuematics.*;

/**
 *
 */
public class IntakeCube extends CommandGroup implements Constants, HardwareAdapter {

    public IntakeCube() {
    	addSequential(new TiltDown());
    	addSequential(new ArmOpen());
    	addSequential(new SpinIn());
    }
}
