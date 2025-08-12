package com.greencart.backend.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "simulation_results")
public class SimulationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private double totalProfit;
    private double efficiency;
    private int totalOrders;
    private int onTimeOrders;

    @Column(columnDefinition = "TEXT")
    private String detailsJson;

    public SimulationResult() {}

    public SimulationResult(LocalDateTime timestamp, double totalProfit, double efficiency, int totalOrders, int onTimeOrders, String detailsJson) {
        this.timestamp = timestamp;
        this.totalProfit = totalProfit;
        this.efficiency = efficiency;
        this.totalOrders = totalOrders;
        this.onTimeOrders = onTimeOrders;
        this.detailsJson = detailsJson;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public double getTotalProfit() { return totalProfit; }
    public void setTotalProfit(double totalProfit) { this.totalProfit = totalProfit; }

    public double getEfficiency() { return efficiency; }
    public void setEfficiency(double efficiency) { this.efficiency = efficiency; }

    public int getTotalOrders() { return totalOrders; }
    public void setTotalOrders(int totalOrders) { this.totalOrders = totalOrders; }

    public int getOnTimeOrders() { return onTimeOrders; }
    public void setOnTimeOrders(int onTimeOrders) { this.onTimeOrders = onTimeOrders; }

    public String getDetailsJson() { return detailsJson; }
    public void setDetailsJson(String detailsJson) { this.detailsJson = detailsJson; }
}