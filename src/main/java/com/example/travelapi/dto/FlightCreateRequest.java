package com.example.travelapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record FlightCreateRequest(
    String flightNumber,
    String type,          // "ARRIVAL" or "DEPARTURE"
    String status,        // "SCHEDULED","BOARDING","DELAYED","EN_ROUTE","LANDED","DEPARTED","CANCELLED"
    Long airlineId,
    Long aircraftId,
    Long boardAirportId,
    Long otherAirportId,
    Long gateId,          
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime scheduledTime,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime estimatedTime,
    String remarks        
) {}
