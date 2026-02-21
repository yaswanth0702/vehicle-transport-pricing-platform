package com.example.vehicletransport;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteService {

  public record LineItem(String label, double amount) {}
  public record EstimateResult(double totalCost, List<LineItem> breakdown) {}

  public EstimateResult estimate(String pickupZip, String dropoffZip, int distanceMiles,
                                 String vehicleType, String transportType, boolean operable) {

    double perMile = perMileRate(distanceMiles);
    double base = perMile * distanceMiles;

    List<LineItem> items = new ArrayList<>();
    items.add(new LineItem("Base (" + distanceMiles + " mi @ $" + round2(perMile) + "/mi)", round2(base)));

    double vMult = vehicleMultiplier(vehicleType);
    double beforeV = base;
    base *= vMult;
    if (vMult != 1.0) items.add(new LineItem("Vehicle multiplier (" + vMult + "x)", round2(base - beforeV)));

    if (!operable) {
      double beforeOp = base;
      base *= 1.20; // inoperable fee
      items.add(new LineItem("Inoperable surcharge (1.20x)", round2(base - beforeOp)));
    }

    if ("ENCLOSED".equalsIgnoreCase(transportType)) {
      double beforeT = base;
      base *= 1.35;
      items.add(new LineItem("Enclosed transport (1.35x)", round2(base - beforeT)));
    }

    double fuelSurcharge = base * 0.08;
    items.add(new LineItem("Fuel surcharge (8%)", round2(fuelSurcharge)));

    double total = base + fuelSurcharge;

    double minCharge = 350.0;
    if (total < minCharge) {
      items.add(new LineItem("Minimum charge adjustment", round2(minCharge - total)));
      total = minCharge;
    }

    return new EstimateResult(round2(total), items);
  }

  private double perMileRate(int miles) {
    if (miles <= 100) return 2.20;
    if (miles <= 500) return 1.80;
    if (miles <= 1500) return 1.50;
    return 1.25;
  }

  private double vehicleMultiplier(String vehicleType) {
    String v = vehicleType == null ? "" : vehicleType.toUpperCase();
    return switch (v) {
      case "SUV" -> 1.10;
      case "TRUCK" -> 1.20;
      case "MOTORCYCLE" -> 0.90;
      default -> 1.00; // SEDAN
    };
  }

  private double round2(double x) {
    return Math.round(x * 100.0) / 100.0;
  }
}
