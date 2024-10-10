package com.Uber.UberApplicaiton.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverID;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

    private Boolean available;

    //this is to define that we are using geometry and 4326 means we are working on earth geometry
    @Column(columnDefinition = "Geometry(Point,4326)")
    Point currentLocation;
}
