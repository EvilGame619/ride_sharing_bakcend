package com.Uber.UberApplicaiton.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riderID;

    //this is creating one to one mapping between user and the rider so that with user id 1 there can be only one rider
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

}
