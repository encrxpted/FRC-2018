package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.intake.SpinOff;
import main.commands.pnuematics.ArmClose;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.TiltDown;
import main.commands.pnuematics.TiltUp;

/**
 *
 */
public class IntakeCubeOff extends CommandGroup {

    public IntakeCubeOff() {
    	addParallel(new ArmClose());
    	addParallel(new SpinOff());
    	addParallel(new TiltUp());
    }
}
