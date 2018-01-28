package controllers;

import lib.Loop;
import main.Constants;
import main.OI;
import main.Robot;

public class Play implements Loop, Constants {
private double playCount = 0;
	
	@Override
	public void onStart() {
		playCount = 0;
	}

	@Override
	public void onLoop() {
		if(OI.playOn() && Robot.dt.highGear()) {
			if(playCount == 0)	
				initialize();
			else
				playCount++;
			execute();
		}
		else if(OI.playOn() && !Robot.dt.highGear()) {
			System.out.println("Error: Must be in High Gear while playing.");
			playCount = 0;
		}
		else
			end();
	}
	
	@Override
	public void onStop() {
		playCount = 0;
	}
	
	//Helper Methods
	private void initialize() {
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

