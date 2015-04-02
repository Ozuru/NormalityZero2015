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
public class ThreeToteAuton_Push extends CommandGroup {
    
    public  ThreeToteAuton_Push() {
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
    	addSequential(new COTTONMOUTH_TIME(1550, true));
    	addSequential(new SetIntake("in"));
    	addSequential(new DriveForDistance(0.55, 26, true));
    	addSequential(new ToggleArms());
    	addSequential(new COTTONMOUTH_TIME(900, false));
    	addSequential(new DriveForDistance(0.65, -4, false));
    	addSequential(new COTTONMOUTH_MIN());
    	addSequential(new DriveForDistance(0.55, 8, true));
    	addSequential(new COTTONMOUTH_TIME(1550, true));
    	addSequential(new DriveForDistance(0.55, 36, true));
    	addSequential(new DriveForDistance(0.65, -4, false));
    	addSequential(new COTTONMOUTH_MIN());
    	addSequential(new DriveForDistance(0.55, 8, true));
    	addSequential(new ToggleArms());
    	addSequential(new Turn(-85, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new DriveForDistance(0.75, 87, true));
    	addSequential(new DriveForDistance(0.7, 10, true));
    	addSequential(new DriveForDistance(0.4, 10, true));
    	addSequential(new Turn(90, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new ToggleArms());
    	addSequential(new SetIntake("stop"));
    	addSequential(new DriveForDistance(0.65, -4, false));
    	
    	
    }
}
