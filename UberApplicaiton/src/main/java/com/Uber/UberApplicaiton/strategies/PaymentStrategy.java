package com.Uber.UberApplicaiton.strategies;

import com.Uber.UberApplicaiton.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_FEES = 0.3;

    void processPayment(Payment payment);

}
