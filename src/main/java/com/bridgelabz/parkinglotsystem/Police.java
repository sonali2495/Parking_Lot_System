package com.bridgelabz.parkinglotsystem;

import java.util.HashMap;

public class Police {
    public static HashMap<Integer, ParkingSlot> whiteCars;

    public Police() {
        this.whiteCars = new HashMap();
    }

    public static boolean isVehicleAdded(int slotNumber) {
        return whiteCars.containsKey(slotNumber);
    }

    public void listOfWhiteVehicles(int slotNo, ParkingSlot parkingSlot) {
        whiteCars.put(slotNo, parkingSlot);
    }
}
