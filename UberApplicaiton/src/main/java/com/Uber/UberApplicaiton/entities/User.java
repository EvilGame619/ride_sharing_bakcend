package com.Uber.UberApplicaiton.entities;

import com.Uber.UberApplicaiton.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
//this is important because postgresql already have a user table
@Table(name = "app_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    //postgre doesnt allow multiple data to store so this will create another coloumn or table to assign the roles
    @ElementCollection(fetch = FetchType.EAGER)
    //this is to select that how data will be stored(string :ADMin,rider,driver; ORDINAL: 0,1,2)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }
}
