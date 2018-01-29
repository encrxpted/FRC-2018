package main.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;
import main.commands.drivetrain.Drive;

public class Intake extends Subsystem implements Constants, HardwareAdapter {
	public static enum WheelStates {
		In, Out, Off
	}
	public static enum PneumaticStates {
		Opened, Closed
	}
	public static enum LiftStates {
		Up, Down
	}
	
	public static WheelStates wheelStates = WheelStates.Off;
	
	public void spinIn() {
		leftIntakeMotor.set(1.0);
    	rightIntakeMotor.set(-1.0);
    	wheelStates = WheelStates.In;

	}
	
	public void spinOut() {
		leftIntakeMotor.set(-1.0);
    	rightIntakeMotor.set(1.0);
    	wheelStates = WheelStates.Out;

	}
	
	public void spinOff() {
    	leftIntakeMotor.set(0.0);
    	rightIntakeMotor.set(0.0);
    	wheelStates = WheelStates.Off;

	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
