package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;
import main.commands.intake.SpinOut;


public class PushOutCube extends CommandGroup {

    public PushOutCube() {
    	//addSequential(new TiltDown());
    	addSequential(new SpinOut());
    	addSequential(new WaitCommand(.2));
    	//addSequential(new ArmOpen());
    }
}
