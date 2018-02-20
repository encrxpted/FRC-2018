package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import main.commands.intake.SpinOut;
import main.commands.pneumatics.tilt.TiltDown;


public class PushOutCube extends CommandGroup {

    public PushOutCube() {
    	addSequential(new TiltDown());
    	addSequential(new SpinOut());
    }
}
