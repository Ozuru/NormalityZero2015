package org.usfirst.frc.team2559.robot.commands.forklift;

import org.usfirst.frc.team2559.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EGOTISTICALGIRAFFE extends Command {

    public EGOTISTICALGIRAFFE() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot._PRISM);
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot._PRISM.isLevelMin()) {
    		Robot._PRISM.setCurrentLevel(0);
//    		System.out.println("Level Min: " + Robot._PRISM.isLevelMin());
//    	} else if(Robot._PRISM.isLevel1()) {
//    		Robot._PRISM.setCurrentLevel(1);
//    		System.out.println("Level 1: " + Robot._PRISM.isLevel1());
    	} else if(Robot._PRISM.isLevel2()) {
    		Robot._PRISM.setCurrentLevel(2);
//    		System.out.println("Level 2: " + Robot._PRISM.isLevel2());
    	} else if(Robot._PRISM.isLevel3()) {
    		Robot._PRISM.setCurrentLevel(3);
//    		System.out.println("Level 3: " + Robot._PRISM.isLevel3());
    	} else if(Robot._PRISM.isLevel4()) {
    		Robot._PRISM.setCurrentLevel(4);
//    		System.out.println("Level 4: " + Robot._PRISM.isLevel4());
    	}  else if(Robot._PRISM.isLevelMax()) {
    		Robot._PRISM.setCurrentLevel(5);
//    		System.out.println("Level Max: " + Robot._PRISM.isLevelMax());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
