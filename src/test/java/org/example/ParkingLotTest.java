package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

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
    void parkVehicleNumber2() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(2, 2);
        Vehicle vehicle = new Vehicle("Mh13", VehicleType.CAR);
        ParkingTicket ticket = parkingLot.park(vehicle);
        assertEquals("Mh13",ticket.getVehicleNumber());
    }
    @Test
    void parkSlotNumber1() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(2, 2);
        Vehicle vehicle = new Vehicle("Mh13", VehicleType.CAR);
        ParkingTicket ticket = parkingLot.park(vehicle);
        assertEquals(1,parkingLot.getCarSlotByVehicleNumber("Mh13").getSlotNumber());
    }
    @Test
    void parkSlotNumber2() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(2, 2);
        Vehicle vehicle = new Vehicle("Mh13", VehicleType.MOTORBIKE);
        ParkingTicket ticket = parkingLot.park(vehicle);
        assertEquals(1,parkingLot.getMotorBikeSlotByVehicleNumber("Mh13").getSlotNumber());
    }
    @Test
    void parkSlotNumber3() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(2, 2);
        Vehicle vehicle = new Vehicle("Mh13", VehicleType.MOTORBIKE);
        parkingLot.park(vehicle);
        Vehicle vehicle2 = new Vehicle("Mh14", VehicleType.MOTORBIKE);
        parkingLot.park(vehicle2);
        assertEquals(2,parkingLot.getMotorBikeSlotByVehicleNumber("Mh14").getSlotNumber());
    }

    @Test
    void fullParkingLot() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(1, 1);
        Vehicle vehicle = new Vehicle("Mh12", VehicleType.MOTORBIKE);
        ParkingTicket ticket = parkingLot.park(vehicle);
        Vehicle vehicle2 = new Vehicle("Mh13", VehicleType.CAR);
        ParkingTicket ticket2 = parkingLot.park(vehicle2);
        Vehicle vehicle3 = new Vehicle("Mh13", VehicleType.CAR);
        ParkingTicket ticket3 = parkingLot.park(vehicle3);
    }

    @Test
    void removeMotorBikeParkedLessThan1hour() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(1, 1);
        Vehicle vehicle = new Vehicle("Mh12", VehicleType.MOTORBIKE);
        ParkingTicket ticket = parkingLot.park(vehicle);
        double charge = parkingLot.removeVehicle(ticket);
        assertEquals(3.0,charge);
    }
    @Test
    void removeMotorBikeParkedMoreThan1hour() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(1, 1);
        Vehicle vehicle = new Vehicle("Mh12", VehicleType.MOTORBIKE);
        ParkingTicket ticket = parkingLot.park(vehicle);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR,-5);
        ticket.setDate(cal.getTime());
        double charge = parkingLot.removeVehicle(ticket);
        assertEquals(11.0,charge);
    }
    @Test
    void removeMotorBikeParkedMoreThan48hours() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(1, 1);
        Vehicle vehicle = new Vehicle("Mh12", VehicleType.MOTORBIKE);
        ParkingTicket ticket = parkingLot.park(vehicle);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-3);
        ticket.setDate(cal.getTime());
        double charge = parkingLot.removeVehicle(ticket);
        assertEquals(127.0,charge);
    }
    @Test
    void removeCarParkedLessThan1hour() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(1, 1);
        Vehicle vehicle = new Vehicle("AB1", VehicleType.CAR);
        ParkingTicket ticket = parkingLot.park(vehicle);
        double charge = parkingLot.removeVehicle(ticket);
        assertEquals(5.0,charge);
    }
    @Test
    void removeCarParkedMoreThan1hour() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(1, 1);
        Vehicle vehicle = new Vehicle("AB1", VehicleType.CAR);
        ParkingTicket ticket = parkingLot.park(vehicle);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR,-5);
        ticket.setDate(cal.getTime());
        double charge = parkingLot.removeVehicle(ticket);
        assertEquals(17.0,charge);
    }
    @Test
    void removeCarParkedMoreThan48hours() {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.initializeParkingSlots(1, 1);
        Vehicle vehicle = new Vehicle("AB1", VehicleType.CAR);
        ParkingTicket ticket = parkingLot.park(vehicle);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-3);
        ticket.setDate(cal.getTime());
        double charge = parkingLot.removeVehicle(ticket);
        assertEquals(198.0,charge);
    }


}