// entity/Airport.java
package com.example.travelapi.entity;

import jakarta.persistence.*;

@Entity
public class Airport {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  private City city;

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public City getCity() { return city; }
  public void setCity(City city) { this.city = city; }
}
