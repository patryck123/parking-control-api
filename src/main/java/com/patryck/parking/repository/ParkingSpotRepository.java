package com.patryck.parking.repository;
import com.patryck.parking.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findBySpotNumber(String spotNumber);
    Optional<ParkingSpot> findByLicensePlate(String licensePlate);
    List<ParkingSpot> findByStatus(SpotStatus status);
    List<ParkingSpot> findByType(SpotType type);
    boolean existsBySpotNumber(String spotNumber);
    boolean existsByLicensePlate(String licensePlate);
}
