package com.radev.project.service.abstraction;

import com.radev.project.dto.PageTemplate;

public interface UserService{
    PageTemplate findAllPagination(String search, Long roleId, Integer page, Integer size, String sortBy, String sortType);
}
