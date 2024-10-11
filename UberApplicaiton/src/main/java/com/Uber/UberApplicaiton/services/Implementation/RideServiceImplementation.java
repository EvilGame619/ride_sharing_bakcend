package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import com.Uber.UberApplicaiton.services.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImplementation implements RideService {
    @Override
    public Ride getRideById(Long rideID) {
        return null;
    }

    @Override
    public void matchWithDrivers(RideRequestDTO rideRequestDTO) {

    }

    @Override
    public Ride createNewRide(RideRequestDTO rideRequestDTO, Driver driver) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Long rideID, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long driverID, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderID, PageRequest pageRequest) {
        return null;
    }
}
