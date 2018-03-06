package Util;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class PeriodicRunnable implements java.lang.Runnable {
	private WPI_TalonSRX talon;
	
	public PeriodicRunnable (WPI_TalonSRX talon) {
		this.talon = talon;
	}

	@Override
	public void run() {
		if(talon != null) talon.processMotionProfileBuffer();
	}

}
