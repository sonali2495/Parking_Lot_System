package com.bridgelabz.parkinglotsystem;

/**
 * Purpose To Know Airport Security When The Lot is full & When Space Available.
 *
 * @author Sonali G
 * @since 23/11/2021
 */
public class AirportSecurity implements ParkingLotObserver{

    private boolean isCapacityFull;


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
