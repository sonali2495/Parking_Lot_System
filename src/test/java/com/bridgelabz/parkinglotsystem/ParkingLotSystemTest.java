package com.bridgelabz.parkinglotsystem;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    private final int capacity = 2;

    @BeforeEach
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem(capacity);
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
           boolean isParked =  parkingLotSystem.isVehicleParked(vehicle);
            assertTrue(isParked);
        } catch (ParkingLotException e) {
        }
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnException() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
             assertEquals("Parking Lot is Full!", e.getMessage());
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnException() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean isUnParked =  parkingLotSystem.isVehicleUnParked(vehicle);
            assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            assertEquals("No Such Vehicle found", e.getMessage());
        }
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotOwner owner = new ParkingLotOwner();
        parkingLotSystem.registerSystem(owner);
        Object vehicle1 = new Object();
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle1);
            parkingLotSystem.park(new Object());
        } catch (ParkingLotException e) {
            assertEquals("Parking Lot is Full!", e.getMessage());
        }
        boolean capacityFull = owner.isCapacityFull();
        assertTrue(capacityFull);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() {
        Object vehicle2 = new Object();
        parkingLotSystem.setCapacity(2);
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.park(vehicle2);
            boolean isParked1 =  parkingLotSystem.isVehicleParked(vehicle);
            boolean isParked2 =  parkingLotSystem.isVehicleParked(vehicle2);
            assertTrue(isParked1 && isParked2);
        } catch (ParkingLotException e) { }
    }
}
