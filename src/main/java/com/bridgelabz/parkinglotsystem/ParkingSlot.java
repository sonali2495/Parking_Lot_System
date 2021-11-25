package com.bridgelabz.parkinglotsystem;

/******************************************************************************
 *  @author Sonali G
 *  @version 1.0
 *  @since 24-11-2021
 ******************************************************************************/
public class ParkingSlot {
    private final String vehicle;
    private final String time;
    private final String vehicleColour;
    private final String numberPlate;

    public ParkingSlot(String numberPlate, String vehicle, String vehicleColour, String time) {
        this.numberPlate = numberPlate;
        this.vehicle = vehicle;
        this.vehicleColour = vehicleColour;
        this.time = time;
    }

    public Object getVehicle() {
        return vehicle;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public String getTime() {
        return time;
    }


    @Override
    public String toString() {
        return "ParkingSlot{" +
                "vehicle=" + vehicle +
                ", time='" + time + '\'' +
                '}';
    }
}
