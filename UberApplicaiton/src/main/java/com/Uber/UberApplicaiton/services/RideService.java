package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideID);

    void matchWithDrivers(RideRequestDTO rideRequestDTO);

    Ride createNewRide(RideRequestDTO rideRequestDTO, Driver driver);

    Ride updateRideStatus(Long rideID, RideStatus rideStatus);

    Page<Ride> getAllRidesOfDriver(Long driverID, PageRequest pageRequest);

    Page<Ride> getAllRidesOfRider(Long riderID, PageRequest pageRequest);


}
