package com.bridgelabz.parkinglotsystem;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    public int capacity = 1;
    ParkingLotOwner owner;
    AirportSecurity airportSecurity;

    @BeforeEach
    public void setUp() throws Exception {
        owner = new ParkingLotOwner();
        parkingLotSystem = new ParkingLotSystem();
        airportSecurity = new AirportSecurity();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        boolean isParked = parkingLotSystem.isVehicleParked("vehicle");
        assertTrue(isParked);

    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        assertThrows(ParkingLotException.class, () -> parkingLotSystem.park("MH04 AK5481", "vehicle", "Red"));
    }

    @Test
    public void givenNullVehicle_WhenUnPark_ShouldReturnException() {
        assertThrows(ParkingLotException.class, () -> parkingLotSystem.unPark("vehicle"));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        boolean isUnParked = parkingLotSystem.unPark("vehicle");
        assertTrue(isUnParked);
    }

    @Test
    public void givenCapacityIs2_ShouldBeAbleToPark2Vehicles() throws ParkingLotException {
        Object vehicle2 = new Object();
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        parkingLotSystem.park("MH04 AK5481", "vehicle2", "Red");
        boolean isVehicleParked1 = parkingLotSystem.isVehicleParked("vehicle");
        boolean isVehicleParked2 = parkingLotSystem.isVehicleParked("vehicle2");
        assertTrue(isVehicleParked1 && isVehicleParked2);
    }

    @Test
    public void givenVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        parkingLotSystem.park("MH04 AK5481", "vehicle2", "Red");
        assertThrows(Exception.class,
                () -> parkingLotSystem.park("MH04 DQ5551", "vehicle3", "Red"));
    }

    @Test
    public void givenWhenParkingLotIsFull_ShouldInformTheSecurity() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        parkingLotSystem.park("MH04 AK5481", "vehicle2", "Red");
        assertThrows(ParkingLotException.class,
                () -> parkingLotSystem.park("MH04 AW8999", "vehicle3", "Red"));
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldReturnTrue() throws ParkingLotException {
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        parkingLotSystem.unPark("vehicle");
        boolean capacityFull = owner.isCapacityFull();
        assertFalse(capacityFull);
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() throws ParkingLotException {
        ParkingLotAttendant parkingLotAttendant = new ParkingLotAttendant();
        parkingLotAttendant.parkVehicle("MH04 AK5481", "vehicle", "Red");
        boolean vehicleParked = parkingLotSystem.isVehicleParked("vehicle");
        assertTrue(vehicleParked);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnSlotNo() throws ParkingLotException {
        parkingLotSystem.setCapacity(3);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Red");
        parkingLotSystem.park("MH04 AK5481", "vehicle2", "Red");
        int slotNum = parkingLotSystem.searchVehicle("vehicle2");
        assertEquals(0, slotNum);
    }

    @Test
    public void givenVehicle_WhenParked_ShouldReturnTime() throws ParkingLotException {
        parkingLotSystem.setCapacity(3);
        parkingLotSystem.park("MH04 AK5481", "vehicle", "Black");
        String parkTime = parkingLotSystem.getParkTime("vehicle");
        assertEquals(parkingLotSystem.getDateTime(), parkTime);
    }

    @Test
    public void givenWhiteVehicle_WhenParked_ShouldInformPoliceDepartment() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park("MH04 0001", "vehicle", "White");
        int slotNo = parkingLotSystem.searchVehicle("vehicle");
        boolean isVehicleAdded = Police.isVehicleAdded(slotNo);
        assertTrue(isVehicleAdded);
    }

    @Test
    public void givenVehicle_WhenBlueToyota_ShouldInformPolice() throws ParkingLotException {
        parkingLotSystem.setCapacity(2);
        parkingLotSystem.park("MH04 AX5668", "Ford", "Black");
        parkingLotSystem.park("MH04 AZ7894", "Toyota", "Blue");
        int slotNo = parkingLotSystem.searchVehicle("Toyota");
        boolean isVehicleAdded = Police.isVehicleAdded(slotNo);
        assertTrue(isVehicleAdded);
    }
}
