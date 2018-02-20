package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.commands.intake.SpinOff;
import main.commands.pneumatics.arm.ArmClose;
import main.commands.pneumatics.tilt.TiltUp;

/**
 *
 */
public class IntakeCubeOff extends CommandGroup {

    public IntakeCubeOff() {
    	addParallel(new SpinOff());
    	addParallel(new TiltUp());
    	addParallel(new ArmClose());
    }
}
