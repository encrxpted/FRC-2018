package main.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import main.Constants;
import main.HardwareAdapter;
import main.OI;
import main.Robot;

public class MoveWithJoystick extends Command implements Constants, HardwareAdapter {
	
	protected void execute() {
		Robot.el.moveWithJoystick(OI.getXbox().getSmoothedMainX());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
