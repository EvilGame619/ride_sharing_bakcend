package com.Uber.UberApplicaiton.dto;


import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.enums.PaymentMethod;
import com.Uber.UberApplicaiton.entities.enums.RideStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDTO {
    private Long rideID;

    private PointDTO pickUpLocation;


    private PointDTO dropOffLocation;


    private LocalDateTime requestedTime;


    private RiderDTO rider;


    private DriverDTO driver;

    private String otp;

    private PaymentMethod paymentMethod;


    private RideStatus rideStatus;

    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
