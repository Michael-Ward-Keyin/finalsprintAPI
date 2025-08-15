package com.example.travelapi.repo;

import com.example.travelapi.entity.Flight;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

  @Query("""
    select f from Flight f
      join fetch f.airline
      join fetch f.aircraft
      join fetch f.boardAirport ba
      join fetch ba.city
      join fetch f.otherAirport oa
      join fetch oa.city
      left  join fetch f.gate
    where ba.city.id = :cityId
  """)
  List<Flight> findDeparturesByCityId(@Param("cityId") Long cityId);

  @Query("""
    select f from Flight f
      join fetch f.airline
      join fetch f.aircraft
      join fetch f.boardAirport ba
      join fetch ba.city
      join fetch f.otherAirport oa
      join fetch oa.city
      left  join fetch f.gate
    where oa.city.id = :cityId
  """)
  List<Flight> findArrivalsByCityId(@Param("cityId") Long cityId);
}
