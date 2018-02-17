/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.commands.autonomous.Auto;
import main.commands.autonomous.TestAuto;
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
	//robot modes
	Runnable teleopCommand;
	SendableChooser<Runnable> teleopChooser, autoChooser, startPos;
	
	//SendableChooser teleopChooser;
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake it;
	public static Elevator el;
	public static DriverAlerts da;
	//public static TfMini mini;
	//public static SmartDashboardInteractions sdb;

	public static String startpos;
	
	// auto modes
	Command autoCommand;

	@Override
	public void robotInit() {
		//create auto command
		//autonomousCommand = new SodaDelivery()
		//mini = new TfMini();
		
		//camera
		CameraServer.getInstance().startAutomaticCapture();
		//OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		it = new Intake();
		//CameraServer.getInstance().startAutomaticCapture();
		el = new Elevator();
		//da = new DriverAlerts();
		//sdb = new SmartDashboardInteractions();
		//robotState = 
		
		//teleop modes
		teleopChooser = new SendableChooser<>();
		//SmartDashboard.putBoolean("alert light", Robot.da.getAlertLightState());
		teleopChooser.addDefault("2 joysticks", ()->{OI.TwoController();});
		teleopChooser.addObject("1 joystick", ()->{OI.OneController();});
		SmartDashboard.putData("teleop mode chooser", teleopChooser);
		
		//auto modes
		autoChooser = new SendableChooser<>();
		autoChooser.addDefault("Baseline", ()->{});
		autoChooser.addObject("Score Cube", ()->{});
		autoChooser.addObject("Do Nothing", ()->{});
		SmartDashboard.putData("auto", autoChooser);
		
		//Starting Pos
		startPos = new SendableChooser<>();
		startPos.addDefault("Left", ()->{OI.Left();});
		startPos.addObject("Middle", ()->{OI.Middle();});
		startPos.addObject("Right", ()->{OI.Right();});
		SmartDashboard.putData("Starting Pos", startPos);
		
		OI.configure();
	}

	
	@Override
	public void disabledInit() {

	}
	
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit() {
		new TestAuto().start();
		// FIXME: use String.equals and instanceof instead of == and Command.toString()
		// FIXME: figure out how to use auto commands
		
		/*autoCommand = (Command) autoChooser.getSelected();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0)
        {
        	if(gameData.charAt(0) == 'L' && startpos != "middle")
			{
	        	//Put left auto code here
				if (startpos == "left" && autoCommand.toString() == "ScoreCube()")
				autoCommand.start();
				else if (autoCommand.toString() == "DoNothing()") {
					autoCommand = new Baseline();
					autoCommand.start();
				}
				else 
					autoCommand.start();	
				
					  
			} else {
				//Put right auto code here
				if (startpos == "right" && autoCommand.toString() == "ScoreCube()")
				autoCommand.start();
				else if (autoCommand == new DoNothing()) {
					autoCommand = new Baseline();
					autoCommand.start();
				}
				else 
					autoCommand.start();
			}
        	if (gameData.charAt(0) == 'L' && startpos == "middle")
        	{
        		//Put middle auto here
        		autoCommand = new DoNothing();
				autoCommand.start();
        	}
    	}
        System.out.println(autoCommand.toString());*/
    }
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (autoCommand != null) autoCommand.cancel();
		teleopCommand = teleopChooser.getSelected();
		teleopCommand.run();
	}
	
	@Override
	public void teleopPeriodic() {
		Runtime runtime=Runtime.getRuntime();
		
		// smartdashboard stuff goes here
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Free memory", runtime.freeMemory());
		SmartDashboard.putNumber("Total memory", runtime.totalMemory());
		//SmartDashboard.putNumber("Lazers ;)", mini.getValue());
		/*SmartDashboard.putNumber("Elevator Encoder Revs", leftElevatorMaster.getSensorCollection().getQuadraturePosition() / countsPerRev);
		SmartDashboard.putBoolean("Is arm at bottom: ", el.isArmAtBottom());
		SmartDashboard.putBoolean("Is arm at top: ", el.isArmAtTop());*/
		//el.check();
		//SmartDashboard.putNumber("Ultrasonic sensor distance (mm): ", HardwareAdapter.ultra.getRangeMM());
		/*SmartDashboard.putNumber("Elevator velocity:", el.getElevatorVelocity());
		SmartDashboard.putNumber("Elevator Distance:", el.getTicksTravelled());*/
		SmartDashboard.putNumber("Pressure: ", HardwareAdapter.analogPressureSensor1.value());
		SmartDashboard.putBoolean("Cube Detected: ", cubeSensor1.get());
	}
}
