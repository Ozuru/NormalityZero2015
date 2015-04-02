package org.usfirst.frc.team2559.robot.commands.auto;

import org.usfirst.frc.team2559.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Align extends Command {

	double distanceToCenter = 0;

	public Align() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot._driveTrain);
		requires(Robot._pneumatics);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot._driveTrain.setAuton(true);
		Robot._driveTrain.clearEncoder();
		Robot._driveTrain.clearGyro();
		distanceToCenter = SmartDashboard.getNumber("toteOffset", 0);
		if (Robot._pneumatics.getArms() != DoubleSolenoid.Value.kReverse) {
			Robot._pneumatics.setArms(DoubleSolenoid.Value.kReverse);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		distanceToCenter = SmartDashboard.getNumber("toteOffset", 0);
		if (distanceToCenter < -3) {
			Robot._driveTrain.tankDrive(-0.23, 0.23);
		} else if (distanceToCenter > 3) {
			Robot._driveTrain.tankDrive(0.23, -0.23);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (distanceToCenter >= -3 && distanceToCenter <= 3);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot._driveTrain.setAuton(false);
		Robot._driveTrain.tankDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("AlignAndDrive interupted");
		end();
	}
}
