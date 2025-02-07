package com.Uber.UberApplicaiton.services;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.SignupDTO;
import com.Uber.UberApplicaiton.dto.UserDTO;


public interface AuthService {

    String login(String email, String password);
    UserDTO signup(SignupDTO signupDTO);
    DriverDTO onboardNewDriver(DriverDTO driverDTO,Long userID);

}
