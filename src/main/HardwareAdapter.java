package main;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import lib.joystick.XboxController;
import Util.RevRoboticsAnalogPressureSensor;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public interface HardwareAdapter extends Constants{
	//OI
	public static XboxController xbox = new XboxController(Xbox_Port);
	public static XboxController xbox2 = new XboxController(Xbox_Port2);
	
	//DRIVETRAIN
	public static WPI_TalonSRX leftDriveMaster = new WPI_TalonSRX(LEFT_Drive_Master);
	public static WPI_TalonSRX leftDriveSlave1 = new WPI_TalonSRX(LEFT_Drive_Slave1);
	public static WPI_TalonSRX rightDriveMaster = new WPI_TalonSRX(RIGHT_Drive_Master);
	public static WPI_TalonSRX rightDriveSlave1 = new WPI_TalonSRX(RIGHT_Drive_Slave1);
	
	//CIMBER
	public static WPI_TalonSRX climberMotor = new WPI_TalonSRX(CLIMBER_Motor);
	
	//INTAKE
	public static WPI_TalonSRX leftIntakeMotor = new WPI_TalonSRX(LEFT_Intake);
	public static WPI_TalonSRX rightIntakeMotor = new WPI_TalonSRX(RIGHT_Intake);
	
	//SENSORS
	public static RevRoboticsAnalogPressureSensor analogPressureSensor1 = new RevRoboticsAnalogPressureSensor(analogSensor);
	
	//PNEUMATICS
	public static DoubleSolenoid shifter = new DoubleSolenoid(PCM_Port1, SHIFTER_EXT, SHIFTER_RET);
	public static DoubleSolenoid lift = new DoubleSolenoid(PCM_Port1, SHIFTER_EXT, SHIFTER_RET);
	public static Compressor comp = new Compressor(PCM_Port1);
	public static DoubleSolenoid clawOpener = new DoubleSolenoid(PCM_Port1, INTAKE_EXT, INTAKE_RET);
}
