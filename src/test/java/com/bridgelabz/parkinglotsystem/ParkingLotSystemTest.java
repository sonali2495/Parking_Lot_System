package com.bridgelabz.parkinglotsystem;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    private final int capacity = 1;

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
            e.printStackTrace();
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
        try {
            parkingLotSystem.park(vehicle);
        } catch (ParkingLotException e) {
            assertEquals("Parking Lot is Full!", e.getMessage());
            e.printStackTrace();
        }
    }
}
