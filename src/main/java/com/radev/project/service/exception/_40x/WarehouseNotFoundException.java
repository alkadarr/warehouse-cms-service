package com.radev.project.service.exception._40x;

public class WarehouseNotFoundException extends EntityNotFoundException {
    public WarehouseNotFoundException(String entity, Object entityId) {
        super("Warehouse",entity,entityId);
    }
}
