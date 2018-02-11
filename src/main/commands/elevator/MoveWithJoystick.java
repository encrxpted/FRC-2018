package main.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import main.Constants;
import main.HardwareAdapter;
import main.OI;
import main.Robot;

public class MoveWithJoystick extends Command implements Constants, HardwareAdapter {
	
	public MoveWithJoystick() {
		requires(Robot.el);
	}
	
	protected void execute() {
//		if(Robot.oi.getControllerMode())
			Robot.el.moveWithJoystick(OI.getXbox().getSmoothedAltY());
//		else
			//Robot.el.moveWithJoystick(OI.getXbox2().getSmoothedMainY());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

}
