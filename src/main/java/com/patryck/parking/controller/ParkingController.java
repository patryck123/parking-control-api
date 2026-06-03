package com.patryck.parking.controller;
import com.patryck.parking.entity.*;
import com.patryck.parking.repository.ParkingSpotRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
@RestController @RequestMapping("/api/parking") @RequiredArgsConstructor
@Tag(name = "Estacionamento", description = "Controle de vagas de estacionamento")
public class ParkingController {
    private final ParkingSpotRepository repo;
    @PostMapping("/spots") @Operation(summary = "Cadastrar vaga")
    public ResponseEntity<ParkingSpot> create(@RequestBody ParkingSpot spot) {
        if (repo.existsBySpotNumber(spot.getSpotNumber())) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(repo.save(spot));
    }
    @GetMapping("/spots") public ResponseEntity<List<ParkingSpot>> findAll() { return ResponseEntity.ok(repo.findAll()); }
    @GetMapping("/spots/available") @Operation(summary = "Vagas disponíveis")
    public ResponseEntity<List<ParkingSpot>> available() { return ResponseEntity.ok(repo.findByStatus(SpotStatus.AVAILABLE)); }
    @PostMapping("/spots/{id}/checkin") @Operation(summary = "Registrar entrada de veículo")
    public ResponseEntity<ParkingSpot> checkin(@PathVariable Long id, @RequestBody ParkingSpot vehicleInfo) {
        return repo.findById(id).map(spot -> {
            if (spot.getStatus() != SpotStatus.AVAILABLE) return ResponseEntity.status(HttpStatus.CONFLICT).<ParkingSpot>build();
            spot.setStatus(SpotStatus.OCCUPIED); spot.setLicensePlate(vehicleInfo.getLicensePlate());
            spot.setVehicleOwner(vehicleInfo.getVehicleOwner()); spot.setVehicleModel(vehicleInfo.getVehicleModel());
            spot.setVehicleColor(vehicleInfo.getVehicleColor()); spot.setEntryTime(LocalDateTime.now());
            return ResponseEntity.ok(repo.save(spot));
        }).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/spots/{id}/checkout") @Operation(summary = "Registrar saída de veículo")
    public ResponseEntity<ParkingSpot> checkout(@PathVariable Long id) {
        return repo.findById(id).map(spot -> {
            spot.setStatus(SpotStatus.AVAILABLE); spot.setLicensePlate(null); spot.setVehicleOwner(null);
            spot.setVehicleModel(null); spot.setVehicleColor(null); spot.setEntryTime(null);
            return ResponseEntity.ok(repo.save(spot));
        }).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/spots/search") @Operation(summary = "Buscar vaga por placa")
    public ResponseEntity<ParkingSpot> findByPlate(@RequestParam String plate) {
        return repo.findByLicensePlate(plate).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
