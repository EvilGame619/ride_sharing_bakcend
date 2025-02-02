package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.entities.Payment;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.enums.PaymentStatus;
import com.Uber.UberApplicaiton.exceptions.PaymentFailedException;
import com.Uber.UberApplicaiton.repository.PaymentRepository;
import com.Uber.UberApplicaiton.services.PaymentService;
import com.Uber.UberApplicaiton.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImple implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    @Transactional
    @Override
    public Payment processPayment(Ride ride) {
        Payment payment = paymentRepository.findByRide(ride).orElseThrow(()-> new PaymentFailedException("Payment not found"));
        System.out.println("process payment m find by ride");
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

        return payment;
    }

    @Transactional
    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentStatus(PaymentStatus.PENDING)
                .amount(ride.getFare())
                .paymentMethod(ride.getPaymentMethod())
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);

    }
}
