package com.greencart.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greencart.backend.Dto.SimulationRequest;
import com.greencart.backend.Dto.SimulationResponse;
import com.greencart.backend.Repository.SimulationResultRepository;
import com.greencart.backend.service.SimulationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${app.frontend.url}")
public class SimulationController {
    @Autowired
    private SimulationService simulationService;
    @Autowired
    private SimulationResultRepository simulationResultRepository;

    @PostMapping("/simulate")
    public ResponseEntity<SimulationResponse> simulate(@Valid @RequestBody SimulationRequest request) {
        SimulationResponse resp = simulationService.runSimulation(request.getNumberOfDrivers(), request.getRouteStartTime(), request.getMaxHoursPerDriver());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/simulations/latest")
    public ResponseEntity<?> latest() {
        return ResponseEntity.ok(simulationResultRepository.findTopByOrderByTimestampDesc());
    }
}
