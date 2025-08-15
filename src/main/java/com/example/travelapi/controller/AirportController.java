package com.example.travelapi.controller;

import com.example.travelapi.dto.AirportDto;
import com.example.travelapi.entity.Airport;
import com.example.travelapi.entity.City;
import com.example.travelapi.repo.AirportRepository;
import com.example.travelapi.repo.CityRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
@CrossOrigin(origins = "http://localhost:5173")
public class AirportController {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    public AirportController(AirportRepository airportRepository, CityRepository cityRepository) {
        this.airportRepository = airportRepository;
        this.cityRepository = cityRepository;
    }


    private static AirportDto toDto(Airport a) {
        Long cityId = (a.getCity() != null) ? a.getCity().getId() : null;
        return new AirportDto(a.getId(), a.getName(), cityId);
    }

    public record CreateAirportRequest(
            @NotBlank String name,
            @NotNull Long cityId
    ) {}


    // GET /airports
    @GetMapping
    public List<AirportDto> all() {
        return airportRepository.findAll().stream().map(AirportController::toDto).toList();
    }

    // GET /airports/by-city/{cityId}
    @GetMapping("/by-city/{cityId}")
    public List<AirportDto> byCity(@PathVariable Long cityId) {
        return airportRepository.findByCityId(cityId).stream().map(AirportController::toDto).toList();
    }

    // GET /airports/{id}
    @GetMapping("/{id}")
    public AirportDto byId(@PathVariable Long id) {
        Airport a = airportRepository.findById(id).orElseThrow();
        return toDto(a);
    }

    /* ---------- CREATE ---------- */

    // POST /airports   { "name": "JFK International", "cityId": 1 }
    @PostMapping
    public AirportDto create(@RequestBody @Valid CreateAirportRequest req) {
        City city = cityRepository.findById(req.cityId())
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + req.cityId()));

        Airport a = new Airport();
        a.setName(req.name());
        a.setCity(city);

        return toDto(airportRepository.save(a));
    }
}
