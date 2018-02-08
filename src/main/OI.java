package main;

import lib.joystick.XboxController;


import main.Robot;
import main.commands.elevator.MoveDown;
import main.commands.elevator.MoveToScale;
import main.commands.elevator.MoveToSwitch;
import main.commands.elevator.MoveUp;
import main.commands.elevator.StopElevator;
import main.commands.intake.SpinIn;
import main.commands.intake.SpinOff;
import main.commands.intake.SpinOut;
import main.commands.pnuematics.ArmClose;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.ShiftDown;
import main.commands.pnuematics.ShiftUp;
import main.commands.pnuematics.TiltDown;
import main.commands.pnuematics.TiltUp;

public class OI implements Constants, HardwareAdapter {
	
	public OI() {
		check();
	}
	
	public static boolean ControllerMode = true;
	
	public static XboxController getXbox() {
		return xbox; 
	}
	
	public static XboxController getXbox2() {
		return xbox2;
	}
	
    public static boolean OneController(){
    	ControllerMode = true;
    	return ControllerMode;
    }
    
    public static boolean TwoController(){
    	ControllerMode = false;
    	return ControllerMode;
    }
    
	
	
	// important
	public void check() {
		if (ControllerMode) {
			// pneumatics
			xbox.leftBumper.whenPressed(new ShiftUp());
			xbox.leftBumper.whenReleased(new ShiftDown());
			//xbox.b.whenPressed(new ArmClose());
			//xbox.x.whenPressed(new ArmOpen());
			//xbox.leftTrigger.whenPressed(new TiltUp());
			//xbox.rightTrigger.whenPressed(new TiltDown());
			// intake
//			xbox.a.whenPressed(new SpinIn());
//			xbox.y.whenPressed(new SpinOut());
//			xbox.a.whenReleased(new SpinOff());
//			xbox.y.whenReleased(new SpinOff());
			// Elevator
			/*xbox.dpadright.whenPressed(new MoveToScale());
			xbox.dpadleft.whenPressed(new MoveToSwitch());
			xbox.dpadup.whenPressed(new MoveUp());
			xbox.dpaddown.whenPressed(new MoveDown());*/
			xbox.leftTrigger.whileHeld(new MoveUp());
			xbox.rightTrigger.whileHeld(new MoveDown());  
			xbox.leftTrigger.whenReleased(new StopElevator());
			xbox.rightTrigger.whenReleased(new StopElevator());
			
		} else {
			xbox.leftBumper.whenPressed(new ShiftUp());
			xbox.leftBumper.whenReleased(new ShiftDown());
			
			xbox2.leftTrigger.whenPressed(new TiltUp());
			xbox2.rightTrigger.whenPressed(new TiltDown());
			xbox2.a.whenPressed(new SpinIn());
			xbox2.y.whenPressed(new SpinOut());
			xbox2.a.whenReleased(new SpinOff());
			xbox2.y.whenReleased(new SpinOff());
			xbox2.b.whenPressed(new ArmClose());
			xbox2.x.whenPressed(new ArmOpen());
			xbox2.leftTrigger.whenPressed(new MoveUp());
			xbox2.rightTrigger.whenPressed(new MoveDown());

		}
	}

}
 