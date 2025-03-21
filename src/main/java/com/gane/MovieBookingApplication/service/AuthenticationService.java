package com.gane.MovieBookingApplication.service;

import com.gane.MovieBookingApplication.DTO.LoginRequestDTO;
import com.gane.MovieBookingApplication.DTO.LoginResponseDTO;
import com.gane.MovieBookingApplication.DTO.RegisterRequestDTO;
import com.gane.MovieBookingApplication.JWT.JwtService;
import com.gane.MovieBookingApplication.entity.User;
import com.gane.MovieBookingApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtService jwtService;

    public User registerNormalUser(RegisterRequestDTO registerRequestDTO){

        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User Already Registered");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);
        System.out.println(user);
        return userRepository.save(user);
    }

    public User registerAdminUser(RegisterRequestDTO registerRequestDTO){

        if(userRepository.findByUsername(registerRequestDTO.getUsername()).isPresent()){
            throw new RuntimeException("User Already Registered");
        }

        Set<String> roles = new HashSet<>();
        roles.add("ROLE_ADMIN");
        roles.add("ROLE_USER");

        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        user.setRoles(roles);

        return userRepository.save(user);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
       User user = userRepository.findByUsername(loginRequestDTO.getUsername())
               .orElseThrow(() -> new RuntimeException("User Not Found Please Register"));

       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(
                       loginRequestDTO.getUsername(),
                       loginRequestDTO.getPassword()
               ));

       String token = jwtService.generateToken(user);

       return LoginResponseDTO.builder()
               .jwtToken(token)
               .username(user.getUsername())
               .roles(user.getRoles())
               .build();

    }



}
