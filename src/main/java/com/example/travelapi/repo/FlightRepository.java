// src/main/java/com/example/travelapi/repository/FlightRepository.java
package com.example.travelapi.repo;

import com.example.travelapi.model.Flight;
import com.example.travelapi.model.FlightType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

  // both directions
  List<Flight> findByBoardAirport_IdOrOtherAirport_Id(Long boardAirportId, Long otherAirportId);

  // filtered by type
  @Query("""
      select f from Flight f
      where (f.boardAirport.id = :airportId or f.otherAirport.id = :airportId)
        and f.type = :type
      """)
  List<Flight> findByAirportAndType(Long airportId, FlightType type);
}
