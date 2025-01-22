package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import com.Uber.UberApplicaiton.exceptions.DriverNotAvailableException;
import com.Uber.UberApplicaiton.exceptions.OtpNotMatchException;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.exceptions.RideRequestStatusException;
import com.Uber.UberApplicaiton.repository.DriverRepository;
import com.Uber.UberApplicaiton.services.DriverService;
import com.Uber.UberApplicaiton.services.RideRequestService;
import com.Uber.UberApplicaiton.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImplementation implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper mapper;

    @Override
    public RideDTO cancelRide(Long rideID) {
        return null;
    }

    @Transactional
    @Override
    public RideDTO startRide(Long rideID, String otp) {
        Ride ride = rideService.findRideByID(rideID);
        if(!ride.getOtp().equals(otp)) throw new OtpNotMatchException("OTP does not match");
        rideService.updateRideStatus(rideID, RideStatus.ONGOING);
        ride.setStartedAt(LocalDateTime.now());
        return mapper.map(ride, RideDTO.class);
    }

    @Override
    public RideDTO endRide(Long rideID) {


        return null;
    }

    @Transactional
    @Override
    public RideDTO acceptRide(Long rideRequestID) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestID);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)) {
            throw new RideRequestStatusException("Ride Request cannot be accepted. Its either cancelled or confirmed. Id: "+rideRequest.getId());
        }
        Driver driver = getCurrentDriver();
        if(!driver.getAvailable()) throw new DriverNotAvailableException("Driver not available");
        Ride ride = rideService.createNewRide(rideRequest,driver);
        driver.setAvailable(false);
        return mapper.map(ride, RideDTO.class);
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

    @Override
    public Driver getCurrentDriver(){
        return driverRepository.findById(2L)
                .orElseThrow(()-> new ResourceNotFoundException("Driver not found with ID: "+"2"));
    }
}
