package com.example.travelapi.repo;
import com.example.travelapi.model.Gate;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface GateRepository extends JpaRepository<Gate, Long> {
  List<Gate> findByAirportId(Long airportId);
}