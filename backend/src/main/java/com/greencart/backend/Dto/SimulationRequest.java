package com.greencart.backend.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SimulationRequest {
    @NotNull @Min(1)
    private Integer numberOfDrivers;
    @NotNull
    private String routeStartTime; 
    @NotNull @Min(1)
    private Double maxHoursPerDriver;

    // getters/setters
    public Integer getNumberOfDrivers() { return numberOfDrivers; }
    public void setNumberOfDrivers(Integer numberOfDrivers) { this.numberOfDrivers = numberOfDrivers; }

    public String getRouteStartTime() { return routeStartTime; }
    public void setRouteStartTime(String routeStartTime) { this.routeStartTime = routeStartTime; }

    public Double getMaxHoursPerDriver() { return maxHoursPerDriver; }
    public void setMaxHoursPerDriver(Double maxHoursPerDriver) { this.maxHoursPerDriver = maxHoursPerDriver; }
}
