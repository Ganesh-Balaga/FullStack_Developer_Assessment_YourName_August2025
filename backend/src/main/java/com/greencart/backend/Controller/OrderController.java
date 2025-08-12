package com.greencart.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencart.backend.Model.DeliveryOrder;
import com.greencart.backend.Repository.OrderRepository;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "${app.frontend.url}")
public class OrderController {
    @Autowired
    private OrderRepository repo;

    @GetMapping
    public List<DeliveryOrder> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryOrder> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DeliveryOrder create(@RequestBody DeliveryOrder order) { return repo.save(order); }

    @PutMapping("/{id}")
    public ResponseEntity<DeliveryOrder> update(@PathVariable Long id, @RequestBody DeliveryOrder body) {
        return repo.findById(id).map(o -> {
            o.setValueRs(body.getValueRs());
            o.setRoute(body.getRoute());
            o.setDeliveryTimestamp(body.getDeliveryTimestamp());
            repo.save(o);
            return ResponseEntity.ok(o);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
