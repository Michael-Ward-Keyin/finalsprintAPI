package com.example.travelapi.dto;

import com.example.travelapi.entity.City;

public record CityView(Long id, String name) {
  public static CityView from(City c) { return new CityView(c.getId(), c.getName()); }
}
