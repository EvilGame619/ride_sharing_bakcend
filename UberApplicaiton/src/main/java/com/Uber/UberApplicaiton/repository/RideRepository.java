package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {

    @Query(value = "SELECT * FROM ride WHERE driver_driverid=:driverID",nativeQuery = true)
    List<Ride> findAllRidesOfDriver(@Param("driverID") Long driverID);

    Page<Ride> findByRider(Rider rider,  Pageable pageable);
    Page<Ride> findByDriver(Driver driver, Pageable pageable);
}
