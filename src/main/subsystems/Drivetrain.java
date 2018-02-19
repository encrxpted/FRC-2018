package main.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;

import Util.DriveHelper;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import main.Constants;
import main.HardwareAdapter;
import main.OI;
import main.commands.drivetrain.Drive;
import main.commands.pnuematics.DisengagePTO;
import main.commands.pnuematics.EngagePTO;

public class Drivetrain extends Subsystem implements Constants, HardwareAdapter {
	private static DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
	private static Drivetrain instance;
	private static boolean highGearState = defaultHighGearState;
	private static driveTrainControlConfig controlModeConfig;
	
	//TELEOP DRIVING
	private DriveHelper helper = new DriveHelper(7.5);
	private boolean engaged = false;
	
	//GYRO
	private static AHRS NavX;

	public Drivetrain() {
		setTalonDefaults();
	}
	
	//DRIVE FOR TELEOP
	public void driveVelocity(double throttle, double heading) {
		if(!OI.getXbox().start.get() || !OI.getXbox2().start.get()) { // either/both not pressed- normal drive and disengage PTO
			if(engaged) {
				Command disengagePto = new DisengagePTO();
				disengagePto.start();
				engaged = false;
			}
			driveTrain.arcadeDrive(helper.driveSmooth(throttle), helper.handleOverPower(helper.handleDeadband(heading, headingDeadband)));
		}
		else { // both pressed- can only drive forward and engage PTO
			if(!engaged) {
				Command engagePto = new EngagePTO();
				engagePto.start();
				engaged = true;
			}
			driveTrain.arcadeDrive(Math.abs(helper.driveSmooth(throttle)), 0.0);
		}
	}
	
	//Drive for playing back
	public void driveVoltageTank(double leftVoltage, double rightVoltage) {
		if(controlModeConfig == driveTrainControlConfig.TankDefault) {	
			driveTrain.tankDrive((Math.abs(leftVoltage) > 12.0) ? Math.signum(leftVoltage) : leftVoltage/12, 
								 -((Math.abs(rightVoltage)  > 12.0) ? Math.signum(rightVoltage) : rightVoltage/12),
								 false);
		if(!rightDriveMaster.isAlive() || !rightDriveSlave1.isAlive() || !rightDriveSlave2.isAlive() ||
		   !leftDriveMaster.isAlive() || !leftDriveSlave1.isAlive() || !leftDriveSlave2.isAlive())
			System.out.println("Problem");
		}
	}
	
	/***********************
	 * PLAY/RECORD METHODS *
	 ***********************/
	public double getLeftVoltage() {
		return leftDriveMaster.getMotorOutputVoltage();
	}
	
	public double getRightVoltage() {
		return rightDriveMaster.getMotorOutputVoltage();
	}
	
	public double getLeftBusVoltage() {
		return leftDriveMaster.getBusVoltage();
	}
	
	
	/*************************
	 * SENSOR OUTPUT METHODS *
	 *************************/
	//SET CONVERSION FACTOR AND WHEEL SIZE BEFORE USING THESE METHODS
//	public int convertToEncoderTicks(double displacement) {//ft
//		return (int) (((displacement / (wheelSize*Math.PI)) * conversionFactor));
//	}
//	public double getDistanceTraveledLeft() {//Feet
//		return wheelSize*Math.PI*(getLeftEncoderPosition()/conversionFactor);
//	}
//	
//	public double getDistanceTraveledRight() {//Feet
//		//Removed - value and changed with reverseSensor() so that pid has correct feedback
//		//System.out.println("r" +wheelSize*Math.PI*(getRightEncoderPosition()/conversionFactor));
//		return wheelSize*Math.PI*(getRightEncoderPosition()/conversionFactor);
//	}
//	
//	public double getDistanceAvg() {
//		return (-getDistanceTraveledLeft() + getDistanceTraveledRight())/2; 
//	}
	
	public double getLeftVelocity() {
		return leftDriveMaster.getSensorCollection().getQuadratureVelocity();// / wheelEncoderMult;
	}
	
	public double getRightVelocity() {
		return rightDriveMaster.getSensorCollection().getQuadratureVelocity();// / wheelEncoderMult;
	}
	
	@SuppressWarnings("unused")
	private double getLeftEncoderPosition() {
		return leftDriveMaster.getSensorCollection().getQuadraturePosition();
	}
	
	@SuppressWarnings("unused")
	private double getRightEncoderPosition() {
		return rightDriveMaster.getSensorCollection().getQuadraturePosition();
	}
	
	/**************************
	 * SENSOR SUPPORT METHODS *
	 **************************/
	public void resetEncoders() {
		leftDriveMaster.getSensorCollection().setQuadraturePosition(0, 10); //Alex is gay
		rightDriveMaster.getSensorCollection().setQuadraturePosition(0, 10); //Alex is gay
		//he said it, not me 
	}
	
	public AHRS getGyro(){
		return NavX;
	}
	
	public void resetGyro() {
		NavX.reset();
		NavX.zeroYaw();
	}
	
	public void resetSensors() {
//		resetGyro(); 
		resetEncoders();
	}
	
	public void driveDistance() {
		// not sure what goes here
	}
	
	@SuppressWarnings("unused")
	private void setFeedBackDefaults(int timeoutMs) {
		leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMs);
		rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, timeoutMs);
		//I don't think the coder per rev thing is needed anymore...
		// "Units are 4X measurements where X units = X quadrature edges. Measured in change per 100ms."
//		leftDriveMaster.configEncoderCodesPerRev(codesPerRev);
//		rightDriveMaster.configEncoderCodesPerRev(codesPerRev);
		leftDriveMaster.setSensorPhase(encodersInverted);
		rightDriveMaster.setSensorPhase(encodersInverted);
	}
	
	//TODO: CONVERT METHODS INTO NEW ONES
//	private void setVoltageDefaults() {
		// NO LONGER MEASURED IN VOLTS. MEASURES THROTTLE IN [-1, 1]
//		leftDriveMaster.configNominalOutputVoltage(+0f, -0f);
//		rightDriveMaster.configNominalOutputVoltage(+0f, -0f);
//		leftDriveMaster.configPeakOutputVoltage(+12f, -12f);
//		rightDriveMaster.configPeakOutputVoltage(+12f, -12f);
//	}

//	private void setRampRate(double ramp) {
//		leftDriveMaster.setVoltageCompensationRampRate(ramp);
//		rightDriveMaster.setVoltageCompensationRampRate(ramp);
//	}

	/*************************
	 * DRIVE SUPPORT METHODS *
	 *************************/
	
	public void changeGearing(){
		highGearState = !highGearState;
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
		//Voltage Compensation
		leftDriveMaster.enableVoltageCompensation(set);
		leftDriveSlave1.enableVoltageCompensation(set);
		leftDriveSlave2.enableVoltageCompensation(set);
		rightDriveMaster.enableVoltageCompensation(set);
		rightDriveSlave1.enableVoltageCompensation(set);
		rightDriveSlave2.enableVoltageCompensation(set);
		leftDriveMaster.configVoltageCompSaturation(voltage, timeout);
		leftDriveSlave1.configVoltageCompSaturation(voltage, timeout);
		leftDriveSlave2.configVoltageCompSaturation(voltage, timeout);
		rightDriveMaster.configVoltageCompSaturation(voltage, timeout);
		rightDriveSlave1.configVoltageCompSaturation(voltage, timeout);
		rightDriveSlave2.configVoltageCompSaturation(voltage, timeout);
		//Nominal and peak outputs
		leftDriveMaster.configPeakOutputForward(1.0, timeout);
		leftDriveSlave1.configPeakOutputForward(1.0, timeout);
		leftDriveSlave2.configPeakOutputForward(1.0, timeout);
		rightDriveMaster.configPeakOutputForward(1.0, timeout);
		rightDriveSlave1.configPeakOutputForward(1.0, timeout);
		rightDriveSlave2.configPeakOutputForward(1.0, timeout);
		leftDriveMaster.configPeakOutputReverse(-1.0, timeout);
		leftDriveSlave1.configPeakOutputReverse(-1.0, timeout);
		leftDriveSlave2.configPeakOutputReverse(-1.0, timeout);
		rightDriveMaster.configPeakOutputReverse(-1.0, timeout);
		rightDriveSlave1.configPeakOutputReverse(-1.0, timeout);
		rightDriveSlave2.configPeakOutputReverse(-1.0, timeout);
		leftDriveMaster.configNominalOutputForward(0.0, timeout);
		leftDriveSlave1.configNominalOutputForward(0.0, timeout);
		leftDriveSlave2.configNominalOutputForward(0.0, timeout);
		rightDriveMaster.configNominalOutputForward(0.0, timeout);
		rightDriveSlave1.configNominalOutputForward(0.0, timeout);
		rightDriveSlave2.configNominalOutputForward(0.0, timeout);
		leftDriveMaster.configNominalOutputReverse(0.0, timeout);
		leftDriveSlave1.configNominalOutputReverse(0.0, timeout);
		leftDriveSlave2.configNominalOutputReverse(0.0, timeout);
		rightDriveMaster.configNominalOutputReverse(0.0, timeout);
		rightDriveSlave1.configNominalOutputReverse(0.0, timeout);
		rightDriveSlave2.configNominalOutputReverse(0.0, timeout);
	}
	
	public void setTalonDefaults() {
		reverseTalons(false);
		setBrakeMode(BRAKE_MODE);
		setCtrlMode();
		setVoltageComp(true, voltageCompensationVoltage, 10);
		controlModeConfig = driveTrainControlConfig.TalonDefault;
	}
	
	public void setTankDefaults() {
		setTalonDefaults();
		controlModeConfig = driveTrainControlConfig.TankDefault;
	}
	
	public driveTrainControlConfig getcontrolModeConfig() {
		return controlModeConfig;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
	public static Drivetrain newInstance() {
		if (instance == null) {
			instance = new Drivetrain();
		}
		return instance;
	}
	
}

