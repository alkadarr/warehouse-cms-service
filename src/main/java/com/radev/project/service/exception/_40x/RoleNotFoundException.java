package com.radev.project.service.exception._40x;

public class RoleNotFoundException extends EntityNotFoundException {
    public RoleNotFoundException(String entity, Object entityId) {
        super("Role",entity,entityId);
    }
}
