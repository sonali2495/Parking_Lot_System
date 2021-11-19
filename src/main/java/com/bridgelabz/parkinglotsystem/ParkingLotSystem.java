package com.bridgelabz.parkinglotsystem;

public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem() {
    }

    /**
     * Purpose To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter
     * @return True For Vehicle Parked
     */
    public boolean park(Object vehicle) {
        if(this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }

    /**
     * Purpose: To check given vehicle is parked to unpark it
     * @param vehicle
     * @return True for vehicle unparked
     */
    public boolean unPark(Object vehicle) {
        if (this.vehicle == null) return false;
        if(this.vehicle.equals(vehicle)){
            this.vehicle = null;
            return true;
        }
        return false;
    }
}
