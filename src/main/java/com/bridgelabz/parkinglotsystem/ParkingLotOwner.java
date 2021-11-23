package com.bridgelabz.parkinglotsystem;

/**
 * Purpose: To Know Parking Lot Owner When The Lot is full & Space Available.
 *
 * @author Sonali G
 * @since 23/11/2021
 */
public class ParkingLotOwner implements ParkingLotObserver {

    private boolean isCapacityFull;

    public boolean isCapacityFull() {
        return this.isCapacityFull;
    }

    @Override
    public void capacityIsFull() {
        isCapacityFull = true;

    }

    @Override
    public void capacityIsAvailable() {
        isCapacityFull = false;
    }
}
