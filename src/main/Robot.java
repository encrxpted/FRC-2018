/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main;

import edu.wpi.first.wpilibj.IterativeRobot;
import Util.SmartDashboardInteractions;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.commands.elevator.MoveToScale;
import main.commands.joystickselector.JoyStick1;
import main.commands.joystickselector.JoyStick2;
import main.subsystems.DriverAlerts;
import main.subsystems.Drivetrain;
import main.subsystems.Elevator;
import main.subsystems.Pneumatics;
import main.subsystems.OtherSensors;

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
	//robot modes
	Command teleopCommand;
	SendableChooser teleopChooser;
	
	//SendableChooser teleopChooser;
	public static OI oi;
	public static Drivetrain dt;
	public static Pneumatics pn;
	//public static Intake it;
	public static Elevator el;
	public static DriverAlerts da;
	//public static SmartDashboardInteractions sdb;

	// auto modes
	Command autoCommand;
	SendableChooser autoChooser;

	@Override
	public void robotInit() {
		//create auto command
		//autonomousCommand = new SodaDelivery();
		
		
		HardwareAdapter.init();
		//camera
		CameraServer.getInstance().startAutomaticCapture();
		//OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		//it = new Intake();
		//CameraServer.getInstance().startAutomaticCapture();
		el = new Elevator();
		//da = new DriverAlerts();
		//sdb = new SmartDashboardInteractions();
		//robotState = 
		oi = new OI();
		
		//teleop modes
		teleopChooser = new SendableChooser();
		teleopChooser.addDefault("default mode", new JoyStick1());
		teleopChooser.addObject("alternate mode", new JoyStick2());
		
		//auto modes
		autoChooser = new SendableChooser();
		autoChooser.addDefault("default mode", new JoyStick1());
		autoChooser.addObject("alternate mode", new JoyStick2());
		SmartDashboard.putData("teleop mode chooser", teleopChooser);
	}

	
	@Override
	public void disabledInit() {

	}
	
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		if(autoCommand != null) autoCommand.start();
		autoCommand = (Command) teleopChooser.getSelected();
		autoCommand.start();
	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		//el.check();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) autoCommand.cancel();
		teleopCommand = (Command) teleopChooser.getSelected();
		teleopCommand.start();
	} 
	@Override
	public void teleopPeriodic() {
		// smartdashboard stuff goes here
		oi.check();
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Analog Sensor 1 value", HardwareAdapter.analogPressureSensor1.value());
		SmartDashboard.putNumber("Elevator Encoder Revs", leftElevatorMaster.getSensorCollection().getQuadraturePosition() / countsPerRev);
		SmartDashboard.putBoolean("Is arm at bottom: ", el.isArmAtBottom());
		SmartDashboard.putBoolean("Is arm at top: ", el.isArmAtTop());
		//el.check();
		//SmartDashboard.putNumber("Ultrasonic sensor distance (mm): ", HardwareAdapter.ultra.getRangeMM());
		SmartDashboard.putNumber("Elevator velocity:", el.getElevatorVelocity());
		SmartDashboard.putNumber("Elevator Distance:", el.getTicksTravelled());
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
