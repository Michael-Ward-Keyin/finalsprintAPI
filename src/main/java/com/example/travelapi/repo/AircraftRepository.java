package com.example.travelapi.repo;
import com.example.travelapi.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {}
