package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Elevator extends Subsystem implements Constants, HardwareAdapter {
	public static enum ElevatorStates {
		MoveUp, MoveDown, Top, Bottom, Stopped
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	public void MoveToScale() {
		leftElevatorMotor.set(1.0);
		rightElevatorMotor.set(1.0);
		
	}
	public void MoveToSwich() {
		leftElevatorMotor.set(1.0);
		rightElevatorMotor.set(1.0);
		
	}
	public void Movedown() {
		leftElevatorMotor.set(1.0);
		rightElevatorMotor.set(1.0);
		
	}
	public void Moveup() {
		leftElevatorMotor.set(1.0);
		rightElevatorMotor.set(1.0);
	}
}
