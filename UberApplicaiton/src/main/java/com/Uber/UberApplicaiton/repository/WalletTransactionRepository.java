package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.WalletTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransactions, Long> {
    Optional<WalletTransactions> findByRide(Ride ride);
}
