package com.Uber.UberApplicaiton.strategies;

import com.Uber.UberApplicaiton.dto.RideRequestDTO;

public interface RideFareCalculationStrategy {

    double calculateFare(RideRequestDTO rideRequestDTO);

}
