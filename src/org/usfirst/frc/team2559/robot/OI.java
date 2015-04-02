package org.usfirst.frc.team2559.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team2559.robot.commands.*;
import org.usfirst.frc.team2559.robot.commands.control.*;
import org.usfirst.frc.team2559.robot.commands.forklift.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// // CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	// // TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	Joystick driverStick = new Joystick(0);
	Joystick shooterStick = new Joystick(1);

	Button 	driverButton1 = new JoystickButton(driverStick, 1),
			_back = new JoystickButton(driverStick, 2),
			_forw = new JoystickButton(driverStick, 3),
			_resetEnc = new JoystickButton(driverStick, 4),
			_slow = new JoystickButton(driverStick, 5),
			_reverse = new JoystickButton(driverStick, 6),
			_feedMe = new JoystickButton(driverStick, 7),
			driverButton8 = new JoystickButton(driverStick, 8),
			driverButton9 = new JoystickButton(driverStick, 9),
			driverButton10 = new JoystickButton(driverStick, 10);

	Button _arms = new JoystickButton(shooterStick, 1),
			_stopArms = new JoystickButton(shooterStick, 2),
			_raiseLift = new JoystickButton(shooterStick, 3),
			_lowerLift = new JoystickButton(shooterStick, 4),
			_pullin = new JoystickButton(shooterStick, 5),
			_pushout = new JoystickButton(shooterStick, 6),
			shooterButton7 = new JoystickButton(shooterStick, 7),
			shooterButton8 = new JoystickButton(shooterStick, 8),
			_rotLeft = new JoystickButton(shooterStick, 9),
			_rotRight = new JoystickButton(shooterStick, 10),
			_noodle = new JoystickButton(shooterStick, 11),
			shooterButton12 = new JoystickButton(shooterStick, 12);

	public OI() {
		//_sol1Toggle.whenPressed(new ToggleSolenoid());		
		_reverse.whileHeld(new Command() {
			protected void initialize() {
				Robot._driveTrain.setReverseDrive(true);
			}

			protected void execute() {
			}

			protected boolean isFinished() {
				return false;
			}

			protected void end() {
				Robot._driveTrain.setReverseDrive(false);
			}

			protected void interrupted() {
				end();
			}
		});
		_forw.whileHeld(new DobbyForward());
		_back.whileHeld(new DobbyBackwards());
		_resetEnc.whenPressed(new DoNothing() {
			protected void initialize() {
				Robot._driveTrain.clearEncoder();
			}
			
			protected boolean isFinished() {
				return true;
			}
		});
		_slow.whileHeld(new Command() {
			protected void initialize() {
				Robot._driveTrain.setSlowDrive(true);
			}

			protected void execute() {
			}

			protected boolean isFinished() {
				return false;
			}

			protected void end() {
				Robot._driveTrain.setSlowDrive(false);
			}

			protected void interrupted() {
				end();
			}
		});
		_feedMe.whenPressed(new LoadFromFeeder());
		_raiseLift.whileHeld(new RaiseForklift());
		_lowerLift.whileHeld(new LowerForklift());
		_rotLeft.whileHeld(new DoNothing() {
			protected void initialize() {
				Robot._forkLift.activateArmsLeft();
			}

			protected boolean isFinished() {
				return false;
			}

			protected void end() {
				Robot._forkLift.stopArms();
			}

			protected void interrupted() {
				end();
			}
		});
		_rotRight.whileHeld(new DoNothing() {
			protected void initialize() {
				Robot._forkLift.activateArmsRight();
			}

			protected boolean isFinished() {
				return false;
			}

			protected void end() {
				Robot._forkLift.stopArms();
			}

			protected void interrupted() {
				end();
			}
		});
		_arms.whenPressed(new ToggleArms());
		_pullin.whenPressed(new SetIntake("in"));
		_pushout.whenPressed(new SetIntake("out"));
		_stopArms.whenPressed(new SetIntake("stop"));
		_noodle.whenPressed(new Noodler());
	}
	
	public double _zeroDeadzone(double joyValue, double dead) {
		return Math.abs(joyValue) > dead ? joyValue : 0;
	}

	public double getLeftDrive() {
		return _zeroDeadzone(-driverStick.getRawAxis(RobotMap.JOYSTICK_AXIS_DRIVE_LEFT), 0.15);
	}

	public double getRightDrive() {
		return _zeroDeadzone(-driverStick.getRawAxis(RobotMap.JOYSTICK_AXIS_DRIVE_RIGHT), 0.15);
		}
	
	public double getSliderVal() {
		return shooterStick.getRawAxis(3);
	}

	public void setRumble(RumbleType type, float num) {
		driverStick.setRumble(type, num);
	}

	public double getTrigger() {
		return driverStick.getRawAxis(3);
	}

}
