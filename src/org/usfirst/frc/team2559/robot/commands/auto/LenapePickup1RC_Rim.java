package org.usfirst.frc.team2559.robot.commands.auto;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.commands.control.DoNothing;
import org.usfirst.frc.team2559.robot.commands.forklift.COTTONMOUTH_TIME;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LenapePickup1RC_Rim extends CommandGroup {
    
    public  LenapePickup1RC_Rim() {
    	addParallel(new DoNothing() {
			protected void initialize() {
				Robot._pneumatics.setArms(DoubleSolenoid.Value.kForward);
			}
			
			protected boolean isFinished() {
				return true;
			}
		});
    	addSequential(new COTTONMOUTH_TIME(1550, true));
    }
}
