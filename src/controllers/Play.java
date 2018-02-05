package controllers;

import lib.Loop;
import main.Constants;
import main.OI;
import main.Robot;

public class Play implements Loop, Constants {
private boolean initialized = false;
	
	@Override
	public void onStart() {
		initialized = false;
	}

	@Override
	public void onLoop() {
		if(OI.playOn()) {
			if(!initialized)
				initialize();
			execute();
		}
		else
			end();
	}
	
	@Override
	public void onStop() {
		initialized = false;
	}
	
	//Helper Methods
	private void initialize() {
		initialized = true;
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TankDefault)
			Robot.dt.setTankDefaults();
	}
	
	private void execute() {
		String action = Robot.lg.readLine();
		if(action != null) {
			double left = Double.parseDouble(action.substring(0, action.indexOf(',')));
			double right = Double.parseDouble(action.substring(action.indexOf(',') + 1, action.length()));
			Robot.dt.driveVoltageTank(left, right);
		}
		else
			end();
	}
	
	private void end() {
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TalonDefault)
			Robot.dt.setTalonDefaults();
	}
}

