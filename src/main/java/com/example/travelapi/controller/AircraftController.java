package com.example.travelapi.controller;

import com.example.travelapi.dto.AircraftDto;
import com.example.travelapi.dto.PassengerDto;
import com.example.travelapi.entity.Aircraft;
import com.example.travelapi.entity.Passenger;
import com.example.travelapi.repo.AircraftRepository;
import com.example.travelapi.repo.PassengerRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aircrafts")
public class AircraftController {
  private final AircraftRepository aircraftRepo;
  private final PassengerRepository passengerRepo;

  public AircraftController(AircraftRepository aircraftRepo, PassengerRepository passengerRepo) {
    this.aircraftRepo = aircraftRepo; this.passengerRepo = passengerRepo;
  }

  @GetMapping
  public List<AircraftDto> listAircrafts() {
    return aircraftRepo.findAll().stream().map(a -> new AircraftDto(a.getId(), a.getModel())).toList();
  }

  @GetMapping("/{id}/passengers")
  public List<PassengerDto> listPassengersByAircraft(@PathVariable Long id) {
    List<Passenger> passengers = passengerRepo.findByAircraft_Id(id);
    return passengers.stream()
      .map(p -> new PassengerDto(p.getId(), p.getName(), p.getAircraft().getId()))
      .toList();
  }

  @PostMapping
  public Aircraft create(@Valid @RequestBody Aircraft incoming) {
    return aircraftRepo.save(incoming);
  }
}
