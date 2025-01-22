package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Driver;

import java.util.List;

public interface DriverService {

    RideDTO cancelRide(Long rideID);

    RideDTO startRide(Long rideID, String otp);

    RideDTO endRide(Long rideID);

    RideDTO acceptRide(Long rideRequestID);

    RiderDTO rateRider(Long rideID, Integer rating);

    DriverDTO getMyProfile();

    List<RideDTO> getAllMyRides();

    Driver getCurrentDriver();
}
