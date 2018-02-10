package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.intake.SpinOff;
import main.commands.intake.SpinOut;
import main.commands.pnuematics.ArmClose;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.TiltDown;
import main.commands.pnuematics.TiltUp;

/**
 *
 */
public class PushOutCubeOff extends CommandGroup {

    public PushOutCubeOff() {
    	addParallel(new TiltUp());
    	addParallel(new SpinOff());
    	addParallel(new ArmClose());
    }
}
