package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

    @Test
    void initializeParkingSlots() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(10, 10);
        assertTrue(parkingLot.initializeParkingSlots(10,10));
    }
    @Test
    void parkingLotCarSlotNumber() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(10, 10);
        assertEquals(10,parkingLot.getCarSlots().size());
    }
    @Test
    void parkingLotMotorBikeSlotNumber() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(10, 10);
        assertEquals(10,parkingLot.getMotorBikeSlots().size());
    }

    @Test
    void parkVehicleNumber() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(2, 2);
        Vehicle vehicle = new Vehicle("Mh12", VehicleType.MOTORBIKE);
        ParkingTicket ticket = parkingLot.park(vehicle);
        assertEquals("Mh12",ticket.getVehicleNumber());
    }

    @Test
    void removeVehicle() {
    }

    @Test
    void getParkingLot() {
    }
}