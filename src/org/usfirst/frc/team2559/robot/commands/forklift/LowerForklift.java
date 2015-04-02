package org.usfirst.frc.team2559.robot.commands.forklift;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class LowerForklift extends Command {

	Long startTime;

	// if input time is larger than a constant reject and set as default

	public LowerForklift() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot._forkLift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		startTime = System.currentTimeMillis();
		if (RobotMap.ANNOY_THE_FUCK_OUT_OF_THE_DRIVERS) {
			Robot.oi.setRumble(RumbleType.kLeftRumble, 1);
			Robot.oi.setRumble(RumbleType.kRightRumble, 1);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!Robot._forkLift.isTopSwitch()) {
			Robot._forkLift.setPulley((-Robot.oi.getSliderVal() + 1) / 2);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot._forkLift.isTopSwitch();
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
