package main.subsystems;

import edu.wpi.first.wpilibj.command.Command;
import interfacesAndAbstracts.RobotSubsystem;
import main.OI;
import main.commands.pneumatics.climber.DeployClimber;
import main.commands.pneumatics.climber.forklifts.DeployForklift;
import main.commands.pneumatics.climber.hook.DeployHook;

public class Climb extends RobotSubsystem {

	@Override
	public void check() {
		if(OI.getXbox().leftTrigger.get() && OI.getXbox2().leftTrigger.get()) {
			Command deployHook = new DeployHook();
			deployHook.start();
		}
		if (OI.getXbox().rightTrigger.get() && OI.getXbox2().rightTrigger.get()) {
			Command deployForklift = new DeployForklift();
			deployForklift.start();
		}
	}

	@Override
	public void zeroSensors() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DeployClimber());
	}

}
