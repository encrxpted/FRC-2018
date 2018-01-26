package main.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Intake extends Climber {
	public static WPI_TalonSRX DriveMaster = new WPI_TalonSRX(intakeMaster);
	
}
