package controllers;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;
import loopController.Loop;
import main.Robot;

public class CheckMPB implements Loop {
	/*
	 * Processes the motion profile buffer for each talon every 5ms
	 */

	@Override
	public void onStart() {
	}

	@Override
	public void onLoop() {
		Robot.dt.checkMPB();
		Command wait = new WaitCommand(0.005);
		wait.start();
	}

	@Override
	public void onStop() {
	}

}
