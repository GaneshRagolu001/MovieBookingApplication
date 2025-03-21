package com.gane.MovieBookingApplication.Controller;

import com.gane.MovieBookingApplication.DTO.LoginRequestDTO;
import com.gane.MovieBookingApplication.DTO.LoginResponseDTO;
import com.gane.MovieBookingApplication.DTO.RegisterRequestDTO;
import com.gane.MovieBookingApplication.entity.User;
import com.gane.MovieBookingApplication.repository.UserRepository;
import com.gane.MovieBookingApplication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public AuthenticationService authenticationService;

    @PostMapping("/registernormaluser")
    public ResponseEntity<User> registerNormalUser(@RequestBody RegisterRequestDTO registerRequestDTO){
        System.out.println("Controller hitted");
        return ResponseEntity.ok(authenticationService.registerNormalUser(registerRequestDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        System.out.println(loginRequestDTO);
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }
}
