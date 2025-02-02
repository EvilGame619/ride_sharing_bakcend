package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RideService {

    Ride getRideById(Long rideID);



    Ride createNewRide(RideRequest rideRequest, Driver driver);

    void updateRideStatus(Long rideID, RideStatus rideStatus);

    Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    List<Ride> getListOfRidesOfDriver(Long driverID);

    Ride findRideByID(Long rideID);
}
