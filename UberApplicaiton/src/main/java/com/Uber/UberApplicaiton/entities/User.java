package com.Uber.UberApplicaiton.entities;

import com.Uber.UberApplicaiton.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
//this is important because postgresql already have a user table
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userID;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    //postgre doesnt allow multiple data to store so this will create another coloumn or table to assign the roles
    @ElementCollection(fetch = FetchType.LAZY)
    //this is to select that how data will be stored(string :ADMin,rider,driver; ORDINAL: 0,1,2)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
}
