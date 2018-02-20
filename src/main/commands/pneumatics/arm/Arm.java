package main.commands.pneumatics.arm;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import interfacesAndAbstracts.ImprovedCommand;
import main.Constants;
import main.HardwareAdapter;
import main.Robot;

public class Arm extends ImprovedCommand implements Constants, HardwareAdapter {
	public DoubleSolenoid.Value v;
	
	public Arm(DoubleSolenoid.Value v) {
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
