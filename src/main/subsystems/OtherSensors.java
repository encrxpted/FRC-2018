package main.subsystems;
import main.subsystems.Elevator;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;
import main.commands.driveAlerts.AlertDriver;


	public class OtherSensors extends Subsystem implements Constants, HardwareAdapter {
		private boolean elevatorLastState;
		private boolean elevatorCurrentState;
		private Command flashLights = new AlertDriver();
		//private InternalButton alertDriverButton = new InternalButton(); //Note to Self: Internal buttons crash robot code!
		
		public OtherSensors() {
			
		}
		
		public void check() {
			gearSwitchCheck();
		}
		
		private void gearSwitchCheck() {
			
			//System.out.println(gearSwitchCurrentState);
			if(elevatorLastState != elevatorCurrentState) flashLights.start();
			//alertDriverButton.setPressed(gearSwitchCurrentState != gearSwitchLastState);
			//alertDriverButton.whenPressed(new AlertDriver());
			elevatorLastState = elevatorCurrentState;
		}
		
		
		@Override
		protected void initDefaultCommand() {
		}

		public boolean isElevatorLastState() {
			return elevatorLastState;
		}

		public void setElevatorLastState(boolean elevatorLastState) {
			this.elevatorLastState = elevatorLastState;
		}

	}
