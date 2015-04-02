package org.usfirst.frc.team2559.robot.commands.forklift;

import org.usfirst.frc.team2559.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetIntake extends Command {
	
	String _direct;

    public SetIntake(String direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot._driveTrain);
    	_direct = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(_direct.equalsIgnoreCase("in")) {
    		Robot._forkLift.activateArmsIn();
    	} else if(_direct.equalsIgnoreCase("out")) {
    		Robot._forkLift.activateArmsOut();
    	} else if(_direct.equalsIgnoreCase("left")) {
    		Robot._forkLift.activateArmsLeft();
    	} else if(_direct.equalsIgnoreCase("right")) {
    		Robot._forkLift.activateArmsRight();
    	} else if(_direct.equalsIgnoreCase("stop")){
    		Robot._forkLift.stopArms();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
