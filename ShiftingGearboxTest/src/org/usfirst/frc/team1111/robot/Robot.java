/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1111.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * This is an experimental class that tests the drive train with the shifting gearbox.
 * The gearbox will have a 3-position cylinder that will be controlled by two DoubleSolenoids.
 * Configuration is TBD and this will be updated when that is available.
 * @author Braidan
 *
 */
public class Robot extends IterativeRobot {
	Joystick joy = new Joystick(0);
	DoubleSolenoid soleA = new DoubleSolenoid(0, 1111); //TODO: Configure
	DoubleSolenoid soleB = new DoubleSolenoid(0, 1111); //TODO: Configure
	TalonSRX frontLeft = new TalonSRX(46);
	TalonSRX frontRight = new TalonSRX(47);
	TalonSRX backLeft = new TalonSRX(55);
	TalonSRX backRight = new TalonSRX(39);
	int state = 0; //0 is LOW, 1 is NEUTRAL, 2 is HIGH
	int dPadUp = 0;
	int dPadDown = 90;
	
	@Override
	public void robotInit() {
		
	}

	@Override
	public void teleopPeriodic() {
		if (joy.getPOV() == dPadUp && state <= 2) {
			changeGear(1);
		}
		else if (joy.getPOV() == dPadDown && state >= 0) {
			changeGear(-1);
		}
		
		
		drive(joy.getRawAxis(1), joy.getRawAxis(3));
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
