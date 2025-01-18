package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.SignupDTO;
import com.Uber.UberApplicaiton.dto.UserDTO;
import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.entities.enums.Role;
import com.Uber.UberApplicaiton.exceptions.RuntimeConflictException;
import com.Uber.UberApplicaiton.repository.UserRepository;
import com.Uber.UberApplicaiton.services.AuthService;
import com.Uber.UberApplicaiton.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor

public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDTO signup(SignupDTO signupDTO) {

        userRepository.findByEmail(signupDTO.getEmail()).orElseThrow(()->
                new RuntimeConflictException("Cannot signup, user already exist by email: "+signupDTO.getEmail()));

        User newUser = mapper.map(signupDTO, User.class);
        newUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(newUser);

        riderService.createRider(savedUser);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Override
    public DriverDTO onboardNewDriver(Long userID) {
        return null;
    }
}
