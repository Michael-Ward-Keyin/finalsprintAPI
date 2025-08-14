package com.example.travelapi.controller;
import com.example.travelapi.model.Airline;
import com.example.travelapi.repo.AirlineRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/airlines") @CrossOrigin
public class AirlineController {
  private final AirlineRepository repo;
  public AirlineController(AirlineRepository repo){ this.repo = repo; }

  @GetMapping public List<Airline> all(){ return repo.findAll(); }
  @PostMapping public Airline create(@RequestBody Airline a){ return repo.save(a); }
}
