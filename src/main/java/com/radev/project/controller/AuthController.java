package com.radev.project.controller;

import com.radev.project.dto.user.LoginRequest;
import com.radev.project.security.jwt.JwtUtils;
import com.radev.project.service.abstraction.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;
    @GetMapping
    public Object test(){
        return authService.getCurrentUser();
    }
    @PostMapping
    public String getTokenJwt(@RequestBody LoginRequest payload){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(),payload.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtUtils.generateToken(payload.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    }
}
