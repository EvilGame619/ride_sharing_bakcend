package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.Rider;

public interface RatingService {

    DriverDTO rateDriver(Ride ride, Double rating);
    RiderDTO rateRider(Ride ride, Double rating);
    void createRating(Ride ride);
}
