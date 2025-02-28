package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.OnBoardNewDriverDTO;
import com.Uber.UberApplicaiton.dto.SignupDTO;
import com.Uber.UberApplicaiton.dto.UserDTO;
import com.Uber.UberApplicaiton.entities.Driver;
import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.entities.enums.Role;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.exceptions.RuntimeConflictException;
import com.Uber.UberApplicaiton.repository.UserRepository;
import com.Uber.UberApplicaiton.security.JWTService;
import com.Uber.UberApplicaiton.services.AuthService;
import com.Uber.UberApplicaiton.services.DriverService;
import com.Uber.UberApplicaiton.services.RiderService;
import com.Uber.UberApplicaiton.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    public String[] login(String email, String password) {
        String[] tokens = new String[2];
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = (User) authentication.getPrincipal();
        tokens[0] = jwtService.generateAccessToken(user);
        tokens[1] = jwtService.generateRefreshToken(user);
        return tokens;
    }

    @Override
    @Transactional
    public UserDTO signup(SignupDTO signupDTO) {

        if (userRepository.findByEmail(signupDTO.getEmail()).isPresent()) {
            throw new RuntimeConflictException("Cannot signup, user already exists by email: " + signupDTO.getEmail());
        }

        User newUser = mapper.map(signupDTO, User.class);
        newUser.setRoles(Set.of(Role.RIDER));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(newUser);
        riderService.createRider(savedUser);
        walletService.createNewWallet(savedUser);
        return mapper.map(savedUser, UserDTO.class);
    }

    @Transactional
    @Override
    public DriverDTO onboardNewDriver(OnBoardNewDriverDTO onBoardNewDriverDTO, Long userID) {
        User user = userRepository.findById(userID).orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+ userID));
        if(user.getRoles().contains(Role.DRIVER)) throw new RuntimeConflictException("User with id "+userID+" is already a driver");
        System.out.println("here");
        Driver driver = Driver.builder()
                .vehicleID(onBoardNewDriverDTO.getVehicleID())
                .build();
        driver.setAvailable(true);
        driver.setUser(user);
        driver.setRating(0.0);
        Driver createdDriver = driverService.createNewDriver(driver);

        //Setting Driver as a role for the user
        Set<Role> roles = new HashSet<>(user.getRoles());  // Create a new HashSet to ensure it's not a direct reference
        roles.add(Role.DRIVER);
        user.setRoles(roles);
        System.out.println(createdDriver);
        return mapper.map(createdDriver, DriverDTO.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserById(userId);
        return jwtService.generateAccessToken(user);
    }
}
