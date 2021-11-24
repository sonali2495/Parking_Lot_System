package com.bridgelabz.parkinglotsystem;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Object vehicle = null;
    public int capacity = 1;
    ParkingLotOwner owner;
    AirportSecurity airportSecurity;

    @BeforeEach
    public void setUp() throws Exception {
        vehicle = new Object();
        owner = new ParkingLotOwner();
        parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.setCapacity(1);
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle, "18:00");
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            assertTrue(isParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        parkingLotSystem.park(vehicle, "18:00");
        assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle, "18:00"));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
      // vehicle = null;
        assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle, "18:00");
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        assertTrue(isUnParked);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        Object vehicle2 = new Object();
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle, "18:00");
            parkingLotSystem.park(vehicle2, "18:00");
            boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
            boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
            assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        parkingLotSystem.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        try {
            parkingLotSystem.park(vehicle, "18:00");
            parkingLotSystem.park(vehicle2, "18:00");
            boolean capacityFull = owner.isCapacityFull();
            assertTrue(capacityFull);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() {
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Object vehicle2 = new Object();
        try {
            parkingLotSystem.park(vehicle, "18:00");
            parkingLotSystem.park(vehicle2, "18:00");
            boolean capacityFull = airportSecurity.isCapacityFull();
            assertTrue(capacityFull);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() {
        parkingLotSystem.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        try {
            parkingLotSystem.park(vehicle, "18:00");
            parkingLotSystem.park(vehicle2, "18:00");
            parkingLotSystem.unPark(vehicle);
            boolean capacityFull = owner.isCapacityFull();
            assertFalse(capacityFull);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle, "18:00");
        boolean vehicleParked = parkingLotSystem.isVehicleParked(vehicle);
        assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        parkingLotSystem.setCapacity(3);
        parkingLotSystem.park(vehicle, "18:00");
        Object vehicle2 = new Object();
        parkingLotSystem.park(vehicle2, "18:00");
        parkingLotSystem.searchVehicle(vehicle2);
        int slotNum = parkingLotSystem.searchVehicle(vehicle2);
        assertEquals(1, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        parkingLotSystem.park(vehicle, "18:00");
        String parkTime = parkingLotSystem.getParkTime(vehicle);
        assertEquals("18:00", parkTime);
    }
}
