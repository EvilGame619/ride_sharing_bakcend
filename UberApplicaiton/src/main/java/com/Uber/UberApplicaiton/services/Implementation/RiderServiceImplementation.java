package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.*;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.exceptions.RideRequestStatusException;
import com.Uber.UberApplicaiton.repository.RideRequestRepository;
import com.Uber.UberApplicaiton.repository.RiderRepository;
import com.Uber.UberApplicaiton.services.DriverService;
import com.Uber.UberApplicaiton.services.RideService;
import com.Uber.UberApplicaiton.services.RiderService;
import com.Uber.UberApplicaiton.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImplementation implements RiderService {

    private final RideStrategyManager rideStrategyManager;
    private final ModelMapper mapper;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;

    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider = getCurrentRider();

        RideRequest rideRequest = mapper.map(rideRequestDTO, RideRequest.class);

        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

        return mapper.map(savedRideRequest,RideRequestDTO.class);
    }
    @Transactional
    @Override
    public RideDTO cancelRide(Long rideID) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideID);
        if(!rider.equals(ride.getRider())) throw new RuntimeException("Wrong rider");
        Driver driver = driverService.getCurrentDriver();
        if(ride.getRideStatus().equals(RideStatus.CANCELLED) || ride.getRideStatus().equals(RideStatus.ENDED))
            throw new RideRequestStatusException("Ride is already ended or cancelled");
        driverService.updateDriverAvailability(ride.getDriver(),true);
        rideService.updateRideStatus(rideID,RideStatus.CANCELLED);
        return mapper.map(ride, RideDTO.class);
    }

    @Transactional
    @Override
    public DriverDTO rateDriver(Long rideID, Integer rating) {
        Ride ride = rideService.getRideById(rideID);
        Driver driver = ride.getDriver();
        Double oldRating = driver.getRating();
        Double newRating = oldRating + rating;
        Integer numberOfRides = driverService.getDriverRidesList(driver.getDriverID());
        driver.setRating(newRating/numberOfRides);
        return mapper.map(driver, DriverDTO.class);
    }

    @Transactional
    @Override
    public RiderDTO getMyProfile() {
        Rider rider = getCurrentRider();
        return mapper.map(rider, RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Rider rider = getCurrentRider();
        return rideService.getAllRidesOfRider(rider, pageRequest).map(
                ride -> mapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Rider createRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        return riderRepository.findById(1L).orElseThrow(()-> new ResourceNotFoundException("Rider not found with id 1"));
    }
}
