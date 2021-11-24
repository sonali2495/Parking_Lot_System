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
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        assertTrue(isParked);

    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park(vehicle);
        assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        assertTrue(isUnParked);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.park(vehicle2);
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        assertTrue(isParked1 && isParked2);
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.registerParkingLotObserver(owner);
        Object vehicle2 = new Object();
        parkingLotSystem.park(vehicle);
        assertThrows(Exception.class, () -> parkingLotSystem.park(vehicle2));
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Object vehicle2 = new Object();
        parkingLotSystem.park(vehicle);
        assertThrows(ParkingLotException.class, () -> parkingLotSystem.park(vehicle2));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park(vehicle);
        parkingLotSystem.unPark(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        assertFalse(capacityFull);
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle(vehicle);
        boolean vehicleParked = parkingLotSystem.isVehicleParked(vehicle);
        assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        parkingLotSystem.setCapacity(3);
        parkingLotSystem.park(vehicle);
        Object vehicle2 = new Object();
        parkingLotSystem.park(vehicle2);
        parkingLotSystem.searchVehicle(vehicle2);
        int slotNum = parkingLotSystem.searchVehicle(vehicle2);
        assertEquals(1, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        parkingLotSystem.park(vehicle);
        String parkTime = parkingLotSystem.getParkTime(vehicle);
        assertEquals(parkingLotSystem.getTime(), parkTime);
    }
}
