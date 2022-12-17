package frc.hardwareWrappers.Gyro;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.lib.Signal.Annotations.Signal;
import frc.robot.Robot;

public class WrapperedGyro  {

    AbstractGyro gyro;
    double offset_rad = 0;


    @Signal(units = "rad")
    private double curAngle_rad;

    public WrapperedGyro(){
        if(Robot.isReal()){
            gyro = new RealGyro();
        } else {
            gyro = new SimGyro();
        }
    }

    public void update(){
        // Gyros are inverted in reference frame (positive clockwise)
        // and we maintain our own offset in code when rezeroing.
        curAngle_rad = gyro.getRawAngle() * -1.0 + offset_rad;
    }

    public void reset(double curAngle_rad) {
        offset_rad = curAngle_rad;
        gyro.reset();
    }

    public void calibrate() {
        gyro.calibrate();
    }

    public double getRate_radpersec() {
        return gyro.getRate();
    }

    public double getAngle_rad() {
        return curAngle_rad;
    }

    public boolean isConnected() {
        return gyro.isConnected();
    }

    public Rotation2d getRotation2d() {
        return new Rotation2d(this.getAngle_rad());
    }
    
}
