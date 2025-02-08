package com.Uber.UberApplicaiton.services.Implementation;


import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.RiderDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.Rating;
import com.Uber.UberApplicaiton.entities.Ride;
import com.Uber.UberApplicaiton.entities.Rider;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.repository.DriverRepository;
import com.Uber.UberApplicaiton.repository.RatingRepository;
import com.Uber.UberApplicaiton.repository.RiderRepository;
import com.Uber.UberApplicaiton.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RatingServiceImple implements RatingService {

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper mapper;

    @Override
    public DriverDTO rateDriver(Ride ride, Double rating) {
        Driver driver = ride.getDriver();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(()->new ResourceNotFoundException("Rating not found"));
        ratingObj.setDriverRating(rating);

        ratingRepository.save(ratingObj);
        List<Rating> totalRides = ratingRepository.findByDriver(driver);
        Double sumOfRatings = totalRides.stream().map(Rating::getDriverRating)
                .filter(Objects::nonNull)
                .reduce(0.0,Double::sum);

        driver.setRating(sumOfRatings/ totalRides.size());
        Driver savedDriver = driverRepository.save(driver);
        return mapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public RiderDTO rateRider(Ride ride, Double rating) {
        Rider rider = ride.getRider();
        Rating ratingObj = ratingRepository.findByRide(ride).orElseThrow(()->new ResourceNotFoundException("Rating not found"));
        ratingObj.setRiderRating(rating);

        ratingRepository.save(ratingObj);
        List<Rating> totalRides = ratingRepository.findByRider(rider);
        Double sumOfRatings = totalRides.stream().map(Rating::getRiderRating)
                .filter(Objects::nonNull)
                .reduce(0.0,Double::sum);

        rider.setRating(sumOfRatings/ totalRides.size());
        Rider rider1 = riderRepository.save(rider);
        return mapper.map(rider1, RiderDTO.class);
    }

    @Override
    public void createRating(Ride ride) {
        Rating rating = Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
