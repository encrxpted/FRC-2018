package Util;

import com.ctre.phoenix.motion.MotionProfileStatus;
import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motion.TrajectoryPoint;
import com.ctre.phoenix.motion.TrajectoryPoint.TrajectoryDuration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Notifier;
import main.Constants;

public class MotionProfile implements Constants {
	private WPI_TalonSRX talon;
	private Notifier notifier = new Notifier(new PeriodicRunnable(talon));
	private MotionProfileStatus status = new MotionProfileStatus();
	
	//private int loopTimeout = -1; // this  is just something that makes sure we arent stuck. -1 is disabled.
	private boolean start = false;
	private enum MPState { NotMPMode, StartingMPE, InMPMode }
	private SetValueMotionProfile setValue = SetValueMotionProfile.Disable;
	
	private final int minPointsInTalon = 5;
	//private final int numLoopTimeout = 10;
	private final TrajectoryDuration duration = TrajectoryDuration.Trajectory_Duration_10ms;
	private MPState state = MPState.NotMPMode;

	
	public MotionProfile(WPI_TalonSRX talon) {
		this.talon = talon;
		talon.changeMotionControlFramePeriod(5);
		notifier.startPeriodic(0.005);
	}
	
	private void ifUnderrun() {
		System.out.println("UNDERRUN");
	}
	
	public void reset() {
		talon.clearMotionProfileTrajectories();
		//loopTimeout = -1;
		setValue = SetValueMotionProfile.Disable;
		state = MPState.NotMPMode;
		start = false;
	}
	
	public void checkLoops() {
		// Checks loop timeout
		//if(loopTimeout == 0) ifNoProgress();
		//else loopTimeout--;
	}

	public void check() { // Intended to be called in a loop
		talon.getMotionProfileStatus(status);

		if(talon.getControlMode() != MOTION_PROFILE_MODE) {
			// checks control mode. Disables if not in motion profile mode.
			state = MPState.NotMPMode;
			//loopTimeout = -1;
		}
		else {
			// does motion profiling if it is in motion profile mode.
			switch(state) {
			case NotMPMode:
				if(start) {
					start = false;
					setValue = SetValueMotionProfile.Disable;
					//fill(); stream trajectory points... I need the points tho
					state = MPState.StartingMPE;
					//loopTimeout = numLoopTimeout;
				}
				break;
			case StartingMPE:
				if (status.btmBufferCnt > minPointsInTalon) {
					setValue = SetValueMotionProfile.Enable; // starts motion profile
					state = MPState.InMPMode;
					//loopTimeout = numLoopTimeout;
				}
				break;
			case InMPMode:
				if(!status.isUnderrun) {
					//loopTimeout = numLoopTimeout;
				}
				if(status.activePointValid && status.isLast) { 
					//this checks if we have reached the last point, and stops if we have reached the last point.
					setValue = SetValueMotionProfile.Hold;
					state = MPState.NotMPMode;
					//loopTimeout = -1;
				}
				break;
			}
			
		}
	}
	
	// Streams trajectory points to the talon
	private void fill(double[][] profile, int numOfPoints, int profileSlot) {
		TrajectoryPoint point = new TrajectoryPoint();
		
		if(status.hasUnderrun) {
			ifUnderrun();
			talon.clearMotionProfileHasUnderrun(0);
		}
		talon.clearMotionProfileTrajectories();
		talon.configMotionProfileTrajectoryPeriod(0, timeout);

		for(int i = 0; i < numOfPoints; ++i) {
			double encUnits = profile[i][0];
			double unitsPer100ms = profile[i][1];
			
			point.position = encUnits;
			point.velocity = unitsPer100ms;
			point.headingDeg = 0;
			point.profileSlotSelect0 = profileSlot;
			point.profileSlotSelect1 = 0;
			point.timeDur = duration;
			
			point.zeroPos = false;
			if(i == 0) point.zeroPos = true; // zero position only on first point
			point.isLastPoint = false;
			if((i + 1) == numOfPoints) point.isLastPoint = true;
			
			talon.pushMotionProfileTrajectory(point);
		}
	}
	
	public void startMotionProfile() {
		start = true;
	}
	
}
