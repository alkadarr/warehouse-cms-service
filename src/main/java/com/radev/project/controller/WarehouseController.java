package com.radev.project.controller;

import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.user.ChangePasswordRequest;
import com.radev.project.dto.user.UserRegister;
import com.radev.project.dto.user.UserUpdate;
import com.radev.project.dto.warehouse.WarehouseRegister;
import com.radev.project.dto.warehouse.WarehouseUpdate;
import com.radev.project.entity.User;
import com.radev.project.entity.Warehouse;
import com.radev.project.service.abstraction.CrudService;
import com.radev.project.service.abstraction.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    @Qualifier("warehouse")
    private CrudService crudService;
    @Autowired
    private WarehouseService warehouseService;
    @GetMapping
    public List<?> findAll() {
        return crudService.findAll();
    }
    @GetMapping("/{warehouseId}")
    public Warehouse findById(@PathVariable("warehouseId") Long id){
        return (Warehouse) crudService.findById(id);
    }
    @GetMapping("/paging")
    public PageTemplate getPaging(
            @RequestParam(name = "search",defaultValue = "",required = false) String search,
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortType", defaultValue = "desc", required = false) String sortType){
        return warehouseService.findAllPagination(search, page, size, sortBy, sortType);
    }
    @PostMapping
    public Warehouse registerUser(@Valid @RequestBody WarehouseRegister payload) {
        return (Warehouse) crudService.create(payload);
    }
    @PutMapping
    public Warehouse updateUser(@Valid @RequestBody WarehouseUpdate payload) {
        return (Warehouse) crudService.update(payload);
    }
    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<String> softDeleteUser(@PathVariable Long warehouseId) {
        crudService.delete(warehouseId);
        return ResponseEntity.ok("Warehouse deleted successfully");
    }
}
