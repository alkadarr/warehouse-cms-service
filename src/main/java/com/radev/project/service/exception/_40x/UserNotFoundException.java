package com.radev.project.service.exception._40x;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException(String entity, Object entityId) {
        super("User",entity,entityId);
    }
}
