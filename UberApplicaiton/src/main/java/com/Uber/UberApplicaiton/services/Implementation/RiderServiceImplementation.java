package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import com.Uber.UberApplicaiton.repository.RideRequestRepository;
import com.Uber.UberApplicaiton.repository.RiderRepository;
import com.Uber.UberApplicaiton.services.RiderService;
import com.Uber.UberApplicaiton.strategies.DriverMatchingStrategy;
import com.Uber.UberApplicaiton.strategies.RideFareCalculationStrategy;
import com.Uber.UberApplicaiton.strategies.impl.RideFareSurgePricingCalculator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImplementation implements RiderService {

    private final RideFareSurgePricingCalculator rfc;
    private final ModelMapper mapper;
    private final DriverMatchingStrategy dm;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;


    @Override
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {

        RideRequest rideRequest = mapper.map(rideRequestDTO, RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setFare(rfc.calculateFare(rideRequest));

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        dm.findMatchingDriver(rideRequest);
        return mapper.map(savedRideRequest, RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideID) {
        return null;
    }

    @Override
    public DriverDTO rateDriver(Long rideID, Integer rating) {
        return null;
    }

    @Override
    public RiderDTO getMyProfile() {
        return null;
    }

    @Override
    public List<RideDTO> getAllMyRides() {
        return List.of();
    }

    @Override
    public Rider createRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }
}
