package com.example.travelapi.dto;

import java.time.LocalDateTime;

import com.example.travelapi.entity.Flight;

public record FlightDto(
    Long id,
    String flightNumber,
    String type,
    String status,

    Long airlineId,
    String airlineName,
    String airlineCode,

    Long aircraftId,
    String aircraftModel,

    Long boardAirportId,
    String boardAirportName,

    Long otherAirportId,
    String otherAirportName,

    Long gateId,
    String gateName,

    LocalDateTime scheduledTime,
    LocalDateTime estimatedTime,
    String remarks
) {
  public static FlightDto from(Flight f) {
    return new FlightDto(
        f.getId(),
        f.getFlightNumber(),
        f.getType() != null ? f.getType().name() : null,
        f.getStatus() != null ? f.getStatus().name() : null,

        f.getAirline() != null ? f.getAirline().getId() : null,
        f.getAirline() != null ? f.getAirline().getName() : null,
        f.getAirline() != null ? f.getAirline().getCode() : null,

        f.getAircraft() != null ? f.getAircraft().getId() : null,
        f.getAircraft() != null ? f.getAircraft().getModel() : null,

        f.getBoardAirport() != null ? f.getBoardAirport().getId() : null,
        f.getBoardAirport() != null ? f.getBoardAirport().getName() : null,

        f.getOtherAirport() != null ? f.getOtherAirport().getId() : null,
        f.getOtherAirport() != null ? f.getOtherAirport().getName() : null,

        f.getGate() != null ? f.getGate().getId() : null,
        f.getGate() != null ? f.getGate().getName() : null,

        f.getScheduledTime(),
        f.getEstimatedTime(),
        f.getRemarks()
    );
  }
}
