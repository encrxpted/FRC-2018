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
    	addParallel(new TiltDown());
    	addParallel(new SpinOut());
    	addParallel(new ArmOpen());
    }
}
