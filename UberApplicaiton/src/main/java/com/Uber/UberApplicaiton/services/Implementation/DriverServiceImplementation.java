package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.services.DriverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImplementation implements DriverService {
    @Override
    public RideDTO cancelRide(Long rideID) {
        return null;
    }

    @Override
    public RideDTO startRide(Long rideID) {
        return null;
    }

    @Override
    public RideDTO endRide(Long rideID) {
        return null;
    }

    @Override
    public RideDTO acceptRide(Long rideID) {
        return null;
    }

    @Override
    public RiderDTO rateRider(Long rideID, Integer rating) {
        return null;
    }

    @Override
    public DriverDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllMyRides() {
        return List.of();
    }
}
