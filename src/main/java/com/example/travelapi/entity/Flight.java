package com.example.travelapi.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String flightNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightType type;            // ARRIVAL or DEPARTURE

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;        // SCHEDULED, BOARDING, EN_ROUTE, LANDED, DELAYED, CANCELLED

    @ManyToOne(optional = false)
    private Airline airline;

    @ManyToOne(optional = false)
    private Aircraft aircraft;

    @ManyToOne(optional = false)
    @JoinColumn(name = "board_airport_id")
    private Airport boardAirport;

    @ManyToOne(optional = false)
    @JoinColumn(name = "other_airport_id")
    private Airport otherAirport;

    @ManyToOne
    private Gate gate;

    private LocalDateTime scheduledTime;
    private LocalDateTime estimatedTime;

    private String remarks;

    public Long getId() { return id; }
public void setId(Long id) { this.id = id; }

public String getFlightNumber() { return flightNumber; }
public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

public FlightType getType() { return type; }
public void setType(FlightType type) { this.type = type; }

public FlightStatus getStatus() { return status; }
public void setStatus(FlightStatus status) { this.status = status; }

public Airline getAirline() { return airline; }
public void setAirline(Airline airline) { this.airline = airline; }

public Aircraft getAircraft() { return aircraft; }
public void setAircraft(Aircraft aircraft) { this.aircraft = aircraft; }

public Airport getBoardAirport() { return boardAirport; }
public void setBoardAirport(Airport boardAirport) { this.boardAirport = boardAirport; }

public Airport getOtherAirport() { return otherAirport; }
public void setOtherAirport(Airport otherAirport) { this.otherAirport = otherAirport; }

public Gate getGate() { return gate; }
public void setGate(Gate gate) { this.gate = gate; }

public LocalDateTime getScheduledTime() { return scheduledTime; }
public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }

public LocalDateTime getEstimatedTime() { return estimatedTime; }
public void setEstimatedTime(LocalDateTime estimatedTime) { this.estimatedTime = estimatedTime; }

public String getRemarks() { return remarks; }
public void setRemarks(String remarks) { this.remarks = remarks; }
}
