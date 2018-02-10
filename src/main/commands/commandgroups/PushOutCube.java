package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.intake.SpinOut;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.TiltDown;

/**
 *
 */
public class PushOutCube extends CommandGroup {

    public PushOutCube() {
    	addSequential(new TiltDown());
    	addSequential(new SpinOut());
    	addSequential(new ArmOpen());
    }
}
