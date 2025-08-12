package com.greencart.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greencart.backend.Model.SimulationResult;

public interface SimulationResultRepository extends JpaRepository<SimulationResult, Long> {
    SimulationResult	 findTopByOrderByTimestampDesc();
}
