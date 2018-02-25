// Copyright (c) 2018 FIRST 3140. All Rights Reserved.

package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Util.Logger;
import controllers.Play;
import controllers.Record;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import interfacesAndAbstracts.ImprovedRobot;
import loopController.Looper;
import main.commands.controllerCommands.DoNothing;
import main.commands.controllerCommands.FileCreator;
import main.commands.controllerCommands.FileDeletor;
import main.commands.controllerCommands.FilePicker;
import main.commands.controllerCommands.StartPlay;
import main.commands.controllerCommands.StartRecord;
import main.subsystems.DriverAlerts;
import main.subsystems.DriverCamera;
import main.subsystems.Drivetrain;
import main.subsystems.Elevator;
import main.subsystems.Intake;
import main.subsystems.Pneumatics;

public class Robot extends ImprovedRobot {
	private enum StartPos {LEFT, MIDDLE, RIGHT}
	public static Drivetrain dt;
	public static Pneumatics pn;
	public static Intake in;
	public static Elevator el;
	public static DriverCamera dc;
	public static DriverAlerts da;	
	public static OI oi;
	// PLAY AND RECORD
	public static Logger lg;
    private static Looper autoLooper;
    private static SendableChooser<Command> fileChooser;
    private static Command autoPlayCommand;
    private Command lastSelectedFile = new DoNothing();
    private static String newFileName = "";
    private static List<File> listOfFiles = new ArrayList<File>();
    private static int lastNumOfFiles = 0;
	// AUTO LOGIC
	public static StartPos start_pos = StartPos.LEFT;
	private boolean doNothing = true;
	private boolean baseline = true;
	private static Command competitionPlayCommand;
	private static Command competitionFileChooser;
	private String fileToPlay = "";
	private static SendableChooser<Runnable> autoChooser, startPos, autoChooser2;

	// auto modes
	//Command autoCommand;

	@Override
	public void robotInit() {
		// OI must be at end
		dt = new Drivetrain();
		pn = new Pneumatics();
		in = new Intake();
		el = new Elevator();
		oi = new OI();
		dc = new DriverCamera();
		// da = new DriverAlerts();	
		lg = new Logger();
		autoLooper = new Looper(kLooperDt);
		autoLooper.register(new Record());
		autoLooper.register(new Play()); 

        //**************************************************SmartDashboard
    	if(!isCompetition) {
    		SmartDashboard.putData("Record", new StartRecord());
			SmartDashboard.putData("Play", new StartPlay());
    		// File adder
    		SmartDashboard.putString("New File Name", "");
    		SmartDashboard.putData("Create a new file", new FileCreator()); 
    		// File deleter
    		SmartDashboard.putData("Delete a file", new FileDeletor());
    		//FileSelector
        	fileChooser = new SendableChooser<>();
        	fileChooser.addDefault("", new DoNothing());
        	SmartDashboard.putData("File Selector", fileChooser);
    		
    		SmartDashboard.putString("NOTICE:", "Whenever you redeploy code you must restart shuffleboard; And whenever you "
					+ "delete a file you must restart robot code.");
    	}
    	
    	else {
    		/* AUTO EXPLAINATION:
    		 * Do Nothing- Robot won't move during auto
    		 * Do Something- Robot decides what to do during auto, depending on the game data
    		 * Baseline or score- If neither the scale or switch is on the same side as the robot, then
    		 * you can choose whether or not you want it to cross baseline or score a cube on the opposite switch
    		 */
    		
			// auto modes
			autoChooser = new SendableChooser<>();
			autoChooser.addDefault("Do Nothing", () -> {
				doNothing = true;
			});
			autoChooser.addObject("Do Something", () -> {
				doNothing = false;
			});
			SmartDashboard.putData("To move or not to move", autoChooser);
			
			// 2nd chooser
			autoChooser2 = new SendableChooser<>();
			autoChooser2.addDefault("Baseline", () -> {
				baseline = true;
			});
			autoChooser2.addObject("Score cube", () -> {
				baseline = false;
			});
			SmartDashboard.putData("Baseline or score", autoChooser2);

			// Starting Pos
			startPos = new SendableChooser<>();
			startPos.addDefault("Left", () -> {
				start_pos = StartPos.LEFT;
			});
			startPos.addObject("Middle", () -> {
				start_pos = StartPos.MIDDLE;
			});
			startPos.addObject("Right", () -> {
				start_pos = StartPos.RIGHT;
			});
			SmartDashboard.putData("Starting Position", startPos);
		}
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
		if (isCompetition) {
			String gmsg = DriverStation.getInstance().getGameSpecificMessage();
			while (gmsg == null || gmsg.length() != 3) {
				gmsg = DriverStation.getInstance().getGameSpecificMessage();
			} // makes sure game message is correct

			boolean leftSwitch = (gmsg.charAt(0) == 'L');
			boolean leftScale = (gmsg.charAt(1) == 'L');

			if (!doNothing) { // Do something chosen
				switch (start_pos) { // Checks which starting position was chosen
				// Following code choose auto mode based on starting position for switch and scale
				case LEFT:
					if (leftSwitch && leftScale)
						fileToPlay = LEFT_SwitchAndScale;
					else if (leftSwitch && !leftScale)
						fileToPlay = LEFT_LeftSwitch;
					else if (!leftSwitch && leftScale)
						fileToPlay = LEFT_Scale;
					else {
						if (baseline) fileToPlay = driveBaseline;
						else fileToPlay = LEFT_RightSwitch;
					}
					break;
				case MIDDLE:
					if (leftSwitch)
						fileToPlay = MID_LeftSwitch;
					else
						fileToPlay = MID_RightSwitch;
					break;
				case RIGHT:
					if (!leftSwitch && !leftScale)
						fileToPlay = RIGHT_SwitchAndScale;
					else if (leftSwitch && !leftScale)
						fileToPlay = RIGHT_Scale;
					else if (!leftSwitch && leftScale)
						fileToPlay = RIGHT_RightSwitch;
					else {
						if (baseline) fileToPlay = driveBaseline;
						else fileToPlay = RIGHT_LeftSwitch;
					}
				}
				competitionFileChooser = new FilePicker(fileToPlay);
				competitionFileChooser.start(); // changes path to the chosen file
				competitionPlayCommand = new StartPlay();
			} 
			else { // Do nothing chosen
				competitionPlayCommand = new DoNothing();
			}

			if (competitionPlayCommand != null)
				competitionPlayCommand.start(); // starts the command
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
		if(!isCompetition) {
			checkForSmartDashboardUpdates();
		}
		autoLooper.outputToSmartDashboard();
		dt.check();
		pn.check();
		in.check();
		el.check();
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
