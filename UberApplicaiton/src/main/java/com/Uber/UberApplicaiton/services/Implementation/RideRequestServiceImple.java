package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.repository.RideRequestRepository;
import com.Uber.UberApplicaiton.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImple implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository.findById(rideRequestId)
                .orElseThrow(()-> new ResourceNotFoundException("Ride Request not found with id: "+ rideRequestId));
    }

    @Override
    public void update(Long id, RideStatus rideStatus) {
        RideRequest rideRequest = findRideRequestById(id);
        if(rideStatus==RideStatus.CONFIRMED) rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        else if(rideStatus==RideStatus.CANCELLED) rideRequest.setRideRequestStatus(RideRequestStatus.CANCELLED);
    }
}
