package org.usfirst.frc.team2559.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;

import org.usfirst.frc.team2559.robot.commands.auto.*;
import org.usfirst.frc.team2559.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	Compressor compressor;
	public static DriveTrain _driveTrain;
	public static Pneumatics _pneumatics;
	public static Forklift _forkLift;
	public static PRISM _PRISM;
	Command autonomousCommand;

	// tribute to Hurricane Joe
	String[] autonomiceNames;
	Command[] autonomice;
	SendableChooser chooser = new SendableChooser();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	public void robotInit() {
		_driveTrain = new DriveTrain();
		_pneumatics = new Pneumatics();
		_forkLift = new Forklift();
		_PRISM = new PRISM();
		oi = new OI();

		compressor = new Compressor(RobotMap.PCM_NODEID_COMPRESSOR);
		compressor.setClosedLoopControl(true);
		compressor.start();
		// instantiate the command used for the autonomous period

//		autonomiceNames = new String[] { "Auton - Push 1 Tote",
//				"Auton - Push 1 Tote 1 RC", "Auton - Drive Only",
//				"Auton - Three Totes", "Auton - Three Totes Push" };
//		autonomice = new Command[] { new Push1Tote(), new Pickup1RCAndPush(),
//				new DriveToZone(), new ThreeToteAuton(),
//				new ThreeToteAuton_Push() };
		autonomiceNames = new String[] {
				"Auton - Push 1 Tote 1 RC Handle (SAFE)",
				"Auton - Push 1 Tote 1 RC Rim (SAFE)",
				"Auton - Drive Only (ALPHA)",
				"Auton - Three Totes Push (ALPHA)",
				"Auton - Lenape Pickup 1 RC Handle",
				"Auton - Lenape Do Nothing" };
		autonomice = new Command[] { new Pickup1RCAndPush(),
				new Pickup1RCAndPush_Rim(), new DriveToZone(),
				new ThreeToteAuton_Push(), new LenapePickup1RC_Rim(),
				new LenapeDoNothing() };

		for (int i = 0; i < autonomice.length; i++) {
			chooser.addObject(autonomiceNames[i], autonomice[i]);
		}

		SmartDashboard.putData("Which autonomous?", chooser);
		SmartDashboard.putData(Scheduler.getInstance());

		new Command("Sensor feedback") {
			protected void initialize() {
			}

			protected void execute() {
				sendSensorData();

			}

			protected boolean isFinished() {
				return false;
			}

			protected void end() {
			}

			protected void interrupted() {
				end();
			}
		}.start();
	}

	private void sendSensorData() {
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard
				.putNumber("Left Distance", _driveTrain.getLeftDistance());
		SmartDashboard.putNumber("Left Speed", _driveTrain.getLeftSpeedEnc());
		SmartDashboard.putNumber("Right Distance",
				_driveTrain.getRightDistance());
		SmartDashboard.putNumber("Right Speed", _driveTrain.getRightSpeedEnc());
		SmartDashboard.putNumber("Battery Voltage", DriverStation.getInstance()
				.getBatteryVoltage());
		SmartDashboard.putBoolean("Enabled", DriverStation.getInstance()
				.isEnabled());
		SmartDashboard.putBoolean("Autonomous", DriverStation.getInstance()
				.isAutonomous());
		SmartDashboard.putBoolean("TeleOp", DriverStation.getInstance()
				.isOperatorControl());
		SmartDashboard.putBoolean("FMS", DriverStation.getInstance()
				.isFMSAttached());
		SmartDashboard.putNumber("Forklift Level", _PRISM.getCurrentLevel());
		SmartDashboard.putNumber("Gyro Angle", _driveTrain.getGyroAngle());
		SmartDashboard.putNumber("Range Finder", _driveTrain.getDistanceIn());
		SmartDashboard.putString("Wheels Info", RobotMap.ARMS_DIAG);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		Robot._driveTrain.setAuton(true);
		autonomousCommand = (Command) chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
		sendSensorData();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		Robot._driveTrain.setAuton(false);
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		sendSensorData();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		sendSensorData();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		sendSensorData();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		sendSensorData();
	}

}
