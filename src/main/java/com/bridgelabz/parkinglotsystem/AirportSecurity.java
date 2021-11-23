package com.bridgelabz.parkinglotsystem;

public class AirportSecurity implements ParkingLotObserver{

    private boolean isCapacityFull;

    /**
     * Purpose To Know Airport Security When The Lot is full & When Space Available.
     *
     * @author Sonali
     * @since 23/11/2021
     */
    @Override
    public void capacityIsFull() {
        isCapacityFull = true;
    }

    @Override
    public void capacityIsAvailable() {
        isCapacityFull = false;
    }

    public boolean isCapacityFull() {
        return this.isCapacityFull;
    }
}
