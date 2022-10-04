package org.example;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class ParkingLot {
    public static ParkingLot parkingLot;
    //Parking Lot contains MotorBike Parking Slots and Car Parking Slots
    private final List<MotorBikeSlot> motorBikeSlots;
    private final List<CarSlot> carSlots;

    public List<MotorBikeSlot> getMotorBikeSlots() {
        return motorBikeSlots;
    }
    public List<CarSlot> getCarSlots() {
        return carSlots;
    }
    //Create MotorBike Parking Slots and Car Parking Slots
    public ParkingLot() {
        this.motorBikeSlots = new ArrayList<>();
        this.carSlots = new ArrayList<>();
    }
    //If ParkingLot is set null get instance of ParkingLot
    public static ParkingLot getParkingLot() {
        if (parkingLot == null)
            parkingLot = new ParkingLot();
        return parkingLot;
    }

    /**
     * Initialize ParkingSlots
     * @param numberOfMotorBikeParkingSlots
     * @param numberOfCarParkingSlots
     * @return true if Parkinglot initialization is complete
     */
    public boolean initializeParkingSlots(int numberOfMotorBikeParkingSlots, int numberOfCarParkingSlots) {
        //Initialize MotorBike Parking Slots
       initializeMotorBikeSlots(numberOfMotorBikeParkingSlots,motorBikeSlots);
       // Initialize Car Parking Slots
       initializeCarSlots(numberOfCarParkingSlots,carSlots);
       return true;
    }

    //Create list of MotorBike Parking Slots
    public void initializeMotorBikeSlots(int numOfSlots, List<MotorBikeSlot> motorBikeSlots){
        for (int i = 1; i <= numOfSlots; i++) {
            motorBikeSlots.add(new MotorBikeSlot(i));
        }
        System.out.printf("Created a motorbike parking lot with %s slots %n", numOfSlots);
    }

    //Create list of Car Parking Slots
    public void initializeCarSlots(int numOfSlots, List<CarSlot> carSlots){
        for (int i = 1; i <= numOfSlots; i++) {
            carSlots.add(new CarSlot(i));
        }
        System.out.printf("Created a car parking lot with %s slots %n", numOfSlots);
    }

    /**
     * Park Vehicle
     * @param vehicle
     * @return Parking Ticket
     * @throws ParkingFullException
     */
    public ParkingTicket park(Vehicle vehicle) throws ParkingFullException {
        ParkingSlot nextAvailableSlot;
        if (vehicle.getVehicleType().equals(VehicleType.CAR)) {
            // get next Available Empty Parking Slot
            nextAvailableSlot = getNextAvailableCarSlot();
        } else {
            nextAvailableSlot = getNextAvailableMotorBikeSlot();
        }
        // Place Vehicle in Next Available Empty Slot
        nextAvailableSlot.placeVehicleSlot(vehicle);
        System.out.printf("Allocated slot number: %d \n", nextAvailableSlot.getSlotNumber());
        ParkingTicket ticket = new ParkingTicket(nextAvailableSlot.getSlotNumber(), vehicle.getVehicleNumber(),
                vehicle.getVehicleType(), nextAvailableSlot.getSlotType(), new Date());
        return ticket;
    }
    private CarSlot getNextAvailableCarSlot() throws ParkingFullException {
        for (CarSlot slot : carSlots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }
        throw new ParkingFullException("No Empty Car Slot available");
    }

    public MotorBikeSlot getNextAvailableMotorBikeSlot() throws ParkingFullException{
        for (MotorBikeSlot slot : motorBikeSlots) {
            if (slot.isEmpty()) {
                return slot;
            }
        }throw new ParkingFullException("No Empty MotorBike Slot available");
    }

    /**
     * Remove Vehicle from Parking Slot
     * @param ticket
     * @return Parking Cost
     * @throws InvalidVehicleNumberException
     */
    public double removeVehicle(ParkingTicket ticket) throws InvalidVehicleNumberException{
        ParkingSlot slot;
        double parkingCost;
        try {
            if (ticket.getSlotType().equals(ParkingSlotType.CARSLOT)) {
                slot = getCarSlotByVehicleNumber(ticket.getVehicleNumber());
            } else {
                slot = getMotorBikeSlotByVehicleNumber(ticket.getVehicleNumber());
            }
            //remove vehicle from parking slot
            slot.removeVehicleSlot();
            //get parked hour
            int hours = getHoursParked(ticket.getDate(), new Date());
            //calculate parking cost depending on parking cost strategy
            parkingCost = ticket.getChargeStrategy().getParkingCharge(hours);
            System.out.println(
                    "Vehicle with registration " + ticket.getVehicleNumber() + " at slot number " + slot.getSlotNumber()
                            + " was parked for " + hours + " hours and the total charge is " + parkingCost);
        }catch (InvalidVehicleNumberException invalidVehicleNumber) {
            System.out.println(invalidVehicleNumber.getMessage());
            throw invalidVehicleNumber;
            }
        return parkingCost;
    }

    /**
     * Get vehicle parked hour
     * @param startDate
     * @param endDate
     * @return
     */
    public int getHoursParked(Date startDate, Date endDate) {
        long secs = (endDate.getTime() - startDate.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        return hours;
    }

    /**
     * get Car Slot by Vehicle license plate number
     * @param vehicleNumber
     * @return  Car Slot
     * @throws InvalidVehicleNumberException
     */
    public CarSlot getCarSlotByVehicleNumber(String vehicleNumber)throws InvalidVehicleNumberException{
        for (CarSlot slot : carSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new InvalidVehicleNumberException("Car with registration number " + vehicleNumber + " not found");
    }

    /**
     * Get MotorBike Slot by Vehicle license plate number
     * @param vehicleNumber
     * @return MotorBike Slot
     * @throws InvalidVehicleNumberException
     */
    public MotorBikeSlot getMotorBikeSlotByVehicleNumber(String vehicleNumber) throws InvalidVehicleNumberException {
        for (MotorBikeSlot slot : motorBikeSlots) {
            Vehicle vehicle = slot.getParkVehicle();
            if (vehicle != null && vehicle.getVehicleNumber().equals(vehicleNumber)) {
                return slot;
            }
        }
        throw new InvalidVehicleNumberException("MotorBike with registration number " + vehicleNumber + " not found");
    }
}
