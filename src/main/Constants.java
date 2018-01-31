package main;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public interface Constants {
	
	public static final boolean OneControllerMode = true;
	
	/*************
	 * VARIABLES *
	 *************/
	// REV ROBOTICS SENSORS
	public final int analogSensor = 0;
	
	// PNEUMATICS
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
	
	// QUAD SENSORS STUFF
	public final int countsPerRev = 1024;
	
	// ELEVATOR STUFF
	public final double spindleDiameter = 2; //placeholder
	public final double spindleCircum = Math.PI * spindleDiameter;
	public final double elevatorHeight = 86;
	public final double elevatorTolerance = 2;
	public final double switchHeight = 24;
	public final double lowScaleHeight = 65;
	public final double highscaleheight = 86;
	public final double DefaultElevatorSpeed = 0.8;
	public final double slowElevatorSpeed = 0.2;
	public final double nearSetpoint = 12;
	
	
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
	public final int LEFT_Drive_Slave2 = 10;
	public final int RIGHT_Drive_Slave2 = 11;
	
    // CLIMBER TALONS
	public final int CLIMBER_Motor = 7; 
	
	// INTAKE MOTORS	
	public final int LEFT_Intake = 0;
	public final int RIGHT_Intake = 1;
	
	// ELEVATOR MOTORS
	public final int LEFT_Elevator_Master = 8;
	public final int RIGHT_Elevator_Master = 9;
	
	// PNEUMATICS CONTROL MODULE
	public final int PCM_Port1 = 1;
	
	// INTAKE PNEUMATICS
	//public final int INTAKE_EXT = 3;
	//public final int INTAKE_RET = 2;	
	public final int INTAKE_EXT = 1;
	public final int INTAKE_RET = 0;
	public final int TILT_EXT = 5;
	public final int TILT_RET = 4;
	
	// SHIFTING
	//public final int SHIFTER_EXT = 1;
	//public final int SHIFTER_RET = 0;
	public final int SHIFTER_EXT = 3;
	public final int SHIFTER_RET = 2;
	
	// SWITCHES
	public final int STAGE1_Bottom = 0;
	public final int STAGE1_Top = 1;
	public final int STAGE2_Bottom = 2;
	public final int STAGE2_Top = 3;
	public final int driverAlertsPort = 0;
	public final int INTAKE_Switch = 5;

	//Driver Alert
		public final double alertOnTime = 0.125;
		public final double alertOffTime = 0.125;
	
}