package com.example.vehicletransport;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "quotes")
public class Quote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String pickupZip;

  @Column(nullable = false)
  private String dropoffZip;

  @Column(nullable = false)
  private int distanceMiles;

  @Column(nullable = false)
  private String vehicleType; // SEDAN, SUV, TRUCK, MOTORCYCLE

  @Column(nullable = false)
  private String transportType; // OPEN, ENCLOSED

  @Column(nullable = false)
  private boolean operable;

  @Column(nullable = false)
  private double totalCost;

  @Column(nullable = false)
  private Instant createdAt = Instant.now();

  protected Quote() {}

  public Quote(String pickupZip, String dropoffZip, int distanceMiles, String vehicleType,
               String transportType, boolean operable, double totalCost) {
    this.pickupZip = pickupZip;
    this.dropoffZip = dropoffZip;
    this.distanceMiles = distanceMiles;
    this.vehicleType = vehicleType;
    this.transportType = transportType;
    this.operable = operable;
    this.totalCost = totalCost;
  }

  public Long getId() { return id; }
  public String getPickupZip() { return pickupZip; }
  public String getDropoffZip() { return dropoffZip; }
  public int getDistanceMiles() { return distanceMiles; }
  public String getVehicleType() { return vehicleType; }
  public String getTransportType() { return transportType; }
  public boolean isOperable() { return operable; }
  public double getTotalCost() { return totalCost; }
  public Instant getCreatedAt() { return createdAt; }
}
