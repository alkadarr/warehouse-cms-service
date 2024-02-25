package com.radev.project.entity;

import com.radev.project.entity.general.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Product_Stock")
@Getter @Setter
public class ProductStock extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @Column(nullable = false)
    private Integer stockQuantity;
}
