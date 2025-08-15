package com.example.travelapi.controller;

import com.example.travelapi.dto.AirlineDto;
import com.example.travelapi.entity.Airline;
import com.example.travelapi.repo.AirlineRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/airlines")
@CrossOrigin(origins = "http://localhost:5173")
public class AirlineController {
    private final AirlineRepository repo;
    public AirlineController(AirlineRepository repo) { this.repo = repo; }

    @GetMapping
    public List<AirlineDto> all() {
        return repo.findAll().stream()
            .map(a -> new AirlineDto(a.getId(), a.getCode(), a.getName()))
            .toList();
    }

    @PostMapping
    public AirlineDto create(@RequestBody AirlineDto dto) {
        Airline a = new Airline();
        a.setCode(dto.code());
        a.setName(dto.name());
        a = repo.save(a);
        return new AirlineDto(a.getId(), a.getCode(), a.getName());
    }
}
