package com.greencart.backend.Dto;

public class OrderSimulationResult {
    private Long orderId;
    private Long driverId;
    private double deliveryTimeMinutes;
    private boolean onTime;
    private double penalty;
    private double bonus;
    private double fuelCost;
    private double profit;

    public OrderSimulationResult() {}

    // getters/setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }

    public double getDeliveryTimeMinutes() { return deliveryTimeMinutes; }
    public void setDeliveryTimeMinutes(double deliveryTimeMinutes) { this.deliveryTimeMinutes = deliveryTimeMinutes; }

    public boolean isOnTime() { return onTime; }
    public void setOnTime(boolean onTime) { this.onTime = onTime; }

    public double getPenalty() { return penalty; }
    public void setPenalty(double penalty) { this.penalty = penalty; }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    public double getFuelCost() { return fuelCost; }
    public void setFuelCost(double fuelCost) { this.fuelCost = fuelCost; }

    public double getProfit() { return profit; }
    public void setProfit(double profit) { this.profit = profit; }
}
