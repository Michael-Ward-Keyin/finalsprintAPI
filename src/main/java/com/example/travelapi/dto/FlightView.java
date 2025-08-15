package com.example.travelapi.dto;

import com.example.travelapi.entity.Flight;
import java.time.LocalDateTime;

public record FlightView(
    Long id,
    String flightNumber,
    String type,          
    String status,        

    AirlineRef  airline,
    AircraftRef aircraft,
    AirportRef  boardAirport,
    AirportRef  otherAirport,
    GateRef     gate,     

    LocalDateTime scheduledTime,
    LocalDateTime estimatedTime,
    String remarks
) {
  public static FlightView from(Flight f) {
    AirlineRef airline = f.getAirline() == null ? null
        : new AirlineRef(f.getAirline().getId(), f.getAirline().getName(), f.getAirline().getCode());
    AircraftRef aircraft = f.getAircraft() == null ? null
        : new AircraftRef(f.getAircraft().getId(), f.getAircraft().getModel());
    AirportRef board = f.getBoardAirport() == null ? null
        : new AirportRef(f.getBoardAirport().getId(), f.getBoardAirport().getName());
    AirportRef other = f.getOtherAirport() == null ? null
        : new AirportRef(f.getOtherAirport().getId(), f.getOtherAirport().getName());
    GateRef gate = f.getGate() == null ? null
        : new GateRef(f.getGate().getId(), f.getGate().getName());

    return new FlightView(
        f.getId(),
        f.getFlightNumber(),
        f.getType() == null ? null : f.getType().name(),
        f.getStatus() == null ? null : f.getStatus().name(),
        airline, aircraft, board, other, gate,
        f.getScheduledTime(),
        f.getEstimatedTime(),
        f.getRemarks()
    );
  }

  public static record AirlineRef(Long id, String name, String code) {}
  public static record AircraftRef(Long id, String model) {}
  public static record AirportRef(Long id, String name) {}
  public static record GateRef(Long id, String name) {}
}
