package com.radev.project.controller;

import com.radev.project.entity.Role;
import com.radev.project.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public List<Role> findAll() {
        return roleService.findAll();
    }
}
