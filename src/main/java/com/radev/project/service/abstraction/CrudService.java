package com.radev.project.service.abstraction;

import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.user.UserRegister;
import com.radev.project.dto.user.UserUpdate;
import com.radev.project.entity.User;

import java.util.List;

public interface CrudService {
    List<?> findAll();
    Object create(Object payload);
    Object update(Object payload);
    void delete(Long id);
}
