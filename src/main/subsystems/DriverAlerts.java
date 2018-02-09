package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class DriverAlerts extends Subsystem {

		private boolean state = false;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void setAlertLightState(boolean b) {
		// TODO Auto-generated method stub
		state = b;
	}

	public boolean getAlertLightState() {
		return state;
	}
	

	
		
	}


