package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
}
