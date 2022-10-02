package org.example;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class ParkingLot {
    private static ParkingLot parkingLot;

    private final List<ParkingSlot> motorBikeSlots;
    private final List<ParkingSlot> carSlots;

    public List<ParkingSlot> getMotorBikeSlots() {
        return motorBikeSlots;
    }

    public List<ParkingSlot> getCarSlots() {
        return carSlots;
    }

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

    public ParkingTicket park(Vehicle vehicle) throws ParkingFullException {
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

    private ParkingSlot getNextAvailableCarSlot() throws ParkingFullException {
        for (ParkingSlot slot : carSlots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }
        throw new ParkingFullException("No Empty Car Slot available");
    }

    public ParkingSlot getNextAvailableMotorBikeSlot() throws ParkingFullException{
        for (ParkingSlot slot : motorBikeSlots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }throw new ParkingFullException("No Empty MotorBike Slot available");
    }

    public double removeVehicle(ParkingTicket ticket) throws InvalidVehicleNumberException{
        ParkingSlot slot;
        double parkingCost;
        try {
            if (ticket.getVehicleType().equals(VehicleType.CAR)) {
                slot = getCarSlotByVehicleNumber(ticket.getVehicleNumber());
            } else {
                slot = getMotorBikeSlotByVehicleNumber(ticket.getVehicleNumber());
            }
            slot.removeVehicleSlot();
            int hours = getHoursParked(ticket.getDate(), new Date());
            parkingCost = ticket.getChargeStrategy().getParkingCharge(hours);
            System.out.println(
                    "Vehicle with registration " + ticket.getVehicleNumber() + " at slot number " + slot.getSlotNumber()
                            + " was parked for " + hours + " hours and the total charge is " + parkingCost);
        } catch (InvalidVehicleNumberException invalidVehicleNumber) {
            System.out.println(invalidVehicleNumber.getMessage());
            throw invalidVehicleNumber;
        }
        return parkingCost;
    }


    public int getHoursParked(Date startDate, Date endDate) {
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        return hours;
    }


    public ParkingSlot getCarSlotByVehicleNumber(String vehicleNumber)throws InvalidVehicleNumberException{
        for (ParkingSlot slot : carSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new InvalidVehicleNumberException("Car with registration number " + vehicleNumber + " not found");
    }

    public ParkingSlot getMotorBikeSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumberException {
        for (ParkingSlot slot : motorBikeSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }

        }
        throw new InvalidVehicleNumberException("MotorBike with registration number " + vehicleNumber + " not found");
    }


}
