// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
     */
    private Joystick m_stick;
  //drive set up
  WPI_VictorSPX m_leftFront = new WPI_VictorSPX(16);
  WPI_VictorSPX m_leftRear = new WPI_VictorSPX(5);
  MotorControllerGroup m_LeftGroup = new MotorControllerGroup(m_leftFront, m_leftRear);
  WPI_VictorSPX m_rightFront = new WPI_VictorSPX(17);
  WPI_VictorSPX m_rightRear = new WPI_VictorSPX(18);
  MotorControllerGroup m_RightGroup = new MotorControllerGroup(m_rightFront, m_rightRear);
  DifferentialDrive m_drive = new DifferentialDrive(m_LeftGroup, m_RightGroup);
  //WPI_VictorSPX m_intake = new WPI_VictorSPX(0);
  //set up if using semi-shooter
  //WPI_VictorSPX m_loElevator = new WPI_VictorSPX(0);
  //WPI_VictorSPX m_upElevator = new WPI_VictorSPX(0);
  private CANSparkMax m_Conveyor;
  private CANSparkMax m_Shooter;

  //

  private final Timer m_timer = new Timer();

  private double startTime;

  @Override
  public void robotInit() {
    m_stick = new Joystick(0);
    m_leftRear.setInverted(true);
    m_rightFront.setInverted(true);
    m_Conveyor = new CANSparkMax(6, MotorType.kBrushed);
    m_Shooter = new CANSparkMax(7, MotorType.kBrushed);
    

  }

  @Override
  public void robotPeriodic() 
  {

  }

  @Override
  public void autonomousInit() 
  {

    startTime = Timer.getFPGATimestamp();

  }

  @Override
  public void autonomousPeriodic() 
  {
    // Time would equal the time of the match.
    double time = Timer.getFPGATimestamp(); 
    double gametime = time - startTime;
    System.out.print(gametime);

    if(gametime < 4 || gametime >= 2)
    {
      System.out.print(gametime + "gameTime < 4");
       //m_drive.arcadeDrive(0, 0);
       //Timer.delay(1.0);
       m_drive.arcadeDrive(0, 0.5);
       //Timer.delay(1.0);
      // m_drive.arcadeDrive(0, 0);

    }
    else if(gametime > 2 || gametime != 0)
    {
      System.out.print(gametime + "gameTime < 2");  
       m_drive.arcadeDrive(0.50, 0);
    }
    else
    {
      System.out.print(gametime + " the else ");
      m_drive.arcadeDrive(0, 0);
    }
 
  

  }

  @Override
  public void teleopInit() 
  {

  }

  @Override
  public void teleopPeriodic() 
  {
     m_drive.arcadeDrive(m_stick.getX(),m_stick.getY());

     /*if(m_stick.getRawButtonPressed(3))
     {
        m_Conveyor.set(1);
     }
     else if(m_stick.getRawButtonPressed(4))
     {
       m_Conveyor.set(-1);
     }
     else
     {
       m_Conveyor.set(0);
     }*/

  }
  @Override
  public void disabledInit() 
  {

  }

  @Override
  public void disabledPeriodic() 
  {

  }

  @Override
  public void testInit() 
  {

  }

  @Override
  public void testPeriodic() 
  {

  }
}
