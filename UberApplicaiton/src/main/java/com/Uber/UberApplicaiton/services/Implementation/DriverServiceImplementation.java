package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Payment;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.RideRequest;
import com.Uber.UberApplicaiton.entities.enums.PaymentStatus;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;
import com.Uber.UberApplicaiton.exceptions.*;
import com.Uber.UberApplicaiton.repository.DriverRepository;
import com.Uber.UberApplicaiton.services.DriverService;
import com.Uber.UberApplicaiton.services.PaymentService;
import com.Uber.UberApplicaiton.services.RideRequestService;
import com.Uber.UberApplicaiton.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final PaymentService paymentService;

    @Override
    @Transactional
    public RideDTO cancelRide(Long rideID) {
        Ride ride = rideService.findRideByID(rideID);
        Driver driver = getCurrentDriver();
        if(ride.getDriver()!=driver) throw new RuntimeException("Wrong Driver");
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) throw new RideRequestStatusException("Ride is either cancelled or started");
        rideService.updateRideStatus(rideID, RideStatus.CANCELLED);
        updateDriverAvailability(ride.getDriver(),true);
        return mapper.map(ride, RideDTO.class);
    }

    @Transactional
    @Override
    public RideDTO startRide(Long rideID, String otp) {
        Ride ride = rideService.findRideByID(rideID);
        if(!ride.getOtp().equals(otp)) throw new OtpNotMatchException("OTP does not match");
        rideService.updateRideStatus(rideID, RideStatus.ONGOING);
        ride.setStartedAt(LocalDateTime.now());
        paymentService.createNewPayment(ride);
        return mapper.map(ride, RideDTO.class);
    }
    @Transactional
    @Override
    public RideDTO endRide(Long rideID) {
        Ride ride = rideService.findRideByID(rideID);
        Driver driver = getCurrentDriver();
        if(ride.getDriver()!=driver) throw new RuntimeException("Wrong Driver");
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)) throw new RideRequestStatusException("Ride is Ongoing");
        Payment payment = paymentService.processPayment(ride);
        if (!payment.getPaymentStatus().equals(PaymentStatus.DONE)) throw new PaymentFailedException("Payment didn't happened");
        ride.setEndedAt(LocalDateTime.now());
        rideService.updateRideStatus(rideID, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        return mapper.map(ride, RideDTO.class);

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
        updateDriverAvailability(driver,false);
        return mapper.map(ride, RideDTO.class);
    }

    @Override
    public RiderDTO rateRider(Long rideID, Double rating) {
        return null;
    }

    @Override
    public DriverDTO getMyProfile() {
        Driver driver = getCurrentDriver();

        return mapper.map(driver, DriverDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Driver driver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver, pageRequest).map(
                ride -> mapper.map(ride, RideDTO.class)
        );
    }

    @Override
    public Integer getDriverRidesList(Long driverID) {
        List<Ride> rides = rideService.getListOfRidesOfDriver(driverID);

        return rides.size();
    }


    @Override
    public Driver getCurrentDriver(){
        return driverRepository.findById(2L)
                .orElseThrow(()-> new ResourceNotFoundException("Driver not found with ID: "+"2"));
    }

    @Override
    public void updateDriverAvailability(Driver driver, Boolean available) {
        driver.setAvailable(available);
    }

    @Override
    public Driver getDriverByID(Long driverID) {
        return driverRepository.findById(driverID).orElseThrow(()-> new ResourceNotFoundException("Driver not found with id: "+driverID));
    }

    @Override
    public Driver createNewDriver(Driver driver) {
        return driverRepository.save(driver);
    }
}
