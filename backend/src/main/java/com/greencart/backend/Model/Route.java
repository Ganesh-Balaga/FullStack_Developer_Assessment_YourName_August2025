package com.greencart.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double distanceKm;
    private String trafficLevel; // e.g., "Low","Medium","High"
    private double baseTimeMinutes;

    public Route() {}

    public Route(String name, double distanceKm, String trafficLevel, double baseTimeMinutes) {
        this.name = name;
        this.distanceKm = distanceKm;
        this.trafficLevel = trafficLevel;
        this.baseTimeMinutes = baseTimeMinutes;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public String getTrafficLevel() { return trafficLevel; }
    public void setTrafficLevel(String trafficLevel) { this.trafficLevel = trafficLevel; }

    public double getBaseTimeMinutes() { return baseTimeMinutes; }
    public void setBaseTimeMinutes(double baseTimeMinutes) { this.baseTimeMinutes = baseTimeMinutes; }
}
