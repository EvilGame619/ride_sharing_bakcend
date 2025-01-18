package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.User;

import java.util.List;

public interface RiderService {

    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideID);

    DriverDTO rateDriver(Long rideID, Integer rating);

    RiderDTO getMyProfile();

    List<RideDTO> getAllMyRides();

    Rider createRider(User user);
}
