package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RiderService {

    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideID);

    DriverDTO rateDriver(Long rideID, Integer rating);

    RiderDTO getMyProfile();

    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Rider createRider(User user);

    Rider getCurrentRider();
}
