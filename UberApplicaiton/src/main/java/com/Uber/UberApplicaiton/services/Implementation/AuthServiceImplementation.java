package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.SignupDTO;
import com.Uber.UberApplicaiton.dto.UserDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.entities.enums.Role;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.exceptions.RuntimeConflictException;
import com.Uber.UberApplicaiton.repository.UserRepository;
import com.Uber.UberApplicaiton.services.AuthService;
import com.Uber.UberApplicaiton.services.DriverService;
import com.Uber.UberApplicaiton.services.RiderService;
import com.Uber.UberApplicaiton.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    @Transactional
    public UserDTO signup(SignupDTO signupDTO) {

        if (userRepository.findByEmail(signupDTO.getEmail()).isPresent()) {
            throw new RuntimeConflictException("Cannot signup, user already exists by email: " + signupDTO.getEmail());
        }

        User newUser = mapper.map(signupDTO, User.class);
        newUser.setRoles(Set.of(Role.RIDER));
        User savedUser = userRepository.save(newUser);
        riderService.createRider(savedUser);
        walletService.createNewWallet(savedUser);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Transactional
    @Override
    public DriverDTO onboardNewDriver(DriverDTO driverDTO, Long userID) {
        User user = userRepository.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+ userID));
        if(user.getRoles().contains(Role.DRIVER)) throw new RuntimeConflictException("User with id "+userID+" is already a driver");
        Driver driver = mapper.map(driverDTO, Driver.class);
        driver.setAvailable(true);
        driver.setUser(user);
        driver.setRating(0.0);
        Driver createdDriver = driverService.createNewDriver(driver);

        //Setting Driver as a role for the user
        Set<Role> roles = new HashSet<>(user.getRoles());  // Create a new HashSet to ensure it's not a direct reference
        roles.add(Role.DRIVER);
        user.setRoles(roles);
        return mapper.map(createdDriver, DriverDTO.class);
    }
}
