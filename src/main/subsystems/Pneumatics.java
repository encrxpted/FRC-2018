package main.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;
import main.Robot;

public class Pneumatics extends Subsystem implements Constants, HardwareAdapter {
	private boolean down = false;
	/**
	 * Constructor
	 */
	public Pneumatics() {
		comp.setClosedLoopControl(true);
		shifter.set(EXT);
		shifter.set(OFF);
		tilter.set(EXT);
		tilter.set(OFF);
		arm.set(EXT);
		arm.set(OFF);
		
	}
	

	/*******************
	 * COMMAND METHODS *
	 *******************/

	/**
	 * Shifts the gearbox from the different gears
	 * 
	 * @param v - Desired shifting value (Uses default shifting values)
	 */
	public void shift(DoubleSolenoid.Value v) {
		shifter.set(v);
	}
	
	public boolean getDown() {
		return down;
	}
	
	/**
	 * Toggles the compressor (ON/OFF)
	 */
	public void toggleComp() {
		if (comp.enabled())
			comp.stop();
		else
			comp.start();
	}
	
	public void turnCompOff() {
		if (comp.enabled())
			comp.stop();
	}

	/*******************
	 * DEFAULT METHODS *
	 *******************/
	public void initDefaultCommand() {
		setDefaultCommand(null);
	}
}
