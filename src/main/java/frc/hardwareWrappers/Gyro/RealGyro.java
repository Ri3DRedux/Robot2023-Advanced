package frc.hardwareWrappers.Gyro;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI.Port;

public class RealGyro extends AbstractGyro {

    ADXRS450_Gyro realGyro;

    public RealGyro(){
        realGyro = new ADXRS450_Gyro(Port.kOnboardCS0);
        
        System.out.println("======================================");
        System.out.println("== GYRO: CALIBRATION IN PROCESS...");
        realGyro.calibrate();
        System.out.println("== ... Complete!");
        System.out.println("======================================");
    }

    @Override
    public void reset() {
        realGyro.reset();
    }

    @Override
    public void calibrate() {
        realGyro.calibrate();
    }

    @Override
    public double getRate() {
        return Units.degreesToRadians(realGyro.getRate());
    }

    @Override
    public double getRawAngle() {
        return Units.degreesToRadians(realGyro.getAngle());
    }

    @Override
    public boolean isConnected() {
        return realGyro.isConnected();
    }
    
}
