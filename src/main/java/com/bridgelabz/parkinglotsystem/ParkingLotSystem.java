package com.bridgelabz.parkinglotsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/***********************************************************
 * Purpose: To Simulate With Parking Lot Problem.
 *
 * @author Sonali G
 * @version 1.0
 * @since 23-11-2021
 ************************************************************/

public class ParkingLotSystem {
    private static List<ParkingSlot> parkingLot1;
    private static List<ParkingSlot> parkingLot2;
    private static List<ParkingLotObserver> observers;
    private static int actualCapacity;
    private static Police police;

    public ParkingLotSystem() {
        this.parkingLot1 = new ArrayList();
        this.parkingLot2 = new ArrayList();
        this.observers = new ArrayList<>();
        police = new Police();

    }

    /**
     * Purpose: To Add Observer In List
     *
     * @param observer Given Observer as a Parameter to add into the list
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose To set the capacity for parking slot
     *
     * @param capacity given Parameter as slot Capacity
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * Purpose: To get the time of the vehicle
     *
     * @return Date and Time For Parked Vehicle
     */
    public String getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(formatTime);
        return formattedDate;
    }

    /**
     * Purpose: To Park Given Vehicle
     *
     * @param vehicle given vehicle as parameter
     * @return True if Vehicle Parked
     */
    public void park(String numberPlate, String vehicle, String vehicleColour) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle Already Parked");
        checkCapacity();

        ParkingSlot parkingSlot = new ParkingSlot(numberPlate, vehicle, vehicleColour, getDateTime());
        if (parkingLot1.size() > parkingLot2.size()) {
            this.parkingLot2.add(parkingSlot);
        } else
            this.parkingLot1.add(parkingSlot);

        checkSuspiciousVehicle(parkingSlot, vehicle);
    }

    /**
     * Purpose To add Suspicious Vehicle In List
     *
     * @param parkingSlot given parkingSlot For Check it's Suspicious or not
     * @param vehicle     given vehicle For Check it's Suspicious Vehicle or not
     */
    private void checkSuspiciousVehicle(ParkingSlot parkingSlot, String vehicle) throws ParkingLotException {
        if (parkingSlot.getVehicleColour() == "White" || parkingSlot.getVehicle() == "BMW")
            police.addInSuspiciousVehicles(searchVehicle(vehicle), parkingSlot);
        if (parkingSlot.getVehicleColour() == "Blue" && parkingSlot.getVehicle() == "Toyota") {
            police.addInSuspiciousVehicles(searchVehicle(vehicle), parkingSlot);
        }
    }

    /**
     * Purpose To check Capacity Of Slots
     *
     * @throws ParkingLotException if Capacity is full Throw Exception
     */
    private void checkCapacity() throws ParkingLotException {
        if (this.parkingLot1.size() == this.actualCapacity && this.parkingLot2.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.capacityIsFull();
            }
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.PARKING_LOT_IS_FULL, "Parking Lot is Full");
        }
    }

    /**
     * Purpose To Check a Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle To check if it is parked
     * @return If Vehicle contains vehicle
     * it will return True
     */
    public boolean isVehicleParked(Object vehicle) {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return true;
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return true;
        }
        return false;
    }

    /**
     * Purpose: To check given vehicle is unparked or not
     *
     * @param vehicle
     * @return True for vehicle unparked
     */
    public boolean unPark(String vehicle) throws ParkingLotException {
        if (this.parkingLot1 == null || this.parkingLot2 == null) return false;
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle)) {
                this.parkingLot1.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle)) {
                this.parkingLot2.remove(vehicle);
                for (ParkingLotObserver observer : observers) {
                    observer.capacityIsAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle found");
    }

    /**
     * Purpose To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return Vehicle Equal to null -> Vehicle is UnParked and return True
     */
    public boolean isVehicleUnParked(Object vehicle) throws ParkingLotException {
        if (vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle found");
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return false;
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return false;
        }
        return true;
    }

    /**
     * Purpose To Search Slot Number For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Slot Number
     * @throws ParkingLotException If Vehicle Not Found Throwing Exception
     */
    public int searchVehicle(String vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot1.indexOf(slot);
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return parkingLot2.indexOf(slot);
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }

    /**
     * Purpose: To Get The Parking Time For Parked Vehicle
     *
     * @param vehicle given Vehicle as Parameter
     * @return Vehicle Park Time
     * @throws ParkingLotException If Vehicle Not Found Throw Exception
     */
    public String getParkTime(Object vehicle) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(vehicle))
                return slot.getTime();
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");
    }
}
