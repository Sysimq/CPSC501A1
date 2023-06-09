package org.example;

public abstract class ParkingSlot {
    private int slotNumber;
    private boolean isEmpty;
    private Vehicle parkVehicle;
    private final ParkingSlotType slotType;

    public ParkingSlot(int slotNumber, ParkingSlotType slotType) {
        this.slotNumber = slotNumber;
        this.slotType = slotType;
        isEmpty = true;

    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }
    public Vehicle getParkVehicle() {
        return parkVehicle;
    }

    public void setParkVehicle(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
    }

    public void removeVehicleFromParkingSlot() {
        parkVehicle = null;
        this.isEmpty = true;
    }

    public ParkingSlotType getSlotType() {
        return slotType;
    }

    public void placeVehicleInParkingSlot(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
        this.isEmpty = false;
    }
}