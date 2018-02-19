package main.commands.commandgroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import main.Constants;
import main.HardwareAdapter;
import main.commands.intake.SpinIn;
import main.commands.pnuematics.ArmOpen;
import main.commands.pnuematics.TiltDown;

public class DropCube extends CommandGroup implements Constants, HardwareAdapter
public DropCube() {
	addParallel(new TiltDown());
	addParallel(new ArmOpen());
}
}

