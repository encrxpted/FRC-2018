package main.commands.elevator;

import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class MoveFromPlay extends ImprovedCommand {
	private double voltage;
	
	public MoveFromPlay(double voltage) {
		this.voltage = voltage;
	}
	
	protected void execute() {
		Robot.el.moveFromPlay(voltage);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
