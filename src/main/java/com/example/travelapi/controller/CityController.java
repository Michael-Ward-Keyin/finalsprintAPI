// controller/CityController.java
package com.example.travelapi.controller;

import com.example.travelapi.dto.AirportDto;
import com.example.travelapi.dto.CityDto;
import com.example.travelapi.entity.Airport;
import com.example.travelapi.entity.City;
import com.example.travelapi.repo.AirportRepository;
import com.example.travelapi.repo.CityRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CityController {
  private final CityRepository cityRepo;
  private final AirportRepository airportRepo;

  public CityController(CityRepository cityRepo, AirportRepository airportRepo) {
    this.cityRepo = cityRepo; this.airportRepo = airportRepo;
  }

  @GetMapping
  public List<CityDto> listCities() {
    return cityRepo.findAll().stream().map(c -> new CityDto(c.getId(), c.getName())).toList();
  }

  @GetMapping("/{id}/airports")
  public List<AirportDto> listAirportsByCity(@PathVariable Long id) {
    return airportRepo.findAllByCityId(id).stream()
      .map(a -> new AirportDto(a.getId(), a.getName(), a.getCity().getId()))
      .toList();
}
  
}
