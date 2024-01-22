package com.radev.project.service.implementation;

import com.radev.project.dao.RoleRepository;
import com.radev.project.entity.Role;
import com.radev.project.service.abstraction.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("role")
public class RoleServiceImpl implements CrudService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<?> findAll() {
        return roleRepository.findAll();
    }
    @Override
    public Object create(Object payload) {
        return null;
    }
    @Override
    public Object update(Object payload) {
        return null;
    }
    @Override
    public void delete(Long id) {
    }
}
