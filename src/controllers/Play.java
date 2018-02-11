
package controllers;

import edu.wpi.first.wpilibj.command.Command;
import loopController.Loop;
import main.Constants;
import main.Robot;
import main.commands.drivetrain.DriveFromPlayer;

public class Play implements Loop, Constants {
	//SET RECORD/PLAY ON COAST AND BUT NORMAL DRIVE ON BRAKE
	private static boolean playOK = false;
	private static boolean finished = false;
	
	public static void okToPlay(boolean okToPlay) {
		playOK = okToPlay;
		if(playOK) System.out.println("Ok To Play");
		else System.out.println("Not Ok To Play");
	}
	
	@Override
	public void onStart() {
	}

	@Override
	public void onLoop() {
		if(playOK)
			execute();
	}
	
	@Override
	public void onStop() {
	}
	
	private void execute() {
		//System.out.println("Running play");
		String line = Robot.lg.readLine();
		if((line) != null) { 
			String[] robotState = line.split(",");
			
			double leftVoltage = Double.parseDouble(robotState[0]);
			double rightVoltage = Double.parseDouble(robotState[1]);
			boolean a = Boolean.parseBoolean(robotState[2]);
			boolean b = Boolean.parseBoolean(robotState[3]);
			boolean x = Boolean.parseBoolean(robotState[4]);
			boolean y = Boolean.parseBoolean(robotState[5]);
			boolean leftBumper = Boolean.parseBoolean(robotState[6]);
			boolean rightBumper = Boolean.parseBoolean(robotState[7]);
			boolean select = Boolean.parseBoolean(robotState[8]);
			boolean start = Boolean.parseBoolean(robotState[9]);
			boolean leftJoystickPress = Boolean.parseBoolean(robotState[10]);
			boolean rightJoystickPress = Boolean.parseBoolean(robotState[11]);
			boolean leftTrigger = Boolean.parseBoolean(robotState[12]);
			boolean rightTrigger = Boolean.parseBoolean(robotState[13]);
			
			Command drive = new DriveFromPlayer(leftVoltage, rightVoltage);
			drive.start();
			Robot.oi.setButtonValues(a, b, x, y, leftBumper, rightBumper, select, start, leftJoystickPress, rightJoystickPress, leftTrigger, rightTrigger);
		}
		else {
			finished = true;
			System.out.println("Definately done now.");
		}
	}
	
	public static boolean isFinished() {
		return finished;
	}
	
	public static void reset() {
		finished = false;
	}
}

