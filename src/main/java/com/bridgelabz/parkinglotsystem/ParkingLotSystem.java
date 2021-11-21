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
    public void park (Object vehicle) throws ParkingLotException {
        if(this.vehicle != null)
            throw new ParkingLotException("Parking Lot is Full!");
        this.vehicle = vehicle;
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

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle
     * @return If Vehicle Equal to Given Vehicle
     * it will return True or False
     */
    public boolean isVehicleParked(Object vehicle) {
        if(this.vehicle.equals(vehicle))
            return true;
        return false;
    }

    /**
     * Purpose To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle
     * @return Vehicle Equal to null -> Vehicle is UnParked and return True
     */
    public boolean isVehicleUnParked(Object vehicle) {
            return this.vehicle == null;
    }
}
