package com.radev.project.service.implementation;

import com.radev.project.dao.LocationRepository;
import com.radev.project.dao.WarehouseRepository;
import com.radev.project.dto.MetaData;
import com.radev.project.dto.PageTemplate;
import com.radev.project.dto.warehouse.WarehouseRegister;
import com.radev.project.dto.warehouse.WarehouseUpdate;
import com.radev.project.entity.Location;
import com.radev.project.entity.Warehouse;
import com.radev.project.service.abstraction.AuthService;
import com.radev.project.service.abstraction.CrudService;
import com.radev.project.service.abstraction.WarehouseService;
import com.radev.project.service.exception._40x.WarehouseNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("warehouse")
public class WarehouseServiceImpl implements CrudService, WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private AuthService authService;
    @Override
    public List<?> findAll() {
        return warehouseRepository.findAll();
    }
    @Override
    public Object findById(Object id) {
        return warehouseRepository.findById((Long) id)
                .orElseThrow(()-> new WarehouseNotFoundException("id",id));
    }
    @Override
    @Transactional
    public Object create(Object payload) {
        var warehouseRegister = (WarehouseRegister) payload;
        Location location = warehouseRegister.getLocation().convert();
        location.setCreatedBy(authService.getCurrentUser().getId().toString());
        locationRepository.save(location);

        var newWarehouse = new Warehouse(
                warehouseRegister.getName(),
                location,
                warehouseRegister.getCategory(),
                warehouseRegister.getCapacity()
        );
        newWarehouse.setCreatedBy(authService.getCurrentUser().getId().toString());

        return warehouseRepository.save(newWarehouse);
    }
    @Override
    public Object update(Object payload) {
        var update = (WarehouseUpdate) payload;
        var warehouse = warehouseRepository.findById(update.getId())
                .orElseThrow(()-> new WarehouseNotFoundException("id",update.getId()));
        warehouse.setName(update.getName());
        warehouse.setCapacity(update.getCapacity());
        warehouse.setCategory(update.getCategory());

        Location warehouseLocation = warehouse.getLocation();
        warehouseLocation.setCity(update.getLocation().getCity());
        warehouseLocation.setState(update.getLocation().getState());
        warehouseLocation.setCountry(update.getLocation().getCountry());
        warehouseLocation.setAddress(update.getLocation().getAddress());
        warehouseLocation.setZipCode(update.getLocation().getZipCode());
        warehouseLocation.setUpdatedBy(authService.getCurrentUser().getId().toString());
        warehouseLocation.setUpdatedDate(LocalDateTime.now());

        return warehouseRepository.save(warehouse);
    }
    @Override
    public void delete(Object id) {
        Warehouse warehouse = warehouseRepository.findById((Long) id)
                .orElseThrow(() -> new WarehouseNotFoundException("id",id));
        warehouseRepository.delete(warehouse);
    }

    @Override
    public PageTemplate findAllPagination(String search, Integer page, Integer size, String sortBy, String sortType) {
        PageTemplate result = new PageTemplate();
        MetaData meta = new MetaData();

        PageRequest pagination = PageRequest.of(
                page-1,
                size,
                Sort.by(Sort.Direction.fromString(sortType), sortBy));

        Page<Warehouse> users = warehouseRepository.findAllPagination(
                search,
                pagination
        );

        meta.setTotalCount(users.getTotalElements());
        meta.setPageCount(users.getTotalPages());
        meta.setCurrentPage(page);
        meta.setPerPage(size);

        result.set_items(users.getTotalPages() == 0 ? null : users.getContent());
        result.set_meta(meta);

        return result;
    }
    @Override
    public Warehouse deactivateOrActivateWarehouse(Long id, boolean isActive) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException("id",id));
        warehouse.setActive(isActive);
        warehouse.setUpdatedDate(LocalDateTime.now());
        warehouse.setUpdatedBy(authService.getCurrentUser().getId().toString());
        return warehouseRepository.save(warehouse);
    }
}
