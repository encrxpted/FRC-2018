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
    	addSequential(new ArmClose());
    	addSequential(new SpinOff());
    	addSequential(new TiltUp());
    }
}
