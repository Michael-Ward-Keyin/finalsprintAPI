// src/main/java/com/example/travelapi/controller/FlightController.java
package com.example.travelapi.controller;

import com.example.travelapi.dto.FlightDto;
import com.example.travelapi.model.Flight;
import com.example.travelapi.model.FlightType;
import com.example.travelapi.repo.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@CrossOrigin
public class FlightController {

  private final FlightRepository flightRepo;

  public FlightController(FlightRepository flightRepo) {
    this.flightRepo = flightRepo;
  }

  // GET /airports/{airportId}/flights?type=ARRIVAL|DEPARTURE (type optional)
  @GetMapping("/{airportId}/flights")
  public List<FlightDto> listByAirport(
      @PathVariable Long airportId,
      @RequestParam(value = "type", required = false) FlightType type) {

    List<Flight> flights = (type == null)
        ? flightRepo.findByBoardAirport_IdOrOtherAirport_Id(airportId, airportId)
        : flightRepo.findByAirportAndType(airportId, type);

    return flights.stream().map(FlightDto::new).toList();
  }
}
