package main.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import Util.EncoderHelper;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Elevator extends Subsystem implements Constants, HardwareAdapter {
	private EncoderHelper encoderHelper = new EncoderHelper();
	//max velocity was 95944u/100ms	
	public Elevator() {
		setElevatorEncoderDefaults();
		setBrakeMode();
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
	 ************************
	 * This is to make a trapezoidal motion profile for the elevator... Hopefully it will work.
	 */
	private void setStatusFrames() {
		//something goes here but idk what
	}
		
	private void setAccelAndVeloDefaults() {
		leftElevatorMaster.configMotionCruiseVelocity(cruiseVelocity, 10);
		leftElevatorMaster.configMotionAcceleration(acceleration, 10);
	}
	
	private void setPIDValues() {
		leftElevatorMaster.selectProfileSlot(elevatorIdx, pidIdx);
		leftElevatorMaster.config_kF(elevatorIdx, fGain, 10);
		leftElevatorMaster.config_kP(elevatorIdx, elevator_kP, 10);
		leftElevatorMaster.config_kI(elevatorIdx, elevator_kI, 10);
		leftElevatorMaster.config_kD(elevatorIdx, elevator_kD, 10);
	}
	
	private void setMotionMagicDefaults() {
		setStatusFrames();;
		setAccelAndVeloDefaults();
		setPIDValues();
	}
	
	public void moveToPosPID(double pos) {
		setMotionMagicDefaults();
		leftElevatorMaster.set(ControlMode.MotionMagic, pos);
	}
	
	/*************************
	 * TALON SUPPORT METHODS *
	 ************************/
	private void setBrakeMode() {
		leftElevatorMaster.setNeutralMode(BRAKE_MODE);
	}
	
	/**************************
	 * SENSOR SUPPORT METHODS *
	 **************************/
	
	private void inchesToElevatorEncoderTicks(double inches) {
		encoderHelper.inchesToEncoderTicks(inches, spindleCircum, countsPerRev);
	}
	
	private void resetElevatorEncoder() {
		leftElevatorMaster.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	// "Instantiates" the encoders onto the talon + sets things
	private void setElevatorEncoderDefaults() {
		leftElevatorMaster.configSelectedFeedbackSensor(encoder, 0, 0);
		resetElevatorEncoder();
		leftElevatorMaster.setSensorPhase(false);
	}
	
	// Checks if the intake is at bottom
	public boolean isArmAtBottom() {
		if (stage1BottomSwitch.get() /*&& stage2BottomSwitch.get()*/) return true;
		else return false;
	}
	
	// Checks if intake is at the top
	public boolean isArmAtTop() {
		if (stage1TopSwitch.get() /*&& stage2TopSwitch.get()*/)
			return true;
		else return false;
	}
	
	// Sets encoders to 0 if the arm is at the bottom (this helps to avoid offset)
	public void check() {
		if (isArmAtBottom())
			resetElevatorEncoder();
	}
	
	public double getElevatorVelocity() {
		return leftElevatorMaster.getSensorCollection().getQuadratureVelocity();
	}
	
	// Gets the number of revolutions of the encoder
	private double getElevatorRevs() {
		return leftElevatorMaster.getSensorCollection().getQuadraturePosition() / countsPerRev;
	}
	
	public double getTicksTravelled() {
		return leftElevatorMaster.getSensorCollection().getQuadraturePosition();
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
	
	public void up() {
		leftElevatorMaster.set(1.0);
	}
	
	public void down() {
		leftElevatorMaster.set(-1.0);
	}
	
	public void stop() {
		leftElevatorMaster.set(0);

	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
