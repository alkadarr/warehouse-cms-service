package com.radev.project.service.abstraction;

import com.radev.project.dto.PageTemplate;
import com.radev.project.entity.Warehouse;

public interface WarehouseService {
    PageTemplate findAllPagination(String search, Integer page, Integer size, String sortBy, String sortType);
    Warehouse deactivateOrActivateWarehouse(Long id, boolean isActive);
}
