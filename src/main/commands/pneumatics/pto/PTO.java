package main.commands.pneumatics.pto;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import interfacesAndAbstracts.ImprovedCommand;
import main.Constants;
import main.HardwareAdapter;
import main.Robot;

public class PTO extends ImprovedCommand implements Constants, HardwareAdapter {
	public DoubleSolenoid.Value v;
	
	public PTO(DoubleSolenoid.Value v) {
		requires(Robot.pn);
		this.v = v;
	}
	
    protected void execute() {
    	Robot.pn.toggleArm(v);
    }

	@Override
	protected boolean isFinished() {
		return false;
	}
}
