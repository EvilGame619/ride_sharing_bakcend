package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
}
