package com.example.travelapi.controller;

import com.example.travelapi.dto.AirportView;
import com.example.travelapi.dto.CityView;
import com.example.travelapi.dto.FlightView;
import com.example.travelapi.entity.City;
import com.example.travelapi.error.NotFoundException;
import com.example.travelapi.repo.AirportRepository;
import com.example.travelapi.repo.CityRepository;
import com.example.travelapi.repo.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/cities")
@CrossOrigin
public class CityController {
  private final CityRepository cities;
  private final AirportRepository airports;
  private final FlightRepository flights;

  public CityController(CityRepository cities, AirportRepository airports, FlightRepository flights) {
    this.cities = cities;
    this.airports = airports;
    this.flights = flights;
  }

  @GetMapping
  public List<CityView> list() {
    return cities.findAll().stream().map(CityView::from).toList();
  }

  @GetMapping("/{id}")
  public CityView getOne(@PathVariable Long id) {
    City c = cities.findById(id).orElseThrow(() -> new NotFoundException("City not found: " + id));
    return CityView.from(c);
  }

  @GetMapping("/{id}/airports")
  public List<AirportView> airports(@PathVariable Long id) {
    // 404 if the city doesn't exist:
    cities.findById(id).orElseThrow(() -> new NotFoundException("City not found: " + id));
    return airports.findByCityId(id).stream().map(AirportView::from).toList();
  }

  /**
   * Flights scoped to a city:
   *   /cities/{id}/flights?flow=departures|arrivals|all   (default: all)
   * "departures" = boardAirport.city == {id}
   * "arrivals"   = otherAirport.city == {id}
   */
  @GetMapping("/{id}/flights")
  public List<FlightView> flightsByCity(@PathVariable Long id, @RequestParam(defaultValue = "all") String flow) {
    cities.findById(id).orElseThrow(() -> new NotFoundException("City not found: " + id));

    List<FlightView> out = new ArrayList<>();
    switch (flow.toLowerCase()) {
      case "departures" -> out = flights.findDeparturesByCityId(id).stream().map(FlightView::from).toList();
      case "arrivals"   -> out = flights.findArrivalsByCityId(id).stream().map(FlightView::from).toList();
      case "all" -> {
        out.addAll(flights.findDeparturesByCityId(id).stream().map(FlightView::from).toList());
        out.addAll(flights.findArrivalsByCityId(id).stream().map(FlightView::from).toList());
      }
      default -> throw new IllegalArgumentException("Invalid flow: " + flow + " (use departures|arrivals|all)");
    }
    return out.stream()
      .sorted(Comparator.comparing(FlightView::scheduledTime, Comparator.nullsLast(Comparator.naturalOrder())))
      .toList();
  }
}
