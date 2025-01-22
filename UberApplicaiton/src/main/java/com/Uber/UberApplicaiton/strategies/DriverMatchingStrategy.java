package com.Uber.UberApplicaiton.strategies;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
