package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Payment;
import com.Uber.UberApplicaiton.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}
