package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;

import java.util.List;

public interface DriverService {

    RideDTO cancelRide(Long rideID);

    RideDTO startRide(Long rideID);

    RideDTO endRide(Long rideID);

    RideDTO acceptRide(Long rideID);

    RiderDTO rateRider(Long rideID, Integer rating);

    DriverDTO getMyProfile();

    List<RideDTO> getAllMyRides();
}
