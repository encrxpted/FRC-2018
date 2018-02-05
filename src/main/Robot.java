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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import interfacesAndAbstracts.ImprovedRobot;
import interfacesAndAbstracts.InterfaceableClass;
import loopController.Looper;
import main.subsystems.Drivetrain;
import main.subsystems.Pneumatics;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends ImprovedRobot {
	public static OI oi;
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static List<InterfaceableClass> systems;
	public static Logger lg;
    private Looper enabledLooper;
	Command autoCommand;
	
	//TODO Recording Multiple Files
	//TODO Putting everything on SmartDashboard
	public Robot() {
		systems.add(dt);
		systems.add(pn);
		//OI must be the last class added, this will make it the last class to be instantiated
		//This is needed in order to ensure classes are defined in the correct order and null errors do not occur
		systems.add(oi);
	}

	@Override
	public void robotInit() {
		//Instantiate Robot Systems
		for(InterfaceableClass s: systems)
			s.newInstance();
		//Other Utility Classes
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
		for(InterfaceableClass s: systems)
			s.check();
	}
}
