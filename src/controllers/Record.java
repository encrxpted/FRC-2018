package controllers;

import lib.Loop;
import main.Constants;
import main.OI;
import main.Robot;

public class Record implements Loop, Constants {
	private double recordCount = 0;
	
	@Override
	public void onStart() {
		recordCount = 0;
	}

	@Override
	public void onLoop() {
		if(OI.recordOn() && Robot.dt.highGear()) {
			if(recordCount == 0)	
				initialize();
			else
				recordCount++;
			execute();
		}
		else if(OI.recordOn() && !Robot.dt.highGear()) {
			System.out.println("Error: Must be in High Gear while recording.");
			recordCount = 0;
		}
		else
			end();
	}
	
	@Override
	public void onStop() {
		recordCount = 0;
	}
	
	//Helper Methods
	private void initialize() {
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TankDefault)
			Robot.dt.setTankDefaults();
	}
	
	private void execute() {
		Robot.dt.driveVelocity(OI.getXbox().getSmoothedMainY(), OI.getXbox().getSmoothedAltX());
		Robot.lg.writeLine(Robot.dt.getLeftVoltage() + "," + Robot.dt.getRightVoltage());
	}
	
	private void end() {
		if(Robot.dt.getcontrolModeConfig() != driveTrainControlConfig.TalonDefault)
			Robot.dt.setTalonDefaults();
	}
}

