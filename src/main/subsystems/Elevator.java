package main.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Elevator extends Subsystem implements Constants, HardwareAdapter {
	
	public Elevator() {
		setElevatorEncoderDefaults();
	}
	
	public static enum ElevatorPosition {
		Top, Bottom, Neither
	}
	
	public static enum ElevatorState {
		Up, Down, Off
	}
	
	public static ElevatorPosition elevatorPosition = ElevatorPosition.Bottom;
	public static ElevatorState elevatorState = ElevatorState.Off;
	
	/**************************
	 * SENSOR SUPPORT METHODS *
	 **************************/
	
	public void resetElevatorEncoder() {
		leftElevatorMaster.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	// "Instantiates" the encoders onto the respective talons
	public void setElevatorEncoderDefaults() {
		leftElevatorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
	}
	
	// Checks if the intake is at bottom
	public boolean isArmAtBottom() {
		if (stage1BottomSwitch.get() == true && stage2BottomSwitch.get() == true) 
			return true;
		else return false;
	}
	
	// Checks if intake is at the top
	public boolean isArmAtTop() {
		if (stage1TopSwitch.get() == true && stage2TopSwitch.get() == true)
			return true;
		else return false;
	}
	
	// Sets encoders to 0 if the arm is at the bottom (this helps to avoid offset)
	public void zeroElevatorEncoder() {
		if (isArmAtBottom() == true)
			resetElevatorEncoder();
	}
	
	// Gets the number of revolutions of the encoder
	public double getElevatorRevs() {
		return leftElevatorMaster.getSensorCollection().getQuadraturePosition() / countsPerRev;
	}
	
	// Get the distance the elevator has travelled
	public double getDistanceTravelled() {
		return getElevatorRevs() * spindleCircum;
	}
	
	
	
	/********************
	 * MOVEMENT METHODS *
	 ********************/

	/*public void moveToScale() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}
	
	public void moveToSwich() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}*/
	
	public void moveDown() {
		leftElevatorMaster.set(-0.1);
		rightElevatorMaster.set(0.1);
	}
	
	public void moveUp() {
		leftElevatorMaster.set(0.1);
		rightElevatorMaster.set(-0.1);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
