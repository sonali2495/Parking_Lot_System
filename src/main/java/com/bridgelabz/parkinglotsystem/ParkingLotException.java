package com.bridgelabz.parkinglotsystem;

/**
 * Purpose To Handle All ParkingLot Exceptions
 *
 * @author Sonali G
 * @since 23/11/2021
 */
public class ParkingLotException extends Exception {
    public ExceptionType exceptionType;

    public ParkingLotException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {PARKING_LOT_IS_FULL, NO_SUCH_VEHICLE, VEHICLE_ALREADY_PARKED}
}
