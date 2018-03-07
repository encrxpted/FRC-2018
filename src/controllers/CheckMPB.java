package controllers;

import loopController.Loop;
import main.Robot;

public class CheckMPB implements Loop {

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLoop() {
		Robot.dt.checkMPB();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		
	}

}
