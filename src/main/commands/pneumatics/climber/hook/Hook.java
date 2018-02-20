package main.commands.pneumatics.climber.hook;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import interfacesAndAbstracts.ImprovedCommand;
import main.Robot;

public class Hook extends ImprovedCommand {
	private DoubleSolenoid.Value v;
	
	public Hook(DoubleSolenoid.Value v) {
		requires(Robot.pn);
		this.v = v;
	}
	
	protected void execute() {
		Robot.pn.toggleHooks(v);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
