package com.radev.project.service.abstraction;

import com.radev.project.dto.PageTemplate;

public interface WarehouseService {
    PageTemplate findAllPagination(String search, Integer page, Integer size, String sortBy, String sortType);
}
