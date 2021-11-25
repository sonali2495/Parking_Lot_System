package com.bridgelabz.parkinglotsystem;

/******************************************************************************
 *  @author Sonali G
 *  @version 1.0
 *  @since 24-11-2021
 ******************************************************************************/
public class ParkingSlot {
    private final Object vehicle;
    private final String time;

    public ParkingSlot(Object vehicle, String time) {
        this.vehicle = vehicle;
        this.time = time;
    }

    public Object getVehicle() {
        return vehicle;
    }

    public String getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingSlot parkingSlot1 = (ParkingSlot) o;
        return vehicle.equals(parkingSlot1.vehicle) && time.equals(parkingSlot1.time);
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "vehicle=" + vehicle +
                ", time='" + time + '\'' +
                '}';
    }
}
