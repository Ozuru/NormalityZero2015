package org.usfirst.frc.team2559.robot.subsystems;

import org.usfirst.frc.team2559.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Forklift extends Subsystem {
    
	private final Talon 				_motor = new Talon(RobotMap.PORT_FORKLIFT_MOTOR_0);
//	private final DigitalInput			_switchTop = new DigitalInput(RobotMap.PORT_LIMSWITCH_TOP);
	private final DigitalInput 			_switchTop2 = new DigitalInput(RobotMap.PORT_LIMSWITCH_TOP_2);
	private final DigitalInput			_switchBot = new DigitalInput(RobotMap.PORT_LIMSWITCH_BOT);
	private final Relay 				_leftArm = new Relay(RobotMap.PORT_RELAY_LEFT);
	private final Relay					_rightArm = new Relay(RobotMap.PORT_RELAY_RIGHT);

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
//    public DigitalInput getTopSwitch() {
//    	return _switchTop;
//    }
    
    public DigitalInput getBotSwitch() {
    	return _switchBot;
    }
    
    public boolean isTopSwitch() {
//    	return _switchTop.get() || _switchTop2.get();
    	return _switchTop2.get();
    }
    
    public boolean isBotSwitch() {
    	return _switchBot.get();
    }
    
    public void setPulley(double speed) {
    	if(speed > 1) {
    		_motor.set(1);
    	} else if(speed < -1) {
    		_motor.set(-1);
    	} else {
    		_motor.set(speed);
    	}
    }
    
    public void stopPulley() {
    	_motor.stopMotor();
    }
    
    public void activateArmsIn() {
//    	_leftArm.set(Relay.Value.kOn);
//    	_rightArm.set(Relay.Value.kOn);
    	_leftArm.set(Relay.Value.kForward);
    	_rightArm.set(Relay.Value.kForward);
    	RobotMap.ARMS_DIAG = "Spinning in!";
    }
    
    public void activateArmsOut() {
//    	_leftArm.set(Relay.Value.kOn);
//    	_rightArm.set(Relay.Value.kOn);
    	_leftArm.set(Relay.Value.kReverse);
    	_rightArm.set(Relay.Value.kReverse);
    	RobotMap.ARMS_DIAG = "Spinning out!";
    }
    
    public void activateArmsLeft() {
    	_leftArm.set(Relay.Value.kReverse);
    	_rightArm.set(Relay.Value.kForward);
    	RobotMap.ARMS_DIAG = "Rotating left!";
    }
    
    public void activateArmsRight() {
    	_leftArm.set(Relay.Value.kForward);
    	_rightArm.set(Relay.Value.kReverse);
    	RobotMap.ARMS_DIAG = "Rotating right!";
    }
    
    public void stopArms() {
    	_leftArm.set(Relay.Value.kOff);
    	_rightArm.set(Relay.Value.kOff);
    	RobotMap.ARMS_DIAG = "Not spinning!";
    }
}

