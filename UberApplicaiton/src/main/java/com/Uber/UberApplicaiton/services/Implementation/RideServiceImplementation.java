package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.repository.RideRepository;
import com.Uber.UberApplicaiton.services.RideRequestService;
import com.Uber.UberApplicaiton.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class RideServiceImplementation implements RideService {

    private final RideRepository rideRepository;
    private final ModelMapper mapper;
    private final RideRequestService rideRequestService;

    @Override
    public Ride getRideById(Long rideID) {
        return null;
    }



    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride newRide = mapper.map(rideRequest, Ride.class);
        newRide.setDriver(driver);
        newRide.setRideStatus(RideStatus.CONFIRMED);
        newRide.setOtp(generateRandomOTP());
        rideRequestService.update(rideRequest.getId(),newRide.getRideStatus());
        return rideRepository.save(newRide);
    }

    @Override
    public void updateRideStatus(Long rideID, RideStatus rideStatus) {
        Ride ride = findRideByID(rideID);
        ride.setRideStatus(rideStatus);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider, pageRequest);
    }

    @Override
    public List<Ride> getListOfRidesOfDriver(Long driverID) {
        return rideRepository.findAllRidesOfDriver(driverID);
    }

    @Override
    public Ride findRideByID(Long rideID) {
        return rideRepository.findById(rideID).orElseThrow(()->
                new ResourceNotFoundException("Ride not found with id: "+rideID));
    }

    public String generateRandomOTP(){
        Random random = new Random();
        int otpINT = random.nextInt(10000);
        return String.format("%04d",otpINT);
    }
}
