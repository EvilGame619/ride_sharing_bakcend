package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
}
