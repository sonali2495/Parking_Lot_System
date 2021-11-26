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
    private static ParkingSlot parkingSlot;

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
     * @param vehicle     given vehicle as parameter
     * @param vehicleType to define Vehicle'Size
     */
    public void park(String numberPlate, String vehicle, String vehicleColour,
                     ParkingSlot.VehicleType vehicleType) throws ParkingLotException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotException
                    (ParkingLotException.ExceptionType.VEHICLE_ALREADY_PARKED, "Vehicle Already Parked");
        checkCapacity();

        parkingSlot = new ParkingSlot(numberPlate, vehicle, vehicleColour, vehicleType, getDateTime());
        if (police.checkNumberPlate(numberPlate)) {
            if (parkingLot1.size() > parkingLot2.size())
                this.parkingLot2.add(parkingSlot);
            else
                this.parkingLot1.add(parkingSlot);
        }
    }

    /**
     * Purpose Check Suspicious Vehicle and Inform Police
     *
     * @param carName given Vehicle as Parameter for Checking Suspicious Vehicle or Not
     * @throws ParkingLotException If Vehicle Not Found Throwing Exception
     */
    public void checkSuspiciousVehicle(String carName) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicle().equals(carName)) {
                police.addInSuspiciousVehicles(searchVehicle(carName), parkingSlot);
            }
            if (slot.getVehicle().equals(carName)) {
                police.addInSuspiciousVehicles(searchVehicle(carName), parkingSlot);
            }
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicle().equals(carName)) {
                police.addInSuspiciousVehicles(searchVehicle(carName), parkingSlot);
            }
            if (slot.getVehicle().equals(carName)) {
                police.addInSuspiciousVehicles(searchVehicle(carName), parkingSlot);
            }
        }
    }
    /**
     * Purpose To Check Suspicious Vehicle By Colour and Inform Police
     *
     * @param vehicleColour for Checking Suspicious Vehicle By Colour
     * @throws ParkingLotException If Vehicle Colour Not Matches Throw Exception
     */
    public void checkSuspiciousVehicleByColour(String vehicleColour) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicleColour().equals(vehicleColour)) {
                police.addInSuspiciousVehicles(searchVehicleByColour(vehicleColour), parkingSlot);
            }
            if (slot.getVehicleColour().equals(vehicleColour)) {
                police.addInSuspiciousVehicles(searchVehicleByColour(vehicleColour), parkingSlot);
            }
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicleColour().equals(vehicleColour)) {
                police.addInSuspiciousVehicles(searchVehicleByColour(vehicleColour), parkingSlot);
            }
            if (slot.getVehicleColour().equals(vehicleColour)) {
                police.addInSuspiciousVehicles(searchVehicleByColour(vehicleColour), parkingSlot);
            }
        }
    }

    /**
     * Purpose To Check Suspicious Vehicle By VehicleName and Vehicle Colour
     *
     * @param vehicleName   for Check Suspicious Vehicle
     * @param vehicleColour for Check Suspicious Vehicle By Colour
     * @throws ParkingLotException If Vehicle Not Found Throw Exception
     */
    public void checkSuspiciousVehicle(String vehicleName, String vehicleColour) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicleColour().equals(vehicleColour) && slot.getVehicle().equals(vehicleName)) {
                police.addInSuspiciousVehicles(searchVehicle(vehicleName), parkingSlot);
            }
            if (slot.getVehicleColour().equals(vehicleColour) && slot.getVehicle().equals(vehicleName)) {
                police.addInSuspiciousVehicles(searchVehicle(vehicleName), parkingSlot);
            }
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicleColour().equals(vehicleColour) && slot.getVehicle().equals(vehicleName)) {
                police.addInSuspiciousVehicles(searchVehicle(vehicleName), parkingSlot);
            }
            if (slot.getVehicleColour().equals(vehicleColour) && slot.getVehicle().equals(vehicleName)) {
                police.addInSuspiciousVehicles(searchVehicle(vehicleName), parkingSlot);
            }
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
    public boolean isVehicleParked(String vehicle) {
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

    private int searchVehicleByColour(String vehicleColour) throws ParkingLotException {
        for (ParkingSlot slot : parkingLot1) {
            if (slot.getVehicleColour().equals(vehicleColour))
                return parkingLot1.indexOf(slot);
        }
        for (ParkingSlot slot : parkingLot2) {
            if (slot.getVehicleColour().equals(vehicleColour))
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
