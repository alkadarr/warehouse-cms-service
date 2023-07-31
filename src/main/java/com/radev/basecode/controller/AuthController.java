package com.radev.basecode.controller;

import com.radev.basecode.common.RestResponse;
import com.radev.basecode.dao.RoleRepository;
import com.radev.basecode.dao.UserRepository;
import com.radev.basecode.dto.LoginRequest;
import com.radev.basecode.dto.SignupRequest;
import com.radev.basecode.entity.Role;
import com.radev.basecode.entity.User;
import com.radev.basecode.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> getTokenJwt(@RequestBody LoginRequest payload){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(),payload.getPassword()));
        var rest = new RestResponse();

        if (authentication.isAuthenticated()){
            rest.setSuccess(true);
            rest.setMessage("success");
            rest.setData(jwtUtils.generateToken(payload.getUsername()));
            return ResponseEntity.ok().body(rest);
        } else {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new RestResponse(false,"Username is already used!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new RestResponse(false,"Email is already used!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        user.setCreatedBy("System Signup");

        var roleIds = signUpRequest.getRoleIds();
        List<Role> roles = new ArrayList<>();

        if (roleIds == null) {
            throw new RuntimeException("Role Cannot be null.");
        } else {
            roles = roleRepository.findAllById(roleIds);
        }

        if (roles.size() == 0){
            roles.add(new Role(3L)); // default role as a ROLE_CUSTOMER
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new RestResponse(true,"User registered successfully!"));
    }
}
