package com.Uber.UberApplicaiton.dto;

import com.Uber.UberApplicaiton.entities.enums.PaymentMethod;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDTO {

    private Long id;


    private PointDTO pickUpLocation;


    private PointDTO dropOffLocation;


    private LocalDateTime requestedTime;


    private RiderDTO rider;


    private PaymentMethod paymentMethod;


    private RideRequestStatus rideRequestStatus;

    private Double fare;
}
