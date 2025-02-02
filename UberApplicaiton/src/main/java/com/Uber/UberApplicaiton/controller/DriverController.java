package com.Uber.UberApplicaiton.controller;

import com.Uber.UberApplicaiton.dto.RideDTO;
import com.Uber.UberApplicaiton.dto.RideStartDTO;
import com.Uber.UberApplicaiton.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@RequiredArgsConstructor
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

    @GetMapping("rateDriver/{driverID}")
    public ResponseEntity<Integer> getRating(@PathVariable Long driverID){
        return ResponseEntity.ok(driverService.getDriverRidesList(driverID));
    }

    @PostMapping("endRide/{rideID}")
    public ResponseEntity<RideDTO> endRide(@PathVariable Long rideID){
        return ResponseEntity.ok(driverService.endRide(rideID));
    }

}
