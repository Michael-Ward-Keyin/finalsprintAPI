package com.example.travelapi.controller;

import com.example.travelapi.dto.FlightCreateRequest;
import com.example.travelapi.dto.FlightView;
import com.example.travelapi.entity.*;
import com.example.travelapi.error.NotFoundException;
import com.example.travelapi.entity.FlightStatus;
import com.example.travelapi.entity.FlightType;
import com.example.travelapi.repo.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/flights")
@CrossOrigin
public class FlightController {

    private final FlightRepository flights;
    private final AirlineRepository airlines;
    private final AircraftRepository aircraft;
    private final AirportRepository airports;
    private final GateRepository gates;

    public FlightController(FlightRepository flights,
                            AirlineRepository airlines,
                            AircraftRepository aircraft,
                            AirportRepository airports,
                            GateRepository gates) {
        this.flights = flights;
        this.airlines = airlines;
        this.aircraft = aircraft;
        this.airports = airports;
        this.gates = gates;
    }

    @GetMapping
    public List<FlightView> list() {
        return flights.findAll().stream().map(FlightView::from).toList();
    }

    @GetMapping("/{id}")
    public FlightView getOne(@PathVariable Long id) {
        Flight f = flights.findById(id)
                .orElseThrow(() -> new NotFoundException("Flight not found: " + id));
        return FlightView.from(f);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FlightView> create(@Valid @RequestBody FlightCreateRequest req) {
        FlightType typeEnum;
        try {
            typeEnum = FlightType.valueOf(req.type().toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid type: " + req.type() + " (allowed: " + Arrays.toString(FlightType.values()) + ")"
            );
        }

        FlightStatus statusEnum;
        try {
            statusEnum = FlightStatus.valueOf(req.status().toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Invalid status: " + req.status() + " (allowed: " + Arrays.toString(FlightStatus.values()) + ")"
            );
        }

        Airline airline = airlines.findById(req.airlineId())
                .orElseThrow(() -> new NotFoundException("Airline not found: " + req.airlineId()));
        Aircraft ac = aircraft.findById(req.aircraftId())
                .orElseThrow(() -> new NotFoundException("Aircraft not found: " + req.aircraftId()));
        Airport board = airports.findById(req.boardAirportId())
                .orElseThrow(() -> new NotFoundException("Board airport not found: " + req.boardAirportId()));
        Airport other = airports.findById(req.otherAirportId())
                .orElseThrow(() -> new NotFoundException("Other airport not found: " + req.otherAirportId()));

        Gate gate = null;
        if (req.gateId() != null) {
            gate = gates.findById(req.gateId())
                    .orElseThrow(() -> new NotFoundException("Gate not found: " + req.gateId()));
        }

        Flight f = new Flight();
        f.setFlightNumber(req.flightNumber());
        f.setType(typeEnum);
        f.setStatus(statusEnum);
        f.setAirline(airline);
        f.setAircraft(ac);
        f.setBoardAirport(board);
        f.setOtherAirport(other);
        f.setGate(gate);
        f.setScheduledTime(req.scheduledTime());
        f.setEstimatedTime(req.estimatedTime());
        f.setRemarks(req.remarks());

        Flight saved = flights.save(f);
        return ResponseEntity.status(HttpStatus.CREATED).body(FlightView.from(saved));
    }
}
