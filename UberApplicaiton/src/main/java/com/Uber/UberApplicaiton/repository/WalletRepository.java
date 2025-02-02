package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByUser(User user);

}
