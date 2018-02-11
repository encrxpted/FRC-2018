package main.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import Util.DriveHelper;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import interfacesAndAbstracts.RobotSubsystem;
import main.commands.drivetrain.Drive;

public class Drivetrain extends RobotSubsystem {
	private static Drivetrain instance;
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
		if(controlModeConfig == driveTrainControlConfig.TalonDefault) {
			driveTrain.arcadeDrive(helper.driveSmooth(throttle), helper.handleOverPower(helper.handleDeadband(heading, headingDeadband)));
		}
	}
	
	public void driveVoltageTank(double leftVoltage, double rightVoltage) {
		if(controlModeConfig == driveTrainControlConfig.TankDefault) {
			//Logic to prevent over-driving from potentially larger than +-12 recorded voltages
			//if(Math.abs(leftVoltage) > 12.0 || Math.abs(rightVoltage) > 12.0) {
//				leftDriveMaster.set(Math.signum(leftVoltage));
//				rightDriveMaster.set(Math.signum(rightVoltage));
				//driveTrain.tankDrive(Math.signum(leftVoltage), Math.signum(rightVoltage));
				// what if left voltage > 12 but right voltage = 5?
			//}
			//else {
				//12 is a fixed number and not the voltageComp target
//				leftDriveMaster.set(leftVoltage/12);
//				rightDriveMaster.set(rightVoltage/12);
			driveTrain.tankDrive((Math.abs(leftVoltage) > 12.0) ? Math.signum(leftVoltage) : leftVoltage/12, 
								 (Math.abs(rightVoltage)  > 12.0) ? Math.signum(rightVoltage) : rightVoltage/12);
			//}
			
			if(!rightDriveMaster.isAlive() || !rightDriveSlave1.isAlive() || !rightDriveSlave2.isAlive() ||
			   !leftDriveMaster.isAlive() || !leftDriveSlave1.isAlive() || !leftDriveSlave2.isAlive())
				System.out.println("Problem");
		}
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
	
	private void setVoltageComp(boolean set, double voltage, int timeout) {
		leftDriveMaster.enableVoltageCompensation(set);
		leftDriveSlave1.enableVoltageCompensation(set);
		leftDriveSlave2.enableVoltageCompensation(set);
		rightDriveMaster.enableVoltageCompensation(set);
		rightDriveSlave1.enableVoltageCompensation(set);
		rightDriveSlave2.enableVoltageCompensation(set);

		if(set == true) {
			leftDriveMaster.configVoltageCompSaturation(voltage, timeout);
			leftDriveSlave1.configVoltageCompSaturation(voltage, timeout);
			leftDriveSlave2.configVoltageCompSaturation(voltage, timeout);
			rightDriveMaster.configVoltageCompSaturation(voltage, timeout);
			rightDriveSlave1.configVoltageCompSaturation(voltage, timeout);
			rightDriveSlave2.configVoltageCompSaturation(voltage, timeout);
		}
	}
	
	private void setSafetyTimeout(double timeout) {
		leftDriveMaster.setExpiration(timeout);
		leftDriveSlave1.setExpiration(timeout);
		leftDriveSlave2.setExpiration(timeout);
		rightDriveMaster.setExpiration(timeout);
		rightDriveSlave1.setExpiration(timeout);
		rightDriveSlave2.setExpiration(timeout);
	}
	
	private void setSafety(boolean safety) {
		leftDriveMaster.setSafetyEnabled(safety);
		leftDriveSlave1.setSafetyEnabled(safety);
		leftDriveSlave2.setSafetyEnabled(safety);
		rightDriveMaster.setSafetyEnabled(safety);
		rightDriveSlave1.setSafetyEnabled(safety);
		rightDriveSlave2.setSafetyEnabled(safety);
	}

	public void setTalonDefaults() {
		reverseTalons(false);
		setBrakeMode(BRAKE_MODE);
		setCtrlMode();
		setVoltageComp(false, 0.0, 0);
		//setSafetyTimeout(10000);
		//setSafety(false);
		controlModeConfig = driveTrainControlConfig.TalonDefault;
	}
	
	public void setTankDefaults() {
		reverseTalons(false);
		setBrakeMode(COAST_MODE);
		setCtrlMode();
		setVoltageComp(true, voltageCompensationVoltage, 10);
		//setSafety(false);
		//setSafetyTimeout(10000);
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
	
	public static Drivetrain newInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}
}

