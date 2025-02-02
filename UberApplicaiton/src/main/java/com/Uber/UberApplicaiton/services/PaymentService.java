package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.entities.Payment;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.enums.PaymentStatus;

public interface PaymentService {

    Payment processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
