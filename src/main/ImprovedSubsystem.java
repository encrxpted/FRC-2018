package main;

import edu.wpi.first.wpilibj.command.Subsystem;

public abstract class ImprovedSubsystem extends Subsystem {
	public abstract ImprovedSubsystem newInstance();
	public abstract void check();
	public abstract void zeroSensors();
}
