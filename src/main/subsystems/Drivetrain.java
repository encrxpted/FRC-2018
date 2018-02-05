package main.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import Util.DriveHelper;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import interfacesAndAbstracts.RobotSubsystem;
import main.commands.drivetrain.Drive;

public class Drivetrain extends RobotSubsystem {
	public static Drivetrain instance;
	private static DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
	private static boolean highGearState = defaultHighGearState;
	private static driveTrainControlConfig controlModeConfig;
	//TELEOP DRIVING
	private DriveHelper helper = new DriveHelper(7.5);
	
	public Drivetrain() {
		setTalonDefaults();
	}
	
	//DRIVE FOR TELEOP
	public void driveVelocity(double throttle, double heading) {
		driveTrain.arcadeDrive(helper.driveSmooth(throttle), helper.handleOverPower(helper.handleDeadband(heading, headingDeadband)));
	}
	
	public void driveVoltageTank(double leftVoltage, double rightVoltage) {
		leftDriveMaster.set(leftVoltage/voltageCompensationVoltage);
		rightDriveMaster.set(rightVoltage/voltageCompensationVoltage);
	}
	
	public double getLeftVoltage() {
		return leftDriveMaster.getMotorOutputVoltage();
	}
	
	public double getRightVoltage() {
		return rightDriveMaster.getMotorOutputVoltage();
	}
	
	/*************************
	 * DRIVE SUPPORT METHODS *
	 *************************/
	
	public void changeGearing(){
		highGearState = !highGearState;
	}
	
	public boolean highGear() {
		return highGearState;
	}

	private void reverseTalons(boolean isInverted) {
		leftDriveMaster.setInverted(isInverted);
		rightDriveMaster.setInverted(isInverted);
		leftDriveSlave1.setInverted(isInverted);
		leftDriveSlave2.setInverted(!isInverted);
		rightDriveSlave1.setInverted(isInverted);
		rightDriveSlave2.setInverted(!isInverted);
	}

	private void setBrakeMode(NeutralMode mode) {
		leftDriveMaster.setNeutralMode(mode);
		leftDriveSlave1.setNeutralMode(mode);
		leftDriveSlave2.setNeutralMode(mode);
		rightDriveMaster.setNeutralMode(mode);
		rightDriveSlave1.setNeutralMode(mode);
		rightDriveSlave2.setNeutralMode(mode);
	}

	private void setCtrlMode() {
		leftDriveSlave1.follow(leftDriveMaster);
		rightDriveSlave1.follow(rightDriveMaster);
		leftDriveSlave2.follow(leftDriveMaster);
		rightDriveSlave2.follow(rightDriveMaster);
	}
	

	public void setTalonDefaults() {
		reverseTalons(false);
		setBrakeMode(BRAKE_MODE);
		leftDriveMaster.enableVoltageCompensation(false);
		rightDriveMaster.enableVoltageCompensation(false);
		setCtrlMode();
		controlModeConfig = driveTrainControlConfig.TalonDefault;
	}
	
	public void setTankDefaults() {
		setTalonDefaults();
		leftDriveMaster.enableVoltageCompensation(true);
		leftDriveMaster.configVoltageCompSaturation(voltageCompensationVoltage, 10);
		rightDriveMaster.enableVoltageCompensation(true);
		rightDriveMaster.configVoltageCompSaturation(voltageCompensationVoltage, 10);
		controlModeConfig = driveTrainControlConfig.TankDefault;
	}
	
	public driveTrainControlConfig getcontrolModeConfig() {
		return controlModeConfig;
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Drivetrain newInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}
	
}

