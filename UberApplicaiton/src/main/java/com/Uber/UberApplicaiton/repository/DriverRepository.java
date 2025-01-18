package com.Uber.UberApplicaiton.repository;

import com.Uber.UberApplicaiton.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickUpLocation) AS distance" +
            " FROM drivers AS d " +
            "where available = true AND ST_DWithin(d.current_location, :pickUpLocation, 5)" +
            "ORDER BY distance" +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickUpLocation);


    @Query(value = "SELECT d.*, ST_Distance(d.current_location,:pickUpLocation) AS distance" +
            "FROM drivers AS d" +
            "where available = true AND ST_DWithin(d.current_location, :pickUpLocation, 5)" +
            "ORDER BY d.rating DESC" +
            "LIMIT 10",nativeQuery = true)
    List<Driver> findTopTenRatedDrivers(Point pickUpLocation);
}
