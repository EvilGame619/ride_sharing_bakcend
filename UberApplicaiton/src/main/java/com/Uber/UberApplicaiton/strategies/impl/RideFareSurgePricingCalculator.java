package com.Uber.UberApplicaiton.strategies.impl;

import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.services.DistanceService;
import com.Uber.UberApplicaiton.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingCalculator implements RideFareCalculationStrategy {

    private final DistanceService distanceService;
    private static final double SURGE_FARE = 1.34896989866896989868968;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_CHARGES * SURGE_FARE;
    }
}
