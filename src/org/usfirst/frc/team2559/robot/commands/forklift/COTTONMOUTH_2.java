package org.usfirst.frc.team2559.robot.commands.forklift;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class COTTONMOUTH_2 extends Command {

	public COTTONMOUTH_2() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot._forkLift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.oi.setRumble(RumbleType.kLeftRumble, 1);
		Robot.oi.setRumble(RumbleType.kRightRumble, 1);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!(Robot._forkLift.isBotSwitch() || Robot._forkLift.isTopSwitch())) {
			if (Robot._PRISM.getCurrentLevel() < 2) {
				Robot._forkLift.setPulley(RobotMap.FORKLIFT_RAISE_SPEED);
			} else if (Robot._PRISM.getCurrentLevel() > 2) {
				Robot._forkLift.setPulley(RobotMap.FORKLIFT_LOWER_SPEED);
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (Robot._forkLift.isBotSwitch() || Robot._forkLift.isTopSwitch() || Robot._PRISM.isLevel2());
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
	}
}
