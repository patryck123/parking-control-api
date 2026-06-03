package com.patryck.parking.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "parking_spots") @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ParkingSpot {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String spotNumber;
    @Enumerated(EnumType.STRING) @Builder.Default private SpotStatus status = SpotStatus.AVAILABLE;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private SpotType type;
    private String licensePlate;
    private String vehicleOwner;
    private String vehicleModel;
    private String vehicleColor;
    private java.time.LocalDateTime entryTime;
}
