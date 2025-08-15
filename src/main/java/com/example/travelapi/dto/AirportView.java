package com.example.travelapi.dto;

import com.example.travelapi.entity.Airport;

public record AirportView(Long id, String name) {
  public static AirportView from(Airport a) { return new AirportView(a.getId(), a.getName()); }
}
