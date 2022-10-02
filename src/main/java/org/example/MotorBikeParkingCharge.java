package org.example;

public class MotorBikeParkingCharge extends ParkingCharge{
    @Override
    public double getParkingCharge(int hours) {
        double parkingCost = 3.0;
        if (hours > 1) {
            parkingCost += (hours - 1) * 2;
        }
        if (hours > 48 ){
            parkingCost -= (hours/48) * 18;
        }
        return parkingCost;
    }
}
