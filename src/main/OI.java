package main;

import edu.wpi.first.wpilibj.command.CommandGroup;
import lib.joystick.XboxController;
import main.commands.commandgroups.IntakeCube;
import main.commands.commandgroups.IntakeCubeOff;
import main.commands.commandgroups.PushOutCube;
import main.commands.commandgroups.PushOutCubeOff;
import main.commands.elevator.MoveDown;
import main.commands.elevator.MoveUp;
import main.commands.elevator.StopElevator;
import main.commands.pnuematics.ShiftDown;
import main.commands.pnuematics.ShiftUp;

public class OI extends CommandGroup implements Constants, HardwareAdapter {
	
	// true: one, false: two
	public static boolean ControllerMode = false;
	
	public static void configure() {
		if (ControllerMode) {
			// pneumatics
			xbox.leftJoystickButton.whenPressed(new ShiftUp());
			xbox.leftJoystickButton.whenReleased(new ShiftDown());
			//xbox.y.whenPressed(new ArmOpen());
			//xbox.b.whenPressed(new ArmClose());
			//xbox.x.whenPressed(new ArmOpen());
			//xbox.leftTrigger.whenPressed(new TiltUp());
			//xbox.rightTrigger.whenPressed(new TiltDown());
			// intake
			//xbox.a.whenPressed(new SpinIn());
			//xbox.a.whenReleased(new SpinOff());
			xbox.a.whenReleased(new IntakeCubeOff());
			xbox.a.whenPressed(new IntakeCube());
			xbox.x.whenPressed(new PushOutCube());
			xbox.x.whenReleased(new PushOutCubeOff());
			// Elevator
			/*xbox.dpadright.whenPressed(new MoveToScale());
			xbox.dpadleft.whenPressed(new MoveToSwitch());
			xbox.dpadup.whenPressed(new MoveUp());
			xbox.dpaddown.whenPressed(new MoveDown());*/
			/*xbox.leftTrigger.whileHeld(new MoveUp());
			xbox.rightTrigger.whileHeld(new MoveDown());  
			xbox.leftTrigger.whenReleased(new StopElevator());
			xbox.rightTrigger.whenReleased(new StopElevator());*/
		} else {
			xbox.leftJoystickButton.whenPressed(new ShiftUp());
			xbox.leftJoystickButton.whenReleased(new ShiftDown());
			
			/*xbox2.leftTrigger.whenPressed(new TiltUp());
			xbox2.rightTrigger.whenPressed(new TiltDown());
			xbox2.a.whenPressed(new SpinIn());
			xbox2.y.whenPressed(new SpinOut());
			xbox2.a.whenReleased(new SpinOff());
			xbox2.y.whenReleased(new SpinOff());
			xbox2.b.whenPressed(new ArmClose());
			xbox2.x.whenPressed(new ArmOpen());*/
			
			xbox2.a.whenReleased(new IntakeCubeOff());
			xbox2.a.whenPressed(new IntakeCube());
			
			xbox2.x.whenPressed(new PushOutCube());
			xbox2.x.whenReleased(new PushOutCubeOff());
			xbox2.leftTrigger.whenPressed(new MoveUp());
			xbox2.rightTrigger.whenPressed(new MoveDown());
			xbox2.leftTrigger.whenReleased(new StopElevator());
			xbox2.rightTrigger.whenReleased(new StopElevator());
		}
	}
	
	public static boolean getControllerMode() {
		return ControllerMode;
	}
	
	public static XboxController getXbox() {
		return xbox;
	}
	
    public static XboxController getXbox2() {
		return xbox2;
	}
    
    public static String Left() {
    	Robot.startpos = "left";
    	return "left";
    }
    
    public static String Middle() {
    	Robot.startpos = "middle";
    	return "middle";
    }
    
    public static String Right() {
    	Robot.startpos = "right";
    	return "right";
    }
    
    public static void BaselineAuto() {
    	Robot.desiredAuto = "Baseline";
    }
    public static void ScoreCubeAuto() {
    	Robot.desiredAuto = "ScoreCube";
    }
    public static void DoNothingAuto() {
    	Robot.desiredAuto = "DoNothing";
    }
    
    public static boolean OneController(){
    	ControllerMode = true;
    	configure();
    	return ControllerMode;
    }
    
	public static boolean TwoController(){
    	ControllerMode = false;
    	configure();
    	return ControllerMode;
    }
}
 