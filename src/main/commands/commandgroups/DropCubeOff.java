package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.pnuematics.ArmClose;
import main.commands.pnuematics.TiltUp;

/**
 *
 */
public class DropCubeOff extends CommandGroup {

    public DropCubeOff() {
    	addParallel(new TiltUp());
    	addParallel(new ArmClose());
    }
}
