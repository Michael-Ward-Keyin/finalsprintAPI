// repo/CityRepository.java
package com.example.travelapi.repo;
import com.example.travelapi.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CityRepository extends JpaRepository<City, Long> {}
