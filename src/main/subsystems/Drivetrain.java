package main.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
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
		leftDriveSlave1.follow(leftDriveMaster);
		rightDriveSlave1.follow(rightDriveMaster);
	}
	

	public void setTalonDefaults() {
		reverseTalons(false);
		setBrakeMode(BRAKE_MODE);
		leftDriveMaster.enableVoltageCompensation(false);
		rightDriveMaster.enableVoltageCompensation(false);
		setCtrlMode();
	}
	
	public void setTankDefaults() {
		setTalonDefaults();
		leftDriveMaster.enableVoltageCompensation(true);
		leftDriveMaster.configVoltageCompSaturation(voltageCompensationVoltage, 10);
		rightDriveMaster.enableVoltageCompensation(true);
		rightDriveMaster.configVoltageCompSaturation(voltageCompensationVoltage, 10);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
}

