package org.usfirst.frc.team2559.robot.subsystems;

import org.usfirst.frc.team2559.robot.Robot;
import org.usfirst.frc.team2559.robot.RobotMap;
import org.usfirst.frc.team2559.robot.commands.forklift.EGOTISTICALGIRAFFE;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PRISM extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private int _curLevel;
	
	private final DigitalInput 	//_level1 = new DigitalInput(RobotMap.PORT_LEVEL1_SWITCH),
							  	_level2 = new DigitalInput(RobotMap.PORT_LEVEL2_SWITCH),
							  	_level3 = new DigitalInput(RobotMap.PORT_LEVEL3_SWITCH),
							  	_level4 = new DigitalInput(RobotMap.PORT_LEVEL4_SWITCH);

    public void initDefaultCommand() {
        setDefaultCommand(new EGOTISTICALGIRAFFE());
    }
    
    public int getCurrentLevel() {
    	return _curLevel;
    }
    
    public void setCurrentLevel(int level) {
    	_curLevel = level;
    }
    
    public boolean isLevelMin() {
    	return Robot._forkLift.isBotSwitch();
    }
    
//    public boolean isLevel1() {
//    	return _level1.get();
//    }
    
    public boolean isLevel2() {
    	return _level2.get();
    }
    
    public boolean isLevel3() {
    	return _level3.get();
    }
    
    public boolean isLevel4() {
    	return _level4.get();
    }
    
    public boolean isLevelMax() {
    	return Robot._forkLift.isTopSwitch();
    }
    
    
    
}

