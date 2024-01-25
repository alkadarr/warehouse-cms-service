package com.radev.project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "Warehouses")
@Getter
@Setter
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Warehouses SET is_deleted = 'true', deleted_date = GETDATE() WHERE id = ?")
@Where(clause = "is_deleted = false")
public class Warehouse extends BaseEntity{
    @Column(name = "name")
    private String name;
    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @Column(name = "category")
    private String category = "BRANCH";
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "is_active")
    private boolean isActive = true;
    @Column(name = "is_deleted")
    private boolean isDeleted = false;
    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    public Warehouse(String name, Location location, String category, Integer capacity) {
        this.name = name;
        this.location = location;
        if (category != null)
            this.category = category;
        this.capacity = capacity;
    }
}
