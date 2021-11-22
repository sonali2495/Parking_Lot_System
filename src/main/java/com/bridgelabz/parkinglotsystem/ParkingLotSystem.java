package com.bridgelabz.parkinglotsystem;

public class ParkingLotSystem {
    private final int actualCapacity;
    private int currentCapacity;
    private Object vehicle;
    private ParkingLotOwner owner;

    public ParkingLotSystem(int capacity) {
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /**
     * Purpose To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter
     * @return True if Vehicle Parked
     */
    public void park (Object vehicle) throws ParkingLotException {
        if(this.currentCapacity == this.actualCapacity)
            throw new ParkingLotException("Parking Lot is Full!");
        this.currentCapacity++;
        this.vehicle = vehicle;
    }

    /**
     * Purpose: To check given vehicle is parked to unpark it
     * @param vehicle
     * @return True for vehicle unparked
     */
    public void unPark(Object vehicle) throws ParkingLotException{
        if (this.vehicle == null) throw new ParkingLotException("No Such Vehicle found");
        if(this.vehicle.equals(vehicle)){
            this.vehicle = null;
        }
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

    /**
     * Purpose To Introduce Parking Lot Owner
     *
     * @param owner given Parameter as Owner
     */
    public  void registerSystem(ParkingLotOwner owner) {
        this.owner = owner;
    }
}
