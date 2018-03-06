package Util;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Notifier;

public class MotionProfile {
	private WPI_TalonSRX talon;
	private Notifier notifier = new Notifier(new PeriodicRunnable(talon));
	
	public MotionProfile(WPI_TalonSRX talon) {
		this.talon = talon;
		talon.changeMotionControlFramePeriod(5);
		notifier.startPeriodic(0.005);
	}
	
	

}
