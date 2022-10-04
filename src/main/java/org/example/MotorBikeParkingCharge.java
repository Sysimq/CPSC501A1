package org.example;

public class MotorBikeParkingCharge extends ParkingCharge{
    /**
     * Calculate MotorBike Slot parking charge
     * @param hours
     * @return
     */
    @Override
    public double getParkingCharge(int hours) {
        //Initial parking charge start from $3 for a minimum of an hour parking
        double parkingCost = 3.0;
        //charge $2 for every added hour after initial hour
        if (hours > 1) {
            parkingCost += (hours - 1) * 2;
        }
        //Discount of $18 if parked for 2 days
        if (hours > 48 ){
            parkingCost -= (hours/48) * 18;
        }
        return parkingCost;
    }
}
