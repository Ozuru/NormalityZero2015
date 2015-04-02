package org.usfirst.frc.team2559.robot.commands.auto;

import org.usfirst.frc.team2559.robot.RobotMap;
import org.usfirst.frc.team2559.robot.commands.DriveForDistance;
import org.usfirst.frc.team2559.robot.commands.ToggleArms;
import org.usfirst.frc.team2559.robot.commands.forklift.COTTONMOUTH_MAX;
import org.usfirst.frc.team2559.robot.commands.forklift.COTTONMOUTH_MIN;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pickup1ToteAndRecycle extends CommandGroup {
    
    public  Pickup1ToteAndRecycle() {
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
    	/****************************************/
    	// pickup recycling container while opening arms
    	addSequential(new COTTONMOUTH_MAX());
    	addParallel(new ToggleArms());
    	// drive straight for x inches
    	addSequential(new DriveForDistance(0.3, 18, true));
    	// lower recycling container back down on tote
    	addSequential(new COTTONMOUTH_MIN());
    	// close arms around tote
    	addSequential(new ToggleArms());
    	// drive forward while lifting tote
    	addSequential(new DriveForDistance( 0.3, 6, true));
    	addParallel(new DriveForDistance(0.3, 12, true));
    	addSequential(new COTTONMOUTH_MAX());
    	// turn to the left 90 degrees while opening arms
    	addSequential(new ToggleArms());
    	addSequential(new Turn(-90, RobotMap.AUTO_TURNING_SPEED));
    	// drive to landmark
    	addSequential(new DriveForDistance(0.6, RobotMap.AUTO_DISTANCE_TO_LANDMARK, true));
    	// turn while lowering lifter
    	addParallel(new Turn(90, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new COTTONMOUTH_MIN());
    	// open arms while driving backwards in the auto zone
    	addParallel(new ToggleArms());
    	addSequential(new DriveForDistance(0.6, -3, false));
    }
}
