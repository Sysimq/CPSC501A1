package org.example;

public class ParkingSlot {
    private int slotNumber;
    private boolean isEmpty;
    private Vehicle parkVehicle;

    public ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
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

    public void removeVehicleSlot() {
        parkVehicle = null;
        this.isEmpty = true;
    }

    public void placeVehicleSlot(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
        this.isEmpty = false;
    }
}