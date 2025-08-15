package com.example.travelapi.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class Aircraft {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String model;

  @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Passenger> passengers = new ArrayList<>();

  // getters/setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getModel() { return model; }
  public void setModel(String model) { this.model = model; }
  public List<Passenger> getPassengers() { return passengers; }
  public void setPassengers(List<Passenger> passengers) { this.passengers = passengers; }
}
