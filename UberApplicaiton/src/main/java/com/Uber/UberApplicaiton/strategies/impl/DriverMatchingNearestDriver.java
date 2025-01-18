package com.Uber.UberApplicaiton.strategies.impl;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.repository.DriverRepository;
import com.Uber.UberApplicaiton.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Primary
public class DriverMatchingNearestDriver implements DriverMatchingStrategy {

    private final DriverRepository dr;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return dr.findTenNearestDrivers(rideRequest.getPickUpLocation());
    }
}
