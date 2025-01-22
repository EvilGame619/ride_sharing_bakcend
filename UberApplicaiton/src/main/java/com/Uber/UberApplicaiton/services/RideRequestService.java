package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestID);

    void update(Long id, RideStatus rideStatus);
}
