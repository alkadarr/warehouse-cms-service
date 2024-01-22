package com.radev.project.service.abstraction;

import com.radev.project.entity.User;

public interface AuthService {
    User getCurrentUser();
    String getCurrentUsernameLogin();
}
