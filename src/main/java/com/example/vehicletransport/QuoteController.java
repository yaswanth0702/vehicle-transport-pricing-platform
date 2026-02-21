package com.example.vehicletransport;

import jakarta.validation.constraints.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuoteController {

  private final QuoteService service;
  private final QuoteRepo repo;

  public QuoteController(QuoteService service, QuoteRepo repo) {
    this.service = service;
    this.repo = repo;
  }

  public static class EstimateRequest {
    @Pattern(regexp = "\\d{5}") public String pickupZip;
    @Pattern(regexp = "\\d{5}") public String dropoffZip;
    @Min(1) @Max(5000) public int distanceMiles;

    @NotBlank public String vehicleType;   // SEDAN, SUV, TRUCK, MOTORCYCLE
    @NotBlank public String transportType; // OPEN, ENCLOSED
    public boolean operable;
  }

  @PostMapping("/estimate")
  public ResponseEntity<?> estimate(@RequestBody EstimateRequest req) {
    var result = service.estimate(
        req.pickupZip, req.dropoffZip, req.distanceMiles,
        req.vehicleType, req.transportType, req.operable
    );

    return ResponseEntity.ok(Map.of(
        "totalCost", result.totalCost(),
        "breakdown", result.breakdown()
    ));
  }

  @PostMapping("/save")
  public ResponseEntity<?> save(@RequestBody EstimateRequest req) {
    var result = service.estimate(
        req.pickupZip, req.dropoffZip, req.distanceMiles,
        req.vehicleType, req.transportType, req.operable
    );

    Quote q = new Quote(
        req.pickupZip, req.dropoffZip, req.distanceMiles,
        req.vehicleType.toUpperCase(), req.transportType.toUpperCase(),
        req.operable, result.totalCost()
    );

    Quote saved = repo.save(q);
    return ResponseEntity.ok(Map.of("quoteId", saved.getId(), "totalCost", saved.getTotalCost()));
  }

  @GetMapping("/quotes")
  public List<Quote> quotes() {
    return repo.findAll();
  }
}
