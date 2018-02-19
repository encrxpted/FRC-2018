/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.commands.autonomous.Baseline;
import main.commands.autonomous.DoNothing;
import main.commands.autonomous.ScoreCubeLeft;
import main.subsystems.DriverAlerts;
import main.subsystems.Drivetrain;
import main.subsystems.Elevator;
import main.subsystems.Intake;
import main.subsystems.Pneumatics;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot implements Constants, HardwareAdapter {
	public static enum RobotState {
		Driving, Climbing, Neither
	}

	// robot modes
	Runnable teleopCommand;
	SendableChooser<Runnable> teleopChooser, autoChooser, startPos;

	// SendableChooser teleopChooser;
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake it;
	public static Elevator el;
	public static DriverAlerts da;
	public static String startpos = "left";
	public static String desiredAuto = "Baseline";

	// auto modes
	Command autoCommand;

	@Override
	public void robotInit() {
		// camera
		CameraServer.getInstance().startAutomaticCapture();
		// OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		it = new Intake();
		el = new Elevator();
		// teleop modes
		teleopChooser = new SendableChooser<>();
		teleopChooser.addDefault("2 joysticks", () -> {
			OI.TwoController();
		});
		teleopChooser.addObject("1 joystick", () -> {
			OI.OneController();
		});
		SmartDashboard.putData("teleop mode chooser", teleopChooser);

		
		// auto modes
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Baseline", () -> {
			OI.BaselineAuto();
		});
		autoChooser.addObject("Score Cube", () -> {
			OI.ScoreCubeAuto();
		});
		autoChooser.addObject("Do Nothing", () -> {
			OI.DoNothingAuto();
		});
		SmartDashboard.putData("Auto Chooser", autoChooser);
		

		// Starting Pos
		startPos = new SendableChooser<>();
		startPos.addDefault("Left", () -> {
			OI.Left();
		});
		startPos.addObject("Middle", () -> {
			OI.Middle();
		});
		startPos.addObject("Right", () -> {
			OI.Right();
		});
		SmartDashboard.putData("Starting Position", startPos);
		
	}

	@Override
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// new TestAuto().start();
		// FIXME: use String.equals and instanceof instead of == and Command.toString()
		// FIXME: figure out how to use auto commands

		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() > 0 && !(desiredAuto.equals("DoNothing"))) {
			//left switch code is here
			if (gameData.charAt(0) == 'L') {
				//switch auto scoring in left
				if (startpos.equals("left") && desiredAuto.equals("ScoreCube"))
					new ScoreCubeLeft().start();
				// crossing auto baseline in right/middle
				else
					// do baseline in right
					if(startpos.equals("right") && desiredAuto.equals("Baseline"))
					new Baseline().start();
					// do nothing in middle
					else
					new DoNothing().start();
			}		
			// put right switch code here
			else {
				// switch auto scoring right
				if ((startpos.equals("right") && desiredAuto.equals("ScoreCube")) || desiredAuto.equals("Baseline"))
					new ScoreCubeLeft().start();
				// switch auto scoring left/middle
				else
					// do baseline in left
					if(startpos.equals("right") && desiredAuto.equals("Baseline"))
						new Baseline().start();
						// do nothing in middle
						else
						new DoNothing().start();
			}
			/*if (gameData.charAt(0) == 'L' && startpos.equals("middle")) {
				// Put middle auto here
				new DoNothing().start();
			}*/
		} 
		// nothing sent to driver station
		else
			//new DoNothing().start();
			System.out.println("Doing Nothing!");
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		OI.configure();

		if (autoCommand != null)
			autoCommand.cancel();
		teleopCommand = teleopChooser.getSelected();
		teleopCommand.run();
	}

	@Override
	public void teleopPeriodic() {
		Runtime runtime = Runtime.getRuntime();

		// smartdashboard stuff goes here
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Free memory", runtime.freeMemory());
		SmartDashboard.putNumber("Total memory", runtime.totalMemory());
		// SmartDashboard.putNumber("Lazers ;)", mini.getValue());
		/*
		 * SmartDashboard.putNumber("Elevator Encoder Revs",
		 * leftElevatorMaster.getSensorCollection().getQuadraturePosition() /
		 * countsPerRev); SmartDashboard.putBoolean("Is arm at bottom: ",
		 * el.isArmAtBottom()); SmartDashboard.putBoolean("Is arm at top: ",
		 * el.isArmAtTop());
		 */
		// el.check();
		// SmartDashboard.putNumber("Ultrasonic sensor distance (mm): ",
		// HardwareAdapter.ultra.getRangeMM());
		/*
		 * SmartDashboard.putNumber("Elevator velocity:", el.getElevatorVelocity());
		 * SmartDashboard.putNumber("Elevator Distance:", el.getTicksTravelled());
		 */
		SmartDashboard.putNumber("Pressure: ", HardwareAdapter.analogPressureSensor1.value());
		SmartDashboard.putBoolean("Cube Detected: ", cubeSensor1.get());
	}
}
