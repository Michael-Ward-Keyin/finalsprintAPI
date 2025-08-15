package com.example.travelapi.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelapi.entity.Airline;
public interface AirlineRepository extends JpaRepository<Airline, Long> {}