/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Util.Logger;
import controllers.Play;
import controllers.Record;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import loopController.Looper;
import main.commands.controllerCommands.DoNothing;
import main.commands.controllerCommands.FileCreator;
import main.commands.controllerCommands.FileDeletor;
import main.commands.controllerCommands.FilePicker;
import main.commands.controllerCommands.StartPlay;
import main.commands.controllerCommands.StartRecord;
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

	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake it;
	public static Elevator el;
	public static DriverAlerts da;	
	public static OI oi;
	public static Logger lg;
    private static Looper autoLooper;
    private static SendableChooser<Command> fileChooser;
    private static Command autoPlayCommand;
    private Command lastSelectedFile = new DoNothing();
    private static String newFileName = "";
    private static List<File> listOfFiles = new ArrayList<File>();
    private static int lastNumOfFiles = 0;

	// auto modes
	Command autoCommand;

	@Override
	public void robotInit() {
		// create auto command
		// autonomousCommand = new SodaDelivery()
		// mini = new TfMini();

		// camera
		CameraServer.getInstance().startAutomaticCapture();
		// OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		it = new Intake();
		// CameraServer.getInstance().startAutomaticCapture();
		el = new Elevator();
		oi = OI.newInstance();
		// da = new DriverAlerts();	
		lg = new Logger();
		autoLooper = new Looper(kLooperDt);
		autoLooper.register(new Record());
		autoLooper.register(new Play()); 

        //**************************************************SmartDashboard
		SmartDashboard.putString("NOTICE:", "Whenever you redeploy code you must restart shuffleboard; And whenever you "
								+ "delete a file you must restart robot code.");
		if(!isCompetition) {
			SmartDashboard.putData("Record", new StartRecord());
			SmartDashboard.putData("Play", new StartPlay());
		}
		//FileSelector
    	fileChooser = new SendableChooser<>();
    	fileChooser.addDefault("", new DoNothing());
    	SmartDashboard.putData("File Selector", fileChooser);
    	//FileAdder
    	if(!isCompetition) {
    		SmartDashboard.putString("New File Name", "");
    		SmartDashboard.putData("Create a new file", new FileCreator()); 
    	}
    	//FileRemover
    	if(!isCompetition)
    		SmartDashboard.putData("Delete a file", new FileDeletor());
	}
	
	@Override
	public void disabledInit() {
		if(isCompetition) {
			if(autoPlayCommand.isRunning()) autoPlayCommand.cancel();
		}
		autoLooper.stop();		
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		allPeriodic();
	}

	@Override
	public void autonomousInit() {
		autoLooper.start();
		if(isCompetition) {
			fileChooser.getSelected().start();
			Command autoPlayCommand = new StartPlay();
			autoPlayCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		allPeriodic();
	}

	@Override
	public void teleopInit() {
		if(isCompetition) {
			if(autoPlayCommand.isRunning())
				autoPlayCommand.cancel();
		}
		if(!isCompetition)
			autoLooper.start();
	}
	

	@Override
	public void teleopPeriodic() {
		Runtime runtime = Runtime.getRuntime();

		// smartdashboard stuff goes here
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Free memory", runtime.freeMemory());
		SmartDashboard.putNumber("Total memory", runtime.totalMemory());
		SmartDashboard.putNumber("Pressure: ", HardwareAdapter.analogPressureSensor1.value());
		SmartDashboard.putBoolean("Cube Detected: ", cubeSensor1.get());
		SmartDashboard.putNumber("Analog Sensor 1 value", HardwareAdapter.analogPressureSensor1.value());
		allPeriodic();
	}
	
	@Override
	public void testPeriodic() {
		allPeriodic();
	}
	
	private void checkForSmartDashboardUpdates() {
		if (!isCompetition && !newFileName.equals(SmartDashboard.getString("New File Name", "")))
			newFileName = SmartDashboard.getString("New File Name", "");
		if (fileChooser.getSelected() != lastSelectedFile && fileChooser.getSelected() != null) {
			fileChooser.getSelected().start();
			lastSelectedFile = fileChooser.getSelected();
		}
		
		if (lg.getFiles(outputPath).length != lastNumOfFiles) {
			for (File file : lg.getFiles(outputPath))
				if (!fileNameInListOfFiles(listOfFiles, file)) {
					fileChooser.addObject(file.getName(), new FilePicker(file.getPath()));
					listOfFiles.add(file);
				}
			lastNumOfFiles = lg.getFiles(outputPath).length;
		} 
	}
	
	private boolean fileNameInListOfFiles(List<File> l, File f) {
		for(File file: l) {
			if(file.getName().toLowerCase().equals(f.getName().toLowerCase()))
				return true;
		}
		return false;
	}
	
	public static SendableChooser<Command> getFileChooser() {
		return fileChooser;
	}
	
	public static Command getFile() {
		return fileChooser.getSelected();
	}
	
	public void allPeriodic() {
		SmartDashboard.updateValues();
		checkForSmartDashboardUpdates();
		autoLooper.outputToSmartDashboard();
//		dt.check();
//		pn.check();
		oi.check();
		// Knowing where you're at
		if(!isCompetition) {
			SmartDashboard.putString("Working File", lg.getWorkingFile());
			SmartDashboard.putString("Working Path", outputPath);
		}
	}
	
	public static String getNewFileName() {
		return newFileName;
	}
}
