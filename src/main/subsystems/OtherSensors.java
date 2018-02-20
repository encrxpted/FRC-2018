package main.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;
import main.commands.driveAlerts.AlertDriver;

public class OtherSensors extends Subsystem implements Constants, HardwareAdapter {
	private boolean elevatorLastState;
	private boolean elevatorCurrentState;
	private Command flashLights = new AlertDriver();
	// private InternalButton alertDriverButton = new InternalButton(); //Note to
	// Self: Internal buttons crash robot code!

	public OtherSensors() {

	}

	public void check() {
		Elevator();
	}

	private void Elevator() {

		// System.out.println(gearSwitchCurrentState);
		if (elevatorLastState != elevatorCurrentState)
			flashLights.start();
		// alertDriverButton.setPressed(gearSwitchCurrentState != gearSwitchLastState);
		// alertDriverButton.whenPressed(new AlertDriver());
		elevatorLastState = elevatorCurrentState;
	}

	private boolean IntakeLastState;
	private boolean IntakeCurrentState;
	// private InternalButton alertDriverButton = new InternalButton(); //Note to
	// Self: Internal buttons crash robot code!

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
		Intake();
	}

	private void Intake() {

		System.out.println(IntakeCurrentState);
		if (IntakeLastState != IntakeCurrentState)
			flashLights.start();
		// alertDriverButton.whenPressed(new AlertDriver());
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

}
