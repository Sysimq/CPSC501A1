package org.example;

import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParkingFullException, InvalidVehicleNumberException{
        ParkingLot parkingLot = ParkingLot.getParkingLot();

        parkingLot.initializeParkingSlots(1, 1);

        Vehicle vehicle = new Vehicle("Mh12", VehicleType.MOTORBIKE);

        ParkingTicket ticket = parkingLot.parkVehicleInParkingLot(vehicle);
        System.out.println(ticket);
        Date c1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,8);
        cal.set(Calendar.DATE, 28);
        cal.set(Calendar.YEAR, 2022);
        cal.set(Calendar.HOUR, 13);
        cal.set(Calendar.MINUTE,45);
        cal.set(Calendar.SECOND,52);
        c1 = cal.getTime();
        ticket.setDate(c1);
        System.out.println(ticket);

        Vehicle vehicle2 = new Vehicle("Mh13", VehicleType.CAR);
        ParkingTicket ticket2 = parkingLot.parkVehicleInParkingLot(vehicle2);
        System.out.println(ticket2);
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2022,8,28,13,52,23);
        ticket2.setDate(cal2.getTime());
        System.out.println(ticket2);


        Vehicle vehicle3 = new Vehicle("Mh15", VehicleType.CAR);
        ParkingTicket ticket3 = parkingLot.parkVehicleInParkingLot(vehicle3);
        System.out.println(ticket3);

        Vehicle vehicle4 = new Vehicle("Mh17", VehicleType.MOTORBIKE);


        ParkingTicket ticket4 = parkingLot.parkVehicleInParkingLot(vehicle4);
        System.out.println(ticket4);

        double cost1 = parkingLot.removeVehicleFromParkingLot(ticket);
        System.out.println(cost1);

        double cost2 = parkingLot.removeVehicleFromParkingLot(ticket2);
        System.out.println(cost2);


    }
}