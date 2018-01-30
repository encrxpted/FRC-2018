package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Elevator extends Subsystem implements Constants, HardwareAdapter {
	public static enum ElevatorStates {
		MoveUp, MoveDown, Top, Bottom, Stopped
	}
	
	public void moveToScale() {
		leftElevatorMotor.set(1.0);
		rightElevatorMotor.set(1.0);
	}
	
	public void moveToSwich() {
		leftElevatorMotor.set(1.0);
		rightElevatorMotor.set(1.0);
	}
	
	public void moveDown() {
		leftElevatorMotor.set(-1.0);
		rightElevatorMotor.set(1.0);
	}
	
	public void moveUp() {
		leftElevatorMotor.set(1.0);
		rightElevatorMotor.set(-1.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
