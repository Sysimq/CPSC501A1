package org.example;

public class CarParkingCharge extends ParkingCharge{
    @Override
    public double getParkingCharge(int hours) {
        double parkingCost = 5.0;
        if (hours > 1) {
            parkingCost += (hours - 1) * 3;
        }
        if (hours > 48 ){
            parkingCost -= (hours/48) * 20;
        }
        return parkingCost;
    }
}
