package com.greencart.backend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greencart.backend.Dto.OrderSimulationResult;
import com.greencart.backend.Dto.SimulationResponse;
import com.greencart.backend.Model.DeliveryOrder;
import com.greencart.backend.Model.Driver;
import com.greencart.backend.Model.Route;
import com.greencart.backend.Model.SimulationResult;
import com.greencart.backend.Repository.DriverRepository;
import com.greencart.backend.Repository.OrderRepository;
import com.greencart.backend.Repository.RouteRepository;
import com.greencart.backend.Repository.SimulationResultRepository;
import com.greencart.backend.util.SimulationUtils;

@Service
public class SimulationService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SimulationResultRepository simulationResultRepository;
    @Autowired
    private RouteRepository routeRepository;

    public SimulationResponse runSimulation(int numberOfDrivers, String routeStartTime, double maxHoursPerDriver) {
        if (numberOfDrivers <= 0) throw new IllegalArgumentException("numberOfDrivers must be > 0");
        if (maxHoursPerDriver <= 0) throw new IllegalArgumentException("maxHoursPerDriver must be > 0");

        List<Driver> allDrivers = driverRepository.findAll();
        if (allDrivers.isEmpty()) throw new IllegalStateException("No drivers found in DB");

        // choose drivers with least pastWeekHours (deterministic)
        allDrivers.sort(Comparator.comparingDouble(Driver::getPastWeekHours));
        int n = Math.min(numberOfDrivers, allDrivers.size());
        List<Driver> selectedDrivers = new ArrayList<>(allDrivers.subList(0, n));

        List<DeliveryOrder> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            SimulationResponse empty = new SimulationResponse();
            empty.setTotalProfit(0);
            empty.setEfficiency(0);
            empty.setTotalOrders(0);
            empty.setOnTimeOrders(0);
            empty.setOrderResults(Collections.emptyList());
            return empty;
        }

        double totalProfit = 0.0;
        int onTimeCount = 0;
        int total = orders.size();
        List<OrderSimulationResult> orderResults = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++) {
            DeliveryOrder order = orders.get(i);
            Route route = order.getRoute();
            if (route == null) {
                // skip orders without route
                continue;
            }

            Driver assigned = selectedDrivers.get(i % n);

            double baseTime = route.getBaseTimeMinutes();
            boolean fatigued = (assigned.getLastDayHours() > 8.0) || (assigned.getCurrentShiftHours() > 8.0);
            double deliveryTime = SimulationUtils.applyFatigue(baseTime, fatigued);

            boolean onTime = SimulationUtils.isOnTime(deliveryTime, baseTime);

            double penalty = onTime ? 0.0 : 50.0; // ₹50 penalty if late
            double bonus = (order.getValueRs() > 1000.0 && onTime) ? (order.getValueRs() * 0.10) : 0.0;
            double fuelCost = SimulationUtils.computeFuelCost(route.getDistanceKm(), route.getTrafficLevel());

            double profit = order.getValueRs() + bonus - penalty - fuelCost;

            OrderSimulationResult r = new OrderSimulationResult();
            r.setOrderId(order.getId());
            r.setDriverId(assigned.getId());
            r.setDeliveryTimeMinutes(deliveryTime);
            r.setOnTime(onTime);
            r.setPenalty(penalty);
            r.setBonus(bonus);
            r.setFuelCost(fuelCost);
            r.setProfit(profit);

            orderResults.add(r);
            totalProfit += profit;
            if (onTime) onTimeCount++;
        }

        double efficiency = total == 0 ? 0.0 : ((double) onTimeCount / (double) total) * 100.0;

        SimulationResponse response = new SimulationResponse();
        response.setTotalProfit(round(totalProfit));
        response.setEfficiency(round(efficiency));
        response.setTotalOrders(total);
        response.setOnTimeOrders(onTimeCount);
        response.setOrderResults(orderResults);

        // persist simulation result (store orderResults as JSON)
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(orderResults);
            SimulationResult sr = new SimulationResult(LocalDateTime.now(), totalProfit, efficiency, total, onTimeCount, json);
            simulationResultRepository.save(sr);
        } catch (Exception ex) {
            // log & continue
            ex.printStackTrace();
        }

        return response;
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
