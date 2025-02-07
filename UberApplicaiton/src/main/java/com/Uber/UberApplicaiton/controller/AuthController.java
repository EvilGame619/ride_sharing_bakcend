package com.Uber.UberApplicaiton.controller;

import com.Uber.UberApplicaiton.dto.DriverDTO;
import com.Uber.UberApplicaiton.dto.SignupDTO;
import com.Uber.UberApplicaiton.dto.UserDTO;
import com.Uber.UberApplicaiton.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupDTO signupDTO){
        return new ResponseEntity<>(authService.signup(signupDTO), HttpStatus.CREATED);
    }

    @PostMapping("onBoardNewDriver/{userID}")
    public ResponseEntity<DriverDTO> onBoardNewDriver(@RequestBody DriverDTO driverDTO,@PathVariable Long userID){
        return new ResponseEntity<>(authService.onboardNewDriver(driverDTO, userID),HttpStatus.CREATED);
    }
}
