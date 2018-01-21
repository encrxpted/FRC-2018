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
	public double timeBetween = 0.0;

	public Drivetrain() {
		setTalonDefaults();
	}
	
	private double lastTime = 0.0;
	public void driveVelocity(double throttle, double heading) {
		setBrakeMode(BRAKE_MODE);
		driveTrain.arcadeDrive(helper.handleOverPower(helper.handleDeadband(throttle, throttleDeadband)), helper.handleOverPower(helper.handleDeadband(heading, headingDeadband)));
		double currentTime = Timer.getFPGATimestamp();
		timeBetween = currentTime-lastTime;
		System.out.println(timeBetween);
		SmartDashboard.putNumber("Milliseconds between each call", timeBetween);
		lastTime = currentTime;
	}
	
	private void reverseTalons(boolean isInverted) {
		leftDriveMaster.setInverted(isInverted);
		rightDriveMaster.setInverted(isInverted);
		leftDriveSlave1.setInverted(isInverted);
		rightDriveSlave1.setInverted(isInverted);
	}
	
	private void setBrakeMode(NeutralMode mode) {
		leftDriveMaster.setNeutralMode(mode);
		leftDriveSlave1.setNeutralMode(mode);
		rightDriveMaster.setNeutralMode(mode);
		rightDriveSlave1.setNeutralMode(mode);
	}
	
	private void setCtrlMode() {
		//Problem Here BOI'S
//		leftDriveMaster.set(PERCENT_VBUS_MODE, 0.5);     
//		rightDriveMaster.set(PERCENT_VBUS_MODE, 0.5);
//		leftDriveSlave1.set(SLAVE_MODE, LEFT_Drive_Master);
//		rightDriveSlave1.set(SLAVE_MODE, RIGHT_Drive_Master);
		leftDriveSlave1.follow(leftDriveMaster);
		rightDriveSlave1.follow(rightDriveMaster);
	}
	
	
	public void setTalonDefaults() {
		reverseTalons(false);
		setBrakeMode(BRAKE_MODE);
		setCtrlMode();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
}
