package org.usfirst.frc.team2559.robot.commands.forklift;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.commands.control.DoNothing;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class RaiseForkliftSafe extends CommandGroup {
    
    public  RaiseForkliftSafe() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new RaiseForklift());
    	addSequential(new WaitCommand(1));
    	addParallel(new DoNothing() {
			protected void initialize() {
				Robot._pneumatics.setArms(DoubleSolenoid.Value.kReverse);
			}
			
			protected boolean isFinished() {
				return true;
			}
		});
    	addSequential(new SetIntake("off"));
    }
}
