package com.Uber.UberApplicaiton.strategies;

import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_CHARGES = 10;

    double calculateFare(RideRequest rideRequest);

}
