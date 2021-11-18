package com.bridgelabz.parkinglotsystem;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParkingLotSystemTest {
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
       ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
       boolean isParked = parkingLotSystem.park(new Object());
       assertTrue(isParked);
    }
}
