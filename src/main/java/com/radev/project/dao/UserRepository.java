package com.radev.project.dao;

import com.radev.project.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = """
          SELECT DISTINCT u
          FROM User u
          LEFT JOIN u.roles r
          WHERE (:roleId IS NULL OR :roleId = 0 OR r.id = :roleId)
          AND (u.username LIKE %:search%
          OR u.email LIKE %:search%)
          """)
    Page<User> findAllPagination(@Param("search") String search,
                                 @Param("roleId") Long roleId,
                                 Pageable pagination);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}