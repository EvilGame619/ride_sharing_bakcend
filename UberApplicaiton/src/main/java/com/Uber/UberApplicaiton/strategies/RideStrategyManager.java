package com.Uber.UberApplicaiton.strategies;

import com.Uber.UberApplicaiton.strategies.impl.DriverMatchingHighestRatedDriver;
import com.Uber.UberApplicaiton.strategies.impl.DriverMatchingNearestDriver;
import com.Uber.UberApplicaiton.strategies.impl.RideFareCalculator;
import com.Uber.UberApplicaiton.strategies.impl.RideFareSurgePricingCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriver driverMatchingHighestRatedDriver;
    private final DriverMatchingNearestDriver driverMatchingNearestDriver;
    private final RideFareCalculator rideFareCalculator;
    private final RideFareSurgePricingCalculator rideFareSurgePricingCalculator;

    public DriverMatchingStrategy driverMatchingStrategy(double rating){
        //driver matching is done with the rider rating, if the rider rating is above 4.5 then he will be matched with top rated drivers;
        if(rating >= 4.5) return driverMatchingHighestRatedDriver;
        else return driverMatchingNearestDriver;
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        //fare is calculated on the basis of peak hour, if the time is between 6pm to 9pm then prices will surge
        LocalTime startTime = LocalTime.of(18,0);
        LocalTime endTime  = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();
        if(currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) return rideFareSurgePricingCalculator;
        else return rideFareCalculator;
    }
}
