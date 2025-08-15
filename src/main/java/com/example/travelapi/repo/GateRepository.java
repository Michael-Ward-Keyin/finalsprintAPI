package com.example.travelapi.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelapi.entity.Gate;

import java.util.List;
public interface GateRepository extends JpaRepository<Gate, Long> {
  List<Gate> findByAirportId(Long airportId);
}