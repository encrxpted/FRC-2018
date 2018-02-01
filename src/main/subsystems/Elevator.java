package main.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Elevator extends Subsystem implements Constants, HardwareAdapter {
	
	public Elevator() {
		setElevatorEncoderDefaults();
		setMotionMagicDefaults();
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
	
	
	/************************
	 * MOTION MAGIC METHODS *
	 ************************/
	private void setStatusFrames() {
		//something goes here but idk what
	}
	
	// Sets max/min output of talon (with percent Vbus mode)... I think...
	private void setPercentVBusDefaults() {
		leftElevatorMaster.configNominalOutputForward(nomSpeedForward, 10);
		leftElevatorMaster.configNominalOutputReverse(nomSpeedReverse, 10);
		leftElevatorMaster.configPeakOutputForward(peakSpeedForward, 10);
		leftElevatorMaster.configPeakOutputReverse(peakSpeedReverse, 10);
	}
	
	private void setAccelAndVeloDefaults() {
		//something goes here.
	}
	
	private void setPIDValues() {
		//I think something is supposed to go here too.
	}
	
	private void setMotionMagicDefaults() {
		setStatusFrames();
		setPercentVBusDefaults();
		setAccelAndVeloDefaults();
		setPIDValues();
	}
	
	/**************************
	 * SENSOR SUPPORT METHODS *
	 **************************/
	
	private void resetElevatorEncoder() {
		leftElevatorMaster.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	// "Instantiates" the encoders onto the talon + sets things
	private void setElevatorEncoderDefaults() {
		leftElevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		resetElevatorEncoder();
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
	public void check() {
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
	
	// Returns whether or not the intake has reached the set position. Pos is in inches
	public boolean isIntakeAtPos(double pos) {
		if (getDistanceFromPos(pos) < elevatorTolerance && getDistanceFromPos(pos) > -1 * elevatorTolerance) {
			return true;
		}
		else return false;
	}
	
	// Returns whether or not the elevator is close to set position
	private boolean isIntakeNearPos(double pos) {
		if (getDistanceFromPos(pos) < nearSetpoint && getDistanceFromPos(pos) > -1* nearSetpoint) {
			return true;
		}
		else return false;
	}
	
	// Returns if the intake is currently below the desired position or not
	private boolean isIntakeBelowPos(double pos) {
		if (getDistanceFromPos(pos) > 0) return true;
		else return false;
	}
	
	
	/********************
	 * MOVEMENT METHODS *
	 ********************/
	
	// Moves fast to a position if far away, slows down when it gets closer, and stops when it reaches
	// the position within a tolerance.
	public void moveToPos(double pos) {
//		if(isIntakeAtPos(pos)) {
//			leftElevatorMaster.set(0);
//		}
		if (isIntakeNearPos(pos)) {
			if (isIntakeBelowPos(pos)) leftElevatorMaster.set(slowElevatorSpeed);
			else leftElevatorMaster.set(-1 * slowElevatorSpeed);
		}
		else {
			if (isIntakeBelowPos(pos)) leftElevatorMaster.set(defaultElevatorSpeed);
			else leftElevatorMaster.set(defaultElevatorSpeed * -1); 
		}
	}

	/*private void moveToScale() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}
	
	private void moveToSwich() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}*/

	public void moveDown() {
		if (isArmAtBottom()) {
			leftElevatorMaster.set(0);
		}
		else if (isIntakeNearPos(0)) {
			leftElevatorMaster.set(-1 * slowElevatorSpeed);
		}
		else {
			leftElevatorMaster.set(-1 * defaultElevatorSpeed);
		}
		//rightElevatorMaster.set(0.1);
	}
	
	public void moveUp() {
		if (isArmAtTop()) {
			leftElevatorMaster.set(0);
			//rightElevatorMaster.set(-0.1);
		}
		else if(isIntakeNearPos(elevatorHeight)) {
			leftElevatorMaster.set(slowElevatorSpeed);
		}
		else {
			leftElevatorMaster.set(defaultElevatorSpeed);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
