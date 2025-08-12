package com.greencart.backend;

import org.junit.jupiter.api.Test;

import com.greencart.backend.util.SimulationUtils;

import static org.junit.jupiter.api.Assertions.*;

public class SimulationUtilsTest {

    @Test
    public void testFuelCostLowTraffic() {
        double c = SimulationUtils.computeFuelCost(10.0, "Low");
        assertEquals(50.0, c, 0.001); // 5 * 10
    }

    @Test
    public void testFuelCostHighTraffic() {
        double c = SimulationUtils.computeFuelCost(10.0, "High");
        assertEquals(70.0, c, 0.001); // 5*10 + 2*10
    }

    @Test
    public void testApplyFatigue() {
        double t = SimulationUtils.applyFatigue(60.0, true);
        assertEquals(78.0, t, 0.001); // 60 * 1.3
    }

    @Test
    public void testIsOnTimeExactBoundary() {
        assertTrue(SimulationUtils.isOnTime(70.0, 60.0)); // base 60 + 10 = 70 => on time
    }

    @Test
    public void testIsLate() {
        assertFalse(SimulationUtils.isOnTime(71.0, 60.0)); // 71 > 70 => late
    }
}

