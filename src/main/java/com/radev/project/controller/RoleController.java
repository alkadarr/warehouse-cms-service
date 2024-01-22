package com.radev.project.controller;

import com.radev.project.service.abstraction.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    @Qualifier("role")
    private CrudService crudService;
    @GetMapping
    public List<?> findAll() {
        return crudService.findAll();
    }
}
