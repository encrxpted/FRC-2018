package main.commands.drivetrain;

import main.Constants;
import main.HardwareAdapter;
import main.Robot;

public class Play extends Drive implements Constants, HardwareAdapter {
	@Override
	protected void initialize() {
		Robot.dt.setTankDefaults();
	}
	
	//Add in logic to prevent erroring once the end of the textfile has been reached
	//and no more actions can be found.
	@Override
	protected void execute() {  
		String action = Robot.lg.readLine();
		double left = Double.parseDouble(action.substring(0, action.indexOf(',')));
		double right = Double.parseDouble(action.substring(action.indexOf(',') + 1, action.length()));
    	Robot.dt.driveVoltageTank(left, right);
    }
	
	@Override
	protected void end() {
		Robot.dt.setTalonDefaults();
	}
}
