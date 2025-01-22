package com.Uber.UberApplicaiton.strategies.impl;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.repository.DriverRepository;
import com.Uber.UberApplicaiton.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriver implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        System.out.println("darioshdfndas");
        return driverRepository.findTopTenRatedDrivers(rideRequest.getPickUpLocation());
    }
}
