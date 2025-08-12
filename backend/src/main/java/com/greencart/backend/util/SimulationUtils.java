package com.greencart.backend.util;


public class SimulationUtils {
    public static double computeFuelCost(double distanceKm, String trafficLevel) {
        double base = 5.0 * distanceKm;
        if ("High".equalsIgnoreCase(trafficLevel)) {
            base += 2.0 * distanceKm; // surcharge
        }
        return base;
    }

    public static double applyFatigue(double baseTime, boolean fatigued) {
        if (fatigued) {
            return baseTime * 1.3; // 30% increase in time
        } else {
            return baseTime;
        }
    }

    public static boolean isOnTime(double deliveryTimeMinutes, double baseTimeMinutes) {
        return deliveryTimeMinutes <= (baseTimeMinutes + 10.0);
    }
}

