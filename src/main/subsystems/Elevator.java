package main.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Elevator extends Subsystem implements Constants, HardwareAdapter {
	
	public Elevator() {
		setElevatorEncoderDefaults();
		resetElevatorEncoder();
	}
	/*
	private static enum ElevatorPosition {
		Top, Bottom, Neither
	}
	
	private static enum ElevatorState {
		Up, Down, Off
	}*/
	
	//private static ElevatorPosition elevatorPosition = ElevatorPosition.Bottom;
	//private static ElevatorState elevatorState = ElevatorState.Off;
	
	/**************************
	 * SENSOR SUPPORT METHODS *
	 **************************/
	
	private void resetElevatorEncoder() {
		leftElevatorMaster.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	// "Instantiates" the encoders onto the talon
	private void setElevatorEncoderDefaults() {
		leftElevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
	}
	
	// Checks if the intake is at bottom
	private boolean isArmAtBottom() {
		if (stage1BottomSwitch.get() == true && stage2BottomSwitch.get() == true) 
			return true;
		else return false;
	}
	
	// Checks if intake is at the top
	private boolean isArmAtTop() {
		if (stage1TopSwitch.get() == true && stage2TopSwitch.get() == true)
			return true;
		else return false;
	}
	
	// Sets encoders to 0 if the arm is at the bottom (this helps to avoid offset)
	private void zeroElevatorEncoder() {
		if (isArmAtBottom() == true)
			resetElevatorEncoder();
	}
	
	// Gets the number of revolutions of the encoder
	private double getElevatorRevs() {
		return leftElevatorMaster.getSensorCollection().getQuadraturePosition() / countsPerRev;
	}
	
	// Get the distance the elevator has travelled
	private double getDistanceTravelled() {
		return getElevatorRevs() * spindleCircum;
	}
	
	// Returns distance from a set position
	private double getDistanceFromPos(double pos) {
		return pos - getDistanceTravelled();
	}
	
	// Returns whether or not the intake has reached the set position
	private boolean isIntakeAtPos(double pos) {
		if (getDistanceFromPos(pos) < elevatorTolerance && getDistanceFromPos(pos) > -1 * elevatorTolerance) {
			return true;
		}
		else return false;
	}
	
	
	/********************
	 * MOVEMENT METHODS *
	 ********************/

	/*private void moveToScale() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}
	
	private void moveToSwich() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}*/
	
	public void driveToPos(double pos) {
	}
	
	private void moveDown() {
		if (!isArmAtBottom()) {
			leftElevatorMaster.set(-0.1);
			rightElevatorMaster.set(0.1);
		}
	}
	
	private void moveUp() {
		if (!isArmAtTop()) {
			leftElevatorMaster.set(0.1);
			rightElevatorMaster.set(-0.1);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
