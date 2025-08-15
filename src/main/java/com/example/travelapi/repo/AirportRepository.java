package com.example.travelapi.repo;

import com.example.travelapi.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByCityId(Long cityId);

    @Query("select a from Airport a where a.city.id = :cityId")
    List<Airport> findAllByCityId(@Param("cityId") Long cityId);
}
