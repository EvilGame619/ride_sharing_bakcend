package com.Uber.UberApplicaiton.strategies.impl;

import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.services.DistanceService;
import com.Uber.UberApplicaiton.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RideFareCalculator implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_CHARGES;
    }
}
