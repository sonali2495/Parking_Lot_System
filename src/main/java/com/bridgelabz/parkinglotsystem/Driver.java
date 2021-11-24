package com.bridgelabz.parkinglotsystem;

/**
 * Purpose: Driver Should Know About Self Vehicle Slot
 *
 * @author Sonali G
 * @version 1.0
 * @since 24-11-2021
 */
public class Driver {
    public static int slotNo;

    /**
     * Purpose To Retrieve Slot Number
     *
     * @param slotNo Vehicle Slot Number
     */
    public void vehicleSlotIs(int slotNo) {
        this.slotNo = slotNo;
    }

    /**
     * Purpose: To return slot number
     *
     * @return Vehicle slot number
     */
    public int showSlot() {
        return slotNo;
    }
}
