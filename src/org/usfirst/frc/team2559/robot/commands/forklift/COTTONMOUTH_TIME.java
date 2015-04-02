package org.usfirst.frc.team2559.robot.commands.forklift;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class COTTONMOUTH_TIME extends Command {

	Long _startTime;
	double _endTime;
	double _time;
	boolean _up;

	// if input time is larger than a constant reject and set as default

	public COTTONMOUTH_TIME(double time, boolean up) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot._forkLift);
		_time = time;
		_up = up;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		_startTime = System.currentTimeMillis();
		_endTime = _startTime + _time;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {	
		if(_up) {
			if(!Robot._forkLift.isBotSwitch()) {
				Robot._forkLift.setPulley(RobotMap.FORKLIFT_RAISE_SPEED);
			}
		} else {
			if(!Robot._forkLift.isTopSwitch()) {
				Robot._forkLift.setPulley(RobotMap.FORKLIFT_LOWER_SPEED);
			}
		}
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(_up) {
		return System.currentTimeMillis() > _endTime || Robot._forkLift.isBotSwitch();
		} else {
		return System.currentTimeMillis() > _endTime || Robot._forkLift.isTopSwitch();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot._forkLift.stopPulley();
		Robot.oi.setRumble(RumbleType.kLeftRumble, 0);
		Robot.oi.setRumble(RumbleType.kRightRumble, 0);
	}
	
	protected void interrupted() {
		end();
	}
}
