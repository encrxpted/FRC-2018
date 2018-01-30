package main.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class Elevator extends Subsystem implements Constants, HardwareAdapter {
	public static enum ElevatorStates {
		MoveUp, MoveDown, Top, Bottom, Stopped
	}
	
	/**************************
	 * SENSOR SUPPORT METHODS *
	 **************************/
	
	public void resetElevatorEncoders() {
		leftElevatorMaster.getSensorCollection().setQuadraturePosition(0, 10);
		rightElevatorMaster.getSensorCollection().setQuadraturePosition(0, 10); 
	}
	
	public void setElevatorEncoderDefaults() {
		leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
	}
	
	/********************
	 * MOVEMENT METHODS *
	 ********************/

	public void moveToScale() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}
	
	public void moveToSwich() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(1.0);
	}
	
	public void moveDown() {
		leftElevatorMaster.set(-1.0);
		rightElevatorMaster.set(1.0);
	}
	
	public void moveUp() {
		leftElevatorMaster.set(1.0);
		rightElevatorMaster.set(-1.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
}
