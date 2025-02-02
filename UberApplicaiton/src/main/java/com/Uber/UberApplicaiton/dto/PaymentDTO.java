package com.Uber.UberApplicaiton.dto;

import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.enums.PaymentMethod;
import com.Uber.UberApplicaiton.entities.enums.PaymentStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Long paymentID;

    private PaymentMethod method;


    private RideDTO ride;

    private Double amount;


    private PaymentStatus paymentStatus;


    private LocalDateTime paymentTime;
}
