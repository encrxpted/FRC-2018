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
	
	// pneumatics
	public final int armUp = 0;
	public final int armDown = 0;
	
	// FILE WRITING
	public final String outputPath = "/home/lvuser/Output.txt";
	
	// JOYSTICK DEADBANDS
	public final double throttleDeadband = 0.03;
	public final double headingDeadband = 0.02;
	
	// DRIVEHELPER
	public final double smoothThrottleDif = 0.1; //Set to max difference you want
	
	// SENSORS
	public final boolean encodersInverted = true;
	public final int sensorTimeoutMs = 10; //timeout for sensor methods. 10ms will allow checking to be performed.
	
	
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
	
	
	/*********
	 * PORTS *
	 *********/
	// XBOX PORTS
	public final int Xbox_Port = 0;
	public final int Xbox_Port2 = 1;
	
	
	// DRIVETRAIN TALONS (CAN BUS)
	public final int LEFT_Drive_Master = 3;
	public final int LEFT_Drive_Slave1 = 6;
	public final int RIGHT_Drive_Master = 2;
	public final int RIGHT_Drive_Slave1 = 5;
	
    // CLIMBER TALONS
	public final int CLIMBER_Motor = 7; 
	
	// INTAKE MOTORS	
	public final int LEFT_Intake = 0;
	public final int RIGHT_Intake = 0;
	public final int PIVOT_Intake = 0;
	
	// pneumatic arm #1
	public final int Arm1_EXT = 1;
	public final int Arm1_RET = 0;
	public final int PCM_Port3 = 2;
	
	//pneumatic arm #2
	public final int Arm2_EXT = 1;
	public final int Arm2_RET = 0;
	public final int PCM_Port2 = 3;

	
	// SHIFTING
	public final int SHIFTER_EXT = 1;
	public final int SHIFTER_RET = 0;
	public final int PCM_Port1 = 1;
	public final int INTAKE_EXT = 2;
	public final int INTAKE_RET = 3;
}
