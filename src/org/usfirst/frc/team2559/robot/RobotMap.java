package org.usfirst.frc.team2559.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static final double SLOWDRIVE_CONSTANT = 0.7;
	
	public static final int AUTO_DISTANCE_TO_LANDMARK = 119;
	public static final double AUTO_DISTANCE_FROM_TOTE = 74;
	public static final double AUTO_TURNING_SPEED = 0.6;
	
	public static final int JOYSTICK_AXIS_DRIVE_LEFT = 1,
							JOYSTICK_AXIS_DRIVE_RIGHT = 5;
	
	public static final double 	FORKLIFT_RAISE_SPEED = -1,
								FORKLIFT_LOWER_SPEED = 0.5;
	
	public static final int PORT_DRIVETRAIN_LEFT_FR = 3,
							PORT_DRIVETRAIN_LEFT_BACK = 4,
							PORT_DRIVETRAIN_RIGHT_FR = 5,
							PORT_DRIVETRAIN_RIGHT_BACK = 6;

	public static final int PORT_FORKLIFT_MOTOR_0 = 2;
	
	public static final int PCM_NODEID = 0,
							PDP_NODEID = 1,
							PCM_NODEID_COMPRESSOR = 0,
							PCM_PORTID_SOLENOID1_FORWARD = 0,
							PCM_PORTID_SOLENOID1_REVERSE = 1,
							PCM_PORTID_SOLENOID2_FORWARD = 2,
							PCM_PORTID_SOLENOID2_REVERSE = 3;
	public static final int PORT_LIMSWITCH_TOP = 0,
							PORT_LIMSWITCH_TOP_2 = 2,
							PORT_LIMSWITCH_BOT = 1;
	
	public static final int //PORT_LEVEL1_SWITCH = 2,
							PORT_LEVEL2_SWITCH = 3,
							PORT_LEVEL3_SWITCH = 4,
							PORT_LEVEL4_SWITCH = 5;
	
	public static final int PORT_ANALOG_GYRO = 0,
							PORT_ANALOG_RANGEFINDER = 1;	
	
	public static final int PORT_RELAY_LEFT = 1,
							PORT_RELAY_RIGHT = 2;
	
	public static final int PORT_ENCODER_LEFT_1 = 6,
							PORT_ENCODER_LEFT_2 = 7,
							PORT_ENCODER_RIGHT_1 = 8,
							PORT_ENCODER_RIGHT_2 = 9;
	
	public static final boolean ANNOY_THE_FUCK_OUT_OF_THE_DRIVERS = true;
	public static final boolean USE_TOO_MUCH_BANDWIDTH = false;
	
	public static String ARMS_DIAG = "Not spinning!";
	
}
