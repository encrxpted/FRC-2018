package main.subsystems;


import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import Util.DriveHelper;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import interfacesAndAbstracts.ImprovedSubsystem;
import main.commands.drivetrain.Drive;

public class Drivetrain extends ImprovedSubsystem  {
	private static DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
	private MotionProfileStatus status = new MotionProfileStatus();
	
	// MOTION PROFILE PID CONSTANTS
	private final double leftMaxSpeed = 1000; // TODO TEST FOR THIS VALUE
	private final double leftFGain = 1023 / leftMaxSpeed;
	private final double leftkP = 2;
	private final double leftkI = 0;
	private final double leftkD = 0;
	private final double rightMaxSpeed = 1000; // TODO TEST FOR THIS VALUE
	private final double rightFGain = 1023 / rightMaxSpeed;
	private final double rightkP = 2;
	private final double rightkI = 0;
	private final double rightkD = 0;
	
	//TELEOP DRIVING
	private DriveHelper helper = new DriveHelper(7.5);

	public Drivetrain() {
		setTalonDefaults();
		setEncoderDefaults();
		setPIDDefaults();
		setMPDefaults();
		zeroSensors();
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

	/****************
	 * PID SETTINGS *
	 ****************/
	
	// Configures the PID loops, f-gain, p-gain, I-gain, and D-gain for each talon
	private void setPIDDefaults() {
		// selectProfileSlot: You can have up to 4 different PID loops. This method chooses which loop you are using
		// for each talon. 
		leftDriveMaster.selectProfileSlot(leftDriveIdx, pidIdx);
		leftDriveMaster.config_kF(leftDriveIdx, leftFGain, timeout);
		leftDriveMaster.config_kP(leftDriveIdx, leftkP, timeout);
		leftDriveMaster.config_kI(leftDriveIdx, leftkI, timeout);
		leftDriveMaster.config_kD(leftDriveIdx, leftkD, timeout);
		
		rightDriveMaster.selectProfileSlot(rightDriveIdx, pidIdx);
		rightDriveMaster.config_kF(rightDriveIdx, rightFGain, timeout);
		rightDriveMaster.config_kP(rightDriveIdx, rightkP, timeout);
		rightDriveMaster.config_kI(rightDriveIdx, rightkI, timeout);
		rightDriveMaster.config_kD(rightDriveIdx, rightkD, timeout);
	}
	
	// Configures settings for the motion profile
	private void setMPDefaults() {
		leftDriveMaster.configMotionProfileTrajectoryPeriod(baseTimeout, timeout);
		rightDriveMaster.configMotionProfileTrajectoryPeriod(baseTimeout, timeout);
		leftDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, frameRate, timeout);
		rightDriveMaster.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, frameRate, timeout);
		leftDriveMaster.changeMotionControlFramePeriod(frameRate/2);
		rightDriveMaster.changeMotionControlFramePeriod(frameRate/2);
	}
	
	/*******************
	 * ENCODER METHODS *
	 *******************/
	
	// "Instantiates" the encoders and sets them to spin in the same direction as the wheels
	private void setEncoderDefaults() {
		leftDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, leftDriveIdx, timeout);
		rightDriveMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, rightDriveIdx, timeout);
		leftDriveMaster.setSensorPhase(true);
		rightDriveMaster.setSensorPhase(true);
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
	
	/**********************
	 * MP SUPPORT METHODS *
	 **********************/
	
	// Resets the motion profile by clearing the buffered motion profile and resetting encoders
	public void resetMP() {
		leftDriveMaster.clearMotionProfileTrajectories();
		rightDriveMaster.clearMotionProfileTrajectories();
		leftDriveMaster.clearStickyFaults(0);
		rightDriveMaster.clearStickyFaults(0);
		zeroSensors();
	}
	
	// This is called periodically and processes the trajectory points
	public void checkMPB() {
		leftDriveMaster.processMotionProfileBuffer();
		rightDriveMaster.processMotionProfileBuffer();
	}

	// Sets talons on motion profile mode. Param is enum which has states of enable, disable
	public void setMPMode(SetValueMotionProfile MPMode) {
		leftDriveMaster.set(MOTION_PROFILE_MODE, MPMode.value);
		rightDriveMaster.set(MOTION_PROFILE_MODE, MPMode.value);
	}
	
	// This method fills each talon with trajectory points. 
	public void fillMPE(double[][] leftProfile, double[][] rightProfile) {
		TrajectoryPoint leftPoint = new TrajectoryPoint();
		TrajectoryPoint rightPoint = new TrajectoryPoint();
		resetMP(); // reset just in case
		
		int lineNum = leftProfile.length; //TODO right dimension??
		for(int i = 0; i < lineNum; i++) {
			// Gets the position and velocity of right/left profiles at each line
			double leftPosition = leftProfile[i][1];
			double leftVelocity = leftProfile[i][0];
			double rightPosition = rightProfile[i][1];
			double rightVelocity = rightProfile[i][0]; 
			
			// sets trajectory points to a certain value
			leftPoint.position = leftPosition;
			rightPoint.position = rightPosition;
			leftPoint.velocity = leftVelocity;
			rightPoint.velocity = rightVelocity;
			leftPoint.headingDeg = 0; // Future feature... leaving it here once this can be used
			rightPoint.headingDeg = 0;
			leftPoint.profileSlotSelect0 = leftDriveIdx;
			rightPoint.profileSlotSelect0 = rightDriveIdx;
			leftPoint.timeDur = duration;
			rightPoint.timeDur = duration;
			leftPoint.zeroPos = false;
			rightPoint.zeroPos = false;
			leftPoint.isLastPoint = false;
			rightPoint.isLastPoint = false;
			if (i + 1 == lineNum) { // TODO check if counting is done right here
				leftPoint.isLastPoint = true; // If we're on the last row, set isLastPoint to true
				rightPoint.isLastPoint = true;
			}
			if(i == 0) {
				leftPoint.zeroPos = true; // If we're on the first point, set zeroPos to true
				rightPoint.zeroPos = true;
			}
			// Pushes the trajectory point whose values were just assigned to the talons
			leftDriveMaster.pushMotionProfileTrajectory(leftPoint);
			rightDriveMaster.pushMotionProfileTrajectory(rightPoint);		
		}
	}
	
	// checks if there are at least 5 trajectory points loaded
	public boolean isEnoughPoints() {
		if(status.btmBufferCnt > 5) return true;
		else return false;
	}
	
	// Checks if the current point is the last one
	public boolean isLastPoint() {
		return status.isLast && status.activePointValid;
	}
	
	// check method called each loop to check if motion profile is underrun and clears the underrun flag if it is.
	@Override
	public void check() {
		leftDriveMaster.getMotionProfileStatus(status);
		rightDriveMaster.getMotionProfileStatus(status);
		if(status.isUnderrun) {
			System.out.println("UNDERRUN");
			leftDriveMaster.clearMotionProfileHasUnderrun(0);
			rightDriveMaster.clearMotionProfileHasUnderrun(0);
		}
	}
	
	@Override
	public void zeroSensors() {
		leftDriveMaster.getSensorCollection().setQuadraturePosition(0, timeout);
		rightDriveMaster.getSensorCollection().setQuadraturePosition(0, timeout); 
	}	
	
}

