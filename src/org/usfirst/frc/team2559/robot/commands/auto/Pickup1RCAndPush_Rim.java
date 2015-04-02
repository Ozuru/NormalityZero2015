package org.usfirst.frc.team2559.robot.commands.auto;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;
import org.usfirst.frc.team2559.robot.commands.DriveForDistance;
import org.usfirst.frc.team2559.robot.commands.ToggleArms;
import org.usfirst.frc.team2559.robot.commands.control.DoNothing;
import org.usfirst.frc.team2559.robot.commands.forklift.COTTONMOUTH_TIME;
import org.usfirst.frc.team2559.robot.commands.forklift.SetIntake;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Pickup1RCAndPush_Rim extends CommandGroup {
    
    public  Pickup1RCAndPush_Rim() {
    	addParallel(new DoNothing() {
			protected void initialize() {
				Robot._pneumatics.setArms(DoubleSolenoid.Value.kForward);
			}
			
			protected boolean isFinished() {
				return true;
			}
		});
    	addSequential(new COTTONMOUTH_TIME(1550, true));
    	addSequential(new SetIntake("in"));
    	addSequential(new DriveForDistance(0.55, 26, true));
    	addSequential(new ToggleArms());
    	addSequential(new Turn(-85, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new DriveForDistance(0.75, 87, true));
    	addSequential(new DriveForDistance(0.7, 10, true));
    	addSequential(new DriveForDistance(0.4, 10, true));
    	addSequential(new Turn(90, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new ToggleArms());
    	addSequential(new SetIntake("stop"));
    	addSequential(new DriveForDistance(0.65, -4, false));
    	// used to be 1800
//    	addSequential(new COTTONMOUTH_TIME(1800, false));
    	addSequential(new DriveForDistance(0.85, -2, false));
    }
}
