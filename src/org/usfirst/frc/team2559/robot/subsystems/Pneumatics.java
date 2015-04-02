package org.usfirst.frc.team2559.robot.subsystems;

import org.usfirst.frc.team2559.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	
	private final DoubleSolenoid 	_arms = new DoubleSolenoid(RobotMap.PCM_NODEID, 
											RobotMap.PCM_PORTID_SOLENOID1_FORWARD, 
											RobotMap.PCM_PORTID_SOLENOID1_REVERSE);
	private final DoubleSolenoid	_pusher = new DoubleSolenoid(RobotMap.PCM_NODEID,
											RobotMap.PCM_PORTID_SOLENOID2_FORWARD,
											RobotMap.PCM_PORTID_SOLENOID2_REVERSE);

    public void initDefaultCommand() {}
    
    public void toggleArms() {
    	if(_arms.get() == DoubleSolenoid.Value.kForward) {
    		_arms.set(DoubleSolenoid.Value.kReverse);
    	} else if(_arms.get() == DoubleSolenoid.Value.kReverse){
    		_arms.set(DoubleSolenoid.Value.kForward);
    	} else {
    		_arms.set(DoubleSolenoid.Value.kReverse);
    	}
    }
    
    public void extendPusher() {
    	_pusher.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractPusher() {
    	_pusher.set(DoubleSolenoid.Value.kReverse);
    }
    
    public DoubleSolenoid.Value getArms() {
    	return _arms.get();
    }
    
    public void setArms(DoubleSolenoid.Value val) {
    	_arms.set(val);
    }
}

