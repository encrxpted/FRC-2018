package main.commands.drivetrain;

import main.Robot;

public class Play extends Drive {
	//Add in logic to prevent erroring once the end of the textfile has been reached
	//and no more actions can be found.
	@Override
	protected void execute() {  
		String action = Robot.lg.readLine();
		double y = Double.parseDouble(action.substring(0, action.indexOf(',')));
		double x = Double.parseDouble(action.substring(action.indexOf(',') + 1, action.length()));
    	Robot.dt.driveVelocity(y, x);
    }
	
}
