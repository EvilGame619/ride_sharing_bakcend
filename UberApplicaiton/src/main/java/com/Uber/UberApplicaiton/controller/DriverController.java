package com.Uber.UberApplicaiton.controller;

import com.Uber.UberApplicaiton.dto.*;
import com.Uber.UberApplicaiton.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
@Secured("ROLE_DRIVER")
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestID}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestID){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestID));
    }

    @PostMapping("/startRide/{rideRequestID}")
    public ResponseEntity<RideDTO> startRide(@PathVariable Long rideRequestID, @RequestBody RideStartDTO rideStartDTO){
        return ResponseEntity.ok(driverService.startRide(rideRequestID,rideStartDTO.getOtp()));
    }


    @PostMapping("endRide/{rideID}")
    public ResponseEntity<RideDTO> endRide(@PathVariable Long rideID){
        return ResponseEntity.ok(driverService.endRide(rideID));
    }
    @PostMapping("/cancelRide/{rideID}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideID){
        return new ResponseEntity<>(driverService.cancelRide(rideID), HttpStatus.OK);
    }

    @PutMapping("/rateRider/{rideID}")
    public ResponseEntity<RiderDTO> rateRider(@RequestBody RatingDTO ratingDTO, @PathVariable Long rideID){
        return new ResponseEntity<>(driverService.rateRider(rideID, ratingDTO.getRating()),HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<DriverDTO> getProfile(){
        return new ResponseEntity<>(driverService.getMyProfile(),HttpStatus.OK);
    }

    @GetMapping("/myRides")
    public ResponseEntity<Page<RideDTO>> getAllRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                     @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffset,pageSize, Sort.by(Sort.Direction.DESC, "createdTime", "id"));
        return new ResponseEntity<>(driverService.getAllMyRides(pageRequest),HttpStatus.OK);

    }

}
