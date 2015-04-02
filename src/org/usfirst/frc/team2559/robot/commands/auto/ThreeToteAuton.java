package org.usfirst.frc.team2559.robot.commands.auto;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;
import org.usfirst.frc.team2559.robot.commands.DriveForDistance;
import org.usfirst.frc.team2559.robot.commands.ToggleArms;
import org.usfirst.frc.team2559.robot.commands.control.DoNothing;
import org.usfirst.frc.team2559.robot.commands.forklift.COTTONMOUTH_MIN;
import org.usfirst.frc.team2559.robot.commands.forklift.COTTONMOUTH_TIME;
import org.usfirst.frc.team2559.robot.commands.forklift.SetIntake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeToteAuton extends CommandGroup {
    
    public  ThreeToteAuton() {
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
    	// suck totes in
    	addParallel(new DoNothing() {
			protected void initialize() {
				Robot._pneumatics.setArms(DoubleSolenoid.Value.kReverse);
			}
			
			protected boolean isFinished() {
				return true;
			}
		});
    	addSequential(new SetIntake("in"));
    	// pickup first tote
    	addSequential(new COTTONMOUTH_TIME(1000, true));
    	// drive forward to next tote
    	addParallel(new ToggleArms());
    	addSequential(new DriveForDistance(0.5, RobotMap.AUTO_DISTANCE_FROM_TOTE, true));
    	addParallel(new ToggleArms());
    	// put first tote down on top of the other tote
    	addSequential(new COTTONMOUTH_MIN());
    	// drive forward
    	addSequential(new DriveForDistance(0.5, 12, true));
    	// pickup the two totes
    	addSequential(new COTTONMOUTH_TIME(2000, true));
    	// drive forward to the next tote
    	addParallel(new ToggleArms());
    	addSequential(new DriveForDistance(0.5, RobotMap.AUTO_DISTANCE_FROM_TOTE, true));
    	addParallel(new ToggleArms());
    	// put down the two totes on top of the other totes
    	addSequential(new COTTONMOUTH_TIME(2000, false));
    	// drive forward
    	addSequential(new DriveForDistance(0.5, 2, true));
    	// pickup the three totes
    	addSequential(new COTTONMOUTH_TIME(3000, true));
    	addParallel(new ToggleArms());
    	addSequential(new Turn(-90, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new DriveForDistance(0.5, RobotMap.AUTO_DISTANCE_TO_LANDMARK, true));
    	addSequential(new SetIntake("stop"));
    	addSequential(new COTTONMOUTH_TIME(3000, false));
    	addSequential(new DriveForDistance(0.6, -4, false));
    	addSequential(new COTTONMOUTH_TIME(1350, false));
    	addSequential(new DriveForDistance(0.8, -2, false));
    }
}
