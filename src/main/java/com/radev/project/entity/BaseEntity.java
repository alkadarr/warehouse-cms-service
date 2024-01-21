package com.radev.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    public BaseEntity(Long id) {
        this.id = id;
    }
}
