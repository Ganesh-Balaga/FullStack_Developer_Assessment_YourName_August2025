package com.greencart.backend.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valueRs;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "route_id")
    private Route route;

    private LocalDateTime deliveryTimestamp; 

    public DeliveryOrder() {}

    public DeliveryOrder(double valueRs, Route route, LocalDateTime deliveryTimestamp) {
        this.valueRs = valueRs;
        this.route = route;
        this.deliveryTimestamp = deliveryTimestamp;
    }

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getValueRs() { return valueRs; }
    public void setValueRs(double valueRs) { this.valueRs = valueRs; }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }

    public LocalDateTime getDeliveryTimestamp() { return deliveryTimestamp; }
    public void setDeliveryTimestamp(LocalDateTime deliveryTimestamp) { this.deliveryTimestamp = deliveryTimestamp; }
}
