package com.Uber.UberApplicaiton.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_rating_rider",columnList = "rider"),
        @Index(name = "idx_rating_driver",columnList = "driver")
})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Driver driver;

    private Double driverRating;
    private Double riderRating;
}
