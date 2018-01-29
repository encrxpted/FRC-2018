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
}
