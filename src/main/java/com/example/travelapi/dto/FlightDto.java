package com.example.travelapi.dto;

import com.example.travelapi.model.*;
import com.example.travelapi.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDto {
  public Long id;
  public String flightNumber;
  public FlightType type;     // ARRIVAL | DEPARTURE
  public FlightStatus status; // SCHEDULED | EN_ROUTE | ...
  public AirlineDto airline;  // { code, name }
  public AircraftDto aircraft; // { id, model }
  public AirportDto boardAirport; // { id, name }
  public AirportDto otherAirport; // { id, name }
  public GateDto gate;        // { id, name }
  public LocalDateTime scheduledTime;
  public LocalDateTime estimatedTime;
  public String remarks;

  public FlightDto(Flight f) {
    this.id = f.getId();
    this.flightNumber = f.getFlightNumber();
    this.type = f.getType();
    this.status = f.getStatus();
    if (f.getAirline() != null) this.airline = new AirlineDto(f.getAirline());
    if (f.getAircraft() != null) this.aircraft = new AircraftDto(f.getAircraft());
    if (f.getBoardAirport() != null) this.boardAirport = new AirportDto(f.getBoardAirport());
    if (f.getOtherAirport() != null) this.otherAirport = new AirportDto(f.getOtherAirport());
    if (f.getGate() != null) this.gate = new GateDto(f.getGate());
    this.scheduledTime = f.getScheduledTime();
    this.estimatedTime = f.getEstimatedTime();
    this.remarks = f.getRemarks();
  }

  public static class AirlineDto {
    public String code;
    public String name;
    public AirlineDto(Airline a) { this.code = a.getCode(); this.name = a.getName(); }
  }
  public static class AircraftDto {
    public Long id;
    public String model;
    public AircraftDto(Aircraft a) { this.id = a.getId(); this.model = a.getModel(); }
  }
  public static class AirportDto {
    public Long id;
    public String name;
    public AirportDto(Airport a) { this.id = a.getId(); this.name = a.getName(); }
  }
  public static class GateDto {
    public Long id;
    public String name;
    public GateDto(Gate g) { this.id = g.getId(); this.name = g.getName(); }
  }
}
