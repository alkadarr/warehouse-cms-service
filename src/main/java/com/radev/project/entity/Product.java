package com.radev.project.entity;

import com.radev.project.entity.general.BaseEntity;
import com.radev.project.entity.general.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "Product")
@Getter @Setter
public class Product extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(length = 1000)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(length = 50)
    private String gender;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
