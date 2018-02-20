package main.commands.pneumatics.climber.forklifts;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class Forklift extends ImprovedCommand {
	private DoubleSolenoid.Value v;

	public Forklift(DoubleSolenoid.Value v) {
		requires(Robot.pn);
		this.v = v;
	}
	
	protected void execute() {
		Robot.pn.toggleForks(v);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
