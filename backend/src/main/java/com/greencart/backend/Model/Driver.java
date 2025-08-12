package com.greencart.backend.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double currentShiftHours;
    private double pastWeekHours;
    private double lastDayHours;

    public Driver() {}

    public Driver(String name, double currentShiftHours, double pastWeekHours, double lastDayHours) {
        this.name = name;
        this.currentShiftHours = currentShiftHours;
        this.pastWeekHours = pastWeekHours;
        this.lastDayHours = lastDayHours;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getCurrentShiftHours() { return currentShiftHours; }
    public void setCurrentShiftHours(double currentShiftHours) { this.currentShiftHours = currentShiftHours; }

    public double getPastWeekHours() { return pastWeekHours; }
    public void setPastWeekHours(double pastWeekHours) { this.pastWeekHours = pastWeekHours; }

    public double getLastDayHours() { return lastDayHours; }
    public void setLastDayHours(double lastDayHours) { this.lastDayHours = lastDayHours; }
}
