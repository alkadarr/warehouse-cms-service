package com.radev.basecode.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "Product")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {
    @Column(name = "product_name", nullable = false, length = 250)
    private String productName;
}