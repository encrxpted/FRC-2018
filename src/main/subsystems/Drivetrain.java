package main.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import Util.DriveHelper;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.Constants;
import main.HardwareAdapter;
import main.commands.drivetrain.Drive;

public class Drivetrain extends Subsystem implements Constants, HardwareAdapter {
	private static DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
	
	private DriveHelper helper = new DriveHelper(7.5);
	private double lastTime = 0.0; // the last time of the call of driveVelocity()

	public Drivetrain() {
		setTalonDefaults();
	}
	
	/**
	 * TODO: what does this method do?
	 * 
	 * @param throttle throttle
	 * @param heading heading
	 */
	public void driveVelocity(double throttle, double heading) {
		double currentTime;
		
		setBrakeMode(BRAKE_MODE);
		System.out.println("Throttle: " + throttle + " | Heading: " + heading);
		driveTrain.arcadeDrive(helper.driveSmooth(throttle), helper.handleOverPower(helper.handleDeadband(heading, headingDeadband)));
		// TODO: get ride of the comment
		//driveTrain.tankDrive
		currentTime = Timer.getFPGATimestamp();
		SmartDashboard.putNumber("Milliseconds between each call", currentTime-lastTime);
		lastTime = currentTime;
	}
	
	/**
	 * Inverts the hbridge output of left & right dive master & slave 1.
	 * 
	 * @param isInverted
	 */
	private void reverseTalons(boolean isInverted) {
		leftDriveMaster.setInverted(isInverted);
		rightDriveMaster.setInverted(isInverted);
		leftDriveSlave1.setInverted(isInverted);
		rightDriveSlave1.setInverted(isInverted);
	}
	
	/**
	 * Sets brake mode for left & right dive master & slave 1.
	 * 
	 * @param mode mode
	 */
	private void setBrakeMode(NeutralMode mode) {
		leftDriveMaster.setNeutralMode(mode);
		leftDriveSlave1.setNeutralMode(mode);
		rightDriveMaster.setNeutralMode(mode);
		rightDriveSlave1.setNeutralMode(mode);
	}
	
	/**
	 * Sets control mode.
	 */
	private void setCtrlMode() {
		// TODO: fix this
		// Problem Here BOI'S
		/*
		leftDriveMaster.set(PERCENT_VBUS_MODE, 0.5);     
		rightDriveMaster.set(PERCENT_VBUS_MODE, 0.5);
		leftDriveSlave1.set(SLAVE_MODE, LEFT_Drive_Master);
		rightDriveSlave1.set(SLAVE_MODE, RIGHT_Drive_Master);
		*/
		leftDriveSlave1.follow(leftDriveMaster);
		rightDriveSlave1.follow(rightDriveMaster);
	}
	
	/**
	 * Sets Talon defaults.
	 */
	public void setTalonDefaults() {
		reverseTalons(false);
		setBrakeMode(BRAKE_MODE);
		setCtrlMode();
	}

	/**
	 * Inits default command.
	 */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
}
