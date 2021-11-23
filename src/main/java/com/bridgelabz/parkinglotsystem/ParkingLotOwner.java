package com.bridgelabz.parkinglotsystem;

public class ParkingLotOwner {
    private boolean isFullCapacity;

    public void capacityFull() {
        isFullCapacity = true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
