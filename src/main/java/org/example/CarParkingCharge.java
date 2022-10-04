package org.example;

public class CarParkingCharge extends ParkingCharge{
    /**
     * Calculate Car Slot Parking Charge
     * @param hours
     * @return
     */
    @Override
    public double getParkingCharge(int hours) {
        //Start with $5 with minimum of an hour parking
        double parkingCost = 5.0;
        //charge $3 for every hour added after initial hour
        if (hours > 1) {
            parkingCost += (hours - 1) * 3;
        }
        //Discount of $20 for every 2 days
        if (hours > 48 ){
            parkingCost -= (hours/48) * 20;
        }
        return parkingCost;
    }
}
