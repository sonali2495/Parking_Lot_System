package com.bridgelabz.parkinglotsystem;

/**
 * Purpose To Park Vehicles Through Attendant.
 *
 * @author Sonali G
 * @version 1.0
 * @since 24-11-2021
 */
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    public void parkVehicle(String vehicleNumber, String vehicle, String vehicleColour) throws ParkingLotException {
        parkingLotSystem.park(vehicleNumber, vehicle, vehicleColour, VehicleType.LARGE);

    }
}
