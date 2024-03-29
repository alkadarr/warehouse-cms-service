package com.radev.project.service.implementation;

import com.radev.project.dao.UserRepository;
import com.radev.project.entity.User;
import com.radev.project.service.abstraction.AuthService;
import com.radev.project.service.exception._40x.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User getCurrentUser() {
        var username = getCurrentUsernameLogin();
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("username",username));
    }

    @Override
    public String getCurrentUsernameLogin() {
        // Get the authentication object from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the user is authenticated
        if (authentication != null && authentication.isAuthenticated()) {
            // Get the username from the authentication object
            return authentication.getName();
        } else {
            throw new AuthenticationCredentialsNotFoundException("no authentication");
        }
    }
}
