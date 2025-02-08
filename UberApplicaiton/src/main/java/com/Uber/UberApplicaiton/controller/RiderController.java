package com.Uber.UberApplicaiton.controller;

import com.Uber.UberApplicaiton.dto.*;
import com.Uber.UberApplicaiton.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;


    @PostMapping("/rideRequest")
    public ResponseEntity<RideRequestDTO> requestRide(@RequestBody RideRequestDTO rideRequestDTO){
        return new ResponseEntity<>(riderService.requestRide(rideRequestDTO), HttpStatus.OK);
    }

    @PostMapping("/cancelRide/{rideID}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideID){
        return new ResponseEntity<>(riderService.cancelRide(rideID), HttpStatus.OK);
    }

    @PutMapping("/rateDriver/{rideID}")
    public ResponseEntity<DriverDTO> rateDriver(@PathVariable Long rideID, @RequestBody RatingDTO ratingDTO){
        return new ResponseEntity<>(riderService.rateDriver(rideID, ratingDTO.getRating()),HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<RiderDTO> getProfile(){
        return new ResponseEntity<>(riderService.getMyProfile(),HttpStatus.OK);
    }

    @GetMapping("/myRides")
    public ResponseEntity<Page<RideDTO>> getAllRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                     @RequestParam(defaultValue = "10",required = false) Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffset,pageSize, Sort.by(Sort.Direction.DESC, "createdTime", "id"));
        return new ResponseEntity<>(riderService.getAllMyRides(pageRequest),HttpStatus.OK);

    }
}
