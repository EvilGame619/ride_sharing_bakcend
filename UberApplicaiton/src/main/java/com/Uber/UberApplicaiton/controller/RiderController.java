package com.Uber.UberApplicaiton.controller;

import com.Uber.UberApplicaiton.dto.RideRequestDTO;
import com.Uber.UberApplicaiton.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;


    @PostMapping("/rideRequest")
    public ResponseEntity<RideRequestDTO> requestRide(@RequestBody RideRequestDTO rideRequestDTO){
        return new ResponseEntity<>(riderService.requestRide(rideRequestDTO), HttpStatus.OK);
    }
}
