package main.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import Util.DriveHelper;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import interfacesAndAbstracts.ImprovedSubsystem;
import main.commands.drivetrain.Drive;

public class Drivetrain extends ImprovedSubsystem  {
	private static DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
	
	private final double maxSpeed = 1000; // TODO TEST FOR THIS VALUE
	private final double fGain = 1023 / maxSpeed;
	private final double kD = 0;
	private final double kP = 2;
	private final double kI = 0;
	
	//TELEOP DRIVING
	private DriveHelper helper = new DriveHelper(7.5);

	public Drivetrain() {
		setTalonDefaults();
		setFeedBackDefaults();
		setPIDDefaults();
	}
	
	// DRIVE FOR TELEOP
	public void driveVelocity(double throttle, double heading) {
		driveTrain.arcadeDrive(helper.handleOverPower(helper.handleDeadband(throttle, throttleDeadband)),
				helper.handleOverPower(helper.handleDeadband(heading, headingDeadband)));
	}
	
	//Drive for playing back
	public void driveVoltageTank(double leftVoltage, double rightVoltage) {
		driveTrain.tankDrive((Math.abs(leftVoltage) > 12.0) ? Math.signum(leftVoltage) : leftVoltage/12, 
								 -((Math.abs(rightVoltage)  > 12.0) ? Math.signum(rightVoltage) : rightVoltage/12),
								 false);	
	}
	
	/***********************
	 * PLAY/RECORD METHODS *
	 ***********************/
	public double getLeftVoltage() {
		return (leftDriveMaster.getMotorOutputVoltage() + leftDriveSlave1.getMotorOutputVoltage())/2;
	}
	
	public double getRightVoltage() {
		return (rightDriveMaster.getMotorOutputVoltage() + rightDriveSlave1.getMotorOutputVoltage())/2;
	}
	
	/*************************
	 * DRIVE SUPPORT METHODS *
	 *************************/
	private void reverseTalons(boolean isInverted) {
		leftDriveMaster.setInverted(isInverted);
		rightDriveMaster.setInverted(isInverted);
		leftDriveSlave1.setInverted(isInverted);
		//leftDriveSlave2.setInverted(!isInverted);
		rightDriveSlave1.setInverted(isInverted);
		//rightDriveSlave2.setInverted(!isInverted);
	}

	private void setBrakeMode(NeutralMode mode) {
		leftDriveMaster.setNeutralMode(mode);
		leftDriveSlave1.setNeutralMode(mode);
		//leftDriveSlave2.setNeutralMode(mode);
		rightDriveMaster.setNeutralMode(mode);
		rightDriveSlave1.setNeutralMode(mode);
		//rightDriveSlave2.setNeutralMode(mode);
	}

	private void setCtrlMode() {
		leftDriveSlave1.follow(leftDriveMaster);
		rightDriveSlave1.follow(rightDriveMaster);
		//leftDriveSlave2.follow(leftDriveMaster);
		//rightDriveSlave2.follow(rightDriveMaster);
	}
	
	private void setVoltageComp(boolean set, double voltage, int timeout) {
		//Voltage Compensation
		leftDriveMaster.enableVoltageCompensation(set);
		leftDriveSlave1.enableVoltageCompensation(set);
//		leftDriveSlave2.enableVoltageCompensation(set);
		rightDriveMaster.enableVoltageCompensation(set);
		rightDriveSlave1.enableVoltageCompensation(set);
//		rightDriveSlave2.enableVoltageCompensation(set);
		leftDriveMaster.configVoltageCompSaturation(voltage, timeout);
		leftDriveSlave1.configVoltageCompSaturation(voltage, timeout);
//		leftDriveSlave2.configVoltageCompSaturation(voltage, timeout);
		rightDriveMaster.configVoltageCompSaturation(voltage, timeout);
		rightDriveSlave1.configVoltageCompSaturation(voltage, timeout);
//		rightDriveSlave2.configVoltageCompSaturation(voltage, timeout);
		//Nominal and peak outputs
		leftDriveMaster.configPeakOutputForward(1.0, timeout);
		leftDriveSlave1.configPeakOutputForward(1.0, timeout);
//		leftDriveSlave2.configPeakOutputForward(1.0, timeout);
		rightDriveMaster.configPeakOutputForward(1.0, timeout);
		rightDriveSlave1.configPeakOutputForward(1.0, timeout);
//		rightDriveSlave2.configPeakOutputForward(1.0, timeout);
		leftDriveMaster.configPeakOutputReverse(-1.0, timeout);
		leftDriveSlave1.configPeakOutputReverse(-1.0, timeout);
//		leftDriveSlave2.configPeakOutputReverse(-1.0, timeout);
		rightDriveMaster.configPeakOutputReverse(-1.0, timeout);
		rightDriveSlave1.configPeakOutputReverse(-1.0, timeout);
//		rightDriveSlave2.configPeakOutputReverse(-1.0, timeout);
		leftDriveMaster.configNominalOutputForward(0.0, timeout);
		leftDriveSlave1.configNominalOutputForward(0.0, timeout);
//		leftDriveSlave2.configNominalOutputForward(0.0, timeout);
		rightDriveMaster.configNominalOutputForward(0.0, timeout);
		rightDriveSlave1.configNominalOutputForward(0.0, timeout);
//		rightDriveSlave2.configNominalOutputForward(0.0, timeout);
		leftDriveMaster.configNominalOutputReverse(0.0, timeout);
		leftDriveSlave1.configNominalOutputReverse(0.0, timeout);
//		leftDriveSlave2.configNominalOutputReverse(0.0, timeout);
		rightDriveMaster.configNominalOutputReverse(0.0, timeout);
		rightDriveSlave1.configNominalOutputReverse(0.0, timeout);
//		rightDriveSlave2.configNominalOutputReverse(0.0, timeout);
	}
	
	public void setTalonDefaults() {
		reverseTalons(false);
		setBrakeMode(BRAKE_MODE);
		setCtrlMode();
		setVoltageComp(true, voltageCompensationVoltage, 10);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}

	@Override
	public void check() {
		// TODO Auto-generated method stub
	}
	
	/***
	 * ENCODERS
	 */
	
	private void setFeedBackDefaults() {
		leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, leftDriveIdx, timeout);
		rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, rightDriveIdx, timeout);
		leftDriveMaster.setSensorPhase(true);
		rightDriveMaster.setSensorPhase(true);
	}
	
	private void setPIDDefaults() {
		leftDriveMaster.selectProfileSlot(leftDriveIdx, pidIdx);
		leftDriveMaster.config_kF(leftDriveIdx, fGain, timeout);
		leftDriveMaster.config_kP(leftDriveIdx, kP, timeout);
		leftDriveMaster.config_kI(leftDriveIdx, kI, timeout);
		leftDriveMaster.config_kD(leftDriveIdx, kD, timeout);
		
		rightDriveMaster.selectProfileSlot(rightDriveIdx, pidIdx);
		rightDriveMaster.config_kF(rightDriveIdx, fGain, timeout);
		rightDriveMaster.config_kP(rightDriveIdx, kP, timeout);
		rightDriveMaster.config_kI(rightDriveIdx, kI, timeout);
		rightDriveMaster.config_kD(rightDriveIdx, kD, timeout);
		
		leftDriveMaster.configMotionProfileTrajectoryPeriod(baseTimeout, timeout);
		rightDriveMaster.configMotionProfileTrajectoryPeriod(baseTimeout, timeout);
		leftDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, frameRate, timeout);
		rightDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, frameRate, timeout);
	}
	
	public double getLeftVelocity() {
		return leftDriveMaster.getSensorCollection().getQuadratureVelocity();
	}
	
	public double getRightVelocity() {
		return rightDriveMaster.getSensorCollection().getQuadratureVelocity();
	}
	
	public double getLeftEncoderPosition() {
		return leftDriveMaster.getSensorCollection().getQuadraturePosition();
	}
	
	public double getRightEncoderPosition() {
		return rightDriveMaster.getSensorCollection().getQuadraturePosition();
	}

	@Override
	public void zeroSensors() {
		leftDriveMaster.getSensorCollection().setQuadraturePosition(0, 10);
		rightDriveMaster.getSensorCollection().setQuadraturePosition(0, 10); 
	}	
}

