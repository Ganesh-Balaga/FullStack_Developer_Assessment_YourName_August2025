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

import com.greencart.backend.Model.Driver;
import com.greencart.backend.Repository.DriverRepository;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "${app.frontend.url}")
public class DriverController {
    @Autowired
    private DriverRepository repo;

    @GetMapping
    public List<Driver> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Driver create(@RequestBody Driver driver) { return repo.save(driver); }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> update(@PathVariable Long id, @RequestBody Driver body) {
        return repo.findById(id).map(d -> {
            d.setName(body.getName());
            d.setCurrentShiftHours(body.getCurrentShiftHours());
            d.setPastWeekHours(body.getPastWeekHours());
            d.setLastDayHours(body.getLastDayHours());
            repo.save(d);
            return ResponseEntity.ok(d);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}