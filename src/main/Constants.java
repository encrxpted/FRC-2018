package main;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public interface Constants {
	//XBOX PORTS
	public final int Xbox_Port = 0;
	
	// TALON SRX'S (CAN BUS)
	public final int LEFT_Drive_Master = 2;
	public final int LEFT_Drive_Slave1 = 3;
	public final int RIGHT_Drive_Master = 5;
	public final int RIGHT_Drive_Slave1 = 6;
	
	// JOYSTICK DEADBAND'S
	public final double throttleDeadband = 0.02;
	public final double headingDeadband = 0.02;
	
	//TALON CONTROL MODES
	public final ControlMode SLAVE_MODE = ControlMode.Follower;
	public final NeutralMode BRAKE_MODE = NeutralMode.Brake;

}
