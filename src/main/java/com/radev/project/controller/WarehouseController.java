package com.radev.project.controller;

import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.warehouse.WarehouseRegister;
import com.radev.project.dto.warehouse.WarehouseUpdate;
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

    @GetMapping("/{id}")
    public Warehouse findById(@PathVariable Long id) {
        return (Warehouse) crudService.findById(id);
    }

    @GetMapping("/paging")
    public PageTemplate getPaging(
            @RequestParam(name = "search", defaultValue = "", required = false) String search,
            @RequestParam(name = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(name = "sortType", defaultValue = "desc", required = false) String sortType) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> softDeleteUser(@PathVariable Long id) {
        crudService.delete(id);
        return ResponseEntity.ok("Warehouse deleted successfully");
    }

    @PatchMapping("/{id}/deactivate")
    public Warehouse deactivateWarehouse(@PathVariable Long id) {
        return warehouseService.deactivateOrActivateWarehouse(id, false);
    }

    @PatchMapping("/{id}/activate")
    public Warehouse activateWarehouse(@PathVariable Long id) {
        return warehouseService.deactivateOrActivateWarehouse(id, true);
    }
}
