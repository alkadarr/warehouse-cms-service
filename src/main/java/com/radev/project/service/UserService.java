package com.radev.project.service;

import com.radev.project.dtos.PageTemplate;
import com.radev.project.dtos.user.UserRegister;
import com.radev.project.dtos.user.UserUpdate;
import com.radev.project.entity.User;

import java.util.List;

public interface UserService{
    List<User> findAll();
    PageTemplate findAllPagination(String search, Long roleId, Integer page, Integer size, String sortBy, String sortType);
    User create(UserRegister userRegister);

    User update(UserUpdate userUpdate);

    void delete(Long userId);
}
