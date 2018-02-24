package main.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import interfacesAndAbstracts.RobotSubsystem;
import main.commands.driveAlerts.AlertDriver;

public class OtherSensors extends RobotSubsystem {
	private boolean elevatorLastState;
	private boolean elevatorCurrentState;
	private Command flashLights = new AlertDriver();

	public OtherSensors() {
	}

	public void check() {
		elevator();
	}

	private void elevator() {
		if (elevatorLastState != elevatorCurrentState)
			flashLights.start();
		elevatorLastState = elevatorCurrentState;
	}

	private boolean IntakeLastState;
	private boolean IntakeCurrentState;

	@Override
	protected void initDefaultCommand() {
	}

	public boolean isIntakeLastState() {
		return elevatorLastState;
	}

	public void setIntakeLastState(boolean elevatorLastState) {
		this.elevatorLastState = elevatorLastState;
	}

	public void check1() {
		intake();
	}

	private void intake() {

		System.out.println(IntakeCurrentState);
		if (IntakeLastState != IntakeCurrentState)
			flashLights.start();
		IntakeLastState = IntakeCurrentState;
	}

	protected void initDefaultCommand1() {
	}

	public boolean isElevatorLastState() {
		return IntakeLastState;
	}

	public void setElevatorLastState(boolean elevatorLastState) {
		this.IntakeLastState = elevatorLastState;
	}

	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
		
	}
}
