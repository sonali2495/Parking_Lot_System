package com.bridgelabz.parkinglotsystem;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/******************************************************************************
 *  Purpose To add Suspicious Vehicle in List So Police Can Investigate
 *
 *  @author Sonali G
 *  @version 1.0
 *  @since 24-11-2021
 ******************************************************************************/
public class Police {
    public static HashMap<Integer, ParkingSlot> suspiciousVehicles;

    public Police() {
        this.suspiciousVehicles = new HashMap();
    }

    public static boolean isVehicleAdded(int slotNumber) {
        return suspiciousVehicles.containsKey(slotNumber);
    }

    public static boolean checkNumberPlate(String vehicleNumberPlate) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[0-9]{2}[ ][A-Z]{2}[0-9]{4,}$");
        Matcher matcher = pattern.matcher(vehicleNumberPlate);
        return matcher.matches();
    }

    public void addInSuspiciousVehicles(int slotNo, ParkingSlot parkingSlot) {
        suspiciousVehicles.put(slotNo, parkingSlot);
    }}
