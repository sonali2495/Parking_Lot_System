package com.bridgelabz.parkinglotsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: To Simulate With Parking Lot Problem.
 *
 * @author Sonali G
 * @version 1.0
 * @since 23-11-2021
 */
public class ParkingLotSystem {
    private static List<ParkingSlot> vehicles;
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;

    public ParkingLotSystem() {
        this.vehicles = new ArrayList();
        this.observers = new ArrayList<>();
        //this.actualCapacity = capacity;
    }

    /**
     * Purpose To Add Observer In List
     *
     * @param observer Given Observer as a Parameter
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose To set the capacity
     *
     * @param capacity given Parameter as Capacity
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * Purpose To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter
     * @return True if Vehicle Parked
     */
    public void park(Object vehicle, String time) throws ParkingLotException {
        if (this.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException("Parking Lot is Full!");
        }
        if (isVehicleParked(vehicle)) throw new ParkingLotException("Vehicle Already Parked!");
        ParkingSlot parkingSlot = new ParkingSlot(vehicle, time);
        this.vehicles.add(parkingSlot);
    }

    /**
     * Purpose: To check given vehicle is parked to unpark it
     *
     * @param vehicle
     * @return True for vehicle unparked
     */
    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (this.vehicles == null) return false;
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle)) {
                this.vehicles.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException("No Such Vehicle found");
    }

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle
     * @return If Vehicle contains vehicle
     * it will return True
     */
    public boolean isVehicleParked(Object vehicle) {
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle))
                return true;
        }
        return false;
    }

    /**
     * Purpose To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return Vehicle Equal to null -> Vehicle is UnParked and return True
     */
    public boolean isVehicleUnParked(Object vehicle) throws ParkingLotException {
        if (vehicle == null) throw new ParkingLotException("No Such Vehicle found");
        if (this.vehicles.contains(vehicle))
            return false;
        return true;
    }

    /**
     * Purpose To Search Slot Number For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Slot Number
     * @throws ParkingLotException If Vehicle Not Found Throwing Exception
     */
    public int searchVehicle(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle))
                return vehicles.indexOf(slot);
        }
        throw new ParkingLotException("No Such Vehicle Found");
    }

    /**
     * Purpose: To Get The Parking Time For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Park Time
     * @throws ParkingLotException If Vehicle Not Found Throw Exception
     */
    public String getParkTime(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : vehicles) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        throw new ParkingLotException("No Such Vehicle Found");
    }
}
