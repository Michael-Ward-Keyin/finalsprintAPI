// src/main/java/com/example/travelapi/controller/GateController.java
package com.example.travelapi.controller;
import com.example.travelapi.model.Gate;
import com.example.travelapi.repo.GateRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/gates") @CrossOrigin
public class GateController {
  private final GateRepository repo;
  public GateController(GateRepository repo){ this.repo = repo; }

  @GetMapping public List<Gate> list(@RequestParam(required=false) Long airportId){
    return airportId == null ? repo.findAll() : repo.findByAirportId(airportId);
  }
  @PostMapping public Gate create(@RequestBody Gate g){ return repo.save(g); }
}
