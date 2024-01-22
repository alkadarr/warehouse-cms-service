package com.radev.project.service;

import com.radev.project.entity.User;

public interface AuthService {
    User getCurrentUser();
    String getCurrentUsernameLogin();
}
