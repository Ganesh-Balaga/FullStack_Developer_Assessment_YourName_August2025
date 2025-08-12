package com.greencart.backend.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.greencart.backend.Model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	
}
