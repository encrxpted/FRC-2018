package main.commands.pnuematics;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.Robot;

/**
 *
 */
public class ShiftDown extends CommandGroup implements Constants{
    
    public  ShiftDown() {
    	addSequential(new Shift(EXT));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Shift(OFF));
    	if(Robot.dt.highGear())
    		Robot.dt.changeGearing();
    }
}
