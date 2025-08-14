// src/main/java/com/example/travelapi/controller/AirportController.java
package com.example.travelapi.controller;

import com.example.travelapi.dto.AirportDto;
import com.example.travelapi.entity.Airport;
import com.example.travelapi.repo.AirportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports") // => GET /airports
@CrossOrigin(origins = "http://localhost:5173") // your Vite dev server
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @GetMapping
    public List<AirportDto> all() {
    return airportRepository.findAll().stream()
        .map(a -> new AirportDto(a.getId(), a.getName(),
                a.getCity() != null ? a.getCity().getId() : null))
        .toList();
    }

    @GetMapping("/by-city/{cityId}")
    public List<AirportDto> byCity(@PathVariable Long cityId) {
    return airportRepository.findAllByCityId(cityId).stream()
      .map(a -> new AirportDto(a.getId(), a.getName(), a.getCity().getId()))
      .toList();
}

    @GetMapping("/{id}")
    public Airport byId(@PathVariable Long id) {
        return airportRepository.findById(id).orElseThrow();
    }


}
