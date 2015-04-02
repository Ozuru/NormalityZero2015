package org.usfirst.frc.team2559.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.*;
import org.usfirst.frc.team2559.robot.RobotMap;
import org.usfirst.frc.team2559.robot.commands.TankDrive;

/**
 *
 */

public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private final Talon 		_leftfr = new Talon(RobotMap.PORT_DRIVETRAIN_LEFT_FR),
								_leftback = new Talon(RobotMap.PORT_DRIVETRAIN_LEFT_BACK),
								_rightfr = new Talon(RobotMap.PORT_DRIVETRAIN_RIGHT_FR),
								_rightback = new Talon(RobotMap.PORT_DRIVETRAIN_RIGHT_BACK);
	
	private final RobotDrive 	_drive = new RobotDrive(_leftfr, _leftback, _rightfr, _rightback);
	
	private final Gyro 			_gyro = new Gyro(RobotMap.PORT_ANALOG_GYRO);
	
	private final Encoder 		_leftEncoder = new Encoder(RobotMap.PORT_ENCODER_LEFT_1, 
								RobotMap.PORT_ENCODER_LEFT_2);
	private final Encoder 		_rightEncoder = new Encoder(RobotMap.PORT_ENCODER_RIGHT_1, 
								RobotMap.PORT_ENCODER_RIGHT_2);
	
	private final Accelerometer	_accel = new BuiltInAccelerometer();
	
	private final AnalogInput _ranger = new AnalogInput(RobotMap.PORT_ANALOG_RANGEFINDER);
	
	private double _gyroOffset = 0;
	
	
	boolean reverseDrive = false,
			slowDrive = false,
			auton = false;
	
	public DriveTrain() {
		_leftEncoder.setDistancePerPulse((Math.PI * 4)/250);
    	_rightEncoder.setDistancePerPulse((Math.PI * 4)/250);
    	resetGyro();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
    
    public double getDistanceIn() {
    	return _ranger.getAverageVoltage() / 0.009765625;
    }
    
    
    public void setReverseDrive(boolean val) {
    	reverseDrive = val;
    }
    
    public void setSlowDrive(boolean val) {
    	slowDrive = val;
    }
	public void tankDrive(double left, double right) {
		double leftCalc = left * Math.pow(Math.abs(left), 0.3);
		double rightCalc = right * Math.pow(Math.abs(right), 0.3);
		System.out.println("Left: " + leftCalc);
		System.out.println("Right: " + rightCalc);
		if(auton) {
			_drive.tankDrive(left, right);
		} else if (!reverseDrive && !slowDrive) {
			_drive.tankDrive(leftCalc, rightCalc);
		} else if(reverseDrive && slowDrive) {
			_drive.tankDrive(-left * RobotMap.SLOWDRIVE_CONSTANT, -right * RobotMap.SLOWDRIVE_CONSTANT);
		} else if(!reverseDrive && slowDrive) {
			_drive.tankDrive(leftCalc * RobotMap.SLOWDRIVE_CONSTANT, rightCalc * RobotMap.SLOWDRIVE_CONSTANT);
		} else if(reverseDrive && !slowDrive) {
			_drive.tankDrive(-leftCalc, -rightCalc);
		}
	}
//    public void tankDrive(double left, double right) {
//		if(auton) {
//			_drive.tankDrive(left, right);
//		} else if (!reverseDrive && !slowDrive) {
//			_drive.tankDrive((left * Math.abs(left) * 0.8), (right * Math.abs(right)) * 0.8);
//		} else if(reverseDrive && slowDrive) {
//			_drive.tankDrive(-left * RobotMap.SLOWDRIVE_CONSTANT, -right * RobotMap.SLOWDRIVE_CONSTANT);
//		} else if(!reverseDrive && slowDrive) {
//			_drive.tankDrive((left * Math.abs(left) * 0.8) * RobotMap.SLOWDRIVE_CONSTANT, (right * Math.abs(right)) * 0.8 * RobotMap.SLOWDRIVE_CONSTANT);
//		} else if(reverseDrive && !slowDrive) {
//			_drive.tankDrive(-left * Math.abs(left) * 0.8, -right * Math.abs(right) * 0.8);
//		}
//	}

	
	public double getLeftDistance() {
		return -_leftEncoder.getDistance();
	}
	
	public double getRightDistance() {
		return _rightEncoder.getDistance();
	}
	
	public double getLeftSpeedEnc() {
		return -_leftEncoder.getRate();
	}
	
	public double getRightSpeedEnc() {
		return _rightEncoder.getRate();
	}
	
	public void clearEncoder() {
		_leftEncoder.reset();
		_rightEncoder.reset();
	}
	
	public void setAuton(boolean val) {
		auton = val;
	}

    public void stop() {
    	_drive.drive(0, 0);
    }
    
    public double getGyroAngle() {
    	return _gyro.getAngle() - _gyroOffset;
    }
    
    public double getGyroRate() {
		return _gyro.getRate();
	}
    
    public void resetGyro() {
    	_gyro.reset();
    	clearGyro();
    }
    
    public void clearGyro() {
    	_gyroOffset = _gyro.getAngle();
    }
    
    public double getXAccel() {
    	return _accel.getX();
    }
    
    public double getYAccel() {
    	return _accel.getY();
    }

}

