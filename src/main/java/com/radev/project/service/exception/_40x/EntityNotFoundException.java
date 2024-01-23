package com.radev.project.service.exception._40x;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, String entity, Object entityId) {
        super(entityName + " not found for " + entity + ": " + entityId);
    }
}
