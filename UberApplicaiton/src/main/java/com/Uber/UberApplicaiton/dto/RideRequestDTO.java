package com.Uber.UberApplicaiton.dto;

import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.enums.PaymentMethod;
import com.Uber.UberApplicaiton.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDTO {

    private Long rideRequestID;


    private Point pickUpLocation;


    private Point dropOffLocation;


    private LocalDateTime requestedTime;


    private Rider rider;


    private PaymentMethod paymentMethod;


    private RideRequestStatus rideRequestStatus;
}
