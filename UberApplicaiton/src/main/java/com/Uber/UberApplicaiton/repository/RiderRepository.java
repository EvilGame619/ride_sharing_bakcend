package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
    Optional<Rider> findByUser(User user);
}
