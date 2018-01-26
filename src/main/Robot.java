/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.subsystems.Drivetrain;
import main.subsystems.Intake;
import main.subsystems.Pneumatics;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static enum RobotState {
		Driving, Climbing, Neither
	}
	
	public static OI oi;
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake it;
	//public static SmartDashboardInteractions sdb;

	Command autoCommand;

	@Override
	public void robotInit() {
		//OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		it = new Intake();
		//sdb = new SmartDashboardInteractions();
		
		//robotState = 
		oi = new OI();
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
	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) autoCommand.cancel();
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Analog Sensor 1 value", HardwareAdapter.analogPressureSensor1.value());
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
