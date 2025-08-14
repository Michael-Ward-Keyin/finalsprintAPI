// entity/City.java
package com.example.travelapi.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class City {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Airport> airports = new ArrayList<>();

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public List<Airport> getAirports() { return airports; }
  public void setAirports(List<Airport> airports) { this.airports = airports; }
}
