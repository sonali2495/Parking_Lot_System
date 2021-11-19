package com.bridgelabz.parkinglotsystem;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    public void setUp() throws Exception {
        vehicle = new Object();
        parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
       boolean isParked = parkingLotSystem.park(new Object());
       assertTrue(isParked);
    }

    @Test
    public void givenVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLotSystem.park(vehicle);
        boolean isParked = parkingLotSystem.park(new Object());
        assertFalse(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.park(vehicle);
        boolean isUnParked = parkingLotSystem.unPark(vehicle);
        assertTrue(isUnParked);
    }
}
