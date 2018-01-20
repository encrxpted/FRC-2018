package main.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import Util.DriveHelper;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import main.Constants;
import main.HardwareAdapter;
import main.commands.drivetrain.Drive;

public class Drivetrain extends Subsystem implements Constants, HardwareAdapter {
	private static DifferentialDrive driveTrain = new DifferentialDrive(leftDriveMaster, rightDriveMaster);
	private DriveHelper helper = new DriveHelper(7.5);

	public Drivetrain() {
		setTalonDefaults();
	}
	
	public void driveVelocity(double throttle, double heading) {
		setBrakeMode(BRAKE_MODE);
		driveTrain.arcadeDrive(helper.handleOverPower(throttle), helper.handleOverPower(heading));
	}
	
	private void reverseTalons(boolean isInverted) {
		leftDriveMaster.setInverted(isInverted);
		rightDriveMaster.setInverted(isInverted);
	}
	
	private void setBrakeMode(NeutralMode mode) {
		leftDriveMaster.setNeutralMode(mode);
		leftDriveSlave1.setNeutralMode(mode);
		rightDriveMaster.setNeutralMode(mode);
		rightDriveSlave1.setNeutralMode(mode);
	}
	
	private void setCtrlMode() {
		//Problem Here BOI'S
		leftDriveMaster.set(PERCENT_VBUS_MODE, 0);        
		rightDriveMaster.set(PERCENT_VBUS_MODE, 0);
		leftDriveSlave1.set(SLAVE_MODE, LEFT_Drive_Master);
		rightDriveSlave1.set(SLAVE_MODE, RIGHT_Drive_Master);
	}
	
	
	public void setTalonDefaults() {
		reverseTalons(true);
		setBrakeMode(BRAKE_MODE);
		setCtrlMode();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive());
	}
	
}
