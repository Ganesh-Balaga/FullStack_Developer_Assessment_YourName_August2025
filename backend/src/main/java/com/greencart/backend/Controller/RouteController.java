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

import com.greencart.backend.Model.Route;
import com.greencart.backend.Repository.RouteRepository;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "${app.frontend.url}")
public class RouteController {
    @Autowired
    private RouteRepository repo;

    @GetMapping
    public List<Route> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Route> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Route create(@RequestBody Route route) { return repo.save(route); }

    @PutMapping("/{id}")
    public ResponseEntity<Route> update(@PathVariable Long id, @RequestBody Route body) {
        return repo.findById(id).map(r -> {
            r.setName(body.getName());
            r.setDistanceKm(body.getDistanceKm());
            r.setTrafficLevel(body.getTrafficLevel());
            r.setBaseTimeMinutes(body.getBaseTimeMinutes());
            repo.save(r);
            return ResponseEntity.ok(r);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}