package main.commands.drivetrain;

import main.OI;
import main.Robot;

public class Record extends Drive { 
	@Override
    protected void execute() {
		double y = OI.getXbox().getSmoothedMainY();
		double x = OI.getXbox().getSmoothedAltX();
		
		Robot.lg.writeLine(y + "," + x);
    	Robot.dt.driveVelocity(y, x);
    }
}
