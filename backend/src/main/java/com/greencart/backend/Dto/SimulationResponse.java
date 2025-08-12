package com.greencart.backend.Dto;

import java.util.List;

public class SimulationResponse {
    private double totalProfit;
    private double efficiency;
    private int totalOrders;
    private int onTimeOrders;
    private List<OrderSimulationResult> orderResults;

    public SimulationResponse() {}

    // getters/setters
    public double getTotalProfit() { return totalProfit; }
    public void setTotalProfit(double totalProfit) { this.totalProfit = totalProfit; }

    public double getEfficiency() { return efficiency; }
    public void setEfficiency(double efficiency) { this.efficiency = efficiency; }

    public int getTotalOrders() { return totalOrders; }
    public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }

    public int getOnTimeOrders() { return onTimeOrders; }
    public void setOnTimeOrders(int onTimeOrders) { this.onTimeOrders = onTimeOrders; }

    public List<OrderSimulationResult> getOrderResults() { return orderResults; }
    public void setOrderResults(List<OrderSimulationResult> orderResults) { this.orderResults = orderResults; }
}
