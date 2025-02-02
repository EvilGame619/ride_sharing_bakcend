package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDTO cancelRide(Long rideID);

    RideDTO startRide(Long rideID, String otp);

    RideDTO endRide(Long rideID);

    RideDTO acceptRide(Long rideRequestID);

    RiderDTO rateRider(Long rideID, Integer rating);

    DriverDTO getMyProfile();

    Page<RideDTO> getAllMyRides(PageRequest pageRequest);

    Integer getDriverRidesList(Long driverID);

    Driver getCurrentDriver();

    void updateDriverAvailability(Driver driver, Boolean available);

    Driver getDriverByID(Long driverID);
}
