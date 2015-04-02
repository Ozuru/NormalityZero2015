package org.usfirst.frc.team2559.robot.commands.forklift;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class COTTONMOUTH_MAX extends Command {

	Long startTime;

	// if input time is larger than a constant reject and set as default

	public COTTONMOUTH_MAX() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot._forkLift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = System.currentTimeMillis();
//		Robot.oi.setRumble(RumbleType.kLeftRumble, 1);
//		Robot.oi.setRumble(RumbleType.kRightRumble, 1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		 * execute: raise motor if raiseTime + current time is not greater than
		 * the maxTime 
		 * execute: if it is, raise for maxTime - currentTime (raise
		 * to the max)
		 * 
		 * isFinished: set the timeUp to the original value + the difference between the
		 * current time and the start time
		 * isFinished: stop if the current time is greater than the raise time +
		 * the start time
		 */
		
		if(!Robot._forkLift.isBotSwitch()) {
			Robot._forkLift.setPulley(RobotMap.FORKLIFT_RAISE_SPEED);
		}		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot._forkLift.isBotSwitch();
//		RobotMap.TIMEMS_FORKLIFT_DOWN += leftoverTime + (System.currentTimeMillis() - startTime);
//		RobotMap.TIMEMS_FORKLIFT_UP -= leftoverTime + (System.currentTimeMillis() - startTime);
//		if(RobotMap.TIMEMS_FORKLIFT_UP <= 0)
//			RobotMap.TIMEMS_FORKLIFT_UP = 0;
//		if(RobotMap.TIMEMS_FORKLIFT_DOWN > RobotMap.TIMEMS_FORKLIFT_MAX_DOWN)
//			RobotMap.TIMEMS_FORKLIFT_DOWN = RobotMap.TIMEMS_FORKLIFT_MAX_DOWN;
//		
//		System.out.println(RobotMap.TIMEMS_FORKLIFT_DOWN);
//		if(_lowerTime + leftoverTime < RobotMap.TIMEMS_FORKLIFT_MAX_DOWN) {
//			System.out.println("Option 1");
//			return System.currentTimeMillis() > (_lowerTime + startTime);
//		} else if(_lowerTime + leftoverTime >= RobotMap.TIMEMS_FORKLIFT_MAX_DOWN){
//			System.out.println("Option 2");
//			return System.currentTimeMillis() > (RobotMap.TIMEMS_FORKLIFT_MAX_DOWN + startTime);
//		}
//		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot._forkLift.stopPulley();
		Robot.oi.setRumble(RumbleType.kLeftRumble, 0);
		Robot.oi.setRumble(RumbleType.kRightRumble, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
