package com.example.travelapi.entity;

import jakarta.persistence.*;

@Entity
public class Passenger {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private Aircraft aircraft;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public Aircraft getAircraft() { return aircraft; }
  public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }
}
