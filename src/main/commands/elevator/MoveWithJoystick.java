package main.commands.elevator;

import interfacesAndAbstracts.ImprovedCommand;
import main.Constants;
import main.HardwareAdapter;
import main.OI;
import main.Robot;

public class MoveWithJoystick extends ImprovedCommand implements Constants, HardwareAdapter {
	public MoveWithJoystick() {
		requires(Robot.el);
	}
	
	protected void execute() {
		Robot.el.moveWithJoystick(-OI.getXbox2().getSmoothedMainY());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
