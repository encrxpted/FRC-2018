package main;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public interface Constants {
	
	/*************
	 * VARIABLES *
	 *************/
	// REV ROBOTICS SENSORS
	public final int analogSensor = 0;
	
	// FILE WRITING
	public final String outputPath = "/home/lvuser/Test2.txt";
	
	// JOYSTICK DEADBANDS
	public final double throttleDeadband = 0.02;
	public final double headingDeadband = 0.02;
	
	// DRIVE HELPER
	public final double smoothThrottleDif = 0.1;
	
	
	/*************
	 * CONSTANTS *
	 *************/
	// PNEUMATIC STATES
	public final DoubleSolenoid.Value EXT = Value.kForward;
	public final DoubleSolenoid.Value RET = Value.kReverse;
	public final DoubleSolenoid.Value OFF = Value.kOff;
	
	// TALON CONTROL MODES
	public final ControlMode SLAVE_MODE = ControlMode.Follower;
	public final ControlMode PERCENT_VBUS_MODE = ControlMode.PercentOutput;
	public final NeutralMode BRAKE_MODE = NeutralMode.Brake;
	public final NeutralMode COAST_MODE = NeutralMode.Coast;
	
	//TALON VOLTAGE COMPENSATION
	public final double voltageCompensationVoltage = 12.0;
	
	//LOOPER CONSTANTS
	public final double kLooperDt = 0.005;//0.005

	//DRIVETRAIN CONSTANTS
	//false: default low gear - true: default high gear
	public final boolean defaultHighGearState = false;
	//Possible DriveTrain Control Configurations
	public enum driveTrainControlConfig {
		TalonDefault(), TankDefault()
	}
	
	
	/*********
	 * PORTS *
	 *********/
	// XBOX PORTS
	public final int Xbox_Port = 0;
	
	// DRIVETRAIN TALONS (CAN BUS)
	public final int LEFT_Drive_Master = 3;
	public final int LEFT_Drive_Slave1 = 6;
	public final int RIGHT_Drive_Master = 2;
	public final int RIGHT_Drive_Slave1 = 5;
	public final int LEFT_Drive_Slave2 = 9;
	public final int RIGHT_Drive_Slave2 = 4;

	// SHIFTING
	public final int SHIFTER_EXT = 1;
	public final int SHIFTER_RET = 0;
	public final int PCM_Port = 1;
}
