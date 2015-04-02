package org.usfirst.frc.team2559.robot.commands.auto;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;
import org.usfirst.frc.team2559.robot.commands.DriveForDistance;
import org.usfirst.frc.team2559.robot.commands.ToggleArms;
import org.usfirst.frc.team2559.robot.commands.control.DoNothing;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 *
 */
public class Push1Tote extends CommandGroup {
    
    public  Push1Tote() {
    	addParallel(new DoNothing() {
			protected void initialize() {
				Robot._pneumatics.setArms(DoubleSolenoid.Value.kForward);
			}
			
			protected boolean isFinished() {
				return true;
			}
		});
    	addSequential(new Turn(90, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new DriveForDistance(0.7, 87, true));
    	addSequential(new DriveForDistance(0.65, 10, true));
    	addSequential(new DriveForDistance(0.35, 10, true));
    	addSequential(new Turn(-90, RobotMap.AUTO_TURNING_SPEED));
    	addSequential(new ToggleArms());
    	addSequential(new DriveForDistance(0.8, -2, false));
    }
}
