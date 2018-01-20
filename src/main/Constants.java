package main;

public interface Constants {
	
	
	public final int Xbox_Port = 0;
	
	// TALON SRX'S (CAN BUS)
	public final int LEFT_Drive_Master = 2;
	public final int LEFT_Drive_Slave1 = 3;
	public final int RIGHT_Drive_Master = 5;
	public final int RIGHT_Drive_Slave1 = 6;
	
	// JOYSTICK DEADBAND'S
	public final double throttleDeadband = 0.02;
	public final double headingDeadband = 0.02;

}
