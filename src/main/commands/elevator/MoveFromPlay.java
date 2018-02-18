package main.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import main.Robot;

public class MoveFromPlay extends Command {
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
