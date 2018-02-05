/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main;

import java.util.List;
import Util.Logger;
import controllers.Play;
import controllers.Record;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import lib.Looper;
import main.subsystems.Drivetrain;
import main.subsystems.Pneumatics;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot implements Constants, HardwareAdapter {
	public static OI oi;
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static List<ImprovedSubsystem> subsystems;
	public static Logger lg;
    private Looper enabledLooper;
	Command autoCommand;
	
	public Robot() {
		subsystems.add(dt);
		subsystems.add(pn);
	}

	@Override
	public void robotInit() {
		//Instantiate Subsystems, Must come before OI
		for(ImprovedSubsystem s: subsystems)
			s.newInstance();
		oi = new OI();
		//Other Utility Classes
		//OI must be before other Utility Classes
		lg = new Logger(outputPath, true);
		enabledLooper = new Looper(kLooperDt);
        enabledLooper.register(new Record());
        enabledLooper.register(new Play());
	}
	
	@Override
	public void disabledInit() {
		enabledLooper.stop();		
		lg.close();
	}
	
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		allPeriodic();
	}

	@Override
	public void autonomousInit() {
		if(autoCommand != null) autoCommand.start();
		enabledLooper.start();
		lg = new Logger(outputPath,  true);
	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		allPeriodic();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) autoCommand.cancel();
		enabledLooper.start();
		lg = new Logger(outputPath, true);
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Analog Sensor 1 value", HardwareAdapter.analogPressureSensor1.value());
		allPeriodic();
	}

	@Override
	public void testPeriodic() {
		allPeriodic();
	}
	
	public void allPeriodic() {
		enabledLooper.outputToSmartDashboard();
		for(ImprovedSubsystem s: subsystems)
			s.check();
	}
}
