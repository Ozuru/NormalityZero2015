package org.usfirst.frc.team2559.robot.commands.auto;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;
import org.usfirst.frc.team2559.robot.commands.DriveForDistance;
import org.usfirst.frc.team2559.robot.commands.control.DoNothing;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class DriveToZone extends CommandGroup {
    
    public  DriveToZone() {
    	addParallel(new DoNothing() {
			protected void initialize() {
				Robot._pneumatics.setArms(DoubleSolenoid.Value.kForward);
			}
			
			protected boolean isFinished() {
				return true;
			}
		});
    	addSequential(new DriveForDistance(0.75, 87, true));
    	addSequential(new DriveForDistance(0.7, 10, true));
    	addSequential(new DriveForDistance(0.4, 10, true));
    }
}
