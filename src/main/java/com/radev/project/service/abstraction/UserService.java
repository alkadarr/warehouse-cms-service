package com.radev.project.service.abstraction;

import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.user.ChangePasswordRequest;

public interface UserService{
    PageTemplate findAllPagination(String search, Long roleId, Integer page, Integer size, String sortBy, String sortType);
    void changePassword(ChangePasswordRequest passwordRequest);
}
