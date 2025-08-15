package com.example.travelapi.repo;
import com.example.travelapi.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
  List<Passenger> findByAircraft_Id(Long aircraftId);
}
