package com.radev.project.dao;

import com.radev.project.entity.User;
import com.radev.project.entity.Warehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query(value = """
          SELECT DISTINCT w
          FROM Warehouse w
          LEFT JOIN w.location l
          WHERE w.name LIKE %:search%
          OR l.city LIKE %:search%
          OR l.state LIKE %:search%
          OR l.country LIKE %:search%
          OR l.address LIKE %:search%
          OR l.zipCode LIKE %:search%
          OR w.capacity LIKE %:search%
          """)
    Page<Warehouse> findAllPagination(@Param("search") String search,
                                 Pageable pagination);
}