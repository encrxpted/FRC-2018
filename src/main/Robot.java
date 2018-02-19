// Copyright (c) 2018 FIRST 3140. All Rights Reserved.

package main;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.subsystems.DriverAlerts;
import main.subsystems.Drivetrain;
import main.subsystems.Elevator;
import main.subsystems.Intake;
import main.subsystems.Pneumatics;

public class Robot extends TimedRobot implements Constants, HardwareAdapter {
	public enum RobotState {Driving, Climbing, Neither}
	// TODO: change enum to capitalized
	private enum StartPos {LEFT, MIDDLE, RIGHT}

	// robot modes
	Runnable teleopCommand;
	SendableChooser<Runnable> teleopChooser, autoChooser, startPos;

	// SendableChooser teleopChooser;
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake it;
	public static Elevator el;
	public static DriverAlerts da;
	
	public static StartPos start_pos=StartPos.LEFT;
	public static boolean auto_score=true;

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
		autoChooser.addObject("Score Cube", () -> {
			auto_score=true;
		});
		autoChooser.addDefault("Baseline", () -> {
			auto_score=true;
		});
		SmartDashboard.putData("Auto Chooser", autoChooser);

		// Starting Pos
		startPos = new SendableChooser<>();
		startPos.addDefault("Left", () -> {
			start_pos=StartPos.LEFT;
		});
		startPos.addObject("Middle", () -> {
			start_pos=StartPos.MIDDLE;
		});
		startPos.addObject("Right", () -> {
			start_pos=StartPos.RIGHT;
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
		String gmsg = DriverStation.getInstance().getGameSpecificMessage();
		
		if (gmsg!=null && gmsg.length()==3) {
			// game message is correct
			
			boolean left=gmsg.charAt(0)=='L';
			
			if (auto_score) {
				switch (start_pos) {
				case LEFT:
					if (left) ; // TODO: score cube to left from left
					else ; // TODO: score cube to right from left
					break;
				case MIDDLE:
					if (left) ; // TODO: score cube to left from middle
					else ; // TODO: score cube to right from middle
					break;
				case RIGHT:
					if (left) ; // TODO: score cube to left from right
					else ; // TODO: score cube to right from right
				}
			} else {
				switch (start_pos) {
				case LEFT:
					if (left) ; // TODO: cross baseline to left from left
					else ; // TODO: cross baseline to right from left
					break;
				case MIDDLE:
					if (left) ; // TODO: cross baseline to left from middle
					else ; // TODO: cross baseline to right from middle
					break;
				case RIGHT:
					if (left) ; // TODO: cross baseline to left from right
					else ; // TODO: cross baseline to right from right
				}
			}
		}
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
