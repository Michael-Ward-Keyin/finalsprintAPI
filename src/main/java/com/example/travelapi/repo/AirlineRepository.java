package com.example.travelapi.repo;
import com.example.travelapi.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AirlineRepository extends JpaRepository<Airline, Long> {}