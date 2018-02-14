package org.usfirst.frc.team1111.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This is an experimental class that tests the drive train with the shifting gearbox.
 * The gearbox will have a 3-position piston that will be controlled by two DoubleSolenoids.
 * Configuration is TBD and this will be updated when that is available.
 * In the meantime, this code is set to work with the current 2-position piston gearbox.
 * @author Braidan
 *
 */
public class Robot extends IterativeRobot {
	Joystick joy = new Joystick(0);
	DoubleSolenoid soleA = new DoubleSolenoid(0, 1111); //TODO: Configure
	DoubleSolenoid soleB = new DoubleSolenoid(0, 1111); //TODO: Configure
	DoubleSolenoid soleGearbox = new DoubleSolenoid(0, 1111); //TODO: Configure
	TalonSRX frontLeft = new TalonSRX(46); //TODO: Configure
	TalonSRX frontRight = new TalonSRX(47); //TODO: Configure
	TalonSRX backLeft = new TalonSRX(55); //TODO: Configure
	TalonSRX backRight = new TalonSRX(39); //TODO: Configure
	int state = 0; //0 is LOW, 1 is NEUTRAL, 2 is HIGH
	int dPadUp = 0;
	int dPadDown = 90;
	boolean low = false;
	
	@Override
	public void robotInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		//-----EXPERIMENTAL CODE FOR 3-POSITION GEARBOX-----
//		if (joy.getPOV() == dPadUp && state <= 2) {
//			changeGear(1);
//		}
//		else if (joy.getPOV() == dPadDown && state >= 0) {
//			changeGear(-1);
//		}
		
		//Gear shift code for 2-position piston
		if (joy.getPOV() == dPadUp || joy.getPOV() == dPadDown) { //Press UP or DOWN DPAD to change gears
			changeGear();
		}
		
		//Normal tank drive
		drive(joy.getRawAxis(1), joy.getRawAxis(3));
	}
	
	public void changeGear() {
		low = !low;
		
		if (low) {
			soleGearbox.set(Value.kForward); //TODO: Verify and configure
		}
		else {
			soleGearbox.set(Value.kReverse); //TODO: Verify and configure
		}
	}
	
	public void changeGear(int dir) {
		state += dir;
		if (state == 0) {
			// Air to chamber C
//			soleA.set(false);
//			soleB.set(false);
		}
		else if (state == 1) {
			//Air to chamber A
//			soleA.set(true);
//			soleB.set(false);
		}
		else if (state == 2) {
			//Air to chamber B
//			soleA.set(false);
//			soleB.set(true);
		}
	}
	
	public void drive(double left, double right) {
		frontLeft.set(ControlMode.PercentOutput, left);
		backLeft.set(ControlMode.PercentOutput, -left);
		frontRight.set(ControlMode.PercentOutput, right);
		backRight.set(ControlMode.PercentOutput, -right);
	}
}
