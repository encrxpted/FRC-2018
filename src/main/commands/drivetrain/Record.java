package main.commands.drivetrain;

import main.Constants;
import main.HardwareAdapter;
import main.OI;
import main.Robot;

public class Record extends Drive {
	@Override
	protected void initialize() {
		Robot.dt.setTankDefaults();
	}
	
	@Override
    protected void execute() {
    	Robot.dt.driveVelocity(OI.getXbox().getSmoothedMainY(), OI.getXbox().getSmoothedAltX());
		Robot.lg.writeLine(Robot.dt.getLeftVoltage() + "," + Robot.dt.getRightVoltage());
    } 
	
	@Override
	protected void end() {
		Robot.dt.setTalonDefaults();
	}
}
