package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ParkingLot {
    private static ParkingLot parkingLot;

    private final List<ParkingSlot> motorBikeSlots;
    private final List<ParkingSlot> carSlots;

    private ParkingLot() {
        this.motorBikeSlots = new ArrayList<>();
        this.carSlots = new ArrayList<>();
    }

    public static ParkingLot getParkingLot() {
        if (parkingLot == null)
            parkingLot = new ParkingLot();
        return parkingLot;
    }

    public boolean initializeParkingSlots(int numberOfMotorBikeParkingSlots, int numberOfCarParkingSlots) {

        for (int i = 1; i <= numberOfMotorBikeParkingSlots; i++) {
            motorBikeSlots.add(new ParkingSlot(i));
        }

        System.out.printf("Created a motorbike parking lot with %s slots %n", numberOfMotorBikeParkingSlots);

        for (int i = 1; i <= numberOfCarParkingSlots; i++) {
            carSlots.add(new ParkingSlot(i));
        }

        System.out.printf("Created a car parking lot with %s slots %n", numberOfCarParkingSlots);
        return true;
    }

    public ParkingTicket park(Vehicle vehicle){
        ParkingSlot nextAvailableSlot;
        if (vehicle.getVehicleType().equals(VehicleType.CAR)) {
            nextAvailableSlot = getNextAvailableCarSlot();
        } else {
            nextAvailableSlot = getNextAvailableMotorBikeSlot();
        }
        nextAvailableSlot.placeVehicleSlot(vehicle);
        System.out.printf("Allocated slot number: %d \n", nextAvailableSlot.getSlotNumber());
        ParkingTicket ticket = new ParkingTicket(nextAvailableSlot.getSlotNumber(), vehicle.getVehicleNumber(),
                vehicle.getVehicleType(), new Date());
        return ticket;
    }

    private ParkingSlot getNextAvailableCarSlot() {
        for (ParkingSlot slot : carSlots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }
        System.out.println("No Empty Car Slot available");
        return null;
    }

    private ParkingSlot getNextAvailableMotorBikeSlot(){
        for (ParkingSlot slot : motorBikeSlots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }
        System.out.println("No Empty MotorBike Slot available");
        return null;
    }

    public double removeVehicle(ParkingTicket ticket) {
        ParkingSlot slot;
        try {
            if (ticket.getVehicleType().equals(VehicleType.CAR)) {
                slot = getCarSlotByVehicleNumber(ticket.getVehicleNumber());
            } else {
                slot = getMotorBikeSlotByVehicleNumber(ticket.getVehicleNumber());
            }
            slot.removeVehicleSlot();
            int hours = getHoursParked(ticket.getDate(), new Date());
            double parkingCost = 0;
            switch (ticket.getVehicleType()) {
                case CAR:
                    parkingCost += 5;
                    if (hours > 1) {
                        parkingCost += (hours - 1) * 3;
                    }
                    if (hours > 48 ){
                        parkingCost -= (hours/48) * 20;
                    }
                    break;
                case MOTORBIKE:
                    parkingCost += 3;
                    if (hours > 1) {
                        parkingCost += (hours - 1) * 2;
                    }
                    if (hours > 48 ){
                        parkingCost -= (hours/48) * 18;
                    }
                    break;
            }
            System.out.println(
                    "Vehicle with registration " + ticket.getVehicleNumber() + " at slot number " + slot.getSlotNumber()
                            + " was parked for " + hours + " hours and the total charge is " + parkingCost);
            return parkingCost;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private int getHoursParked(Date startDate, Date endDate) {
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        return hours;
    }


    private ParkingSlot getCarSlotByVehicleNumber(String vehicleNumber){
        for (ParkingSlot slot : carSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        System.out.println("Car with registration number " + vehicleNumber + " not found");
        return null;
    }

    private ParkingSlot getMotorBikeSlotByVehicleNumber(String vehicleNumber) {
        for (ParkingSlot slot : motorBikeSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }

        }
        System.out.println("MotorBike with registration number " + vehicleNumber + " not found");
        return null;
    }


}
