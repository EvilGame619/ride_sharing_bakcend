package com.Uber.UberApplicaiton.services.Implementation;

import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.exceptions.ResourceNotFoundException;
import com.Uber.UberApplicaiton.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User not found with email: "+username));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+id));
    }
}
